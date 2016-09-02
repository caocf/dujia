package cn.com.gome.dujia.controller.order;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.gome.dujia.enums.*;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.gome.dujia.controller.venue.CommonController;
import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.disconf.JsonMapDisconf;
import cn.com.gome.dujia.dto.PageInfo;
import cn.com.gome.dujia.exception.BizException;
import cn.com.gome.dujia.exception.ErrorCode;
import cn.com.gome.dujia.log.APILog;
import cn.com.gome.dujia.log.LogType;
import cn.com.gome.dujia.model.Order;
import cn.com.gome.dujia.model.OrderDetail;
import cn.com.gome.dujia.model.OrderPay;
import cn.com.gome.dujia.model.OrderRefund;
import cn.com.gome.dujia.model.OrderStatusLog;
import cn.com.gome.dujia.model.PushSap;
import cn.com.gome.dujia.model.SmsLog;
import cn.com.gome.dujia.model.ZbyProduct;
import cn.com.gome.dujia.model.ZbyProductPackage;
import cn.com.gome.dujia.model.ZbyProductPackageDetail;
import cn.com.gome.dujia.model.ZbyRecomInfo;
import cn.com.gome.dujia.service.OrderDetailService;
import cn.com.gome.dujia.service.OrderPayService;
import cn.com.gome.dujia.service.OrderRefundService;
import cn.com.gome.dujia.service.OrderService;
import cn.com.gome.dujia.service.PushSapService;
import cn.com.gome.dujia.service.SmsLogService;
import cn.com.gome.dujia.service.ZbyProductPackageDetailService;
import cn.com.gome.dujia.service.ZbyProductPackageService;
import cn.com.gome.dujia.service.ZbyProductService;
import cn.com.gome.dujia.service.ZbyRecomInfoService;
import cn.com.gome.dujia.vo.order.OrderQueryInfo;
import cn.com.gome.dujia.vo.order.TravlePassengerInfo;
import cn.com.gome.trip.unite.model.UserTraveler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.gome.commerce.Interface.smsmail.DubboSMSMessage;
import com.gome.commerce.Interface.smsmail.SmsMessage;
import com.gome.common.web.context.LoginContext;
import com.gome.plan.mybatis.pagehelper.Page;
import com.gome.plan.tools.utils.DateUtils;
import com.gome.plan.tools.utils.JsonUtils;
import com.gome.plan.tools.utils.RegularUtils;
import com.gome.plan.tools.utils.StringUtils;

/**
 * 订单管理
 * Created by liuhexin on 2016/4/14.
 */
@Controller
@RequestMapping(value = "/admin/order")
public class OrderController extends CommonController {
    public static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private ZbyProductService zbyProductService;
    @Autowired
    private OrderPayService orderPayService;
    @Autowired
    private OrderRefundService orderRefundService;
    @Autowired
    private ZbyProductPackageService zbyProductPackageService;
    @Autowired
    private ZbyProductPackageDetailService zbyProductPackageDetailService;
    @Autowired
    private ZbyRecomInfoService zbyRecomInfoService;
    @Autowired
    private PushSapService pushSapService;
    @Autowired
    private SmsLogService smsLogService;
    @Autowired
    private DubboSMSMessage dubboSMSMessage;

    private static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON).ignoreEmpty();

    /**
     * 动态获取分页订单列表
     *
     * @return
     */
    @RequestMapping(value = "/order_list")
    public String queryListByPage(HttpServletRequest request, Model model, OrderQueryInfo orderinfo) {
        String act = "";
        try {
            act = request.getParameter("ACT");// 页面操作行为,1=有操作，0=无操作
            // 获取分页参数
            int currentPage = 1;// 当前页码
            String currentPageP = request.getParameter("currentPage");
            if (currentPageP != null && currentPageP.trim().length() != 0) {
                currentPage = Integer.parseInt(currentPageP);
            }
            int perPageRows = 15;// 每页行数
            String perPageRowsP = request.getParameter("perPageRows");
            if (perPageRowsP != null && perPageRowsP.trim().length() != 0) {
                perPageRows = Integer.parseInt(perPageRowsP);
            }
            Page<OrderQueryInfo> pageList = new Page<>();
            // 获取分页数据
            pageList = orderService.queryOrderListByVO(orderinfo, currentPage, perPageRows);

            // 获取总行数
            long totalCount = pageList.getTotal();
            List<OrderQueryInfo> list = pageList.getResult();
            //获取景点信息
            for (OrderQueryInfo order : list) {
                // 设置状态显示名称
                String display = OrderStatus.getName(order.getOrderStatus());
                order.setOrderStatusDisplay(display);
                //设置游玩天数
                order.setTravelDayDisplay(zbyProductService.travlDaysFormat(order.getTravelDay()));
                //设置主题名称
                order.setRecomTitle(getRecomTitle(order.getProductId()));
                //设置景点名称
                order.setPackageName(getPackageDetailResName(order.getProductId(), order.getPackageId(), order.getOrderId()));
                //设置会员账号
                String userName = order.getUserName();
                order.setExtended1(order.getUserName());
                if (null != userName && userName.length() > 12) {
                    order.setUserName(StringUtils.left(order.getUserName(), 12) + "...");
                } else {
                    order.setUserName(order.getUserName());
                }

            }
            // 设置分页显示信息
            PageInfo page = new PageInfo();
            page.setCurrentPage(currentPage);
            page.setPerPageRows(perPageRows);
            page.setTotalRows(totalCount);
            // 设置返回信息
            model.addAttribute("order", orderinfo);
            model.addAttribute("orders", list);
            model.addAttribute("pageResult", page.getHtml());
            model.addAttribute("ACT", act);// 操作类型,用于页面处理

        } catch (Exception e) {
            logger.error("查询订单列表出错{}", e);
        }
        return "order/order_list";
    }

    /**
     * 根据productId获取主题名称
     *
     * @param productId
     * @return
     */
    public String getRecomTitle(String productId) {
        String title = "";
        if (StringUtils.isNotEmpty(productId)) {
            ZbyRecomInfo recomInfo = new ZbyRecomInfo();
            recomInfo.setProductId(productId);
            recomInfo.setIsDelete(false);
            List<ZbyRecomInfo> recomInfos = zbyRecomInfoService.getRecomInfoList(recomInfo);
            if (null != recomInfos && recomInfos.size() > 0) {
                for (ZbyRecomInfo recomInfo1 : recomInfos) {
                    title += recomInfo1.getTitle() + "，";
                }
                title = title.substring(0, title.length() - 1);
            }
        }
        return title;
    }

    /**
     * 根据productId,packageId获取景点名称
     *
     * @param productId
     * @param packageId
     * @return
     */
    public String getPackageDetailResName(String productId, String packageId, String orderId) {

        String resName = "";
        List<ZbyProductPackageDetail> packageDetails = zbyProductPackageDetailService.getPackageDetailList(productId, packageId, orderId);
        if (null != packageDetails && packageDetails.size() > 0) {
            for (ZbyProductPackageDetail packageDetail : packageDetails) {
                if (packageDetail.getResType() == 1) {
                    resName += packageDetail.getResName() + "，";
                }
            }
            if (resName.length() > 1) {
                resName = resName.substring(0, resName.length() - 1);
            }
        }
        return resName;
    }

    /**
     * 查询订单详情
     *
     * @return
     */
    @RequestMapping(value = "/orderDetail")
    public String orderDetail(HttpServletRequest request, Model model, String orderId) {
        String act = "";
        try {
            act = request.getParameter("ACT");// 页面操作行为,1=有操作，0=无操作
            /**
             * 订单信息+商品信息+支付信息+退款信+订单履历+短信日志+短信发送环节
             */
            /**
             * 获取订单信息(联系人信息)
             */
            Order order = orderService.queryOrderInfo(orderId);
            //使用扩展字段1存储“游玩天数”显示值
            order.setExtended1(zbyProductService.travlDaysFormat(order.getTravelDay()));
            // 使用扩展字段2存储“订单状态”显示值
            String display = OrderStatus.getName(order.getOrderStatus());
            order.setExtended2(display);
            /**
             * 获取出游人信息
             */
            List<TravlePassengerInfo> passengerInfos = new ArrayList<>();
            if (StringUtils.isNotEmpty(order.getTravelPeople())) {
                List<UserTraveler> travelers = jsonUtils.deserialize(order.getTravelPeople(), new TypeReference<List<UserTraveler>>() {
                });
                if (!CollectionUtils.isEmpty(travelers)) {
                    for (UserTraveler traveler : travelers) {
                        TravlePassengerInfo passengerInfo = new TravlePassengerInfo();
                        passengerInfo.setCertType(IdType.SFZ.getValue());//证件类型
                        passengerInfo.setIdNo(RegularUtils.sourceMosaic(traveler.getCertificateCode()));//证件号
                        passengerInfo.setMobile(traveler.getMobile());//电话号
                        passengerInfo.setName(traveler.getName());//姓名
                        passengerInfos.add(passengerInfo);
                    }
                }
            }
            /**
             * 根据订单id查询正常支付的支付信息
             */
            List<OrderPay> orderPays = orderPayService.getOrderPayListByOrderId(orderId);

            /**
             * 获取商品信息+套餐信息
             */
            ZbyProduct product = zbyProductService.selectByPrimaryKey(order.getProductId());
            ZbyProductPackage productPackage = zbyProductPackageService.getPackageByProductIDAndPackageID(order.getProductId(), order.getPackageId());
            String recomTitle = getRecomTitle(order.getProductId());
            /**
             * 获取酒店信息+景点信息
             */
            List<ZbyProductPackageDetail> packageDetails = zbyProductPackageDetailService.getPackageDetailList(order.getProductId(), order.getPackageId(), orderId);
            //order_detail信息中获取入住退房or游玩时间
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(order.getOrderId());
            List<OrderDetail> orderDetails = orderDetailService.getOrderOrderDetail(orderDetail);
            /**
             * 获取订单履历
             */
            List<OrderStatusLog> orderStatusLogs = orderService.queryOrderStatusLogsByOrderId(order.getOrderId());

            /**
             * 退款信息
             */
            OrderRefund orderRefund = new OrderRefund();
            orderRefund.setOrderId(orderId);
            List<OrderRefund> refunds = orderRefundService.queryOrderRefundList(orderRefund);

            /**
             * 获取订单短信日志列表
             */
            SmsLog sms = new SmsLog();
            sms.setOrderId(order.getOrderId());
            sms.setOrderType(1);// 设置 订单类型 (1订单，2退款单)
            List<SmsLog> smsList = orderService.querySmsLogList(sms);
            // 设置返回信息
            model.addAttribute("order", order);
            model.addAttribute("product", product);
            model.addAttribute("recomTitle", recomTitle);
            model.addAttribute("productPackage", productPackage);
            model.addAttribute("passengerInfos", passengerInfos);
            model.addAttribute("packageDetails", packageDetails);
            model.addAttribute("orderDetails", orderDetails);
            model.addAttribute("orderStatusLogs", orderStatusLogs);
            model.addAttribute("smsLogs", smsList);
            model.addAttribute("orderPays", orderPays);
            model.addAttribute("orderRefunds", refunds);
            model.addAttribute("RefundStatus", RefundStatus.values());
            model.addAttribute("payMode", JsonMapDisconf.getPayModeMap());
            model.addAttribute("ACT", act);
            model.addAttribute("pushSapFlag", pushSapFlag(orderId, ""));// 手动推送sap按钮标记

        } catch (Exception e) {
            logger.error("查询订单详情出错{}", e);
        }
        return "/order/order_detail";
    }

    /**
     * 如果 推送正向SAP > 推送阀值,需要重推：返回true
     *
     * @param orderId
     * @return
     */
    private boolean pushSapFlag(String orderId, String businessNo) {
        try {
            // 推送阀值
            Integer maxNumber = Integer.valueOf(GlobalDisconf.getPushSapMaxNumber());

            PushSap pushSapInfo = new PushSap();
            pushSapInfo.setOrderId(orderId); // 订单id
            pushSapInfo.setPushStatus(SapStatus.PUSH_SAP_FAIL.getValue()); // 推送失败
            pushSapInfo.setPushNum(maxNumber); // 最大推送次数

            // 查询推送正向SAP记录
            List<PushSap> pushSapList = pushSapService.queryPushPositiveSapByInfo(pushSapInfo);

            // 如果推送失败次数达到阀值,则显示手动推送sap按钮
            if (!CollectionUtils.isEmpty(pushSapList)) {// 会查出推送正向 sap收款 和 sap收入的记录
                return true;
            }
        } catch (Exception e) {
            logger.error("查询推送正向SAP出错", e);
        }
        return false;
    }

    /**
     * 获取订单状态选项
     *
     * @param response
     */
    @RequestMapping(value = "/getOrderStatus")
    public void queryOrderStatus(HttpServletResponse response, String selected) {
        StringBuffer sbf = new StringBuffer();
        sbf.append("<option value=\"\">全部状态</option>");
        for (int i = 0; i < OrderStatus.values().length; i++) {
            Integer value = OrderStatus.values()[i].getValue();
            // 去掉 超时支付 状态
            if (value == OrderStatus.TIMEOUT_PAY.getValue()) {
                continue;
            }
            String key = value.toString();
            sbf.append("<option ");
            if (selected.equals(key)) {
                sbf.append("selected=\"selected\" ");
            }
            sbf.append("value=\"" + key + "\">" + OrderStatus.values()[i].getName() + "</option>");

        }
        writeResult(response, sbf.toString());
    }

    /**
     * 获取游玩天数
     *
     * @param response
     * @param selected
     */
    @RequestMapping(value = "/getTravelDays")
    public void queryTravleDays(HttpServletResponse response, String selected) {
        StringBuffer sbf = new StringBuffer();
        sbf.append("<option value=\"\">全部</option>");
        List<Integer> list = zbyProductService.queryAllTravelDays();
        for (Integer integer : list) {
            Integer ir = integer.intValue();
            String value = zbyProductService.travlDaysFormat(ir);
            String key = ir.toString();
            sbf.append("<option ");
            if (selected.equals(key)) {
                sbf.append("selected=\"selected\" ");
            }
            sbf.append("value=\"" + key + "\">" + value + "</option>");
        }
        writeResult(response, sbf.toString());
    }


    /**
     * @param response
     * @param orderInfo
     * @throws UnsupportedEncodingException
     * @Description: 导出订单数据
     */
    @RequestMapping(value = "excelData")
    public void excelData(HttpServletResponse response, OrderQueryInfo orderInfo) {
        logger.info("开始导出订单数据......");
        WritableWorkbook wwb = null;
        WritableSheet ws = null;
        OutputStream os = null;
        // 定义数据格式化
        DecimalFormat df = new DecimalFormat("#.00");

        try {
            String fileName = this.encodeFilename(request, "度假订单导出_" + DateUtils.formatCurrent("yyyyMMdd") + ".xls");
            os = response.getOutputStream();
            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setContentType("application/vnd.ms-excel");
            wwb = Workbook.createWorkbook(os);
            ws = wwb.createSheet("订单信息", 0);
            ws.getSettings().setDefaultColumnWidth(15);
            // 创建表头
            WritableFont wfc = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
            WritableCellFormat wcfFC = new WritableCellFormat(wfc);
            String[] titleArr = {"订单编号", "第三方订单号", "会员账号", "会员ID", "手机号码", "所在城市", "线路名称", "主题标签", "景点名称", "游玩天数", "数量", "订单金额", "订单来源", "出游时间", "下单时间", "支付状态", "订单状态", "支付平台", "支付订单号"};
            for (int j = 0; j < titleArr.length; j++) {
                ws.addCell(new Label(j, 0, titleArr[j], wcfFC));
                ws.setColumnView(j, 20);
            }
            // 平台
            HashMap<Integer, String> orderSourceMap = new HashMap<Integer, String>();
            orderSourceMap.put(0, "WEB");
            orderSourceMap.put(1, "WEB");
            orderSourceMap.put(2, "APP");
            orderSourceMap.put(3, "Wap");

            // 如果支付订单号条件存在,只查询该支付订单号对应订单
            OrderPay ordPay = null;
            if (!StringUtils.isEmpty(orderInfo.getTransNo())) {
                OrderPay payParam = new OrderPay();
                payParam.setTransNo(orderInfo.getTransNo());
                ordPay = orderPayService.getOrderPay(payParam);
            }
            if (ordPay != null) {
                orderInfo.setOrderId(ordPay.getOrderId());// 设置支付订单id
            }
            List<OrderQueryInfo> orderList = orderService.queryOrderListByVO(orderInfo);

            WritableFont dataWfc = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
            WritableCellFormat dataWcfFC = new WritableCellFormat(dataWfc);
            dataWcfFC.setWrap(true);
            for (int i = 0; i < orderList.size(); i++) {
                OrderQueryInfo order = orderList.get(i);
                order.setTravelDayDisplay(zbyProductService.travlDaysFormat(order.getTravelDay()));
                ws.addCell(new Label(0, (i + 1), order.getOrderId(), dataWcfFC)); // 订单编号
                ws.addCell(new Label(1, (i + 1), order.getVenderOrderId(), dataWcfFC)); // 第三方订单编号
                ws.addCell(new Label(2, (i + 1), order.getUserName(), dataWcfFC));// 用户账号
                ws.addCell(new Label(3, (i + 1), order.getUserId(), dataWcfFC));// 用户ID
                ws.addCell(new Label(4, (i + 1), StringUtils.replaceString(order.getContactsTelphone(), 4, 7, "*"), dataWcfFC));// 手机号
                ws.addCell(new Label(5, (i + 1), order.getPackageCityName(), dataWcfFC));// 所在城市
                ws.addCell(new Label(6, (i + 1), order.getProductName(), dataWcfFC));// 线路名称
                ws.addCell(new Label(7, (i + 1), getRecomTitle(order.getProductId()), dataWcfFC));// 主题标签
                ws.addCell(new Label(8, (i + 1), getPackageDetailResName(order.getProductId(), order.getPackageId(), order.getOrderId()), dataWcfFC));// 景点名称
                ws.addCell(new Label(9, (i + 1), order.getTravelDayDisplay(), dataWcfFC));// 游玩天数
                ws.addCell(new Label(10, (i + 1), order.getBuyCount().toString(), dataWcfFC));// 数量
                ws.addCell(new Label(11, (i + 1), df.format(order.getOrderAmount()).toString(), dataWcfFC));// 订单金额
                ws.addCell(new Label(12, (i + 1), orderSourceMap.get(order.getOrderSource()), dataWcfFC));// 订单来源
                ws.addCell(new Label(13, (i + 1), DateUtils.format(order.getTravelStartTime(), "yyyy-MM-dd EEEE"), dataWcfFC));// 出游时间
                ws.addCell(new Label(14, (i + 1), DateUtils.format(order.getCreateTime(), "yyyy-MM-dd HH:mm:ss"), dataWcfFC));// 下单时间
                ws.addCell(new Label(15, (i + 1), PayStatus.getNameByValue(order.getPayStatus()), dataWcfFC));// 订单支付状态
                ws.addCell(new Label(16, (i + 1), OrderStatus.getName(order.getOrderStatus()), dataWcfFC));// 订单状态
                /**
                 * 根据订单id查询正常支付的支付信息
                 */
                String payMode = "";
                String transNo = "";
                try {
                    OrderPay payParm = new OrderPay();
                    payParm.setOrderId(order.getOrderId());
                    if (orderInfo.getTransNo() != null && orderInfo.getTransNo().trim() != "") {
                        payParm.setTransNo(orderInfo.getTransNo());
                    }
                    List<OrderPay> ops = orderPayService.getOrderPayList(payParm);
                    for (OrderPay op : ops) {
                        payMode += JsonMapDisconf.getPayModeMap().get(op.getPayModeSap()) + ",";
                        transNo += op.getTransNo() + ",";
                    }
                } catch (Exception e) {
                    logger.error("导出Excel,获取订单支付信息错误{}", e);
                }
                ws.addCell(new Label(17, (i + 1), payMode != "" ? payMode.substring(0, payMode.length() - 1) : "", dataWcfFC));// 支付平台
                ws.addCell(new Label(18, (i + 1), transNo != "" ? transNo.substring(0, transNo.length() - 1) : "", dataWcfFC));// 支付订单号

            }
            wwb.write();
            logger.info("订单导出完成......");
        } catch (Exception e) {
            logger.error("导出订单数据出错{}", e);

        } finally {
            try {
                wwb.close();
                os.close();
            } catch (Exception e) {
                logger.error("关闭出错");
            }
        }
    }

    /**
     * 短信推送
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/smsMessage")
    public void smsMessage(HttpServletRequest request, HttpServletResponse response) {
        String orderId = request.getParameter("orderId");// 订单ID
        int smsType = Integer.parseInt(request.getParameter("smsType"));// 短信类型
        String phone = request.getParameter("phone");// 手机号
        String smsCreateTime = request.getParameter("createTime");// 短信创建时间
        String returnMsg = "";
        logger.info("重新发送短信：短信模板{}, 手机号{}, 订单号{}", smsType, phone, orderId);
        try {
            /**
             * 获取订单信息
             */
            Order order = orderService.queryOrderInfo(orderId);
            // 获取短信参数
            Map<String, Object> paramMap = new HashMap<String, Object>();
            //1、支付提醒 2、超时取消 3、供应商退款 4、报警短信
            if (null != order) {
                if (smsType == 1) {
                    Date orderTime = order.getCreateTime();
                    Integer remindTime = Integer.parseInt(GlobalDisconf.getOrderRemindTime()); // 提醒时间
                    Date remTime = DateUtils.addMinutes(orderTime, remindTime); // 提醒时间
                    paramMap.put("orderId", order.getOrderId());
                    paramMap.put("date", DateUtils.format(remTime, DateUtils.LONG_WEB_FORMAT));
                } else if (smsType == 2) {
                    paramMap.put("orderId", order.getOrderId());
                } else if (smsType == 3) {
                    OrderRefund orderRefund = new OrderRefund();
                    orderRefund.setOrderId(orderId);
                    orderRefund.setRefundSource(RefundSource.GOME.ordinal());
                    List<OrderRefund> orderRefunds = orderRefundService.queryOrderRefundList(orderRefund);
                    if (null != orderRefunds) {
                        paramMap.put("orderId", order.getOrderId());
                        paramMap.put("refundAmount", orderRefunds.get(0).getRefundAmount());
                    }
                } else if (smsType == 4) {
                    OrderPay orderPay = new OrderPay();
                    orderPay.setOrderId(orderId); // 订单号
                    orderPay.setIsRepeatPay(false);      // 正常支付
                    OrderPay pay = orderPayService.getOrderPay(orderPay);//查询退款单对应支付信息
                    if (null != pay) {
                        paramMap.put("orderId", orderId);
                        paramMap.put("venderOrderId", order.getVenderOrderId());
                        paramMap.put("paytime", DateUtils.format(pay.getPayTime(), DateUtils.LONG_WEB_FORMAT));
                    }
                }
            }
            String diffId = null;// 模板ID
            String smsContent = null;// 短信内容
            // 根据短信类型 获取 枚举值
            for (int i = 0; i < SmsType.values().length; i++) {
                int tempv = SmsType.values()[i].getValue();
                if (tempv == smsType) {
                    diffId = SmsType.values()[i].getDiffId();
                    smsContent = SmsType.values()[i].buildSmsContent(paramMap);
                }
            }
            //发送短信对象
            SmsMessage smsMessage = new SmsMessage();
            smsMessage.setDiffId(diffId); //短信模板id
            smsMessage.setMobileId(phone); //手机号
            smsMessage.setIntervalTime(0); //即时发送
            smsMessage.setObject(paramMap); //短信内容
            //发送短信
            String result = dubboSMSMessage.sendMessageByJob(smsMessage);
            boolean res = true;
            //S:保存成功， E:数据校验不通过
            if (GlobalDisconf.smsResult.equals(result)) {
                returnMsg = "短信推送成功!";
                res = true;
            } else {
                returnMsg = "短信推送失败，请重试!";
                res = false;
            }
            // 获取当前登录用户
            String userId = "";
            String userName = "";
            LoginContext ux = getLoginUserInfo();
            if (ux != null) {
                userId = ux.getUserId();// 登录用户ID
                userName = ux.getPin();// 登录用户Name
            }
            // 记录短信操作日志
            SmsLog smsLog = new SmsLog();
            smsLog.setContent(smsContent);
            smsLog.setPhoneNumber(phone);
            smsLog.setOrderId(orderId);
            smsLog.setOrderType(1);// 1订单，2退款单
            smsLog.setStatus(res);// 短信推送结果
            smsLog.setUpdateTime(new Date());
            smsLog.setCreateTime(DateUtils.parse(smsCreateTime, DateUtils.LONG_WEB_FORMAT));// 短信创建时间
            smsLog.setUserId(userId);
            smsLog.setOperator(userName);
            smsLog.setSmsType(smsType);
            smsLogService.insert(smsLog);

        } catch (Exception e) {
            logger.error("短信推送失败!{}", e);
            returnMsg = "短信推送出错，请重试!";
        }
        logger.info(returnMsg);
        writeResult(response, returnMsg);
    }

    /**
     * 手动推送sap
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/sendSap")
    public void sendSap(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> rspMap = new HashMap<String, String>();
        String orderId = "";
        String resultCode = ErrorCode.E91100.name();

        try {
            orderId = request.getParameter("orderId"); // 订单Id
            // 记录日志
            APILog.build("手动推送正向sap", LogType.SAP_POSITIVE_INCOME).appendRequest("订单ID", orderId).record();

            if (StringUtils.isBlank(orderId)) {
                throw new BizException(ErrorCode.E30001.name());
            }

            // 推送阀值
            Integer maxNumber = Integer.valueOf(GlobalDisconf.pushSapMaxNumber);
            PushSap pushSapInfo = new PushSap();
            pushSapInfo.setOrderId(orderId); // 订单id
            pushSapInfo.setPushStatus(SapStatus.PUSH_SAP_FAIL.getValue()); // 推送失败
            pushSapInfo.setPushNum(maxNumber);// 最大推送次数

            /**
             * 根据订单id查询正常支付的支付信息
             */
            OrderPay orderPay = orderPayService.getOrderPayByOrderId(orderId);
            List<OrderPay> orderPays = new ArrayList<>();
            if (orderPay == null) {
                resultCode = ErrorCode.E51001.name();// 订单不存在支付信息
                logger.info("订单id=" + orderId + "," + resultCode);
            } else {
                orderPays.add(orderPay);
            }
            if (orderPays.size() > 0) {

                // 查询推送正向SAP记录
                List<PushSap> pushSapList = pushSapService.queryPushPositiveSapByInfo(pushSapInfo);

                if (pushSapList != null && pushSapList.size() > 0) {
                    for (PushSap push : pushSapList) {

                        if (SapType.POSITIVE_SAP_PAY.getValue() == push.getSapType()) {// 1:SAP正向收款
                            // 记录日志
                            APILog.build("手动推送正向sap收款", LogType.SAP_POSITIVE_PAY).appendRequest("订单ID", orderId).record();

                            pushSapService.pushPositiveSapPay(orderPays);// 推送正向SAP收款

                        } else if (SapType.POSITIVE_SAP_INCOME.getValue() == push.getSapType()) {// 2:SAP正向收入
                            // 记录日志
                            APILog.build("手动推送正向sap收入", LogType.SAP_POSITIVE_INCOME).appendRequest("订单ID", orderId).record();

                            pushSapService.pushPositiveSapIncome(orderPays);// 推送正向SAP收入
                        }
                        resultCode = ErrorCode.E90000.name();// 成功

                    }
                }
            }

        } catch (BizException biz) {
            resultCode = biz.getMessage();

        } catch (Exception e) {
            resultCode = ErrorCode.E90001.name();
            logger.error(resultCode, e);
        } finally {
            rspMap.put("resultCode", resultCode);
            String rspResult = new JsonUtils("JSON").serialize(rspMap);
            writeResult(response, rspResult);
        }
    }
}

package cn.com.gome.dujia.controller.refund;

import cn.com.gome.dujia.controller.venue.CommonController;
import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.disconf.JsonMapDisconf;
import cn.com.gome.dujia.dto.PageInfo;
import cn.com.gome.dujia.enums.*;
import cn.com.gome.dujia.exception.BizException;
import cn.com.gome.dujia.exception.ErrorCode;
import cn.com.gome.dujia.log.APILog;
import cn.com.gome.dujia.log.LogType;
import cn.com.gome.dujia.model.*;
import cn.com.gome.dujia.service.*;
import cn.com.gome.dujia.vo.refund.OrderRefundInfo;
import cn.com.gome.dujia.vo.sap.PushSapInfo;
import com.gome.plan.mybatis.pagehelper.Page;
import com.gome.common.web.context.LoginContext;
import com.gome.plan.tools.utils.DateUtils;
import com.gome.plan.tools.utils.JsonUtils;
import com.gome.plan.tools.utils.StringUtils;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.Boolean;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Description : 退款管理
 * Copyright : Copyright (c) 2008- 2015 All rights reserved. <br/>
 * Created Time : 2015年9月21日 上午11:24:34 <br/>
 *
 * @author WenJie Mai
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/admin/refund")
public class OrderRefundController extends CommonController {

    public static final Logger log = LoggerFactory.getLogger(OrderRefundController.class);

    @Resource
    private OrderRefundService orderRefundService;

    @Resource
    private OrderService orderService;

    @Autowired
    private OrderPayService orderPayService;

    @Resource
    private ZbyProductService zbyProductService;


    @Resource
    private PushSapService pushSapService;

    @Autowired
    private SapService sapService;

    private DecimalFormat numberFmt = new DecimalFormat("######0.00");


    /**
     * 查询退款单(列表)
     *
     * @param request
     * @param response
     * @return
     * @author WenJie Mai
     */
    @RequestMapping(value = "/queryOrderRefundList")
    public String queryOrderRefundList(HttpServletRequest request, HttpServletResponse response, Model model, OrderRefundInfo refundInfo) {

        List<OrderRefundInfo> refundList = null;
        List<Integer> refundStatusList = null;
        PageInfo page = new PageInfo();
        int currentPage = page.getCurrentPage();
        int perPageRows = page.getPerPageRows();
        Long totalCount = 0L;
        String queryType = "";
        String location = "";
        String reqcurrentPage = "";
        String reqPerPageRows = "";
        try {
            reqcurrentPage = request.getParameter("currentPage");
            reqPerPageRows = request.getParameter("perPageRows");
            queryType = request.getParameter("refundQueryType");

            // 记录日志
            APILog.build("查询退款单列表", LogType.REFUND)
                    .appendRequest("refundQueryType", queryType)
                    .appendRequest("orderId", refundInfo.getOrderId()).appendRequest("refundId", refundInfo.getRefundId())
                    .appendRequest("payTrxorderId", refundInfo.getPayTrxorderId()).appendRequest("userName", refundInfo.getUserName())
                    .appendRequest("refundStatus", refundInfo.getRefundStatus()).appendRequest("refundType", refundInfo.getRefundType())
                    .appendRequest("refundType", refundInfo.getRefundType())
                    .appendRequest("startTime", refundInfo.getStartTime()).appendRequest("endTime", refundInfo.getEndTime())
                    .appendRequest("auditeStartTime", refundInfo.getAuditeStartTime()).appendRequest("auditeEndTime", refundInfo.getAuditeEndTime())
                    .appendRequest("finishStartTime", refundInfo.getRefundFinishStartTime()).appendRequest("finishEndTime", refundInfo.getRefundFinishEndTime())
                    .appendRequest("currentPage", reqcurrentPage).appendRequest("perPageRows", reqPerPageRows)
                    .record();

            /**
             * INIT  : 待审核(新建和财务审核未通过)
             * ALL   : 所有--无导出功能
             * EXPORT: 所有--导出
             * AUDIT : 已审核(已去掉)
             */
            if ((StringUtils.isBlank(queryType)) || (!"INIT".equals(queryType) && !"ALL".equals(queryType) && !"EXPORT".equals(queryType))) {
                throw new BizException(ErrorCode.E90002);
            }

            if ("INIT".equals(queryType)) {
                refundStatusList = new ArrayList<Integer>();
                refundStatusList.add(RefundStatus.INIT.ordinal());        //新建
                refundStatusList.add(RefundStatus.AUDIT_FAILD.ordinal());// 财务审核未通过
                refundInfo.setRefundStatusList(refundStatusList);
                location = "度假待审批退款单管理";
            } else if ("ALL".equals(queryType)) {
                location = "度假退款单查询";
            } else {
                location = "度假退款单导出";
            }

            refundInfo.setRefundQueryType(queryType);

            if (!StringUtils.isBlank(reqcurrentPage)) {
                currentPage = Integer.valueOf(reqcurrentPage);
            }

            if (!StringUtils.isBlank(reqPerPageRows)) {
                perPageRows = Integer.valueOf(reqPerPageRows);
            }

            if (null != refundInfo.getAuditStatus() && refundInfo.getAuditStatus() == 1) {
                refundInfo.setAuditStatus(null);
                refundInfo.setNonAuditStatus(1);
            }
            // 分页查询
            Page<OrderRefundInfo> pagelist = orderRefundService.queryOrderRefundInfoList(refundInfo, currentPage, perPageRows);
            if (pagelist != null) {
                // 总结果数
                totalCount = pagelist.getTotal();
                // 数据List
                refundList = pagelist.getResult();
            }
            if (null != refundInfo.getNonAuditStatus()) {
                refundInfo.setAuditStatus(1);
            }
//			  refundInfo.setTotalRows(totalCount);

            page.setCurrentPage(currentPage);
            page.setPerPageRows(perPageRows);
            page.setTotalRows(totalCount);

            if (refundList != null && refundList.size() > 0) {
                for (OrderRefundInfo info : refundList) {
                    //支付平台
                    if (!StringUtils.isBlank(info.getOrderPayModeSap())) {
                        String platformName = JsonMapDisconf.getPayModeMap().get(info.getOrderPayModeSap());
                        if (!StringUtils.isBlank(platformName)) {
                            info.setPayModeName(platformName);
                        }
                    }

                    //退款状态
                    if (null != RefundStatus.getEnumByIndex(info.getRefundStatus())) {
                        info.setRefundStatusName(RefundStatus.getEnumByIndex(info.getRefundStatus()));
                    }

                    //退款类型
                    if (null != RefundPayType.getEnumByIndex(info.getRefundType())) {
                        info.setRefundPayTypeName(RefundPayType.getEnumByIndex(info.getRefundType()));
                    }

                    //退款原因
                    if (null != RefundReason.getEnumByIndex(info.getRefundType())) {
                        info.setRefundReasonName(RefundReason.getEnumByIndex(info.getRefundReason()));
                    }

                    //退款方式
                    if (null != RefundMode.getEnumByIndex(info.getRefundMode())) {
                        info.setRefundModeName(RefundMode.getEnumByIndex(info.getRefundMode()));
                    }
                }
            }
        } catch (BizException biz) {
            log.error("++++++++++++++++queryOrderRefundList_queryType:" + queryType + "_refundId:" + refundInfo.getRefundId() + "_BizException:" + biz.getMessage(), biz);
        } catch (Exception e) {
            log.error("++++++++++++++++queryOrderRefundList_queryType:" + queryType + "_refundId:" + refundInfo.getRefundId() + "_Exception:" + e.getMessage(), e);
        } finally {
            model.addAttribute("refundInfo", refundInfo);
            model.addAttribute("refundList", refundList);
            model.addAttribute("totalCount", totalCount);
            model.addAttribute("RefundReasonMap", RefundReason.values());
            model.addAttribute("refundPayTypeMap", RefundPayType.values());
            model.addAttribute("refundStatusMap", RefundStatus.values());
            model.addAttribute("platformMaps", JsonMapDisconf.getPayModeMap());
            model.addAttribute("pageResult", page.getHtml());
            model.addAttribute("location", location);
            model.addAttribute("RefundAuditStatus", RefundAuditStatus.values());
        }

        return "refund/queryOrderRefundList";
    }

    /**
     * 查询退款单(详情页)
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @author WenJie Mai
     */
    @RequestMapping(value = "/queryOrderRefundDetail")
    public String queryOrderRefundDetail(HttpServletRequest request, HttpServletResponse response, Model model) {

        String refundId = "";  // dj_order_refund表主键Id，非退款ID
        String refundStatusName = "";
        String orderSourceName = "";
        String orderStatusName = "";
        String payStatusName = "";
        String rTypeName = "";
        String refundReasonName = "";
        String refundModeName = "";
        String refundSourceName = "";
        String orderId = "";
        String showFlag = "";
        String oneLevelMenu = "";
        String twoLevelMenu = "";
        OrderRefund orderRefund = null;
        Order orderInfo = null;
        Boolean sendSapFlag = false;
        List<OrderStatusLog> refundLogList = null;
        List<OrderPay> orderPaysList = null;
        BigDecimal incentiveAmount = new BigDecimal(0); //优惠金额     TODO 由于目前业务不支持折扣等,故此优惠金额为0

        try {
            refundId = request.getParameter("refundId");
            showFlag = request.getParameter("showFlag");

            // 记录请求日志
            APILog.build("查询退款单详情", LogType.REFUND).appendRequest("refundId", refundId).appendRequest("showFlag", showFlag).record();

            /**
             * INIT  : 待审核(新建和财务审核未通过)
             * ALL   : 所有--无导出功能
             * EXPORT: 所有--导出
             * OTHER : 订单详情跳转到退款详情
             * AUDIT : 已审核(已去掉)
             */
            if ((StringUtils.isBlank(showFlag)) || (!"INIT".equals(showFlag) && !"ALL".equals(showFlag) && !"EXPORT".equals(showFlag) && !"OTHER".equals(showFlag))) {
                throw new BizException(ErrorCode.E90002);
            }

            if ("INIT".equals(showFlag)) {
                oneLevelMenu = "演出票待审批退款单管理";
                twoLevelMenu = "待审批退款单详情";
            } else if ("ALL".equals(showFlag) || "OTHER".equals(showFlag)) {
                oneLevelMenu = "演出票退款单查询";
                twoLevelMenu = "退款单查询详情";
            } else if ("EXPORT".equals(showFlag)) {
                oneLevelMenu = "演出票退款单导出";
                twoLevelMenu = "退款单导出详情";
            }

            if (StringUtils.isBlank(refundId)) {
                throw new BizException(ErrorCode.E60001);
            }

            // 查询退款单
            OrderRefund refund = new OrderRefund();
            refund.setId(Integer.valueOf(refundId));
            orderRefund = orderRefundService.getOrderRefund(refund);

            if (orderRefund == null) {
                throw new BizException(ErrorCode.E60001);
            }

            orderId = orderRefund.getOrderId();

            log.info("queryOrderRefundDetail_refundId:" + refundId + "_orderId:" + orderId);

            // 订单信息
            orderInfo = orderService.queryOrderInfo(orderId);

            if (orderInfo == null) {
                throw new BizException(ErrorCode.E50002);
            }

            // 订单来源
            if (orderInfo.getOrderSource() != null) {
                orderSourceName = OrderSource.getName(orderInfo.getOrderSource());
            }

            // 订单状态
            if (orderInfo.getOrderStatus() != null) {
                orderStatusName = OrderStatus.getName(orderInfo.getOrderStatus());
            }

            // 支付状态
            if (orderInfo.getOrderPay() != null) {
                payStatusName = PayStatus.getNameByValue(orderInfo.getOrderPay());
            }

            // 退款类型
            if (orderRefund.getRefundType() != null) {
                rTypeName = RefundPayType.getEnumByIndex(orderRefund.getRefundType()).getName();
            }

            // 退款方式
            if (orderRefund.getRefundMode() != null) {
                refundModeName = RefundMode.getEnumByIndex(orderRefund.getRefundMode()).getName();
            }

            // 退款状态
            if (orderRefund.getRefundStatus() != null) {
                refundStatusName = RefundStatus.getEnumByIndex(orderRefund.getRefundStatus()).getName();
            }

            // 退款原因
            if (orderRefund.getRefundReason() != null) {
                refundReasonName = RefundReason.getEnumByIndex(orderRefund.getRefundReason()).getName();
            }

            // 退款来源
            if (orderRefund.getRefundSource() != null) {
                refundSourceName = RefundSource.getEnumByIndex(orderRefund.getRefundSource()).getName();
            }

            // 支付信息
            orderPaysList = orderPayService.getOrderPayListByOrderId(orderId);

            // 非待审核显示退款日志
            if (RefundStatus.INIT.ordinal() != orderRefund.getRefundStatus()) {
                OrderStatusLog refundLog = new OrderStatusLog();
                refundLog.setOrderId(orderRefund.getRefundId());
                refundLog.setOrderType(OrderType.REFUND.getValue());
                refundLogList = orderService.queryOrderStatusLogList(refundLog);
            }

            // RB审核通过 才会推送sap
            if (RefundStatus.REFUND_COMPLETE.ordinal() == orderRefund.getRefundStatus()) {
                // 推送阀值
                Integer maxNumber = GlobalDisconf.getPushSapMaxNumber();

                log.info("++++++++++++++++++++++++queryOrderRefundDetail_orderId:" + orderId + "_maxNumber" + maxNumber);

                PushSapInfo pushSapInfo = new PushSapInfo();
                pushSapInfo.setOrderId(orderId);                                    // 订单id
                pushSapInfo.setBusinessNo(orderRefund.getRefundId());                // 退款单id
                pushSapInfo.setPushStatus(SapStatus.PUSH_SAP_FAIL.getValue());     // 推送失败
                pushSapInfo.setMaxPushNum(maxNumber);                                // 最大推送次数

                // 查询推送记录
                List<PushSap> pushSapList = pushSapService.queryPushSapByInfo(pushSapInfo);

                log.info("++++++++++++++++++++++++queryOrderRefundDetail_orderId:" + orderId + "_pushSapList_size:" + pushSapList == null ? "NULL" : String.valueOf(pushSapList.size()));

                // 如果推送失败次数达到阀值,则显示手动推送sap按钮
                if (!CollectionUtils.isEmpty(pushSapList)) {// 会查出sap逆向收款 和 sap逆向收入的记录
                    sendSapFlag = true;
                }
            }
        } catch (BizException biz) {
            log.error("++++++++++++++++queryOrderRefundDetail_refundId:" + refundId + "_showFlag:" + showFlag + "_BizException:" + biz.getMessage(), biz);
        } catch (Exception e) {
            log.error("++++++++++++++++queryOrderRefundDetail_refundId:" + refundId + "_showFlag:" + showFlag + "_Exception:" + e.getMessage(), e);
        } finally {
            model.addAttribute("showFlag", showFlag);
            model.addAttribute("sendSapFlag", sendSapFlag);
            model.addAttribute("orderRefund", orderRefund);
            model.addAttribute("orderInfo", orderInfo);
            model.addAttribute("incentiveAmount", incentiveAmount);
            model.addAttribute("orderSourceName", orderSourceName);
            model.addAttribute("orderStatusName", orderStatusName);
            model.addAttribute("payStatusName", payStatusName);
            model.addAttribute("refundStatusName", refundStatusName);
            model.addAttribute("refundSourceName", refundSourceName);
            model.addAttribute("refundTypeName", rTypeName);
            model.addAttribute("refundReasonName", refundReasonName);
            model.addAttribute("refundModeName", refundModeName);
            model.addAttribute("refundLogList", refundLogList);
            model.addAttribute("orderPaysList", orderPaysList);
            model.addAttribute("oneLevelMenu", oneLevelMenu);
            model.addAttribute("twoLevelMenu", twoLevelMenu);
            model.addAttribute("payModeMap", JsonMapDisconf.getPayModeMap());
        }

        return "refund/queryOrderRefundDetail";
    }

    /**
     * 提交退款审核、更改退款审核状态
     *
     * @param request
     * @param response
     * @author WenJie Mai
     */
    @RequestMapping(value = "/updateAuditeStatus")
    public void updateAuditeStatus(HttpServletRequest request, HttpServletResponse response) {

        String refundId = "";
        String auditeType = "";
        String auditeMemo = "";
        String resultCode = "";
        String auditName = "";
        String auditMessag = "";
        OrderRefund orderRefund = null;
        Order orderInfo = null;
        OrderStatusLog orderLog = null;
        OrderStatusLog refundLog = null;
        PrintWriter out = null;
        Map<String, String> rspMap = new HashMap<String, String>();

        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");


        try {
            out = response.getWriter();
            refundId = request.getParameter("refundId");
            auditeType = request.getParameter("auditFlag");
            auditeMemo = request.getParameter("memo");

            // 记录请求日志
            APILog.build("退款单审核", LogType.REFUND)
                    .appendRequest("refundId", refundId).appendRequest("auditeType", auditeType).appendRequest("auditeMemo", auditeMemo).record();

            /**
             *  1: 同意
             *  2: 拒绝
             */
            if (StringUtils.isBlank(auditeType) || (!"1".equals(auditeType) && !"2".equals(auditeType))) {
                throw new BizException(ErrorCode.E60002);
            }

            if (StringUtils.isBlank(refundId)) {
                throw new BizException(ErrorCode.E60001);
            }

            // 查询退款单
            OrderRefund refund = new OrderRefund();
            refund.setId(Integer.valueOf(refundId));
            orderRefund = orderRefundService.getOrderRefund(refund);

            if (orderRefund == null) {
                throw new BizException(ErrorCode.E60001);
            }

            // 只有待审核和财务审核未通过的退款单才可以审核
            if ((RefundStatus.INIT.ordinal() != orderRefund.getRefundStatus() && RefundStatus.AUDIT_FAILD.ordinal() != orderRefund.getRefundStatus())) {
                throw new BizException(ErrorCode.E60002);
            }

            String orderId = orderRefund.getOrderId();
            Integer refundReason = orderRefund.getRefundReason();

            log.info("++++++++++++++++++updateAuditeStatus_refundId:" + refundId + "_orderId:" + orderId + "_refundReason:" + refundReason);

            //TODO: 注意：财务审核通过的退款单、更改订单表的订单状态为：退款中(除重复支付退款的退款类型外)
            if ("1".equals(auditeType)) {
                auditMessag = RefundStatus.AUDIT_SUCCESS.getName();

                // 排除重复支付退款的数据
                if (RefundReason.REPEAT_PAY_REFUND.ordinal() != refundReason) {
                    // 查询订单
                    orderInfo = orderService.queryOrderInfo(orderId);

                    if (orderInfo != null) {
                        OrderRefund refundParam = new OrderRefund();
                        refundParam.setOrderId(orderInfo.getOrderId());
                        List<OrderRefund> refunds = orderRefundService.queryOrderRefundList(refundParam);
                        if (!CollectionUtils.isEmpty(refunds)) {
                            BigDecimal tatalRefundAmount = new BigDecimal(0);
                            for (OrderRefund rf : refunds) {
                                if (rf.getRefundReason() != RefundReason.REPEAT_PAY_REFUND.ordinal()) {
                                    // 排除重复支付
                                    tatalRefundAmount = tatalRefundAmount.add(rf.getReturnAmount());
                                }
                            }
                            if (orderInfo.getOrderAmount().compareTo(tatalRefundAmount) == 0) {
                                orderInfo.setOrderStatus(OrderStatus.WAIT_REFUND.getValue());
                                orderInfo.setUpdateTime(new Date());
                                // 订单日志
                                orderLog = new OrderStatusLog(orderId, false, OrderStatus.WAIT_REFUND.getValue(), OrderType.ORDER.getValue(), OrderStatus.WAIT_REFUND.getName(), new Date(), new Date());
                            } else {
                                orderInfo = null; //由于部分退款不更改订单状态，所以不需要更新订单记录
                                orderLog = null;
                            }
                        }
                    }
                }
            } else if ("2".equals(auditeType)) {
                auditMessag = RefundStatus.AUDIT_FAILD.getName();
            }

            auditMessag += "，备注: " + auditeMemo;

            LoginContext ux = getLoginUserInfo();
            if (ux != null) {
                auditName = ux.getPin(); // 获取当前登录用户名
                auditMessag += "，操作员[" + auditName + "]";
            }

            orderRefund.setAuditName(auditName);
            orderRefund.setRefundStatus(Integer.valueOf(auditeType));
            orderRefund.setAuditNote(auditeMemo);
            orderRefund.setAuditTime(new Date());
            orderRefund.setUpdateTime(new Date());

            // 退款单日志
            refundLog = new OrderStatusLog(orderRefund.getRefundId(), false, orderRefund.getRefundStatus(), OrderType.REFUND.getValue(), auditMessag, new Date(), new Date());

            orderRefundService.updateRefundAndOrder(orderInfo, orderLog, orderRefund, refundLog, false, false);

            resultCode = ErrorCode.E90000.name();
        } catch (BizException biz) {
            log.error("++++++++++++++++updateAuditeStatus_refundId:" + refundId + "_BizException:" + biz.getMessage(), biz);
            resultCode = biz.getMessage();
        } catch (Exception e) {
            log.error("++++++++++++++++updateAuditeStatus_refundId:" + refundId + "_Exception:" + e.getMessage(), e);
            resultCode = ErrorCode.E90001.name();
        } finally {
            rspMap.put("resultCode", resultCode);
            String rspResult = new JsonUtils("JSON").serialize(rspMap);
            // 记录日志
            log.info("++++++++++++++++updateAuditeStatus_refundId:" + refundId + "_rspResult:" + rspResult);
            if (out != null) {
                out.print(rspResult);
                out.close();
            }
        }
    }

    /**
     * 下载退款数据_Excel
     *
     * @author WenJie Mai
     */
    @RequestMapping(value = "/refundExcelData")
    public void refundExcelData(HttpServletRequest request, HttpServletResponse rsponse) {

        WritableWorkbook wwb = null;
        WritableSheet ws = null;
        OutputStream os = null;
        OrderRefundInfo info = new OrderRefundInfo();

        try {

            String refundId = StringUtils.nullTransEmpty(request.getParameter("refundId"));
            String orderId = StringUtils.nullTransEmpty(request.getParameter("orderId"));
            String userName = StringUtils.nullTransEmpty(request.getParameter("userName"));
            String payTrxorderId = StringUtils.nullTransEmpty(request.getParameter("payTrxorderId"));
            String refundReason = StringUtils.nullTransEmpty(request.getParameter("refundReason"));
            String refundType = StringUtils.nullTransEmpty(request.getParameter("refundType"));
            String refundStatus = StringUtils.nullTransEmpty(request.getParameter("refundStatus"));
            String startTime = StringUtils.nullTransEmpty(request.getParameter("startTime"));
            String endTime = StringUtils.nullTransEmpty(request.getParameter("endTime"));
            String auditeStartTime = StringUtils.nullTransEmpty(request.getParameter("auditeStartTime"));
            String auditeEndTime = StringUtils.nullTransEmpty(request.getParameter("auditeEndTime"));
            String finishStartTime = StringUtils.nullTransEmpty(request.getParameter("finishStartTime"));
            String finishEndTime = StringUtils.nullTransEmpty(request.getParameter("finishEndTime"));
            String venderOrderId = StringUtils.nullTransEmpty(request.getParameter("venderOrderId"));
            String orderPayModeSap = StringUtils.nullTransEmpty(request.getParameter("orderPayModeSap"));
            String auditStatus = StringUtils.nullTransEmpty(request.getParameter("auditStatus"));
            // 记录请求日志
            APILog.build("退款单数据导出", LogType.REFUND)
                    .appendRequest("orderId", orderId).appendRequest("refundId", refundId)
                    .appendRequest("payTrxorderId", payTrxorderId).appendRequest("userName", userName)
                    .appendRequest("refundStatus", refundStatus).appendRequest("refundReason", refundReason)
                    .appendRequest("refundType", refundType)
                    .appendRequest("startTime", startTime).appendRequest("endTime", endTime)
                    .appendRequest("auditeStartTime", auditeStartTime).appendRequest("auditeEndTime", auditeEndTime)
                    .appendRequest("finishStartTime", finishStartTime).appendRequest("finishEndTime", finishEndTime)
                    .appendRequest("venderOrderId", venderOrderId).appendRequest("orderPayModeSap", orderPayModeSap)
                    .appendRequest("auditStatus", auditStatus)
                    .record();

            info.setRefundId(refundId);
            info.setOrderId(orderId);
            info.setPayTrxorderId(payTrxorderId);
            info.setUserName(userName);
            info.setVenderOrderId(venderOrderId);
            info.setOrderPayModeSap(orderPayModeSap);
            //审核方式
            if (!StringUtils.isBlank(auditStatus)) {
                info.setAuditStatus(Integer.valueOf(auditStatus));
            }
            //退款状态
            if (!StringUtils.isBlank(refundStatus)) {
                info.setRefundStatus(Integer.valueOf(refundStatus));
            }

            //退款类型
            if (!StringUtils.isBlank(refundType)) {
                info.setRefundType(Integer.valueOf(refundType));
            }

            //退款原因
            if (!StringUtils.isBlank(refundReason)) {
                info.setRefundReason(Integer.valueOf(refundReason));
            }

            // 创建时间
            if (!StringUtils.isBlank(startTime)) {
                info.setStartTime(DateUtils.parseDate(startTime, "yyyy-MM-dd"));
            }

            if (!StringUtils.isBlank(endTime)) {
                info.setEndTime(DateUtils.parseDate(endTime, "yyyy-MM-dd"));
            }

            // 审批时间
            if (!StringUtils.isBlank(auditeStartTime)) {
                info.setAuditeStartTime(DateUtils.parseDate(auditeStartTime, "yyyy-MM-dd"));
            }

            if (!StringUtils.isBlank(auditeEndTime)) {
                info.setAuditeEndTime(DateUtils.parseDate(auditeEndTime, "yyyy-MM-dd"));
            }

            // 退款完成时间
            if (!StringUtils.isBlank(finishStartTime)) {
                info.setRefundFinishStartTime(DateUtils.parseDate(finishStartTime, "yyyy-MM-dd"));
            }

            if (!StringUtils.isBlank(finishEndTime)) {
                info.setRefundFinishEndTime(DateUtils.parseDate(finishEndTime, "yyyy-MM-dd"));
            }

            String fileName = this.encodeFilename(request, "度假退款单导出_" + DateUtils.formatCurrent("yyyyMMdd") + ".xls");

            os = rsponse.getOutputStream();
            rsponse.reset();
            rsponse.setHeader("Content-disposition", "attachment; filename=" + fileName);
            rsponse.setContentType("application/vnd.ms-excel");

            wwb = Workbook.createWorkbook(os);
            ws = wwb.createSheet("退款单信息", 0);
            ws.getSettings().setDefaultColumnWidth(15);

            //创建表头
            WritableFont wfc = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
            WritableCellFormat wcfFC = new WritableCellFormat(wfc);
            String titleArr[] = new String[18];

            //退款单信息
            String titleArr3[] = {"订单编号", "退款单号", "会员账号", "供应商订单号", "支付平台", "实际支付金额", "支付时间", "支付订单号", "退款创建时间", "退款金额", "退款类型", "退款原因", "退款状态", "退款方式", "审核人", "审核方式", "完成时间", "审核时间"};
            titleArr = titleArr3;

            for (int j = 0; j < titleArr.length; j++) {
                ws.addCell(new Label(j, 0, titleArr[j], wcfFC));
                ws.setColumnView(j, 20);
            }
            if (null != info.getAuditStatus() && info.getAuditStatus() == 1) {
                info.setAuditStatus(null);
                info.setNonAuditStatus(1);
            }
            // 退款结果集
            List<OrderRefundInfo> refundList = orderRefundService.queryOrderRefundInfo(info);
            if (null != info.getNonAuditStatus()) {
                info.setAuditStatus(1);
            }
            WritableFont dataWfc = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
            WritableCellFormat dataWcfFC = new WritableCellFormat(dataWfc);
            dataWcfFC.setWrap(true);

            for (int i = 0; i < refundList.size(); i++) {
                OrderRefundInfo refund = refundList.get(i);

                // 订单编号
                ws.addCell(new Label(0, (i + 1), refund.getOrderId(), dataWcfFC));

                // 退款单号
                ws.addCell(new Label(1, (i + 1), refund.getRefundId(), dataWcfFC));

                // 会员账号
                ws.addCell(new Label(2, (i + 1), refund.getUserName(), dataWcfFC));

                //供应商订单号
                ws.addCell(new Label(3, (i + 1), refund.getVenderOrderId(), dataWcfFC));

                // 支付平台
                if (StringUtils.isBlank(refund.getOrderPayModeSap())) {
                    ws.addCell(new Label(4, (i + 1), "", dataWcfFC));
                } else {
                    String platformName = JsonMapDisconf.getPayModeMap().get(refund.getOrderPayModeSap());
                    if (!StringUtils.isBlank(platformName)) {
                        ws.addCell(new Label(4, (i + 1), platformName, dataWcfFC));
                    } else {
                        ws.addCell(new Label(4, (i + 1), "", dataWcfFC));
                    }
                }

                // 实际支付金额
                if (refund.getOrderPayMoney() == null) {
                    ws.addCell(new Label(5, (i + 1), "0.00", dataWcfFC));
                } else {
                    ws.addCell(new Label(5, (i + 1), numberFmt.format(refund.getOrderPayMoney()), dataWcfFC));
                }

                // 支付时间
                if (refund.getOrderPayTime() == null) {
                    ws.addCell(new Label(6, (i + 1), "", dataWcfFC));
                } else {
                    ws.addCell(new Label(6, (i + 1), DateUtils.format(refund.getOrderPayTime(), "yyyy-MM-dd HH:mm:ss"), dataWcfFC));
                }

                // 支付订单号
                ws.addCell(new Label(7, (i + 1), refund.getPayTrxorderId(), dataWcfFC));

                // 退款创建时间
                if (refund.getCreateTime() == null) {
                    ws.addCell(new Label(8, (i + 1), "", dataWcfFC));
                } else {
                    ws.addCell(new Label(8, (i + 1), DateUtils.format(refund.getCreateTime(), "yyyy-MM-dd HH:mm:ss"), dataWcfFC));
                }

                // 退款金额
                if (refund.getReturnAmount() == null) {
                    ws.addCell(new Label(9, (i + 1), "0.00", dataWcfFC));
                } else {
                    ws.addCell(new Label(9, (i + 1), numberFmt.format(refund.getReturnAmount()), dataWcfFC));
                }

                // 退款类型
                if (refund.getRefundType() == null) {
                    ws.addCell(new Label(10, (i + 1), "", dataWcfFC));
                } else {
                    RefundPayType status = RefundPayType.getEnumByIndex(refund.getRefundType());
                    if (status == null) {
                        ws.addCell(new Label(10, (i + 1), "", dataWcfFC));
                    } else {
                        ws.addCell(new Label(10, (i + 1), status.getName(), dataWcfFC));
                    }
                }

                // 退款原因
                if (refund.getRefundReason() == null) {
                    ws.addCell(new Label(11, (i + 1), "", dataWcfFC));
                } else {
                    RefundReason status = RefundReason.getEnumByIndex(refund.getRefundReason());
                    if (status == null) {
                        ws.addCell(new Label(11, (i + 1), "", dataWcfFC));
                    } else {
                        ws.addCell(new Label(11, (i + 1), status.getValue(), dataWcfFC));
                    }
                }

                // 退款状态
                if (refund.getRefundStatus() == null) {
                    ws.addCell(new Label(12, (i + 1), "", dataWcfFC));
                } else {
                    RefundStatus status = RefundStatus.getEnumByIndex(refund.getRefundStatus());
                    if (status == null) {
                        ws.addCell(new Label(12, (i + 1), "", dataWcfFC));
                    } else {
                        ws.addCell(new Label(12, (i + 1), status.getName(), dataWcfFC));
                    }
                }

                // 退款方式
                if (refund.getRefundMode() == null) {
                    ws.addCell(new Label(13, (i + 1), "", dataWcfFC));
                } else {
                    RefundMode status = RefundMode.getEnumByIndex(refund.getRefundMode());
                    if (status == null) {
                        ws.addCell(new Label(13, (i + 1), "", dataWcfFC));
                    } else {
                        ws.addCell(new Label(13, (i + 1), status.getName(), dataWcfFC));
                    }
                }

                // 审核人
                ws.addCell(new Label(14, (i + 1), refund.getAuditName(), dataWcfFC));

                //审核方式
                if (refund.getAuditStatus() == null) {
                    ws.addCell(new Label(15, (i + 1), "", dataWcfFC));
                } else {
                    if (info.getAuditStatus() == 0) {
                        ws.addCell(new Label(15, (i + 1), RefundAuditStatus.getEnumByIndex(0).getName(), dataWcfFC));
                    } else {
                        ws.addCell(new Label(15, (i + 1), RefundAuditStatus.getEnumByIndex(1).getName(), dataWcfFC));
                    }
                }

                // 完成时间
                if (refund.getFinishTime() == null) {
                    ws.addCell(new Label(16, (i + 1), "", dataWcfFC));
                } else {
                    ws.addCell(new Label(16, (i + 1), DateUtils.format(refund.getFinishTime(), "yyyy-MM-dd HH:mm:ss"), dataWcfFC));
                }

                // 审核时间
                if (refund.getAuditTime() == null) {
                    ws.addCell(new Label(17, (i + 1), "", dataWcfFC));
                } else {
                    ws.addCell(new Label(17, (i + 1), DateUtils.format(refund.getAuditTime(), "yyyy-MM-dd HH:mm:ss"), dataWcfFC));
                }


            }
        } catch (Exception e) {
            log.error("++++++++++++++++refundExcelData_Exception:" + e.getMessage(), e);
        } finally {
            try {
                wwb.write();
                wwb.close();
                os.close();
            } catch (Exception e) {
                log.error("refundExcelData:" + e.getMessage(), e);
            }
        }
    }


    /**
     * 手动推送sap
     *
     * @param request
     * @param response
     * @author WenJie Mai
     */
    @RequestMapping(value = "/sendSap")
    public void sendSap(HttpServletRequest request, HttpServletResponse response) {

        PrintWriter out = null;
        Map<String, String> rspMap = new HashMap<String, String>();
        String refundId = "";
        String resultCode = ErrorCode.E91100.name();
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");

        try {
            out = response.getWriter();
            refundId = request.getParameter("refundId");  //dj_order_refund 表主键ID

            // 记录日志
            APILog.build("手动推送sap", LogType.REFUND).appendRequest("primative_refund_id", refundId).record();

            if (StringUtils.isBlank(refundId)) {
                throw new BizException(ErrorCode.E60001);
            }

            // 查询退款单
            OrderRefund refund = new OrderRefund();
            refund.setId(Integer.valueOf(refundId));
            OrderRefund orderRefund = orderRefundService.getOrderRefund(refund);

            if (orderRefund == null) {
                throw new BizException(ErrorCode.E60001);
            }

            // 只有RB审核通过的退款单才能手动退款
            if (RefundStatus.REFUND_COMPLETE.ordinal() != orderRefund.getRefundStatus()) {
                throw new BizException(ErrorCode.E60002);
            }

            PushSap pushSap = new PushSap(orderRefund.getOrderId(), orderRefund.getRefundId(), SapStatus.PUSH_SAP_FAIL.getValue());

            // 查询推送记录
            List<PushSap> pushSapList = pushSapService.queryPushSap(pushSap);

            log.info("+++++++++++++++++++++++sendSap_orderId:" + orderRefund.getOrderId() + "_sapList_Size_" + pushSapList == null ? "NULL" : String.valueOf(pushSapList.size()));

            if (pushSapList != null && pushSapList.size() > 0) {
                // 推送阀值
                Integer maxNumber = GlobalDisconf.getPushSapMaxNumber();
                List<OrderRefund> refundList = new ArrayList<OrderRefund>();
                refundList.add(orderRefund);

                for (PushSap push : pushSapList) {
                    log.info("+++++++++++++++++++++++sendSap_orderId:" + orderRefund.getOrderId() + "_businessNo:" + push.getBusinessNo() + "_sapType:" + push.getSapType() +
                            "_pushNum:" + push.getPushNum() + "_maxNumber:" + maxNumber);
                    if (push.getPushNum() >= maxNumber) {
                        if (SapType.NEGATIVE_SAP_PAY.getValue() == push.getSapType()) {
                            pushSapService.pushNegativeSapPay(refundList);
                        } else if (SapType.NEGATIVE_SAP_INCOME.getValue() == push.getSapType()) {
                            pushSapService.pushNegativeSapIncome(refundList);
                        }
                        resultCode = ErrorCode.E90000.name();
                    }
                }
            }
        } catch (BizException biz) {
            resultCode = biz.getMessage();
            log.error("++++++++++++++++sendSap_refundId:" + refundId + "_BizException:" + biz.getMessage(), biz);
        } catch (Exception e) {
            resultCode = ErrorCode.E90001.name();
            log.error("++++++++++++++++sendSap_refundId:" + refundId + "_Exception:" + e.getMessage(), e);
        } finally {
            rspMap.put("resultCode", resultCode);
            String rspResult = new JsonUtils("JSON").serialize(rspMap);

            if (out != null) {
                out.print(rspResult);
                out.close();
            }
        }
    }

}

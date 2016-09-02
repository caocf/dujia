package cn.com.gome.dujia.controller;

import cn.com.gome.dujia.constant.Constants;
import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.disconf.JsonMapDisconf;
import cn.com.gome.dujia.dto.*;
import cn.com.gome.dujia.enums.*;
import cn.com.gome.dujia.exception.BizException;
import cn.com.gome.dujia.model.*;
import cn.com.gome.dujia.service.*;
import cn.com.gome.dujia.vo.json.productinfo.ResourcesProductDate;
import cn.com.gome.dujia.vo.order.ValidatePriceResponse;
import cn.com.gome.trip.unite.model.TripFlag;
import cn.com.gome.trip.unite.model.UserConcat;
import cn.com.gome.trip.unite.model.UserTraveler;
import cn.com.gome.trip.unite.service.ConcatService;
import cn.com.gome.trip.unite.service.UserTravelerService;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.gome.plan.tools.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author wangweiran
 *         <p/>
 *         订单
 */
@Controller
@RequestMapping(value = "/order", produces = {"application/json;charset=UTF-8"})
public class OrderController extends BaseController {
    public static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON).ignoreEmpty();

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private OrderStatusLogService orderStatusLogService;

    @Autowired
    private OrderRefundService orderRefundService;

    @Autowired
    private ZbyProductService zbyProductService;

    @Autowired
    private ZbyProductPackageService packageService;

    @Autowired
    private ZbyProductPackageDetailService packageDetailService;

    @Autowired
    private CashierService cashierService;

    @Autowired
    private UserTravelerService userTravelerService;

    @Autowired
    private ConcatService concatService;

    @Autowired
    private TcService tcService;

    /**
     * 点击日期购买，跳转订单确认页面
     *
     * @param orderDto
     * @param request
     * @param response
     * @throws BizException return
     */
    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public Object orderBuy(OrderDto orderDto, HttpServletRequest request, HttpServletResponse response, ModelMap paramMap) {
        // 判断用户是否登录
        if (!this.checkLogin(request, response)) {
            return null;
        }
        try {
            if (null != orderDto && StringUtils.isNotEmpty(orderDto.getProductId()) && StringUtils.isNotEmpty(orderDto.getPackageId()) && StringUtils.isNotEmpty(orderDto.getSaleDate())) {
                //获取线路信息
                ZbyProduct product = zbyProductService.selectByPrimaryKey(orderDto.getProductId());
                if (null == product || product.getIsDelete()) {
                    logger.error("线路为空或已删除，套餐id:{}", orderDto.getProductId());
                    return GlobalDisconf.errorPage;
                }

                //获取线路出游天数
                Integer travelDays = zbyProductService.getTravlDaysByProductId(orderDto.getProductId());
                //获取价格信息
                ZbyProductPackagePrice productPackagePrice = packageService.selectPackagePriceByIdDate(orderDto.getProductId(), orderDto.getPackageId(), orderDto.getSaleDate());

                //获取套餐基本信息
                ZbyProductPackage productPackage = packageService.selectByPackageId(orderDto.getPackageId());

                // 验证套餐是否已删除
                if (null == productPackage || productPackage.getIsDelete()) {
                    logger.error("套餐ID:{}已删除！", productPackage.getPackageId());
                    return GlobalDisconf.errorPage;
                }
                // 是否开放售卖：true=1开放售卖；false =0限量售卖
                if (productPackagePrice.getInventoryStats() == 4 || (!productPackagePrice.getOpeningSale() && 
                		(productPackagePrice.getInventoryRemainder() == null || productPackagePrice.getInventoryRemainder() <= 0))) {
                    logger.error("套餐已售完！", productPackage.getPackageId());
                    return GlobalDisconf.errorPage;
                }
                
                //判断是否超过可预订时间
                String day = (DateUtils.format(orderDto.getSaleDate(), DateUtils.SHORT_FORMAT, 
                		DateUtils.WEB_FORMAT) + " " + productPackage.getAdvanceTime());
                String nowDay = DateUtils.format(new Date(), DateUtils.LONG_WEB_FORMAT);
                if(DateUtils.dateMinusDateForDays(nowDay, day, DateUtils.LONG_WEB_FORMAT) < productPackage.getAdvanceDay()){
                	 logger.error("套餐超过预定时间，请选择其他日期，套餐ID:{},预定时间:{}", productPackage.getPackageId(), 
                			 productPackage.getAdvanceTime());
                	 return ResponseDto.bulidFail("套餐超过预定日期，请选择其他日期！");
                }
                
                if (new Date().getTime() >= DateUtils.parse(day, DateUtils.LONG_WEB_FORMAT).getTime()) {
                    logger.error("套餐超过预定时间，请选择其他日期，套餐ID:{},预定时间:{}", productPackage.getPackageId(), 
                    		productPackage.getAdvanceTime());
                    return ResponseDto.bulidFail("套餐超过预定时间，请选择其他时间！");
                }

                //获取套餐酒店，景点详情信息
                List<ZbyProductPackageDetail> packageDetails = packageDetailService.queryPackageDetailByPackageId(orderDto.getPackageId());
                List<ZbyProductPackageDetail> hotes = new ArrayList<>();
                List<ZbyProductPackageDetail> sceneries = new ArrayList<>();
                if (CollectionUtils.isNotEmpty(packageDetails)) {
                    paramMap.put("needCard", false);
                    for (ZbyProductPackageDetail detail : packageDetails) {
                        if (null != detail.getNeedCard() && detail.getNeedCard().equals("身份证")) {
                            paramMap.put("needCard", true);
                        }
                        if (detail.getResType() == ResourceType.HOTEL.getValue()) {
                            hotes.add(detail);
                        } else if (detail.getResType() == ResourceType.SCENIC.getValue()) {
                            sceneries.add(detail);
                        }
                    }
                    Collections.sort(hotes);
                    Collections.sort(sceneries);
                }
                List<UserTraveler> userTravelers = new ArrayList<>();
                List<UserConcat> userConcats = new ArrayList<>();
                try {
                    //用户id
                    String userId = this.getUserId(request);
                    //获取常用旅客信息
                    userTravelers = userTravelerService.fetchUserConcatList(userId, TripFlag.HOTEL);
                    if (CollectionUtils.isNotEmpty(userTravelers)) {
                        for (UserTraveler traveler : userTravelers) {
                            traveler.setMobile(RegularUtils.sourceMosaic(traveler.getMobile()));
                            if (traveler.getCertificateType() == 1) {
                                //如果是身份证
                                traveler.setCertificateCode(RegularUtils.sourceMosaic(traveler.getCertificateCode()));
                            }
                        }
                    }
                    //获取常用联系人信息
                    userConcats = concatService.fetchUserConcatList(userId, TripFlag.HOTEL);
                    if (CollectionUtils.isNotEmpty(userConcats)) {
                        for (UserConcat userConcat : userConcats) {
                            userConcat.setMobile(RegularUtils.sourceMosaic(userConcat.getMobile()));
                            userConcat.setEmail(RegularUtils.sourceMosaic(userConcat.getEmail()));
                        }
                    }
                } catch (Exception e) {
                    logger.error("获取基础数据常用游客信息错误:{}", e);
                }

                if (null != productPackagePrice) {
                    Date sDate = productPackagePrice.getPackageSaleDate();
                    if (null != sDate) {
                        String packDate = DateUtils.format(sDate, "yyyy/MM/dd");
                        productPackagePrice.setPackDate(packDate);
                    }
                }
                if (StringUtils.isNotEmpty(product.getImageUrl())) {
                    //更新票品图片为小尺寸图
                    String imageUrl = product.getImageUrl();
                    String suffix = imageUrl.substring(imageUrl.lastIndexOf("."));
                    String imageName = imageUrl.substring(0, imageUrl.lastIndexOf("."));
                    product.setImageUrl(imageName + TcImageType.WH200x120.getValue() + suffix);
                }

                paramMap.put("product", product);
                paramMap.put("productPackage", productPackage);
                paramMap.put("productPackagePrice", productPackagePrice);
                paramMap.put("hotes", hotes);
                paramMap.put("sceneries", sceneries);
                paramMap.put("userTravelers", userTravelers);
                paramMap.put("userConcats", userConcats);
                paramMap.put("travelDays", travelDays);
            } else {
                logger.error("请求日期格式错误！ProductId{},PackageId{},SaleDate{}", orderDto.getProductId(), 
                		orderDto.getPackageId(), orderDto.getSaleDate());
                return GlobalDisconf.errorPage;
            }
        } catch (Exception e) {
            logger.error("点击日期购买，跳转订单确认页面：参数{}", jsonUtils.serialize(orderDto), e);
            return GlobalDisconf.errorPage;
        }
        return "order_confirm";
    }

    /**
     * 购买页(订单确认页)提交订单
     *
     * @param flag     flag=1 变价确认后提交，flag=0正常提交
     * @param request
     * @param response
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/submits", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseDto orderSubmits(String orderDtoStr, HttpServletRequest request, HttpServletResponse response) {
        // 判断用户是否登录
        if (!this.checkLogin(request, response)) {
            return null;
        }
        try {
            OrderDto orderDto = jsonUtils.deserialize(orderDtoStr, OrderDto.class);
            if (null != orderDto) {
                logger.info("提交订单参数{}", jsonUtils.serialize(orderDto));
                //用户id
                String userId = this.getUserId(request);
                //用户名
                String username = this.getUserName(request);
                orderDto.setUserId(userId);
                orderDto.setUsername(username);
                validateOrderDto(orderDto);
                //获取线路信息
                ZbyProductDetailRespDto productRespDto = zbyProductService.getProductAndPackageByIds(orderDto.getProductId(), orderDto.getPackageId());

                if (null == productRespDto.getDetail() || productRespDto.getDetail().getDelete()) {
                    logger.error("线路不可预订，ID:{}", productRespDto.getDetail().getProductId());
                    return ResponseDto.bulidFail("线路不可预订，请选择其他套餐！");
                }

                //获取套餐基本信息
                ZbyProductPackage productPackage = packageService.selectByPackageId(orderDto.getPackageId());

                // 验证套餐是否已删除
                if (null == productPackage || productPackage.getIsDelete()) {
                    logger.error("套餐不可预订，ID:{}！", productPackage.getPackageId());
                    return ResponseDto.bulidFail("套餐不可预订，请选择其他套餐！");
                }

                //判断是否超过可预订时间
                String day = (orderDto.getSaleDate() + " " + productPackage.getAdvanceTime());
                String nowDay = DateUtils.format(new Date(), DateUtils.LONG_WEB_FORMAT);
                if(DateUtils.dateMinusDateForDays(nowDay, day, DateUtils.LONG_WEB_FORMAT) < productPackage.getAdvanceDay()){
                	 logger.error("套餐超过预定时间，请选择其他日期，套餐ID:{},预定时间:{}", productPackage.getPackageId(), productPackage.getAdvanceTime());
                	 return ResponseDto.bulidFail("套餐超过预定日期，请选择其他日期！");
                }
                
                if (new Date().getTime() >= DateUtils.parse(day, DateUtils.LONG_WEB_FORMAT).getTime()) {
                    logger.error("套餐超过预定时间，请选择其他日期，套餐ID:{},预定时间:{}", productPackage.getPackageId(), productPackage.getAdvanceTime());
                    return ResponseDto.bulidFail("套餐超过预定时间，请选择其他时间！");
                }
                
                if(orderDto.getTravelerList().size() > orderDto.getBuyCount() * (productPackage.getAdultNum() + productPackage.getChildNum())){
                	return ResponseDto.bulidFail("出游人数超出套餐最大人数，请修改！");
                }

                //获取价格信息
                ZbyProductPackagePrice productPackagePrice = packageService.selectPackagePriceByIdDate(orderDto.getProductId(), orderDto.getPackageId(), orderDto.getSaleDate());

                //同程验价
                ValidatePriceResponse priceResponse = orderService.validatePrice(orderDto.getProductId(), orderDto.getPackageId(), orderDto.getSaleDate(), orderDto.getSaleDate(), productPackagePrice.getTcDirectPrice());

                if (!priceResponse.getCode().equals(OrderErrorCode.E200.getCode())) {
                    // flag=1 变价确认后提交，E502=变价标识
                    if (priceResponse.getCode().equals(OrderErrorCode.E502.getCode())) {
                        logger.error("同程验价失败，原因：{}", priceResponse.getMessage());
                        return ResponseDto.bulidConfirm("套餐价格已变动,新价格" + priceResponse.getData() + "是否继续预定");
                    } else {
                        return ResponseDto.bulidFail(GlobalDisconf.errorMessage);
                    }
                }

                //插入订单
                String orderId = "";
                if (null != productRespDto && null != productPackage && null != productPackagePrice) {
                    orderId = orderService.saveOrder(orderDto, productRespDto, productPackage, productPackagePrice);
                }
                if (StringUtils.isEmpty(orderId)) {
                    logger.error("创建订单失败，OrderDto：{}", jsonUtils.serialize(orderDto));
                    return ResponseDto.bulidFail(GlobalDisconf.errorMessage);
                }

                // 跳转订单支付页面地址
                StringBuffer payUrl = new StringBuffer();
                payUrl.append("/order/pay.html?orderId=").append(orderId).append("&isNormalPay=0");
                logger.info("提交成功跳转支付地址{}", payUrl);
                return ResponseDto.bulidSuccess(payUrl);
            } else {
                return ResponseDto.bulidFail(GlobalDisconf.errorMessage);
            }
        } catch (BizException biz) {
            logger.error("提交订单失败：参数{}", orderDtoStr, biz);
            return ResponseDto.bulidFail(GlobalDisconf.errorMessage);
        } catch (Exception e) {
            logger.error("提交订单失败：参数{}", orderDtoStr, e);
            MonitorUtil.recordOne("orderSubmits_exception");
            return ResponseDto.bulidFail(GlobalDisconf.errorMessage);
        }
    }

    /**
     * 重新获取套餐价格信息
     *
     * @param productId
     * @param packageId
     * @param saleDate
     * @param paramMap
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/getPackagePrice", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseDto getPackagePrice(@RequestParam String productId, @RequestParam String packageId, @RequestParam String saleDate, ModelMap paramMap) {
        try {
            ZbyProductPackagePrice productPackagePrice = null;
            if (StringUtils.isNotEmpty(productId) && StringUtils.isNotEmpty(packageId) && StringUtils.isNotEmpty(saleDate)) {
                //获取价格信息
                productPackagePrice = packageService.selectPackagePriceByIdDate(productId, packageId, saleDate);
                //同程验价
                ValidatePriceResponse priceResponse = orderService.validatePrice(productId, packageId, saleDate, saleDate, productPackagePrice.getTcDirectPrice());
                if (!priceResponse.getCode().equals(OrderErrorCode.E200.getCode())) {
                    if (priceResponse.getCode().equals(OrderErrorCode.E501.getCode())) {
                        logger.error("同程验价失败，原因：{}", priceResponse.getMessage());
                        return ResponseDto.bulidFail(priceResponse.getMessage());
                    } else if (priceResponse.getCode().equals(OrderErrorCode.E502.getCode())) {
                        logger.error("同程验价失败，原因：{}", priceResponse.getMessage());
                        return ResponseDto.bulidConfirm("套餐价格已变动,新价格" + priceResponse.getData() + "是否继续预定");
                    }
                }
            } else {
                logger.error("productId:{},packageId:{},saleDate:{}", productId, packageId, saleDate);
                throw new BizException("参数异常，请确认后再次尝试！");
            }
            if (null != productPackagePrice) {
                Date sDate = productPackagePrice.getPackageSaleDate();
                if (null != sDate) {
                    String packDate = DateUtils.format(sDate, "yyyy/MM/dd");
                    productPackagePrice.setPackDate(packDate);
                }
            }
            return ResponseDto.bulidSuccess(productPackagePrice);
        } catch (BizException biz) {
            return ResponseDto.bulidFail(GlobalDisconf.errorMessage);
        }
    }

    @RequestMapping(value = "/getResProductVerifyDate", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseDto getResProductVerifyDate(@RequestParam String packageId, @RequestParam String saleDate) {
        Map<String, String> requstMap = new HashMap<String, String>();
        requstMap.put("LineProductId", packageId);
        requstMap.put("Date", saleDate);
        List<ResourcesProductDate> resourcesProductDates = tcService.getResProductVerifyDate(requstMap);
        if(CollectionUtils.isEmpty(resourcesProductDates)){
        	return ResponseDto.bulidConfirm("获取景区信息失败，是否重新获取!");
        }
        else{
        	return ResponseDto.bulidSuccess(resourcesProductDates);
        }
    }

    /**
     * 跳转收银台支付页面
     *
     * @param orderId 订单id
     * @return
     */
    @RequestMapping(value = "/pay", method = {RequestMethod.GET})
    public String pay(@RequestParam(value = "orderId", required = true) String orderId, Integer isNormalPay,
                      HttpServletRequest request, HttpServletResponse response) {
        // 判断用户是否登录
        if (!this.checkLogin(request, response)) {
            return null;
        }
        logger.info("跳转收银台支付页面订单号{}", orderId);
        try {
            //用户id
            String userId = this.getUserId(request);
            Order orderInfo = orderService.getOrderByIds(userId, orderId);
            if (null == orderInfo) {
                logger.error("跳转收银台支付页面：用户id{}+订单号{}不存在", userId, orderId);
                return GlobalDisconf.errorPage;
            }

            // 判断订单状态
            if (!OrderStatus.WAIT_PAYMENT.getValue().equals(orderInfo.getOrderStatus()) || !PayStatus.WAIT_PAY.getValue().equals(orderInfo.getOrderPay())) {
                //订单状态不是待付款 或者 支付状态不是未支付的 不准去收银台支付
                logger.error("跳转收银台支付页面：订单号{}，订单状态{}，支付状态{}", orderId, orderInfo.getOrderStatus(), orderInfo.getOrderPay());
                return "redirect:" + "/order/order_detail/query.html?orderId=" + orderId;
            }

            // 判断订单是否超时
            Date invalidTime = DateUtils.addMinutes(orderInfo.getCreateTime(), Integer.parseInt(GlobalDisconf.orderLockTime));
            // 如果失效时间在当前时间之前
            if (invalidTime.before(new Date())) {
                // 订单超时
                logger.error("跳转收银台支付页面：订单号{}，下单时间{}，失效时间{}", orderId, DateUtils.format(orderInfo.getCreateTime(), DateUtils.LONG_WEB_FORMAT), DateUtils.format(invalidTime, DateUtils.LONG_WEB_FORMAT));
                return "redirect:" + "/order/order_detail/query.html?orderId=" + orderId;
            }


            //跳转收银台支付
            CashierRequest cashierRequest = new CashierRequest();

            cashierRequest.setSiteAccount(GlobalDisconf.siteAccount);// 商户号
            cashierRequest.setOrderNo(orderId);// 订单号
            cashierRequest.setUserNo(orderInfo.getUserId());//用户编号
            cashierRequest.setPageBackUrl(GlobalDisconf.pageBackUrl);// 页面回调地址
            cashierRequest.setNotifyUrl(GlobalDisconf.notifyUrl);// 点对点回调地址
            cashierRequest.setOrderType(GlobalDisconf.orderType);//订单类型
            cashierRequest.setIsSupportStages("0");//1：支持分期、0：不支持分期（商品是否支持分期）
            cashierRequest.setIsByStages("0");//用户选择支付方式,1：分期置顶显示、0：分期不置顶显示（用户是否选择分期支付）、2：企业网银支付
            cashierRequest.setOrderExpireMsg(GlobalDisconf.orderLockTime + "M");//过期时长
            cashierRequest.setOrderExpireTime(DateUtils.format(invalidTime, DateUtils.LONG_FORMAT));//过期时间

            /**
             * 以下参数不需要签名(收银台埋码)
             */
            cashierRequest.setIsNormalPay(null != isNormalPay ? 0 : 1);//是否正常支付
            cashierRequest.setSiteName(GlobalDisconf.getSapSkuName());

            /**
             * 以下为系统参数
             */
            cashierRequest.setPayMd5Key(GlobalDisconf.payMd5Key);//密钥(MD5签名)
            cashierRequest.setPayAesKey(GlobalDisconf.payAesKey);//密钥(AES签名)
            cashierRequest.setPayUrl(GlobalDisconf.payUrl);//收银台支付地址

            cashierRequest.setIsSupportStages("0");//1：支持分期、0：不支持分期（商品是否支持分期）
            cashierRequest.setIsByStages("0");//用户选择支付方式,1：分期置顶显示、0：分期不置顶显示（用户是否选择分期支付）、2：企业网银支付
            cashierRequest.setNotifyUrl(GlobalDisconf.notifyUrl);// 点对点回调地址
            cashierRequest.setOrderExpireMsg(GlobalDisconf.orderLockTime + "M");//过期时长
            cashierRequest.setOrderExpireTime(DateUtils.format(invalidTime, DateUtils.LONG_FORMAT));//过期时间
            cashierRequest.setOrderNo(orderId);// 订单号
            cashierRequest.setOrderType(GlobalDisconf.orderType);//订单类型
            cashierRequest.setPageBackUrl(GlobalDisconf.pageBackUrl);// 页面回调地址
//          cashierRequest.setPayQueryUrl(GlobalDisconf.payQueryUrl);//收银台支付查询接口地址

            cashierRequest.setUserNo(orderInfo.getUserId());//用户编号
            cashierRequest.setIsNormalPay(null != isNormalPay ? 0 : 1);//是否正常支付
            if (GlobalDisconf.env.equals(Constants.PRD)) {
                //生产环境
                cashierRequest.setPayMoney(String.valueOf(BigDecimalUtil.changeY2F(orderInfo.getPaymentAmount())));//支付金额(单位：分)
                cashierRequest.setOrderMoney(String.valueOf(BigDecimalUtil.changeY2F(orderInfo.getOrderAmount())));//订单总金额(单位：分)
            } else {
                //测试环境1分钱
                cashierRequest.setPayMoney("1");//支付金额(单位：分)
                cashierRequest.setOrderMoney("1");//订单总金额(单位：分)
            }
            //参数签名加密
            String payUrl = cashierService.cashierPay(cashierRequest);
            if (StringUtils.isEmpty(payUrl)) {
                logger.error("跳转收银台支付页面失败：订单号{}", orderId);
                return GlobalDisconf.errorPage;
            }
            logger.info("收银台支付请求地址：{}", payUrl);

            // 重定向收银台支付页面
            return "redirect:" + payUrl;
        } catch (Exception e) {
            logger.error("跳转收银台支付页面失败：订单号{}", orderId, e);
            MonitorUtil.recordOne("order_pay_exception");
            return GlobalDisconf.errorPage;
        }
    }

    /**
     * 支付成功，回调页面
     *
     * @param model
     * @param rspInfo
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/success", method = {RequestMethod.GET})
    public String success(ModelMap model, @RequestParam(value = "rspInfo", required = true) String rspInfo, HttpServletRequest request, HttpServletResponse response) {
        try {
            // 判断用户是否登录
            this.checkLogin(request, response);
            logger.info("收银台支付成功回调页面：参数{}", rspInfo);

            // AES解密
            String decrypt = EncrUtil.decrypt(GlobalDisconf.payAesKey, rspInfo);
            if (StringUtils.isEmpty(decrypt)) {
                logger.error("收银台支付成功回调页面AES解密失败");
                return GlobalDisconf.errorPage;
            }

            logger.info("收银台支付成功回调页面：AES解密结果{}", decrypt);

            // 把参数解析存放到LinkedHashMap中（有序的）
            Map<String, String> paramsMap = new LinkedHashMap<String, String>();
            for (String str : decrypt.split("&")) {
                if (!str.endsWith("=")) {
                    paramsMap.put(str.split("=")[0], str.split("=")[1]);
                }
            }

            // 根据订单id查询订单信息
            String userId = this.getUserId(request);
            String orderId = paramsMap.get("orderNo");
            Order orderInfo = orderService.getOrderByIds(userId, orderId);
            if (null == orderInfo) {
                logger.error("收银台支付成功回调页面：用户id{}+订单号{}不存在", userId, orderId);
                return GlobalDisconf.errorPage;
            }

            // 出游人信息
            List<UserTraveler> travelerList = null;
            if (!StringUtils.isBlank(orderInfo.getTravelPeople())) {
                travelerList = jsonUtils.deserialize(orderInfo.getTravelPeople(), new TypeReference<List<UserTraveler>>() {
                });
            }

            model.addAttribute("orderInfo", orderInfo);
            model.addAttribute("travelerList", travelerList);
            model.addAttribute("payMode", JsonMapDisconf.getPayModeMap().get(paramsMap.get("sapCode")));
            return "order_success";
        } catch (Exception e) {
            logger.error("收银台支付成功回调页面处理失败：", e);
            MonitorUtil.recordOne("pay_success_exception");
            return GlobalDisconf.errorPage;
        }
    }

    private void validateOrderDto(OrderDto orderDto) throws BizException {
        if (null == orderDto) {
            throw new BizException("参数错误，请确认后尝试");
        }
        if (StringUtils.isEmpty(orderDto.getProductId())) {
            logger.error("线路id为空,OrderDto:{}", jsonUtils.serialize(orderDto));
            throw new BizException("线路id为空！");
        }
        if (StringUtils.isEmpty(orderDto.getPackageId())) {
            logger.error("套餐id为空,OrderDto:{}", jsonUtils.serialize(orderDto));
            throw new BizException("套餐id为空！");
        }
        if (StringUtils.isEmpty(orderDto.getSaleDate())) {
            logger.error("出游日期为空,OrderDto:{}", jsonUtils.serialize(orderDto));
            throw new BizException("出游日期为空！");
        }
        if (orderDto.getBuyCount() < 1) {
            logger.error("套餐数小于1,OrderDto:{}", jsonUtils.serialize(orderDto));
            throw new BizException("套餐数不能小于1！");
        }
        if (orderDto.getAdultNum() + (orderDto.getChildNum() == null ? 0 : orderDto.getChildNum()) < 1) {
            logger.error("套餐使用人数不能小于1,OrderDto:{}", jsonUtils.serialize(orderDto));
            throw new BizException("套餐使用人数不能小于1！");
        }
        if (CollectionUtils.isEmpty(orderDto.getTravelerList())) {
            logger.error("出游人人数不能小于1,OrderDto:{}", jsonUtils.serialize(orderDto));
            throw new BizException("出游人人数不能小于1！");
        }
        if (CollectionUtils.isEmpty(orderDto.getOrderDetails())) {
            logger.error("出行详情不能为空,OrderDto:{}", jsonUtils.serialize(orderDto));
            throw new BizException("出行详情不能为空！");
        } else {
            for (OrderDetail orderDetail : orderDto.getOrderDetails()) {
                if (null == orderDetail.getResourceId()) {
                    logger.error("出行详情资源号不能为空,OrderDto:{}", jsonUtils.serialize(orderDto));
                    throw new BizException("出行详情资源号不能为空！");
                }
                if (orderDetail.getUseStartTime() == null) {
                    logger.error("出行详情日期不能为空,OrderDto:{}", jsonUtils.serialize(orderDto));
                    throw new BizException("出行详情日期不能为空！");
                }
            }
        }

        if (CollectionUtils.isNotEmpty(orderDto.getTravelerList())) {
            //验证出行人的信息是否正确
            List<UserTraveler> travelers = new ArrayList<>();

            for (int i = 0; i < orderDto.getTravelerList().size(); i++) {
                UserTraveler newTraveler = orderDto.getTravelerList().get(i);

                if (null == newTraveler.getId()) {
                    if (StringUtils.isEmpty(newTraveler.getName())) {
                        logger.error("出游人姓名为空,OrderDto:{}", jsonUtils.serialize(orderDto));
                        throw new BizException("出游人姓名为空！");
                    }
                    if (StringUtils.isNotEmpty(newTraveler.getCertificateCode()) &&
                            !RegularUtils.validate(newTraveler.getCertificateCode(), RegularUtils.IDCARD)) {
                        logger.error("出游人身份证号不正确,OrderDto:{}", jsonUtils.serialize(orderDto));
                        throw new BizException("出游人身份证号不正确");
                    }
                    if (!RegularUtils.validate(newTraveler.getMobile(), RegularUtils.MOBILE)) {
                        logger.error("出游人电话不正确,OrderDto:{}", jsonUtils.serialize(orderDto));
                        throw new BizException("出游人电话不正确");
                    }
                    newTraveler.setUserId(orderDto.getUserId());
                    newTraveler.setTripFlag(TripFlag.HOTEL.getFlag());
                    newTraveler.setCertificateType(1);
                    newTraveler.setCanUse(1);
                    newTraveler.setCreateTime(new Date());
                    newTraveler.setUpdateTime(new Date());
                    travelers.add(newTraveler);

                    userTravelerService.addUserTraveler(newTraveler);
                } else {
                    // 已存在出游人
                    List<UserTraveler> oldTravelers = userTravelerService.fetchUserConcatList(orderDto.getUserId(), TripFlag.HOTEL);

                    if (CollectionUtils.isNotEmpty(oldTravelers)) {
                        boolean isHave = false;
                        for (UserTraveler oldTraveler : oldTravelers) {
                            if (oldTraveler.getId().equals(newTraveler.getId())) {

                                if (StringUtils.isNotEmpty(newTraveler.getCertificateCode()) && RegularUtils.validate(newTraveler.getCertificateCode(), RegularUtils.IDCARD) &&
                                        (oldTraveler.getCertificateCode() == null || !oldTraveler.getCertificateCode().equals(newTraveler.getCertificateCode()))) {
                                    oldTraveler.setCertificateCode(newTraveler.getCertificateCode());
                                    isHave = true;
                                }

                                if (RegularUtils.validate(newTraveler.getMobile(), RegularUtils.MOBILE) && !oldTraveler.getMobile().equals(newTraveler.getMobile())) {
                                    oldTraveler.setMobile(newTraveler.getMobile());
                                    isHave = true;
                                }

                                if (StringUtils.isNotEmpty(newTraveler.getName()) && !newTraveler.getName().equals(oldTraveler.getName())) {
                                    oldTraveler.setName(newTraveler.getName());
                                    isHave = true;
                                }
                                
                                if (isHave) {
                                    userTravelerService.updateTraveler(oldTraveler);
                                }
                                if(StringUtils.isEmpty(newTraveler.getCertificateCode())){
                                	oldTraveler.setCertificateCode(null);
                                }
                                travelers.add(oldTraveler);
                            }
                        }
                    }
                }
            }
            orderDto.setTravelerList(travelers);
        }
        if (null == orderDto.getUserConcat()) {
            logger.error("联系人不能为空,OrderDto:{}", jsonUtils.serialize(orderDto));
            throw new BizException("联系人不能为空！");
        } else if (null == orderDto.getUserConcat().getId()) {
            if (!RegularUtils.validate(orderDto.getUserConcat().getMobile(), RegularUtils.MOBILE)) {
                logger.error("联系人电话不正确,OrderDto:{}", jsonUtils.serialize(orderDto));
                throw new BizException("联系人电话不正确");
            }
            if (StringUtils.isNotEmpty(orderDto.getUserConcat().getEmail()) && !RegularUtils.validate(orderDto.getUserConcat().getEmail(), RegularUtils.EMAIL)) {
                logger.error("联系人电话不正确,OrderDto:{}", jsonUtils.serialize(orderDto));
                throw new BizException("联系人电话不正确");
            }
            orderDto.getUserConcat().setUserId(orderDto.getUserId());
            orderDto.getUserConcat().setTripFlag(TripFlag.HOTEL.getFlag());
            orderDto.getUserConcat().setCanUse(1);
            orderDto.getUserConcat().setCreateTime(new Date());
            orderDto.getUserConcat().setUpdateTime(new Date());
            concatService.addConcat(orderDto.getUserConcat());
        } else if (orderDto.getUserConcat().getId() != null) {
            UserConcat newConcat = orderDto.getUserConcat();
            List<UserConcat> userConcats = concatService.fetchUserConcatList(orderDto.getUserId(), TripFlag.HOTEL);
            if (CollectionUtils.isNotEmpty(userConcats)) {
                for (UserConcat concat : userConcats) {
                    if (concat.getId().equals(orderDto.getUserConcat().getId())) {
                        boolean isHave = false;
                        if (!concat.getName().equals(newConcat.getName()) && RegularUtils.validate(newConcat.getName(), RegularUtils.CHINESE_NAME)) {
                            concat.setName(newConcat.getName());
                            isHave = true;
                        }
                        if (RegularUtils.validate(newConcat.getMobile(), RegularUtils.MOBILE)) {
                            concat.setMobile(newConcat.getMobile());
                            isHave = true;
                        }
                        if (StringUtils.isNotEmpty(newConcat.getEmail()) && RegularUtils.validate(newConcat.getEmail(), RegularUtils.EMAIL)) {
                            concat.setEmail(newConcat.getEmail());
                            isHave = true;
                        }
                        if (isHave)
                            concatService.updateConcat(concat);
                        orderDto.setUserConcat(concat);
                    }
                }
            }
        }
    }


    /**
     * 获取订单列表
     *
     * @param model
     * @param req
     * @param request
     * @param response
     * @return
     * @author WenJie Mai
     */
    @RequestMapping(value = "/list")
    public String queryOrderListByTime(Model model, QueryUserOrderReq req, HttpServletRequest request, HttpServletResponse response) {
        //判断用户是否登录
        if (!this.checkLogin(request, response)) {
            return null;
        }
        logger.info("获取订单列表参数{}", jsonUtils.serialize(req));

        //用户id
        String userId = this.getUserId(request);
        req.setUserId(userId);
        QueryUserOrderResp result = orderService.queryUserOrderInfos(req);

        int pageIndex = result.getPi();
        int totalPages = result.getTp();
        List<Order> orderPageList = result.getOl();

        // 获取未付款订单数量
        Order oi = new Order();
        oi.setUserId(userId);
        oi.setOrderStatus(OrderStatus.WAIT_PAYMENT.getValue());

        int waitPay_count = orderService.countUserOrderByStatus(oi);

        for (Order order : orderPageList) {
            // 计算订单支付剩余时间
            long surplusTime = (60 * 60 * 1000 - (System.currentTimeMillis() - order.getCreateTime().getTime()));
            order.setSurplusTime(surplusTime > 0 ? surplusTime : 0);
        }

        // 订单状态
        String orderStatus = null;
        if (req.getOrderStatus() != null) {
            orderStatus = req.getOrderStatus().toString();
        }

        // 返回结果集
        model.addAttribute("orderInfos", orderPageList); // 订单数据
        model.addAttribute("pageIndex", pageIndex); // 当前页号
        model.addAttribute("totalPages", totalPages); // 总页数
        model.addAttribute("timeSlot", req.getDateType()); // 查询参数：日期
        model.addAttribute("orderId", StringUtils.nullTransEmpty(req.getOrderNo())); // 查询参数：订单编号
        model.addAttribute("waitPay", waitPay_count); // 未支付订单数量
        model.addAttribute("orderStatus", orderStatus);//订单状态

        return "order_list";
    }


    /**
     * 获取订单详情
     *
     * @param orderId
     * @param request
     * @param response
     * @param map
     * @return
     * @author WenJie Mai
     */
    @RequestMapping(value = "/order_detail/query", method = {RequestMethod.GET})
    public String queryOrderDetail(@RequestParam(value = "orderId", required = true) String orderId, HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        //判断用户是否登录
        if (!this.checkLogin(request, response)) {
            return null;
        }
        //用户id
        String userId = this.getUserId(request);
        logger.info("获取订单参数用户ID{},订单号{}", userId, orderId);

        if (StringUtils.isEmpty(orderId)) {
            return GlobalDisconf.getErrorPage();
        }

        // 订单信息
        Order orderInfo = orderService.getOrderByIds(userId, orderId);
        if (null == orderInfo) {
            return GlobalDisconf.getErrorPage();
        }

        List<OrderDetail> hotelList = new ArrayList<>();
        List<OrderDetail> sceniList = new ArrayList<>();
        BigDecimal refundTotalAmount = new BigDecimal(0); // 退款总金额

        // 出游人信息
        List<UserTraveler> travelerList = jsonUtils.deserialize(orderInfo.getTravelPeople(), new TypeReference<List<UserTraveler>>() {
        });
        //敏感信息马赛克

        orderInfo.setContactsTelphone(RegularUtils.sourceMosaic(orderInfo.getContactsTelphone()));
        orderInfo.setContactsEmail(RegularUtils.sourceMosaic(orderInfo.getContactsEmail()));

        if (CollectionUtils.isNotEmpty(travelerList)) {
            for (UserTraveler traveler : travelerList) {
                traveler.setCertificateCode(RegularUtils.sourceMosaic(traveler.getCertificateCode()));
                traveler.setEmail(RegularUtils.sourceMosaic(traveler.getEmail()));
                traveler.setMobile(RegularUtils.sourceMosaic(traveler.getMobile()));
            }
        }

        // 订单详情
        OrderDetail orderDetailParam = new OrderDetail();
        orderDetailParam.setOrderId(orderInfo.getOrderId());
        orderDetailParam.setUpdateTime(null);
        List<OrderDetail> orderDetailList = orderDetailService.getOrderOrderDetail(orderDetailParam);

        if (!CollectionUtils.isEmpty(orderDetailList)) {
            for (OrderDetail tail : orderDetailList) {
                if (tail.getResourceType() == 0) { // 酒店
                    hotelList.add(tail);
                }
                if (tail.getResourceType() == 1) { // 景区
                    sceniList.add(tail);
                }
            }
        }

        // 退款信息
        OrderRefund refundParam = new OrderRefund(orderInfo.getUserId(), orderInfo.getOrderId(), null);
        List<OrderRefund> refundList = orderRefundService.queryOrderRefundList(refundParam);

        if (!CollectionUtils.isEmpty(refundList)) {
            for (OrderRefund refund : refundList) {
                refundTotalAmount = refundTotalAmount.add(refund.getReturnAmount());
                RefundStatus status = RefundStatus.getEnumByIndex(refund.getRefundStatus());
                if (null != status) {
                    refund.setRefundStatusString(status.getName());
                }
            }
        }

        // 计算订单支付剩余时间
        long surplusTime = (60 * 60 * 1000 - (System.currentTimeMillis() - orderInfo.getCreateTime().getTime()));
        orderInfo.setSurplusTime(surplusTime > 0 ? surplusTime : 0);

        map.put("orderInfo", orderInfo);
        map.put("orderDetailInfo", orderDetailList);
        map.put("refundList", refundList);
        map.put("refundTotalAmount", refundTotalAmount);
        map.put("travelerList", travelerList);
        map.put("hotelList", hotelList);
        map.put("sceniList", sceniList);

        return "order_detail";
    }

    /**
     * 根据orderId获取订单状态
     *
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/order_status/query", method = {RequestMethod.GET})
    @ResponseBody
    public String queryOrderStatus(@RequestParam(value = "orderId", required = true) String orderId, HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        //判断用户是否登录
        if (!this.checkLogin(request, response)) {
            return null;
        }
        //用户id
        String userId = this.getUserId(request);
        logger.info("获取订单参数用户ID{},订单号{}", userId, orderId);

        if (StringUtils.isEmpty(orderId)) {
            return "";
        }

        // 查询订单信息
        Order or = orderService.queryOrderInfo(orderId);
        if (null == or || !or.getUserId().equals(userId)) {
            logger.info("订单用户与用户ID不一致，订单用户ID{},登录用户ID{}", or.getUserId(), userId);
            return "";
        }

        OrderStatusLog orderStatusLog = new OrderStatusLog();
        orderStatusLog.setOrderId(orderId);
        orderStatusLog.setIsShow(true);
        orderStatusLog.setOrderType(1);
        List<OrderStatusLog> orderStatusList = orderStatusLogService.queryOrderStatusLogNoRepeat(orderStatusLog);

        List<Integer> orderStatus = new ArrayList<>();
        for (OrderStatusLog statusLog : orderStatusList) {
            statusLog.setDetails(OrderStatus.getName(statusLog.getOrderStatus()));
            orderStatus.add(statusLog.getOrderStatus());
        }
        map.put("orderStatusList", orderStatusList);
        map.put("orderStatus", orderStatus);

        System.out.println(JsonUtils.getJsonString(map));

        return JsonUtils.getJsonString(map);
    }

    /**
     * 取消订单
     *
     * @param orderId
     * @param isSystemCancel
     * @param request
     * @param response
     * @return
     * @author WenJie Mai
     */
    @RequestMapping(value = "/cancel")
    @ResponseBody
    public ResponseDto cancelOrder(@RequestParam(value = "orderId", required = true) String orderId,
                                   @RequestParam(value = "isSystemCancel", required = true) Boolean isSystemCancel,
                                   HttpServletRequest request, HttpServletResponse response) {
        try {
            //判断用户是否登录
            if (!this.checkLogin(request, response)) {
                return null;
            }
            logger.info("取消订单：订单号{}，是否系统取消{}", orderId, isSystemCancel);

            //取消定单
            orderService.cancelOrder(orderId, this.getUserId(request), isSystemCancel);
            return ResponseDto.bulidSuccess();
        } catch (Exception e) {
            logger.error("取消订单失败：订单号{}，是否系统取消{}", orderId, isSystemCancel, e);
            return ResponseDto.bulidFail(GlobalDisconf.errorMessage);
        }
    }
}

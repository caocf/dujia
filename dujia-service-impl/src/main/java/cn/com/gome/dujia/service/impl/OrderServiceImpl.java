package cn.com.gome.dujia.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.gome.plan.mybatis.pagehelper.Page;
import com.gome.plan.mybatis.pagehelper.PageHelper;
import com.gome.plan.tools.utils.BeanUtils;
import com.gome.plan.tools.utils.BigDecimalUtil;
import com.gome.plan.tools.utils.DateUtils;
import com.gome.plan.tools.utils.JsonUtils;
import com.gome.plan.tools.utils.MonitorUtil;
import com.gome.plan.tools.utils.StringUtils;

import cn.com.gome.dujia.constant.Constants;
import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.dto.OrderDto;
import cn.com.gome.dujia.dto.QueryUserOrderReq;
import cn.com.gome.dujia.dto.QueryUserOrderResp;
import cn.com.gome.dujia.dto.ZbyProductDetailRespDto;
import cn.com.gome.dujia.dto.ZbyProductResourceDto;
import cn.com.gome.dujia.enums.IdType;
import cn.com.gome.dujia.enums.OrderErrorCode;
import cn.com.gome.dujia.enums.OrderSource;
import cn.com.gome.dujia.enums.OrderStatus;
import cn.com.gome.dujia.enums.OrderType;
import cn.com.gome.dujia.enums.PayStatus;
import cn.com.gome.dujia.enums.PushStatus;
import cn.com.gome.dujia.enums.RefundReason;
import cn.com.gome.dujia.enums.RefundSource;
import cn.com.gome.dujia.enums.ResourceType;
import cn.com.gome.dujia.enums.SapType;
import cn.com.gome.dujia.enums.TrueFalseStatus;
import cn.com.gome.dujia.exception.BizException;
import cn.com.gome.dujia.mapper.business.OrderDetailMapper;
import cn.com.gome.dujia.mapper.business.OrderMapper;
import cn.com.gome.dujia.mapper.business.OrderPayMapper;
import cn.com.gome.dujia.mapper.business.OrderRefundMapper;
import cn.com.gome.dujia.mapper.business.OrderStatusLogMapper;
import cn.com.gome.dujia.mapper.business.PushSapMapper;
import cn.com.gome.dujia.mapper.business.SmsLogMapper;
import cn.com.gome.dujia.mapper.business.ZbyProductPackagePriceMapper;
import cn.com.gome.dujia.model.Order;
import cn.com.gome.dujia.model.OrderDetail;
import cn.com.gome.dujia.model.OrderPay;
import cn.com.gome.dujia.model.OrderRefund;
import cn.com.gome.dujia.model.OrderStatusLog;
import cn.com.gome.dujia.model.PushSap;
import cn.com.gome.dujia.model.PushVender;
import cn.com.gome.dujia.model.SmsLog;
import cn.com.gome.dujia.model.ZbyProductPackage;
import cn.com.gome.dujia.model.ZbyProductPackagePrice;
import cn.com.gome.dujia.service.CashierService;
import cn.com.gome.dujia.service.IdGeneratorService;
import cn.com.gome.dujia.service.OrderRefundService;
import cn.com.gome.dujia.service.OrderService;
import cn.com.gome.dujia.service.PushSapService;
import cn.com.gome.dujia.service.PushVenderService;
import cn.com.gome.dujia.service.RedisCacheService;
import cn.com.gome.dujia.service.TcService;
import cn.com.gome.dujia.service.biz.ProductBizService;
import cn.com.gome.dujia.vo.json.order.LineSaleInfoCalenderResult;
import cn.com.gome.dujia.vo.json.order.OrderCreateResult;
import cn.com.gome.dujia.vo.json.productinfo.PackageDetail;
import cn.com.gome.dujia.vo.order.OrderQueryInfo;
import cn.com.gome.dujia.vo.order.OrderQueryParam;
import cn.com.gome.dujia.vo.order.ResourceUseDetail;
import cn.com.gome.dujia.vo.order.TravlePassengerInfo;
import cn.com.gome.dujia.vo.order.ValidatePriceResponse;
import cn.com.gome.trip.unite.model.UserTraveler;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON).ignoreEmpty();

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderStatusLogMapper orderStatusLogMapper;

    @Autowired
    private OrderRefundService orderRefundService;

    @Resource
    private PushSapService pushSapService;

    @Resource
    private PushSapMapper pushSapMapper;

    @Resource
    private OrderPayMapper orderPayMapper;

    @Resource
    private OrderRefundMapper orderRefundMapper;

    @Resource
    private SmsLogMapper smsLogMapper;

    @Autowired
    private IdGeneratorService idGeneratorService;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private CashierService cashierService;

    @Autowired
    private TcService tcService;

    @Autowired
    private ProductBizService productBizService;

    @Autowired
    private PushVenderService pushVenderService;
    
    @Autowired
    private ZbyProductPackagePriceMapper productPackagePriceMapper;
    
    @Autowired
    private RedisCacheService redisCacheService;

    @Override
    public int insert(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public Order selectByPrimaryKey(Integer id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Order order) {
        return orderMapper.updateByPrimaryKey(order);
    }

    @Override
    public int updateByPrimaryKeySelective(Order order) {
        return 0;
    }

    @Override
    public List<Order> queryOrderList(Order order) {
        List<Order> orderList = orderMapper.queryOrderList(order);
        if (null == orderList) {
            return null;
        }
        return orderList;
    }

    @Override
    public void batchExecute(List<OrderStatusLog> orderStatusLogs, List<PushSap> pushSaps) {
        if (!CollectionUtils.isEmpty(orderStatusLogs)) {
            orderStatusLogMapper.batchInsert(orderStatusLogs);
        }

        if (!CollectionUtils.isEmpty(pushSaps)) {
            pushSapMapper.batchUpdate(pushSaps);
        }
    }

    @Override
    public Order queryOrderInfo(String orderId) {
        return orderMapper.queryOrderInfo(orderId);
    }

    @Override
    public Page<OrderQueryInfo> queryOrderListByVO(OrderQueryInfo orderVo, int pageNumber, int pageSize) {
        Page<OrderQueryInfo> pageVenderList = PageHelper.startPage(pageNumber, pageSize);
        orderMapper.queryOrderListByVO(orderVo);
        return pageVenderList;
    }

    @Override
    public List<OrderQueryInfo> queryOrderListByVO(OrderQueryInfo orderVo) {
        return orderMapper.queryOrderListByVO(orderVo);
    }

    @Override
    public void cancelOrder(String orderId, String userId, Boolean isSystemCancel) throws BizException {

        if (StringUtils.isEmpty(orderId) || null == isSystemCancel) {
            logger.error("取消订单参数不能为空");
            throw new BizException("取消订单参数不能为空");
        }

        logger.info("取消订单：订单号{}，用户id{}，是否系统取消{}", orderId, userId, isSystemCancel);
        // 根据订单id查询订单信息， 加锁
        Order orderObj = new Order();
        orderObj.setOrderId(orderId); // 订单id
        if (StringUtils.isNotEmpty(userId)) {
            orderObj.setUserId(userId); // 用户id
        }
        Order orderInfo = orderMapper.getOrderInfoForUpdate(orderObj);
        if (null == orderInfo) {
            logger.error("取消订单：订单号{}不存在", orderId);
            throw new BizException("订单号不存在");
        }

        // 判断订单状态
        if (!OrderStatus.WAIT_PAYMENT.getValue().equals(orderInfo.getOrderStatus()) || !PayStatus.WAIT_PAY.getValue().equals(orderInfo.getOrderPay())) {
            // 订单状态不是待付款 或者 支付状态不是未支付的 不能取消订单
            logger.error("取消订单：订单号{}，订单状态{}，支付状态{}", orderId, orderInfo.getOrderStatus(), orderInfo.getOrderPay());
            throw new BizException("订单不能取消");
        }

        // 更新订单状态为已取消
        Order updateOrderInfo = new Order();
        updateOrderInfo.setOrderId(orderInfo.getOrderId());                 // 订单id
        updateOrderInfo.setOrderStatus(OrderStatus.ORDER_CANCEL.getValue()); // 已取消
        updateOrderInfo.setUpdateTime(new Date());
        orderMapper.batchUpdate(Arrays.asList(updateOrderInfo));

        // 订单状态表
        OrderStatusLog orderStatusLog = new OrderStatusLog();
        orderStatusLog.setOrderId(orderInfo.getOrderId()); // 订单id
        orderStatusLog.setIsShow(TrueFalseStatus.TRUE.getValue()); // 是否前台显示
        orderStatusLog.setOrderStatus(OrderStatus.ORDER_CANCEL.getValue()); // 已取消
        orderStatusLog.setOrderType(OrderType.ORDER.getValue()); // 订单类型 订单

        if (isSystemCancel) {
            // 系统自动取消
            orderStatusLog.setDetails("订单超时系统自动取消"); // 状态说明
        } else {
            // 用户手动取消
            orderStatusLog.setDetails("用户手动取消订单"); // 状态说明
        }
        orderStatusLog.setRecordTime(new Date()); // 记录时间
        orderStatusLog.setUpdateTime(new Date());
        orderStatusLogMapper.insertOrderStatusLog(orderStatusLog);
    }


    @Override
    public Order getOrderByIds(String userId, String orderId) {
        if (StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(orderId)) {
            return orderMapper.getOrderByIds(userId, orderId);
        } else {
            return null;
        }
    }

    @Override
    public String saveOrder(OrderDto orderDto, ZbyProductDetailRespDto productRespDto,
                            ZbyProductPackage productPackage, ZbyProductPackagePrice productPackagePrice) throws BizException {
        //插入订单信息
        Order order = new Order();
        String orderId = idGeneratorService.getOrderId();
        order.setAdultNum(orderDto.getAdultNum());//出游成人数量
        order.setChildNum(orderDto.getChildNum());//出游儿童数量
        order.setContactsName(orderDto.getUserConcat().getName());//联系人姓名
        order.setContactsTelphone(orderDto.getUserConcat().getMobile());//联系人电话
        order.setContactsEmail(orderDto.getUserConcat().getEmail());//联系人邮箱
        order.setBusinessType(0);//业务类型
        order.setProductName(productRespDto.getDetail().getSubName());//线路名称，存副标题
        order.setCreateTime(new Date());//订单创建时间
        order.setBuyCount(orderDto.getBuyCount());//购买数量
        order.setOrderTotalAmount(productPackagePrice.getTcDirectPrice().multiply(new BigDecimal(orderDto.getBuyCount())));//订单总金额
        order.setOrderAmount(productPackagePrice.getTcDirectPrice().multiply(new BigDecimal(orderDto.getBuyCount())));//订单金额
        order.setOrderId(orderId);//订单id
        order.setOrderPay(PayStatus.WAIT_PAY.getValue());//支付状态
        order.setOrderSource(OrderSource.WEB.getValue());//订单来源
        order.setOrderStatus(OrderStatus.WAIT_PAYMENT.getValue());//订单状态
        order.setPackageId(productPackage.getPackageId());//套餐价格id
        order.setPackageName(productPackage.getPackageName());//套餐名称
        order.setPaymentAmount(productPackagePrice.getTcDirectPrice().multiply(new BigDecimal(orderDto.getBuyCount())));//实际支付金额
        order.setProductId(orderDto.getProductId());//线路id
        order.setTravelDay(productRespDto.getDetail().getDays());//出游天数
        order.setTravelPeople(jsonUtils.serialize(orderDto.getTravelerList()));//出游人信息
        order.setOrderTime(new Date());//下单时间
        order.setPackagePrice(productPackagePrice.getTcDirectPrice());//套餐单价
        order.setVenderId(productRespDto.getDetail().getVenderId());//供应商编号
        order.setUpdateTime(new Date());
        //order.setTravelEndTime(detailRespDto.getDetail().getOverDate());//出游结束日期
        try {
            order.setTravelStartTime(DateUtils.parse(orderDto.getSaleDate(), DateUtils.WEB_FORMAT));   //出游开始日期
            order.setTravelEndTime(DateUtils.addDays(order.getTravelStartTime(), order.getTravelDay()));//出游结束日期
        } catch (ParseException e) {
        }
        order.setUserId(orderDto.getUserId());
        order.setUserName(orderDto.getUsername());
        order.setOrderActive(0);//订单是否有效，0有效，1无效
        orderMapper.insert(order);

        //订单详情
        List<OrderDetail> orderDetails = new ArrayList<>();
        if (!CollectionUtils.isEmpty(productRespDto.getResource())) {
            orderDetails.addAll(createOrderDetails(orderDto, order, productPackage, productRespDto.getResource()));
        }
        if (!CollectionUtils.isEmpty(orderDetails))
            orderDetailMapper.batchInsert(orderDetails);

        //插入订单状态
        OrderStatusLog orderStatusLog = new OrderStatusLog();
        orderStatusLog.setOrderId(order.getOrderId());
        orderStatusLog.setIsShow(true);
        orderStatusLog.setOrderStatus(OrderStatus.WAIT_PAYMENT.getValue());
        orderStatusLog.setOrderType(OrderType.ORDER.getValue());
        orderStatusLog.setRecordTime(new Date());
        orderStatusLog.setUpdateTime(new Date());
        orderStatusLog.setDetails("用户下单");
        orderStatusLogMapper.insert(orderStatusLog);
        return orderId;
    }

    /**
     * 拼装订单详情
     *
     * @param order
     * @param productPackage
     * @param dtos
     * @return
     * @throws BizException
     */
    private List<OrderDetail> createOrderDetails(OrderDto orderDto, Order order, ZbyProductPackage productPackage, List<ZbyProductResourceDto> dtos) throws BizException {
        List<OrderDetail> orderDetails = new ArrayList<>();
        if (!CollectionUtils.isEmpty(dtos) && !CollectionUtils.isEmpty(orderDto.getOrderDetails())) {
            Map<String, OrderDetail> map = new HashMap<>();
            for (OrderDetail detail : orderDto.getOrderDetails()) {
                map.put(detail.getResourceId().toString(), detail);
            }
            for (ZbyProductResourceDto resourceDto : dtos) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setCreateTime(new Date());
                orderDetail.setOrderId(order.getOrderId());//订单id
                orderDetail.setPackageName(productPackage.getPackageName());//套餐名称
                if(ResourceType.HOTEL.getValue().equals(Integer.valueOf(resourceDto.getResType()))){
                	// 如果是酒店，购买数量=购买份数
                	orderDetail.setResourceCount(order.getBuyCount());//购买数量
                }
                else if(ResourceType.SCENIC.getValue().equals(Integer.valueOf(resourceDto.getResType()))){
                	// 如果是景区门票，购买数量=购买份数*总人数
                	orderDetail.setResourceCount(order.getBuyCount() * orderDto.getTravelerList().size());//购买数量
                }
                orderDetail.setResourceId(Integer.valueOf(resourceDto.getRelatedId()));//关联id
                orderDetail.setResourceName(resourceDto.getResName());//资源名称
                orderDetail.setResourceType(Integer.valueOf(resourceDto.getResType()));//资源类型:0酒店，1景区
                orderDetail.setResourceProName(resourceDto.getResProName());//资源产品名称
                orderDetail.setUpdateTime(new Date());
//				orderDetail.setVenderOrderId();//供应商订单号，在支付后去第三方创建订单时更新
                if (map.get(resourceDto.getResId()) != null) {
                    orderDetail.setUseEndTime(map.get(resourceDto.getResId()).getUseEndTime());//消费结束时间
                    orderDetail.setUseStartTime(map.get(resourceDto.getResId()).getUseStartTime());//消费开始时间
                } else {
                    logger.error("创建订单详情失败!orderDto:{}", jsonUtils.serialize(orderDto));
                    throw new BizException("创建订单详情失败");
                }
                orderDetails.add(orderDetail);
            }
        }

        return orderDetails;
    }

    @Override
    public QueryUserOrderResp queryUserOrderInfos(QueryUserOrderReq req) {

        int pageNumber = req.getPageIndex();
        int pageSize = req.getPageSize();

        /*try {
            // 获取配置
            pageSize = Integer.parseInt(GlobalDisconf.getOrderListSize());
        } catch (Exception e) {
            logger.error("获取 GlobalConfig.order_list_size 错误{}", e);
        }*/

        Page<Order> pageResult = PageHelper.startPage(pageNumber,7);
        orderMapper.queryUserOrderInfos(req);

        if (null == pageResult || null == pageResult.getResult()) {
            logger.error("查询用户订单列表为空");
        }


        // 订单列表
        List<Order> orderInfoList = pageResult.getResult();

        QueryUserOrderResp resp = new QueryUserOrderResp();
        // 设置分页参数
        resp.setPi(pageResult.getPageNum());
        resp.setPs(pageResult.getPageSize());
        resp.setTc((int) pageResult.getTotal());
        resp.setTp(pageResult.getPages());
        // 设置用户订单
        resp.setOl(orderInfoList);

        return resp;
    }

    /**
     * 收银台支付成功更新信息
     *
     * @param orderInfo      订单表
     * @param orderPay       订单支付表
     * @param orderStatusLog 订单状态表
     * @param pushSap        推送SAP表
     * @param orderRefund    退款单表
     */
    public void update(Order orderInfo, OrderPay orderPay, OrderStatusLog orderStatusLog, PushSap pushSap, OrderRefund orderRefund) {
        if (null != orderInfo) {
            orderInfo.setUpdateTime(new Date());
            orderMapper.updateOrderInfo(orderInfo);
        }
        if (null != orderPay) {
            orderPay.setUpdateTime(new Date());
            orderPayMapper.insert(orderPay);
        }
        if (null != orderStatusLog) {
            orderStatusLog.setUpdateTime(new Date());
            orderStatusLogMapper.insert(orderStatusLog);
        }
        if (null != pushSap) {
            pushSap.setUpdateTime(new Date());
            pushSapMapper.insert(pushSap);
        }
        if (null != orderRefund) {
            orderRefund.setUpdateTime(new Date());
            orderRefundMapper.insert(orderRefund);
        }
    }

    /**
     * 收银台支付成功回调，更新订单
     *
     * @param map
     */
    public String updateOrderPayCallBack(Map<String, String> map) {
        if (CollectionUtils.isEmpty(map)) {
            logger.info("收银台支付成功回调方法参数不能为空");
            return Constants.PAY_CALLBACK_ERROR;
        }
        logger.info("收银台支付成功回调方法参数{}", map);

        //订单号
        String orderNo = map.get("orderNo");
        logger.info("收银台支付成功回调，订单号{}:", orderNo);

        // 根据订单id查询订单信息， 加锁
        Order orderInfo = orderMapper.getOrderInfoForUpdate(new Order(orderNo));
        if (null == orderInfo) {
            logger.error("收银台支付成功回调方法：订单号{}不存在", map.get("orderNo"));
            return Constants.PAY_CALLBACK_ERROR;
        }
        logger.info("收银台支付成功回调方法：订单号{}, 订单状态{}， 支付状态{}", orderInfo.getOrderId(), orderInfo.getOrderStatus(), orderInfo.getOrderPay());

        // 调用收银台支付查询接口，验证收银状态是否支付成功
        if (!cashierService.isPaySuccess(GlobalDisconf.siteAccount, orderInfo.getOrderId(), orderInfo.getUserId())) {
            logger.error("收银台支付成功回调方法验证收银状态失败，订单号{}", orderInfo.getOrderId());
            return Constants.PAY_CALLBACK_ERROR;
        }

        // 根据订单id和收银台流水号和供应商流水号查询是否重复推送
        // 正常支付：一个收银台流水号对应一个供应商流水号
        // 重复支付：一个收银台流水号对应多个供应商流水号
        OrderPay orderPayObj = new OrderPay();
        orderPayObj.setOrderId(orderInfo.getOrderId()); // 订单id
        orderPayObj.setCashierNo(map.get("cashierNo")); // 收银台流水号
        orderPayObj.setMerchantNo(map.get("merchantNo")); // 供应商流水号
        OrderPay pay = orderPayMapper.getOrderPay(orderPayObj);
        if (null != pay) {
            // 重复推送
            logger.info("收银台支付成功回调方法：订单号{}重复推送", orderInfo.getOrderId());
            return Constants.PAY_CALLBACK_SUCCESS;
        }

        if (!GlobalDisconf.env.equals(Constants.PRD)) {
            //测试环境  1分钱交易，支付金额还是订单实际金额
            map.put("payMoney", String.valueOf(BigDecimalUtil.changeY2F(orderInfo.getPaymentAmount())));
        }

        // 订单支付信息，默认正常支付
        OrderPay orderPay = this.createOrderPay(map, TrueFalseStatus.FALSE.getValue());

        // 推送sap正向收款
        PushSap pushSap = pushSapService.createPushSap(orderInfo.getOrderId(), String.valueOf(orderPay.getId()), SapType.POSITIVE_SAP_PAY.getValue());
        if (null == orderPay || null == pushSap) {
            logger.error("订单支付对象或推送SAP对象不能为空");
            return Constants.PAY_CALLBACK_ERROR;
        }

        // 订单状态表
        OrderStatusLog orderStatusLog = new OrderStatusLog();
        orderStatusLog.setOrderId(orderInfo.getOrderId()); // 订单id
        orderStatusLog.setIsShow(TrueFalseStatus.FALSE.getValue()); // 是否前台显示
        orderStatusLog.setOrderType(OrderType.ORDER.getValue()); // 订单类型 订单
        orderStatusLog.setOrderStatus(orderInfo.getOrderStatus()); // 订单状态
        orderStatusLog.setRecordTime(new Date()); // 记录时间

        // 判断支付状态是否已支付
        if (PayStatus.PAY_SUCCESS.getValue().equals(orderInfo.getOrderPay())) {
            // 重复支付
            logger.info("收银台支付成功回调方法订单号{}重复支付", orderInfo.getOrderId());

            // 支付信息 是重复支付
            orderPay.setIsRepeatPay(TrueFalseStatus.TRUE.getValue()); // 是重复支付

            // 订单状态表
            orderStatusLog.setDetails("订单重复支付，等待退款"); // 状态说明

            // 生成退款单 重复支付退款
            OrderRefund orderRefund = orderRefundService.createOrderRefund(orderInfo, map, RefundReason.REPEAT_PAY_REFUND.ordinal(), RefundSource.GOME.ordinal());

            // 保存订单状态表，保存退款单表，保存订单支付表
            this.update(null, orderPay, orderStatusLog, pushSap, orderRefund);

            return Constants.PAY_CALLBACK_SUCCESS;
        }

        // 修改订单支付状态为已支付
        Order updateOrderInfo = new Order();
        updateOrderInfo.setOrderId(orderInfo.getOrderId()); // 订单id
        updateOrderInfo.setOrderPay(PayStatus.PAY_SUCCESS.getValue()); // 支付状态：已支付

        // 判断订单是否已取消
        if (OrderStatus.ORDER_CANCEL.getValue().equals(orderInfo.getOrderStatus())) {
            // 订单超时支付
            logger.info("收银台支付成功回调方法订单号{}超时支付", orderInfo.getOrderId());

            // 订单状态表
            orderStatusLog.setIsShow(TrueFalseStatus.TRUE.getValue()); // 是否前台显示
            orderStatusLog.setOrderStatus(OrderStatus.TIMEOUT_PAY.getValue()); // 超时支付
            orderStatusLog.setDetails("订单超时支付已取消，等待退款"); // 状态说明

            // 生成退款单表 超时支付退款
            OrderRefund orderRefund = orderRefundService.createOrderRefund(orderInfo, map, RefundReason.TIMEOUT_PAY_REFUND.ordinal(), RefundSource.GOME.ordinal());

            // 保存订单支付表，保存订单状态表，保存退款单表，保存订单支付表
            this.update(updateOrderInfo, orderPay, orderStatusLog, pushSap, orderRefund);

            return Constants.PAY_CALLBACK_EXPIRE;
        }

        // 修改订单状态为已付款
        updateOrderInfo.setOrderStatus(OrderStatus.PAYMENT_SUCCESS.getValue()); // 订单状态：已付款

        // 订单状态表
        orderStatusLog.setIsShow(TrueFalseStatus.TRUE.getValue()); // 是否前台显示
        orderStatusLog.setOrderStatus(OrderStatus.PAYMENT_SUCCESS.getValue()); // 已付款
        orderStatusLog.setDetails("用户已付款，可以推单"); // 状态说明

        // 更新订单表和保存订单状态表
        this.update(updateOrderInfo, orderPay, orderStatusLog, pushSap, null);

        //支付后推单
        PushVender pushVender = new PushVender();
        pushVender.setOrderId(orderInfo.getOrderId());// 订单id
        pushVender.setPushStatus(PushStatus.WAIT_PUSH.getValue()); //待推送
        pushVender.setPushNum(0); //推送次数
        pushVender.setCreateTime(new Date()); //创建时间
        pushVenderService.insert(pushVender);

        // 统计票品销售量
        productBizService.addSaleCountForProduct(orderInfo.getOrderId());

        logger.info("收银台支付成功回调方法订单号{}处理成功", orderInfo.getOrderId());
        return Constants.PAY_CALLBACK_SUCCESS;
    }

    /**
     * 创建订单支付表
     *
     * @param map 支付信息
     * @return
     * @parma isRepeatPay 是否重复支付
     */
    private OrderPay createOrderPay(Map<String, String> map, Boolean isRepeatPay) {
        if (!CollectionUtils.isEmpty(map) && null != isRepeatPay) {
            // 订单支付表
            OrderPay orderPay = new OrderPay();
            orderPay.setId(GlobalDisconf.payPrefix + map.get("transNo")); // 主键
            orderPay.setOrderId(map.get("orderNo")); // 订单id
            orderPay.setCashierNo(map.get("cashierNo")); // 收银台流水号
            orderPay.setPayMoney(BigDecimalUtil.changeF2Y(map.get("payMoney"))); // 支付金额
            orderPay.setPayMode(map.get("payMode")); // 支付方式
            orderPay.setPayModeSap(map.get("sapCode")); // sap支付方式
            orderPay.setPayBank(map.get("payBank")); // 支付银行
            orderPay.setPayTime(strToDate(map.get("payTime")));// 支付时间
            orderPay.setTransNo(map.get("transNo")); // 交易流水号
            orderPay.setMerchantNo(map.get("merchantNo")); // 供应商交易流水号
            if (StringUtils.isNotEmpty(map.get("stagingCount"))) {
                orderPay.setStagingCount(Integer.valueOf(map.get("stagingCount"))); // 分期数
            }
            if (StringUtils.isNotEmpty(map.get("counterFee"))) {
                orderPay.setCounterFee(BigDecimalUtil.changeF2Y(map.get("counterFee"))); // 手续费
            }
            orderPay.setIsRepeatPay(isRepeatPay); // 是否重复支付
            orderPay.setCreateTime(new Date()); // 创建时间
            return orderPay;
        }
        return null;
    }


    /**
     * 把字符串转成日期类型
     *
     * @param time
     * @return
     */
    private Date strToDate(String time) {
        try {
            return DateUtils.parse(time, DateUtils.LONG_FORMAT);
        } catch (ParseException e) {
            logger.error("收银台支付成功回调方法支付时间{}格式错误", time);
            return null;
        }
    }

    @Override
    public int countUserOrderByStatus(Order orderInfo) {
        return orderMapper.countOrderByStatus(orderInfo);
    }

    @Override
    public List<OrderStatusLog> queryOrderStatusLogsByOrderId(String orderId) {
        return orderStatusLogMapper.queryOrderStatusLogsByOrderId(orderId);
    }

    @Override
    public List<OrderStatusLog> queryOrderStatusLogList(OrderStatusLog orderStatusLog) {
        return orderStatusLogMapper.select(orderStatusLog);
    }

    @Override
    public List<SmsLog> querySmsLogList(SmsLog record) {
        return smsLogMapper.queryLatestSmsLogList(record);
    }

    /**
     * 同程创建订单
     *
     * @param orderInfo
     * @return
     * @throws BizException
     */
    public OrderCreateResult tcOrderCreate(Order orderInfo) throws BizException {
        //获取订单详情信息
        OrderDetail record = new OrderDetail();
        record.setOrderId(orderInfo.getOrderId());
        record.setUpdateTime(null);
        List<OrderDetail> orderDetails = orderDetailMapper.select(record);
        if (CollectionUtils.isEmpty(orderDetails)) {
            throw new BizException("订单详细信息为空！");
        }

        //拼装基础信息
        Map<String, String> map = new HashMap<>();
        map.put("LineId", orderInfo.getProductId());//线路编号
        map.put("ComboId", orderInfo.getPackageId());//套餐编号
        map.put("TravelStartDate", DateUtils.format(orderInfo.getTravelStartTime(), DateUtils.WEB_FORMAT));//出游时间
        map.put("AdultNum", orderInfo.getAdultNum().toString());//成人数(与儿童数必传一个)
        if (null != orderInfo.getChildNum() && orderInfo.getChildNum() > 0) {
            map.put("ChildNum", orderInfo.getChildNum().toString());//儿童数(与成人数必传一个)
        }
        map.put("BookCount", orderInfo.getBuyCount().toString());//预订份数
        map.put("LinkManName", orderInfo.getContactsName());//联系人姓名
        map.put("LinkManSex", "1");//联系人性别（0-女，1-男）
        map.put("LinkManMobile", orderInfo.getContactsTelphone());//联系人手机号
        map.put("OutSideOrderId", orderInfo.getOrderId());//国美订单号

        //拼装具体使用信息
        List<ResourceUseDetail> TravelUseInfoDetails = new ArrayList<>();
        for (OrderDetail detail : orderDetails) {
            ResourceUseDetail useDetail = new ResourceUseDetail();
            useDetail.setRelateId(detail.getResourceId());
            useDetail.setTravelDate(DateUtils.format(detail.getUseStartTime(), DateUtils.WEB_FORMAT));
            TravelUseInfoDetails.add(useDetail);
        }
        map.put("TravelUseInfoDetails", jsonUtils.serialize(TravelUseInfoDetails));//具体使用信息
        //拼装出游人信息
        List<TravlePassengerInfo> passengerInfos = new ArrayList<>();
        if (StringUtils.isNotEmpty(orderInfo.getTravelPeople())) {
            List<UserTraveler> travelers = jsonUtils.deserialize(orderInfo.getTravelPeople(), new TypeReference<List<UserTraveler>>() {
            });
            if (!CollectionUtils.isEmpty(travelers)) {
                for (UserTraveler traveler : travelers) {
                    TravlePassengerInfo passengerInfo = new TravlePassengerInfo();
                    passengerInfo.setCertType(IdType.SFZ.getValue());//证件类型
                    passengerInfo.setIdNo(traveler.getCertificateCode());//证件号
                    passengerInfo.setMobile(traveler.getMobile());//电话号
                    try {
                        passengerInfo.setBirthDay(DateUtils.takeBirthDayFromIdNo1(traveler.getCertificateCode(), ""));//设置
                    } catch (Exception e) {
                    }
                    passengerInfo.setName(traveler.getName());//姓名
                    passengerInfo.setSex(traveler.getGender());//性别
                    passengerInfos.add(passengerInfo);
                }
            } else {
                logger.error("第三方创建订单，出游人信息拼装失败,出游人信息：{}", orderInfo.getTravelPeople());
                throw new BizException("第三方创建订单，出游人信息拼装失败！");
            }
        } else {
            logger.error("出游人信息：orderId:{},出游人信息：{}", orderInfo.getOrderId(), orderInfo.getTravelPeople());
            throw new BizException("出游人信息为空！");
        }
        map.put("PassengerInfos", jsonUtils.serialize(passengerInfos));//出游人信息
        map.put("OrderMoney", orderInfo.getOrderAmount().toString());//前台下单金额
        map.put("IsPay", "true");//是否立即支付，如果为true，创建订单后，同程方订单状态为已支付
        //第三方创建订单
        OrderCreateResult orderCreateResult = tcService.orderCreate(map);
        return orderCreateResult;
    }

    /**
     * 同程验价
     *
     * @param productId     线路id
     * @param packageId     套餐id
     * @param startTime     线路开始时间，格式：yyyy-MM-dd，如果为空，则默认为1900-1-1
     * @param endTime       线路结束时间，格式：yyyy-MM-dd，如果为空，则默认为1900-1-1
     * @param tcDirectPrice 国美订单价格
     * @throws BizException
     */
    public ValidatePriceResponse validatePrice(String productId, String packageId, String startTime, String endTime, BigDecimal tcDirectPrice) {
        ValidatePriceResponse response = new ValidatePriceResponse();
        response.setCode(OrderErrorCode.E200.getCode());
        startTime = StringUtils.isEmpty(startTime) ? "1900-1-1" : startTime;
        endTime = StringUtils.isEmpty(endTime) ? "1900-1-1" : endTime;
        Map<String, String> map2 = new HashMap<>();
        map2.put("LineId", productId);//线路id
        map2.put("PackageIds", packageId);//套餐id，多个以英文逗号,分隔
        map2.put("StartTime", startTime);//开始时间，若不使用请赋值“1900-1-1”
        map2.put("EndTime", endTime);//结束时间，若不使用请赋值“1900-1-1”
        // 调用同程套餐价格班期接口
        LineSaleInfoCalenderResult calenderResult = tcService.lineSaleInfoCalendar(map2);
        if (null != calenderResult && calenderResult.getLineId().equals(productId)) {
            if (!CollectionUtils.isEmpty(calenderResult.getPackageDetails())) {
                for (PackageDetail packageDetail : calenderResult.getPackageDetails()) {
                    if (MapUtils.isNotEmpty(packageDetail.getPackageDetails())) {
                        for (String key : packageDetail.getPackageDetails().keySet()) {
                            ZbyProductPackagePrice packagePrice = packageDetail.getPackageDetails().get(key);
                            //如果放开卖，就不用看库存
                            if (null == packagePrice) {
                                logger.error("同程验价失败，没有找到指定的套餐内容，productId：{},packageId:{},startTime:{},endTime:{}",
                                        productId, packageId, startTime, endTime);
                                //请日历和预定按钮缓存
                                redisCacheService.evict("class cn.com.gome.dujia.service.impl.ZbyProductPackageServiceImpl.getProductPackageList_" + productId);
                                redisCacheService.evict("class cn.com.gome.dujia.service.impl.ZbyProductPackageServiceImpl.queryPriceListByProIdAndPackId_" + productId + "_" + packageId);
                                response.setCode(OrderErrorCode.E501.getCode());
                                response.setMessage("该套餐已售完，请选择其他套餐!");

                            } 
                            else if (packagePrice.getInventoryStats() == 4 || (!packagePrice.getOpeningSale() && Integer.valueOf(packagePrice.getInventoryRemainder()) == 0)) {
                            	//如果库存不足，更新库存
                            	ZbyProductPackagePrice productPackagePrice = productPackagePriceMapper.selectPackagePriceByIdDate(productId, packageId, startTime);
                            	ZbyProductPackagePrice record = new ZbyProductPackagePrice();
                            	record.setId(productPackagePrice.getId());
                            	record.setInventoryRemainder(0);
                            	List<ZbyProductPackagePrice> updates = new ArrayList<>();
                            	updates.add(record);
                            	productPackagePriceMapper.batchUpdate(updates);
                            	
                            	//请日历和预定按钮缓存
                            	redisCacheService.evict("class cn.com.gome.dujia.service.impl.ZbyProductPackageServiceImpl.getProductPackageList_" + productId);
                                redisCacheService.evict("class cn.com.gome.dujia.service.impl.ZbyProductPackageServiceImpl.queryPriceListByProIdAndPackId_" + productId + "_" + packageId);
                                
                            	response.setCode(OrderErrorCode.E501.getCode());
                                response.setMessage("库存不足!");
                            }
                            else if (!BigDecimalUtil.format(packagePrice.getTcDirectPrice()).equals(BigDecimalUtil.format(tcDirectPrice))) {
                            	logger.error("同程价格已变动,原价{},新价{}", tcDirectPrice, BigDecimalUtil.format(packagePrice.getTcDirectPrice()));
                            	 
                            	//如果价钱不一致，更新库中价格
                            	ZbyProductPackagePrice productPackagePrice = productPackagePriceMapper.selectPackagePriceByIdDate(productId, packageId, startTime);
                            	ZbyProductPackagePrice record = new ZbyProductPackagePrice();
                            	record.setId(productPackagePrice.getId());
                            	record.setTcDirectPrice(packagePrice.getTcDirectPrice());
                            	List<ZbyProductPackagePrice> updates = new ArrayList<>();
                            	updates.add(record);
                            	productPackagePriceMapper.batchUpdate(updates);
                               
                            	//请日历和预定按钮缓存
                            	redisCacheService.evict("class cn.com.gome.dujia.service.impl.ZbyProductPackageServiceImpl.getProductPackageList_" + productId);
                                redisCacheService.evict("class cn.com.gome.dujia.service.impl.ZbyProductPackageServiceImpl.queryPriceListByProIdAndPackId_" + productId + "_" + packageId);
                                
                                response.setCode(OrderErrorCode.E502.getCode());
                                response.setMessage("套餐价格已变动!");
                                response.setData(BigDecimalUtil.format(packagePrice.getTcDirectPrice()));

                            } 
                        }
                    } else {
                        logger.error("同程验价失败，没有找到指定的套餐内容，productId：{},packageId:{},startTime:{},endTime:{}",
                                productId, packageId, startTime, endTime);
                        
                        //同程查询为空，库中价格库存更新为0
                    	ZbyProductPackagePrice productPackagePrice = productPackagePriceMapper.selectPackagePriceByIdDate(productId, packageId, startTime);
                    	ZbyProductPackagePrice record = new ZbyProductPackagePrice();
                    	record.setId(productPackagePrice.getId());
                    	record.setInventoryRemainder(0);
                    	List<ZbyProductPackagePrice> updates = new ArrayList<>();
                    	updates.add(record);
                    	productPackagePriceMapper.batchUpdate(updates);
                        
                    	//请日历和预定按钮缓存
                    	redisCacheService.evict("class cn.com.gome.dujia.service.impl.ZbyProductPackageServiceImpl.getProductPackageList_" + productId);
                        redisCacheService.evict("class cn.com.gome.dujia.service.impl.ZbyProductPackageServiceImpl.queryPriceListByProIdAndPackId_" + productId + "_" + packageId);
                        
                        response.setCode(OrderErrorCode.E501.getCode());
                        response.setMessage("未找到该套餐信息!");
                    }
                }
            }
        }
        return response;
    }

    @Override
    public void batchUpdate(List<Order> orderInfos) {
        orderMapper.batchUpdate(orderInfos);
    }

    @Override
    public List<Order> getOrderByVenderOrderIds(String venderOrderIds) {
        if (StringUtils.isNotEmpty(venderOrderIds)) {
            return orderMapper.getOrderByVenderOrderIds(venderOrderIds);
        }
        return null;
    }

    @Override
    public void updateOrderAndPushSap(Order orderInfo, PushSap pushSap, OrderStatusLog orderStatusLog) {
        if (null != orderInfo) {
            orderInfo.setUpdateTime(new Date());
            orderMapper.updateOrderInfo(orderInfo);
        }

        if (null != pushSap) {
            pushSap.setUpdateTime(new Date());
            pushSapMapper.insert(pushSap);
        }
        
        if (null != orderStatusLog) {
        	orderStatusLog.setUpdateTime(new Date());
        	orderStatusLogMapper.insert(orderStatusLog);
        }
    }

    /**
     * 推单
     *
     * @param pushVender
     * @throws BizException
     */
    public void updatePushVender(PushVender pushVender) throws BizException {
        if (null == pushVender || StringUtils.isEmpty(pushVender.getOrderId())) {
            logger.error("推送供应商下单参数不能为空");
            throw new BizException("推送供应商下单参数不能为空");
        }

        // 订单id
        String orderId = pushVender.getOrderId();
        // 推送次数增加一次
        pushVender.setPushNum(pushVender.getPushNum() + 1); //推送次数+1

        // 根据订单id查询订单信息
        Order orderInfoObj = new Order();
        orderInfoObj.setOrderId(orderId); // 订单id
        Order order = orderMapper.getOrderInfoForUpdate(orderInfoObj);
        if (null == order) {
            logger.error("推送供应商下单：订单号{}不存在", orderId);

            // 订单id不存在，推送状态设置为取消推送
            pushVender.setPushStatus(PushStatus.CANCEL_PUSH.getValue()); //取消推送
        } else {
            logger.info("推送供应商下单: 订单号{}",orderId);
            // 订单状态表
            OrderStatusLog orderStatusLog = new OrderStatusLog();
            orderStatusLog.setOrderId(order.getOrderId()); // 订单id
            orderStatusLog.setIsShow(TrueFalseStatus.FALSE.getValue()); // 是否前台显示
            orderStatusLog.setOrderType(OrderType.ORDER.getValue()); // 订单类型 订单
            orderStatusLog.setOrderStatus(order.getOrderStatus()); // 订单状态
            orderStatusLog.setRecordTime(new Date()); // 记录时间
            orderStatusLog.setUpdateTime(new Date()); //修改时间

            //验价
            String saleDate = DateUtils.format(order.getTravelStartTime(), DateUtils.WEB_FORMAT);
            ValidatePriceResponse priceResponse = this.validatePrice(order.getProductId(), order.getPackageId(), saleDate, saleDate, order.getOrderAmount());
            if (null == priceResponse || !priceResponse.getCode().equals(OrderErrorCode.E200.getCode())) {
                logger.error("推送供应商下单: 订单号{}验价失败",orderId);
                // 支付后推单，验价失败，推送状态设置为取消推送
                pushVender.setPushStatus(PushStatus.CANCEL_PUSH.getValue()); //取消推送

                orderStatusLog.setDetails("支付后下单验价失败"); // 说明

                OrderRefund refundParam = new OrderRefund();
                refundParam.setOrderId(order.getOrderId());
                refundParam.setRefundReason(RefundReason.PAY_PUSH_ERROR_REFUND.ordinal());
                List<OrderRefund> refundList = orderRefundService.queryOrderRefundList(refundParam);
                if(refundList == null){
                    //验价失败，退款
                    OrderRefund orderRefund = orderRefundService.createOrderRefund(order, null, RefundReason.PAY_PUSH_ERROR_REFUND.ordinal(), RefundSource.GOME.ordinal());
                    logger.info("同程验价失败，生产退款单：订单号{}，退款单号{}", order.getOrderId(), orderRefund.getRefundId());
                    orderRefundMapper.insert(orderRefund);
                }

                // 报警埋码
                MonitorUtil.recordOne("push_vender_validate_price_fail");
            }

            // 调用同程创建订单接口
            OrderCreateResult result = this.tcOrderCreate(order);
            if (null != result) {
                logger.info("供应商创建订单成功：国美订单号{}， 供应商订单号{}", orderId, result.getOrderId());
                // 推送状态设置为推送成功
                pushVender.setPushStatus(PushStatus.PUSH_SUCCESS.getValue()); //推送成功

                //更新订单表中，供应商订单id
                orderInfoObj.setVenderOrderId(result.getOrderId()); //供应商订单号
                orderInfoObj.setOrderStatus(OrderStatus.WAIT_TC_CONFIRM.getValue()); //待同程确认
                orderInfoObj.setUpdateTime(new Date()); //修改时间
                orderMapper.updateOrderInfo(orderInfoObj);

                orderStatusLog.setDetails("支付后下单成功，待同程确认"); // 说明
                
                // 立马同步库存价格
                updateProductPrice(order);
            } else {
                logger.info("供应商创建订单失败：订单号{}", orderId);
                // 推送状态设置为推送失败
                pushVender.setPushStatus(PushStatus.PUSH_FAILD.getValue()); //推送失败

                orderStatusLog.setDetails("支付后下单失败"); // 说明

                // 如果推送次数>=最大重推次数 直接退款
                if (pushVender.getPushNum().compareTo(GlobalDisconf.pushVenderMaxNumber) >= 0) {
                    OrderRefund refundParam = new OrderRefund();
                    refundParam.setOrderId(order.getOrderId());
                    refundParam.setRefundReason(RefundReason.PAY_PUSH_ERROR_REFUND.ordinal());
                    List<OrderRefund> refundList = orderRefundService.queryOrderRefundList(refundParam);
                    if(refundList == null){
                        // 支付后推单失败退款
                        OrderRefund orderRefund = orderRefundService.createOrderRefund(order, null, RefundReason.PAY_PUSH_ERROR_REFUND.ordinal(), RefundSource.GOME.ordinal());
                        orderRefundMapper.insert(orderRefund);
                    }
                }

                // 报警埋码
                MonitorUtil.recordOne("push_vender_task_fail");
            }

            orderStatusLogMapper.insert(orderStatusLog);
        }
        //更新推送表
        pushVenderService.update(pushVender);
    }
    
    private void updateProductPrice(Order order) {
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("LineId", order.getProductId());//线路id
    	map.put("PackageIds", order.getPackageId());//套餐id，多个以英文逗号,分隔
    	String saleDate = DateUtils.format(order.getTravelStartTime(), DateUtils.WEB_FORMAT);
    	map.put("StartTime", saleDate);//开始时间，若不使用请赋值“1900-1-1”
    	map.put("EndTime", saleDate);//结束时间，若不使用请赋值“1900-1-1”
        // 调用同程套餐价格班期接口
        LineSaleInfoCalenderResult calenderResult = tcService.lineSaleInfoCalendar(map);
        if (null != calenderResult) {
        	if (order.getProductId().equals(calenderResult.getLineId())) {
        		List<PackageDetail> packageDetails = calenderResult.getPackageDetails();
        		if (null != packageDetails) {
        			for (PackageDetail packageDetail : packageDetails) {
        				if (order.getPackageId().equals(packageDetail.getPackageId()) && null != packageDetail.getPackageDetails()) {
        					for(String key : packageDetail.getPackageDetails().keySet()) {
        						ZbyProductPackagePrice tcPrice = packageDetail.getPackageDetails().get(key);
        						ZbyProductPackagePrice dbPrice = productPackagePriceMapper.selectPackagePriceByIdDate(order.getProductId(), order.getProductId(), saleDate);
        						if (null != tcPrice && null != dbPrice) {
        							ZbyProductPackagePrice record = null;
									try {
										record = BeanUtils.getDiffProperties(dbPrice, tcPrice);
									} catch (Exception e) {
									}
        							if (null != record) {
        								record.setId(dbPrice.getId());
        								productPackagePriceMapper.updateByPrimaryKeySelective(record);
        							}
        						}
        					}
        				}
        			}
        		}
        	}
        }
    }

    /**
     * 批量执行订单
     *
     * @param orders
     * @param orderStatusLogs
     */
    public void batchExecuteOrder(List<Order> orders, List<OrderStatusLog> orderStatusLogs) {
        if (!CollectionUtils.isEmpty(orders)) {
            orderMapper.batchUpdate(orders);
        }

        if (!CollectionUtils.isEmpty(orderStatusLogs)) {
            orderStatusLogMapper.batchInsert(orderStatusLogs);
        }
    }
    
    /**
     * 订单，退款单
     * @param order
     * @param orderRefund
     */
    public void updateOrderRefund(Order order, OrderRefund orderRefund) {
    	if (null != order) {
            orderMapper.updateOrderInfo(order);
        }
    	
    	if (null != orderRefund) {
            orderRefundMapper.insert(orderRefund);
        }
    }

    @Override
    public void updateOrderRefund(Order order, OrderRefund orderRefund, PushSap pushSap) {
        if (null != order) {
            orderMapper.updateOrderInfo(order);
        }

        if (null != orderRefund) {
            orderRefundMapper.insert(orderRefund);
        }

        if(null != pushSap){
            pushSapMapper.insert(pushSap);
        }
    }

    @Override
    public List<Order> queryOrderListByParam(OrderQueryParam param) {
        return orderMapper.queryOrderListByParam(param);
    }
}

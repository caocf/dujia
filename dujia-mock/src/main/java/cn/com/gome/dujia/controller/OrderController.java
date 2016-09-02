package cn.com.gome.dujia.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.dto.ResponseDto;
import cn.com.gome.dujia.enums.IdType;
import cn.com.gome.dujia.enums.OrderErrorCode;
import cn.com.gome.dujia.enums.OrderStatus;
import cn.com.gome.dujia.enums.OrderType;
import cn.com.gome.dujia.enums.PushStatus;
import cn.com.gome.dujia.enums.RefundReason;
import cn.com.gome.dujia.enums.RefundSource;
import cn.com.gome.dujia.enums.SapType;
import cn.com.gome.dujia.enums.TrueFalseStatus;
import cn.com.gome.dujia.exception.BizException;
import cn.com.gome.dujia.mapper.business.OrderDetailMapper;
import cn.com.gome.dujia.mapper.business.OrderMapper;
import cn.com.gome.dujia.mapper.business.OrderRefundMapper;
import cn.com.gome.dujia.mapper.business.OrderStatusLogMapper;
import cn.com.gome.dujia.mapper.business.PushVenderMapper;
import cn.com.gome.dujia.model.Order;
import cn.com.gome.dujia.model.OrderDetail;
import cn.com.gome.dujia.model.OrderPay;
import cn.com.gome.dujia.model.OrderRefund;
import cn.com.gome.dujia.model.OrderStatusLog;
import cn.com.gome.dujia.model.PushSap;
import cn.com.gome.dujia.model.PushVender;
import cn.com.gome.dujia.service.OrderPayService;
import cn.com.gome.dujia.service.OrderRefundService;
import cn.com.gome.dujia.service.OrderService;
import cn.com.gome.dujia.service.PushSapService;
import cn.com.gome.dujia.service.TcService;
import cn.com.gome.dujia.vo.json.order.OrderCreateResult;
import cn.com.gome.dujia.vo.json.productinfo.OrderCreateResponse;
import cn.com.gome.dujia.vo.order.ResourceUseDetail;
import cn.com.gome.dujia.vo.order.TravlePassengerInfo;
import cn.com.gome.dujia.vo.order.ValidatePriceResponse;
import cn.com.gome.trip.unite.model.UserTraveler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.gome.plan.tools.utils.DateUtils;
import com.gome.plan.tools.utils.JsonUtils;
import com.gome.plan.tools.utils.StringUtils;

@Controller
@RequestMapping(value = "/mock/order")
public class OrderController {

	public static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderPayService orderPayService;
	
	@Autowired
	private PushSapService pushSapService;
	
	@Autowired
	private OrderRefundService orderRefundService;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderStatusLogMapper orderStatusLogMapper;
	
	@Autowired
	private PushVenderMapper pushVenderMapper;
	
	@Autowired
	private OrderRefundMapper orderRefundMapper;
	
	@Autowired
    private OrderDetailMapper orderDetailMapper;
	
	@Autowired
	private TcService tcService;
	
	private static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON).ignoreEmpty();
	
	/**
	 * 订单列表
	 * @param model
	 * @param orderStatus
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(Model model, Integer orderStatus, String orderId) {
		if(StringUtils.isNotEmpty(orderId)) {
			Order orderInfo = new Order();
			orderInfo.setOrderId(orderId);
			orderInfo.setOrderStatus(orderStatus);
			orderInfo.setOrderPay(1);
			List<Order> orders = orderService.queryOrderList(orderInfo);
			if(null != orders && orders.size() > 0) {
				model.addAttribute("orders", orders);
			}else{
				model.addAttribute("error", "无查询结果");
			}
		}
		model.addAttribute("orderStatus", orderStatus);
		model.addAttribute("orderId", orderId);
		return "order_list";
	}
	
	/**
     * 永乐订单状态对应国美订单状态
     */
    private static Map<Integer, Integer> orderStatusMap = new HashMap<Integer, Integer>();

    /**
     * 永乐订单状态对应国美订单描述
     */
    private static Map<Integer, String> orderDescMap = new HashMap<Integer, String>();

    static {
    	orderStatusMap.put(2, OrderStatus.WAIT_TC_CONFIRM.getValue()); //待确认
    	orderStatusMap.put(3, OrderStatus.HAS_TC_CONFIRMED.getValue()); //已确认
    	orderStatusMap.put(4, OrderStatus.ORDER_SUCCESS.getValue()); //已完成

    	orderDescMap.put(2, "支付后下单成功，待同程确认"); //待确认
    	orderDescMap.put(3, "同程已确认"); //已确认
    	orderDescMap.put(4, "订单完成"); //已完成
    }
	
	/**
	 * 修改订单状态
	 * @param orderId
	 * @param orderStatus
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDto update(@RequestParam(value = "orderId", required = true) String orderId, 
			@RequestParam(value = "orderStatus", required = true)  Integer orderStatus) {
		try {
			Order orderInfoObj = new Order();
			orderInfoObj.setOrderId(orderId);
			orderInfoObj.setOrderStatus(orderStatus);
			orderInfoObj.setOrderPay(1);
			Order order = orderMapper.selectOne(orderInfoObj);
			if(null != order) {
				updateOrderStatus(order, orderStatus);
				return ResponseDto.bulidSuccess();
			}
		} catch (Exception e) {
			logger.error("修改订单状态失败：订单号{}，订单状态{}", orderId, orderStatus, e);
			if(e.getClass().equals(BizException.class)) {
				return ResponseDto.bulidFail(e.getMessage());
			}
		}
		return ResponseDto.bulidFail();
	}
	
	private void updateOrderStatus(Order order, Integer orderStatus) throws Exception {
		Integer status = orderStatusMap.get(orderStatus); 
		 // 更新订单状态
        Order orderInfo = new Order();
        orderInfo.setOrderId(order.getOrderId()); //订单id
        orderInfo.setOrderStatus(status); //订单状态
        orderInfo.setUpdateTime(new Date());

        // 订单状态表
        OrderStatusLog orderStatusLog = new OrderStatusLog();
        orderStatusLog.setOrderId(order.getOrderId()); // 订单id
        orderStatusLog.setOrderStatus(status); // 订单状态
        orderStatusLog.setOrderType(OrderType.ORDER.getValue()); //订单类型 订单
        orderStatusLog.setDetails(orderDescMap.get(orderStatus)); // 状态说明
        orderStatusLog.setRecordTime(new Date()); // 记录时间;
        orderStatusLog.setUpdateTime(new Date()); // 修改时间
        
        if (OrderStatus.WAIT_TC_CONFIRM.getValue().equals(status)) {
        	//待确认
        	logger.info("更新订单状态：订单号{}， 待确认", order.getOrderId());
        	orderStatusLog.setIsShow(TrueFalseStatus.FALSE.getValue()); // 后台显示
        	
        	//同程下订单
        	createOrder(order, orderStatusLog);
        } else if (OrderStatus.HAS_TC_CONFIRMED.getValue().equals(status)) {
        	//已确认
        	logger.info("更新订单状态：订单号{}， 已确认", order.getOrderId());
        	//已确认需要前台显示
        	orderStatusLog.setIsShow(TrueFalseStatus.TRUE.getValue()); // 前台显示
        	orderStatusLogMapper.insert(orderStatusLog);
        	
            orderMapper.updateOrderInfo(orderInfo);
        } else if (OrderStatus.ORDER_SUCCESS.getValue().equals(status)) {
        	//已完成
        	logger.info("更新订单状态：订单号{}， 已完成", order.getOrderId());
        	//已完成需要前台显示
        	orderStatusLog.setIsShow(TrueFalseStatus.TRUE.getValue()); // 前台显示
        	orderStatusLogMapper.insert(orderStatusLog);
        	orderMapper.updateOrderInfo(orderInfo);
        }
	}
	
	public void createOrder(Order order, OrderStatusLog orderStatusLog) throws Exception {
		if(null != order) {
			// 更新推送状态为取消推送
			PushVender pvp = new PushVender();
			pvp.setOrderId(order.getOrderId());
			pvp.setPushStatus(PushStatus.WAIT_PUSH.getValue());
			PushVender pushVender = pushVenderMapper.selectOne(pvp);
			if (null == pushVender) {
				throw new BizException("此订单已经由任务推送了！");
			}
			
			//验价
			String saleDate = DateUtils.format(order.getTravelStartTime(), DateUtils.WEB_FORMAT);
	        ValidatePriceResponse priceResponse = orderService.validatePrice(order.getProductId(), order.getPackageId(), saleDate, saleDate, order.getOrderAmount());
	        if (null == priceResponse || !priceResponse.getCode().equals(OrderErrorCode.E200.getCode())) {
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
	        }
				
			// 调用同程创建订单接口
			OrderCreateResult result = this.orderCreate(order);
			if (null != result) {
				logger.info("供应商创建订单成功：国美订单号{}， 供应商订单号{}", order.getOrderId(), result.getOrderId());
				// 推送状态设置为推送成功
				pushVender.setPushStatus(PushStatus.PUSH_SUCCESS.getValue()); //推送成功
				
				//更新订单表中，供应商订单id
				Order orderInfoObj = new Order();
				orderInfoObj.setOrderId(order.getOrderId()); //订单号
				orderInfoObj.setVenderOrderId(result.getOrderId()); //供应商订单号
				orderInfoObj.setOrderStatus(OrderStatus.WAIT_TC_CONFIRM.getValue()); //待同程确认
				orderInfoObj.setUpdateTime(new Date()); //修改时间
				orderMapper.updateOrderInfo(orderInfoObj);
				
				orderStatusLog.setDetails("支付后下单成功，待同程确认"); // 说明
			} else {
				logger.info("供应商创建订单失败：订单号{}", order.getOrderId());
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
			}
			
			orderStatusLogMapper.insert(orderStatusLog);
			pushVenderMapper.updateByPrimaryKey(pushVender);
		}
	}
	
	public OrderCreateResult orderCreate(Order orderInfo) throws BizException {
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
        
        OrderCreateResponse response = tcService.orderCreateResponse(map);
        if (null == response) {
        	throw new BizException("下单失败");
		}
        if (!"200".equals(response.getRetCode())) {
        	throw new BizException(response.getResult().getMsg());
        }
        return response.getResult();
	}
	
}

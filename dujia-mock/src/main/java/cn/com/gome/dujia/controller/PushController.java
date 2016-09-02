package cn.com.gome.dujia.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.dto.ResponseDto;
import cn.com.gome.dujia.enums.OrderErrorCode;
import cn.com.gome.dujia.enums.OrderStatus;
import cn.com.gome.dujia.enums.OrderType;
import cn.com.gome.dujia.enums.PushStatus;
import cn.com.gome.dujia.enums.RefundReason;
import cn.com.gome.dujia.enums.RefundSource;
import cn.com.gome.dujia.enums.TrueFalseStatus;
import cn.com.gome.dujia.mapper.business.OrderMapper;
import cn.com.gome.dujia.mapper.business.OrderRefundMapper;
import cn.com.gome.dujia.mapper.business.OrderStatusLogMapper;
import cn.com.gome.dujia.mapper.business.PushVenderMapper;
import cn.com.gome.dujia.model.Order;
import cn.com.gome.dujia.model.OrderRefund;
import cn.com.gome.dujia.model.OrderStatusLog;
import cn.com.gome.dujia.model.PushVender;
import cn.com.gome.dujia.service.OrderRefundService;
import cn.com.gome.dujia.service.OrderService;
import cn.com.gome.dujia.vo.json.order.OrderCreateResult;
import cn.com.gome.dujia.vo.order.ValidatePriceResponse;

import com.gome.plan.tools.utils.DateUtils;
import com.gome.plan.tools.utils.StringUtils;

@Controller
@RequestMapping(value = "/mock/push")
public class PushController {

	public static final Logger logger = LoggerFactory.getLogger(PushController.class);

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderRefundMapper orderRefundMapper;
	
	@Autowired
	private OrderStatusLogMapper orderStatusLogMapper;
	
	@Autowired
	private PushVenderMapper pushVenderMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderRefundService orderRefundService;
	
	
	@RequestMapping(value = "/list")
	public String list(Model model, String orderId) {
		if(StringUtils.isNotEmpty(orderId)) {
			Order orderInfo = new Order();
			orderInfo.setOrderId(orderId);
			orderInfo.setOrderStatus(2);
			orderInfo.setOrderPay(1);
			List<Order> orders = orderService.queryOrderList(orderInfo);
			if(null != orders && orders.size() > 0) {
				model.addAttribute("orders", orders);
			}else{
				model.addAttribute("error", "无查询结果");
			}
		}
		model.addAttribute("orderId", orderId);
		return "push_list";
	}
	
	/**
	 * 修改订单状态
	 * @param orderId
	 * @param orderStatus
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDto update(@RequestParam(value = "orderId", required = true) String orderId) {
		try {
			Order orderInfoObj = new Order();
			orderInfoObj.setOrderId(orderId);
			orderInfoObj.setOrderStatus(2);
			orderInfoObj.setOrderPay(1);
			Order order = orderMapper.selectOne(orderInfoObj);
			if(null != order) {
				// 更新推送状态为取消推送
				PushVender pvp = new PushVender();
				pvp.setOrderId(order.getOrderId());
				pvp.setPushStatus(PushStatus.WAIT_PUSH.getValue());
				PushVender pushVender = pushVenderMapper.selectOne(pvp);
				
				// 订单状态表
				OrderStatusLog orderStatusLog = new OrderStatusLog();
				orderStatusLog.setOrderId(order.getOrderId()); // 订单id
				orderStatusLog.setIsShow(TrueFalseStatus.FALSE.getValue()); // 是否前台显示
				orderStatusLog.setOrderStatus(order.getOrderStatus()); // 订单状态
				orderStatusLog.setOrderType(OrderType.ORDER.getValue()); // 订单类型 订单
				orderStatusLog.setRecordTime(new Date()); // 记录时间
				orderStatusLog.setUpdateTime(new Date());
				
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
				OrderCreateResult result = orderService.tcOrderCreate(order);
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
				}
				
				orderStatusLogMapper.insert(orderStatusLog);
				pushVenderMapper.updateByPrimaryKey(pushVender);
				return ResponseDto.bulidSuccess();
			}
		} catch (Exception e) {
			logger.error("修改订单状态失败：订单号{}", orderId, e);
		}
		return ResponseDto.bulidFail();
	}
	
}

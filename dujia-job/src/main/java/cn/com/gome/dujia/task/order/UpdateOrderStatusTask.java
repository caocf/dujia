package cn.com.gome.dujia.task.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import cn.com.gome.dujia.enums.PayStatus;
import cn.com.gome.dujia.enums.RefundReason;
import cn.com.gome.dujia.model.OrderRefund;
import cn.com.gome.dujia.service.OrderRefundService;
import cn.com.gome.dujia.vo.order.OrderQueryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import cn.com.gome.dujia.enums.OrderStatus;
import cn.com.gome.dujia.enums.OrderType;
import cn.com.gome.dujia.enums.TrueFalseStatus;
import cn.com.gome.dujia.model.Order;
import cn.com.gome.dujia.model.OrderStatusLog;
import cn.com.gome.dujia.service.OrderService;
import cn.com.gome.dujia.task.common.AbstractTask;
/**
 * Description : 修改订单状态
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年5月14日 下午3:12:48 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
@Component
public class UpdateOrderStatusTask extends AbstractTask {
	
	
	private static final Logger logger = LoggerFactory.getLogger(UpdateOrderStatusTask.class);
	
	
	@Autowired
	private OrderService orderService;

    @Autowired
    private OrderRefundService orderRefundService;

	@Override
	public String getTaskName() {
		return "订单完成";
	}


	/**
	 * 	流程：
	 * 		1. 订单状态!=已完成
	 * 		2. 当前时间>=出游结束日期+1
	 */
	@Override
	public void doExecute() {

		logger.info("----------------修改订单状态任务...开始----------------");
		
		try {
               OrderQueryParam param = new OrderQueryParam();
               param.setNonOrderStatus(OrderStatus.ORDER_SUCCESS.getValue());  // 非已完成
               param.setPayStatus(PayStatus.PAY_SUCCESS.getValue());          //  已支付
               param.setVenderOrderId("venderOrderId");                       //  第三方订单号不能为空
               param.setJudgeEndTime("travelEndTime");                        //  判断出游结束日期
			   List<Order> orderList = orderService.queryOrderListByParam(param);
			
			   if(!CollectionUtils.isEmpty(orderList)){
				   List<Order>          updateOrderList    = new ArrayList<Order>();
				   List<OrderStatusLog> orderStatusLogList = new ArrayList<OrderStatusLog>();

                   // 订单状态表
                   OrderStatusLog orderStatusLog = new OrderStatusLog();
                   orderStatusLog.setIsShow(TrueFalseStatus.TRUE.getValue()); // 前台显示
                   orderStatusLog.setOrderType(OrderType.ORDER.getValue()); // 订单类型 订单
                   orderStatusLog.setRecordTime(new Date()); // 记录时间
                   orderStatusLog.setUpdateTime(new Date()); //修改时间
				   
				   for(Order order : orderList){
                       logger.info("----------------修改订单状态_orderId:"+order.getOrderId()+"_orderStatus_"+order.getOrderStatus()+"_travelEndTime_"+order.getTravelEndTime());

                       OrderRefund refundParam = new OrderRefund();
                       refundParam.setOrderId(order.getOrderId());
                       List<OrderRefund> refundList = orderRefundService.queryOrderRefundList(refundParam);

                       boolean refundFlag = false;

                       if(!CollectionUtils.isEmpty(refundList)){
                           //总退款金额
                           BigDecimal tatalRefundAmount = new BigDecimal(0);
                           for(OrderRefund refund : refundList){
                               if(refund.getRefundReason() != RefundReason.REPEAT_PAY_REFUND.ordinal()){
                                   tatalRefundAmount = tatalRefundAmount.add(refund.getReturnAmount());
                               }
                           }
                           // 非全额退款,更改订单状态
                           if (order.getOrderAmount().compareTo(tatalRefundAmount) != 0) {
                               refundFlag = true;
                           }
                       }else{
                           // 无退款
                           refundFlag = true;
                       }

                       if(refundFlag){
                           order.setOrderStatus(OrderStatus.ORDER_SUCCESS.getValue());	// 已完成
                           order.setUpdateTime(new Date());
                           updateOrderList.add(order);

                           orderStatusLog.setOrderId(order.getOrderId()); // 订单id
                           orderStatusLog.setOrderStatus(order.getOrderStatus()); // 订单状态
                           orderStatusLog.setDetails("订单完成");
                           orderStatusLogList.add(orderStatusLog);
                       }
				   }
				   
				   // 批量更新
				   orderService.batchExecuteOrder(updateOrderList,orderStatusLogList);
			   }
		} catch (Exception e) {
			logger.error("修改订单状态失败", e);
		}
		logger.info("----------------修改订单状态任务...结束----------------");
	}

}

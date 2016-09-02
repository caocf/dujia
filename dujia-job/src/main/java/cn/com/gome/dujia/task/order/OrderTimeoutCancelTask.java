package cn.com.gome.dujia.task.order;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.enums.OrderStatus;
import cn.com.gome.dujia.enums.PayStatus;
import cn.com.gome.dujia.enums.SmsType;
import cn.com.gome.dujia.enums.TrueFalseStatus;
import cn.com.gome.dujia.model.Order;
import cn.com.gome.dujia.service.CashierService;
import cn.com.gome.dujia.service.OrderService;
import cn.com.gome.dujia.service.RedisCacheService;
import cn.com.gome.dujia.service.SmsLogService;
import cn.com.gome.dujia.task.common.AbstractTask;
import cn.com.gome.dujia.task.rb.RefundSendRbTask;
import com.gome.plan.tools.utils.DateUtils;
import com.gome.plan.tools.utils.MonitorUtil;
/**
 * Description : 订单超时取消任务 Copyright : Copyright (c) 2008- 2016 All rights
 * reserved. <br/>
 * Created Time : 2016年5月3日 下午4:20:26 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
@Component
public class OrderTimeoutCancelTask extends AbstractTask {

	private static final Logger logger = LoggerFactory.getLogger(RefundSendRbTask.class);

	@Autowired
	private OrderService orderService;

	@Autowired
	private CashierService cashierService;

    @Autowired
    private SmsLogService smsLogService;
    
    @Autowired
    private RedisCacheService redisCacheService;

	@Override
	public String getTaskName() {
		return "订单超时取消";
	}

	
	/**
	 * 待支付订单 
	 * 			1. 30Min< 订单创建时间 <=60Min 发短信 
	 * 			2. 订单创建时间>60Min 取消订单
	 */
	@Override
	public void doExecute() {

		logger.info("----------------订单超时系统自动取消任务...开始----------------");
		
		try {
			  // 查询订单表：订单状态（待付款），支付状态（未支付）
			  Order orderParam = new Order();
			  orderParam.setOrderStatus(OrderStatus.WAIT_PAYMENT.getValue());   // 待付款
			  orderParam.setOrderPay(PayStatus.WAIT_PAY.getValue()); 		   // 未支付
			  // 订单列表
			  List<Order> orders = orderService.queryOrderList(orderParam);
			  
			  if (!CollectionUtils.isEmpty(orders)) {
				  Integer remindTime = Integer.parseInt(GlobalDisconf.getOrderRemindTime()); // 提醒时间
				  Integer lockTime   = Integer.parseInt(GlobalDisconf.getOrderLockTime());   // 锁定时间
				  logger.info("-----------------remindTime:"+remindTime+"_lockTime:"+lockTime+"-----------------");
				  
			      for (Order order : orders) {
			    	  Date orderTime   = order.getCreateTime();					  // 创建时间
			    	  Date invalidTime = DateUtils.addMinutes(orderTime,lockTime);// 失效时间
			    	  logger.info("-----------------orderTime:"+orderTime+"_invalidTime:"+invalidTime+"-----------------");
			    	  
			    	  // 失效时间在当前时间之前(当前时间>=失效时间)
			    	  if (invalidTime.before(new Date())) {
			    		  // 订单超时，取消定单
			    		  logger.info("订单超时取消任务：订单号{}，下单时间{}，失效时间{}",order.getOrderId(),DateUtils.format(orderTime,DateUtils.LONG_WEB_FORMAT),DateUtils.format(invalidTime,DateUtils.LONG_WEB_FORMAT));
			    		  try {
			    			  	// 取消之前验证是否有在收银台支付成功
			    			  	if (cashierService.isPaySuccess(GlobalDisconf.getSiteAccount(),order.getOrderId(), order.getUserId())) {
			    			  		logger.error("订单超时取消，收银台支付成功，没有收到收银台回调，订单号{}",order.getOrderId());
                                    MonitorUtil.recordOne("ORDER_TIME_OUT_CANCLE");
			    			  	}else{
                                    // 超时系统自动取消订单
                                    orderService.cancelOrder(order.getOrderId(),null,TrueFalseStatus.TRUE.getValue());
                                    Map<String, Object> mx = new HashMap<String,Object>();
                                    mx.put("orderId",order.getOrderId());
                                    smsLogService.sendSms(SmsType.OUT_TIME_CANCEL.getDiffId(),order.getContactsTelphone(),mx);
                                }
			    		  } catch (Exception e) {
							logger.error("订单超时系统自动取消失败：订单号{}",order.getOrderId(), e);
                            MonitorUtil.recordOne("ORDER_TIME_OUT_CANCLE");
						  }
					}else{
						Date remTime = DateUtils.addMinutes(orderTime,remindTime); // 提醒时间
						// 提醒时间在当前时间之前(当前时间>=提醒时间)
						if (remTime.before(new Date())) {
							//发送短信，通知用户支付
                            try{
                            	String key = "PAY_REMIND_" + order.getOrderId();
                                if(null == redisCacheService.get(key)){
                                    Map<String, Object> paramMap = new HashMap<String,Object>();
                                    paramMap.put("orderId",order.getOrderId());
                                    paramMap.put("date",DateUtils.format(remTime, DateUtils.LONG_WEB_FORMAT));
                                    smsLogService.sendSms(SmsType.PAY_REMIND.getDiffId(),order.getContactsTelphone(),paramMap);

                                    redisCacheService.set(key,"1",1800);//30分钟有效(每个订单只发一次短信)
                                }
                            }catch (Exception e){
                                continue;
                            }
						}
					}
				}
				logger.info("订单超时系统自动取消成功");
			}
		} catch (Exception e) {
			logger.error("订单超时系统自动取消失败", e);
		}
		logger.info("----------------订单超时系统自动取消任务...结束----------------");
	}

}

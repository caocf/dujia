package cn.com.gome.dujia.service;

import java.util.List;
import java.util.Map;
import cn.com.gome.dujia.model.OrderPay;
/**
 * Description : 支付
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月5日 上午11:29:54 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public interface OrderPayService {
	
	/**
	 * 根据条件查询订单支付信息
	 * @param orderPay
	 * @return
	 */
	OrderPay getOrderPay(OrderPay orderPay);

	/**
	 * 根据订单号查询正常支付的支付信息
	 * 
	 * @param orderId
	 * @return
	 */
	public OrderPay getOrderPayByOrderId(String orderId);
	
	public List<OrderPay> queryOrderPayByPushSap(Map<String, Object> map);

	/**
	 * 根据订单ID 获取支付信息
	 *
	 * @param orderId
	 * @return
	 */
	List<OrderPay> getOrderPayListByOrderId(String orderId);

	/**
	 * 根据条件查询订单支付信息
	 *
	 * @param orderPay
	 * @return
	 */
	List<OrderPay> getOrderPayList(OrderPay orderPay);
}

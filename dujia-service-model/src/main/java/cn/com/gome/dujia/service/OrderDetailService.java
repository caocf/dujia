package cn.com.gome.dujia.service;

import java.util.List;

import cn.com.gome.dujia.model.OrderDetail;

/**
 * Description : 订单详情
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月5日 上午11:29:27 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public interface OrderDetailService {
	
	/**
	 * 插入订单详情信息
	 * @param orderDetail
	 * @return
	 */
	int insert(OrderDetail orderDetail);
	
	/**
	 * 查询订单详情信息
	 * @param orderDetail
	 * @return
	 */
	List<OrderDetail> getOrderOrderDetail(OrderDetail orderDetail);
	
	/**
	 * 更新订单详情信息
	 * @param orderDetail
	 * @return
	 */
	int updateOrderDetail(OrderDetail orderDetail);

}

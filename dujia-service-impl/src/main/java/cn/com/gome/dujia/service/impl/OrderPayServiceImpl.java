package cn.com.gome.dujia.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import cn.com.gome.dujia.enums.TrueFalseStatus;
import cn.com.gome.dujia.mapper.business.OrderPayMapper;
import cn.com.gome.dujia.model.OrderPay;
import cn.com.gome.dujia.service.OrderPayService;
import com.gome.plan.tools.utils.StringUtils;
/**
 * Description : 支付表Service
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年5月3日 下午2:09:53 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
@Service
public class OrderPayServiceImpl implements OrderPayService {
	
	@Autowired
	private OrderPayMapper orderPayMapper;

	
	@Override
	public OrderPay getOrderPay(OrderPay orderPay) {
		if (null != orderPay) {
			return orderPayMapper.getOrderPay(orderPay);
		}
		return null;
	}


	@Override
	public OrderPay getOrderPayByOrderId(String orderId) {
		if (StringUtils.isNotEmpty(orderId)) {
			OrderPay orderPay = new OrderPay();
			orderPay.setOrderId(orderId); // 订单id
			orderPay.setIsRepeatPay(TrueFalseStatus.FALSE.getValue()); // 正常支付
			return orderPayMapper.getOrderPay(orderPay);
		}
		return null;
	}

	@Override
	public List<OrderPay> queryOrderPayByPushSap(Map<String, Object> map) {
		if (!CollectionUtils.isEmpty(map)) {
			return orderPayMapper.queryOrderPayByPushSap(map);
		}
		return null;
	}

	@Override
	public List<OrderPay> getOrderPayListByOrderId(String orderId) {

		if (StringUtils.isNotEmpty(orderId)) {
			OrderPay orderPay = new OrderPay();
			orderPay.setOrderId(orderId); // 订单id

			return orderPayMapper.select(orderPay);
		}
		return null;
	}

	@Override
		public List<OrderPay> getOrderPayList(OrderPay orderPay)
	{
		if (null != orderPay) {
			return orderPayMapper.select(orderPay);
		}
		return null;
	}
}

package cn.com.gome.dujia.mapper.business;

import java.util.List;
import java.util.Map;
import com.gome.plan.mybatis.mapper.common.Mapper;
import cn.com.gome.dujia.model.OrderPay;

public interface OrderPayMapper extends Mapper<OrderPay> {
	
    /**
	 * 根据条件查询订单支付信息
	 * @param orderPay
	 * @return
	 */
	OrderPay getOrderPay(OrderPay orderPay);
	
	/**
	 * 根据推送sap信息查询订单支付信息
	 * @param map
	 * @return
	 */
	List<OrderPay> queryOrderPayByPushSap(Map<String, Object> map);

	/**
	 * 获取订单所有支付信息列表
	 * @param orderPay
	 * @return
	 */
	List<OrderPay> getOrderPayList(OrderPay orderPay);
}
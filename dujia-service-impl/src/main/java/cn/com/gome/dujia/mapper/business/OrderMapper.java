package cn.com.gome.dujia.mapper.business;

import java.util.List;
import cn.com.gome.dujia.vo.order.OrderQueryParam;
import org.apache.ibatis.annotations.Param;
import cn.com.gome.dujia.dto.QueryUserOrderReq;
import cn.com.gome.dujia.model.Order;
import cn.com.gome.dujia.vo.order.OrderQueryInfo;
import com.gome.plan.mybatis.mapper.common.Mapper;

public interface OrderMapper extends Mapper<Order> {
	
	/**
	 * 根据条件查询订单列表
	 */
	public List<Order> queryOrderList(Order order);
	
	/**
	 * 通过订单号查询订单信息
	 * @author WenJie Mai
	 *
	 * @param orderId
	 * @return
	 */
	public Order queryOrderInfo(String orderId);

	/**
	 * 根据VO对象动态获取订单列表
	 *
	 * @param orderVo
	 * @return
	 */
	public List<OrderQueryInfo> queryOrderListByVO(OrderQueryInfo orderVo);
	
	/**
	 * 根据条件查询订单信息,加锁
	 * 
	 * @param orderInfo
	 * @return
	 */
	public Order getOrderByIds(@Param("userId") String userId, @Param("orderId") String orderId);
	
	/**
     * 批量更新
     * @param orderInfos
     */
    void batchUpdate(List<Order> orderInfos);
    
    /**
     * 根据用户ID、时间，获取订单列表
     * 
     * @param orderInfo
     * @return
     */
    List<Order> queryUserOrderInfos(QueryUserOrderReq userOrderReq);
    
	/**
	 * 根据状态统计订单数量
	 * 
	 * @param orderStatus
	 * @return
	 */
	int countOrderByStatus(Order orderInfo);
	
	 /**
	 * 根据同程订单号查询订单信息
	 * @param userId
	 * @param orderId
	 * @return
	 */
	List<Order> getOrderByVenderOrderIds(String venderOrderIds);
	
	/**
	 * 根据条件查询订单信息,加锁
	 * 
	 * @param orderInfo
	 * @return
	 */
	Order getOrderInfoForUpdate(Order orderInfo);
	
	/**
	 * 更新订单
	 * @param orderInfo
	 * @return
	 */
	void updateOrderInfo(Order orderInfo);

    /**
     * 查询订单信息
     * @param param
     * @return
     */
    public List<Order> queryOrderListByParam(OrderQueryParam param);
}
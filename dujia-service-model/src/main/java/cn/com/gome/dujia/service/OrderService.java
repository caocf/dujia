package cn.com.gome.dujia.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import cn.com.gome.dujia.dto.OrderDto;
import cn.com.gome.dujia.dto.QueryUserOrderReq;
import cn.com.gome.dujia.dto.QueryUserOrderResp;
import cn.com.gome.dujia.dto.ZbyProductDetailRespDto;
import cn.com.gome.dujia.exception.BizException;
import cn.com.gome.dujia.model.Order;
import cn.com.gome.dujia.model.OrderRefund;
import cn.com.gome.dujia.model.OrderStatusLog;
import cn.com.gome.dujia.model.PushSap;
import cn.com.gome.dujia.model.PushVender;
import cn.com.gome.dujia.model.SmsLog;
import cn.com.gome.dujia.model.ZbyProductPackage;
import cn.com.gome.dujia.model.ZbyProductPackagePrice;
import cn.com.gome.dujia.vo.json.order.OrderCreateResult;
import cn.com.gome.dujia.vo.order.OrderQueryInfo;
import cn.com.gome.dujia.vo.order.OrderQueryParam;
import cn.com.gome.dujia.vo.order.ValidatePriceResponse;

import com.gome.plan.mybatis.pagehelper.Page;
/**
 * Description : 订单
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月5日 上午11:28:23 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public interface OrderService {
	
	/**
	 * 插入新订单信息
	 */
	int insert(Order order);

	/**
	 * 根据主键查询订单信息
	 */
	Order selectByPrimaryKey(Integer id);

	/**
	 * 更新订单信息
	 */
	int updateByPrimaryKeySelective(Order order);
	
	/**
	 * 更新订单信息
	 */
	int updateByPrimaryKey(Order order);

	/**
	 * 根据条件查询订单列表
	 */
	List<Order> queryOrderList(Order order);

	/**
	 * 根据订单编号查询订单信息
	 * @param orderId
	 * @return
	 */
	public Order queryOrderInfo(String orderId);
	
	/**
	 * 批量更新数据
	 * @author WenJie Mai
	 *
	 * @param orderStatusLogs
	 * @param pushSaps
	 */
	public void batchExecute(List<OrderStatusLog> orderStatusLogs, List<PushSap> pushSaps);

	/**
	 * 通过VO获取订单分页列表
	 *
	 * @param orderVo 订单详细VO对象
	 * @param pageNumber 开始页号
	 * @param pageSize	每页大小
	 * @return
	 */
	Page<OrderQueryInfo> queryOrderListByVO(OrderQueryInfo orderVo, int pageNumber, int pageSize);

	/**
	 * 通过VO获取订单列表
	 *
	 * @param orderVo
	 * @return
	 */
	List<OrderQueryInfo> queryOrderListByVO(OrderQueryInfo orderVo);
	
	/**
	 * 取消订单
	 * @author WenJie Mai
	 *
	 * @param orderId
	 * @param userId
	 * @param isSystemCancel
	 * @throws BizException
	 */
	public void cancelOrder(String orderId, String userId, Boolean isSystemCancel)throws BizException;
	
	
	/**
	 * 根据条件查询订单信息
	 * @param userId
	 * @param orderId
	 * @return
	 */
	public Order getOrderByIds(String userId, String orderId);
	
	/**
	 * 创建订单
	 * 
	 * @param orderDto 提交订单参数
	 * @param productPackage
	 * @param productPackagePrice
	 * @return
	 * @throws BizException
	 */
	String saveOrder(OrderDto orderDto, ZbyProductDetailRespDto productRespDto, ZbyProductPackage productPackage, ZbyProductPackagePrice productPackagePrice) throws BizException;
	
	/**
	 * 通过页面条件查询信息
	 * @author WenJie Mai
	 *
	 * @param req
	 * @return
	 */
	public QueryUserOrderResp queryUserOrderInfos(QueryUserOrderReq req);
	
	/**
	 * 根据状态获取订单数量
	 * @author WenJie Mai
	 *
	 * @param orderInfo
	 * @return
	 */
	public int countUserOrderByStatus(Order orderInfo);

	/**
	 * 根据订单ID,获取订单状态流转信息
	 *
	 * @param orderId
	 * @return
	 */
	List<OrderStatusLog> queryOrderStatusLogsByOrderId(String orderId);

	/**
	 * 获取推送的短信
	 *
	 * @param record
	 * @return
	 */
	public List<SmsLog> querySmsLogList(SmsLog record);
	
	/**
	 * 同程创建订单
	 * @param orderInfo
	 * @return
	 * @throws BizException
	 */
	OrderCreateResult tcOrderCreate(Order orderInfo) throws BizException;
	
	/**
	 * 同程验票验价
	 * @param productId	线路id
	 * @param packageId	套餐id
	 * @param startTime	线路开始时间，格式：yyyy-MM-dd，如果为空，则默认为1900-1-1
	 * @param endTime	线路结束时间，格式：yyyy-MM-dd，如果为空，则默认为1900-1-1
	 * @param tcDirectPrice	国美订单价格
	 * @throws BizException
	 */
	ValidatePriceResponse validatePrice(String productId, String packageId, String startTime, String endTime, BigDecimal tcDirectPrice);

	/**
	 * 查询订单状态日志记录
	 * @param orderStatusLog
	 * @return
	 */
	List<OrderStatusLog> queryOrderStatusLogList(OrderStatusLog orderStatusLog);
	
	/**
     * 批量更新
     * @param orderInfos
     */
    void batchUpdate(List<Order> orderInfos);
    
    /**
	 * 根据同程订单号查询订单信息
	 * @return
	 */
	List<Order> getOrderByVenderOrderIds(String venderOrderIds);

    /**
     * 1. 修改订单记录
     * 2. 新增sap记录
     * @param orderInfo
     * @param pushSap
     */
    void updateOrderAndPushSap(Order orderInfo,PushSap pushSap, OrderStatusLog orderStatusLog);
    
    /**
	 * 收银台支付成功回调，更新订单
	 * 
	 * @param map
	 */
    String updateOrderPayCallBack(Map<String, String> map);
    
    /**
     * 推单
     * @param pushVender
     * @throws BizException
     */
    void updatePushVender(PushVender pushVender) throws BizException;
    
    /**
     * 批量执行订单
     * @param orders
     * @param orderStatusLogs
     */
    public void batchExecuteOrder(List<Order> orders, List<OrderStatusLog> orderStatusLogs);
    
    /**
     * 订单，退款单
     * @param order
     * @param orderRefund
     */
    public void updateOrderRefund(Order order, OrderRefund orderRefund);

    /**
     *
     * @param order         订单
     * @param orderRefund   退款
     * @param pushSap       sap
     */
    public void updateOrderRefund(Order order,OrderRefund orderRefund,PushSap pushSap);

    /**
     * 查询订单信息
     * @param param
     * @return
     */
    public List<Order> queryOrderListByParam(OrderQueryParam param);
}

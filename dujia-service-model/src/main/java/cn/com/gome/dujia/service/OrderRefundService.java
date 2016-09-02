package cn.com.gome.dujia.service;

import cn.com.gome.dujia.model.Order;
import cn.com.gome.dujia.model.OrderRefund;
import cn.com.gome.dujia.model.OrderStatusLog;
import cn.com.gome.dujia.vo.refund.OrderRefundInfo;
import com.gome.plan.mybatis.pagehelper.Page;
import java.util.List;
import java.util.Map;
/**
 * Description :
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月5日 上午11:30:20 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public interface OrderRefundService {

	
	/**
	 * 新增记录
	 * @author WenJie Mai
	 *
	 * @param orderRefund
	 * @return
	 */
	int insert(OrderRefund orderRefund);
	
	/**
	 * 查询退款
	 * @author WenJie Mai
	 *
	 * @param orderRefund
	 * @return
	 */
	List<OrderRefund> queryOrderRefundList(OrderRefund orderRefund);
	
    /**
     *  慎用：
     *      1. 修改订单记录
     *      2. 创建订单日志记录(订单)
     *      3. 修改退款单记录
     *      4. 创建订单日志记录(退款单)
     * @author WenJie Mai
     *
     * @param orderInfo			订单信息
     * @param orderLog          订单日志
     * @param orderRefund       退款信息
     * @param orderRefundLog    退款日志(存在订单日志表中)
     * @param isSendSapPay      是否推送sap逆向收款
     * @param isSendSapIncome   是否推送sap逆向收入  
     */
    void updateRefundAndOrder(Order orderInfo,OrderStatusLog orderLog,OrderRefund orderRefund,OrderStatusLog orderRefundLog,Boolean isSendSapPay,Boolean isSendSapIncome);

    /**
     * 慎用：
     * 		1. 更新退款单记录
     * 	    2. 创建订单日志记录(退款单)
     * @author WenJie Mai
     *
     * @param orderRefund
     * @param orderRefundLog
     */
    void updateAndinsert(OrderRefund orderRefund,OrderStatusLog orderRefundLog);
    /**
     * 根据推送sap信息查询退款单信息
     * @author WenJie Mai
     *
     * @param map
     * @return
     */
	List<OrderRefund> queryOrderRefundByPushSap(Map<String, Object> map);

    /**
     * 创建退款单
     * @author WenJie Mai
     *
     * @param orderInfo
     * @param map
     * @param refundReason
     * @param refundSource
     * @return
     */
    public OrderRefund createOrderRefund(Order orderInfo,Map<String,String> map,Integer refundReason,Integer refundSource);

	/**
	 *  根据指定主键获取一条数据库记录
	 *
	 * @param id
	 */
	OrderRefund selectByPrimaryKey(Integer id);

	/**
	 * 此方法涉及到多表关联
	 *
	 * @param refundInfo  查询条件(退款VO)
	 * @param currentPage 当前页数
	 * @param pageSize    每页显示个数
	 * @return
	 */
	Page<OrderRefundInfo> queryOrderRefundInfoList(OrderRefundInfo refundInfo,int currentPage,int pageSize);

	/**
	 * 通过VO对象查询List对象(多表)
	 *
	 * @param refundInfo
	 * @return
	 */
	List<OrderRefundInfo> queryOrderRefundInfo(OrderRefundInfo refundInfo);
	
	/**
	 * 查询退款信息
	 * @author WenJie Mai
	 *
	 * @param refundId
	 * @return
	 */
	OrderRefund queryOrderRefund(String refundId);

	/**
	 * 查询退款信息
	 * @author WenJie Mai
	 *
	 * @param orderRefund
	 * @return
	 */
	OrderRefund getOrderRefund(OrderRefund orderRefund);

    /**
     * 更新退款单
     * @param orderRefund
     * @return
     */
    int updateOrderRefund(OrderRefund orderRefund);

    List<OrderRefund> queryRefundList(OrderRefundInfo refundInfo);
}

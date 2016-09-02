package cn.com.gome.dujia.service;

import java.util.List;

import cn.com.gome.dujia.exception.BizException;
import cn.com.gome.dujia.model.OrderPay;
import cn.com.gome.dujia.model.OrderRefund;
import cn.com.gome.dujia.model.PushSap;
import cn.com.gome.dujia.vo.sap.PushSapInfo;

/**
 * Description : 推送sap服务
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月5日 上午11:32:04 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public interface PushSapService {
	
	/**
	 * 推送正向SAP收款(同步接口)
	 * @author WenJie Mai
	 *
	 * @param orderPays
	 * @throws BizException
	 */
	public void pushPositiveSapPay(List<OrderPay> orderPays) throws BizException;
	
	/**
	 * 推送逆向SAP收款(同步接口)
	 * @author WenJie Mai
	 *
	 * @param orderRefunds
	 * @throws BizException
	 */
	public void pushNegativeSapPay(List<OrderRefund> orderRefunds) throws BizException;
	
	/**
	 * 推送正向SAP收入(异步接口)
	 * @author WenJie Mai
	 *
	 * @param orderPays
	 * @throws BizException
	 */
	public void pushPositiveSapIncome(List<OrderPay> orderPays) throws BizException;
	
	/**
	 * 推送逆向SAP收入(异步接口)
	 * @author WenJie Mai
	 *
	 * @param orderRefunds
	 * @throws BizException
	 */
	public void pushNegativeSapIncome(List<OrderRefund> orderRefunds) throws BizException;
	
	
	public int insert(PushSap record);

	/**
	 * 根据条件查询推送sap信息
	 * @author WenJie Mai
	 *
	 * @param pushSap
	 * @return
	 */
	public PushSap getPushSap(PushSap pushSap);
	
	
    /**
	 * 根据条件查询推送SAP列表
	 * @param pushSap
	 * @return
	 */
	List<PushSap> queryPushSap(PushSap pushSap);
	
	public PushSap createPushSap(String orderId, String businessNo, Integer sapType);

	/**
	 * 查询推送信息
	 * @param pushSapInfo
	 * @return
	 */
	List<PushSap> queryPushSapByInfo(PushSapInfo pushSapInfo);

	/**
	 * 查询推送正向SAP信息
	 * @param pushSapInfo
	 * @return
	 */
	List<PushSap> queryPushPositiveSapByInfo(PushSap pushSapInfo);

    /**
     * 批量插入sap收入
     * @param positiveSapIncomes
     * @param negativeSapIncomes
     */
    public void batchInsert(List<PushSap> positiveSapIncomes,List<PushSap> negativeSapIncomes);
}

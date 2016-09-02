package cn.com.gome.dujia.service;

import java.util.Map;

import cn.com.gome.dujia.exception.BizException;
import cn.com.gome.dujia.model.CashierRequest;
/**
 * Description : TODO <br/>
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月22日 上午10:44:55 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public interface CashierService {

	/**
	 * 收银台支付方法
	 * @author WenJie Mai
	 *
	 * @param cashierRequest
	 * @return url
	 */
	public String cashierPay(CashierRequest cashierRequest) throws BizException;
	
	/**
	 * 收银台查询方法
	 * @author WenJie Mai
	 *
	 * @param cashierRequest
	 * @return
	 */
	public String cashiderQuery(CashierRequest cashierRequest) throws BizException;
	
	/**
	 * 验证收银台是否支付成功
	 * @param siteAccount  商户号
	 * @param orderNo      订单号
	 * @param userNo       用户编码
	 * @return
	 */
	public boolean isPaySuccess(String siteAccount,String orderNo,String userNo);
	
	/**
	 * 收银台支付完成回调参数解析
	 * @param rspInfo
	 * @return	如果返回参数为空，给收银台返回值为"ERROR:失败"
	 * 获取订单号方法：返回值 paramsMap.get("orderNo")
	 */
	Map<String, String> payResponse(String rspInfo);
	
	
	public String queryPay(String siteAccount, String orderNo, String userNo);
}

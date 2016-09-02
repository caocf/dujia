package cn.com.gome.dujia.service;

import cn.com.gome.dujia.exception.BizException;
import cn.com.gome.dujia.model.SapRequest;
import cn.com.gome.dujia.ws.client.sap.income.DTIFI402VirtualBusinessIncomeREQ;
import cn.com.gome.dujia.ws.client.sap.income.SIXUNI2ECCIFI402VirtualBusinessIncomeREQ;
import cn.com.gome.dujia.ws.client.sap.pay.SIIFI02REQ;
import cn.com.gome.dujia.ws.client.sap.pay.ZECSDAYSACC;
/**
 * Description : TODO <br/>
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月20日 下午2:07:40 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public interface SapService {

	/**
	 * 构建SAP收款服务的代理
	 * @author WenJie Mai
	 *
	 * @param sapRequest
	 * @return
	 * @throws BizException
	 */
	public SIIFI02REQ buildSapPayServiceProxy(SapRequest sapRequest) throws BizException;
	
	
	/**
	 * 构建SAP收入服务的代理
	 * @author WenJie Mai
	 *
	 * @param sapRequest
	 * @return
	 * @throws BizException
	 */
	public SIXUNI2ECCIFI402VirtualBusinessIncomeREQ buildSapIncomeServiceProxy(SapRequest sapRequest) throws BizException;
	
	/**
	 * 构建SAP收款数据
	 * @author WenJie Mai
	 *
	 * @param sapRequest
	 * @return
	 * @throws BizException
	 */
	public ZECSDAYSACC buildSapPayData(SapRequest sapRequest) throws BizException;
	
	/**
	 * 构建SAP收入数据
	 * @author WenJie Mai
	 *
	 * @param  sapRequest
	 * @throws BizException
	 */
	public DTIFI402VirtualBusinessIncomeREQ buildSapIncomeData(SapRequest sapRequest) throws BizException;
	
}

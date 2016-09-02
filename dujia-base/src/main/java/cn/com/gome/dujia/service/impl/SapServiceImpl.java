package cn.com.gome.dujia.service.impl;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import cn.com.gome.dujia.exception.BizException;
import cn.com.gome.dujia.exception.ErrorCode;
import cn.com.gome.dujia.model.SapData;
import cn.com.gome.dujia.model.SapRequest;
import cn.com.gome.dujia.service.SapService;
import cn.com.gome.dujia.ws.client.sap.income.DTIFI402VirtualBusinessIncomeREQ;
import cn.com.gome.dujia.ws.client.sap.income.DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS;
import cn.com.gome.dujia.ws.client.sap.income.SIXUNI2ECCIFI402VirtualBusinessIncomeREQ;
import cn.com.gome.dujia.ws.client.sap.pay.SIIFI02REQ;
import cn.com.gome.dujia.ws.client.sap.pay.ZECSDAYSACC;
import cn.com.gome.dujia.ws.client.sap.pay.ZFIDAYS;
import com.gome.plan.tools.utils.DateUtils;
import com.gome.plan.tools.utils.StringUtils;
/**
 * Description : sap服务
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月20日 下午2:08:07 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
@Service
public class SapServiceImpl implements SapService {
	
	private static final Logger logger = LoggerFactory.getLogger(SapServiceImpl.class);

	
	@Override
	public SIIFI02REQ buildSapPayServiceProxy(SapRequest sapRequest) throws BizException {
		
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		
		String address = sapRequest.getSapPayAddress();
		String username= sapRequest.getSapPayUsername();
		String password= sapRequest.getSapPayPassword();
		
		logger.info("+++++++++++++base_buildSapPayServiceProxy_address:"+address+"_username:"+username+"_password:"+password);
		
		//SAP收款接口地址
		if(StringUtils.isEmpty(address)) {
			throw new BizException(ErrorCode.E40001);
		}
		
		//SAP收款接口用户名
		if(StringUtils.isEmpty(username)) {
			throw new BizException(ErrorCode.E40002);
		}
		
		//SAP收款接口密码
		if(StringUtils.isEmpty(password)) {
			throw new BizException(ErrorCode.E40003);
		}
		
		factory.setAddress(address);
		factory.setUsername(username);
		factory.setPassword(password);
		
		return factory.create(SIIFI02REQ.class);
	}


	@Override
	public SIXUNI2ECCIFI402VirtualBusinessIncomeREQ buildSapIncomeServiceProxy(SapRequest sapRequest) throws BizException {
		
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		
		String address = sapRequest.getSapIncomeAddress();
		String username= sapRequest.getSapIncomeUsername();
		String password= sapRequest.getSapIncomePassword();
		
		logger.info("+++++++++++++base_buildSapIncomeServiceProxy_address:"+address+"_username:"+username+"_password:"+password);
		
		//SAP收入接口地址
		if(StringUtils.isEmpty(address)) {
			throw new BizException(ErrorCode.E41001);
		}
		
		//SAP收入接口用户名
		if(StringUtils.isEmpty(username)) {
			throw new BizException(ErrorCode.E41002);
		}
		
		//SAP收入接口密码
		if(StringUtils.isEmpty(password)) {
			throw new BizException(ErrorCode.E41003);
		}

		factory.setAddress(address);
		factory.setUsername(username);
		factory.setPassword(password);
		
		return factory.create(SIXUNI2ECCIFI402VirtualBusinessIncomeREQ.class);
	}


	@Override
	public ZECSDAYSACC buildSapPayData(SapRequest sapRequest) throws BizException {
		
		List<SapData> sapDataList = sapRequest.getSapDataList();
		
		if(CollectionUtils.isEmpty(sapDataList)) {
			logger.error("+++++++++++++base_buildSapPayData_Is_Null+++++++++++++");
			return null;
		}
		
		ZECSDAYSACC       zECSDAYSACC = new ZECSDAYSACC();
		ZECSDAYSACC.ITECDAYS iTECDAYS = new ZECSDAYSACC.ITECDAYS();
		zECSDAYSACC.setITECDAYS(iTECDAYS);
		List<ZFIDAYS> zFIDAYSList = iTECDAYS.getItem();
		
		for (SapData sapData : sapDataList) {
			ZFIDAYS zFIDAYS   = new ZFIDAYS();
			Integer sapType   = sapData.getSapType();
			Integer orderType = sapData.getOrderType();
			String  orderId   = sapData.getOrderId();
			
			logger.info("+++++++++++++base_orderId:"+orderId+"_sapType:"+sapType+"_orderType:"+orderType);
			
			//0:收款;1:收入
			if(sapType != 0 && sapType != 1){
				throw new BizException(ErrorCode.E40004);
			}
			
			//0:正向;1:逆向
			if(orderType != 0 && orderType != 1){
				throw new BizException(ErrorCode.E40005);
			}
			
			if(sapType == 1){// 如果收入相关操作，直接抛异常
				throw new BizException(ErrorCode.E40004);
			}
			
			if(orderType == 0){
				zFIDAYS.setZKEY(RandomStringUtils.randomAlphanumeric(5)+"_"+sapData.getOrderPayId()); // 行项目主键,随机生成
                zFIDAYS.setVBELN(sapData.getOrderId()); // 销售凭证(订单号)
				zFIDAYS.setZZDDH(sapData.getOrderId()); // 主订单号
				if(sapData.getIsRepeatPay()){
					zFIDAYS.setZPAYID(sapData.getZPAYID()); //支付id(支付交易流水号)
				}
			}else{
				zFIDAYS.setZKEY(RandomStringUtils.randomAlphanumeric(5)+"_"+sapData.getRefundId()); // 行项目主键，随机生成
                zFIDAYS.setVBELN(sapData.getRefundId());            // 销售凭证(退款单号)
				zFIDAYS.setZZDDH(sapData.getRefundId());     		// 主退款单号
				zFIDAYS.setZVBELN(sapData.getOrderId());	 		// 原订单号,逆向时需要(订单号)
				zFIDAYS.setZTKDH(sapData.getRefundId());	 		// 退款单号,逆向时需要(退款单号)
				zFIDAYS.setZCKDH(sapData.getVenderOrderId());		// 客户订单参考号,逆向时需要		 
			}

			zFIDAYS.setZJYCK(sapData.getZJYCK());  			   // 交易参考号（退款交易流水号）
			zFIDAYS.setZSAUT(sapData.getZSAUT()); 			   // 订单类型
			zFIDAYS.setZSKFS(sapData.getPayModeSap()); 		   // 收款方式
			zFIDAYS.setZYSZK(sapData.getPayAmount());// 应收账款金额
			zFIDAYS.setZYWLX(sapData.getZYWLX()); // 收款类型
			zFIDAYS.setZXSLY(sapData.getZXSLY()); // 订单销售来源
			zFIDAYS.setZSKLX(sapData.getZSKLX()); // 销售渠道类型
			zFIDAYS.setBUDAT(sapData.getBUDAT()); // 调用接口日期
			zFIDAYS.setBLDAT(sapData.getBLDAT()); // 凭证日期(支付时间)
			if(null != sapData.getZYYLM()){
				zFIDAYS.setZYYLM(sapData.getZYYLM()); // 合作商编码(供应商编码)
			}
			
			zFIDAYSList.add(zFIDAYS);
		}
		
		return zECSDAYSACC;
	}

	@Override
	public DTIFI402VirtualBusinessIncomeREQ buildSapIncomeData(SapRequest sapRequest) throws BizException {
		
		List<SapData> sapDataList = sapRequest.getSapDataList();
		
		if(CollectionUtils.isEmpty(sapDataList)) {
			logger.error("+++++++++++++base_buildSapIncomeData_Is_Null+++++++++++++");
			return null;
		}
		
		DTIFI402VirtualBusinessIncomeREQ dTIFI402VirtualBusinessIncomeREQ = new DTIFI402VirtualBusinessIncomeREQ();
		dTIFI402VirtualBusinessIncomeREQ.setTransactionID(RandomStringUtils.randomAlphanumeric(5) +"_"+ System.currentTimeMillis());// XML交易ID
		dTIFI402VirtualBusinessIncomeREQ.setTransactionData(DateUtils.format(new Date(), DateUtils.SHORT_FORMAT));// XML生产时间
		List<DTIFI402VirtualBusinessIncomeREQ.DETAILS> detailsList = dTIFI402VirtualBusinessIncomeREQ.getDETAILS();
		dTIFI402VirtualBusinessIncomeREQ.setDetails(detailsList);
		
		for (SapData sapData : sapDataList) {
			
			DTIFI402VirtualBusinessIncomeREQ.DETAILS.HEADER header = new DTIFI402VirtualBusinessIncomeREQ.DETAILS.HEADER();
			
			Integer sapType   = sapData.getSapType();
			Integer orderType = sapData.getOrderType();
			String  orderId   = sapData.getOrderId();
			
			logger.info("+++++++++++++base_orderId:"+orderId+"_sapType:"+sapType+"_orderType:"+orderType);
			
			//0:收款;1:收入
			if(sapType != 0 && sapType != 1){
				throw new BizException(ErrorCode.E40004);
			}
			
			//0:正向;1:逆向
			if(orderType != 0 && orderType != 1){
				throw new BizException(ErrorCode.E40005);
			}
			
			if(sapType == 0){// 如果收款相关操作，直接抛异常
				throw new BizException(ErrorCode.E41004);
			}
			
			if(orderType == 0){
				header.setZXNDDH(sapData.getOrderId());// 订单号
			}else{
				header.setZXNDDH(sapData.getRefundId());// 退款单号
			}
			
			header.setORDAT(DateUtils.format(sapData.getCreateTime(), DateUtils.SHORT_FORMAT));// 订单日期
			header.setORTIM(DateUtils.format(sapData.getCreateTime(), DateUtils.TIME_FORMAT));// 订单时间
			header.setLIFNR(sapData.getLIFNR()); // 店铺编号
			header.setZSAUT(sapData.getZSAUT()); // 订单类型
			header.setZYWLX(sapData.getZYWLX()); // 收款类型
			header.setBUDAT(sapData.getBUDAT()); // 调用接口日期
			header.setZXSLY(sapData.getZXSLY()); // 订单销售来源
			header.setZSITE(sapData.getZSITE()); // 销售站点来源
			header.setZDSFH(sapData.getVenderOrderId());// 第三方订单号(供应商订单号)
						
			DTIFI402VirtualBusinessIncomeREQ.DETAILS details = new DTIFI402VirtualBusinessIncomeREQ.DETAILS();
			details.setHEADER(header);
			
			//行项目表结构
			List<DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS> items = details.getITEMS(); 
			details.setItems(items);
			
			if(orderType == 0){//正向,购买多笔传多次
				for (int i = 0; i < sapData.getTicketNum(); i++) {
					ITEMS item = buildSapIncomeDetailsItemsData(sapData,i);
					if(item != null){
						items.add(item);
					}
				}
			}else{
				ITEMS item = buildSapIncomeDetailsItemsData(sapData,0);
				if(item != null){
					items.add(item);
				}
			}
			
			detailsList.add(details);
		}
				
		return dTIFI402VirtualBusinessIncomeREQ;
	}

	
	private ITEMS buildSapIncomeDetailsItemsData(SapData sapData,int index) {
		
		if (null == sapData) {
			logger.error("+++++++++++++base_buildPositiveSapIncomeDetailsItemsData_Is_Null+++++++++++++");
			return null;
		}

		DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS items = new DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS();
		
		Integer orderType = sapData.getOrderType();
		
		//订单行项目号,必须唯一
		if(orderType == 0){//由于正向同笔订单会对应多笔支付记录,保证项目号的唯一性,传入index参数
			items.setZVBELP(sapData.getOrderId()+StringUtils.leftPad(String.valueOf(index),3,'0'));
		}else{
			items.setZVBELP(sapData.getRefundId());
			items.setZYDDX(sapData.getOrderId());// 原订单的行项目号
			items.setZYDDH(sapData.getOrderId());// 退款时才用，原订单号
		}
		
//		items.setMATNR(sapData.getMATNR());// SKU编号
//		items.setMAKTX(sapData.getMAKTX());// SKU名称
		items.setMATKL(sapData.getMATKL());// 商品品类
		items.setZPP(sapData.getZPP());	   // 商品品牌
		items.setZMENGE(String.valueOf(sapData.getZMENGE()));// 销售数量
		items.setNETWR(sapData.getNETWR());  // 单价(保留2位小数)
		items.setZYSZK(sapData.getZYSZK());  // 实收用户金额(保留2位小数)
		items.setZJSFA(sapData.getZJSFA());  // 佣金计算方案 (20：定拥类型)
		items.setZDSFH1(sapData.getVenderOrderId());// 第三方订单号(供应商订单号)
		
		// 行项目定价表结构
		List<DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS.ZPRITB> zpritbs = items.getZPRITB();
		items.setZpritb(zpritbs);

		DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS.ZPRITB zpritbPR01 = new DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS.ZPRITB();
		zpritbPR01.setZPRTY(sapData.getZPRTY_SALE_PRICE());// 价格类型 PR01 销售单价
		zpritbPR01.setZPRJE(sapData.getZPRJE());// 金额(保留2位小数)
		zpritbPR01.setZMENGE(String.valueOf(sapData.getZMENGE()));// 数量
		zpritbs.add(zpritbPR01);
		
		DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS.ZPRITB zpritbFKJE = new DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS.ZPRITB();
		zpritbFKJE.setZPRTY(sapData.getZPRTY_AMOUNT());// 价格类型 FKJE 应收金额
		zpritbFKJE.setZPRJE(sapData.getZPRJE());// 金额(保留2位小数)
		zpritbFKJE.setZMENGE(String.valueOf(sapData.getZMENGE()));// 数量
		zpritbs.add(zpritbFKJE);
				
		return items;
	}

}

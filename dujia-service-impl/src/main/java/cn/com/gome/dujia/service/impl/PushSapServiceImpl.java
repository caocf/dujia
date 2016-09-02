package cn.com.gome.dujia.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import cn.com.gome.dujia.enums.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.enums.OrderSource;
import cn.com.gome.dujia.enums.OrderType;
import cn.com.gome.dujia.enums.SapStatus;
import cn.com.gome.dujia.enums.SapType;
import cn.com.gome.dujia.enums.TrueFalseStatus;
import cn.com.gome.dujia.exception.BizException;
import cn.com.gome.dujia.log.APILog;
import cn.com.gome.dujia.log.LogType;
import cn.com.gome.dujia.mapper.business.PushSapMapper;
import cn.com.gome.dujia.model.Order;
import cn.com.gome.dujia.model.OrderPay;
import cn.com.gome.dujia.model.OrderRefund;
import cn.com.gome.dujia.model.OrderStatusLog;
import cn.com.gome.dujia.model.PushSap;
import cn.com.gome.dujia.model.SapData;
import cn.com.gome.dujia.model.SapRequest;
import cn.com.gome.dujia.service.OrderRefundService;
import cn.com.gome.dujia.service.OrderService;
import cn.com.gome.dujia.service.PushSapService;
import cn.com.gome.dujia.service.SapService;
import cn.com.gome.dujia.vo.sap.PushSapInfo;
import cn.com.gome.dujia.ws.client.sap.income.DTIFI402VirtualBusinessIncomeREQ;
import cn.com.gome.dujia.ws.client.sap.income.DTIFI402VirtualBusinessIncomeRES;
import cn.com.gome.dujia.ws.client.sap.income.SIXUNI2ECCIFI402VirtualBusinessIncomeREQ;
import cn.com.gome.dujia.ws.client.sap.pay.SIIFI02REQ;
import cn.com.gome.dujia.ws.client.sap.pay.ZECSDAYSACC;
import cn.com.gome.dujia.ws.client.sap.pay.ZECSDAYSACCResponse;
import cn.com.gome.dujia.ws.client.sap.pay.ZLOMATNRTOOA;
import com.gome.plan.tools.utils.BigDecimalUtil;
import com.gome.plan.tools.utils.DateUtils;
import com.gome.plan.tools.utils.JsonUtils;
import com.gome.plan.tools.utils.MonitorUtil;
import com.gome.plan.tools.utils.StringUtils;
/**
 * Description : 推送sap服务
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月27日 上午10:25:27 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
@Service
public class PushSapServiceImpl implements PushSapService {

	private static final Logger logger = LoggerFactory.getLogger(PushSapServiceImpl.class);
	
	private static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.XML).ignoreEmpty();
	
	@Autowired
	private PushSapMapper pushSapMapper;
	
	@Autowired
	private SapService sapService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderRefundService orderRefundService;


	@Override
	public void pushPositiveSapPay(List<OrderPay> orderPays)throws BizException {
		if(CollectionUtils.isEmpty(orderPays)) {
			logger.info("没有要推送正向SAP收款的数据");
		}else{
			logger.info("推送正向SAP收款数据{}", jsonUtils.serialize(orderPays));
			
			SapRequest sapRequest = buildPositiveSapPayData(orderPays);
			
			//构建推送正向SAP收款数据
			ZECSDAYSACC zECSDAYSACC = sapService.buildSapPayData(sapRequest);
			logger.info("推送正向SAP收款请求数据{}", jsonUtils.serialize(zECSDAYSACC));
			
			//构建SAP收款服务的代理
			SIIFI02REQ sIIFI02REQ = sapService.buildSapPayServiceProxy(sapRequest);
			
			if(null != sIIFI02REQ && null != zECSDAYSACC) {
				//调用正向SAP收款服务
				ZECSDAYSACCResponse response = sIIFI02REQ.siIFI02REQ(zECSDAYSACC);
				logger.info("推送正向SAP收款响应结果{}", jsonUtils.serialize(response));
				
				//记录接口日志
				APILog.build("推送正向SAP收款", LogType.SAP_POSITIVE_PAY).appendRequest("request", zECSDAYSACC).appendResponse("response", response).record();
				
				if(null != response) {
					ZECSDAYSACCResponse.RTN rtn = response.getRTN();
					if(null != rtn) {
						List<ZLOMATNRTOOA> items = rtn.getItem();
						if(!CollectionUtils.isEmpty(items)) {
							List<OrderStatusLog> orderStatusLogs = new ArrayList<OrderStatusLog>(); //保存订单状态表
							List<PushSap>        pushSaps        = new ArrayList<PushSap>(); //更新推送SAP表
							for(ZLOMATNRTOOA item : items) {
								//行项目主键
								String zkey = item.getZKEY();
								//状态
								String status = item.getZJGBS();
								
								if(StringUtils.isNotEmpty(zkey) && zkey.length() > 6 && StringUtils.isNotEmpty(status)) {
									//去掉前6位得到得到支付id
									String payId = zkey.substring(6);
									
									//根据支付id和sap类型查询推送sap信息
									PushSap pushSap = this.getPushSap(new PushSap(null,payId,SapType.POSITIVE_SAP_PAY.getValue()));
									
									if(null != pushSap) {
										//只有推送状态是待推送或者推送失败的才继续
										if(SapStatus.WAIT_PUSH_SAP.getValue().equals(pushSap.getPushStatus()) || SapStatus.PUSH_SAP_FAIL.getValue().equals(pushSap.getPushStatus())) {
											// 根据订单id查询订单信息
											Order orderInfo = orderService.queryOrderInfo(pushSap.getOrderId());
											
											if(null != orderInfo){
												//订单状态表
												OrderStatusLog orderStatusLog = new OrderStatusLog();
												orderStatusLog.setOrderId(orderInfo.getOrderId()); //订单id
												orderStatusLog.setIsShow(TrueFalseStatus.FALSE.getValue()); //是否前台显示
												orderStatusLog.setOrderStatus(orderInfo.getOrderStatus()); //订单状态
												orderStatusLog.setOrderType(OrderType.ORDER.getValue()); //订单类型 订单
												orderStatusLog.setRecordTime(new Date()); //记录时间
                                                orderStatusLog.setUpdateTime(new Date());
												
												//更新推送sap状态和推送次数
												pushSap.setPushNum(pushSap.getPushNum() +1); //推送次数+1
                                                pushSap.setUpdateTime(new Date());
												
												if(GlobalDisconf.sapPayResult.equals(status)) { 
													//成功
													orderStatusLog.setDetails("推送正向SAP收款成功"); //状态说明
													pushSap.setPushStatus(SapStatus.PUSH_SAP_SUCCESS.getValue()); //推送成功
												}else{
													//失败
													orderStatusLog.setDetails("推送正向SAP收款失败"); //状态说明
													pushSap.setPushStatus(SapStatus.PUSH_SAP_FAIL.getValue()); //推送失败
													//报警埋点
													MonitorUtil.recordOne("Positive_Sap_Pay_Response_Faild");
												}
												orderStatusLogs.add(orderStatusLog);
												pushSaps.add(pushSap);
											}
										}
									}
								}
							}
							
							//批量执行
							orderService.batchExecute(orderStatusLogs,pushSaps);
						}
					}
				}
			}
		}		
	}

	
	
	@Override
	public void pushNegativeSapPay(List<OrderRefund> orderRefunds) throws BizException {
		if(CollectionUtils.isEmpty(orderRefunds)) {
			logger.info("没有要推送逆向SAP收款的数据");
		}else{
			logger.info("推送逆向SAP收款数据{}", jsonUtils.serialize(orderRefunds));
			SapRequest sapRequest = this.buildNegativeSapPayData(orderRefunds);
			
			//构建SAP逆向收款数据
			ZECSDAYSACC zECSDAYSACC = sapService.buildSapPayData(sapRequest);
			logger.info("推送逆向SAP收款请求数据{}", jsonUtils.serialize(zECSDAYSACC));
			
			//构建SAP收款服务的代理
			SIIFI02REQ sIIFI02REQ = sapService.buildSapPayServiceProxy(sapRequest);
			
			if(null != sIIFI02REQ && null != zECSDAYSACC) {
				//调用逆向SAP收款服务
				ZECSDAYSACCResponse response = sIIFI02REQ.siIFI02REQ(zECSDAYSACC);
				logger.info("推送逆向SAP收款响应结果{}", jsonUtils.serialize(response));
				
				//记录接口日志
				APILog.build("推送逆向SAP收款",LogType.SAP_NEGATIVE_PAY).appendRequest("request",zECSDAYSACC).appendResponse("response",response).record();
				
				if(null != response) {
					ZECSDAYSACCResponse.RTN rtn = response.getRTN();
					if(null != rtn) {
						List<ZLOMATNRTOOA> items = rtn.getItem();
						if(!CollectionUtils.isEmpty(items)) {
							List<OrderStatusLog> orderStatusLogs = new ArrayList<OrderStatusLog>(); //保存订单状态表
							List<PushSap>        pushSaps 		 = new ArrayList<PushSap>(); 		//更新推送SAP表
							for(ZLOMATNRTOOA item : items) {
								//行项目主键
								String zkey = item.getZKEY();
								//状态
								String status = item.getZJGBS();
								
								if(StringUtils.isNotEmpty(zkey) && zkey.length() > 6 && StringUtils.isNotEmpty(status)) {
									//去掉前6位得到退款单id
									String refundId = zkey.substring(6);
									
									//根据退款单id和sap类型查询推送sap信息
									PushSap pushSap = this.getPushSap(new PushSap(null,refundId,SapType.NEGATIVE_SAP_PAY.getValue()));
									
									
									if(null != pushSap) {
										//只有推送状态是待推送或者推送失败的才继续
										if(SapStatus.WAIT_PUSH_SAP.getValue().equals(pushSap.getPushStatus()) || SapStatus.PUSH_SAP_FAIL.getValue().equals(pushSap.getPushStatus())) {
											
											// 根据退款单id查询退款单信息
											OrderRefund orderRefund = orderRefundService.queryOrderRefund(refundId);
											if(null != orderRefund){
												//订单状态表
												OrderStatusLog orderStatusLog = new OrderStatusLog();
												orderStatusLog.setOrderId(orderRefund.getRefundId()); //退款单id
												orderStatusLog.setIsShow(TrueFalseStatus.FALSE.getValue()); //是否前台显示
												orderStatusLog.setOrderStatus(orderRefund.getRefundStatus()); //订单状态
												orderStatusLog.setOrderType(OrderType.REFUND.getValue()); //订单类型 退款单
												orderStatusLog.setRecordTime(new Date()); //记录时间
                                                orderStatusLog.setUpdateTime(new Date());
												
												//更新推送sap状态和推送次数
												pushSap.setPushNum(pushSap.getPushNum() +1); //推送次数+1
                                                pushSap.setUpdateTime(new Date());
												
												if(GlobalDisconf.sapPayResult.equals(status)) { 
													//成功
													orderStatusLog.setDetails("推送逆向SAP收款成功"); //状态说明
													pushSap.setPushStatus(SapStatus.PUSH_SAP_SUCCESS.getValue()); //推送成功
												}else{
													//失败
													orderStatusLog.setDetails("推送逆向SAP收款失败"); //状态说明
													pushSap.setPushStatus(SapStatus.PUSH_SAP_FAIL.getValue()); //推送失败
													//报警埋点
													MonitorUtil.recordOne("Negative_Sap_Pay_Response_Faild");
												}
												orderStatusLogs.add(orderStatusLog);
												pushSaps.add(pushSap);
											}
										}
									}
								}
							}
							
							//批量执行
							orderService.batchExecute(orderStatusLogs,pushSaps);
						}
					}
				}
			}
		}
	}

	@Override
	public void pushPositiveSapIncome(List<OrderPay> orderPays) throws BizException {
		if(CollectionUtils.isEmpty(orderPays)) {
			logger.info("没有要推送正向SAP收入的数据");
		}else{
			logger.info("推送正向SAP收入数据{}", jsonUtils.serialize(orderPays));
			SapRequest sapRequest = this.buildPositiveSapIncomeData(orderPays);
			
			//构建推送正向SAP收入数据
			DTIFI402VirtualBusinessIncomeREQ dTIFI402VirtualBusinessIncomeREQ = sapService.buildSapIncomeData(sapRequest);
			logger.info("推送正向SAP收入请求数据{}", jsonUtils.serialize(dTIFI402VirtualBusinessIncomeREQ));
			
			//构建SAP收入服务的代理
			SIXUNI2ECCIFI402VirtualBusinessIncomeREQ sIXUNI2ECCIFI402VirtualBusinessIncomeREQ = sapService.buildSapIncomeServiceProxy(sapRequest);
			
			if(null != sIXUNI2ECCIFI402VirtualBusinessIncomeREQ && null != dTIFI402VirtualBusinessIncomeREQ) {
				//调用正向SAP收入服务
				DTIFI402VirtualBusinessIncomeRES response = sIXUNI2ECCIFI402VirtualBusinessIncomeREQ.siXUNI2ECCIFI402VirtualBusinessIncomeREQ(dTIFI402VirtualBusinessIncomeREQ);
				logger.info("推送正向SAP收入响应结果{}", jsonUtils.serialize(response));
				
				//记录接口日志
				APILog.build("推送正向SAP收入", LogType.SAP_POSITIVE_INCOME).appendRequest("request", dTIFI402VirtualBusinessIncomeREQ).appendResponse("response", response).record();
				
				//更新推送sap推送状态为已推送
				List<OrderStatusLog> orderStatusLogs = new ArrayList<OrderStatusLog>(); //保存订单状态表
				List<PushSap> pushSaps = new ArrayList<PushSap>(); //更新推送SAP表
				
				for(OrderPay orderPay : orderPays) {
					//根据支付id和sap类型查询推送sap信息
					PushSap pushSap = this.getPushSap(new PushSap(null,String.valueOf(orderPay.getId()),SapType.POSITIVE_SAP_INCOME.getValue()));
					
					if(null != pushSap) {
						//只有推送状态是待推送或者推送失败的才继续
						if(SapStatus.WAIT_PUSH_SAP.getValue().equals(pushSap.getPushStatus()) 
								|| SapStatus.PUSH_SAP_FAIL.getValue().equals(pushSap.getPushStatus())) {
							//订单状态表
							OrderStatusLog orderStatusLog = new OrderStatusLog();
							orderStatusLog.setOrderId(orderPay.getOrderId()); //订单id
							orderStatusLog.setIsShow(TrueFalseStatus.FALSE.getValue()); //是否前台显示
							orderStatusLog.setOrderStatus(OrderStatus.ORDER_SUCCESS.getValue()); //订单状态
							orderStatusLog.setOrderType(OrderType.ORDER.getValue()); //订单类型 订单
							orderStatusLog.setDetails("已推送正向SAP收入"); //状态说明
							orderStatusLog.setRecordTime(new Date()); //记录时间
                            orderStatusLog.setUpdateTime(new Date());
							orderStatusLogs.add(orderStatusLog);
							
							//更新推送sap状态和推送次数
							pushSap.setPushStatus(SapStatus.HAS_PUSH_SAP.getValue()); //已推送
							pushSap.setPushNum(pushSap.getPushNum() + 1); //推送次数+1
                            pushSap.setUpdateTime(new Date());
							pushSaps.add(pushSap);
						}
					}
				}
				
				//批量执行
				orderService.batchExecute(orderStatusLogs, pushSaps);
			}
		}
	}


	@Override
	public void pushNegativeSapIncome(List<OrderRefund> orderRefunds) throws BizException {
		if(CollectionUtils.isEmpty(orderRefunds)) {
			logger.info("没有要推送逆向SAP收入的数据");
		}else{
			logger.info("推送逆向SAP收入数据{}", jsonUtils.serialize(orderRefunds));
			
			SapRequest sapRequest = buildNegativeSapIncomeData(orderRefunds);
			
			//构建推送逆向SAP收入数据
			DTIFI402VirtualBusinessIncomeREQ dTIFI402VirtualBusinessIncomeREQ = sapService.buildSapIncomeData(sapRequest);
			logger.info("推送逆向SAP收入请求数据{}", jsonUtils.serialize(dTIFI402VirtualBusinessIncomeREQ));
			
			//构建SAP收入服务的代理
			SIXUNI2ECCIFI402VirtualBusinessIncomeREQ sIXUNI2ECCIFI402VirtualBusinessIncomeREQ = sapService.buildSapIncomeServiceProxy(sapRequest);
			if(null != sIXUNI2ECCIFI402VirtualBusinessIncomeREQ && null != dTIFI402VirtualBusinessIncomeREQ) {
				//调用逆向SAP收入服务
				DTIFI402VirtualBusinessIncomeRES response = sIXUNI2ECCIFI402VirtualBusinessIncomeREQ.siXUNI2ECCIFI402VirtualBusinessIncomeREQ(dTIFI402VirtualBusinessIncomeREQ);
				logger.info("推送逆向SAP收入响应结果{}", jsonUtils.serialize(response));
				
				//记录接口日志
				APILog.build("推送逆向SAP收入",LogType.SAP_NEGATIVE_INCOME).appendRequest("request",dTIFI402VirtualBusinessIncomeREQ).appendResponse("response",response).record();
				
				//更新推送sap推送状态为已推送
				List<OrderStatusLog> orderStatusLogs = new ArrayList<OrderStatusLog>(); //保存订单状态表
				List<PushSap> pushSaps = new ArrayList<PushSap>(); //更新推送SAP表
				for(OrderRefund orderRefund : orderRefunds) {
					//根据退款单id和sap类型查询推送sap信息
					PushSap pushSap = this.getPushSap(new PushSap(null,orderRefund.getRefundId(),SapType.NEGATIVE_SAP_INCOME.getValue()));
					
					if(null != pushSap) {
						//只有推送状态是待推送或者推送失败的才继续
						if(SapStatus.WAIT_PUSH_SAP.getValue().equals(pushSap.getPushStatus()) || SapStatus.PUSH_SAP_FAIL.getValue().equals(pushSap.getPushStatus())) {
							//订单状态表
							OrderStatusLog orderStatusLog = new OrderStatusLog();
							orderStatusLog.setOrderId(orderRefund.getRefundId()); //退款单id
							orderStatusLog.setIsShow(TrueFalseStatus.FALSE.getValue()); //是否前台显示
							orderStatusLog.setOrderStatus(orderRefund.getRefundStatus()); //订单状态
							orderStatusLog.setOrderType(OrderType.REFUND.getValue()); //订单类型 退款单
							orderStatusLog.setDetails("已推送逆向SAP收入"); //状态说明
							orderStatusLog.setRecordTime(new Date()); //记录时间
                            orderStatusLog.setUpdateTime(new Date());
							orderStatusLogs.add(orderStatusLog);
							
							//更新推送sap状态和推送次数
							pushSap.setPushStatus(SapStatus.HAS_PUSH_SAP.getValue()); //已推送
							pushSap.setPushNum(pushSap.getPushNum() + 1); //推送次数+1
                            pushSap.setUpdateTime(new Date());
							pushSaps.add(pushSap);
						}
					}
				}
				
				//批量执行
				orderService.batchExecute(orderStatusLogs,pushSaps);
			}
		}
	}
	
	
	
	
	
	/**
	 * 构建正向SAP收款数据
	 * @param orderPays
	 * @return
	 */
	private SapRequest buildPositiveSapPayData(List<OrderPay> orderPays) {
		if(CollectionUtils.isEmpty(orderPays)) {
			logger.error("推送正向SAP收款数据不能为空");
			return null;
		}

		SapRequest 	  sapRequest  = new SapRequest();
		List<SapData> sapDataList = new ArrayList<SapData>();
		
		sapRequest.setSapPayAddress(GlobalDisconf.getSapPayAddress());
		sapRequest.setSapPayUsername(GlobalDisconf.getSapPayUsername());
		sapRequest.setSapPayPassword(GlobalDisconf.getSapPayPassword());

		for (OrderPay orderPay : orderPays) {
			SapData sapData    = new SapData();
			String  payTime    = DateUtils.format(orderPay.getPayTime(), DateUtils.SHORT_FORMAT); //支付时间
			String  merchantNo = orderPay.getMerchantNo(); //交易流水号
			
			if(Arrays.asList("10","K1","C7").contains(orderPay.getPayModeSap())) {
				merchantNo = payTime + merchantNo;	//在线_银联 和 快捷_银联   支付时间+交易流水号
			}
			
			sapData.setSapType(0);		//0:收款;1:收入
			sapData.setOrderType(0);	//0:正向;1:逆向
			sapData.setOrderPayId(orderPay.getId());
			sapData.setOrderId(orderPay.getOrderId());
			sapData.setZJYCK(merchantNo); 						//交易参考号，供应商交易流水号
			sapData.setPayModeSap(orderPay.getPayModeSap()); 	//收款方式
			sapData.setPayAmount(orderPay.getPayMoney()); 		//应收账款金额(订单支付金额)(正值)
			sapData.setBUDAT(DateUtils.format(new Date(),DateUtils.SHORT_FORMAT)); //调用接口日期
			sapData.setBLDAT(payTime); 								 //凭证日期（支付时间）
			sapData.setZSAUT(GlobalDisconf.getSapOrderTypeSO());     //订单类型(SO:销售)
			sapData.setZXSLY(GlobalDisconf.getOrderType()); 	     //订单销售来源
			sapData.setZSKLX(GlobalDisconf.getSapSaleChannelType()); //销售渠道类型，25虚拟业务
			
			if(orderPay.getIsRepeatPay()) {
				sapData.setZPAYID(merchantNo);						 //支付id(支付交易流水号)
				sapData.setZYWLX(GlobalDisconf.getSapAbnormalPay()); //收款类型(11:异常收款)
				sapData.setIsRepeatPay(true);
			}else{
				//正常支付，正常收款
				sapData.setZYWLX(GlobalDisconf.getSapPositivePay()); //收款类型(10:正常收款)
				sapData.setIsRepeatPay(false);
			}
			
			Order orderInfo = orderService.queryOrderInfo(orderPay.getOrderId());
			if(null != orderInfo) {
				sapData.setZYYLM(orderInfo.getVenderId()); //合作商编码(供应商编码)
			}
			
			sapDataList.add(sapData);
		}
		
		sapRequest.setSapDataList(sapDataList);
		
		return sapRequest;
	}
	
	
	/**
	 * 构建逆向SAP收款数据
	 * @param orderRefunds
	 * @return
	 */
	private SapRequest buildNegativeSapPayData(List<OrderRefund> orderRefunds) {
		if(CollectionUtils.isEmpty(orderRefunds)) {
			logger.error("推送逆向SAP收款退款单数据不能为空");
			return null;
		}
		
		SapRequest 	  sapRequest  = new SapRequest();
		List<SapData> sapDataList = new ArrayList<SapData>();
		
		sapRequest.setSapPayAddress(GlobalDisconf.getSapPayAddress());
		sapRequest.setSapPayUsername(GlobalDisconf.getSapPayUsername());
		sapRequest.setSapPayPassword(GlobalDisconf.getSapPayPassword());
		
		for (OrderRefund orderRefund : orderRefunds) {
			
			SapData sapData = new SapData();
			
			sapData.setSapType(0);		//0:收款;1:收入
			sapData.setOrderType(1);	//0:正向;1:逆向
			sapData.setOrderId(orderRefund.getOrderId());
			sapData.setRefundId(orderRefund.getRefundId());
			sapData.setZJYCK(orderRefund.getRefundBankTransNum());// 交易参考号（退款交易流水号）
			sapData.setVenderOrderId(orderRefund.getVenderOrderId());// 客户订单参考号，逆向时需要
			sapData.setZSAUT(GlobalDisconf.getSapOrderTypeRO()); 	 // 订单类型 逆向RO
			sapData.setPayModeSap(orderRefund.getPayModeSap());		 // 收款方式， sap
			sapData.setPayAmount(orderRefund.getReturnAmount().negate()); // 应收账款金额， 订单总金额(负值)
			sapData.setZYWLX(GlobalDisconf.getSapNegativePay()); 	 // 收款类型， 21原路返回
			sapData.setZXSLY(GlobalDisconf.getOrderType()); 		 // 订单销售来源
			sapData.setZSKLX(GlobalDisconf.getSapSaleChannelType()); // 销售渠道类型，25虚拟业务
			sapData.setBUDAT(DateUtils.format(new Date(), DateUtils.SHORT_FORMAT)); // 调用接口日期
			sapData.setBLDAT(DateUtils.format(orderRefund.getFinishTime(), DateUtils.SHORT_FORMAT)); // 凭证日期（退款完成时间）
			
			Order orderInfo = orderService.queryOrderInfo(orderRefund.getOrderId());
			if(null != orderInfo){
				sapData.setZYYLM(orderInfo.getVenderId()); //合作商编码(供应商编码)
			}

			sapDataList.add(sapData);
		}
		
		sapRequest.setSapDataList(sapDataList);
		
		return sapRequest;
	}
	
	/**
	 * 构建正向SAP收入数据
	 * @author WenJie Mai
	 *
	 * @param orderPays
	 * @return
	 */
	private SapRequest buildPositiveSapIncomeData(List<OrderPay> orderPays) {
		
		SapRequest    sapRequest  = new SapRequest();
		List<SapData> sapDataList = new ArrayList<SapData>();
		
		sapRequest.setSapIncomeAddress(GlobalDisconf.getSapIncomeAddress());
		sapRequest.setSapIncomeUsername(GlobalDisconf.getSapIncomeUsername());
		sapRequest.setSapIncomePassword(GlobalDisconf.getSapIncomePassword());
		
		for (OrderPay orderPay : orderPays) {
			Order orderInfo = orderService.queryOrderInfo(orderPay.getOrderId());
			
			if(null != orderInfo){
				SapData sapData = new SapData();
				
				sapData.setSapType(1);		//0:收款;1:收入
				sapData.setOrderType(0);	//0:正向;1:逆向
				sapData.setOrderId(orderInfo.getOrderId());
				sapData.setOrderPayId(orderPay.getId());
				sapData.setCreateTime(orderInfo.getCreateTime());
				sapData.setVenderOrderId(orderInfo.getVenderOrderId());					//第三方订单号(供应商订单号)
				sapData.setLIFNR(GlobalDisconf.getSapShopNum());	//店铺编号
				sapData.setZSAUT(GlobalDisconf.getSapOrderTypeSO());//订单类型(SO:销售)
				sapData.setZYWLX(GlobalDisconf.getSapPositivePay());//收款类型(10: 正常收款)
				sapData.setBUDAT(DateUtils.format(new Date(), DateUtils.SHORT_FORMAT)); //调用接口日期
				sapData.setZXSLY(GlobalDisconf.getOrderType());		//订单销售来源
				sapData.setMATKL(GlobalDisconf.getSapProductCategory());// 商品品类
				sapData.setZPP(GlobalDisconf.getSapProductBrand());		// 商品品牌
				sapData.setZJSFA(GlobalDisconf.getSapCommission());		// 佣金计算方案(20:定拥类型)
				sapData.setZPRTY_SALE_PRICE(GlobalDisconf.getSapSalePrice()); // 价格类型 PR01  销售单价
				sapData.setZPRTY_AMOUNT(GlobalDisconf.getSapReceiveAmount()); // 价格类型 FKJE 应收金额
				sapData.setNETWR(BigDecimalUtil.format(orderInfo.getPackagePrice()));// 单价、保留2位小数   --》统一要求传订单总金额---->再次修改为单价
				sapData.setZYSZK(BigDecimalUtil.format(orderPay.getPayMoney()));// 实收用户金额、保留2位小数---->再次修改为单价
				sapData.setZPRJE(BigDecimalUtil.format(orderInfo.getPackagePrice()));// 金额、保留2位小数  --》统一要求传订单总金额---->再次修改为单价
				sapData.setZMENGE(1);								  			// 销售数量  --》统一要求传1
				sapData.setTicketNum(orderInfo.getBuyCount());					// 购买数量
				
				//销售站点来源
				sapData.setZSITE(OrderSource.getKeyByValue(orderInfo.getOrderSource()));
				
				sapDataList.add(sapData);
			}
		}
		
		sapRequest.setSapDataList(sapDataList);
		
		return sapRequest;
	}
	
	
	/**
	 * 构建逆向SAP收入数据
	 * @author WenJie Mai
	 *
	 * @return
	 */
	private SapRequest buildNegativeSapIncomeData(List<OrderRefund> orderRefunds) {
		
		SapRequest    sapRequest  = new SapRequest();
		List<SapData> sapDataList = new ArrayList<SapData>();
		
		sapRequest.setSapIncomeAddress(GlobalDisconf.getSapIncomeAddress());
		sapRequest.setSapIncomeUsername(GlobalDisconf.getSapIncomeUsername());
		sapRequest.setSapIncomePassword(GlobalDisconf.getSapIncomePassword());
		
		for (OrderRefund orderRefund : orderRefunds) {
			//根据订单id查询订单信息
			Order orderInfo = orderService.queryOrderInfo(orderRefund.getOrderId());
			
			if(null != orderInfo){
				SapData sapData = new SapData();
				
				sapData.setSapType(1);		//0:收款;1:收入
				sapData.setOrderType(1);	//0:正向;1:逆向
				sapData.setOrderId(orderRefund.getOrderId());
				sapData.setRefundId(orderRefund.getRefundId());
				sapData.setCreateTime(orderRefund.getCreateTime());
				sapData.setBUDAT(DateUtils.format(new Date(), DateUtils.SHORT_FORMAT)); // 调用接口日期
				sapData.setVenderOrderId(orderInfo.getVenderOrderId());// 第三方订单号(供应商订单号)
				sapData.setLIFNR(GlobalDisconf.getSapShopNum());// 店铺编号
				sapData.setZSAUT(GlobalDisconf.getSapOrderTypeRO());// 订单类型(RO:退货)
				sapData.setZYWLX(GlobalDisconf.getSapNegativePay());// 收款类型(21:原路返回)
				sapData.setZXSLY(GlobalDisconf.getOrderType());			// 订单销售来源
				sapData.setMATKL(GlobalDisconf.getSapProductCategory());// 商品品类
				sapData.setZPP(GlobalDisconf.getSapProductBrand());// 商品品牌
				sapData.setZJSFA(GlobalDisconf.getSapCommission());		       // 佣金计算方案(20:定拥类型)
				sapData.setZPRTY_SALE_PRICE(GlobalDisconf.getSapSalePrice()); // 价格类型 PR01  销售单价
				sapData.setZPRTY_AMOUNT(GlobalDisconf.getSapReceiveAmount()); // 价格类型 FKJE 应收金额
				sapData.setNETWR(BigDecimalUtil.format(orderRefund.getReturnAmount().negate()));// 单价、保留2位小数  --》统一要求传订单总金额
				sapData.setZYSZK(BigDecimalUtil.format(orderRefund.getReturnAmount().negate()));// 实收用户金额、保留2位小数
				sapData.setZPRJE(BigDecimalUtil.format(orderInfo.getPackagePrice().negate()));// 金额、保留2位小数  --》统一要求传订单总金额
				sapData.setZMENGE(1);// 销售数量  --》统一要求传1
				
				//销售站点来源
				sapData.setZSITE(OrderSource.getKeyByValue(orderInfo.getOrderSource()));
				
				sapDataList.add(sapData);
			}
		}
		
		sapRequest.setSapDataList(sapDataList);
		
		return sapRequest;
	}
	
	
	
	
	
	
	
	
	
	
	@Override
	public int insert(PushSap record) {
		return pushSapMapper.insert(record);
	}
	
	@Override
	public PushSap getPushSap(PushSap pushSap) {
		if(null != pushSap) {
			return pushSapMapper.getPushSap(pushSap);
		}
		return null;
	}
	
	@Override
	public List<PushSap> queryPushSap(PushSap pushSap) {
		if(null != pushSap) {
			return pushSapMapper.queryPushSap(pushSap);
		}
		return null;
	}
	
	public PushSap createPushSap(String orderId, String businessNo, Integer sapType) {
		logger.info("创建推送SAP任务表：orderId:{}，businessNo:{}，sapType:{}", orderId, businessNo, sapType);
		if(StringUtils.isNotEmpty(orderId) && StringUtils.isNotEmpty(businessNo) && null != sapType) {
			PushSap pushSap = new PushSap();
			pushSap.setOrderId(orderId); //订单id
			pushSap.setBusinessNo(businessNo); //业务单号
			pushSap.setSapType(sapType); //sap类型
			pushSap.setPushStatus(SapStatus.WAIT_PUSH_SAP.getValue()); //待推送SAP
			pushSap.setPushNum(0); //推送次数
			pushSap.setCreateTime(new Date()); //创建时间
			pushSap.setUpdateTime(new Date()); //修改时间
			return pushSap;
		}
		return null;
	}

	@Override
	public List<PushSap> queryPushSapByInfo(PushSapInfo pushSapInfo) {
		return pushSapMapper.select(pushSapInfo);
	}

	@Override
	public List<PushSap> queryPushPositiveSapByInfo(PushSap pushSapInfo) {
		return pushSapMapper.queryPushPositiveSapByInfo(pushSapInfo);
	}

    @Override
    public void batchInsert(List<PushSap> positiveSapIncomes, List<PushSap> negativeSapIncomes) {
        if(!CollectionUtils.isEmpty(positiveSapIncomes)){
            pushSapMapper.batchInsert(positiveSapIncomes);
        }

        if(!CollectionUtils.isEmpty(negativeSapIncomes)){
            pushSapMapper.batchInsert(negativeSapIncomes);
        }
    }
}

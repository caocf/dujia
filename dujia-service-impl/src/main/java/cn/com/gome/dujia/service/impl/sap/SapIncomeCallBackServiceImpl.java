package cn.com.gome.dujia.service.impl.sap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.enums.OrderStatus;
import cn.com.gome.dujia.enums.OrderType;
import cn.com.gome.dujia.enums.SapStatus;
import cn.com.gome.dujia.enums.SapType;
import cn.com.gome.dujia.enums.TrueFalseStatus;
import cn.com.gome.dujia.log.APILog;
import cn.com.gome.dujia.log.LogType;
import cn.com.gome.dujia.model.OrderPay;
import cn.com.gome.dujia.model.OrderRefund;
import cn.com.gome.dujia.model.OrderStatusLog;
import cn.com.gome.dujia.model.PushSap;
import cn.com.gome.dujia.service.OrderPayService;
import cn.com.gome.dujia.service.OrderRefundService;
import cn.com.gome.dujia.service.OrderService;
import cn.com.gome.dujia.service.PushSapService;
import cn.com.gome.dujia.service.SmsLogService;
import cn.com.gome.dujia.service.sap.SapIncomeCallBackService;
import cn.com.gome.dujia.vo.sap.SapIncomeResultItem;
import cn.com.gome.dujia.vo.sap.SapIncomeResultResp;
import com.gome.plan.tools.utils.JsonUtils;
import com.gome.plan.tools.utils.MonitorUtil;
import com.gome.plan.tools.utils.StringUtils;
/**
 * Description : sap收入回调服务
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月29日 下午2:33:30 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
@Service
public class SapIncomeCallBackServiceImpl implements SapIncomeCallBackService {

	private static final Logger logger = LoggerFactory.getLogger(SapIncomeCallBackServiceImpl.class);
	
	private static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON);
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderRefundService orderRefundService;
	
	@Autowired
	private PushSapService pushSapService;
	
	@Autowired
	private OrderPayService orderPayService;
	
	@Autowired
	private SmsLogService smsService;
	
	/**
	 * 提供给推送sap收入回调的服务方法
	 * @param sapIncomeResultItems
	 * @return
	 */
	public SapIncomeResultResp sapIncomeAuditResult(List<SapIncomeResultItem> sapIncomeResultItems) {
		
		SapIncomeResultResp response = null;
		
		try {
			if(CollectionUtils.isEmpty(sapIncomeResultItems)) {
				logger.error("SAP收入回调方法参数不能为空");
				response = new SapIncomeResultResp("500", "SAP收入回调方法参数不能为空");
				return response;
			}
			
			logger.info("接收SAP收入回调方法参数{}", jsonUtils.serialize(sapIncomeResultItems));
			
			List<OrderStatusLog> orderStatusLogs = new ArrayList<OrderStatusLog>(); //保存订单状态表
			List<PushSap> 		 pushSaps		 = new ArrayList<PushSap>(); 		//更新推送SAP表
			
			for(SapIncomeResultItem sapIncomeResultItem : sapIncomeResultItems) {
				//单号id
				String id = sapIncomeResultItem.getZXNDDH();
				//状态
				String status = sapIncomeResultItem.getTransaction_status();
				if(StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(status)) {
					if(id.startsWith(GlobalDisconf.getRefundPrefix())) {
						//退款单处理
						logger.info("推送逆向SAP收入回调：退款单号{}，状态{}", id, status);
						refundHandle(orderStatusLogs, pushSaps, id, status);
					}else{
						//订单处理
						logger.info("推送正向SAP收入回调：订单号{}，状态{}", id, status);
						orderHandle(orderStatusLogs, pushSaps, id, status);
					}
				}
			}
			
			//批量执行
			orderService.batchExecute(orderStatusLogs,pushSaps);
			response = new SapIncomeResultResp("200", "推送SAP收入回调方法处理成功");
			return response;
		} catch (Exception e) {
			logger.error("推送SAP收入回调方法处理失败：", e);
			response = new SapIncomeResultResp("500", "推送SAP收入回调方法处理失败");
			return response;
		} finally {
			//记录接口日志
			APILog.build("接收SAP收入回调接口",LogType.SAP_INCOME_CALLBACK).appendRequest("request",sapIncomeResultItems).appendResponse("response",response).record();
		}
	}
	
	/**
	 * 订单处理
	 * @param orderStatusLogs  订单日志数据
	 * @param pushSaps         更新推送sap数据
	 * @param orderId          订单id
	 * @param status           状态
	 */
	private void orderHandle(List<OrderStatusLog> orderStatusLogs, List<PushSap> pushSaps, String orderId, String status) {
		
		if(StringUtils.isNotEmpty(orderId) && StringUtils.isNotEmpty(status)) {
			//根据订单id查询正常支付的支付信息
			OrderPay orderPay = orderPayService.getOrderPayByOrderId(orderId);
			
			if(null != orderPay) {
				//根据支付id和sap类型查询推送sap信息
				PushSap pushSapObj = new PushSap();
				pushSapObj.setBusinessNo(orderPay.getId()); //支付id
				pushSapObj.setSapType(SapType.POSITIVE_SAP_INCOME.getValue()); //SAP正向收人
				PushSap pushSap = pushSapService.getPushSap(pushSapObj);
				if(null != pushSap) {
					//只有推送状态是已推送的才继续
					if(SapStatus.HAS_PUSH_SAP.getValue().equals(pushSap.getPushStatus())) {
						//订单状态表
						OrderStatusLog orderStatusLog = new OrderStatusLog();
						orderStatusLog.setOrderId(orderId); //订单id
						orderStatusLog.setIsShow(TrueFalseStatus.FALSE.getValue()); //是否前台显示
						orderStatusLog.setOrderStatus(OrderStatus.ORDER_SUCCESS.getValue());//订单状态
						orderStatusLog.setOrderType(OrderType.ORDER.getValue()); //订单类型 订单
						orderStatusLog.setRecordTime(new Date()); //记录时间
						
						if(GlobalDisconf.getSapIncomeResult().equals(status)) { 
							//成功
							orderStatusLog.setDetails("推送正向SAP收入成功"); //状态说明
							pushSap.setPushStatus(SapStatus.PUSH_SAP_SUCCESS.getValue()); //推送成功
						}else{
							//失败
							orderStatusLog.setDetails("推送正向SAP收入失败"); //状态说明
							pushSap.setPushStatus(SapStatus.PUSH_SAP_FAIL.getValue()); //推送失败
							//报警埋点
							MonitorUtil.recordOne("ZBY_Positive_Sap_Income_Response_Faild");
						}
						orderStatusLog.setUpdateTime(new Date()); //修改时间
						orderStatusLogs.add(orderStatusLog);
						
						pushSap.setUpdateTime(new Date()); //修改时间
						pushSaps.add(pushSap);
					}
				}
			}
		}
	}
	
	/**
	 * 退款单处理
	 * @param orderStatusLogs  订单日志数据
	 * @param pushSaps         更新推送sap数据
	 * @param refundId         退款单id
	 * @param status           状态
	 */
	private void refundHandle(List<OrderStatusLog> orderStatusLogs, List<PushSap> pushSaps, String refundId, String status) {
		if(StringUtils.isNotEmpty(refundId) && StringUtils.isNotEmpty(status)) {
			//根据退款单id和sap类型查询推送sap信息
			PushSap pushSapObj = new PushSap();
			pushSapObj.setBusinessNo(refundId); //退款单id
			pushSapObj.setSapType(SapType.NEGATIVE_SAP_INCOME.getValue()); //SAP逆向收人
			PushSap pushSap = pushSapService.getPushSap(pushSapObj);
			if(null != pushSap) {
				//只有推送状态是已推送的才继续
				if(SapStatus.HAS_PUSH_SAP.getValue().equals(pushSap.getPushStatus())) {
					// 根据退款单id查询退款单信息
					OrderRefund orderRefund = orderRefundService.queryOrderRefund(refundId);
					if (null != orderRefund) {
						//订单状态表
						OrderStatusLog orderStatusLog = new OrderStatusLog();
						orderStatusLog.setOrderId(orderRefund.getRefundId()); //退款单id
						orderStatusLog.setIsShow(TrueFalseStatus.FALSE.getValue()); //是否前台显示
						orderStatusLog.setOrderStatus(orderRefund.getRefundStatus()); //订单状态
						orderStatusLog.setOrderType(OrderType.REFUND.getValue()); //订单类型 退款单
						orderStatusLog.setRecordTime(new Date()); //记录时间
						
						if(GlobalDisconf.getSapIncomeResult().equals(status)) { 
							//成功
							orderStatusLog.setDetails("推送逆向SAP收入成功"); //状态说明
							pushSap.setPushStatus(SapStatus.PUSH_SAP_SUCCESS.getValue()); //推送成功
						}else{
							//失败
							orderStatusLog.setDetails("推送逆向SAP收入失败"); //状态说明
							pushSap.setPushStatus(SapStatus.PUSH_SAP_FAIL.getValue()); //推送失败
							//报警埋点
							MonitorUtil.recordOne("ZBY_Negative_Sap_Income_Response_Faild");
						}
						orderStatusLog.setUpdateTime(new Date()); //修改时间
						orderStatusLogs.add(orderStatusLog);
						
						pushSap.setUpdateTime(new Date()); //修改时间
						pushSaps.add(pushSap);
					}
				}
			}
		}
	}

}

package cn.com.gome.dujia.service.impl.rb;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import cn.com.gome.dujia.enums.OrderStatus;
import cn.com.gome.dujia.enums.RefundReason;
import cn.com.gome.dujia.enums.RefundSource;
import cn.com.gome.dujia.enums.SmsType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.dto.PlatformResponseDto;
import cn.com.gome.dujia.enums.OrderType;
import cn.com.gome.dujia.enums.RefundStatus;
import cn.com.gome.dujia.exception.BizException;
import cn.com.gome.dujia.exception.ErrorCode;
import cn.com.gome.dujia.log.APILog;
import cn.com.gome.dujia.log.LogType;
import cn.com.gome.dujia.model.Order;
import cn.com.gome.dujia.model.OrderRefund;
import cn.com.gome.dujia.model.OrderStatusLog;
import cn.com.gome.dujia.service.OrderRefundService;
import cn.com.gome.dujia.service.OrderService;
import cn.com.gome.dujia.service.SmsLogService;
import cn.com.gome.dujia.service.rb.RbCallBackService;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.gome.plan.tools.utils.DateUtils;
import com.gome.plan.tools.utils.Md5Util;
import com.gome.plan.tools.utils.MonitorUtil;
import com.gome.plan.tools.utils.StringUtils;
import org.springframework.util.CollectionUtils;
/**
 * Description : rb回调服务
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月29日 下午2:28:45 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
@Service
@Path("rbBizService")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class RbCallBackServiceImpl implements RbCallBackService {

private static final Logger log = LoggerFactory.getLogger(RbCallBackServiceImpl.class);
	
	@Autowired
	private OrderRefundService orderRefundService;

	@Autowired
	private OrderService orderService;

    @Autowired
    private SmsLogService smsLogService;
	
	/**
	 * rb回调重推策略(已于rb提供方确认)
	 *   			如果 rbRefundCallBack 返回成功, rb下次不再调用
	 *   			如果 rbRefundCallBack 返回失败, rb下次继续调用此方法，知道返回成功为止
	 * 
	 */
    @GET
    @Path("rbRefundCallBack/{refundNo}/{state}/{refundFinishTime}/{refundTransId}/{sign}")
	@Override
	public PlatformResponseDto rbRefundCallBack(@PathParam("refundNo")String refundNo, @PathParam("state")String state, @PathParam("refundFinishTime")String refundFinishTime, 
								@PathParam("refundTransId")String refundTransId, @PathParam("sign")String sign) {
		
		PlatformResponseDto rbDto	 = new PlatformResponseDto();
		Order	          orderInfo  = null;
		OrderStatusLog    orderLog   = null; 
		OrderStatusLog    refundLog  = null;
		String 			  orderId    = "";
		String       	  descption	 = "";
		Boolean      isSendSapPay	 = false;  // 是否推送sap逆向收款   (rb回调成功)

		try {
			  // 记录响应参数日志
			  APILog.build("周边游退款(rb回调)",LogType.RB).appendResponse("refundNo",refundNo).appendResponse("state",state).appendResponse("refundFinishTime",refundFinishTime)
			  									.appendResponse("refundTransId",refundTransId).appendResponse("sign",sign).record();
			
			  // 1.验证必传参数是否为空
			  if(StringUtils.isBlank(refundNo) || StringUtils.isBlank(state) || StringUtils.isBlank(refundFinishTime)  
					  														 || StringUtils.isBlank(refundTransId) || StringUtils.isBlank(sign)){
				  	throw new BizException(ErrorCode.E90002);
			  }
			  
			  // 2.验签 
			  String localSign = Md5Util.md5(getSourceString(refundNo,state,refundFinishTime,refundTransId) + GlobalDisconf.getRbMd5Key());
			  log.info("+++++++++++++++++rbRefundCallBack_sign:"+sign+"_localSign:"+localSign);
			  if(!sign.equals(localSign)){
				 throw new BizException(ErrorCode.E90003);
			  }
			  
			  OrderRefund orderRefund = new OrderRefund();
			  orderRefund.setRefundId(refundNo);
			  List<OrderRefund> refundList = orderRefundService.queryOrderRefundList(orderRefund);
			  
			  // 3.验证退款单号
			  if(refundList == null || refundList.size() == 0){
				 throw new BizException(ErrorCode.E60001);
			  }
			  
			  OrderRefund refund = refundList.get(0);
			  
			  // 4.验证退款状态(TODO: rb回调的都是之前rb调用成功的数据,也就是已推RB类型的退款单),防止重复调用
			  if(refund.getRefundStatus() != RefundStatus.HAS_SEND_RB.ordinal()){
				  throw new BizException(ErrorCode.E60002);
			  }
			  
			  		  orderId 	  = refund.getOrderId();
			  Integer refundReason= refund.getRefundReason();
			  
			  log.info("+++++++++++++++++rbRefundCallBack_orderId:"+orderId+"_refundNo:"+refundNo+"_refundReason:"+refundReason);
			  
			  // 5.退款响应结果      TODO: 注意：退款成功的退款单、更改订单表的订单状态为：已退款(除重复支付退款的退款类型外)
			  if("S".equalsIgnoreCase(state)){
				  isSendSapPay = true;
				  refund.setRefundStatus(RefundStatus.REFUND_COMPLETE.ordinal());         //  RB审核通过
				  
				  // 查询订单
				  orderInfo = orderService.queryOrderInfo(orderId);
				  
				  // 发送退款短信(同程发起退款)
				  if(RefundReason.TC_REFUND.ordinal() == refundReason && RefundSource.TC.ordinal() == refund.getRefundSource()){
                      Map<String, Object> paramMap = new HashMap<String, Object>();
                      paramMap.put("orderId",orderInfo.getOrderId());
                      paramMap.put("refundAmount",refund.getRefundAmount());
                      smsLogService.sendSms(SmsType.VENDER_PAY_REFUND.getDiffId(),orderInfo.getContactsTelphone(),paramMap);
				  }
				  
				  // 排除重复支付退款的数据
				  if(RefundReason.REPEAT_PAY_REFUND.ordinal() != refundReason){
                      // 如果不是重复支付、只有当全额退款时，订单状态=已退款，否则不改订单状态
					  if(orderInfo != null){
                          OrderRefund refundParam = new OrderRefund();
                          refundParam.setOrderId(orderInfo.getOrderId());
                          List<OrderRefund> refunds = orderRefundService.queryOrderRefundList(refundParam);
                          if(!CollectionUtils.isEmpty(refunds)){
                              BigDecimal tatalRefundAmount = new BigDecimal(0);
                              for (OrderRefund rf : refunds){
                                  // 排除重复支付
                                  if(rf.getRefundReason() != RefundReason.REPEAT_PAY_REFUND.ordinal()){
                                      tatalRefundAmount = tatalRefundAmount.add(rf.getReturnAmount());
                                  }
                              }
                              if(orderInfo.getOrderAmount().compareTo(tatalRefundAmount) == 0){
                                  orderInfo.setOrderStatus(OrderStatus.REFUND_SUCCESS.getValue());
                                  orderInfo.setUpdateTime(new Date());
                                  // 订单日志
                                  orderLog = new OrderStatusLog(orderId,true,OrderStatus.REFUND_SUCCESS.getValue(),OrderType.ORDER.getValue(),OrderStatus.REFUND_SUCCESS.getName(),new Date(),new Date());
                              }else{
                                  orderInfo = null; //由于部分退款不更改订单状态，所以不需要更新订单记录
                                  orderLog  = null;
                              }
                          }
                      }
				  }else{// 重复支付退款单不操作任何订单信息
					  orderInfo = null;
					  orderLog  = null;
				  }
				  
				  descption = RefundStatus.REFUND_COMPLETE.getName();
			  }else{
				  // TODO: 如果rb回调返回失败，则将退款状态改为： RB审核不通过 报警
				  refund.setRefundStatus(RefundStatus.REFUND_FAILD.ordinal());          // RB审核不通过
                  //报警埋点
				  MonitorUtil.recordOne("ZBY_RB_Audit_Faild_CallBack");
				  descption = RefundStatus.REFUND_FAILD.getName();
			  }
			  
			  // 修改时间
			  refund.setUpdateTime(new Date());
			  
			  // 退款银行流水号
			  if(!StringUtils.isBlank(refundTransId)){
				  refund.setRefundBankTransNum(refundTransId);
			  }
			  
			  //退款完成时间
			  if(!StringUtils.isBlank(refundFinishTime)){ // refundFinishTime:yyyyMMddHHmmss
				     String pattern1 = "yyyyMMddHHmmss"; 
					 String pattern2 = "yyyy-MM-dd HH:mm:ss";
					 Date   dt = DateUtils.parseDate(refundFinishTime,pattern1);
					 String ss = DateUtils.format(dt,pattern2);
				     refund.setFinishTime(DateUtils.parseDate(ss,pattern2));
			  }
			 
			  // 退款单日志
			  refundLog = new OrderStatusLog(refund.getRefundId(),false,refund.getRefundStatus(),OrderType.REFUND.getValue(),descption,new Date(),new Date());
	    	  
			  // 6.更新退款记录、新增退款状态日志记录
	    	  orderRefundService.updateRefundAndOrder(orderInfo,orderLog,refund,refundLog,isSendSapPay,false);
			  
	    	  // 7.返回成功状态
	    	  rbDto.setState(ErrorCode.E90000.name());
	    	  rbDto.setReasonCode(ErrorCode.E90000.getDesc());
		}catch(BizException biz){
			rbDto.setState(biz.getMessage().split(":")[0]);
			rbDto.setReasonCode(biz.getMessage().split(":")[1]);
			log.error("+++++++++++++++++rbRefundCallBack_refundId:"+refundNo+"_BizException:"+biz.getMessage(),biz);
			MonitorUtil.recordOne("ZBY_RB_Audit_Faild_BizException");
		}catch (Exception e) {
			rbDto.setState(ErrorCode.E90001.name());
			rbDto.setReasonCode(ErrorCode.E90001.getDesc());
			log.error("+++++++++++++++++rbRefundCallBack_refundId:"+refundNo+"_Exception:"+e.getMessage(),e);
			MonitorUtil.recordOne("ZBY_RB_Audit_Faild_Exception");
		}finally{
			log.info("++++++++++++++++++rbRefundCallBack_rspCode:"+rbDto.getState()+"_:rspMessage"+rbDto.getReasonCode());
			if("E90000".equals(rbDto.getState())){
				rbDto.setState("S");
			}else{
				rbDto.setState("E");
			}
		}
		
		return rbDto;
	}
	
	private String getSourceString(String refundNo,String state,String refundFinishTime,String refundTransId){
		
		Map<String,String> paramMap  = new TreeMap<String,String>();
		StringBuilder   paramBuilder = new StringBuilder();
		
		paramMap.put("refundNo",refundNo);
		paramMap.put("state",state);
		paramMap.put("refundFinishTime",refundFinishTime);
		paramMap.put("refundTransId",refundTransId);
		
		for (Entry<String,String> entry : paramMap.entrySet()) {
			paramBuilder.append(entry.getValue());
		}
		
		log.info("++++++++++++getSourceString:"+paramBuilder.toString());
		
		return paramBuilder.toString();
	}

}

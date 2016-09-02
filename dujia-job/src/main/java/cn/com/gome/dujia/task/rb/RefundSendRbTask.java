package cn.com.gome.dujia.task.rb;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.dto.ResponseDto;
import cn.com.gome.dujia.enums.OrderType;
import cn.com.gome.dujia.enums.RefundStatus;
import cn.com.gome.dujia.exception.BizException;
import cn.com.gome.dujia.exception.ErrorCode;
import cn.com.gome.dujia.model.OrderPay;
import cn.com.gome.dujia.model.OrderRefund;
import cn.com.gome.dujia.model.OrderStatusLog;
import cn.com.gome.dujia.model.RbRefund;
import cn.com.gome.dujia.service.OrderPayService;
import cn.com.gome.dujia.service.OrderRefundService;
import cn.com.gome.dujia.service.RbService;
import cn.com.gome.dujia.task.common.AbstractTask;
import cn.com.gome.dujia.vo.refund.OrderRefundInfo;
import cn.com.gome.dujia.ws.client.rb.AcceptRefundTask;
import com.gome.plan.tools.utils.MonitorUtil;
/**
 * 
 * Description : 推送rb(退款)
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年5月4日 上午9:09:55 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
@Component
public class RefundSendRbTask extends AbstractTask {

	private static final Logger log = LoggerFactory.getLogger(RefundSendRbTask.class);

	@Autowired
	private OrderRefundService orderRefundService;

	@Autowired
	private OrderPayService orderPayService;

	@Autowired
	private RbService rbService;

	@Override
	public String getTaskName() {
		return "RB退款";
	}

	@Override
	public void doExecute() {

		log.info("----------------推送RB退款任务...开始----------------");

		List<OrderRefund> refundList = null;
		OrderRefundInfo refundInfo = new OrderRefundInfo();

		try {
			refundInfo.setRefundStatus(RefundStatus.AUDIT_SUCCESS.ordinal()); // 财务审核通过
			// 推送rb退款数据
			refundList = orderRefundService.queryOrderRefundList(refundInfo);

			if (!CollectionUtils.isEmpty(refundList)) {
				
				AcceptRefundTask acceptRefund = rbService.buildAcceptRefundTaskProxy(GlobalDisconf.getRbAddress());
				
				if(acceptRefund == null){
                    throw new BizException(ErrorCode.E30002);
                }
				
				for (OrderRefund refund : refundList) {
					try {
						 RbRefund    rb = getRbRefund(refund);
                         rb.setAcceptRefund(acceptRefund);
						 ResponseDto res= rbService.sendRb(rb);
						 String resCode = res.getCode();
						 String resMess = res.getMessage();
						 
						 log.info("+++++++RefundSendRbTask refundId:{} rescode:{}, rspMessage:{}", refund.getRefundId(), resCode, resMess);
						 
						 if("200".equals(resCode)){
							 refund.setRefundStatus(RefundStatus.HAS_SEND_RB.ordinal());
                             refund.setUpdateTime(new Date());
                             OrderStatusLog refundLog = new OrderStatusLog(refund.getRefundId(), false, refund.getRefundStatus(), OrderType.REFUND.getValue(), RefundStatus.HAS_SEND_RB.getName(), new Date(), new Date());
                             // 更新退款记录、新增退款状态日志记录
                             orderRefundService.updateAndinsert(refund,refundLog);
                             continue;
						 }else{
							 MonitorUtil.recordOne("ZBY_Send_Rb_Faild");
						 }
					} catch (Exception e) {
						log.error("+++++++++++++++++++RefundSendRbTask_refundId:"+ refund.getRefundId()+ "_Exception+++++++++++++++++++"+ e.getMessage(), e);
						MonitorUtil.recordOne("Send_Rb_Faild_Exception");
					}
				}
			}
		} catch (Exception e) {
			log.error("+++++++++++++++++++RefundSendRbTask_doExecute_Exception:"+ e.getMessage(), e);
			MonitorUtil.recordOne("Send_Rb_Faild_Exception");
		}

		log.info("----------------推送RB退款任务...结束----------------");
	}
	
	
	
	private RbRefund getRbRefund(OrderRefund refund){
		RbRefund rb = new RbRefund();
		
		rb.setOrderId(refund.getOrderId());	  // 订单号
		rb.setRefundId(refund.getRefundId()); // 退款单号 
		rb.setRefundAmount(refund.getReturnAmount().doubleValue()); //退款金额
		rb.setPayModeSap(refund.getPayModeSap()); // sap支付方式
		rb.setBuid(9600); 						  // rb提供(9600)
		rb.setPayTime(refund.getPayTime()); 	  // 支付时间
		rb.setTransNo(refund.getTransNo());       // 供应商交易流水号
		rb.setPayAmount(refund.getPayAmount().doubleValue()); //支付金额

        /**
         * 以为为空
         */
		rb.setCardNumber("");
        rb.setOrgSysNo("");
        rb.setRefundDetailsId("9600");	//退款明细同buid
		
		OrderPay orderPay = new OrderPay();
		orderPay.setOrderId(refund.getOrderId());
		orderPay.setMerchantNo(refund.getTransNo());
		OrderPay pay = orderPayService.getOrderPay(orderPay);//查询退款单对应支付信息
		if(pay != null){
			rb.setPayOrderNo(pay.getTransNo());
		}else{
			rb.setPayOrderNo("");
		}
		
		return rb;
	}

}

package cn.com.gome.dujia.task.sap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.enums.SapStatus;
import cn.com.gome.dujia.enums.SapType;
import cn.com.gome.dujia.exception.BizException;
import cn.com.gome.dujia.model.OrderRefund;
import cn.com.gome.dujia.service.OrderRefundService;
import cn.com.gome.dujia.service.PushSapService;
import cn.com.gome.dujia.task.common.AbstractTask;
import com.gome.plan.tools.utils.MonitorUtil;
/**
 * Description : 推送逆向SAP收款任务
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年5月3日 下午4:07:37 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
@Component
public class PushNegativeSapPayTask extends AbstractTask {
	
	private static final Logger logger = LoggerFactory.getLogger(PushNegativeSapPayTask.class);
	
	@Autowired
	private OrderRefundService orderRefundService;
	
	@Autowired
	private PushSapService pushSapService;

	@Override
	public String getTaskName() {
		return "推送逆向SAP收款";
	}

	@Override
	public void doExecute() {
		
		logger.info("----------------推送逆向SAP收款任务...开始----------------");
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sapType", SapType.NEGATIVE_SAP_PAY.getValue()); //逆向SAP收款
			map.put("sapStatusList", Arrays.asList(SapStatus.WAIT_PUSH_SAP.getValue(), SapStatus.PUSH_SAP_FAIL.getValue())); //待推送和推送失败
			map.put("pushNum", GlobalDisconf.getPushSapMaxNumber()); 										 //最大推送次数
			List<OrderRefund> refundList = orderRefundService.queryOrderRefundByPushSap(map);
			
			//推送逆向SAP收款
			if(!CollectionUtils.isEmpty(refundList)){
				pushSapService.pushNegativeSapPay(refundList);
				logger.info("推送逆向SAP收款成功");
			}
		}catch(BizException biz) {
			logger.error("推送逆向SAP收款失败_业务异常:", biz);
			MonitorUtil.recordOne("Push_Negative_Sap_Pay_BizException");
		}catch(Exception e){
			logger.error("推送逆向SAP收款失败_异常:", e);
			MonitorUtil.recordOne("Push_Negative_Sap_Pay_Exception");
		}
		logger.info("----------------推送逆向SAP收款任务...结束----------------");
	}

}

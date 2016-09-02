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
import com.gome.plan.tools.utils.MonitorUtil;
import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.enums.SapStatus;
import cn.com.gome.dujia.enums.SapType;
import cn.com.gome.dujia.exception.BizException;
import cn.com.gome.dujia.model.OrderPay;
import cn.com.gome.dujia.service.OrderPayService;
import cn.com.gome.dujia.service.PushSapService;
import cn.com.gome.dujia.task.common.AbstractTask;
/**
 * Description : 推送正向SAP收入任务
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年5月3日 下午4:05:33 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
@Component
public class PushPositiveSapIncomeTask extends AbstractTask {
	
	private static final Logger logger = LoggerFactory.getLogger(PushPositiveSapIncomeTask.class);
	
	@Autowired
	private OrderPayService orderPayService;
	
	@Autowired
	private PushSapService pushSapService;
	
	@Override
	public String getTaskName() {
		return "推送正向SAP收入";
	}

	@Override
	public void doExecute() throws Exception {

		logger.info("----------------推送正向SAP收入任务...开始----------------");
		try {
			// 查询推送正向SAP收入列表，sap类型（正向SAP收入），推送状态（待推送和推送失败），推送次数小于3
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sapType", SapType.POSITIVE_SAP_INCOME.getValue()); //正向SAP收入
			map.put("sapStatusList", Arrays.asList(SapStatus.WAIT_PUSH_SAP.getValue(), SapStatus.PUSH_SAP_FAIL.getValue())); //待推送和推送失败
			map.put("pushNum", GlobalDisconf.getPushSapMaxNumber()); //最大推送次数
			List<OrderPay> orderPays = orderPayService.queryOrderPayByPushSap(map);
			
			if(!CollectionUtils.isEmpty(orderPays)) {
				//推送正向SAP收入
				pushSapService.pushPositiveSapIncome(orderPays);
				logger.info("推送正向SAP收入成功");
			}
		}catch(BizException biz) {
			logger.error("推送正向SAP收入失败_业务异常:"+biz.getMessage(),biz);
			MonitorUtil.recordOne("Push_Positive_Sap_Income_BizException");
		}catch (Exception e) {
			logger.error("推送正向SAP收入失败",e);
			MonitorUtil.recordOne("Push_Positive_Sap_Income_Exception");
		}
		logger.info("----------------推送正向SAP收入任务...结束----------------");
	}

}

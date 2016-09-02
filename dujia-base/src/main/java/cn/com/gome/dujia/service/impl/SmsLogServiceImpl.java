package cn.com.gome.dujia.service.impl;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.enums.OrderType;
import cn.com.gome.dujia.enums.SmsType;
import cn.com.gome.dujia.exception.BizException;
import cn.com.gome.dujia.log.APILog;
import cn.com.gome.dujia.log.LogType;
import cn.com.gome.dujia.mapper.business.SmsLogMapper;
import cn.com.gome.dujia.model.SmsLog;
import cn.com.gome.dujia.service.RedisCacheService;
import cn.com.gome.dujia.service.SmsLogService;

import com.gome.commerce.Interface.smsmail.DubboSMSMessage;
import com.gome.commerce.Interface.smsmail.SmsMessage;
import com.gome.plan.tools.utils.StringUtils;

/**
 * wangweiran
 *
 * 短信服务
 */
@Service
public class SmsLogServiceImpl implements SmsLogService {
	
	private static final Logger logger = LoggerFactory.getLogger(SmsLogServiceImpl.class);
	
	@Autowired
	private DubboSMSMessage dubboSMSMessage;
	
	@Autowired
	private RedisCacheService cacheService;
	
	@Autowired
	private SmsLogMapper smsLogMapper;
	

	public void insert(SmsLog smsLog) {
		smsLogMapper.insert(smsLog);
	}
	
	/**
	 * 发送短信
	 * @param diffId    短信模板id
	 * @param phone     手机号
	 * @param paramMap  短信内容
	 * @return
	 * 1、支付提醒:生成订单后,30分钟未支付,在第30分钟发送短信给用户提醒支付 
	 * 		短信模板号：sms_diffid_pay_remind=7012
	 *		短信内容：【国美在线】您预定的周边游酒景套餐订单#orderId#还未支付，请您#date#前完成支付...
	 * 2、超时取消订单:超时未支付,订单已取消
	 * 		短信模板号：sms_diffid_out_time_cancel=7013
	 * 		短信内容：【国美在线】您好，订单#orderId#已过支付等待时间，订单已取消...
	 * 3、供应商退款:生成退款单(供应商发起退款)
	 * 		短信模板号：sms_diffid_vender_pay_refund=7014
	 * 		短信内容：【国美在线】您好，感谢您选择国美旅行，您的周边游酒景套餐订单#orderId#，退款金额#refundAmount#，退款将于15个工作日....
	 * 4、报警短信,供应商超过3小时未确认
	 * 		短信模板号：sms_diffid_alarm=7015
	 * 		短信内容：【国美报警短信】周边游订单号#orderId#（#venderOrderId#），支付时间#paytime#， 超过3小时未收到供应商确认通知。
	 */
	public void sendSms(String diffId, String phone, Map<String, Object> paramMap) {
		boolean status = false;
		try {
			if(StringUtils.isEmpty(diffId) || StringUtils.isEmpty(phone) || CollectionUtils.isEmpty(paramMap)) {
				logger.error("发送短信参数不能为空");
				throw new BizException("发送短信参数不能为空");
			}
			logger.info("发送短信参数：短信模板{}, 手机号{}, 模板内容{}", diffId, phone, paramMap);
			
			//发送短信对象
			SmsMessage smsMessage  = new SmsMessage();
			smsMessage.setDiffId(diffId); //短信模板id
			smsMessage.setMobileId(phone); //手机号
			smsMessage.setIntervalTime(0); //即时发送
			smsMessage.setObject(paramMap); //短信内容
			
			//发送短信
			String result = dubboSMSMessage.sendMessageByJob(smsMessage);
			logger.info("发送短信结果{}", result);
			
			//记录发送短信日志
			APILog.build("发送短信", LogType.SMS)
					.appendRequest("request", smsMessage)
					.appendResponse("response", result)
					.record();
			
			//S:保存成功， E:数据校验不通过
			if(GlobalDisconf.smsResult.equals(result)){
				logger.info("发送短信成功：短信模板{}, 手机号{}", diffId, phone);
				status = true;
			}else{
				logger.info("发送短信数据校验不通过：短信模板{}, 手机号{}", diffId, phone);
			}
		} catch (Exception e) {
			logger.error("发送短信失败：短信模板{}, 手机号{}, 模板内容{}", diffId, phone, paramMap, e);
		} finally {
			//保存发送短信
			SmsLog smsLog = new SmsLog();
			smsLog.setPhoneNumber(phone); //手机号
			smsLog.setSystemType(1); //系统类型
			smsLog.setSmsType(SmsType.getSmsType(diffId).getValue()); //短信类型
			smsLog.setContent(SmsType.getSmsType(diffId).buildSmsContent(paramMap)); //短信内容
			smsLog.setOrderId((String) paramMap.get("orderId")); //订单id
			smsLog.setOrderType(OrderType.ORDER.getValue()); //订单类型 订单
			smsLog.setUserId("admin"); //用户id
			smsLog.setOperator("系统发送"); //操作者
			smsLog.setCreateTime(new Date()); //创建时间
			smsLog.setUpdateTime(new Date()); //修改时间
			smsLog.setStatus(status); //发送短信状态
			
			//保存短信日志
			insert(smsLog);
			logger.info("短信日志保存成功");
		}
	}

}

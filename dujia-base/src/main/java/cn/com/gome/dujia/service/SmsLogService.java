package cn.com.gome.dujia.service;

import java.util.Map;

import cn.com.gome.dujia.model.SmsLog;

/**
 * @author wangweiran
 *
 * 短信
 */
public interface SmsLogService {
	/**
	 * 保存短信日志
	 * @param smsLog
	 * @return
	 */
	void insert(SmsLog smsLog);
	
	/**
	 * 发送短信
	 * @param diffId    短信模板id
	 * @param phone     手机号
	 * @param paramMap  短信内容
	 * @return
	 */
	void sendSms(String diffId, String phone, Map<String, Object> paramMap);
	
}

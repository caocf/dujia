package cn.com.gome.dujia.enums;

import java.util.Map;
import java.util.Set;

import cn.com.gome.dujia.disconf.GlobalDisconf;

public enum SmsType {

	PAY_REMIND("支付提醒", 1, GlobalDisconf.smsDiffidPayRemind, GlobalDisconf.smsDiffidPayRemindTemp) {
		public String buildSmsContent(Map<String, Object> paramMap) {
			return buildSms(this,paramMap);
		}
	},

	OUT_TIME_CANCEL("超时取消", 2, GlobalDisconf.smsDiffidOutTimeCancel, GlobalDisconf.smsDiffidOutTimeCancelTemp) {
		public String buildSmsContent(Map<String, Object> paramMap) {
			return buildSms(this,paramMap);
		}
	},

	VENDER_PAY_REFUND("供应商退款短信", 3, GlobalDisconf.smsDiffidVenderPayRefund, GlobalDisconf.smsDiffidVenderPayRefundTemp) {
		public String buildSmsContent(Map<String, Object> paramMap) {
			return buildSms(this,paramMap);
		}
	},

	ALARM("报警短信", 4, GlobalDisconf.smsDiffidAlarm, GlobalDisconf.smsDiffidAlarmTemp) {
		public String buildSmsContent(Map<String, Object> paramMap) {
			return buildSms(this,paramMap);
		}
	};

	private String name;
	private Integer value;
	private String diffId;
	private String template;

	private SmsType(String name, Integer value, String diffId, String template) {
        this.name = name;
        this.value = value;
        this.diffId = diffId;
        this.template = template;
    }

    public String getName() {
        return this.name;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getDiffId() {
        return this.diffId;
    }

    public String getTemplate() {
        return this.template;
    }
    
    /**
     * 根据短信类型获取枚举对象
     * @param value
     * @return
     */
    public static SmsType getSmsType(String diffId) {
    	if(null != diffId) {
			for (SmsType smsType : SmsType.values()) {
				if (smsType.getDiffId().equals(diffId)) {
					return smsType;
				}
			}
		}
		return null;
    }

	/**
	 * 构建短信模板内容
	 *
	 * @param paramMap
	 * @return
	 */
	public abstract String buildSmsContent(Map<String, Object> paramMap);

	/**
	 * 短信模板内容
	 *
	 * @param smsType
	 * @param paramMap
	 * @return
	 */
	private static String buildSms(SmsType smsType, Map<String, Object> paramMap) {
		if (null == smsType || null == paramMap || paramMap.size() == 0) {
			return null;
		}
		// 短信模板内容
		String smsContent = smsType.template;
		// 内容map
		Set<String> keys = paramMap.keySet();
		for (String key : keys) {
            if(null != paramMap.get(key)){
                smsContent = smsContent.replace("#" + key + "#", paramMap.get(key).toString());
            }else{
                smsContent = smsContent.replace("#" + key + "#", "");
            }
		}
		return smsContent;
	}
}

package cn.com.gome.dujia.disconf;

import java.util.Map;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import com.fasterxml.jackson.core.type.TypeReference;
import com.gome.plan.tools.utils.JsonUtils;
import com.gome.plan.tools.utils.StringUtils;

/**
 * json-map 配置信息
 * 
 * @author xiongyan
 * @date 2016年4月14日 上午10:02:35
 */
@DisconfFile(filename = "jsonmap.properties")
public final class JsonMapDisconf {
	
	private static JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON);

	/**
	 * 同程接口日志
	 */
	private static String tcInterfaceLog;
	
	/**
	 * 支付方式
	 */
	private static String payMode;
	

	@DisconfFileItem(name = "tc_interface_log")
	public static String getTcInterfaceLog() {
		return tcInterfaceLog;
	}

	public static void setTcInterfaceLog(String tcInterfaceLog) {
		JsonMapDisconf.tcInterfaceLog = tcInterfaceLog;
	}
	
	@DisconfFileItem(name = "pay_mode")
	public static String getPayMode() {
		return payMode;
	}

	public static void setPayMode(String payMode) {
		JsonMapDisconf.payMode = payMode;
	}

	
	/**
	 * 获取同程接口日志map
	 * @return
	 */
	public static Map<String, Boolean> getTcInterfaceLogMap() {
		if (StringUtils.isNotEmpty(tcInterfaceLog)) {
			return jsonUtils.deserialize(tcInterfaceLog, new TypeReference<Map<String, Boolean>>() {});
		}
		return null;
	}
	
	/**
	 * 获取支付方式map
	 * @return
	 */
	public static Map<String, String> getPayModeMap() {
		if (StringUtils.isNotEmpty(payMode)) {
			return jsonUtils.deserialize(payMode, new TypeReference<Map<String, String>>() {});
		}
		return null;
	}
	
}

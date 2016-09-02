package cn.com.gome.dujia.enums;

import cn.com.gome.dujia.disconf.GlobalDisconf;

/**
 * 订单来源
 * 
 * @author xiongyan
 * @date 2016年5月7日 下午3:00:39
 */
public enum OrderSource {

	WEB("WEB", 1, GlobalDisconf.orderSourceWeb),
	
	APP("APP", 2, GlobalDisconf.orderSourceApp),
	
	WAP("WAP", 3, GlobalDisconf.orderSourceWap);
	
	private String name;
    private Integer value;
    private String key;
	
    private OrderSource(String name, Integer value, String key) {
    	this.name = name;
		this.value = value;
		this.key = key;
	}
    
    public String getName() {
    	return this.name;
    }
	
	public Integer getValue() {
		return this.value;
	}
	
	public String getKey() {
		return this.key;
	}
	
	/**
	 * 根据value 获取key
	 * @param value
	 * @return
	 */
	public static String getKeyByValue(Integer value) {
		if(null != value) {
			for (OrderSource orderSource : OrderSource.values()) {
				if (orderSource.getValue().equals(value)) {
					return orderSource.key;
				}
			}
		}
		return null;
	}
	
	/**
	 * 根据value 获取name
	 * @param value
	 * @return
	 */
	public static String getName(Integer value){
		if(null != value) {
			for (OrderSource orderSource : OrderSource.values()) {
				if (orderSource.getValue().equals(value)) {
					return orderSource.name;
				}
			}
		}
		return "";
	}

}

package cn.com.gome.dujia.enums;
/**
 * 频道来源
 * @author lvzhongyang
 *
 */
public enum ChannelType {

	ZBY("周边游",1);
	
	
	private String name;
    private Integer value;
    
    private ChannelType(String name, Integer value) {
		this.name = name;
		this.value = value;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getValue() {
		return value;
	}


	public void setValue(Integer value) {
		this.value = value;
	}


	/**
	 * 根据value 获取name
	 * @param value
	 * @return
	 */
	public static String getName(Integer value){
		if(null != value) {
			for (ChannelType channelType : ChannelType.values()) {
				if (channelType.getValue().equals(value)) {
					return channelType.name;
				}
			}
		}
		return "";
	}

}

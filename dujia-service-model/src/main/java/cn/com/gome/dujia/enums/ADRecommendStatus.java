package cn.com.gome.dujia.enums;

/**
 * Description : 广告推荐位状态
 * 
 * @author liuhexin
 * @version 1.0
 */
public enum ADRecommendStatus {

	/**
	 * 0-待排期
	 */
	WAIT(0, "待排期"),

	/**
	 * 1-展示中
	 */
	START(1, "展示中"),

	/**
	 * 3-已暂停
	 */
	STOP(3, "已暂停"),

	/**
	 * 4-已过期
	 */
	EXPIRE(4, "已过期");

	private ADRecommendStatus(Integer value, String name) {
		this.value = value;
		this.name = name;
	}

	private Integer value;
	private String name;

	public String getName() {
		return name;
	}

	public Integer getValue() {
		return value;
	}

	/**
	 * 根据value，获取名称
	 * 
	 * @param value
	 * @return
	 */
	public static String getName(Integer value) {
		if (null != value && !"".equals(value)) {
			for (ADRecommendStatus status : ADRecommendStatus.values()) {
				if (status.getValue().equals(value)) {
					return status.name;
				}
			}
		}
		return null;
	}
}

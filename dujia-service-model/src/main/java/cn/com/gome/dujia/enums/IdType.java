package cn.com.gome.dujia.enums;
/**
 * @author wangweiran
 * 
 * 证件类型
 */
public enum IdType {
	WTX("未填写", -1),
	SFZ("身份证", 1),
	HZ("护照", 2),
	Jun_GZ("军官证", 3),
	SBZ("士兵证", 4),
	GATTXZ("港澳台通行证", 5),
	LSSFZ("临时身份证", 6),
	HKB("户口本", 7),
	QT("其他", 8),
	JING_GZ("警官证", 9),
	WGRJLZ("外国人居留证", 12),
	HXZ("回乡证", 15),
	QYYYZZ("企业营业执照", 16),
	FRDMZ("法人代码证", 17),
	TBZ("台胞证", 18);
	
	private String name;
    private Integer value;
	
    private IdType(String name, Integer value) {
    	this.name = name;
		this.value = value;
	}
    
    public String getName() {
    	return this.name;
    }
	
	public Integer getValue() {
		return this.value;
	}
	
	/**
	 * 根据value获取name
	 * @param value
	 * @return
	 */
	public static String getNameByValue(String value) {
		if(null != value && !"".equals(value)) {
			for (IdType cashierStatus : IdType.values()) {
				if (cashierStatus.getValue().equals(value)) {
					return cashierStatus.name;
				}
			}
		}
		return null;
	}
}

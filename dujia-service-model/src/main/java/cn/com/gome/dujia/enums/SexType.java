package cn.com.gome.dujia.enums;
/**
 * @author wangweiran
 *
 * 同程出游人性别
 */
public enum SexType {
	WOMAN("女人", 0),
	MAN("男人", 1);
	
	private String name;
    private Integer value;
	
    private SexType(String name, Integer value) {
    	this.name = name;
		this.value = value;
	}
    
    public String getName() {
    	return this.name;
    }
	
	public Integer getValue() {
		return this.value;
	}
}

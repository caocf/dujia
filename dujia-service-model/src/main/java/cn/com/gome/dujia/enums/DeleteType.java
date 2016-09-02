package cn.com.gome.dujia.enums;
/**
 * @author wangweiran
 *
 * 同程出游人性别
 */
public enum DeleteType {
	DELETE("不删除", 0),
	NOT_DELTE("删除", 1);

	private String name;
    private Integer value;

    DeleteType(String name, Integer value) {
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

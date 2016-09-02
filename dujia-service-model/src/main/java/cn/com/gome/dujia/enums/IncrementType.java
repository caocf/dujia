package cn.com.gome.dujia.enums;
/**
 * @author wangweiran
 *
 * 订单增量类型
 */
public enum IncrementType {
	ORDER_CREATE("下单", 0),
	ORDER_FLAG_CHANGE("订单状态变化", 1);
	
	private String name;
    private Integer value;
	
    private IncrementType(String name, Integer value) {
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

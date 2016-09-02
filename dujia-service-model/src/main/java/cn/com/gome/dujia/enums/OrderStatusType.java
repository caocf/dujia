package cn.com.gome.dujia.enums;

/**
 * @author wangweiran
 * 
 * 同程订单状态
 */
public enum OrderStatusType {
	DEFAULT("默认状态", -1),
	WAIT_INVENTORY_CONFIRM("待确认库存", 0),
	WAIT_FOR_PAY("待支付", 1),
	CANCELED("已取消", 2),
	ALREADY_PAY("已支付", 3),
	NEED_TC_CONFIRM("待同程确认", 5),
	TC_ALREADY_CONFIRM("同程已确认", 10),
	PARTIAL_REFUND("部分退款(有人出游)", 15),
	ALL_REFUND_APPLY("申请全额退款", 20),
	ALL_REFUND_FINISH("全额退款结束", 25),
	PARTIAL_REFUND_FINISH("部分退款结束", 30),
	FINISHED("已结算", 35);
	
	private String name;
    private Integer value;
	
    private OrderStatusType(String name, Integer value) {
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

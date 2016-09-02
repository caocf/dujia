package cn.com.gome.dujia.enums;
/**
 * @author wangweiran
 *
 * 同程订单增量变化原因
 */
public enum IncrementReason {
	ORDER_CANCEL("订单取消", 0),
	INVENTORY_CONFIRM("库存确认", 1),
	ORDER_PAY("订单支付", 2),
	ORDER_CONFIRM("订单库存确认", 3),
	ORDER_REFUND("订单申请退款", 4),
	ORDER_REFUND_FINISH("订单确认退款", 5),
	ORDER_CREATE("订单创建", 6);
	
	private String name;
    private Integer value;
	
    private IncrementReason(String name, Integer value) {
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

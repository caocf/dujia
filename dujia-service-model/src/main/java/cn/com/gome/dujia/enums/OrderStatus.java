package cn.com.gome.dujia.enums;

/**
 * 订单状态
 * 
 * @author 刘核心
 * @date 2016年4月26日
 */
public enum OrderStatus {

	WAIT_PAYMENT("待付款", 1),

    PAYMENT_SUCCESS("已付款", 2),

    WAIT_TC_CONFIRM("待同程确认", 3),

    HAS_TC_CONFIRMED("同程已确认", 4),

    TC_BOOK_FAILD("同程预订失败",5),

    ORDER_CANCEL("已取消", 6),

    ORDER_SUCCESS("已完成", 7),

    WAIT_REFUND("退款中", 8),

    REFUND_SUCCESS("已退款", 9),

    TIMEOUT_PAY("超时支付", 10);

	private String name;
    private Integer value;
	
    private OrderStatus(String name, Integer value) {
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
	 * 根据value，获取名称
	 * 
	 * @param value
	 * @return
	 */
	public static String getName(Integer value) {
		if(null != value && !"".equals(value)) {
			for (OrderStatus orderStatus : OrderStatus.values()) {
				if (orderStatus.getValue().equals(value)) {
					return orderStatus.name;
				}
			}
		}
		return null;
	}
	
}

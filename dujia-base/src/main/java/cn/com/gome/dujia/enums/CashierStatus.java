package cn.com.gome.dujia.enums;

/**
 * 
 * Description : 收银状态
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年5月4日 上午10:16:28 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public enum CashierStatus {

	WAIT_PAY("待支付", "010"),
	
	PAY_FAIL("支付失败", "020"),
	
	PAY_EXPIRED("订单过期", "030"),
	
	PART_PAY("部分支付", "040"),
	
	PAY_SUCCESS("支付成功", "050"),
	
	EXPIRED_PAY_SUCCESS("过期支付成功", "060");
	
	private String name;
    private String value;
	
    private CashierStatus(String name, String value) {
    	this.name = name;
		this.value = value;
	}
    
    public String getName() {
    	return this.name;
    }
	
	public String getValue() {
		return this.value;
	}
	
	/**
	 * 根据value获取name
	 * @param value
	 * @return
	 */
	public static String getNameByValue(String value) {
		if(null != value && !"".equals(value)) {
			for (CashierStatus cashierStatus : CashierStatus.values()) {
				if (cashierStatus.getValue().equals(value)) {
					return cashierStatus.name;
				}
			}
		}
		return null;
	}
	
}

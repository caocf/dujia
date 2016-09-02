package cn.com.gome.dujia.enums;

/**
 * 
 * Description : 订单类型
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月27日 上午10:35:58 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public enum OrderType {

	ORDER("订单", 1), 
	
	REFUND("退款单", 2);
	
	private String name;
    private Integer value;
	
    private OrderType(String name, Integer value) {
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

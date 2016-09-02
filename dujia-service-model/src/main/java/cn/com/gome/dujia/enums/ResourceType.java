package cn.com.gome.dujia.enums;

/**
 * Description : 资源类型
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月5日 下午2:25:20 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public enum ResourceType {
	
	HOTEL("酒店", 0), 
	
	SCENIC("景区", 1), 
	
	CATER("餐饮", 2), 
	
	TICKET("门票", 3);

	private String name;
    private Integer value;
	
    private ResourceType(String name, Integer value) {
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

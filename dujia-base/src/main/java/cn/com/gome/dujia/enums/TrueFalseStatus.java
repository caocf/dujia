package cn.com.gome.dujia.enums;

/**
 * 
 * Description : 是否状态
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月27日 上午10:36:41 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public enum TrueFalseStatus {

	TRUE("是", true),
	
	FALSE("否", false);
	
	private String name;
    private Boolean value;
	
    private TrueFalseStatus(String name, Boolean value) {
    	this.name = name;
		this.value = value;
	}
    
    public String getName() {
    	return this.name;
    }
	
	public Boolean getValue() {
		return this.value;
	}
}

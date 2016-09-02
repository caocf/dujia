package cn.com.gome.dujia.enums;

/**
 * 
 * Description : sap类型
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月25日 上午11:42:14 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public enum SapType {

	POSITIVE_SAP_PAY("SAP正向收款", 1),
	
	POSITIVE_SAP_INCOME("SAP正向收入", 2),
	
	NEGATIVE_SAP_PAY("SAP逆向收款", 3), 
	
	NEGATIVE_SAP_INCOME("SAP逆向收入", 4); 
	
	private String name;
	
    private Integer value;
	
    private SapType(String name, Integer value) {
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

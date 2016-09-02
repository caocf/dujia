package cn.com.gome.dujia.enums;

/**
 * 
 * Description : sap状态
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月25日 上午11:44:24 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public enum SapStatus {

	WAIT_PUSH_SAP("待推送", 1),
	
	HAS_PUSH_SAP("已推送", 2),
	
	PUSH_SAP_SUCCESS("推送成功", 3), 
	
	PUSH_SAP_FAIL("推送失败", 4),
	
	NOT_PUSH_SAP("不推送", 5); 
	
	private String name;
    private Integer value;
	
    private SapStatus(String name, Integer value) {
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

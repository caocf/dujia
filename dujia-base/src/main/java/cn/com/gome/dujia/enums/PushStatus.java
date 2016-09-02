package cn.com.gome.dujia.enums;

/**
 * 
 * Description : 推送状态
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月25日 上午11:48:14 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public enum PushStatus {

	WAIT_PUSH("待推送", 0),
	
	PUSH_SUCCESS("推送成功", 1),
	
	PUSH_FAILD("推送失败", 2),
	
	CANCEL_PUSH("取消推送", 3);
	
	private String name;
	
	private Integer value;
	
	private PushStatus(String name,Integer value){
		this.name = name;
		this.value= value;
	}

	public String getName() {
		return name;
	}

	public Integer getValue() {
		return value;
	}
	
}

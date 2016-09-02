package cn.com.gome.dujia.enums;

/**
 * 
 * Description : 支付状态
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月25日 上午11:49:15 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public enum PayStatus {

	WAIT_PAY("未支付", 0),

	PAY_SUCCESS("已支付", 1);

	private String name;
	private Integer value;

	private PayStatus(String name, Integer value) {
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
	 * 根据value 获取name
	 * 
	 * @param value
	 * @return
	 */
	public static String getNameByValue(Integer value) {
		if (null != value) {
			for (PayStatus ps : PayStatus.values()) {
				if (ps.getValue().equals(value)) {
					return ps.getName();
				}
			}
		}
		return null;
	}
}

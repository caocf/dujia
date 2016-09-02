package cn.com.gome.dujia.enums;

/**
 * 
 * Description : 退款类型
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月25日 上午11:47:20 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public enum RefundPayType {
	
	/**
	 * 收入
	 */
	REFUND_INCOME("收入"),
	
	/**
	 * 	收款
	 */
	REFUND_PAY("收款"),
	
	/**
	 * 	异常
	 */
	REFUND_EXCEPTION("异常");
	
	private String name;
	
	private RefundPayType(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	/**
     * 通过枚举标识，获得枚举对象
     *
     * @param index
     * @return
     * @author WenJie Mai
     */
    public static RefundPayType getEnumByIndex(Integer index) {

        switch (index) {
            case 0:
                return REFUND_INCOME;
            case 1:
                return REFUND_PAY;
            case 2:
                return REFUND_EXCEPTION;
        }

        return null;
    }
	
}

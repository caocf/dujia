package cn.com.gome.dujia.enums;

/**
 * 
 * Description : 退款方式
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月25日 上午11:47:07 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public enum RefundMode {

	/**
	 * 原路返回
	 */
	RETURN_BY_THE_WAY("原路返回");
	
	private String name;
	
	private RefundMode(String name){
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
    public static RefundMode getEnumByIndex(Integer index) {

        switch (index) {
            case 0:
                return RETURN_BY_THE_WAY;
        }

        return null;
    }
}

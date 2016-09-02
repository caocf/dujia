package cn.com.gome.dujia.enums;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * Description : 支付方式
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年5月3日 下午1:55:47 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public enum PayMode {

    NET_BANK_PAY("网银支付","010"),
    
    QUICK_PAY("快捷支付","020"),
    
    SCAN_CODE_PAY("扫码支付","040"),
    
    PLATFORM("支付平台","050"),
    
    INSTALLMENT_PAY("分期支付","030"),
    
    ENTERPRISE_PAY("企业银行","060"),
    
    MEI_YING_BAO("美盈宝","070");
    
    
    private String name;
    
    private String value;
    
    private PayMode(String name,String value) {
        this.name = name;
        this.value= value;
    }

    public String getName() {
        return name;
    }

	public String getValue() {
		return value;
	}
	
	public static Map<String,String> getEnumMap(){
		
		Map<String,String> enumMap = new HashMap<String,String>();
		
		PayMode type[] = PayMode.values();
		
		for(int i=0;i<type.length;i++){
			enumMap.put(type[i].value,type[i].name);
		}
		
		return enumMap;
	}
	
	/**
     * 通过枚举标识，获得枚举对象
     *
     * @param name
     * @return
     * @author WenJie Mai
     */
    public static PayMode getEnumByIndex(String name) {

        switch (name) {
            case "010":
                return NET_BANK_PAY;
            case "020":
                return QUICK_PAY;
            case "040":
                return SCAN_CODE_PAY;
            case "050":
                return PLATFORM;
            case "030":
                return INSTALLMENT_PAY;
            case "060":
                return ENTERPRISE_PAY;
            case "070":
                return MEI_YING_BAO;
        }

        return null;
    }

}

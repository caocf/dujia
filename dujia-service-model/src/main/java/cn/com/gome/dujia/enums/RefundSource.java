package cn.com.gome.dujia.enums;

/**
 * 
 * Description : 退款来源
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月25日 上午11:46:16 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public enum RefundSource {

    TC("同程"),

    GOME("国美");

    private String name;

    private RefundSource(String name) {
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
    public static RefundSource getEnumByIndex(Integer index) {

        switch (index) {
            case 0:
                return TC;
            case 1:
                return GOME;
        }

        return null;
    }
}

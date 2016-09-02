package cn.com.gome.dujia.enums;

/**
 * Description  : 退款形式
 * Copyright    : Copyright (c) 2008- 2016 All rights reserved;
 * Created Time : 2016/5/18 15:22;
 *
 * @author WenJie Mai
 * @version 1.0
 */
public enum RefundForm {

    /**
     * 全额退款
     */
    FULL_REFUND("全额退款"),

    /**
     * 部分退款
     */
    PART_REFUND("部分退款");


    private String name;

    private RefundForm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

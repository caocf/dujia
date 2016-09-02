package cn.com.gome.dujia.enums;

/**
 * Description : 退款原因(对应refund_type字段)
 * Copyright : Copyright (c) 2008- 2015 All rights reserved. <br/>
 * Created Time : 2015年9月21日 下午4:18:02 <br/>
 *
 * @author WenJie Mai
 * @version 1.0
 */
public enum RefundReason {

    TC_REFUND("同程退款", "同程退款"),

    TIMEOUT_PAY_REFUND("超时支付退款", "超时支付"),

    REPEAT_PAY_REFUND("重复支付退款", "重复支付"),

    PAY_PUSH_ERROR_REFUND("支付后推单失败", "支付后推单失败退款");

    private RefundReason(String name, String value) {
        this.name = name;
        this.value = value;
    }

    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    /**
     * 根据退款类型获取value
     *
     * @param value
     * @return
     */
    public static String getRefundReason(int value) {
        for (RefundReason refundReason : RefundReason.values()) {
            if (refundReason.ordinal() == value) {
                return refundReason.getValue();
            }
        }
        return null;
    }

    /**
     * 通过枚举标识，获得枚举对象
     *
     * @param index
     * @return
     * @author WenJie Mai
     */
    public static RefundReason getEnumByIndex(Integer index) {

        switch (index) {
            case 0:
                return TC_REFUND;
            case 1:
                return TIMEOUT_PAY_REFUND;
            case 2:
                return REPEAT_PAY_REFUND;
            case 3:
                return PAY_PUSH_ERROR_REFUND;
        }

        return null;
    }
}

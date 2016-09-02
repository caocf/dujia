package cn.com.gome.dujia.enums;

/**
 * Description : 退款状态
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月25日 上午10:34:49 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public enum RefundStatus {

	/**
     * 新建
     */
    INIT("等待财务审核"),

    /**
     * 财务审核通过
     */
    AUDIT_SUCCESS("财务审核通过"),

    /**
     * 财务审核不通过
     */
    AUDIT_FAILD("财务审核未通过"),

    /**
     * 已推RB
     */
    HAS_SEND_RB("退款处理中"),
    
    
    /**
     * RB审核通过
     */
    REFUND_COMPLETE("退款成功"),

    /**
     * RB审核不通过
     */
    REFUND_FAILD("退款失败");
    

    RefundStatus(String name) {
        this.name = name;
    }

    private String name;

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
    public static RefundStatus getEnumByIndex(Integer index) {

        switch (index) {
            case 0:
                return INIT;
            case 1:
                return AUDIT_SUCCESS;
            case 2:
                return AUDIT_FAILD;
            case 3:
                return HAS_SEND_RB;
            case 4:
                return REFUND_COMPLETE;
            case 5:
                return REFUND_FAILD;
        }

        return null;
    }
}

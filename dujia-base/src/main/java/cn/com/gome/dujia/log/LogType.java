package cn.com.gome.dujia.log;

/**
 * 
 * Description : 日志类型
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月27日 下午6:22:08 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public enum LogType {
    
    /**
     * 正向收款
     */
    SAP_POSITIVE_PAY(0),
    
    /**
     * 逆向收款
     */
    SAP_NEGATIVE_PAY(1),
    
    /**
     * 正向收入
     */
    SAP_POSITIVE_INCOME(2),
    
    /**
     * 逆向收入
     */
    SAP_NEGATIVE_INCOME(3),
    
    /**
     * 收入回调
     */
    SAP_INCOME_CALLBACK(4),
    
    /**
     * 支付
     */
    PAY(5),

    /**
     * 退款
     */
    REFUND(6),
   
    /**
     * 短信
     */
    SMS(7),
    
    /**
     * RB
     */
    RB(8),
    
    /**
     * 订单状态通知接口
     */
    ORDER_STATUS_NOTICE(9),

    /**
     * 同程接口
     */
    TC(10);
    
    int value = 0;

    /**
     *
     * @param value
     */
    LogType(int value) {
        this.value = value;
    }


    public static LogType toLogType(int value) {

        if (value == 1) {
            return PAY;
        }
        //TODO
        return null;
    }
}

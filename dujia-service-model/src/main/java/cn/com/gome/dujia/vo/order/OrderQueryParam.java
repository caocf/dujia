package cn.com.gome.dujia.vo.order;

/**
 * Description  :
 * Copyright    : Copyright (c) 2008- 2016 All rights reserved;
 * Created Time : 2016/6/6 20:05;
 *
 * @author WenJie Mai
 * @version 1.0
 */
public class OrderQueryParam {

    /**
     * 支付状态
     */
    private Integer payStatus;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 排除订单状态
     */
    private Integer nonOrderStatus;

    /**
     * 第三方订单号
     */
    private String venderOrderId;

    /**
     * 是否判断出游结束日期
     */
    private String judgeEndTime;

    /**
     * 是否判断sap参数
     */
    private String orderSapParam;

    /**
     * 是否判断sap参数
     */
    private String refundSapParam;

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getNonOrderStatus() {
        return nonOrderStatus;
    }

    public void setNonOrderStatus(Integer nonOrderStatus) {
        this.nonOrderStatus = nonOrderStatus;
    }

    public String getVenderOrderId() {
        return venderOrderId;
    }

    public void setVenderOrderId(String venderOrderId) {
        this.venderOrderId = venderOrderId;
    }

    public String getJudgeEndTime() {
        return judgeEndTime;
    }

    public void setJudgeEndTime(String judgeEndTime) {
        this.judgeEndTime = judgeEndTime;
    }

    public String getOrderSapParam() {
        return orderSapParam;
    }

    public void setOrderSapParam(String orderSapParam) {
        this.orderSapParam = orderSapParam;
    }

    public String getRefundSapParam() {
        return refundSapParam;
    }

    public void setRefundSapParam(String refundSapParam) {
        this.refundSapParam = refundSapParam;
    }
}

package cn.com.gome.dujia.vo.refund;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.com.gome.dujia.enums.*;
import cn.com.gome.dujia.model.OrderRefund;

/**
 * Description : 退款VO
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年5月3日 下午1:54:59 <br/>
 *
 * @author WenJie Mai
 * @version 1.0
 */
public class OrderRefundInfo extends OrderRefund {

    /**
     * @author WenJie Mai
     * <p>
     * Created Time : 2016年5月3日 下午1:55:05 <br/>
     */
    private static final long serialVersionUID = -1006510644011005616L;

    /**
     * 退款原因
     */
    private RefundReason refundReasonName;

    /**
     * 退款类型
     */
    private RefundPayType refundPayTypeName;

    /**
     * 退款状态
     */
    private RefundStatus refundStatusName;

    /**
     * 退款方式
     */
    private RefundMode refundModeName;

    /**
     * 支付平台
     */
    private PayMode platformName;

    /**
     * 支付平台
     */
    private String payModeName;

    /**
     * 退款单创建开始时间(用于页面查询)
     */
    private Date startTime;

    /**
     * 退款单创建结束时间(用于页面查询)
     */
    private Date endTime;

    /**
     * 审批开始时间(用于页面查询)
     */
    private Date auditeStartTime;

    /**
     * 审批结束时间(用于页面查询)
     */
    private Date auditeEndTime;

    /**
     * 订单支付开始时间(用于页面查询)
     */
    private Date payStartTime;

    /**
     * 订单支付结束时间(用于页面查询)
     */
    private Date payEndTime;

    /**
     * 退款支付开始时间(用于页面查询)
     */
    private Date refundPayStartTime;

    /**
     * 退款支付结束时间(用于页面查询)
     */
    private Date refundPayEndTime;

    /**
     * 退款单完成时间
     */
    private Date refundFinishStartTime;

    /**
     * 退款单完成时间
     */
    private Date refundFinishEndTime;

    /**
     * 非退款状态
     */
    private Integer nonRefundStatus;

    /**
     * 收货人手机号
     */
    private String phone;

    /**
     * INIT : 待审核
     * AUDIT: 已审核
     * ALL  : 所有
     */
    private String refundQueryType;

    /**
     * 退款状态
     */
    private List<Integer> refundStatusList;

    /**
     * 支付订单号(对应st_order_pay表的trans_no)
     */
    private String payTrxorderId;

    /**
     * 支付时间(对应st_order_pay表的pay_time)
     */
    private Date orderPayTime;

    /**
     * 支付金额(对应st_order_pay表的pay_money)
     */
    private BigDecimal orderPayMoney;

    /**
     * 支付方式(对应st_order_pay表的pay_mode)
     */
    private String orderPayMode;

    /**
     * 支付方式sap(对应st_order_pay表的pay_mode_sap)
     */
    private String orderPayModeSap;

    /**
     * 审核方式
     */
    private Integer auditStatus;

    private Integer nonAuditStatus;

    /**
     * 订单id集合
     */
    private String orderIds;

    private List<String> orderIdList;

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getNonAuditStatus() {
        return nonAuditStatus;
    }

    public void setNonAuditStatus(Integer nonAuditStatus) {
        this.nonAuditStatus = nonAuditStatus;
    }

    public RefundReason getRefundReasonName() {
        return refundReasonName;
    }

    public void setRefundReasonName(RefundReason refundReasonName) {
        this.refundReasonName = refundReasonName;
    }

    public RefundPayType getRefundPayTypeName() {
        return refundPayTypeName;
    }

    public void setRefundPayTypeName(RefundPayType refundPayTypeName) {
        this.refundPayTypeName = refundPayTypeName;
    }

    public RefundMode getRefundModeName() {
        return refundModeName;
    }

    public void setRefundModeName(RefundMode refundModeName) {
        this.refundModeName = refundModeName;
    }

    public RefundStatus getRefundStatusName() {
        return refundStatusName;
    }

    public void setRefundStatusName(RefundStatus refundStatusName) {
        this.refundStatusName = refundStatusName;
    }

    public PayMode getPlatformName() {
        return platformName;
    }

    public void setPlatformName(PayMode platformName) {
        this.platformName = platformName;
    }

    public String getPayModeName() {
        return payModeName;
    }

    public void setPayModeName(String payModeName) {
        this.payModeName = payModeName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getAuditeStartTime() {
        return auditeStartTime;
    }

    public void setAuditeStartTime(Date auditeStartTime) {
        this.auditeStartTime = auditeStartTime;
    }

    public Date getAuditeEndTime() {
        return auditeEndTime;
    }

    public void setAuditeEndTime(Date auditeEndTime) {
        this.auditeEndTime = auditeEndTime;
    }

    public Date getPayStartTime() {
        return payStartTime;
    }

    public void setPayStartTime(Date payStartTime) {
        this.payStartTime = payStartTime;
    }

    public Date getPayEndTime() {
        return payEndTime;
    }

    public void setPayEndTime(Date payEndTime) {
        this.payEndTime = payEndTime;
    }

    public Integer getNonRefundStatus() {
        return nonRefundStatus;
    }

    public void setNonRefundStatus(Integer nonRefundStatus) {
        this.nonRefundStatus = nonRefundStatus;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRefundQueryType() {
        return refundQueryType;
    }

    public void setRefundQueryType(String refundQueryType) {
        this.refundQueryType = refundQueryType;
    }

    public List<Integer> getRefundStatusList() {
        return refundStatusList;
    }

    public void setRefundStatusList(List<Integer> refundStatusList) {
        this.refundStatusList = refundStatusList;
    }

    public Date getRefundPayStartTime() {
        return refundPayStartTime;
    }

    public void setRefundPayStartTime(Date refundPayStartTime) {
        this.refundPayStartTime = refundPayStartTime;
    }

    public Date getRefundPayEndTime() {
        return refundPayEndTime;
    }

    public void setRefundPayEndTime(Date refundPayEndTime) {
        this.refundPayEndTime = refundPayEndTime;
    }

    public Date getRefundFinishStartTime() {
        return refundFinishStartTime;
    }

    public void setRefundFinishStartTime(Date refundFinishStartTime) {
        this.refundFinishStartTime = refundFinishStartTime;
    }

    public Date getRefundFinishEndTime() {
        return refundFinishEndTime;
    }

    public void setRefundFinishEndTime(Date refundFinishEndTime) {
        this.refundFinishEndTime = refundFinishEndTime;
    }

    public String getPayTrxorderId() {
        return payTrxorderId;
    }

    public void setPayTrxorderId(String payTrxorderId) {
        this.payTrxorderId = payTrxorderId;
    }

    public Date getOrderPayTime() {
        return orderPayTime;
    }

    public void setOrderPayTime(Date orderPayTime) {
        this.orderPayTime = orderPayTime;
    }

    public BigDecimal getOrderPayMoney() {
        return orderPayMoney;
    }

    public void setOrderPayMoney(BigDecimal orderPayMoney) {
        this.orderPayMoney = orderPayMoney;
    }

    public String getOrderPayMode() {
        return orderPayMode;
    }

    public void setOrderPayMode(String orderPayMode) {
        this.orderPayMode = orderPayMode;
    }

    public String getOrderPayModeSap() {
        return orderPayModeSap;
    }

    public void setOrderPayModeSap(String orderPayModeSap) {
        this.orderPayModeSap = orderPayModeSap;
    }

    public String getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(String orderIds) {
        this.orderIds = orderIds;
    }

    public List<String> getOrderIdList() {
        return orderIdList;
    }

    public void setOrderIdList(List<String> orderIdList) {
        this.orderIdList = orderIdList;
    }
}
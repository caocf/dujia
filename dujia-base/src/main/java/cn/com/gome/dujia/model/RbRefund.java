package cn.com.gome.dujia.model;

import java.io.Serializable;
import java.util.Date;
import cn.com.gome.dujia.ws.client.rb.AcceptRefundTask;
/**
 * Description  :
 * Copyright    : Copyright (c) 2008- 2016 All rights reserved;
 * Created Time : 2016/4/18 15:41;
 *
 * @author WenJie Mai
 * @version 1.0
 */
public class RbRefund implements Serializable {

    private static final long serialVersionUID = 5877161612606087551L;

    /**
     * 请求url
     */
    private String url;

    /**
     * 订单号(国美)
     */
    private String orderId;

    /**
     * 流水号
     */
    private String payOrderNo;

    /**
     * 退款单号
     */
    private String refundId;

    /**
     * 退款金额
     */
    private Double refundAmount;

    /**
     * 支付金额
     */
    private Double payAmount;

    /**
     *  sap支付方式
     */
    private String payModeSap;

    /**
     * 销售渠道代码(退款单的来源平台代码固定值，rb提供)
     */
    private Integer buid;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 供应商交易流水号
     */
    private String transNo;

    /**
     * 卡号
     */
    private String cardNumber;

    /**
     * 原系统参考号(线下退款用)
     */
    private String orgSysNo;

    /**
     * 退款明细
     */
    private String refundDetailsId;
    
    /**
     * acceptRefund
     */
    private AcceptRefundTask acceptRefund;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public Double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayModeSap() {
        return payModeSap;
    }

    public void setPayModeSap(String payModeSap) {
        this.payModeSap = payModeSap;
    }

    public Integer getBuid() {
        return buid;
    }

    public void setBuid(Integer buid) {
        this.buid = buid;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getOrgSysNo() {
        return orgSysNo;
    }

    public void setOrgSysNo(String orgSysNo) {
        this.orgSysNo = orgSysNo;
    }

    public String getRefundDetailsId() {
        return refundDetailsId;
    }

    public void setRefundDetailsId(String refundDetailsId) {
        this.refundDetailsId = refundDetailsId;
    }

	public AcceptRefundTask getAcceptRefund() {
		return acceptRefund;
	}

	public void setAcceptRefund(AcceptRefundTask acceptRefund) {
		this.acceptRefund = acceptRefund;
	}
    
}

package cn.com.gome.dujia.vo.order;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * Description  : 订单状态通知请求参数
 * Copyright    : Copyright (c) 2008- 2016 All rights reserved;
 * Created Time : 2016/5/24 10:19;
 *
 * @author WenJie Mai
 * @version 1.0
 */
public class OrderStatusNoticeRequest implements Serializable {

    private static final long serialVersionUID = 7438214552869795050L;

    /**
     * 操作类型
     */
    private String OperateType;

    /**
     * 同程订单号
     */
    private String orderId;

    /**
     * 订单金额
     */
    private BigDecimal OrderAmount;

    /**
     * 历史退款金额（包括本次退款金额）
     */
    private BigDecimal AllRefundAmount;

    /**
     * 本次退款金额
     */
    private BigDecimal RefundAmount;

    /**
     * 退款单号
     */
    private String RefundNumber;

    /**
     * 同城收取服务费
     */
    private BigDecimal ServiceCharge;

    /**
     * 退款原因备注
     */
    private String Remark;

    /**
     * 国美订单号
     */
    private String ExternalOrderId;

    /**
     * 订单有效性
     */
    private Boolean OrderFlag;

    public String getOperateType() {
        return OperateType;
    }

    public void setOperateType(String operateType) {
        OperateType = operateType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getOrderAmount() {
        return OrderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        OrderAmount = orderAmount;
    }

    public BigDecimal getAllRefundAmount() {
        return AllRefundAmount;
    }

    public void setAllRefundAmount(BigDecimal allRefundAmount) {
        AllRefundAmount = allRefundAmount;
    }

    public BigDecimal getRefundAmount() {
        return RefundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        RefundAmount = refundAmount;
    }

    public String getRefundNumber() {
        return RefundNumber;
    }

    public void setRefundNumber(String refundNumber) {
        RefundNumber = refundNumber;
    }

    public BigDecimal getServiceCharge() {
        return ServiceCharge;
    }

    public void setServiceCharge(BigDecimal serviceCharge) {
        ServiceCharge = serviceCharge;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getExternalOrderId() {
        return ExternalOrderId;
    }

    public void setExternalOrderId(String externalOrderId) {
        ExternalOrderId = externalOrderId;
    }

    public Boolean getOrderFlag() {
        return OrderFlag;
    }

    public void setOrderFlag(Boolean orderFlag) {
        OrderFlag = orderFlag;
    }
}

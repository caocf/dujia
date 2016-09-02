package cn.com.gome.dujia.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.ws.rs.FormParam;

/**
 * 同程回调接口参数
 * 
 * @author xiongyan
 * @date 2016年5月31日 上午11:40:28
 */
public class OrderNoticeDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 操作类型
	 */
	@FormParam("OperateType")
	private String OperateType;
	
	/**
	 * 同程订单号
	 */
	@FormParam("orderId")
	private String orderId;
	
	/**
	 * 订单金额
	 */
	@FormParam("OrderAmount")
	private BigDecimal OrderAmount;
	
	/**
	 * 历史退款金额（包括本次退款金额）
	 */
	@FormParam("AllRefundAmount")
	private BigDecimal AllRefundAmount;
	
	/**
	 * 本次退款金额
	 */
	@FormParam("RefundAmount")
	private BigDecimal RefundAmount;

	/**
	 * 退款单号
	 */
	@FormParam("RefundNumber")
	private String RefundNumber;

	/**
	 * 同城收取服务费
	 */
	@FormParam("ServiceCharge")
	private BigDecimal ServiceCharge;
	
	/**
	 * 退款原因备注
	 */
	@FormParam("Remark")
	private String Remark;
	
	/**
	 * 合作方订单号
	 */
	@FormParam("ExternalOrderId")
	private String ExternalOrderId;
	
	/**
	 * 订单有效性
	 */
	@FormParam("OrderFlag")
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

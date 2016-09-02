package cn.com.gome.dujia.vo.order;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import cn.com.gome.dujia.enums.IncrementReason;
import cn.com.gome.dujia.enums.IncrementType;
import cn.com.gome.dujia.enums.OrderStatusType;
/**
 * @author wangweiran
 *
 * 订单增量信息
 */
public class OrderIncrementInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 同程订单号
	 * Y
	 */
	@JacksonXmlProperty(localName = "OrderId")
	private String orderId;
	/**
	 * 增量类型 0： OrderCreate 下单 1： OrderFlagChange 订单状态变化
	 * Y
	 */
	@JacksonXmlProperty(localName = "IncrementType")
	private IncrementType incrementType;
	/**
	 * 变化时间
	 * Y
	 */
	@JacksonXmlProperty(localName = "IncrementDate")
	private Date incrementDate;
	/**
	 *  变化原因0：OrderCancel 订单取消,
	 *  1：InventoryConfirm 库存确认
	 *  2：OrderPay 订单支付
	 *  3：OrderConfirm 订单库存确认
	 *  4： OrderRefund 订单申请退款
	 *  5：OrderRefundFinish 订单确认退款
	 *  6：OrderCreate 订单创建
	 *  
	 *  Y
	 */
	@JacksonXmlProperty(localName = "IncrementReason")
	private IncrementReason incrementReason;
	/**
	 * 订单状态
	 *	0：WaitInventoryConfirm 待确认库存
	 *	1：WaitForPay 待支付
	 *	2：Cancel 已取消
	 *	3：AlreadyPay 已支付
	 *	5： NeedTcConfirm 待同程确认
	 *	10：TcAlreadyConfirm 同程已确认
	 *	15：PartialRefund 部分退款(有人出游)
	 *	20： AllRefund 申请全额退款
	 *	25：FinishRefund 全额退款结束
	 *	30：PartialRefundConfirmed部分退款结束
	 *	35：Finished 已结算
	 *
	 *	Y
	 */
	@JacksonXmlProperty(localName = "OrderFlag")
	private OrderStatusType orderFlag;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public IncrementType getIncrementType() {
		return incrementType;
	}
	public void setIncrementType(IncrementType incrementType) {
		this.incrementType = incrementType;
	}
	public Date getIncrementDate() {
		return incrementDate;
	}
	public void setIncrementDate(Date incrementDate) {
		this.incrementDate = incrementDate;
	}
	public IncrementReason getIncrementReason() {
		return incrementReason;
	}
	public void setIncrementReason(IncrementReason incrementReason) {
		this.incrementReason = incrementReason;
	}
	public OrderStatusType getOrderFlag() {
		return orderFlag;
	}
	public void setOrderFlag(OrderStatusType orderFlag) {
		this.orderFlag = orderFlag;
	}
}

package cn.com.gome.dujia.vo.order;

import java.io.Serializable;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
/**
 * @author wangweiran
 * 
 * 创建订单返回参数
 */
public class CreateOrderResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 提示信息
	 */
	@JacksonXmlProperty(localName = "Msg")
	private String msg;
	/**
	 * 订单号
	 */
	@JacksonXmlProperty(localName = "OrderId")
	private String orderId;
	/**
	 * 订单金额
	 */
	@JacksonXmlProperty(localName = "OrderMoney")
	private String orderMoney;
	/**
	 * 是否可以及时确认
	 */
	@JacksonXmlProperty(localName = "IsAffirm")
	private String isAffirm;
	/**
	 * 返回码
	 */
	@JacksonXmlProperty(localName = "ResultCode")
	private String resultCode;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(String orderMoney) {
		this.orderMoney = orderMoney;
	}
	public String getIsAffirm() {
		return isAffirm;
	}
	public void setIsAffirm(String isAffirm) {
		this.isAffirm = isAffirm;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
}

package cn.com.gome.dujia.vo.json.order;

import cn.com.gome.dujia.vo.product.PassengerModel;
import cn.com.gome.dujia.vo.product.ResourceModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author wangweiran
 * 
 * 同程订单详情
 */
public class OrderInfoModel implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 客户订单号
	 */
	@JsonProperty(value = "CusOrderId")
	private String cusOrderId;
	/**
	 * 订单流水号
	 */
	@JsonProperty(value = "DisplayId")
	private String displayId;
	/**
	 * 订单状态：文字描述
	 */
	@JsonProperty(value = "OrderFlag")
	private String orderFlag;
	/**
	 * 总价 (订单+保险)
	 */
	@JsonProperty(value = "Origin")
	private BigDecimal origin;
	/**
	 * 保险金额
	 */
	@JsonProperty(value = "InsuranceAmount")
	private BigDecimal insuranceAmount;
	/**
	 * 订单金额
	 */
	@JsonProperty(value = "OrderAmount")
	private BigDecimal orderAmount;
	/**
	 * 客人已支付金额
	 */
	@JsonProperty(value = "Money")
	private BigDecimal money;
	/**
	 * 退款金额
	 */
	@JsonProperty(value = "RefundAmount")
	private BigDecimal refundAmount;
	/**
	 * 赔款金额
	 */
	@JsonProperty(value = "CompensateAmount")
	private BigDecimal compensateAmount;
	/**
	 * 手续费
	 */
	@JsonProperty(value = "PenaltyAmount")
	private BigDecimal penaltyAmount;
	/**
	 * 联系人
	 */
	@JsonProperty(value = "Contact")
	private String contact;
	/**
	 * 联系人手机
	 */
	@JsonProperty(value = "Cellphone")
	private String cellphone;
	/**
	 * 联系人性别
	 * 0->女，1->男
	 */
	@JsonProperty(value = "ContactSex")
	private Integer contactSex;
	/**
	 * 消费开始时间
	 */
	@JsonProperty(value = "StartTime")
	private String startTime;
	/**
	 * 消费结束时间
	 */
	@JsonProperty(value = "EndTime")
	private String endTime;
	/**
	 * 下单时间
	 */
	@JsonProperty(value = "CreateTime")
	private String createTime;
	/**
	 * 产品标题
	 */
	@JsonProperty(value = "ProductTitle")
	private String productTitle;
	/**
	 * 数量
	 */
	@JsonProperty(value = "Counts")
	private CountModel counts;
	/**
	 * 出游人信息
	 */
	@JsonProperty(value = "Passengers")
	private List<PassengerModel> passengers;
	/**
	 * 订产品信息
	 */
	@JsonProperty(value = "Resources")
	private List<ResourceModel> resources;
	public String getCusOrderId() {
		return cusOrderId;
	}
	public void setCusOrderId(String cusOrderId) {
		this.cusOrderId = cusOrderId;
	}
	public String getDisplayId() {
		return displayId;
	}
	public void setDisplayId(String displayId) {
		this.displayId = displayId;
	}
	public String getOrderFlag() {
		return orderFlag;
	}
	public void setOrderFlag(String orderFlag) {
		this.orderFlag = orderFlag;
	}
	public BigDecimal getOrigin() {
		return origin;
	}
	public void setOrigin(BigDecimal origin) {
		this.origin = origin;
	}
	public BigDecimal getInsuranceAmount() {
		return insuranceAmount;
	}
	public void setInsuranceAmount(BigDecimal insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public BigDecimal getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}
	public BigDecimal getCompensateAmount() {
		return compensateAmount;
	}
	public void setCompensateAmount(BigDecimal compensateAmount) {
		this.compensateAmount = compensateAmount;
	}
	public BigDecimal getPenaltyAmount() {
		return penaltyAmount;
	}
	public void setPenaltyAmount(BigDecimal penaltyAmount) {
		this.penaltyAmount = penaltyAmount;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public Integer getContactSex() {
		return contactSex;
	}
	public void setContactSex(Integer contactSex) {
		this.contactSex = contactSex;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public CountModel getCounts() {
		return counts;
	}
	public void setCounts(CountModel counts) {
		this.counts = counts;
	}
	public List<PassengerModel> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<PassengerModel> passengers) {
		this.passengers = passengers;
	}
	public List<ResourceModel> getResources() {
		return resources;
	}
	public void setResources(List<ResourceModel> resources) {
		this.resources = resources;
	}
}

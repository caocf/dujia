package cn.com.gome.dujia.vo.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
/**
 * @author wangweiran
 *
 *	同程创建订单请求
 */
public class CreateOrderRequest implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 线路编号
	 * Y
	 */
	@JacksonXmlProperty(localName = "LineId")
	private Integer lineId;
	/**
	 * 套餐编号
	 * Y
	 */
	@JacksonXmlProperty(localName = "ComboId")
	private Integer comboId;
	/**
	 * 出游时间
	 * Y
	 */
	@JacksonXmlProperty(localName = "TravelStartDate")
	private Date travelStartDate;
	/**
	 * 成人数(与儿童数必传一个)
	 * Y|N
	 */
	@JacksonXmlProperty(localName = "AdultNum")
	private Integer adultNum;
	/**
	 * 儿童数(与成人数必传一个)
	 * Y|N
	 */
	@JacksonXmlProperty(localName = "ChildNum")
	private Integer childNum;
	/**
	 * 预订份数
	 * Y
	 */
	@JacksonXmlProperty(localName = "BookCount")
	private Integer bookCount;
	/**
	 * 联系人姓名
	 * Y
	 */
	@JacksonXmlProperty(localName = "LinkManName")
	private String linkManName;
	/**
	 * 联系人性别（0-女，1-男）
	 * Y
	 */
	@JacksonXmlProperty(localName = "LinkManSex")
	private Integer linkManSex;
	/**
	 * 联系人手机号
	 * Y
	 */
	@JacksonXmlProperty(localName = "LinkManMobile")
	private String linkManMobile;
	/**
	 * 外部订单号
	 */
	@JacksonXmlProperty(localName = "OutSideOrderId")
	private String outSideOrderId;
	/**
	 * 外部扩展信息
	 */
	@JacksonXmlProperty(localName = "OutSideOrderInfo")
	private String outSideOrderInfo;
	/**
	 * 具体使用信息
	 * Y
	 */
	@JacksonXmlProperty(localName = "TravelUseInfoDetails")
	private List<ResourceUseDetail> travelUseInfoDetails;
	/**
	 * 出游人信息
	 * Y
	 */
	@JacksonXmlProperty(localName = "PassengerInfos")
	private List<TravlePassengerInfo> passengerInfos;
	/**
	 * 前台下单金额
	 */
	@JacksonXmlProperty(localName = "OrderMoney")
	private BigDecimal orderMoney;
	/**
	 * 是否立即支付
	 */
	@JacksonXmlProperty(localName = "IsPay")
	private Boolean isPay;
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	public Integer getComboId() {
		return comboId;
	}
	public void setComboId(Integer comboId) {
		this.comboId = comboId;
	}
	public Date getTravelStartDate() {
		return travelStartDate;
	}
	public void setTravelStartDate(Date travelStartDate) {
		this.travelStartDate = travelStartDate;
	}
	public Integer getAdultNum() {
		return adultNum;
	}
	public void setAdultNum(Integer adultNum) {
		this.adultNum = adultNum;
	}
	public Integer getChildNum() {
		return childNum;
	}
	public void setChildNum(Integer childNum) {
		this.childNum = childNum;
	}
	public Integer getBookCount() {
		return bookCount;
	}
	public void setBookCount(Integer bookCount) {
		this.bookCount = bookCount;
	}
	public String getLinkManName() {
		return linkManName;
	}
	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}
	public Integer getLinkManSex() {
		return linkManSex;
	}
	public void setLinkManSex(Integer linkManSex) {
		this.linkManSex = linkManSex;
	}
	public String getLinkManMobile() {
		return linkManMobile;
	}
	public void setLinkManMobile(String linkManMobile) {
		this.linkManMobile = linkManMobile;
	}
	public String getOutSideOrderId() {
		return outSideOrderId;
	}
	public void setOutSideOrderId(String outSideOrderId) {
		this.outSideOrderId = outSideOrderId;
	}
	public String getOutSideOrderInfo() {
		return outSideOrderInfo;
	}
	public void setOutSideOrderInfo(String outSideOrderInfo) {
		this.outSideOrderInfo = outSideOrderInfo;
	}
	public List<ResourceUseDetail> getTravelUseInfoDetails() {
		return travelUseInfoDetails;
	}
	public void setTravelUseInfoDetails(List<ResourceUseDetail> travelUseInfoDetails) {
		this.travelUseInfoDetails = travelUseInfoDetails;
	}
	public List<TravlePassengerInfo> getPassengerInfos() {
		return passengerInfos;
	}
	public void setPassengerInfos(List<TravlePassengerInfo> passengerInfos) {
		this.passengerInfos = passengerInfos;
	}
	public BigDecimal getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(BigDecimal orderMoney) {
		this.orderMoney = orderMoney;
	}
	public Boolean getIsPay() {
		return isPay;
	}
	public void setIsPay(Boolean isPay) {
		this.isPay = isPay;
	}
	
}

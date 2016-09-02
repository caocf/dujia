package cn.com.gome.dujia.vo.order;

import java.io.Serializable;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import cn.com.gome.dujia.enums.IdType;
/**
 * @author wangweiran
 * 
 * 订单预定房间、天数、人数的数量
 */
public class CountModel implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 成人数
	 */
	@JacksonXmlProperty(localName = "AdultCount")
	private Integer adultCount;
	/**
	 * 儿童数
	 */
	@JacksonXmlProperty(localName = "ChildCount")
	private Integer childCount;
	/**
	 * 预定份数
	 */
	@JacksonXmlProperty(localName = "PriceFraction")
	private Integer priceFraction;
	/**
	 * 房间总数
	 */
	@JacksonXmlProperty(localName = "RoomCount")
	private Integer roomCount;
	/**
	 * 行程天数
	 */
	@JacksonXmlProperty(localName = "Days")
	private Integer days;
	/**
	 * 姓名
	 */
	@JacksonXmlProperty(localName = "Name")
	private String name;
	/**
	 * 证件类型：身份证 1，护照 2，军官证 3，士兵证 4，港澳台通行证 5，临时身份证 6，户口本 7，
	 * 其他 8，警官证 9，外国人居留证 12，回乡证 15， 企业营业执照 16， 法人代码证 17，台胞证 18
	 */
	@JacksonXmlProperty(localName = "IdType")
	private IdType idType;
	/**
	 * 证件编号
	 */
	@JacksonXmlProperty(localName = "IdNo")
	private Integer idNo;
	public Integer getAdultCount() {
		return adultCount;
	}
	public void setAdultCount(Integer adultCount) {
		this.adultCount = adultCount;
	}
	public Integer getChildCount() {
		return childCount;
	}
	public void setChildCount(Integer childCount) {
		this.childCount = childCount;
	}
	public Integer getPriceFraction() {
		return priceFraction;
	}
	public void setPriceFraction(Integer priceFraction) {
		this.priceFraction = priceFraction;
	}
	public Integer getRoomCount() {
		return roomCount;
	}
	public void setRoomCount(Integer roomCount) {
		this.roomCount = roomCount;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public IdType getIdType() {
		return idType;
	}
	public void setIdType(IdType idType) {
		this.idType = idType;
	}
	public Integer getIdNo() {
		return idNo;
	}
	public void setIdNo(Integer idNo) {
		this.idNo = idNo;
	}
}

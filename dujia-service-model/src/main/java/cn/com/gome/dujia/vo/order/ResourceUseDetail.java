package cn.com.gome.dujia.vo.order;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @author wangweiran
 * 
 *	创建订单具体使用信息
 */
public class ResourceUseDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 资源关联ID
	 * Y
	 */
	@JsonProperty(value = "RelateId")
	private Integer relateId;
	/**
	 * 使用日期
	 * Y
	 */
	@JsonProperty(value = "TravelDate")
	private String travelDate;
	/**
	 * 特殊要求
	 */
	@JsonProperty(value = "SpecialRequest")
	private String specialRequest;
	public Integer getRelateId() {
		return relateId;
	}
	public void setRelateId(Integer relateId) {
		this.relateId = relateId;
	}
	public String getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}
	public String getSpecialRequest() {
		return specialRequest;
	}
	public void setSpecialRequest(String specialRequest) {
		this.specialRequest = specialRequest;
	}
}

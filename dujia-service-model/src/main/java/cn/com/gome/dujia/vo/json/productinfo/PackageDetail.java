package cn.com.gome.dujia.vo.json.productinfo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import cn.com.gome.dujia.model.ZbyProductPackagePrice;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author wangweiran
 *
 * 商品套餐详情
 */
public class PackageDetail implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * 套餐 Id
	 */
	@JsonProperty(value = "PackageId")
	private String packageId;
	/**
	 * 套餐提前预订天数
	 */
	@JsonProperty(value = "BeforehandBookingDay")
	private Integer beforehandBookingDay;
	/**
	 * 餐剩余库存量,价格详情
	 */
	@JsonProperty(value = "PackageSaleInfoDetails")
	private Map<String, ZbyProductPackagePrice> packageDetails = new HashMap<String, ZbyProductPackagePrice>();

	/**
	 * 套餐可预订时间 默认每天可以到 23:59:59 为止
	 */
	@JsonProperty(value = "ReserveTime")
	private String reserveTime;
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	public Integer getBeforehandBookingDay() {
		return beforehandBookingDay;
	}
	public void setBeforehandBookingDay(Integer beforehandBookingDay) {
		this.beforehandBookingDay = beforehandBookingDay;
	}

	public Map<String, ZbyProductPackagePrice> getPackageDetails() {
		return packageDetails;
	}
	public String getReserveTime() {
		return reserveTime;
	}
	public void setReserveTime(String reserveTime) {
		this.reserveTime = reserveTime;
	}
}

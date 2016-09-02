package cn.com.gome.dujia.vo.json.productinfo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author wangweiran
 *
 * 套餐剩余库存量,价格详情
 */
public class PackageSaleInfoDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 门市价
	 */
	@JsonProperty(value = "RetailPrice")
	private String retailPrice;
	/**
	 * 分销商卖价
	 */
	@JsonProperty(value = "DistributionSalePrice")
	private String distributionSalePrice;
	/**
	 * 建议售卖价(前端展示的价钱，有3%提成)
	 */
	@JsonProperty(value = "TcDirectPrice")
	private String tcDirectPrice;
	/**
	 * 库存剩余量
	 */
	@JsonProperty(value = "InventoryRemainder")
	private String inventoryRemainder;
	/**
	 * 库存状态：0，即时；1，正常；4，不可售
	 */
	@JsonProperty(value = "InventoryStats")
	private String inventoryStats;
	/**
	 * 是否开放售卖： true，开放售卖； false，限量售卖
	 */
	@JsonProperty(value = "OpeningSale")
	private Boolean openingSale;
	public String getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(String retailPrice) {
		this.retailPrice = retailPrice;
	}
	public String getDistributionSalePrice() {
		return distributionSalePrice;
	}
	public void setDistributionSalePrice(String distributionSalePrice) {
		this.distributionSalePrice = distributionSalePrice;
	}
	public String getTcDirectPrice() {
		return tcDirectPrice;
	}
	public void setTcDirectPrice(String tcDirectPrice) {
		this.tcDirectPrice = tcDirectPrice;
	}
	public String getInventoryRemainder() {
		return inventoryRemainder;
	}
	public void setInventoryRemainder(String inventoryRemainder) {
		this.inventoryRemainder = inventoryRemainder;
	}
	public String getInventoryStats() {
		return inventoryStats;
	}
	public void setInventoryStats(String inventoryStats) {
		this.inventoryStats = inventoryStats;
	}
	public Boolean getOpeningSale() {
		return openingSale;
	}
	public void setOpeningSale(Boolean openingSale) {
		this.openingSale = openingSale;
	}
}

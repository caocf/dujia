package cn.com.gome.dujia.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "zby_product_package_price")
public class ZbyProductPackagePrice implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id") 
	private Integer id;
	
	@Column(name="product_id") 
	private String productId;
	
	@Column(name="package_id") 
	private String packageId;
	
	@Column(name="package_sale_date") 
	private Date packageSaleDate;
	
	@Column(name="retail_price") 
	@JsonProperty(value = "RetailPrice")
	private BigDecimal retailPrice;
	
	@Column(name="distribution_sale_price") 
	@JsonProperty(value = "DistributionSalePrice")
	private BigDecimal distributionSalePrice;
	
	@Column(name="tc_direct_price") 
	@JsonProperty(value = "TcDirectPrice")
	private BigDecimal tcDirectPrice;
	
	@Column(name="inventory_remainder") 
	@JsonProperty(value = "InventoryRemainder")
	private Integer inventoryRemainder;
	
	@Column(name="inventory_stats") 
	@JsonProperty(value = "InventoryStats")
	private Integer inventoryStats;
	
	@Column(name="opening_sale") 
	@JsonProperty(value = "OpeningSale")
	private Boolean openingSale;
	
	@Column(name="beforehand_booking_day") 
	private Integer beforehandBookingDay;
	
	/**
     * 套餐班期最低价格
     */
    @Transient
    private BigDecimal minPrice;

    /**
     * 套餐班期最高价格
     */
    @Transient
    private BigDecimal maxPrice;

    /**
     * @非库字段
     */
    @Transient
    private String packDate;

	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setProductId(String value) {
		this.productId = value;
	}
	
	public String getProductId() {
		return this.productId;
	}

	public void setPackageId(String value) {
		this.packageId = value;
	}
	
	public String getPackageId() {
		return this.packageId;
	}

	public void setPackageSaleDate(Date value) {
		this.packageSaleDate = value;
	}
	
	public Date getPackageSaleDate() {
		return this.packageSaleDate;
	}

	public void setRetailPrice(BigDecimal value) {
		this.retailPrice = value;
	}
	
	public BigDecimal getRetailPrice() {
		return this.retailPrice;
	}

	public void setDistributionSalePrice(BigDecimal value) {
		this.distributionSalePrice = value;
	}
	
	public BigDecimal getDistributionSalePrice() {
		return this.distributionSalePrice;
	}

	public void setTcDirectPrice(BigDecimal value) {
		this.tcDirectPrice = value;
	}
	
	public BigDecimal getTcDirectPrice() {
		return this.tcDirectPrice;
	}

	public void setInventoryRemainder(Integer value) {
		this.inventoryRemainder = value;
	}
	
	public Integer getInventoryRemainder() {
		return this.inventoryRemainder;
	}

	public void setInventoryStats(Integer value) {
		this.inventoryStats = value;
	}
	
	public Integer getInventoryStats() {
		return this.inventoryStats;
	}

	public void setOpeningSale(Boolean value) {
		this.openingSale = value;
	}
	
	public Boolean getOpeningSale() {
		return this.openingSale;
	}

	public void setBeforehandBookingDay(Integer value) {
		this.beforehandBookingDay = value;
	}
	
	public Integer getBeforehandBookingDay() {
		return this.beforehandBookingDay;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

    public String getPackDate() {
        return packDate;
    }

    public void setPackDate(String packDate) {
        this.packDate = packDate;
    }

    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ZbyProductPackagePrice other = (ZbyProductPackagePrice) that;
        return (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getPackageId() == null ? other.getPackageId() == null : this.getPackageId().equals(other.getPackageId()))
            && (this.getPackageSaleDate() == null ? other.getPackageSaleDate() == null : this.getPackageSaleDate().equals(other.getPackageSaleDate()))
            && (this.getRetailPrice() == null ? other.getRetailPrice() == null : this.getRetailPrice().equals(other.getRetailPrice()))
            && (this.getDistributionSalePrice() == null ? other.getDistributionSalePrice() == null : this.getDistributionSalePrice().equals(other.getDistributionSalePrice()))
            && (this.getTcDirectPrice() == null ? other.getTcDirectPrice() == null : this.getTcDirectPrice().equals(other.getTcDirectPrice()))
            && (this.getInventoryRemainder() == null ? other.getInventoryRemainder() == null : this.getInventoryRemainder().equals(other.getInventoryRemainder()))
            && (this.getInventoryStats() == null ? other.getInventoryStats() == null : this.getInventoryStats().equals(other.getInventoryStats()))
            && (this.getOpeningSale() == null ? other.getOpeningSale() == null : this.getOpeningSale().equals(other.getOpeningSale()))
            && (this.getBeforehandBookingDay() == null ? other.getBeforehandBookingDay() == null : this.getBeforehandBookingDay().equals(other.getBeforehandBookingDay()));
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getPackageId() == null) ? 0 : getPackageId().hashCode());
        result = prime * result + ((getPackageSaleDate() == null) ? 0 : getPackageSaleDate().hashCode());
        result = prime * result + ((getRetailPrice() == null) ? 0 : getRetailPrice().hashCode());
        result = prime * result + ((getDistributionSalePrice() == null) ? 0 : getDistributionSalePrice().hashCode());
        result = prime * result + ((getTcDirectPrice() == null) ? 0 : getTcDirectPrice().hashCode());
        result = prime * result + ((getInventoryRemainder() == null) ? 0 : getInventoryRemainder().hashCode());
        result = prime * result + ((getInventoryStats() == null) ? 0 : getInventoryStats().hashCode());
        result = prime * result + ((getOpeningSale() == null) ? 0 : getOpeningSale().hashCode());
        result = prime * result + ((getBeforehandBookingDay() == null) ? 0 : getBeforehandBookingDay().hashCode());
        return result;
    }
}
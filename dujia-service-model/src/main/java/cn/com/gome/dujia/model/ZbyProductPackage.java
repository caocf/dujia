package cn.com.gome.dujia.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Table(name = "zby_product_package")
public class ZbyProductPackage implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "package_id")
    @JsonProperty(value = "PackagePriceId")
    private String packageId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "package_name")
    @JsonProperty(value = "PackagePriceName")
    private String packageName;

    @Column(name = "package_long_name")
    @JsonProperty(value = "PackagePeiceLongName")
    private String packageLongName;

    @Column(name = "package_short_name")
    @JsonProperty(value = "PackagePriceShortName")
    private String packageShortName;

    @Column(name = "package_description")
    @JsonProperty(value = "PackagePriceDescription")
    private String packageDescription;

    @Column(name = "all_count_num")
    @JsonProperty(value = "ALLCountNum")
    private Integer allCountNum;

    @Column(name = "adult_num")
    @JsonProperty(value = "AdultNum")
    private Integer adultNum;

    @Column(name = "child_num")
    @JsonProperty(value = "ChildNum")
    private Integer childNum;

    @Column(name = "advance_day")
    @JsonProperty(value = "AdvanceDay")
    private Integer advanceDay;

    @Column(name = "advance_time")
    @JsonProperty(value = "AdvanceTime")
    private String advanceTime;

    @Column(name = "sort")
    @JsonProperty(value = "Sort")
    private Integer sort;

    @Column(name = "fee_include_info")
    @JsonProperty(value = "FeeIncludeInfo")
    private String feeIncludeInfo;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "is_delete")
    private Boolean isDelete;

    @Column(name = "sale_count")
    private Integer saleCount;

    @JsonProperty(value = "HotelList")
    @Transient
    private List<ZbyProductPackageDetail> hotelList;

    @JsonProperty(value = "SceneryList")
    @Transient
    private List<ZbyProductPackageDetail> sceneryList;

    /**
     * 售卖日期,非实体属性，用于前端数据展示
     */
    @Transient
    private Date packageSaleDate_dis;

    /**
     * 门市价,非实体属性，用于前端数据展示
     */
    @Transient
    private BigDecimal retailPrice_dis;

    /**
     * 分销商卖价,非实体属性，用于前端数据展示
     */
    @Transient
    private BigDecimal distributionSalePrice_dis;

    /**
     * 建议售卖价,非实体属性，用于前端数据展示
     */
    @Transient
    private BigDecimal tcDirectPrice_dis;

    /**
     * '库存状态：0，即时；1，正常；4，不可售',
     */
    @Transient
    private Integer inventoryStats;

    /**
     * '是否开放售卖：true开放售卖；false限量售卖',
     */
    @Transient
    private Boolean openingSale;

    /**
     * '库存剩余量'
     */
    @Transient
    private Integer inventoryRemainder;

    public Integer getInventoryRemainder() {
        return inventoryRemainder;
    }

    public void setInventoryRemainder(Integer inventoryRemainder) {
        this.inventoryRemainder = inventoryRemainder;
    }

    public Integer getInventoryStats() {
        return inventoryStats;
    }

    public void setInventoryStats(Integer inventoryStats) {
        this.inventoryStats = inventoryStats;
    }

    public Boolean isOpeningSale() {
        return openingSale;
    }

    public void setOpeningSale(Boolean openingSale) {
        this.openingSale = openingSale;
    }

    public void setPackageId(String value) {
        this.packageId = value;
    }

    public String getPackageId() {
        return this.packageId;
    }

    public void setProductId(String value) {
        this.productId = value;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setPackageName(String value) {
        this.packageName = value;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageLongName(String value) {
        this.packageLongName = value;
    }

    public String getPackageLongName() {
        return this.packageLongName;
    }

    public void setPackageShortName(String value) {
        this.packageShortName = value;
    }

    public String getPackageShortName() {
        return this.packageShortName;
    }

    public void setPackageDescription(String value) {
        this.packageDescription = value;
    }

    public String getPackageDescription() {
        return this.packageDescription;
    }

    public void setAllCountNum(Integer value) {
        this.allCountNum = value;
    }

    public Integer getAllCountNum() {
        return this.allCountNum;
    }

    public void setAdultNum(Integer value) {
        this.adultNum = value;
    }

    public Integer getAdultNum() {
        return this.adultNum;
    }

    public void setChildNum(Integer value) {
        this.childNum = value;
    }

    public Integer getChildNum() {
        return this.childNum;
    }

    public void setAdvanceDay(Integer value) {
        this.advanceDay = value;
    }

    public Integer getAdvanceDay() {
        return this.advanceDay;
    }

    public void setAdvanceTime(String value) {
        this.advanceTime = value;
    }

    public String getAdvanceTime() {
        return this.advanceTime;
    }

    public void setSort(Integer value) {
        this.sort = value;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setFeeIncludeInfo(String value) {
        this.feeIncludeInfo = value;
    }

    public String getFeeIncludeInfo() {
        return this.feeIncludeInfo;
    }

    public void setCreateTime(Date value) {
        this.createTime = value;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setUpdateTime(Date value) {
        this.updateTime = value;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setIsDelete(Boolean value) {
        this.isDelete = value;
    }

    public Boolean getIsDelete() {
        return this.isDelete;
    }

    public void setSaleCount(Integer value) {
        this.saleCount = value;
    }

    public Integer getSaleCount() {
        return this.saleCount;
    }

    public List<ZbyProductPackageDetail> getHotelList() {
        return hotelList;
    }

    public void setHotelList(List<ZbyProductPackageDetail> hotelList) {
        this.hotelList = hotelList;
    }

    public List<ZbyProductPackageDetail> getSceneryList() {
        return sceneryList;
    }

    public void setSceneryList(List<ZbyProductPackageDetail> sceneryList) {
        this.sceneryList = sceneryList;
    }

    /**
     * 售卖日期,非实体属性，用于前端数据展示
     */
    public Date getPackageSaleDate_dis() {
        return packageSaleDate_dis;
    }

    public void setPackageSaleDate_dis(Date packageSaleDate_dis) {
        this.packageSaleDate_dis = packageSaleDate_dis;
    }

    /**
     * 门市价,非实体属性，用于前端数据展示
     */
    public BigDecimal getRetailPrice_dis() {
        return retailPrice_dis;
    }

    public void setRetailPrice_dis(BigDecimal retailPrice_dis) {
        this.retailPrice_dis = retailPrice_dis;
    }

    /**
     * 分销商卖价,非实体属性，用于前端数据展示
     */
    public BigDecimal getDistributionSalePrice_dis() {
        return distributionSalePrice_dis;
    }

    public void setDistributionSalePrice_dis(BigDecimal distributionSalePrice_dis) {
        this.distributionSalePrice_dis = distributionSalePrice_dis;
    }

    /**
     * 建议售卖价,非实体属性，用于前端数据展示
     */
    public BigDecimal getTcDirectPrice_dis() {
        return tcDirectPrice_dis;
    }

    public void setTcDirectPrice_dis(BigDecimal tcDirectPrice_dis) {
        this.tcDirectPrice_dis = tcDirectPrice_dis;
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
        ZbyProductPackage other = (ZbyProductPackage) that;
        return (
                (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
                && (this.getPackageName() == null ? other.getPackageName() == null : this.getPackageName().equals(other.getPackageName()))
                && (this.getPackageLongName() == null ? other.getPackageLongName() == null : this.getPackageLongName().equals(other.getPackageLongName()))
                && (this.getPackageShortName() == null ? other.getPackageShortName() == null : this.getPackageShortName().equals(other.getPackageShortName()))
                && (this.getPackageDescription() == null ? other.getPackageDescription() == null : this.getPackageDescription().equals(other.getPackageDescription()))
                && (this.getAllCountNum() == null ? other.getAllCountNum() == null : this.getAllCountNum().equals(other.getAllCountNum()))
                && (this.getAdultNum() == null ? other.getAdultNum() == null : this.getAdultNum().equals(other.getAdultNum()))
                && (this.getChildNum() == null ? other.getChildNum() == null : this.getChildNum().equals(other.getChildNum()))
                && (this.getAdvanceDay() == null ? other.getAdvanceDay() == null : this.getAdvanceDay().equals(other.getAdvanceDay()))
                && (this.getAdvanceTime() == null ? other.getAdvanceTime() == null : this.getAdvanceTime().equals(other.getAdvanceTime()))
                && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
                && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
                && (this.getFeeIncludeInfo() == null ? other.getFeeIncludeInfo() == null : this.getFeeIncludeInfo().equals(other.getFeeIncludeInfo())));
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPackageName() == null) ? 0 : getPackageName().hashCode());
        result = prime * result + ((getPackageLongName() == null) ? 0 : getPackageLongName().hashCode());
        result = prime * result + ((getPackageShortName() == null) ? 0 : getPackageShortName().hashCode());
        result = prime * result + ((getPackageDescription() == null) ? 0 : getPackageDescription().hashCode());
        result = prime * result + ((getAllCountNum() == null) ? 0 : getAllCountNum().hashCode());
        result = prime * result + ((getAdultNum() == null) ? 0 : getAdultNum().hashCode());
        result = prime * result + ((getChildNum() == null) ? 0 : getChildNum().hashCode());
        result = prime * result + ((getAdvanceDay() == null) ? 0 : getAdvanceDay().hashCode());
        result = prime * result + ((getAdvanceTime() == null) ? 0 : getAdvanceTime().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getFeeIncludeInfo() == null) ? 0 : getFeeIncludeInfo().hashCode());
        return result;
    }
}
package cn.com.gome.dujia.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 套餐
 * reated by zhaoxiang-ds on 2016/4/14.
 */
public class ZbyPackageDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 线路ID
     */
    private String productId;

    /**
     * 套餐ID
     */
    private String packageId;
    /**
     * 套餐名称
     */
    private String packageName;

    /**
     * 套餐长名称
     */
    private String packageLongName;

    /**
     * 套餐价格
     */
    private BigDecimal price;

    /**
     * '库存状态：0，即时；1，正常；4，不可售',
     */
    private Integer inventoryStats;

    /**
     * '是否开放售卖：true开放售卖；false限量售卖',
     */
    private Boolean openingSale;

    /**
     * '库存剩余量'
     */
    private Integer inventoryRemainder;

    /**
     * 是否显示：0-不显示，1-显示
     */
    private Integer isShow = 1;

    /**
     * 资源列表=酒店、景区
     */
    private List<ZbyPackageResourceSimpleDto> resources;

    /**
     * 线路酒店
     */
    private List<ZbyPackageResourceSimpleDto> hotels;

    /**
     * 线路景区
     */
    private List<ZbyPackageResourceSimpleDto> sceneries;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<ZbyPackageResourceSimpleDto> getResources() {
        return resources;
    }

    public void setResources(List<ZbyPackageResourceSimpleDto> resources) {
        this.resources = resources;
    }

    public String getPackageLongName() {
        return packageLongName;
    }

    public void setPackageLongName(String packageLongName) {
        this.packageLongName = packageLongName;
    }

    public List<ZbyPackageResourceSimpleDto> getHotels() {
        return hotels;
    }

    public void setHotels(List<ZbyPackageResourceSimpleDto> hotels) {
        this.hotels = hotels;
    }

    public List<ZbyPackageResourceSimpleDto> getSceneries() {
        return sceneries;
    }

    public void setSceneries(List<ZbyPackageResourceSimpleDto> sceneries) {
        this.sceneries = sceneries;
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

    public Integer getInventoryRemainder() {
        return inventoryRemainder;
    }

    public void setInventoryRemainder(Integer inventoryRemainder) {
        this.inventoryRemainder = inventoryRemainder;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }
}

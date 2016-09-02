package cn.com.gome.dujia.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 详情页 - 套餐价格班期
 * Created by zhaoxiang-ds on 2016/4/25.
 */
public class ZbyPackagePriceDto implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 售卖日期str
     */
    private String saleDate;

    /**
     * 售卖最低价
     */
    private BigDecimal price;

    /**
     * 库存剩余量
     */
    private Integer stock;

    /**
     * 库存状态：0，即时；1，正常；4，不可售
     */
    private String inventoryStats;

    /**
     * 是否开放售卖：true开放售卖；false限量售卖
     */
    private Boolean openingSale;

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getInventoryStats() {
        return inventoryStats;
    }

    public void setInventoryStats(String inventoryStats) {
        this.inventoryStats = inventoryStats;
    }

    public Boolean isOpeningSale() {
        return openingSale;
    }

    public void setOpeningSale(Boolean openingSale) {
        this.openingSale = openingSale;
    }
}

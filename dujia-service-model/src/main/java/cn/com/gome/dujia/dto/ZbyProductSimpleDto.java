package cn.com.gome.dujia.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 首页线路 DTO
 * Created by zhaoxiang-ds on 2016/4/13.
 */
public class ZbyProductSimpleDto implements Serializable {

    private static final long serialVersionUID = 1L;
    /*
    * 线路ID
    */
    private String productId;

    /**
     * 线路标题
     */
    private String productName;

    /**
     *
     */
    private String titleName;

    /**
     * 行程天数
     */
    private Integer days;

    /**
     * 线路主目的地城市名称
     */
    private String cityName;

    /**
     * 线路最小价(同程价)
     */
    private BigDecimal minPrice;

    /**
     * 卖价单位：起/份(同程价)
     */
    private String sellUnit;

    /**
     * 线路图片
     */
    private String imageUrl;

    /**
     * 连接
     */
    private String productUrl;

    /**
     * 位置
     */
    private String position;


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public String getSellUnit() {
        return sellUnit;
    }

    public void setSellUnit(String sellUnit) {
        this.sellUnit = sellUnit;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }
}

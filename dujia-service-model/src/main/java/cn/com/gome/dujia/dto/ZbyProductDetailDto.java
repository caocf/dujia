package cn.com.gome.dujia.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 线路详情DTO
 * Created by zhaoxiang-ds on 2016/4/13.
 */
public class ZbyProductDetailDto implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 线路ID
     */
    private String productId;

    /**
     * 线路主标题
     */
    private String mainName;

    /**
     * 线路副标题
     */
    private String subName;

    /**
     * App 标题
     */
    private String appTitle;

    /**
     * 线路图片链接
     */
    private String productImageUrlList;

    /**
     * 行程天数
     */
    private Integer days;

    /**
     * 线路主目的地城市Id
     */
    private String cityId;

    /**
     * 线路主目的地城市名称
     */
    private String cityName;

    /**
     * 线路主目的地城市拼音
     */
    private String cityPinyin;

    /**
     * 线路最小价(同程价)
     */
    private BigDecimal productMinPrice;

    /**
     * 线路最大价(同程价)
     */
    private BigDecimal productMaxPrice;

    /**
     * 卖价单位：起/份(同程价)
     */
    private String sellUnit;

    /**
     * 线路开始时间
     */
    private Date beginDate;

    /**
     * 线路结束时间
     */
    private Date overDate;

    /**
     * 退改规定
     */
    private String refundRuleInfo;

    /**
     * 发票事宜
     */
    private String tipInfo;

    /**
     * 推荐理由
     */
    private String recommendReason;

    /**
     * 线路特色,支持富文本
     */
    private String productFeature;

    /**
     * 线路行程推荐
     */
    private String productRouteDes;

    /**
     * 产品优惠信息
     */
    private String preferential;

    /**
     * 注意事项
     */
    private String attentionInfo;

    /**
     * 友情提醒
     */
    private String remindInfo;

    /**
     * 线路包含
     */
    private String productInclude;

    /**
     * 酒店特色
     */
    private String hotelFeatureInfo;

    /**
     * 景区特色
     */
    private String sceneryFeatureInfo;

    /**
     * 供应商编号
     */
    private String venderId;

    /**
     * 交通线路
     */
    private String transportInfo;

    /**
     * 是否删除
     */
    private Boolean isDelete;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getAppTitle() {
        return appTitle;
    }

    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }

    public String getProductImageUrlList() {
        return productImageUrlList;
    }

    public void setProductImageUrlList(String productImageUrlList) {
        this.productImageUrlList = productImageUrlList;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityPinyin() {
        return cityPinyin;
    }

    public void setCityPinyin(String cityPinyin) {
        this.cityPinyin = cityPinyin;
    }

    public BigDecimal getProductMinPrice() {
        return productMinPrice;
    }

    public void setProductMinPrice(BigDecimal productMinPrice) {
        this.productMinPrice = productMinPrice;
    }

    public BigDecimal getProductMaxPrice() {
        return productMaxPrice;
    }

    public void setProductMaxPrice(BigDecimal productMaxPrice) {
        this.productMaxPrice = productMaxPrice;
    }

    public String getSellUnit() {
        return sellUnit;
    }

    public void setSellUnit(String sellUnit) {
        this.sellUnit = sellUnit;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getOverDate() {
        return overDate;
    }

    public void setOverDate(Date overDate) {
        this.overDate = overDate;
    }

    public String getRefundRuleInfo() {
        return refundRuleInfo;
    }

    public void setRefundRuleInfo(String refundRuleInfo) {
        this.refundRuleInfo = refundRuleInfo;
    }

    public String getTipInfo() {
        return tipInfo;
    }

    public void setTipInfo(String tipInfo) {
        this.tipInfo = tipInfo;
    }

    public String getRecommendReason() {
        return recommendReason;
    }

    public void setRecommendReason(String recommendReason) {
        this.recommendReason = recommendReason;
    }

    public String getProductFeature() {
        return productFeature;
    }

    public void setProductFeature(String productFeature) {
        this.productFeature = productFeature;
    }

    public String getProductRouteDes() {
        return productRouteDes;
    }

    public void setProductRouteDes(String productRouteDes) {
        this.productRouteDes = productRouteDes;
    }

    public String getPreferential() {
        return preferential;
    }

    public void setPreferential(String preferential) {
        this.preferential = preferential;
    }

    public String getAttentionInfo() {
        return attentionInfo;
    }

    public void setAttentionInfo(String attentionInfo) {
        this.attentionInfo = attentionInfo;
    }

    public String getRemindInfo() {
        return remindInfo;
    }

    public void setRemindInfo(String remindInfo) {
        this.remindInfo = remindInfo;
    }

    public String getProductInclude() {
        return productInclude;
    }

    public void setProductInclude(String productInclude) {
        this.productInclude = productInclude;
    }

    public String getHotelFeatureInfo() {
        return hotelFeatureInfo;
    }

    public void setHotelFeatureInfo(String hotelFeatureInfo) {
        this.hotelFeatureInfo = hotelFeatureInfo;
    }

    public String getSceneryFeatureInfo() {
        return sceneryFeatureInfo;
    }

    public void setSceneryFeatureInfo(String sceneryFeatureInfo) {
        this.sceneryFeatureInfo = sceneryFeatureInfo;
    }

    public String getVenderId() {
        return venderId;
    }

    public void setVenderId(String venderId) {
        this.venderId = venderId;
    }

    public String getTransportInfo() {
        return transportInfo;
    }

    public void setTransportInfo(String transportInfo) {
        this.transportInfo = transportInfo;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }
}

package cn.com.gome.dujia.model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "zby_product")
public class ZbyProduct implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="product_id") 
	@JsonProperty(value = "ProductId")
	private String productId;
	
	@Column(name="vender_id") 
	private String venderId;
	
	@Column(name="main_name") 
	@JsonProperty(value = "MainName")
	private String mainName;
	
	@Column(name="sub_name") 
	@JsonProperty(value = "SubName")
	private String subName;
	
	@Column(name="show_name") 
	@JsonProperty(value = "ShowName")
	private String showName;
	
	@Column(name="app_title") 
	@JsonProperty(value = "AppTitle")
	private String appTitle;
	
	@Column(name="days") 
	@JsonProperty(value = "Days")
	private Integer days;
	
	@Column(name="begin_date") 
	@JsonProperty(value = "BeginDate")
	private String beginDate;
	
	@Column(name="over_date") 
	@JsonProperty(value = "OverDate")
	private String overDate;
	
	@Column(name="recom_image_url_local") 
	private String recomImageUrlLocal;
	
	@Column(name="recom_image_url") 
	@JsonProperty(value = "RecomImageUrl")
	private String recomImageUrl;
	
	@Column(name="image_url_local") 
	private String imageUrlLocal;
	
	@Column(name="image_url") 
	@JsonProperty(value = "ImageUrl")
	private String imageUrl;
	
	@Column(name="city_id") 
	@JsonProperty(value = "CityId")
	private String cityId;
	
	@Column(name="city_name") 
	@JsonProperty(value = "CityName")
	private String cityName;
	
	@Column(name="city_pinyin") 
	@JsonProperty(value = "CityPinyin")
	private String cityPinyin;
	
	@Column(name="product_min_price")
	@JsonProperty(value = "ProductMinPrice")
	private BigDecimal productMinPrice;
	
	@Column(name="product_max_price") 
	@JsonProperty(value = "ProductMaxPrice")
	private BigDecimal productMaxPrice;
	
	@Column(name="sell_unit") 
	@JsonProperty(value = "SellUnit")
	private String sellUnit;
	
	@Column(name="product_feature") 
	@JsonProperty(value = "ProductFeature")
	private String productFeature;
	
	@Column(name="product_route_des") 
	@JsonProperty(value = "ProductRouteDes")
	private String productRouteDes;
	
	@Column(name="preferential") 
	@JsonProperty(value = "Preferential")
	private String preferential;
	
	@Column(name="product_image_url_list") 
	private String productImageUrlList;
	
	@Column(name="product_image_url_list_local") 
	private String productImageUrlListLocal;
	
	@Column(name="attention_info") 
	@JsonProperty(value = "AttentionInfo")
	private String attentionInfo;
	
	@Column(name="refund_rule_info") 
	@JsonProperty(value = "RefundRuleInfo")
	private String refundRuleInfo;
	
	@Column(name="tip_info") 
	@JsonProperty(value = "TipInfo")
	private String tipInfo;
	
	@Column(name="transport_info") 
	@JsonProperty(value = "TransportInfo")
	private String transportInfo;
	
	@Column(name="remind_info") 
	@JsonProperty(value = "RemindInfo")
	private String remindInfo;
	
	@Column(name="hotel_feature_info") 
	@JsonProperty(value = "HotelFeatureInfo")

	private String hotelFeatureInfo;
	
	@Column(name="scenery_feature_info")
	@JsonProperty(value = "SceneryFeatureInfo")

	private String sceneryFeatureInfo;
	
	@Column(name="recommend_reason") 
	@JsonProperty(value = "RecommendReason")

	private String recommendReason;
	
	@Column(name="product_include") 
	@JsonProperty(value = "ProductInclude")

	private String productInclude;
	
	@Column(name="product_long_name") 
	@JsonProperty(value = "ProductLongName")
	private String productLongName;
	
	@Column(name="sale_count") 
	private Integer saleCount;
	
	@Column(name="browse_count") 
	private Integer browseCount;
	
	@Column(name="create_time") 
	private Date createTime;
	
	@Column(name="update_time") 
	private Date updateTime;
	
	@Column(name="is_delete") 
	private Boolean isDelete;
	

	@JsonProperty(value = "ProductImageUrlList")
    @Transient
    private List<String> productImageUrlListRaw;

	@JsonProperty(value = "ProductRecomImage")
    @Transient
    private List<ZbyRecomInfo> recomInfo;

    @JsonProperty(value = "ProductPriceList")
    @Transient
    private List<ZbyProductPackage> productPackageList;

//    @JsonProperty(value = "PackageIdList")
//    @Transient
//    private List<String> packageIdList;

	public void setProductId(String value) {
		this.productId = value;
	}
	
	public String getProductId() {
		return this.productId;
	}

	public void setVenderId(String value) {
		this.venderId = value;
	}
	
	public String getVenderId() {
		return this.venderId;
	}

	public void setMainName(String value) {
		this.mainName = value;
	}
	
	public String getMainName() {
		return this.mainName;
	}

	public void setSubName(String value) {
		this.subName = value;
	}
	
	public String getSubName() {
		return this.subName;
	}

	public void setShowName(String value) {
		this.showName = value;
	}
	
	public String getShowName() {
		return this.showName;
	}

	public void setAppTitle(String value) {
		this.appTitle = value;
	}
	
	public String getAppTitle() {
		return this.appTitle;
	}

	public void setDays(Integer value) {
		this.days = value;
	}
	
	public Integer getDays() {
		return this.days;
	}

	public void setBeginDate(String value) {
		this.beginDate = value;
	}
	
	public String getBeginDate() {
		return this.beginDate;
	}

	public void setOverDate(String value) {
		this.overDate = value;
	}
	
	public String getOverDate() {
		return this.overDate;
	}

	public void setRecomImageUrlLocal(String value) {
		this.recomImageUrlLocal = value;
	}
	
	public String getRecomImageUrlLocal() {
		return this.recomImageUrlLocal;
	}

	public void setRecomImageUrl(String value) {
		this.recomImageUrl = value;
	}
	
	public String getRecomImageUrl() {
		return this.recomImageUrl;
	}

	public void setImageUrlLocal(String value) {
		this.imageUrlLocal = value;
	}
	
	public String getImageUrlLocal() {
		return this.imageUrlLocal;
	}

	public void setImageUrl(String value) {
		this.imageUrl = value;
	}
	
	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setCityId(String value) {
		this.cityId = value;
	}
	
	public String getCityId() {
		return this.cityId;
	}

	public void setCityName(String value) {
		this.cityName = value;
	}
	
	public String getCityName() {
		return this.cityName;
	}

	public void setCityPinyin(String value) {
		this.cityPinyin = value;
	}
	
	public String getCityPinyin() {
		return this.cityPinyin;
	}

	public void setProductMinPrice(BigDecimal value) {
		this.productMinPrice = value;
	}
	
	public BigDecimal getProductMinPrice() {
		return this.productMinPrice;
	}

	public void setProductMaxPrice(BigDecimal value) {
		this.productMaxPrice = value;
	}
	
	public BigDecimal getProductMaxPrice() {
		return this.productMaxPrice;
	}

	public void setSellUnit(String value) {
		this.sellUnit = value;
	}
	
	public String getSellUnit() {
		return this.sellUnit;
	}

	public void setProductFeature(String value) {
		this.productFeature = value;
	}
	
	public String getProductFeature() {
		return this.productFeature;
	}

	public void setProductRouteDes(String value) {
		this.productRouteDes = value;
	}
	
	public String getProductRouteDes() {
		return this.productRouteDes;
	}

	public void setPreferential(String value) {
		this.preferential = value;
	}
	
	public String getPreferential() {
		return this.preferential;
	}

	public void setProductImageUrlList(String value) {
		this.productImageUrlList = value;
	}
	
	public String getProductImageUrlList() {
		return this.productImageUrlList;
	}

	public void setProductImageUrlListLocal(String value) {
		this.productImageUrlListLocal = value;
	}
	
	public String getProductImageUrlListLocal() {
		return this.productImageUrlListLocal;
	}

	public void setAttentionInfo(String value) {
		this.attentionInfo = value;
	}
	
	public String getAttentionInfo() {
		return this.attentionInfo;
	}

	public void setRefundRuleInfo(String value) {
		this.refundRuleInfo = value;
	}
	
	public String getRefundRuleInfo() {
		return this.refundRuleInfo;
	}

	public void setTipInfo(String value) {
		this.tipInfo = value;
	}
	
	public String getTipInfo() {
		return this.tipInfo;
	}

	public void setTransportInfo(String value) {
		this.transportInfo = value;
	}
	
	public String getTransportInfo() {
		return this.transportInfo;
	}

	public void setRemindInfo(String value) {
		this.remindInfo = value;
	}
	
	public String getRemindInfo() {
		return this.remindInfo;
	}

	public void setHotelFeatureInfo(String value) {
		this.hotelFeatureInfo = value;
	}
	
	public String getHotelFeatureInfo() {
		return this.hotelFeatureInfo;
	}

	public void setSceneryFeatureInfo(String value) {
		this.sceneryFeatureInfo = value;
	}
	
	public String getSceneryFeatureInfo() {
		return this.sceneryFeatureInfo;
	}

	public void setRecommendReason(String value) {
		this.recommendReason = value;
	}
	
	public String getRecommendReason() {
		return this.recommendReason;
	}

	public void setProductInclude(String value) {
		this.productInclude = value;
	}
	
	public String getProductInclude() {
		return this.productInclude;
	}

	public void setProductLongName(String value) {
		this.productLongName = value;
	}
	
	public String getProductLongName() {
		return this.productLongName;
	}

	public void setSaleCount(Integer value) {
		this.saleCount = value;
	}
	
	public Integer getSaleCount() {
		return this.saleCount;
	}

	public void setBrowseCount(Integer value) {
		this.browseCount = value;
	}
	
	public Integer getBrowseCount() {
		return this.browseCount;
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

	public List<String> getProductImageUrlListRaw() {
		return productImageUrlListRaw;
	}

	public void setProductImageUrlListRaw(List<String> productImageUrlListRaw) {
		this.productImageUrlListRaw = productImageUrlListRaw;
	}

	public List<ZbyRecomInfo> getRecomInfo() {
		return recomInfo;
	}

	public void setRecomInfo(List<ZbyRecomInfo> recomInfo) {
		this.recomInfo = recomInfo;
	}

	public List<ZbyProductPackage> getProductPackageList() {
		return productPackageList;
	}

	public void setProductPackageList(List<ZbyProductPackage> productPackageList) {
		this.productPackageList = productPackageList;
	}

//	public List<String> getPackageIdList() {
//		return packageIdList;
//	}
//
//	public void setPackageIdList(List<String> packageIdList) {
//		this.packageIdList = packageIdList;
//	}

	
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
        ZbyProduct other = (ZbyProduct) that;
        return (
             (this.getMainName() == null ? other.getMainName() == null : this.getMainName().equals(other.getMainName()))
            && (this.getSubName() == null ? other.getSubName() == null : this.getSubName().equals(other.getSubName()))
            && (this.getShowName() == null ? other.getShowName() == null : this.getShowName().equals(other.getShowName()))
            && (this.getAppTitle() == null ? other.getAppTitle() == null : this.getAppTitle().equals(other.getAppTitle()))
            && (this.getDays() == null ? other.getDays() == null : this.getDays().equals(other.getDays()))
            && (this.getBeginDate() == null ? other.getBeginDate() == null : this.getBeginDate().equals(other.getBeginDate()))
            && (this.getOverDate() == null ? other.getOverDate() == null : this.getOverDate().equals(other.getOverDate()))
            && (this.getCityId() == null ? other.getCityId() == null : this.getCityId().equals(other.getCityId()))
            && (this.getCityName() == null ? other.getCityName() == null : this.getCityName().equals(other.getCityName()))
            && (this.getCityPinyin() == null ? other.getCityPinyin() == null : this.getCityPinyin().equals(other.getCityPinyin()))
            && (this.getProductMinPrice() == null ? other.getProductMinPrice() == null : this.getProductMinPrice().equals(other.getProductMinPrice()))
            && (this.getProductMaxPrice() == null ? other.getProductMaxPrice() == null : this.getProductMaxPrice().equals(other.getProductMaxPrice()))
            && (this.getSellUnit() == null ? other.getSellUnit() == null : this.getSellUnit().equals(other.getSellUnit()))
            && (this.getProductFeature() == null ? other.getProductFeature() == null : this.getProductFeature().equals(other.getProductFeature()))
            && (this.getProductRouteDes() == null ? other.getProductRouteDes() == null : this.getProductRouteDes().equals(other.getProductRouteDes()))
            && (this.getPreferential() == null ? other.getPreferential() == null : this.getPreferential().equals(other.getPreferential()))
            && (this.getAttentionInfo() == null ? other.getAttentionInfo() == null : this.getAttentionInfo().equals(other.getAttentionInfo()))
            && (this.getRefundRuleInfo() == null ? other.getRefundRuleInfo() == null : this.getRefundRuleInfo().equals(other.getRefundRuleInfo()))
            && (this.getTipInfo() == null ? other.getTipInfo() == null : this.getTipInfo().equals(other.getTipInfo()))
            && (this.getTransportInfo() == null ? other.getTransportInfo() == null : this.getTransportInfo().equals(other.getTransportInfo()))
            && (this.getRemindInfo() == null ? other.getRemindInfo() == null : this.getRemindInfo().equals(other.getRemindInfo()))
            && (this.getHotelFeatureInfo() == null ? other.getHotelFeatureInfo() == null : this.getHotelFeatureInfo().equals(other.getHotelFeatureInfo()))
            && (this.getSceneryFeatureInfo() == null ? other.getSceneryFeatureInfo() == null : this.getSceneryFeatureInfo().equals(other.getSceneryFeatureInfo()))
            && (this.getRecommendReason() == null ? other.getRecommendReason() == null : this.getRecommendReason().equals(other.getRecommendReason()))
            && (this.getProductInclude() == null ? other.getProductInclude() == null : this.getProductInclude().equals(other.getProductInclude()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getProductLongName() == null ? other.getProductLongName() == null : this.getProductLongName().equals(other.getProductLongName())));
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMainName() == null) ? 0 : getMainName().hashCode());
        result = prime * result + ((getSubName() == null) ? 0 : getSubName().hashCode());
        result = prime * result + ((getShowName() == null) ? 0 : getShowName().hashCode());
        result = prime * result + ((getAppTitle() == null) ? 0 : getAppTitle().hashCode());
        result = prime * result + ((getDays() == null) ? 0 : getDays().hashCode());
//        result = prime * result + ((getBeginDate() == null) ? 0 : getBeginDate().hashCode());
//        result = prime * result + ((getOverDate() == null) ? 0 : getOverDate().hashCode());
        result = prime * result + ((getCityId() == null) ? 0 : getCityId().hashCode());
        result = prime * result + ((getCityName() == null) ? 0 : getCityName().hashCode());
        result = prime * result + ((getCityPinyin() == null) ? 0 : getCityPinyin().hashCode());
 //       result = prime * result + ((getProductImageUrlList() == null) ? 0 : getProductImageUrlList().hashCode());
        result = prime * result + ((getProductMinPrice() == null) ? 0 : getProductMinPrice().hashCode());
        result = prime * result + ((getProductMaxPrice() == null) ? 0 : getProductMaxPrice().hashCode());
        result = prime * result + ((getSellUnit() == null) ? 0 : getSellUnit().hashCode());
        result = prime * result + ((getProductFeature() == null) ? 0 : getProductFeature().hashCode());
        result = prime * result + ((getProductRouteDes() == null) ? 0 : getProductRouteDes().hashCode());
        result = prime * result + ((getPreferential() == null) ? 0 : getPreferential().hashCode());
        result = prime * result + ((getAttentionInfo() == null) ? 0 : getAttentionInfo().hashCode());
        result = prime * result + ((getRefundRuleInfo() == null) ? 0 : getRefundRuleInfo().hashCode());
        result = prime * result + ((getTipInfo() == null) ? 0 : getTipInfo().hashCode());
        result = prime * result + ((getTransportInfo() == null) ? 0 : getTransportInfo().hashCode());
        result = prime * result + ((getRemindInfo() == null) ? 0 : getRemindInfo().hashCode());
        result = prime * result + ((getHotelFeatureInfo() == null) ? 0 : getHotelFeatureInfo().hashCode());
        result = prime * result + ((getSceneryFeatureInfo() == null) ? 0 : getSceneryFeatureInfo().hashCode());
        result = prime * result + ((getRecommendReason() == null) ? 0 : getRecommendReason().hashCode());
        result = prime * result + ((getProductInclude() == null) ? 0 : getProductInclude().hashCode());
        result = prime * result + ((getProductLongName() == null) ? 0 : getProductLongName().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }
}
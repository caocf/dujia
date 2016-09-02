package cn.com.gome.dujia.model;

import cn.com.gome.dujia.vo.json.productinfo.ResourceGps;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Table(name = "zby_product_package_detail")
public class ZbyProductPackageDetail implements Serializable, Comparable<ZbyProductPackageDetail> {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "package_id")
    private String packageId;

    @Column(name = "related_id")
    @JsonProperty(value = "RelatedId")
    private Integer relatedId;

    @Column(name = "res_id")
    @JsonProperty(value = "ResId")
    private Integer resId;

    @Column(name = "tc_scenery_related_id")
    @JsonProperty(value = "TcSceneryRelatedId")
    private String tcSceneryRelatedId;

    @Column(name = "res_name")
    @JsonProperty(value = "ResName")
    private String resName;

    @Column(name = "res_type")
    @JsonProperty(value = "ResType")
    private Integer resType;

    @Column(name = "res_pro_id")
    @JsonProperty(value = "ResProId")
    private Integer resProId;

    @Column(name = "res_pro_name")
    @JsonProperty(value = "ResProName")
    private String resProName;

    @Column(name = "sup_id")
    @JsonProperty(value = "SupId")
    private Integer supId;

    @Column(name = "sup_pp_id")
    @JsonProperty(value = "SupPPId")
    private Integer supPpId;

    @Column(name = "sup_pp_name")
    @JsonProperty(value = "SupPPName")
    private String supPpName;

    @Column(name = "pro_price")
    @JsonProperty(value = "ProPrice")
    private String proPrice;

    @Column(name = "pro_contain_type")
    @JsonProperty(value = "ProContainType")
    private Integer proContainType;

    @Column(name = "pro_contain_count")
    @JsonProperty(value = "ProContainCount")
    private Integer proContainCount;

    @Column(name = "net")
    @JsonProperty(value = "Net")
    private String net;

    @Column(name = "booking_days")
    @JsonProperty(value = "BookingDays")
    private String bookingDays;

    @Column(name = "need_card")
    @JsonProperty(value = "NeedCard")
    private String needCard;

    @Column(name = "get_ticket_way")
    @JsonProperty(value = "GetTicketWay")
    private String getTicketWay;

    @Column(name = "res_phone")
    @JsonProperty(value = "ResPhone")
    private String resPhone;

    @Column(name = "pro_props")
    private String proProps;

    @Column(name = "address")
    @JsonProperty(value = "Address")
    private String address;

    @Column(name = "res_city")
    @JsonProperty(value = "ResCity")
    private String resCity;

    @Column(name = "start_time")
    @JsonProperty(value = "StartTime")
    private String startTime;

    @Column(name = "res_grade")
    @JsonProperty(value = "ResGrade")
    private String resGrade;

    @Column(name = "res_intro")
    @JsonProperty(value = "ResIntro")
    private String resIntro;

    @Column(name = "use_day")
    @JsonProperty(value = "Useday")
    private String useDay;

    @Column(name = "res_image_list_local")
    private String resImageListLocal;

    @Column(name = "res_image_list")
    private String resImageList;

    @Column(name = "lon")
    private String lon;

    @Column(name = "lat")
    private String lat;

    @Column(name = "bed_type")
    private String bedType;

    @JsonProperty(value = "ProProps")
    @Transient
    private Map<String, String> proPropsRaw;

    @JsonProperty(value = "ResImageList")
    @Transient
    private List<String> resImageListRaw;

    @JsonProperty(value = "ResAdditionalPro")
    @Transient
    private List<ZbyProductAddition> additionProductList;

    @JsonProperty(value = "ResGPSList")
    @Transient
    private List<ResourceGps> resourceGpses;

    // 	资源图片<小图，大图>，展示使用
    @Transient
    private Map<String, String> resImgMap;

    // 	资源图片<小图>，展示使用
    @Transient
    private List<String> smallImg;

    // 	资源图片<大图>，展示使用
    @Transient
    private List<String> bigImg;

    @Column(name = "update_time")
    private Date updateTime;


    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<String> getSmallImg() {
        return smallImg;
    }

    public void setSmallImg(List<String> smallImg) {
        this.smallImg = smallImg;
    }

    public List<String> getBigImg() {
        return bigImg;
    }

    public void setBigImg(List<String> bigImg) {
        this.bigImg = bigImg;
    }

    public Map<String, String> getResImgMap() {
        return resImgMap;
    }

    public void setResImgMap(Map<String, String> resImgMap) {
        this.resImgMap = resImgMap;
    }

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

    public void setRelatedId(Integer value) {
        this.relatedId = value;
    }

    public Integer getRelatedId() {
        return this.relatedId;
    }

    public void setResId(Integer value) {
        this.resId = value;
    }

    public Integer getResId() {
        return this.resId;
    }

    public void setTcSceneryRelatedId(String value) {
        this.tcSceneryRelatedId = value;
    }

    public String getTcSceneryRelatedId() {
        return this.tcSceneryRelatedId;
    }

    public void setResName(String value) {
        this.resName = value;
    }

    public String getResName() {
        return this.resName;
    }

    public void setResType(Integer value) {
        this.resType = value;
    }

    public Integer getResType() {
        return this.resType;
    }

    public void setResProId(Integer value) {
        this.resProId = value;
    }

    public Integer getResProId() {
        return this.resProId;
    }

    public void setResProName(String value) {
        this.resProName = value;
    }

    public String getResProName() {
        return this.resProName;
    }

    public void setSupId(Integer value) {
        this.supId = value;
    }

    public Integer getSupId() {
        return this.supId;
    }

    public void setSupPpId(Integer value) {
        this.supPpId = value;
    }

    public Integer getSupPpId() {
        return this.supPpId;
    }

    public void setSupPpName(String value) {
        this.supPpName = value;
    }

    public String getSupPpName() {
        return this.supPpName;
    }

    public void setProPrice(String value) {
        this.proPrice = value;
    }

    public String getProPrice() {
        return this.proPrice;
    }

    public void setProContainType(Integer value) {
        this.proContainType = value;
    }

    public Integer getProContainType() {
        return this.proContainType;
    }

    public void setProContainCount(Integer value) {
        this.proContainCount = value;
    }

    public Integer getProContainCount() {
        return this.proContainCount;
    }

    public void setNet(String value) {
        this.net = value;
    }

    public String getNet() {
        return this.net;
    }

    public void setBookingDays(String value) {
        this.bookingDays = value;
    }

    public String getBookingDays() {
        return this.bookingDays;
    }

    public void setNeedCard(String value) {
        this.needCard = value;
    }

    public String getNeedCard() {
        return this.needCard;
    }

    public void setGetTicketWay(String value) {
        this.getTicketWay = value;
    }

    public String getGetTicketWay() {
        return this.getTicketWay;
    }

    public void setResPhone(String value) {
        this.resPhone = value;
    }

    public String getResPhone() {
        return this.resPhone;
    }

    public void setProProps(String value) {
        this.proProps = value;
    }

    public String getProProps() {
        return this.proProps;
    }

    public void setAddress(String value) {
        this.address = value;
    }

    public String getAddress() {
        return this.address;
    }

    public void setResCity(String value) {
        this.resCity = value;
    }

    public String getResCity() {
        return this.resCity;
    }

    public void setStartTime(String value) {
        this.startTime = value;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setResGrade(String value) {
        this.resGrade = value;
    }

    public String getResGrade() {
        return this.resGrade;
    }

    public void setResIntro(String value) {
        this.resIntro = value;
    }

    public String getResIntro() {
        return this.resIntro;
    }

    public void setUseDay(String value) {
        this.useDay = value;
    }

    public String getUseDay() {
        return this.useDay;
    }

    public void setResImageListLocal(String value) {
        this.resImageListLocal = value;
    }

    public String getResImageListLocal() {
        return this.resImageListLocal;
    }

    public void setResImageList(String value) {
        this.resImageList = value;
    }

    public String getResImageList() {
        return this.resImageList;
    }

    public void setLon(String value) {
        this.lon = value;
    }

    public String getLon() {
        return this.lon;
    }

    public void setLat(String value) {
        this.lat = value;
    }

    public String getLat() {
        return this.lat;
    }

    public void setBedType(String value) {
        this.bedType = value;
    }

    public String getBedType() {
        return this.bedType;
    }

    public Map<String, String> getProPropsRaw() {
        return proPropsRaw;
    }

    public void setProPropsRaw(Map<String, String> proPropsRaw) {
        this.proPropsRaw = proPropsRaw;
    }

    public List<String> getResImageListRaw() {
        return resImageListRaw;
    }

    public void setResImageListRaw(List<String> resImageListRaw) {
        this.resImageListRaw = resImageListRaw;
    }

    public List<ZbyProductAddition> getAdditionProductList() {
        return additionProductList;
    }

    public void setAdditionProductList(List<ZbyProductAddition> additionProductList) {
        this.additionProductList = additionProductList;
    }

    public List<ResourceGps> getResourceGpses() {
        return resourceGpses;
    }

    public void setResourceGpses(List<ResourceGps> resourceGpses) {
        this.resourceGpses = resourceGpses;
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
        ZbyProductPackageDetail other = (ZbyProductPackageDetail) that;
        return (
                 (this.getRelatedId() == null ? other.getRelatedId() == null : this.getRelatedId().equals(other.getRelatedId()))
                && (this.getResId() == null ? other.getResId() == null : this.getResId().equals(other.getResId()))
                && (this.getTcSceneryRelatedId() == null ? other.getTcSceneryRelatedId() == null : this.getTcSceneryRelatedId().equals(other.getTcSceneryRelatedId()))
                && (this.getResName() == null ? other.getResName() == null : this.getResName().equals(other.getResName()))
                && (this.getResType() == null ? other.getResType() == null : this.getResType().equals(other.getResType()))
                && (this.getResProId() == null ? other.getResProId() == null : this.getResProId().equals(other.getResProId()))
                && (this.getResProName() == null ? other.getResProName() == null : this.getResProName().equals(other.getResProName()))
                && (this.getSupId() == null ? other.getSupId() == null : this.getSupId().equals(other.getSupId()))
                && (this.getSupPpId() == null ? other.getSupPpId() == null : this.getSupPpId().equals(other.getSupPpId()))
                && (this.getSupPpName() == null ? other.getSupPpName() == null : this.getSupPpName().equals(other.getSupPpName()))
                && (this.getProPrice() == null ? other.getProPrice() == null : this.getProPrice().equals(other.getProPrice()))
                && (this.getProContainType() == null ? other.getProContainType() == null : this.getProContainType().equals(other.getProContainType()))
                && (this.getProContainCount() == null ? other.getProContainCount() == null : this.getProContainCount().equals(other.getProContainCount()))
                && (this.getNet() == null ? other.getNet() == null : this.getNet().equals(other.getNet()))
                && (this.getBookingDays() == null ? other.getBookingDays() == null : this.getBookingDays().equals(other.getBookingDays()))
                && (this.getNeedCard() == null ? other.getNeedCard() == null : this.getNeedCard().equals(other.getNeedCard()))
                && (this.getGetTicketWay() == null ? other.getGetTicketWay() == null : this.getGetTicketWay().equals(other.getGetTicketWay()))
                && (this.getResPhone() == null ? other.getResPhone() == null : this.getResPhone().equals(other.getResPhone()))
                && (this.getProProps() == null ? other.getProProps() == null : this.getProProps().equals(other.getProProps()))
                && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
                && (this.getResCity() == null ? other.getResCity() == null : this.getResCity().equals(other.getResCity()))
                && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
                && (this.getResGrade() == null ? other.getResGrade() == null : this.getResGrade().equals(other.getResGrade()))
                && (this.getResIntro() == null ? other.getResIntro() == null : this.getResIntro().equals(other.getResIntro()))
                && (this.getUseDay() == null ? other.getUseDay() == null : this.getUseDay().equals(other.getUseDay()))
               // && (this.getResImageListLocal() == null ? other.getResImageListLocal() == null : this.getResImageListLocal().equals(other.getResImageListLocal()))
                && (this.getResImageList() == null ? other.getResImageList() == null : this.getResImageList().equals(other.getResImageList()))
                && (this.getLon() == null ? other.getLon() == null : this.getLon().equals(other.getLon()))
                && (this.getLat() == null ? other.getLat() == null : this.getLat().equals(other.getLat())));
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
//        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
//        result = prime * result + ((getPackageId() == null) ? 0 : getPackageId().hashCode());
        result = prime * result + ((getRelatedId() == null) ? 0 : getRelatedId().hashCode());
        result = prime * result + ((getResId() == null) ? 0 : getResId().hashCode());
        result = prime * result + ((getTcSceneryRelatedId() == null) ? 0 : getTcSceneryRelatedId().hashCode());
        result = prime * result + ((getResName() == null) ? 0 : getResName().hashCode());
        result = prime * result + ((getResType() == null) ? 0 : getResType().hashCode());
        result = prime * result + ((getResProId() == null) ? 0 : getResProId().hashCode());
        result = prime * result + ((getResProName() == null) ? 0 : getResProName().hashCode());
        result = prime * result + ((getSupId() == null) ? 0 : getSupId().hashCode());
        result = prime * result + ((getSupPpId() == null) ? 0 : getSupPpId().hashCode());
        result = prime * result + ((getSupPpName() == null) ? 0 : getSupPpName().hashCode());
        result = prime * result + ((getProPrice() == null) ? 0 : getProPrice().hashCode());
        result = prime * result + ((getProContainType() == null) ? 0 : getProContainType().hashCode());
        result = prime * result + ((getProContainCount() == null) ? 0 : getProContainCount().hashCode());
        result = prime * result + ((getNet() == null) ? 0 : getNet().hashCode());
        result = prime * result + ((getBookingDays() == null) ? 0 : getBookingDays().hashCode());
        result = prime * result + ((getNeedCard() == null) ? 0 : getNeedCard().hashCode());
        result = prime * result + ((getGetTicketWay() == null) ? 0 : getGetTicketWay().hashCode());
        result = prime * result + ((getResPhone() == null) ? 0 : getResPhone().hashCode());
        result = prime * result + ((getProProps() == null) ? 0 : getProProps().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getResCity() == null) ? 0 : getResCity().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getResGrade() == null) ? 0 : getResGrade().hashCode());
        result = prime * result + ((getResIntro() == null) ? 0 : getResIntro().hashCode());
        result = prime * result + ((getUseDay() == null) ? 0 : getUseDay().hashCode());
        result = prime * result + ((getResImageList() == null) ? 0 : getResImageList().hashCode());
        result = prime * result + ((getLon() == null) ? 0 : getLon().hashCode());
        result = prime * result + ((getLat() == null) ? 0 : getLat().hashCode());
        return result;
    }

	@Override
	public int compareTo(ZbyProductPackageDetail o) {
		int result = 0;
		Integer t = Integer.valueOf(this.getUseDay().split(",")[0]);
		Integer s = Integer.valueOf(o.getUseDay().split(",")[0]);
		result = t.equals(s) ? 0 : (t < s ? -1 : 1);
		return result;
	}
}
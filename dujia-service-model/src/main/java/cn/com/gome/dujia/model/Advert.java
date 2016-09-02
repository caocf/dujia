package cn.com.gome.dujia.model;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "dj_advert")
public class Advert implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "plateform")
    private Integer plateform;

    @Column(name = "data_type")
    private Integer dataType;

    @Column(name = "type")
    private Integer type;

    @Column(name = "city_id")
    private String cityId;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "page")
    private String page;

    @Column(name = "module")
    private String module;

    @Column(name = "position")
    private String position;

    @Column(name = "title_name")
    private String titleName;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private BigDecimal productPrice;

    @Column(name = "product_image")
    private String productImage;

    @Column(name = "product_city_id")
    private String productCityId;

    @Column(name = "product_city_name")
    private String productCityName;

    @Column(name = "url")
    private String url;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "states")
    private Integer states;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_user")
    private String updateUser;

    @Column(name = "update_time")
    private Date updateTime;


    public void setId(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return this.id;
    }

    public void setPlateform(Integer value) {
        this.plateform = value;
    }

    public Integer getPlateform() {
        return this.plateform;
    }

    public void setDataType(Integer value) {
        this.dataType = value;
    }

    public Integer getDataType() {
        return this.dataType;
    }

    public void setType(Integer value) {
        this.type = value;
    }

    public Integer getType() {
        return this.type;
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

    public void setPage(String value) {
        this.page = value;
    }

    public String getPage() {
        return this.page;
    }

    public void setModule(String value) {
        this.module = value;
    }

    public String getModule() {
        return this.module;
    }

    public void setPosition(String value) {
        this.position = value;
    }

    public String getPosition() {
        return this.position;
    }

    public void setTitleName(String value) {
        this.titleName = value;
    }

    public String getTitleName() {
        return this.titleName;
    }

    public void setProductId(String value) {
        this.productId = value;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductName(String value) {
        this.productName = value;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductPrice(BigDecimal value) {
        this.productPrice = value;
    }

    public BigDecimal getProductPrice() {
        return this.productPrice;
    }

    public void setProductImage(String value) {
        this.productImage = value;
    }

    public String getProductImage() {
        return this.productImage;
    }

    public void setProductCityId(String value) {
        this.productCityId = value;
    }

    public String getProductCityId() {
        return this.productCityId;
    }

    public void setProductCityName(String value) {
        this.productCityName = value;
    }

    public String getProductCityName() {
        return this.productCityName;
    }

    public void setUrl(String value) {
        try {
            this.url = URLDecoder.decode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            this.url = value;
        }
    }

    public String getUrl() {
        return this.url;
    }

    public void setStartTime(Date value) {
        this.startTime = value;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setEndTime(Date value) {
        this.endTime = value;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setStates(Integer value) {
        this.states = value;
    }

    public Integer getStates() {
        return this.states;
    }

    public void setCreateUser(String value) {
        this.createUser = value;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateTime(Date value) {
        this.createTime = value;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setUpdateUser(String value) {
        this.updateUser = value;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateTime(Date value) {
        this.updateTime = value;
    }

    public Date getUpdateTime() {
        return this.updateTime;
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
        Advert other = (Advert) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getPlateform() == null ? other.getPlateform() == null : this.getPlateform().equals(other.getPlateform()))
                && (this.getDataType() == null ? other.getDataType() == null : this.getDataType().equals(other.getDataType()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getCityId() == null ? other.getCityId() == null : this.getCityId().equals(other.getCityId()))
                && (this.getCityName() == null ? other.getCityName() == null : this.getCityName().equals(other.getCityName()))
                && (this.getPage() == null ? other.getPage() == null : this.getPage().equals(other.getPage()))
                && (this.getModule() == null ? other.getModule() == null : this.getModule().equals(other.getModule()))
                && (this.getPosition() == null ? other.getPosition() == null : this.getPosition().equals(other.getPosition()))
                && (this.getTitleName() == null ? other.getTitleName() == null : this.getTitleName().equals(other.getTitleName()))
                && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
                && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
                && (this.getProductPrice() == null ? other.getProductPrice() == null : this.getProductPrice().equals(other.getProductPrice()))
                && (this.getProductImage() == null ? other.getProductImage() == null : this.getProductImage().equals(other.getProductImage()))
                && (this.getProductCityId() == null ? other.getProductCityId() == null : this.getProductCityId().equals(other.getProductCityId()))
                && (this.getProductCityName() == null ? other.getProductCityName() == null : this.getProductCityName().equals(other.getProductCityName()))
                && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
                && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
                && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
                && (this.getStates() == null ? other.getStates() == null : this.getStates().equals(other.getStates()))
                && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPlateform() == null) ? 0 : getPlateform().hashCode());
        result = prime * result + ((getDataType() == null) ? 0 : getDataType().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getCityId() == null) ? 0 : getCityId().hashCode());
        result = prime * result + ((getCityName() == null) ? 0 : getCityName().hashCode());
        result = prime * result + ((getPage() == null) ? 0 : getPage().hashCode());
        result = prime * result + ((getModule() == null) ? 0 : getModule().hashCode());
        result = prime * result + ((getPosition() == null) ? 0 : getPosition().hashCode());
        result = prime * result + ((getTitleName() == null) ? 0 : getTitleName().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getProductPrice() == null) ? 0 : getProductPrice().hashCode());
        result = prime * result + ((getProductImage() == null) ? 0 : getProductImage().hashCode());
        result = prime * result + ((getProductCityId() == null) ? 0 : getProductCityId().hashCode());
        result = prime * result + ((getProductCityName() == null) ? 0 : getProductCityName().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getStates() == null) ? 0 : getStates().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}
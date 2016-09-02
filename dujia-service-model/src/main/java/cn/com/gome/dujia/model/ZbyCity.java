package cn.com.gome.dujia.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "zby_city")
public class ZbyCity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "province_id")
    private Integer provinceId;

    @Column(name = "province_name")
    private String provinceName;

    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "city_py")
    private String cityPy;

    @Column(name = "city_pinyin")
    private String cityPinyin;

    @Column(name = "code")
    private String code;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "is_hot")
    private Boolean isHot;

    public void setId(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return this.id;
    }

    public void setProvinceId(Integer value) {
        this.provinceId = value;
    }

    public Integer getProvinceId() {
        return this.provinceId;
    }

    public void setProvinceName(String value) {
        this.provinceName = value;
    }

    public String getProvinceName() {
        return this.provinceName;
    }

    public void setCityId(Integer value) {
        this.cityId = value;
    }

    public Integer getCityId() {
        return this.cityId;
    }

    public void setCityName(String value) {
        this.cityName = value;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityPy(String value) {
        this.cityPy = value;
    }

    public String getCityPy() {
        return this.cityPy;
    }

    public void setCityPinyin(String value) {
        this.cityPinyin = value;
    }

    public String getCityPinyin() {
        return this.cityPinyin;
    }

    public void setCode(String value) {
        this.code = value;
    }

    public String getCode() {
        return this.code;
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

    public Boolean getIsHot() {
        return isHot;
    }

    public void setIsHot(Boolean isHot) {
        this.isHot = isHot;
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
        ZbyCity other = (ZbyCity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getProvinceId() == null ? other.getProvinceId() == null : this.getProvinceId().equals(other.getProvinceId()))
                && (this.getProvinceName() == null ? other.getProvinceName() == null : this.getProvinceName().equals(other.getProvinceName()))
                && (this.getCityId() == null ? other.getCityId() == null : this.getCityId().equals(other.getCityId()))
                && (this.getCityName() == null ? other.getCityName() == null : this.getCityName().equals(other.getCityName()))
                && (this.getCityPy() == null ? other.getCityPy() == null : this.getCityPy().equals(other.getCityPy()))
                && (this.getCityPinyin() == null ? other.getCityPinyin() == null : this.getCityPinyin().equals(other.getCityPinyin()))
                && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProvinceId() == null) ? 0 : getProvinceId().hashCode());
        result = prime * result + ((getProvinceName() == null) ? 0 : getProvinceName().hashCode());
        result = prime * result + ((getCityId() == null) ? 0 : getCityId().hashCode());
        result = prime * result + ((getCityName() == null) ? 0 : getCityName().hashCode());
        result = prime * result + ((getCityPy() == null) ? 0 : getCityPy().hashCode());
        result = prime * result + ((getCityPinyin() == null) ? 0 : getCityPinyin().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}
package cn.com.gome.dujia.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "dj_around_city")
public class AroundCity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id") 
	private Integer id;
	
	@Column(name="plateform") 
	private Integer plateform;
	
	@Column(name="type") 
	private Integer type;
	
	@Column(name="city_id") 
	private String cityId;
	
	@Column(name="city_name") 
	private String cityName;
	
	@Column(name="city_pinyin") 
	private String cityPinyin;
	
	@Column(name="around_city_id") 
	private String aroundCityId;
	
	@Column(name="around_city_name") 
	private String aroundCityName;
	
	@Column(name="around_city_pinyin") 
	private String aroundCityPinyin;
	
	@Column(name="around_city") 
	private Integer aroundCity;
	
	@Column(name="keyword") 
	private String keyword;
	
	@Column(name="image_url") 
	private String imageUrl;
	
	@Column(name="states") 
	private Boolean states;
	
	@Column(name="create_user") 
	private String createUser;
	
	@Column(name="create_time") 
	private Date createTime;
	
	@Column(name="update_user") 
	private String updateUser;
	
	@Column(name="update_time") 
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

	public void setCityPinyin(String value) {
		this.cityPinyin = value;
	}
	
	public String getCityPinyin() {
		return this.cityPinyin;
	}

	public void setAroundCityId(String value) {
		this.aroundCityId = value;
	}
	
	public String getAroundCityId() {
		return this.aroundCityId;
	}

	public void setAroundCityName(String value) {
		this.aroundCityName = value;
	}
	
	public String getAroundCityName() {
		return this.aroundCityName;
	}

	public void setAroundCityPinyin(String value) {
		this.aroundCityPinyin = value;
	}
	
	public String getAroundCityPinyin() {
		return this.aroundCityPinyin;
	}

	public void setAroundCity(Integer value) {
		this.aroundCity = value;
	}
	
	public Integer getAroundCity() {
		return this.aroundCity;
	}

	public void setKeyword(String value) {
		this.keyword = value;
	}
	
	public String getKeyword() {
		return this.keyword;
	}

	public void setImageUrl(String value) {
		this.imageUrl = value;
	}
	
	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setStates(Boolean value) {
		this.states = value;
	}
	
	public Boolean getStates() {
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
        AroundCity other = (AroundCity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPlateform() == null ? other.getPlateform() == null : this.getPlateform().equals(other.getPlateform()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getCityId() == null ? other.getCityId() == null : this.getCityId().equals(other.getCityId()))
            && (this.getCityName() == null ? other.getCityName() == null : this.getCityName().equals(other.getCityName()))
            && (this.getCityPinyin() == null ? other.getCityPinyin() == null : this.getCityPinyin().equals(other.getCityPinyin()))
            && (this.getAroundCityId() == null ? other.getAroundCityId() == null : this.getAroundCityId().equals(other.getAroundCityId()))
            && (this.getAroundCityName() == null ? other.getAroundCityName() == null : this.getAroundCityName().equals(other.getAroundCityName()))
            && (this.getAroundCityPinyin() == null ? other.getAroundCityPinyin() == null : this.getAroundCityPinyin().equals(other.getAroundCityPinyin()))
            && (this.getAroundCity() == null ? other.getAroundCity() == null : this.getAroundCity().equals(other.getAroundCity()))
            && (this.getKeyword() == null ? other.getKeyword() == null : this.getKeyword().equals(other.getKeyword()))
            && (this.getImageUrl() == null ? other.getImageUrl() == null : this.getImageUrl().equals(other.getImageUrl()))
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
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getCityId() == null) ? 0 : getCityId().hashCode());
        result = prime * result + ((getCityName() == null) ? 0 : getCityName().hashCode());
        result = prime * result + ((getCityPinyin() == null) ? 0 : getCityPinyin().hashCode());
        result = prime * result + ((getAroundCityId() == null) ? 0 : getAroundCityId().hashCode());
        result = prime * result + ((getAroundCityName() == null) ? 0 : getAroundCityName().hashCode());
        result = prime * result + ((getAroundCityPinyin() == null) ? 0 : getAroundCityPinyin().hashCode());
        result = prime * result + ((getAroundCity() == null) ? 0 : getAroundCity().hashCode());
        result = prime * result + ((getKeyword() == null) ? 0 : getKeyword().hashCode());
        result = prime * result + ((getImageUrl() == null) ? 0 : getImageUrl().hashCode());
        result = prime * result + ((getStates() == null) ? 0 : getStates().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}
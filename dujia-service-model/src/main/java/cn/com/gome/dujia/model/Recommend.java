package cn.com.gome.dujia.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "dj_recommend")
public class Recommend implements Serializable {
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
	
	@Column(name="page") 
	private String page;
	
	@Column(name="module") 
	private Integer module;
	
	@Column(name="city_id") 
	private String cityId;
	
	@Column(name="city_name") 
	private String cityName;
	
	@Column(name="target_city_id") 
	private String targetCityId;
	
	@Column(name="target_city_name") 
	private String targetCityName;
	
	@Column(name="keyword") 
	private String keyword;
	
	@Column(name="label") 
	private String label;
	
	@Column(name="image_url") 
	private String imageUrl;
	
	@Column(name="start_time") 
	private Date startTime;
	
	@Column(name="end_time") 
	private Date endTime;
	
	@Column(name="states") 
	private Integer states;
	
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

	public void setPage(String value) {
		this.page = value;
	}
	
	public String getPage() {
		return this.page;
	}

	public void setModule(Integer value) {
		this.module = value;
	}
	
	public Integer getModule() {
		return this.module;
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

	public void setTargetCityId(String value) {
		this.targetCityId = value;
	}
	
	public String getTargetCityId() {
		return this.targetCityId;
	}

	public void setTargetCityName(String value) {
		this.targetCityName = value;
	}
	
	public String getTargetCityName() {
		return this.targetCityName;
	}

	public void setKeyword(String value) {
		this.keyword = value;
	}
	
	public String getKeyword() {
		return this.keyword;
	}

	public void setLabel(String value) {
		this.label = value;
	}
	
	public String getLabel() {
		return this.label;
	}

	public void setImageUrl(String value) {
		this.imageUrl = value;
	}
	
	public String getImageUrl() {
		return this.imageUrl;
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
        Recommend other = (Recommend) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPlateform() == null ? other.getPlateform() == null : this.getPlateform().equals(other.getPlateform()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getPage() == null ? other.getPage() == null : this.getPage().equals(other.getPage()))
            && (this.getModule() == null ? other.getModule() == null : this.getModule().equals(other.getModule()))
            && (this.getCityId() == null ? other.getCityId() == null : this.getCityId().equals(other.getCityId()))
            && (this.getCityName() == null ? other.getCityName() == null : this.getCityName().equals(other.getCityName()))
            && (this.getTargetCityId() == null ? other.getTargetCityId() == null : this.getTargetCityId().equals(other.getTargetCityId()))
            && (this.getTargetCityName() == null ? other.getTargetCityName() == null : this.getTargetCityName().equals(other.getTargetCityName()))
            && (this.getKeyword() == null ? other.getKeyword() == null : this.getKeyword().equals(other.getKeyword()))
            && (this.getLabel() == null ? other.getLabel() == null : this.getLabel().equals(other.getLabel()))
            && (this.getImageUrl() == null ? other.getImageUrl() == null : this.getImageUrl().equals(other.getImageUrl()))
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
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getPage() == null) ? 0 : getPage().hashCode());
        result = prime * result + ((getModule() == null) ? 0 : getModule().hashCode());
        result = prime * result + ((getCityId() == null) ? 0 : getCityId().hashCode());
        result = prime * result + ((getCityName() == null) ? 0 : getCityName().hashCode());
        result = prime * result + ((getTargetCityId() == null) ? 0 : getTargetCityId().hashCode());
        result = prime * result + ((getTargetCityName() == null) ? 0 : getTargetCityName().hashCode());
        result = prime * result + ((getKeyword() == null) ? 0 : getKeyword().hashCode());
        result = prime * result + ((getLabel() == null) ? 0 : getLabel().hashCode());
        result = prime * result + ((getImageUrl() == null) ? 0 : getImageUrl().hashCode());
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
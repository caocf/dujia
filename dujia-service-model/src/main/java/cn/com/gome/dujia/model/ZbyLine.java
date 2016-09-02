package cn.com.gome.dujia.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "zby_line")
public class ZbyLine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id") 
	private Integer id;
	
	@Column(name="line_id") 
	@JsonProperty(value = "LineId")
	private String lineId;
	
	@Column(name="main_name")
	@JsonProperty(value = "MainName")
	private String mainName;
	
	@Column(name="province_name") 
	@JsonProperty(value = "ProvinceName")
	private String provinceName;
	
	@Column(name="city_name") 
	@JsonProperty(value = "CityName")
	private String cityName;
	
	@Column(name="package_id_list") 
	private String packageIdList;
	
	@Column(name="create_time") 
	private Date createTime;
	
	@Column(name="update_time") 
	private Date updateTime;
	
	@JsonProperty(value = "PackageIdList")
    @Transient
    private List<String> packageIdListRaw;


	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setLineId(String value) {
		this.lineId = value;
	}
	
	public String getLineId() {
		return this.lineId;
	}

	public void setMainName(String value) {
		this.mainName = value;
	}
	
	public String getMainName() {
		return this.mainName;
	}

	public void setProvinceName(String value) {
		this.provinceName = value;
	}
	
	public String getProvinceName() {
		return this.provinceName;
	}

	public void setCityName(String value) {
		this.cityName = value;
	}
	
	public String getCityName() {
		return this.cityName;
	}

	public void setPackageIdList(String value) {
		this.packageIdList = value;
	}
	
	public String getPackageIdList() {
		return this.packageIdList;
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

	public List<String> getPackageIdListRaw() {
		return packageIdListRaw;
	}

	public void setPackageIdListRaw(List<String> packageIdListRaw) {
		this.packageIdListRaw = packageIdListRaw;
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
        ZbyLine other = (ZbyLine) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLineId() == null ? other.getLineId() == null : this.getLineId().equals(other.getLineId()))
            && (this.getMainName() == null ? other.getMainName() == null : this.getMainName().equals(other.getMainName()))
            && (this.getProvinceName() == null ? other.getProvinceName() == null : this.getProvinceName().equals(other.getProvinceName()))
            && (this.getCityName() == null ? other.getCityName() == null : this.getCityName().equals(other.getCityName()))
            && (this.getPackageIdList() == null ? other.getPackageIdList() == null : this.getPackageIdList().equals(other.getPackageIdList()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLineId() == null) ? 0 : getLineId().hashCode());
        result = prime * result + ((getMainName() == null) ? 0 : getMainName().hashCode());
        result = prime * result + ((getProvinceName() == null) ? 0 : getProvinceName().hashCode());
        result = prime * result + ((getCityName() == null) ? 0 : getCityName().hashCode());
        result = prime * result + ((getPackageIdList() == null) ? 0 : getPackageIdList().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}
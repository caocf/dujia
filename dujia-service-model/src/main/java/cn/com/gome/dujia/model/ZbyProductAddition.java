package cn.com.gome.dujia.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "zby_product_addition")
public class ZbyProductAddition implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id") 
	private Integer id;
	
	@Column(name="package_detail_id") 
	private Integer packageDetailId;
	
	@Column(name="product_id") 
	private String productId;
	
	@Column(name="package_id") 
	private String packageId;
	
	@Column(name="name") 
	@JsonProperty(value = "Name")
	private String name;
	
	@Column(name="ser_type") 
	@JsonProperty(value = "SerType")
	private String serType;
	
	@Column(name="is_provider") 
	@JsonProperty(value = "IsProvider")
	private String isProvider;
	
	@Column(name="unit") 
	@JsonProperty(value = "Unit")
	private String unit;
	
	@Column(name="mark") 
	@JsonProperty(value = "Mark")
	private String mark;
	
	@Column(name="num") 
	@JsonProperty(value = "Num")
	private String num;
	
	@Column(name="create_time") 
	private Date createTime;
	
	@Column(name="update_time") 
	private Date updateTime;
	
	@Column(name="is_delate") 
	private Boolean isDelate;
	


	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setPackageDetailId(Integer value) {
		this.packageDetailId = value;
	}
	
	public Integer getPackageDetailId() {
		return this.packageDetailId;
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

	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}

	public void setSerType(String value) {
		this.serType = value;
	}
	
	public String getSerType() {
		return this.serType;
	}

	public void setIsProvider(String value) {
		this.isProvider = value;
	}
	
	public String getIsProvider() {
		return this.isProvider;
	}

	public void setUnit(String value) {
		this.unit = value;
	}
	
	public String getUnit() {
		return this.unit;
	}

	public void setMark(String value) {
		this.mark = value;
	}
	
	public String getMark() {
		return this.mark;
	}

	public void setNum(String value) {
		this.num = value;
	}
	
	public String getNum() {
		return this.num;
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

	public void setIsDelate(Boolean value) {
		this.isDelate = value;
	}
	
	public Boolean getIsDelate() {
		return this.isDelate;
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
        ZbyProductAddition other = (ZbyProductAddition) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPackageDetailId() == null ? other.getPackageDetailId() == null : this.getPackageDetailId().equals(other.getPackageDetailId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getPackageId() == null ? other.getPackageId() == null : this.getPackageId().equals(other.getPackageId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getSerType() == null ? other.getSerType() == null : this.getSerType().equals(other.getSerType()))
            && (this.getIsProvider() == null ? other.getIsProvider() == null : this.getIsProvider().equals(other.getIsProvider()))
            && (this.getUnit() == null ? other.getUnit() == null : this.getUnit().equals(other.getUnit()))
            && (this.getMark() == null ? other.getMark() == null : this.getMark().equals(other.getMark()))
            && (this.getNum() == null ? other.getNum() == null : this.getNum().equals(other.getNum()));
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPackageDetailId() == null) ? 0 : getPackageDetailId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getPackageId() == null) ? 0 : getPackageId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getSerType() == null) ? 0 : getSerType().hashCode());
        result = prime * result + ((getIsProvider() == null) ? 0 : getIsProvider().hashCode());
        result = prime * result + ((getUnit() == null) ? 0 : getUnit().hashCode());
        result = prime * result + ((getMark() == null) ? 0 : getMark().hashCode());
        result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());
        return result;
    }
}
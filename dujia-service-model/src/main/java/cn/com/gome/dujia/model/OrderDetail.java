package cn.com.gome.dujia.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "dj_order_detail")
public class OrderDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id") 
	private Integer id;
	
	@Column(name="order_id") 
	private String orderId;
	
	@Column(name="vender_order_id") 
	private String venderOrderId;
	
	@Column(name="resource_id") 
	private Integer resourceId;
	
	@Column(name="resource_type") 
	private Integer resourceType;
	
	@Column(name="resource_name") 
	private String resourceName;
	
	@Column(name="resource_pro_name")
	private String resourceProName;
	
	@Column(name="resource_count") 
	private Integer resourceCount;
	
	@Column(name="package_name") 
	private String packageName;
	
	@Column(name="use_start_time") 
	private Date useStartTime;
	
	@Column(name="use_end_time") 
	private Date useEndTime;
	
	@Column(name="create_time") 
	private Date createTime;
	
	@Column(name="update_time") 
	private Date updateTime;

    public OrderDetail(){

    }

    public OrderDetail(String orderId){
        this.orderId = orderId;
    }

	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setOrderId(String value) {
		this.orderId = value;
	}
	
	public String getOrderId() {
		return this.orderId;
	}

	public void setVenderOrderId(String value) {
		this.venderOrderId = value;
	}
	
	public String getVenderOrderId() {
		return this.venderOrderId;
	}

	public void setResourceId(Integer value) {
		this.resourceId = value;
	}
	
	public Integer getResourceId() {
		return this.resourceId;
	}

	public void setResourceType(Integer value) {
		this.resourceType = value;
	}
	
	public Integer getResourceType() {
		return this.resourceType;
	}

	public void setResourceName(String value) {
		this.resourceName = value;
	}
	
	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceCount(Integer value) {
		this.resourceCount = value;
	}
	
	public Integer getResourceCount() {
		return this.resourceCount;
	}

	public void setPackageName(String value) {
		this.packageName = value;
	}
	
	public String getPackageName() {
		return this.packageName;
	}

	public void setUseStartTime(Date value) {
		this.useStartTime = value;
	}
	
	public Date getUseStartTime() {
		return this.useStartTime;
	}

	public void setUseEndTime(Date value) {
		this.useEndTime = value;
	}
	
	public Date getUseEndTime() {
		return this.useEndTime;
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
	
	public String getResourceProName() {
		return resourceProName;
	}

	public void setResourceProName(String resourceProName) {
		this.resourceProName = resourceProName;
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
        OrderDetail other = (OrderDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getVenderOrderId() == null ? other.getVenderOrderId() == null : this.getVenderOrderId().equals(other.getVenderOrderId()))
            && (this.getResourceId() == null ? other.getResourceId() == null : this.getResourceId().equals(other.getResourceId()))
            && (this.getResourceType() == null ? other.getResourceType() == null : this.getResourceType().equals(other.getResourceType()))
            && (this.getResourceName() == null ? other.getResourceName() == null : this.getResourceName().equals(other.getResourceName()))
            && (this.getResourceCount() == null ? other.getResourceCount() == null : this.getResourceCount().equals(other.getResourceCount()))
            && (this.getPackageName() == null ? other.getPackageName() == null : this.getPackageName().equals(other.getPackageName()))
            && (this.getResourceProName() == null ? other.getResourceProName() == null : this.getResourceProName().equals(other.getResourceProName()))
            && (this.getUseStartTime() == null ? other.getUseStartTime() == null : this.getUseStartTime().equals(other.getUseStartTime()))
            && (this.getUseEndTime() == null ? other.getUseEndTime() == null : this.getUseEndTime().equals(other.getUseEndTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getVenderOrderId() == null) ? 0 : getVenderOrderId().hashCode());
        result = prime * result + ((getResourceId() == null) ? 0 : getResourceId().hashCode());
        result = prime * result + ((getResourceType() == null) ? 0 : getResourceType().hashCode());
        result = prime * result + ((getResourceName() == null) ? 0 : getResourceName().hashCode());
        result = prime * result + ((getResourceCount() == null) ? 0 : getResourceCount().hashCode());
        result = prime * result + ((getResourceProName() == null) ? 0 : getResourceProName().hashCode());
        result = prime * result + ((getPackageName() == null) ? 0 : getPackageName().hashCode());
        result = prime * result + ((getUseStartTime() == null) ? 0 : getUseStartTime().hashCode());
        result = prime * result + ((getUseEndTime() == null) ? 0 : getUseEndTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}
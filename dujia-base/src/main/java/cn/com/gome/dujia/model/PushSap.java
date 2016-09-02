package cn.com.gome.dujia.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "dj_push_sap")
public class PushSap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id") 
	private Integer id;
	
	@Column(name="order_id") 
	private String orderId;
	
	@Column(name="business_no") 
	private String businessNo;
	
	@Column(name="sap_type") 
	private Integer sapType;
	
	@Column(name="push_status") 
	private Integer pushStatus;
	
	@Column(name="push_num") 
	private Integer pushNum;
	
	@Column(name="create_time") 
	private Date createTime;
	
	@Column(name="update_time") 
	private Date updateTime;

    
    public PushSap(){
    	
    }

    public PushSap(String orderId,String businessNo,Integer sapType){
    	this.orderId    = orderId;
    	this.businessNo = businessNo;
    	this.sapType    = sapType;
    }
    
    public PushSap(String orderId,Integer sapType,String businessNo){
		this.orderId 	= orderId;
		this.sapType 	= sapType;
		this.businessNo = businessNo;
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

	public void setBusinessNo(String value) {
		this.businessNo = value;
	}
	
	public String getBusinessNo() {
		return this.businessNo;
	}

	public void setSapType(Integer value) {
		this.sapType = value;
	}
	
	public Integer getSapType() {
		return this.sapType;
	}

	public void setPushStatus(Integer value) {
		this.pushStatus = value;
	}
	
	public Integer getPushStatus() {
		return this.pushStatus;
	}

	public void setPushNum(Integer value) {
		this.pushNum = value;
	}
	
	public Integer getPushNum() {
		return this.pushNum;
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
        PushSap other = (PushSap) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getBusinessNo() == null ? other.getBusinessNo() == null : this.getBusinessNo().equals(other.getBusinessNo()))
            && (this.getSapType() == null ? other.getSapType() == null : this.getSapType().equals(other.getSapType()))
            && (this.getPushStatus() == null ? other.getPushStatus() == null : this.getPushStatus().equals(other.getPushStatus()))
            && (this.getPushNum() == null ? other.getPushNum() == null : this.getPushNum().equals(other.getPushNum()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getBusinessNo() == null) ? 0 : getBusinessNo().hashCode());
        result = prime * result + ((getSapType() == null) ? 0 : getSapType().hashCode());
        result = prime * result + ((getPushStatus() == null) ? 0 : getPushStatus().hashCode());
        result = prime * result + ((getPushNum() == null) ? 0 : getPushNum().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}
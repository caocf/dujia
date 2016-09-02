package cn.com.gome.dujia.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "dj_order_status_log")
public class OrderStatusLog implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id") 
	private Integer id;
	
	@Column(name="order_id") 
	private String orderId;
	
	@Column(name="is_show") 
	private Boolean isShow;
	
	@Column(name="order_status") 
	private Integer orderStatus;
	
	@Column(name="order_type") 
	private Integer orderType;
	
	@Column(name="details") 
	private String details;
	
	@Column(name="record_time") 
	private Date recordTime;
	
	@Column(name="update_time") 
	private Date updateTime;
    
    public OrderStatusLog(){
    	
    }
    
    public OrderStatusLog(String orderId,Integer orderType,Integer orderStatus,Date recordTime,Boolean isShow){
    	this.orderId     = orderId;
    	this.orderType   = orderType;
    	this.orderStatus = orderStatus;
    	this.recordTime  = recordTime;
    	this.isShow      = isShow;
    }
    
    public OrderStatusLog(String orderId,Boolean isShow,Integer orderStatus,Integer orderType,String details,Date recordTime,Date updateTime){
    	this.orderId 	 = orderId;
    	this.isShow  	 = isShow;
    	this.orderStatus = orderStatus;
    	this.orderType   = orderType;
    	this.details     = details;
    	this.recordTime  = recordTime;
    	this.updateTime  = updateTime;
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

	public void setIsShow(Boolean value) {
		this.isShow = value;
	}
	
	public Boolean getIsShow() {
		return this.isShow;
	}

	public void setOrderStatus(Integer value) {
		this.orderStatus = value;
	}
	
	public Integer getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderType(Integer value) {
		this.orderType = value;
	}
	
	public Integer getOrderType() {
		return this.orderType;
	}

	public void setDetails(String value) {
		this.details = value;
	}
	
	public String getDetails() {
		return this.details;
	}

	public void setRecordTime(Date value) {
		this.recordTime = value;
	}
	
	public Date getRecordTime() {
		return this.recordTime;
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
        OrderStatusLog other = (OrderStatusLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getIsShow() == null ? other.getIsShow() == null : this.getIsShow().equals(other.getIsShow()))
            && (this.getOrderStatus() == null ? other.getOrderStatus() == null : this.getOrderStatus().equals(other.getOrderStatus()))
            && (this.getOrderType() == null ? other.getOrderType() == null : this.getOrderType().equals(other.getOrderType()))
            && (this.getDetails() == null ? other.getDetails() == null : this.getDetails().equals(other.getDetails()))
            && (this.getRecordTime() == null ? other.getRecordTime() == null : this.getRecordTime().equals(other.getRecordTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getIsShow() == null) ? 0 : getIsShow().hashCode());
        result = prime * result + ((getOrderStatus() == null) ? 0 : getOrderStatus().hashCode());
        result = prime * result + ((getOrderType() == null) ? 0 : getOrderType().hashCode());
        result = prime * result + ((getDetails() == null) ? 0 : getDetails().hashCode());
        result = prime * result + ((getRecordTime() == null) ? 0 : getRecordTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}
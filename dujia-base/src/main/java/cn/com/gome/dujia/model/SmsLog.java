package cn.com.gome.dujia.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "dj_sms_log")
public class SmsLog implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id") 
	private Integer id;
	
	@Column(name="phone_number") 
	private String phoneNumber;
	
	@Column(name="system_type") 
	private Integer systemType;
	
	@Column(name="sms_type") 
	private Integer smsType;
	
	@Column(name="content") 
	private String content;
	
	@Column(name="order_id") 
	private String orderId;
	
	@Column(name="order_type") 
	private Integer orderType;
	
	@Column(name="status") 
	private Boolean status;
	
	@Column(name="user_id") 
	private String userId;
	
	@Column(name="operator") 
	private String operator;
	
	@Column(name="create_time") 
	private Date createTime;
	
	@Column(name="update_time") 
	private Date updateTime;
	


	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setPhoneNumber(String value) {
		this.phoneNumber = value;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setSystemType(Integer value) {
		this.systemType = value;
	}
	
	public Integer getSystemType() {
		return this.systemType;
	}

	public void setSmsType(Integer value) {
		this.smsType = value;
	}
	
	public Integer getSmsType() {
		return this.smsType;
	}

	public void setContent(String value) {
		this.content = value;
	}
	
	public String getContent() {
		return this.content;
	}

	public void setOrderId(String value) {
		this.orderId = value;
	}
	
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderType(Integer value) {
		this.orderType = value;
	}
	
	public Integer getOrderType() {
		return this.orderType;
	}

	public void setStatus(Boolean value) {
		this.status = value;
	}
	
	public Boolean getStatus() {
		return this.status;
	}

	public void setUserId(String value) {
		this.userId = value;
	}
	
	public String getUserId() {
		return this.userId;
	}

	public void setOperator(String value) {
		this.operator = value;
	}
	
	public String getOperator() {
		return this.operator;
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
        SmsLog other = (SmsLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPhoneNumber() == null ? other.getPhoneNumber() == null : this.getPhoneNumber().equals(other.getPhoneNumber()))
            && (this.getSystemType() == null ? other.getSystemType() == null : this.getSystemType().equals(other.getSystemType()))
            && (this.getSmsType() == null ? other.getSmsType() == null : this.getSmsType().equals(other.getSmsType()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getOrderType() == null ? other.getOrderType() == null : this.getOrderType().equals(other.getOrderType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getOperator() == null ? other.getOperator() == null : this.getOperator().equals(other.getOperator()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPhoneNumber() == null) ? 0 : getPhoneNumber().hashCode());
        result = prime * result + ((getSystemType() == null) ? 0 : getSystemType().hashCode());
        result = prime * result + ((getSmsType() == null) ? 0 : getSmsType().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getOrderType() == null) ? 0 : getOrderType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getOperator() == null) ? 0 : getOperator().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}
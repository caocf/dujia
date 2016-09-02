package cn.com.gome.dujia.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "dj_order_pay")
public class OrderPay implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id") 
	private String id;
	
	@Column(name="order_id") 
	private String orderId;
	
	@Column(name="cashier_no") 
	private String cashierNo;
	
	@Column(name="trans_no") 
	private String transNo;
	
	@Column(name="merchant_no") 
	private String merchantNo;
	
	@Column(name="pay_money") 
	private BigDecimal payMoney;
	
	@Column(name="pay_mode") 
	private String payMode;
	
	@Column(name="pay_mode_sap") 
	private String payModeSap;
	
	@Column(name="pay_bank") 
	private String payBank;
	
	@Column(name="pay_time") 
	private Date payTime;
	
	@Column(name="staging_count") 
	private Integer stagingCount;
	
	@Column(name="counter_fee") 
	private BigDecimal counterFee;
	
	@Column(name="is_repeat_pay") 
	private Boolean isRepeatPay;
	
	@Column(name="create_time") 
	private Date createTime;
	
	@Column(name="update_time") 
	private Date updateTime;
	

	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return this.id;
	}

	public void setOrderId(String value) {
		this.orderId = value;
	}
	
	public String getOrderId() {
		return this.orderId;
	}

	public void setCashierNo(String value) {
		this.cashierNo = value;
	}
	
	public String getCashierNo() {
		return this.cashierNo;
	}

	public void setTransNo(String value) {
		this.transNo = value;
	}
	
	public String getTransNo() {
		return this.transNo;
	}

	public void setMerchantNo(String value) {
		this.merchantNo = value;
	}
	
	public String getMerchantNo() {
		return this.merchantNo;
	}

	public void setPayMoney(BigDecimal value) {
		this.payMoney = value;
	}
	
	public BigDecimal getPayMoney() {
		return this.payMoney;
	}

	public void setPayMode(String value) {
		this.payMode = value;
	}
	
	public String getPayMode() {
		return this.payMode;
	}

	public void setPayModeSap(String value) {
		this.payModeSap = value;
	}
	
	public String getPayModeSap() {
		return this.payModeSap;
	}

	public void setPayBank(String value) {
		this.payBank = value;
	}
	
	public String getPayBank() {
		return this.payBank;
	}

	public void setPayTime(Date value) {
		this.payTime = value;
	}
	
	public Date getPayTime() {
		return this.payTime;
	}

	public void setStagingCount(Integer value) {
		this.stagingCount = value;
	}
	
	public Integer getStagingCount() {
		return this.stagingCount;
	}

	public void setCounterFee(BigDecimal value) {
		this.counterFee = value;
	}
	
	public BigDecimal getCounterFee() {
		return this.counterFee;
	}

	public void setIsRepeatPay(Boolean value) {
		this.isRepeatPay = value;
	}
	
	public Boolean getIsRepeatPay() {
		return this.isRepeatPay;
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
        OrderPay other = (OrderPay) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getCashierNo() == null ? other.getCashierNo() == null : this.getCashierNo().equals(other.getCashierNo()))
            && (this.getTransNo() == null ? other.getTransNo() == null : this.getTransNo().equals(other.getTransNo()))
            && (this.getMerchantNo() == null ? other.getMerchantNo() == null : this.getMerchantNo().equals(other.getMerchantNo()))
            && (this.getPayMoney() == null ? other.getPayMoney() == null : this.getPayMoney().equals(other.getPayMoney()))
            && (this.getPayMode() == null ? other.getPayMode() == null : this.getPayMode().equals(other.getPayMode()))
            && (this.getPayModeSap() == null ? other.getPayModeSap() == null : this.getPayModeSap().equals(other.getPayModeSap()))
            && (this.getPayBank() == null ? other.getPayBank() == null : this.getPayBank().equals(other.getPayBank()))
            && (this.getPayTime() == null ? other.getPayTime() == null : this.getPayTime().equals(other.getPayTime()))
            && (this.getStagingCount() == null ? other.getStagingCount() == null : this.getStagingCount().equals(other.getStagingCount()))
            && (this.getCounterFee() == null ? other.getCounterFee() == null : this.getCounterFee().equals(other.getCounterFee()))
            && (this.getIsRepeatPay() == null ? other.getIsRepeatPay() == null : this.getIsRepeatPay().equals(other.getIsRepeatPay()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getCashierNo() == null) ? 0 : getCashierNo().hashCode());
        result = prime * result + ((getTransNo() == null) ? 0 : getTransNo().hashCode());
        result = prime * result + ((getMerchantNo() == null) ? 0 : getMerchantNo().hashCode());
        result = prime * result + ((getPayMoney() == null) ? 0 : getPayMoney().hashCode());
        result = prime * result + ((getPayMode() == null) ? 0 : getPayMode().hashCode());
        result = prime * result + ((getPayModeSap() == null) ? 0 : getPayModeSap().hashCode());
        result = prime * result + ((getPayBank() == null) ? 0 : getPayBank().hashCode());
        result = prime * result + ((getPayTime() == null) ? 0 : getPayTime().hashCode());
        result = prime * result + ((getStagingCount() == null) ? 0 : getStagingCount().hashCode());
        result = prime * result + ((getCounterFee() == null) ? 0 : getCounterFee().hashCode());
        result = prime * result + ((getIsRepeatPay() == null) ? 0 : getIsRepeatPay().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}
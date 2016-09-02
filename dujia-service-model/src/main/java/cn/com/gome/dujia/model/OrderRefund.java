package cn.com.gome.dujia.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "dj_order_refund")
public class OrderRefund implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="id") 
	private Integer id;
	
	@Column(name="user_id") 
	private String userId;
	
	@Column(name="user_name") 
	private String userName;
	
	@Column(name="order_id") 
	private String orderId;
	
	@Column(name="vender_order_id") 
	private String venderOrderId;
	
	@Column(name="refund_id") 
	private String refundId;
	
	@Column(name="vender_refund_id") 
	private String venderRefundId;
	
	@Column(name="pay_amount") 
	private BigDecimal payAmount;
	
	@Column(name="refund_amount") 
	private BigDecimal refundAmount;
	
	@Column(name="compensate_amount") 
	private BigDecimal compensateAmount;
	
	@Column(name="penalty_amount") 
	private BigDecimal penaltyAmount;
	
	@Column(name="deduct_mount") 
	private BigDecimal deductMount;
	
	@Column(name="return_amount") 
	private BigDecimal returnAmount;
	
	@Column(name="pay_mode") 
	private String payMode;
	
	@Column(name="pay_mode_sap") 
	private String payModeSap;
	
	@Column(name="pay_time") 
	private Date payTime;
	
	@Column(name="trans_no") 
	private String transNo;
	
	@Column(name="audit_name") 
	private String auditName;
	
	@Column(name="audit_time") 
	private Date auditTime;
	
	@Column(name="audit_note") 
	private String auditNote;
	
	@Column(name="refund_form") 
	private Integer refundForm;
	
	@Column(name="refund_reason") 
	private Integer refundReason;
	
	@Column(name="refund_type") 
	private Integer refundType;
	
	@Column(name="refund_mode") 
	private Integer refundMode;
	
	@Column(name="refund_status") 
	private Integer refundStatus;
	
	@Column(name="refund_source") 
	private Integer refundSource;
	
	@Column(name="refund_bank_trans_num") 
	private String refundBankTransNum;
	
	@Column(name="finish_time") 
	private Date finishTime;
	
	@Column(name="refund_note") 
	private String refundNote;
	
	@Column(name="create_time") 
	private Date createTime;
	
	@Column(name="update_time") 
	private Date updateTime;

    /**
     * 退款状态
     */
	@Transient
    private String refundStatusString;
	

	public OrderRefund(){
    	
    }
    
    public OrderRefund(String orderId,String refundId){
    	this.orderId  = orderId;
    	this.refundId = refundId;
    }

    public OrderRefund(String userId, String orderId, String refundId){
        this.userId   = userId;
        this.orderId  = orderId;
        this.refundId = refundId;
    }

	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setUserId(String value) {
		this.userId = value;
	}
	
	public String getUserId() {
		return this.userId;
	}

	public void setUserName(String value) {
		this.userName = value;
	}
	
	public String getUserName() {
		return this.userName;
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

	public void setRefundId(String value) {
		this.refundId = value;
	}
	
	public String getRefundId() {
		return this.refundId;
	}

	public void setVenderRefundId(String value) {
		this.venderRefundId = value;
	}
	
	public String getVenderRefundId() {
		return this.venderRefundId;
	}

	public void setPayAmount(BigDecimal value) {
		this.payAmount = value;
	}
	
	public BigDecimal getPayAmount() {
		return this.payAmount;
	}

	public void setRefundAmount(BigDecimal value) {
		this.refundAmount = value;
	}
	
	public BigDecimal getRefundAmount() {
		return this.refundAmount;
	}

	public void setCompensateAmount(BigDecimal value) {
		this.compensateAmount = value;
	}
	
	public BigDecimal getCompensateAmount() {
		return this.compensateAmount;
	}

	public void setPenaltyAmount(BigDecimal value) {
		this.penaltyAmount = value;
	}
	
	public BigDecimal getPenaltyAmount() {
		return this.penaltyAmount;
	}

	public void setDeductMount(BigDecimal value) {
		this.deductMount = value;
	}
	
	public BigDecimal getDeductMount() {
		return this.deductMount;
	}

	public void setReturnAmount(BigDecimal value) {
		this.returnAmount = value;
	}
	
	public BigDecimal getReturnAmount() {
		return this.returnAmount;
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

	public void setPayTime(Date value) {
		this.payTime = value;
	}
	
	public Date getPayTime() {
		return this.payTime;
	}

	public void setTransNo(String value) {
		this.transNo = value;
	}
	
	public String getTransNo() {
		return this.transNo;
	}

	public void setAuditName(String value) {
		this.auditName = value;
	}
	
	public String getAuditName() {
		return this.auditName;
	}

	public void setAuditTime(Date value) {
		this.auditTime = value;
	}
	
	public Date getAuditTime() {
		return this.auditTime;
	}

	public void setAuditNote(String value) {
		this.auditNote = value;
	}
	
	public String getAuditNote() {
		return this.auditNote;
	}

	public void setRefundForm(Integer value) {
		this.refundForm = value;
	}
	
	public Integer getRefundForm() {
		return this.refundForm;
	}

	public void setRefundReason(Integer value) {
		this.refundReason = value;
	}
	
	public Integer getRefundReason() {
		return this.refundReason;
	}

	public void setRefundType(Integer value) {
		this.refundType = value;
	}
	
	public Integer getRefundType() {
		return this.refundType;
	}

	public void setRefundMode(Integer value) {
		this.refundMode = value;
	}
	
	public Integer getRefundMode() {
		return this.refundMode;
	}

	public void setRefundStatus(Integer value) {
		this.refundStatus = value;
	}
	
	public Integer getRefundStatus() {
		return this.refundStatus;
	}

	public void setRefundSource(Integer value) {
		this.refundSource = value;
	}
	
	public Integer getRefundSource() {
		return this.refundSource;
	}

	public void setRefundBankTransNum(String value) {
		this.refundBankTransNum = value;
	}
	
	public String getRefundBankTransNum() {
		return this.refundBankTransNum;
	}

	public void setFinishTime(Date value) {
		this.finishTime = value;
	}
	
	public Date getFinishTime() {
		return this.finishTime;
	}

	public void setRefundNote(String value) {
		this.refundNote = value;
	}
	
	public String getRefundNote() {
		return this.refundNote;
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

    public String getRefundStatusString() {
        return refundStatusString;
    }

    public void setRefundStatusString(String refundStatusString) {
        this.refundStatusString = refundStatusString;
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
        OrderRefund other = (OrderRefund) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getVenderOrderId() == null ? other.getVenderOrderId() == null : this.getVenderOrderId().equals(other.getVenderOrderId()))
            && (this.getRefundId() == null ? other.getRefundId() == null : this.getRefundId().equals(other.getRefundId()))
            && (this.getVenderRefundId() == null ? other.getVenderRefundId() == null : this.getVenderRefundId().equals(other.getVenderRefundId()))
            && (this.getPayAmount() == null ? other.getPayAmount() == null : this.getPayAmount().equals(other.getPayAmount()))
            && (this.getRefundAmount() == null ? other.getRefundAmount() == null : this.getRefundAmount().equals(other.getRefundAmount()))
            && (this.getCompensateAmount() == null ? other.getCompensateAmount() == null : this.getCompensateAmount().equals(other.getCompensateAmount()))
            && (this.getPenaltyAmount() == null ? other.getPenaltyAmount() == null : this.getPenaltyAmount().equals(other.getPenaltyAmount()))
            && (this.getDeductMount() == null ? other.getDeductMount() == null : this.getDeductMount().equals(other.getDeductMount()))
            && (this.getReturnAmount() == null ? other.getReturnAmount() == null : this.getReturnAmount().equals(other.getReturnAmount()))
            && (this.getPayMode() == null ? other.getPayMode() == null : this.getPayMode().equals(other.getPayMode()))
            && (this.getPayModeSap() == null ? other.getPayModeSap() == null : this.getPayModeSap().equals(other.getPayModeSap()))
            && (this.getPayTime() == null ? other.getPayTime() == null : this.getPayTime().equals(other.getPayTime()))
            && (this.getTransNo() == null ? other.getTransNo() == null : this.getTransNo().equals(other.getTransNo()))
            && (this.getAuditName() == null ? other.getAuditName() == null : this.getAuditName().equals(other.getAuditName()))
            && (this.getAuditTime() == null ? other.getAuditTime() == null : this.getAuditTime().equals(other.getAuditTime()))
            && (this.getAuditNote() == null ? other.getAuditNote() == null : this.getAuditNote().equals(other.getAuditNote()))
            && (this.getRefundForm() == null ? other.getRefundForm() == null : this.getRefundForm().equals(other.getRefundForm()))
            && (this.getRefundReason() == null ? other.getRefundReason() == null : this.getRefundReason().equals(other.getRefundReason()))
            && (this.getRefundType() == null ? other.getRefundType() == null : this.getRefundType().equals(other.getRefundType()))
            && (this.getRefundMode() == null ? other.getRefundMode() == null : this.getRefundMode().equals(other.getRefundMode()))
            && (this.getRefundStatus() == null ? other.getRefundStatus() == null : this.getRefundStatus().equals(other.getRefundStatus()))
            && (this.getRefundSource() == null ? other.getRefundSource() == null : this.getRefundSource().equals(other.getRefundSource()))
            && (this.getRefundBankTransNum() == null ? other.getRefundBankTransNum() == null : this.getRefundBankTransNum().equals(other.getRefundBankTransNum()))
            && (this.getFinishTime() == null ? other.getFinishTime() == null : this.getFinishTime().equals(other.getFinishTime()))
            && (this.getRefundNote() == null ? other.getRefundNote() == null : this.getRefundNote().equals(other.getRefundNote()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getVenderOrderId() == null) ? 0 : getVenderOrderId().hashCode());
        result = prime * result + ((getRefundId() == null) ? 0 : getRefundId().hashCode());
        result = prime * result + ((getVenderRefundId() == null) ? 0 : getVenderRefundId().hashCode());
        result = prime * result + ((getPayAmount() == null) ? 0 : getPayAmount().hashCode());
        result = prime * result + ((getRefundAmount() == null) ? 0 : getRefundAmount().hashCode());
        result = prime * result + ((getCompensateAmount() == null) ? 0 : getCompensateAmount().hashCode());
        result = prime * result + ((getPenaltyAmount() == null) ? 0 : getPenaltyAmount().hashCode());
        result = prime * result + ((getDeductMount() == null) ? 0 : getDeductMount().hashCode());
        result = prime * result + ((getReturnAmount() == null) ? 0 : getReturnAmount().hashCode());
        result = prime * result + ((getPayMode() == null) ? 0 : getPayMode().hashCode());
        result = prime * result + ((getPayModeSap() == null) ? 0 : getPayModeSap().hashCode());
        result = prime * result + ((getPayTime() == null) ? 0 : getPayTime().hashCode());
        result = prime * result + ((getTransNo() == null) ? 0 : getTransNo().hashCode());
        result = prime * result + ((getAuditName() == null) ? 0 : getAuditName().hashCode());
        result = prime * result + ((getAuditTime() == null) ? 0 : getAuditTime().hashCode());
        result = prime * result + ((getAuditNote() == null) ? 0 : getAuditNote().hashCode());
        result = prime * result + ((getRefundForm() == null) ? 0 : getRefundForm().hashCode());
        result = prime * result + ((getRefundReason() == null) ? 0 : getRefundReason().hashCode());
        result = prime * result + ((getRefundType() == null) ? 0 : getRefundType().hashCode());
        result = prime * result + ((getRefundMode() == null) ? 0 : getRefundMode().hashCode());
        result = prime * result + ((getRefundStatus() == null) ? 0 : getRefundStatus().hashCode());
        result = prime * result + ((getRefundSource() == null) ? 0 : getRefundSource().hashCode());
        result = prime * result + ((getRefundBankTransNum() == null) ? 0 : getRefundBankTransNum().hashCode());
        result = prime * result + ((getFinishTime() == null) ? 0 : getFinishTime().hashCode());
        result = prime * result + ((getRefundNote() == null) ? 0 : getRefundNote().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}
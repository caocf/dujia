package cn.com.gome.dujia.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "dj_order")
public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id") 
	private Integer id;
	
	@Column(name="user_id") 
	private String userId;
	
	@Column(name="user_name") 
	private String userName;
	
	@Column(name="order_id") 
	private String orderId;
	
	@Column(name="order_status") 
	private Integer orderStatus;
	
	@Column(name="order_pay") 
	private Integer orderPay;
	
	@Column(name="order_source") 
	private Integer orderSource;
	
	@Column(name="order_total_amount") 
	private BigDecimal orderTotalAmount;
	
	@Column(name="order_amount") 
	private BigDecimal orderAmount;
	
	@Column(name="order_time") 
	private Date orderTime;
	
	@Column(name="package_price") 
	private BigDecimal packagePrice;
	
	@Column(name="insurance_amount") 
	private BigDecimal insuranceAmount;
	
	@Column(name="compensate_amount") 
	private BigDecimal compensateAmount;
	
	@Column(name="penalty_amount") 
	private BigDecimal penaltyAmount;
	
	@Column(name="payment_amount") 
	private BigDecimal paymentAmount;
	
	@Column(name="product_id") 
	private String productId;
	
	@Column(name="product_name") 
	private String productName;
	
	@Column(name="package_id") 
	private String packageId;
	
	@Column(name="package_name") 
	private String packageName;
	
	@Column(name="buy_count") 
	private Integer buyCount;
	
	@Column(name="vender_id") 
	private String venderId;
	
	@Column(name="vender_order_id") 
	private String venderOrderId;
	
	@Column(name="vender_order_status") 
	private Integer venderOrderStatus;
	
	@Column(name="vender_pay_status") 
	private Integer venderPayStatus;
	
	@Column(name="lnvoice_type") 
	private Integer lnvoiceType;
	
	@Column(name="lnvoice_title") 
	private String lnvoiceTitle;
	
	@Column(name="lnvoice_content") 
	private String lnvoiceContent;
	
	@Column(name="business_type") 
	private Integer businessType;
	
	@Column(name="order_active") 
	private Integer orderActive;
	
	@Column(name="adult_num") 
	private Integer adultNum;
	
	@Column(name="child_num") 
	private Integer childNum;
	
	@Column(name="promotion_id") 
	private Integer promotionId;
	
	@Column(name="promotion_amount") 
	private BigDecimal promotionAmount;
	
	@Column(name="coupons_id") 
	private Integer couponsId;
	
	@Column(name="coupons_amount") 
	private BigDecimal couponsAmount;
	
	@Column(name="balance") 
	private BigDecimal balance;
	
	@Column(name="contacts_name") 
	private String contactsName;
	
	@Column(name="contacts_telphone") 
	private String contactsTelphone;
	
	@Column(name="contacts_email") 
	private String contactsEmail;
	
	@Column(name="travel_day") 
	private Integer travelDay;
	
	@Column(name="travel_start_time") 
	private Date travelStartTime;
	
	@Column(name="travel_end_time") 
	private Date travelEndTime;
	
	@Column(name="travel_people") 
	private String travelPeople;
	
	@Column(name="extended1") 
	private String extended1;
	
	@Column(name="extended2") 
	private String extended2;
	
	@Column(name="create_time") 
	private Date createTime;
	
	@Column(name="update_time") 
	private Date updateTime;

    /**
     * 订单支付剩余时间，前端显示使用(非库中字段)
     */
	@Transient
    private long surplusTime;
	
	
	public Order() {
		
	}
	
	public Order(String orderId) {
		this.orderId = orderId;
	}

    public Order(String userId, String orderId) {
        this.orderId = orderId;
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

	public void setOrderStatus(Integer value) {
		this.orderStatus = value;
	}
	
	public Integer getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderPay(Integer value) {
		this.orderPay = value;
	}
	
	public Integer getOrderPay() {
		return this.orderPay;
	}

	public void setOrderSource(Integer value) {
		this.orderSource = value;
	}
	
	public Integer getOrderSource() {
		return this.orderSource;
	}

	public void setOrderTotalAmount(BigDecimal value) {
		this.orderTotalAmount = value;
	}
	
	public BigDecimal getOrderTotalAmount() {
		return this.orderTotalAmount;
	}

	public void setOrderAmount(BigDecimal value) {
		this.orderAmount = value;
	}
	
	public BigDecimal getOrderAmount() {
		return this.orderAmount;
	}

	public void setOrderTime(Date value) {
		this.orderTime = value;
	}
	
	public Date getOrderTime() {
		return this.orderTime;
	}

	public void setPackagePrice(BigDecimal value) {
		this.packagePrice = value;
	}
	
	public BigDecimal getPackagePrice() {
		return this.packagePrice;
	}

	public void setInsuranceAmount(BigDecimal value) {
		this.insuranceAmount = value;
	}
	
	public BigDecimal getInsuranceAmount() {
		return this.insuranceAmount;
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

	public void setPaymentAmount(BigDecimal value) {
		this.paymentAmount = value;
	}
	
	public BigDecimal getPaymentAmount() {
		return this.paymentAmount;
	}

	public void setProductId(String value) {
		this.productId = value;
	}
	
	public String getProductId() {
		return this.productId;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setPackageId(String value) {
		this.packageId = value;
	}
	
	public String getPackageId() {
		return this.packageId;
	}

	public void setPackageName(String value) {
		this.packageName = value;
	}
	
	public String getPackageName() {
		return this.packageName;
	}

	public void setBuyCount(Integer value) {
		this.buyCount = value;
	}
	
	public Integer getBuyCount() {
		return this.buyCount;
	}

	public void setVenderId(String value) {
		this.venderId = value;
	}
	
	public String getVenderId() {
		return this.venderId;
	}

	public void setVenderOrderId(String value) {
		this.venderOrderId = value;
	}
	
	public String getVenderOrderId() {
		return this.venderOrderId;
	}

	public void setVenderOrderStatus(Integer value) {
		this.venderOrderStatus = value;
	}
	
	public Integer getVenderOrderStatus() {
		return this.venderOrderStatus;
	}

	public void setVenderPayStatus(Integer value) {
		this.venderPayStatus = value;
	}
	
	public Integer getVenderPayStatus() {
		return this.venderPayStatus;
	}

	public void setLnvoiceType(Integer value) {
		this.lnvoiceType = value;
	}
	
	public Integer getLnvoiceType() {
		return this.lnvoiceType;
	}

	public void setLnvoiceTitle(String value) {
		this.lnvoiceTitle = value;
	}
	
	public String getLnvoiceTitle() {
		return this.lnvoiceTitle;
	}

	public void setLnvoiceContent(String value) {
		this.lnvoiceContent = value;
	}
	
	public String getLnvoiceContent() {
		return this.lnvoiceContent;
	}

	public void setBusinessType(Integer value) {
		this.businessType = value;
	}
	
	public Integer getBusinessType() {
		return this.businessType;
	}

	public void setOrderActive(Integer value) {
		this.orderActive = value;
	}
	
	public Integer getOrderActive() {
		return this.orderActive;
	}

	public void setAdultNum(Integer value) {
		this.adultNum = value;
	}
	
	public Integer getAdultNum() {
		return this.adultNum;
	}

	public void setChildNum(Integer value) {
		this.childNum = value;
	}
	
	public Integer getChildNum() {
		return this.childNum;
	}

	public void setPromotionId(Integer value) {
		this.promotionId = value;
	}
	
	public Integer getPromotionId() {
		return this.promotionId;
	}

	public void setPromotionAmount(BigDecimal value) {
		this.promotionAmount = value;
	}
	
	public BigDecimal getPromotionAmount() {
		return this.promotionAmount;
	}

	public void setCouponsId(Integer value) {
		this.couponsId = value;
	}
	
	public Integer getCouponsId() {
		return this.couponsId;
	}

	public void setCouponsAmount(BigDecimal value) {
		this.couponsAmount = value;
	}
	
	public BigDecimal getCouponsAmount() {
		return this.couponsAmount;
	}

	public void setBalance(BigDecimal value) {
		this.balance = value;
	}
	
	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setContactsName(String value) {
		this.contactsName = value;
	}
	
	public String getContactsName() {
		return this.contactsName;
	}

	public void setContactsTelphone(String value) {
		this.contactsTelphone = value;
	}
	
	public String getContactsTelphone() {
		return this.contactsTelphone;
	}

	public String getContactsEmail() {
		return contactsEmail;
	}

	public void setContactsEmail(String contactsEmail) {
		this.contactsEmail = contactsEmail;
	}

	public void setTravelDay(Integer value) {
		this.travelDay = value;
	}
	
	public Integer getTravelDay() {
		return this.travelDay;
	}

	public void setTravelStartTime(Date value) {
		this.travelStartTime = value;
	}
	
	public Date getTravelStartTime() {
		return this.travelStartTime;
	}

	public void setTravelEndTime(Date value) {
		this.travelEndTime = value;
	}
	
	public Date getTravelEndTime() {
		return this.travelEndTime;
	}

	public void setTravelPeople(String value) {
		this.travelPeople = value;
	}
	
	public String getTravelPeople() {
		return this.travelPeople;
	}

	public void setExtended1(String value) {
		this.extended1 = value;
	}
	
	public String getExtended1() {
		return this.extended1;
	}

	public void setExtended2(String value) {
		this.extended2 = value;
	}
	
	public String getExtended2() {
		return this.extended2;
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

    public long getSurplusTime() {
        return surplusTime;
    }

    public void setSurplusTime(long surplusTime) {
        this.surplusTime = surplusTime;
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
        Order other = (Order) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getOrderStatus() == null ? other.getOrderStatus() == null : this.getOrderStatus().equals(other.getOrderStatus()))
            && (this.getOrderPay() == null ? other.getOrderPay() == null : this.getOrderPay().equals(other.getOrderPay()))
            && (this.getOrderSource() == null ? other.getOrderSource() == null : this.getOrderSource().equals(other.getOrderSource()))
            && (this.getOrderTotalAmount() == null ? other.getOrderTotalAmount() == null : this.getOrderTotalAmount().equals(other.getOrderTotalAmount()))
            && (this.getOrderAmount() == null ? other.getOrderAmount() == null : this.getOrderAmount().equals(other.getOrderAmount()))
            && (this.getOrderTime() == null ? other.getOrderTime() == null : this.getOrderTime().equals(other.getOrderTime()))
            && (this.getPackagePrice() == null ? other.getPackagePrice() == null : this.getPackagePrice().equals(other.getPackagePrice()))
            && (this.getInsuranceAmount() == null ? other.getInsuranceAmount() == null : this.getInsuranceAmount().equals(other.getInsuranceAmount()))
            && (this.getCompensateAmount() == null ? other.getCompensateAmount() == null : this.getCompensateAmount().equals(other.getCompensateAmount()))
            && (this.getPenaltyAmount() == null ? other.getPenaltyAmount() == null : this.getPenaltyAmount().equals(other.getPenaltyAmount()))
            && (this.getPaymentAmount() == null ? other.getPaymentAmount() == null : this.getPaymentAmount().equals(other.getPaymentAmount()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getPackageId() == null ? other.getPackageId() == null : this.getPackageId().equals(other.getPackageId()))
            && (this.getPackageName() == null ? other.getPackageName() == null : this.getPackageName().equals(other.getPackageName()))
            && (this.getBuyCount() == null ? other.getBuyCount() == null : this.getBuyCount().equals(other.getBuyCount()))
            && (this.getVenderId() == null ? other.getVenderId() == null : this.getVenderId().equals(other.getVenderId()))
            && (this.getVenderOrderId() == null ? other.getVenderOrderId() == null : this.getVenderOrderId().equals(other.getVenderOrderId()))
            && (this.getVenderOrderStatus() == null ? other.getVenderOrderStatus() == null : this.getVenderOrderStatus().equals(other.getVenderOrderStatus()))
            && (this.getVenderPayStatus() == null ? other.getVenderPayStatus() == null : this.getVenderPayStatus().equals(other.getVenderPayStatus()))
            && (this.getLnvoiceType() == null ? other.getLnvoiceType() == null : this.getLnvoiceType().equals(other.getLnvoiceType()))
            && (this.getLnvoiceTitle() == null ? other.getLnvoiceTitle() == null : this.getLnvoiceTitle().equals(other.getLnvoiceTitle()))
            && (this.getLnvoiceContent() == null ? other.getLnvoiceContent() == null : this.getLnvoiceContent().equals(other.getLnvoiceContent()))
            && (this.getBusinessType() == null ? other.getBusinessType() == null : this.getBusinessType().equals(other.getBusinessType()))
            && (this.getOrderActive() == null ? other.getOrderActive() == null : this.getOrderActive().equals(other.getOrderActive()))
            && (this.getAdultNum() == null ? other.getAdultNum() == null : this.getAdultNum().equals(other.getAdultNum()))
            && (this.getChildNum() == null ? other.getChildNum() == null : this.getChildNum().equals(other.getChildNum()))
            && (this.getPromotionId() == null ? other.getPromotionId() == null : this.getPromotionId().equals(other.getPromotionId()))
            && (this.getPromotionAmount() == null ? other.getPromotionAmount() == null : this.getPromotionAmount().equals(other.getPromotionAmount()))
            && (this.getCouponsId() == null ? other.getCouponsId() == null : this.getCouponsId().equals(other.getCouponsId()))
            && (this.getCouponsAmount() == null ? other.getCouponsAmount() == null : this.getCouponsAmount().equals(other.getCouponsAmount()))
            && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getContactsName() == null ? other.getContactsName() == null : this.getContactsName().equals(other.getContactsName()))
            && (this.getContactsTelphone() == null ? other.getContactsTelphone() == null : this.getContactsTelphone().equals(other.getContactsTelphone()))
            && (this.getContactsEmail() == null ? other.getContactsEmail() == null : this.getContactsEmail().equals(other.getContactsEmail()))
            && (this.getTravelDay() == null ? other.getTravelDay() == null : this.getTravelDay().equals(other.getTravelDay()))
            && (this.getTravelStartTime() == null ? other.getTravelStartTime() == null : this.getTravelStartTime().equals(other.getTravelStartTime()))
            && (this.getTravelEndTime() == null ? other.getTravelEndTime() == null : this.getTravelEndTime().equals(other.getTravelEndTime()))
            && (this.getTravelPeople() == null ? other.getTravelPeople() == null : this.getTravelPeople().equals(other.getTravelPeople()))
            && (this.getExtended1() == null ? other.getExtended1() == null : this.getExtended1().equals(other.getExtended1()))
            && (this.getExtended2() == null ? other.getExtended2() == null : this.getExtended2().equals(other.getExtended2()))
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
        result = prime * result + ((getOrderStatus() == null) ? 0 : getOrderStatus().hashCode());
        result = prime * result + ((getOrderPay() == null) ? 0 : getOrderPay().hashCode());
        result = prime * result + ((getOrderSource() == null) ? 0 : getOrderSource().hashCode());
        result = prime * result + ((getOrderTotalAmount() == null) ? 0 : getOrderTotalAmount().hashCode());
        result = prime * result + ((getOrderAmount() == null) ? 0 : getOrderAmount().hashCode());
        result = prime * result + ((getOrderTime() == null) ? 0 : getOrderTime().hashCode());
        result = prime * result + ((getPackagePrice() == null) ? 0 : getPackagePrice().hashCode());
        result = prime * result + ((getInsuranceAmount() == null) ? 0 : getInsuranceAmount().hashCode());
        result = prime * result + ((getCompensateAmount() == null) ? 0 : getCompensateAmount().hashCode());
        result = prime * result + ((getPenaltyAmount() == null) ? 0 : getPenaltyAmount().hashCode());
        result = prime * result + ((getPaymentAmount() == null) ? 0 : getPaymentAmount().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getPackageId() == null) ? 0 : getPackageId().hashCode());
        result = prime * result + ((getPackageName() == null) ? 0 : getPackageName().hashCode());
        result = prime * result + ((getBuyCount() == null) ? 0 : getBuyCount().hashCode());
        result = prime * result + ((getVenderId() == null) ? 0 : getVenderId().hashCode());
        result = prime * result + ((getVenderOrderId() == null) ? 0 : getVenderOrderId().hashCode());
        result = prime * result + ((getVenderOrderStatus() == null) ? 0 : getVenderOrderStatus().hashCode());
        result = prime * result + ((getVenderPayStatus() == null) ? 0 : getVenderPayStatus().hashCode());
        result = prime * result + ((getLnvoiceType() == null) ? 0 : getLnvoiceType().hashCode());
        result = prime * result + ((getLnvoiceTitle() == null) ? 0 : getLnvoiceTitle().hashCode());
        result = prime * result + ((getLnvoiceContent() == null) ? 0 : getLnvoiceContent().hashCode());
        result = prime * result + ((getBusinessType() == null) ? 0 : getBusinessType().hashCode());
        result = prime * result + ((getOrderActive() == null) ? 0 : getOrderActive().hashCode());
        result = prime * result + ((getAdultNum() == null) ? 0 : getAdultNum().hashCode());
        result = prime * result + ((getChildNum() == null) ? 0 : getChildNum().hashCode());
        result = prime * result + ((getPromotionId() == null) ? 0 : getPromotionId().hashCode());
        result = prime * result + ((getPromotionAmount() == null) ? 0 : getPromotionAmount().hashCode());
        result = prime * result + ((getCouponsId() == null) ? 0 : getCouponsId().hashCode());
        result = prime * result + ((getCouponsAmount() == null) ? 0 : getCouponsAmount().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getContactsName() == null) ? 0 : getContactsName().hashCode());
        result = prime * result + ((getContactsTelphone() == null) ? 0 : getContactsTelphone().hashCode());
        result = prime * result + ((getContactsEmail() == null) ? 0 : getContactsEmail().hashCode());
        result = prime * result + ((getTravelDay() == null) ? 0 : getTravelDay().hashCode());
        result = prime * result + ((getTravelStartTime() == null) ? 0 : getTravelStartTime().hashCode());
        result = prime * result + ((getTravelEndTime() == null) ? 0 : getTravelEndTime().hashCode());
        result = prime * result + ((getTravelPeople() == null) ? 0 : getTravelPeople().hashCode());
        result = prime * result + ((getExtended1() == null) ? 0 : getExtended1().hashCode());
        result = prime * result + ((getExtended2() == null) ? 0 : getExtended2().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}
package cn.com.gome.dujia.ws.client.rb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
/**
 * <p>Java class for refundBankTask complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="refundBankTask">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bankType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buid" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="cardNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgSysno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="payOrderNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="payTradeNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="refundAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="refundDetailsId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="refundMerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="refundNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="refundState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="refundTradeNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sendCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="tag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taskState" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "refundBankTask", propOrder = {
	"orderNo",
	"payOrderNo",	
    "refundNo",
    "refundAmount",
    "bankType",
    "buid",
    "payTime",
    "payTradeNo",
    "payAmt",
    "cardNumber",
    "orgSysno",
    "refundDetailsId",
    "refundMerId"
})
public class RefundBankTask {
	
	/**
	 * 订单编号
	 */
	protected String orderNo;
	
	/**
	 * 支付订单编号
	 */
	protected String payOrderNo;
	
	/**
	 * 退款编号ID
	 */
	protected String refundNo;
	
	/**
	 * 退款金额
	 */
	protected Double refundAmount;
	
    /**
     * 原路返回平台类型
     */
	protected String bankType;
    
    
    /**
     * 销售渠道代码(退款单的来源平台代码固定值，rb提供)
     */
	protected Integer buid;
	
	/**
	 * 订单支付时间
	 */
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar payTime;
    
    /**
     * 支付交易流水号
     */
    protected String payTradeNo;
    
    /**
     * 支付金额
     */
    protected Double payAmt;
    
    /**
     * 卡号(线下退款用)
     */
    protected String cardNumber;
    
    /**
     * 原系统参考号(线下退款用)
     */
    protected String orgSysno;
    /**
     * 退款明细
     */
    protected String refundDetailsId;
    
    /**
     * 退款商户号
     */
    protected String refundMerId;
    

    /**
     * Gets the value of the bankType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankType() {
        return bankType;
    }

    /**
     * Sets the value of the bankType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankType(String value) {
        this.bankType = value;
    }

    /**
     * Gets the value of the buid property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBuid() {
        return buid;
    }

    /**
     * Sets the value of the buid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBuid(Integer value) {
        this.buid = value;
    }

    /**
     * Gets the value of the cardNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the value of the cardNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardNumber(String value) {
        this.cardNumber = value;
    }

    /**
     * Gets the value of the orderNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * Sets the value of the orderNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderNo(String value) {
        this.orderNo = value;
    }

    /**
     * Gets the value of the orgSysno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgSysno() {
        return orgSysno;
    }

    /**
     * Sets the value of the orgSysno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgSysno(String value) {
        this.orgSysno = value;
    }

    /**
     * Gets the value of the payAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPayAmt() {
        return payAmt;
    }

    /**
     * Sets the value of the payAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPayAmt(Double value) {
        this.payAmt = value;
    }

    /**
     * Gets the value of the payOrderNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayOrderNo() {
        return payOrderNo;
    }

    /**
     * Sets the value of the payOrderNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayOrderNo(String value) {
        this.payOrderNo = value;
    }

    /**
     * Gets the value of the payTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPayTime() {
        return payTime;
    }

    /**
     * Sets the value of the payTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPayTime(XMLGregorianCalendar value) {
        this.payTime = value;
    }

    /**
     * Gets the value of the payTradeNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayTradeNo() {
        return payTradeNo;
    }

    /**
     * Sets the value of the payTradeNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayTradeNo(String value) {
        this.payTradeNo = value;
    }

    /**
     * Gets the value of the refundAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getRefundAmount() {
        return refundAmount;
    }

    /**
     * Sets the value of the refundAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRefundAmount(Double value) {
        this.refundAmount = value;
    }

    /**
     * Gets the value of the refundDetailsId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefundDetailsId() {
        return refundDetailsId;
    }

    /**
     * Sets the value of the refundDetailsId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefundDetailsId(String value) {
        this.refundDetailsId = value;
    }

    /**
     * Gets the value of the refundMerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefundMerId() {
        return refundMerId;
    }

    /**
     * Sets the value of the refundMerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefundMerId(String value) {
        this.refundMerId = value;
    }

    /**
     * Gets the value of the refundNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefundNo() {
        return refundNo;
    }

    /**
     * Sets the value of the refundNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefundNo(String value) {
        this.refundNo = value;
    }
}

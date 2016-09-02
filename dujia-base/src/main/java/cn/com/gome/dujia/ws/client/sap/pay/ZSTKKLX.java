package cn.com.gome.dujia.ws.client.sap.pay;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
/**
 * <p>Java class for ZST_KKLX complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZST_KKLX">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ZKKLX" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZKKJE" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="11"/>
 *               &lt;fractionDigits value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="WAERK" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="5"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZST_KKLX", propOrder = {
    "zkklx",
    "zkkje",
    "waerk"
})
public class ZSTKKLX {

    @XmlElement(name = "ZKKLX")
    protected String zkklx;
    
    @XmlElement(name = "ZKKJE")
    protected BigDecimal zkkje;
    
    @XmlElement(name = "WAERK")
    protected String waerk;

    /**
     * Gets the value of the zkklx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZKKLX() {
        return zkklx;
    }

    /**
     * Sets the value of the zkklx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZKKLX(String value) {
        this.zkklx = value;
    }

    /**
     * Gets the value of the zkkje property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getZKKJE() {
        return zkkje;
    }

    /**
     * Sets the value of the zkkje property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setZKKJE(BigDecimal value) {
        this.zkkje = value;
    }

    /**
     * Gets the value of the waerk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWAERK() {
        return waerk;
    }

    /**
     * Sets the value of the waerk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWAERK(String value) {
        this.waerk = value;
    }

}

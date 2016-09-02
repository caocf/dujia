package cn.com.gome.dujia.ws.client.sap.pay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
/**
 * <p>Java class for ZLOMATNRTOOA complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZLOMATNRTOOA">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="JYID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ZKEY" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="32"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZJGBS" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="5"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZFKMS" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="220"/>
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
@XmlType(name = "ZLOMATNRTOOA", propOrder = {
    "jyid",
    "zkey",
    "zjgbs",
    "zfkms"
})
public class ZLOMATNRTOOA {

    @XmlElement(name = "JYID")
    protected String jyid;
    
    @XmlElement(name = "ZKEY")
    protected String zkey;
    
    @XmlElement(name = "ZJGBS")
    protected String zjgbs;
    
    @XmlElement(name = "ZFKMS")
    protected String zfkms;

    /**
     * Gets the value of the jyid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJYID() {
        return jyid;
    }

    /**
     * Sets the value of the jyid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJYID(String value) {
        this.jyid = value;
    }

    /**
     * Gets the value of the zkey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZKEY() {
        return zkey;
    }

    /**
     * Sets the value of the zkey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZKEY(String value) {
        this.zkey = value;
    }

    /**
     * Gets the value of the zjgbs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZJGBS() {
        return zjgbs;
    }

    /**
     * Sets the value of the zjgbs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZJGBS(String value) {
        this.zjgbs = value;
    }

    /**
     * Gets the value of the zfkms property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZFKMS() {
        return zfkms;
    }

    /**
     * Sets the value of the zfkms property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZFKMS(String value) {
        this.zfkms = value;
    }

}

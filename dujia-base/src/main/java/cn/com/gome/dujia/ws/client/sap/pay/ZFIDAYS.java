package cn.com.gome.dujia.ws.client.sap.pay;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
/**
 * <p>Java class for ZFI_DAYS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZFI_DAYS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ZKEY" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="32"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="MANDT" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="VBELN" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZJYCK" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZPAYID" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="16"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZYWLX" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BUDAT" type="{urn:sap-com:document:sap:rfc:functions}date" minOccurs="0"/>
 *         &lt;element name="BLDAT" type="{urn:sap-com:document:sap:rfc:functions}date" minOccurs="0"/>
 *         &lt;element name="BUDATS" type="{urn:sap-com:document:sap:rfc:functions}date" minOccurs="0"/>
 *         &lt;element name="ZJSRQ" type="{urn:sap-com:document:sap:rfc:functions}date" minOccurs="0"/>
 *         &lt;element name="ZSAUT" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="4"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SEGMENT" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZSKFS" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZYSZK" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="15"/>
 *               &lt;fractionDigits value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZDSFM" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="8"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZKHBH" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="32"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZKHMC" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="40"/>
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
 *         &lt;element name="ZMARK" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZJZPZ" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZJZPG" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZSKBZ" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZYSBZ" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZDFPZ" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZSKPZ" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZTZPZ" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZAUDIT" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZDZRQ" type="{urn:sap-com:document:sap:rfc:functions}date" minOccurs="0"/>
 *         &lt;element name="ZBEIZ" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="220"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZDZPZ" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="GJAHR" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="4"/>
 *               &lt;pattern value="\d+"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="MONAT" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *               &lt;pattern value="\d+"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZVBELN" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZTKDH" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZDDYS" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="15"/>
 *               &lt;fractionDigits value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZSKLX" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZYYLM" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZHZSD" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="32"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZXSLY" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZCKDH" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="35"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZZDDH" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZJNJE" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="13"/>
 *               &lt;fractionDigits value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZKKZJE" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="15"/>
 *               &lt;fractionDigits value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZZFDH" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="16"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZKJZF" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CDATE" type="{urn:sap-com:document:sap:rfc:functions}date" minOccurs="0"/>
 *         &lt;element name="CTIME" type="{urn:sap-com:document:sap:rfc:functions}time" minOccurs="0"/>
 *         &lt;element name="CUSER" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="12"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="EDATE" type="{urn:sap-com:document:sap:rfc:functions}date" minOccurs="0"/>
 *         &lt;element name="ETIME" type="{urn:sap-com:document:sap:rfc:functions}time" minOccurs="0"/>
 *         &lt;element name="EUSER" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="12"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ZKKBT" type="{urn:sap-com:document:sap:rfc:functions}ZKKB" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZFI_DAYS", propOrder = {
    "zkey",
    "mandt",
    "vbeln",
    "zjyck",
    "zpayid",
    "zywlx",
    "budat",
    "bldat",
    "budats",
    "zjsrq",
    "zsaut",
    "segment",
    "zskfs",
    "zyszk",
    "zdsfm",
    "zkhbh",
    "zkhmc",
    "waerk",
    "zmark",
    "zjzpz",
    "zjzpg",
    "zskbz",
    "zysbz",
    "zdfpz",
    "zskpz",
    "ztzpz",
    "zaudit",
    "zdzrq",
    "zbeiz",
    "zdzpz",
    "gjahr",
    "monat",
    "zvbeln",
    "ztkdh",
    "zddys",
    "zsklx",
    "zyylm",
    "zhzsd",
    "zxsly",
    "zckdh",
    "zzddh",
    "zjnje",
    "zkkzje",
    "zzfdh",
    "zkjzf",
    "cdate",
    "ctime",
    "cuser",
    "edate",
    "etime",
    "euser",
    "zkkbt"
})
public class ZFIDAYS {

    @XmlElement(name = "ZKEY")
    protected String zkey;
    @XmlElement(name = "MANDT")
    protected String mandt;
    @XmlElement(name = "VBELN")
    protected String vbeln;
    @XmlElement(name = "ZJYCK")
    protected String zjyck;
    @XmlElement(name = "ZPAYID")
    protected String zpayid;
    @XmlElement(name = "ZYWLX")
    protected String zywlx;
    @XmlElement(name = "BUDAT")
    protected String budat;
    @XmlElement(name = "BLDAT")
    protected String bldat;
    @XmlElement(name = "BUDATS")
    protected String budats;
    @XmlElement(name = "ZJSRQ")
    protected String zjsrq;
    @XmlElement(name = "ZSAUT")
    protected String zsaut;
    @XmlElement(name = "SEGMENT")
    protected String segment;
    @XmlElement(name = "ZSKFS")
    protected String zskfs;
    @XmlElement(name = "ZYSZK")
    protected BigDecimal zyszk;
    @XmlElement(name = "ZDSFM")
    protected String zdsfm;
    @XmlElement(name = "ZKHBH")
    protected String zkhbh;
    @XmlElement(name = "ZKHMC")
    protected String zkhmc;
    @XmlElement(name = "WAERK")
    protected String waerk;
    @XmlElement(name = "ZMARK")
    protected String zmark;
    @XmlElement(name = "ZJZPZ")
    protected String zjzpz;
    @XmlElement(name = "ZJZPG")
    protected String zjzpg;
    @XmlElement(name = "ZSKBZ")
    protected String zskbz;
    @XmlElement(name = "ZYSBZ")
    protected String zysbz;
    @XmlElement(name = "ZDFPZ")
    protected String zdfpz;
    @XmlElement(name = "ZSKPZ")
    protected String zskpz;
    @XmlElement(name = "ZTZPZ")
    protected String ztzpz;
    @XmlElement(name = "ZAUDIT")
    protected String zaudit;
    @XmlElement(name = "ZDZRQ")
    protected String zdzrq;
    @XmlElement(name = "ZBEIZ")
    protected String zbeiz;
    @XmlElement(name = "ZDZPZ")
    protected String zdzpz;
    @XmlElement(name = "GJAHR")
    protected String gjahr;
    @XmlElement(name = "MONAT")
    protected String monat;
    @XmlElement(name = "ZVBELN")
    protected String zvbeln;
    @XmlElement(name = "ZTKDH")
    protected String ztkdh;
    @XmlElement(name = "ZDDYS")
    protected BigDecimal zddys;
    @XmlElement(name = "ZSKLX")
    protected String zsklx;
    @XmlElement(name = "ZYYLM")
    protected String zyylm;
    @XmlElement(name = "ZHZSD")
    protected String zhzsd;
    @XmlElement(name = "ZXSLY")
    protected String zxsly;
    @XmlElement(name = "ZCKDH")
    protected String zckdh;
    @XmlElement(name = "ZZDDH")
    protected String zzddh;
    @XmlElement(name = "ZJNJE")
    protected BigDecimal zjnje;
    @XmlElement(name = "ZKKZJE")
    protected BigDecimal zkkzje;
    @XmlElement(name = "ZZFDH")
    protected String zzfdh;
    @XmlElement(name = "ZKJZF")
    protected String zkjzf;
    @XmlElement(name = "CDATE")
    protected String cdate;
    @XmlElement(name = "CTIME")
    protected String ctime;
    @XmlElement(name = "CUSER")
    protected String cuser;
    @XmlElement(name = "EDATE")
    protected String edate;
    @XmlElement(name = "ETIME")
    protected String etime;
    @XmlElement(name = "EUSER")
    protected String euser;
    @XmlElement(name = "ZKKBT")
    protected ZKKB zkkbt;

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
     * Gets the value of the mandt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMANDT() {
        return mandt;
    }

    /**
     * Sets the value of the mandt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMANDT(String value) {
        this.mandt = value;
    }

    /**
     * Gets the value of the vbeln property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVBELN() {
        return vbeln;
    }

    /**
     * Sets the value of the vbeln property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVBELN(String value) {
        this.vbeln = value;
    }

    /**
     * Gets the value of the zjyck property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZJYCK() {
        return zjyck;
    }

    /**
     * Sets the value of the zjyck property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZJYCK(String value) {
        this.zjyck = value;
    }

    /**
     * Gets the value of the zpayid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZPAYID() {
        return zpayid;
    }

    /**
     * Sets the value of the zpayid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZPAYID(String value) {
        this.zpayid = value;
    }

    /**
     * Gets the value of the zywlx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZYWLX() {
        return zywlx;
    }

    /**
     * Sets the value of the zywlx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZYWLX(String value) {
        this.zywlx = value;
    }

    /**
     * Gets the value of the budat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBUDAT() {
        return budat;
    }

    /**
     * Sets the value of the budat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBUDAT(String value) {
        this.budat = value;
    }

    /**
     * Gets the value of the bldat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBLDAT() {
        return bldat;
    }

    /**
     * Sets the value of the bldat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBLDAT(String value) {
        this.bldat = value;
    }

    /**
     * Gets the value of the budats property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBUDATS() {
        return budats;
    }

    /**
     * Sets the value of the budats property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBUDATS(String value) {
        this.budats = value;
    }

    /**
     * Gets the value of the zjsrq property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZJSRQ() {
        return zjsrq;
    }

    /**
     * Sets the value of the zjsrq property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZJSRQ(String value) {
        this.zjsrq = value;
    }

    /**
     * Gets the value of the zsaut property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZSAUT() {
        return zsaut;
    }

    /**
     * Sets the value of the zsaut property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZSAUT(String value) {
        this.zsaut = value;
    }

    /**
     * Gets the value of the segment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSEGMENT() {
        return segment;
    }

    /**
     * Sets the value of the segment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSEGMENT(String value) {
        this.segment = value;
    }

    /**
     * Gets the value of the zskfs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZSKFS() {
        return zskfs;
    }

    /**
     * Sets the value of the zskfs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZSKFS(String value) {
        this.zskfs = value;
    }

    /**
     * Gets the value of the zyszk property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getZYSZK() {
        return zyszk;
    }

    /**
     * Sets the value of the zyszk property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setZYSZK(BigDecimal value) {
        this.zyszk = value;
    }

    /**
     * Gets the value of the zdsfm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZDSFM() {
        return zdsfm;
    }

    /**
     * Sets the value of the zdsfm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZDSFM(String value) {
        this.zdsfm = value;
    }

    /**
     * Gets the value of the zkhbh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZKHBH() {
        return zkhbh;
    }

    /**
     * Sets the value of the zkhbh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZKHBH(String value) {
        this.zkhbh = value;
    }

    /**
     * Gets the value of the zkhmc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZKHMC() {
        return zkhmc;
    }

    /**
     * Sets the value of the zkhmc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZKHMC(String value) {
        this.zkhmc = value;
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

    /**
     * Gets the value of the zmark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZMARK() {
        return zmark;
    }

    /**
     * Sets the value of the zmark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZMARK(String value) {
        this.zmark = value;
    }

    /**
     * Gets the value of the zjzpz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZJZPZ() {
        return zjzpz;
    }

    /**
     * Sets the value of the zjzpz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZJZPZ(String value) {
        this.zjzpz = value;
    }

    /**
     * Gets the value of the zjzpg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZJZPG() {
        return zjzpg;
    }

    /**
     * Sets the value of the zjzpg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZJZPG(String value) {
        this.zjzpg = value;
    }

    /**
     * Gets the value of the zskbz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZSKBZ() {
        return zskbz;
    }

    /**
     * Sets the value of the zskbz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZSKBZ(String value) {
        this.zskbz = value;
    }

    /**
     * Gets the value of the zysbz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZYSBZ() {
        return zysbz;
    }

    /**
     * Sets the value of the zysbz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZYSBZ(String value) {
        this.zysbz = value;
    }

    /**
     * Gets the value of the zdfpz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZDFPZ() {
        return zdfpz;
    }

    /**
     * Sets the value of the zdfpz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZDFPZ(String value) {
        this.zdfpz = value;
    }

    /**
     * Gets the value of the zskpz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZSKPZ() {
        return zskpz;
    }

    /**
     * Sets the value of the zskpz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZSKPZ(String value) {
        this.zskpz = value;
    }

    /**
     * Gets the value of the ztzpz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZTZPZ() {
        return ztzpz;
    }

    /**
     * Sets the value of the ztzpz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZTZPZ(String value) {
        this.ztzpz = value;
    }

    /**
     * Gets the value of the zaudit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZAUDIT() {
        return zaudit;
    }

    /**
     * Sets the value of the zaudit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZAUDIT(String value) {
        this.zaudit = value;
    }

    /**
     * Gets the value of the zdzrq property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZDZRQ() {
        return zdzrq;
    }

    /**
     * Sets the value of the zdzrq property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZDZRQ(String value) {
        this.zdzrq = value;
    }

    /**
     * Gets the value of the zbeiz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZBEIZ() {
        return zbeiz;
    }

    /**
     * Sets the value of the zbeiz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZBEIZ(String value) {
        this.zbeiz = value;
    }

    /**
     * Gets the value of the zdzpz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZDZPZ() {
        return zdzpz;
    }

    /**
     * Sets the value of the zdzpz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZDZPZ(String value) {
        this.zdzpz = value;
    }

    /**
     * Gets the value of the gjahr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGJAHR() {
        return gjahr;
    }

    /**
     * Sets the value of the gjahr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGJAHR(String value) {
        this.gjahr = value;
    }

    /**
     * Gets the value of the monat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMONAT() {
        return monat;
    }

    /**
     * Sets the value of the monat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMONAT(String value) {
        this.monat = value;
    }

    /**
     * Gets the value of the zvbeln property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZVBELN() {
        return zvbeln;
    }

    /**
     * Sets the value of the zvbeln property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZVBELN(String value) {
        this.zvbeln = value;
    }

    /**
     * Gets the value of the ztkdh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZTKDH() {
        return ztkdh;
    }

    /**
     * Sets the value of the ztkdh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZTKDH(String value) {
        this.ztkdh = value;
    }

    /**
     * Gets the value of the zddys property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getZDDYS() {
        return zddys;
    }

    /**
     * Sets the value of the zddys property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setZDDYS(BigDecimal value) {
        this.zddys = value;
    }

    /**
     * Gets the value of the zsklx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZSKLX() {
        return zsklx;
    }

    /**
     * Sets the value of the zsklx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZSKLX(String value) {
        this.zsklx = value;
    }

    /**
     * Gets the value of the zyylm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZYYLM() {
        return zyylm;
    }

    /**
     * Sets the value of the zyylm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZYYLM(String value) {
        this.zyylm = value;
    }

    /**
     * Gets the value of the zhzsd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZHZSD() {
        return zhzsd;
    }

    /**
     * Sets the value of the zhzsd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZHZSD(String value) {
        this.zhzsd = value;
    }

    /**
     * Gets the value of the zxsly property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZXSLY() {
        return zxsly;
    }

    /**
     * Sets the value of the zxsly property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZXSLY(String value) {
        this.zxsly = value;
    }

    /**
     * Gets the value of the zckdh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZCKDH() {
        return zckdh;
    }

    /**
     * Sets the value of the zckdh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZCKDH(String value) {
        this.zckdh = value;
    }

    /**
     * Gets the value of the zzddh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZZDDH() {
        return zzddh;
    }

    /**
     * Sets the value of the zzddh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZZDDH(String value) {
        this.zzddh = value;
    }

    /**
     * Gets the value of the zjnje property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getZJNJE() {
        return zjnje;
    }

    /**
     * Sets the value of the zjnje property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setZJNJE(BigDecimal value) {
        this.zjnje = value;
    }

    /**
     * Gets the value of the zkkzje property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getZKKZJE() {
        return zkkzje;
    }

    /**
     * Sets the value of the zkkzje property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setZKKZJE(BigDecimal value) {
        this.zkkzje = value;
    }

    /**
     * Gets the value of the zzfdh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZZFDH() {
        return zzfdh;
    }

    /**
     * Sets the value of the zzfdh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZZFDH(String value) {
        this.zzfdh = value;
    }

    /**
     * Gets the value of the zkjzf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZKJZF() {
        return zkjzf;
    }

    /**
     * Sets the value of the zkjzf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZKJZF(String value) {
        this.zkjzf = value;
    }

    /**
     * Gets the value of the cdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCDATE() {
        return cdate;
    }

    /**
     * Sets the value of the cdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCDATE(String value) {
        this.cdate = value;
    }

    /**
     * Gets the value of the ctime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCTIME() {
        return ctime;
    }

    /**
     * Sets the value of the ctime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCTIME(String value) {
        this.ctime = value;
    }

    /**
     * Gets the value of the cuser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCUSER() {
        return cuser;
    }

    /**
     * Sets the value of the cuser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCUSER(String value) {
        this.cuser = value;
    }

    /**
     * Gets the value of the edate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEDATE() {
        return edate;
    }

    /**
     * Sets the value of the edate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEDATE(String value) {
        this.edate = value;
    }

    /**
     * Gets the value of the etime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getETIME() {
        return etime;
    }

    /**
     * Sets the value of the etime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setETIME(String value) {
        this.etime = value;
    }

    /**
     * Gets the value of the euser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEUSER() {
        return euser;
    }

    /**
     * Sets the value of the euser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEUSER(String value) {
        this.euser = value;
    }

    /**
     * Gets the value of the zkkbt property.
     * 
     * @return
     *     possible object is
     *     {@link ZKKB }
     *     
     */
    public ZKKB getZKKBT() {
        return zkkbt;
    }

    /**
     * Sets the value of the zkkbt property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZKKB }
     *     
     */
    public void setZKKBT(ZKKB value) {
        this.zkkbt = value;
    }

}

package cn.com.gome.dujia.ws.client.sap.income;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
/**
 * 虚拟业务收入
 * 
 * <p>Java class for DT_IFI402_VirtualBusinessIncome_REQ complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DT_IFI402_VirtualBusinessIncome_REQ">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="transaction_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transaction_data" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DETAILS" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="HEADER">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="ZXNDDH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ORDAT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ORTIM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="LIFNR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ZSAUT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ZYWLX" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BUDAT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ZXSLY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ZSITE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ZDSFH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="ITEMS" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="ZVBELP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="MAKTX" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="MATKL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ZPP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ZMENGE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="NETWR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ZYSZK" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ZYJJE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ZYJBL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ZYDDH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ZYDDX" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ZJSFA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ZPRITB" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="ZPRTY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="ZPRJE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="ZCURR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="ZMENGE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="ZDANWEI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="ZYOHB" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="ZYHQH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="ZYOHJ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="AUFNR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
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
@XmlType(name = "DT_IFI402_VirtualBusinessIncome_REQ", propOrder = {
    "transactionID",
    "transactionData",
    "details"
})
public class DTIFI402VirtualBusinessIncomeREQ {

    @XmlElement(name = "transaction_ID", required = true)
    protected String transactionID;
    
    @XmlElement(name = "transaction_data", required = true)
    protected String transactionData;
    
    @XmlElement(name = "DETAILS")
    protected List<DTIFI402VirtualBusinessIncomeREQ.DETAILS> details;
    
    /**
     * Sets the value of the details property.
     * 
	 * @param details
	 */
	public void setDetails(List<DTIFI402VirtualBusinessIncomeREQ.DETAILS> details) {
		this.details = details;
	}

    /**
     * Gets the value of the transactionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * Sets the value of the transactionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionID(String value) {
        this.transactionID = value;
    }

    /**
     * Gets the value of the transactionData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionData() {
        return transactionData;
    }

    /**
     * Sets the value of the transactionData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionData(String value) {
        this.transactionData = value;
    }

    /**
     * Gets the value of the details property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the details property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDETAILS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTIFI402VirtualBusinessIncomeREQ.DETAILS }
     * 
     * 
     */
    public List<DTIFI402VirtualBusinessIncomeREQ.DETAILS> getDETAILS() {
        if (details == null) {
            details = new ArrayList<DTIFI402VirtualBusinessIncomeREQ.DETAILS>();
        }
        return this.details;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="HEADER">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="ZXNDDH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ORDAT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ORTIM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="LIFNR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ZSAUT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ZYWLX" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BUDAT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ZXSLY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ZSITE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ZDSFH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="ITEMS" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="ZVBELP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="MAKTX" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="MATKL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ZPP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ZMENGE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="NETWR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ZYSZK" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ZYJJE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ZYJBL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ZYDDH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ZYDDX" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ZJSFA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ZPRITB" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="ZPRTY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="ZPRJE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="ZCURR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="ZMENGE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="ZDANWEI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="ZYOHB" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="ZYHQH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="ZYOHJ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="AUFNR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
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
    @XmlType(name = "", propOrder = {
        "header",
        "items"
    })
    public static class DETAILS {

        @XmlElement(name = "HEADER", required = true)
        protected DTIFI402VirtualBusinessIncomeREQ.DETAILS.HEADER header;
        
        @XmlElement(name = "ITEMS")
        protected List<DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS> items;

        /**
         * Gets the value of the header property.
         * 
         * @return
         *     possible object is
         *     {@link DTIFI402VirtualBusinessIncomeREQ.DETAILS.HEADER }
         *     
         */
        public DTIFI402VirtualBusinessIncomeREQ.DETAILS.HEADER getHEADER() {
            return header;
        }

        /**
         * Sets the value of the header property.
         * 
         * @param value
         *     allowed object is
         *     {@link DTIFI402VirtualBusinessIncomeREQ.DETAILS.HEADER }
         *     
         */
        public void setHEADER(DTIFI402VirtualBusinessIncomeREQ.DETAILS.HEADER value) {
            this.header = value;
        }
        
        /**
         * Sets the value of the items property.
         * 
		 * @param items
		 */
		public void setItems(List<DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS> items) {
			this.items = items;
		}

        /**
         * Gets the value of the items property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the items property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getITEMS().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS }
         * 
         * 
         */
        public List<DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS> getITEMS() {
            if (items == null) {
                items = new ArrayList<DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS>();
            }
            return this.items;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="ZXNDDH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ORDAT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ORTIM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="LIFNR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ZSAUT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ZYWLX" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BUDAT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ZXSLY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ZSITE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ZDSFH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "zxnddh",
            "ordat",
            "ortim",
            "lifnr",
            "zsaut",
            "zywlx",
            "budat",
            "zxsly",
            "zsite",
            "zdsfh"
        })
        public static class HEADER {

            @XmlElement(name = "ZXNDDH")
            protected String zxnddh;
            @XmlElement(name = "ORDAT")
            protected String ordat;
            @XmlElement(name = "ORTIM")
            protected String ortim;
            @XmlElement(name = "LIFNR")
            protected String lifnr;
            @XmlElement(name = "ZSAUT")
            protected String zsaut;
            @XmlElement(name = "ZYWLX")
            protected String zywlx;
            @XmlElement(name = "BUDAT")
            protected String budat;
            @XmlElement(name = "ZXSLY")
            protected String zxsly;
            @XmlElement(name = "ZSITE")
            protected String zsite;
            @XmlElement(name = "ZDSFH")
            protected String zdsfh;

            /**
             * Gets the value of the zxnddh property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getZXNDDH() {
                return zxnddh;
            }

            /**
             * Sets the value of the zxnddh property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setZXNDDH(String value) {
                this.zxnddh = value;
            }

            /**
             * Gets the value of the ordat property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getORDAT() {
                return ordat;
            }

            /**
             * Sets the value of the ordat property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setORDAT(String value) {
                this.ordat = value;
            }

            /**
             * Gets the value of the ortim property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getORTIM() {
                return ortim;
            }

            /**
             * Sets the value of the ortim property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setORTIM(String value) {
                this.ortim = value;
            }

            /**
             * Gets the value of the lifnr property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLIFNR() {
                return lifnr;
            }

            /**
             * Sets the value of the lifnr property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLIFNR(String value) {
                this.lifnr = value;
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
             * Gets the value of the zsite property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getZSITE() {
                return zsite;
            }

            /**
             * Sets the value of the zsite property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setZSITE(String value) {
                this.zsite = value;
            }

            /**
             * Gets the value of the zdsfh property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getZDSFH() {
                return zdsfh;
            }

            /**
             * Sets the value of the zdsfh property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setZDSFH(String value) {
                this.zdsfh = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="ZVBELP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="MAKTX" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="MATKL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ZPP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ZMENGE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="NETWR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ZYSZK" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ZYJJE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ZYJBL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ZYDDH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ZYDDX" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ZJSFA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ZPRITB" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="ZPRTY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="ZPRJE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="ZCURR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="ZMENGE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="ZDANWEI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="ZYOHB" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="ZYHQH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="ZYOHJ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="AUFNR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
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
        @XmlType(name = "", propOrder = {
            "zvbelp",
            "matnr",
            "maktx",
            "matkl",
            "zpp",
            "zmenge",
            "netwr",
            "zyszk",
            "zyjje",
            "zyjbl",
            "zyddh",
            "zyddx",
            "zjsfa",
            "zdsfh1",
            "zpritb",
            "zyohb"
        })
        public static class ITEMS {

            @XmlElement(name = "ZVBELP")
            protected String zvbelp;
            @XmlElement(name = "MATNR")
            protected String matnr;
            @XmlElement(name = "MAKTX")
            protected String maktx;
            @XmlElement(name = "MATKL")
            protected String matkl;
            @XmlElement(name = "ZPP")
            protected String zpp;
            @XmlElement(name = "ZMENGE")
            protected String zmenge;
            @XmlElement(name = "NETWR")
            protected String netwr;
            @XmlElement(name = "ZYSZK")
            protected String zyszk;
            @XmlElement(name = "ZYJJE")
            protected String zyjje;
            @XmlElement(name = "ZYJBL")
            protected String zyjbl;
            @XmlElement(name = "ZYDDH")
            protected String zyddh;
            @XmlElement(name = "ZYDDX")
            protected String zyddx;
            @XmlElement(name = "ZJSFA")
            protected String zjsfa;
            @XmlElement(name = "ZDSFH1")
            protected String zdsfh1;
            @XmlElement(name = "ZPRITB")
            protected List<DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS.ZPRITB> zpritb;
            @XmlElement(name = "ZYOHB")
            protected List<DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS.ZYOHB> zyohb;

            /**
             * Gets the value of the zvbelp property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getZVBELP() {
                return zvbelp;
            }

            /**
             * Sets the value of the zvbelp property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setZVBELP(String value) {
                this.zvbelp = value;
            }

            /**
             * Gets the value of the matnr property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMATNR() {
                return matnr;
            }

            /**
             * Sets the value of the matnr property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMATNR(String value) {
                this.matnr = value;
            }

            /**
             * Gets the value of the maktx property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMAKTX() {
                return maktx;
            }

            /**
             * Sets the value of the maktx property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMAKTX(String value) {
                this.maktx = value;
            }

            /**
             * Gets the value of the matkl property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMATKL() {
                return matkl;
            }

            /**
             * Sets the value of the matkl property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMATKL(String value) {
                this.matkl = value;
            }

            /**
             * Gets the value of the zpp property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getZPP() {
                return zpp;
            }

            /**
             * Sets the value of the zpp property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setZPP(String value) {
                this.zpp = value;
            }

            /**
             * Gets the value of the zmenge property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getZMENGE() {
                return zmenge;
            }

            /**
             * Sets the value of the zmenge property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setZMENGE(String value) {
                this.zmenge = value;
            }

            /**
             * Gets the value of the netwr property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNETWR() {
                return netwr;
            }

            /**
             * Sets the value of the netwr property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNETWR(String value) {
                this.netwr = value;
            }

            /**
             * Gets the value of the zyszk property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getZYSZK() {
                return zyszk;
            }

            /**
             * Sets the value of the zyszk property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setZYSZK(String value) {
                this.zyszk = value;
            }

            /**
             * Gets the value of the zyjje property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getZYJJE() {
                return zyjje;
            }

            /**
             * Sets the value of the zyjje property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setZYJJE(String value) {
                this.zyjje = value;
            }

            /**
             * Gets the value of the zyjbl property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getZYJBL() {
                return zyjbl;
            }

            /**
             * Sets the value of the zyjbl property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setZYJBL(String value) {
                this.zyjbl = value;
            }

            /**
             * Gets the value of the zyddh property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getZYDDH() {
                return zyddh;
            }

            /**
             * Sets the value of the zyddh property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setZYDDH(String value) {
                this.zyddh = value;
            }

            /**
             * Gets the value of the zyddx property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getZYDDX() {
                return zyddx;
            }

            /**
             * Sets the value of the zyddx property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setZYDDX(String value) {
                this.zyddx = value;
            }

            /**
             * Gets the value of the zjsfa property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getZJSFA() {
                return zjsfa;
            }

            /**
             * Sets the value of the zjsfa property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setZJSFA(String value) {
                this.zjsfa = value;
            }
            
            /**
             * Gets the value of the zdsfh1 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getZDSFH1() {
                return zdsfh1;
            }

            /**
             * Sets the value of the zdsfh1 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setZDSFH1(String value) {
                this.zdsfh1 = value;
            }
            
            /**
             * Sets the value of the zpritb property
             * 
			 * @param zpritb
			 */
			public void setZpritb(List<DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS.ZPRITB> zpritb) {
				this.zpritb = zpritb;
			}


            /**
             * Gets the value of the zpritb property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the zpritb property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getZPRITB().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS.ZPRITB }
             * 
             * 
             */
            public List<DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS.ZPRITB> getZPRITB() {
                if (zpritb == null) {
                    zpritb = new ArrayList<DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS.ZPRITB>();
                }
                return this.zpritb;
            }

            /**
             * Gets the value of the zyohb property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the zyohb property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getZYOHB().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS.ZYOHB }
             * 
             * 
             */
            public List<DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS.ZYOHB> getZYOHB() {
                if (zyohb == null) {
                    zyohb = new ArrayList<DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS.ZYOHB>();
                }
                return this.zyohb;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="ZPRTY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="ZPRJE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="ZCURR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="ZMENGE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="ZDANWEI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "zprty",
                "zprje",
                "zcurr",
                "zmenge",
                "zdanwei"
            })
            public static class ZPRITB {

                @XmlElement(name = "ZPRTY")
                protected String zprty;
                @XmlElement(name = "ZPRJE")
                protected String zprje;
                @XmlElement(name = "ZCURR")
                protected String zcurr;
                @XmlElement(name = "ZMENGE")
                protected String zmenge;
                @XmlElement(name = "ZDANWEI")
                protected String zdanwei;

                /**
                 * Gets the value of the zprty property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getZPRTY() {
                    return zprty;
                }

                /**
                 * Sets the value of the zprty property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setZPRTY(String value) {
                    this.zprty = value;
                }

                /**
                 * Gets the value of the zprje property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getZPRJE() {
                    return zprje;
                }

                /**
                 * Sets the value of the zprje property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setZPRJE(String value) {
                    this.zprje = value;
                }

                /**
                 * Gets the value of the zcurr property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getZCURR() {
                    return zcurr;
                }

                /**
                 * Sets the value of the zcurr property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setZCURR(String value) {
                    this.zcurr = value;
                }

                /**
                 * Gets the value of the zmenge property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getZMENGE() {
                    return zmenge;
                }

                /**
                 * Sets the value of the zmenge property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setZMENGE(String value) {
                    this.zmenge = value;
                }

                /**
                 * Gets the value of the zdanwei property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getZDANWEI() {
                    return zdanwei;
                }

                /**
                 * Sets the value of the zdanwei property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setZDANWEI(String value) {
                    this.zdanwei = value;
                }

            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="ZYHQH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="ZYOHJ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="AUFNR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "zyhqh",
                "zyohj",
                "aufnr"
            })
            public static class ZYOHB {

                @XmlElement(name = "ZYHQH")
                protected String zyhqh;
                @XmlElement(name = "ZYOHJ")
                protected String zyohj;
                @XmlElement(name = "AUFNR")
                protected String aufnr;

                /**
                 * Gets the value of the zyhqh property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getZYHQH() {
                    return zyhqh;
                }

                /**
                 * Sets the value of the zyhqh property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setZYHQH(String value) {
                    this.zyhqh = value;
                }

                /**
                 * Gets the value of the zyohj property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getZYOHJ() {
                    return zyohj;
                }

                /**
                 * Sets the value of the zyohj property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setZYOHJ(String value) {
                    this.zyohj = value;
                }

                /**
                 * Gets the value of the aufnr property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getAUFNR() {
                    return aufnr;
                }

                /**
                 * Sets the value of the aufnr property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setAUFNR(String value) {
                    this.aufnr = value;
                }

            }

        }

    }

}

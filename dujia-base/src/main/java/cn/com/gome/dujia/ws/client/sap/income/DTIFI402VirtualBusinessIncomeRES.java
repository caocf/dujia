package cn.com.gome.dujia.ws.client.sap.income;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
/**
 * <p>Java class for DT_IFI402_VirtualBusinessIncome_RES complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DT_IFI402_VirtualBusinessIncome_RES">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="transaction_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ITEMS" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ZXNDDH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="transaction_status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="transaction_dsp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "DT_IFI402_VirtualBusinessIncome_RES", propOrder = {
    "transactionID",
    "items"
})
public class DTIFI402VirtualBusinessIncomeRES {

    @XmlElement(name = "transaction_ID", required = true)
    protected String transactionID;
    
    @XmlElement(name = "ITEMS")
    protected List<DTIFI402VirtualBusinessIncomeRES.ITEMS> items;

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
     * {@link DTIFI402VirtualBusinessIncomeRES.ITEMS }
     * 
     * 
     */
    public List<DTIFI402VirtualBusinessIncomeRES.ITEMS> getITEMS() {
        if (items == null) {
            items = new ArrayList<DTIFI402VirtualBusinessIncomeRES.ITEMS>();
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
     *         &lt;element name="transaction_status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="transaction_dsp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "transactionStatus",
        "transactionDsp"
    })
    public static class ITEMS {

        @XmlElement(name = "ZXNDDH")
        protected String zxnddh;
        @XmlElement(name = "transaction_status")
        protected String transactionStatus;
        @XmlElement(name = "transaction_dsp")
        protected String transactionDsp;

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
         * Gets the value of the transactionStatus property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTransactionStatus() {
            return transactionStatus;
        }

        /**
         * Sets the value of the transactionStatus property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTransactionStatus(String value) {
            this.transactionStatus = value;
        }

        /**
         * Gets the value of the transactionDsp property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTransactionDsp() {
            return transactionDsp;
        }

        /**
         * Sets the value of the transactionDsp property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTransactionDsp(String value) {
            this.transactionDsp = value;
        }

    }

}

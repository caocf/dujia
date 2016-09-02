package cn.com.gome.dujia.ws.client.rb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
/**
 * <p>Java class for acceptTask complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="acceptTask">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="taskBean" type="{http://webservice.rb.gome.founder.com/}refundBankTask" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "acceptTask", propOrder = {
    "taskBean"
})
public class AcceptTask {

    protected RefundBankTask taskBean;

    /**
     * Gets the value of the taskBean property.
     * 
     * @return
     *     possible object is
     *     {@link RefundBankTask }
     *     
     */
    public RefundBankTask getTaskBean() {
        return taskBean;
    }

    /**
     * Sets the value of the taskBean property.
     * 
     * @param value
     *     allowed object is
     *     {@link RefundBankTask }
     *     
     */
    public void setTaskBean(RefundBankTask value) {
        this.taskBean = value;
    }

}

package cn.com.gome.dujia.ws.client.sap.income;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cn.com.gome.showticket.ws.sap.income.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _MTIFI402VirtualBusinessIncomeRES_QNAME = new QName("URN:ECC", "MT_IFI402_VirtualBusinessIncome_RES");
    
    private final static QName _MTIFI402VirtualBusinessIncomeREQ_QNAME = new QName("URN:ECC", "MT_IFI402_VirtualBusinessIncome_REQ");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cn.com.gome.showticket.ws.sap.income.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DTIFI402VirtualBusinessIncomeRES }
     * 
     */
    public DTIFI402VirtualBusinessIncomeRES createDTIFI402VirtualBusinessIncomeRES() {
        return new DTIFI402VirtualBusinessIncomeRES();
    }

    /**
     * Create an instance of {@link DTIFI402VirtualBusinessIncomeREQ }
     * 
     */
    public DTIFI402VirtualBusinessIncomeREQ createDTIFI402VirtualBusinessIncomeREQ() {
        return new DTIFI402VirtualBusinessIncomeREQ();
    }

    /**
     * Create an instance of {@link DTIFI402VirtualBusinessIncomeREQ.DETAILS }
     * 
     */
    public DTIFI402VirtualBusinessIncomeREQ.DETAILS createDTIFI402VirtualBusinessIncomeREQDETAILS() {
        return new DTIFI402VirtualBusinessIncomeREQ.DETAILS();
    }

    /**
     * Create an instance of {@link DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS }
     * 
     */
    public DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS createDTIFI402VirtualBusinessIncomeREQDETAILSITEMS() {
        return new DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS();
    }

    /**
     * Create an instance of {@link DTIFI402VirtualBusinessIncomeRES.ITEMS }
     * 
     */
    public DTIFI402VirtualBusinessIncomeRES.ITEMS createDTIFI402VirtualBusinessIncomeRESITEMS() {
        return new DTIFI402VirtualBusinessIncomeRES.ITEMS();
    }

    /**
     * Create an instance of {@link DTIFI402VirtualBusinessIncomeREQ.DETAILS.HEADER }
     * 
     */
    public DTIFI402VirtualBusinessIncomeREQ.DETAILS.HEADER createDTIFI402VirtualBusinessIncomeREQDETAILSHEADER() {
        return new DTIFI402VirtualBusinessIncomeREQ.DETAILS.HEADER();
    }

    /**
     * Create an instance of {@link DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS.ZPRITB }
     * 
     */
    public DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS.ZPRITB createDTIFI402VirtualBusinessIncomeREQDETAILSITEMSZPRITB() {
        return new DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS.ZPRITB();
    }

    /**
     * Create an instance of {@link DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS.ZYOHB }
     * 
     */
    public DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS.ZYOHB createDTIFI402VirtualBusinessIncomeREQDETAILSITEMSZYOHB() {
        return new DTIFI402VirtualBusinessIncomeREQ.DETAILS.ITEMS.ZYOHB();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTIFI402VirtualBusinessIncomeRES }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "URN:ECC", name = "MT_IFI402_VirtualBusinessIncome_RES")
    public JAXBElement<DTIFI402VirtualBusinessIncomeRES> createMTIFI402VirtualBusinessIncomeRES(DTIFI402VirtualBusinessIncomeRES value) {
        return new JAXBElement<DTIFI402VirtualBusinessIncomeRES>(_MTIFI402VirtualBusinessIncomeRES_QNAME, DTIFI402VirtualBusinessIncomeRES.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTIFI402VirtualBusinessIncomeREQ }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "URN:ECC", name = "MT_IFI402_VirtualBusinessIncome_REQ")
    public JAXBElement<DTIFI402VirtualBusinessIncomeREQ> createMTIFI402VirtualBusinessIncomeREQ(DTIFI402VirtualBusinessIncomeREQ value) {
        return new JAXBElement<DTIFI402VirtualBusinessIncomeREQ>(_MTIFI402VirtualBusinessIncomeREQ_QNAME, DTIFI402VirtualBusinessIncomeREQ.class, null, value);
    }

}

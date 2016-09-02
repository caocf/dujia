package cn.com.gome.dujia.ws.client.rb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cn.com.gome.train.ws.rb.refund.client package. 
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

    private final static QName _AcceptTask_QNAME = new QName("http://webservice.rb.gome.founder.com/", "acceptTask");
    private final static QName _AcceptTaskResponse_QNAME = new QName("http://webservice.rb.gome.founder.com/", "acceptTaskResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cn.com.gome.train.ws.rb.refund.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AcceptTask }
     * 
     */
    public AcceptTask createAcceptTask() {
        return new AcceptTask();
    }

    /**
     * Create an instance of {@link AcceptTaskResponse }
     * 
     */
    public AcceptTaskResponse createAcceptTaskResponse() {
        return new AcceptTaskResponse();
    }

    /**
     * Create an instance of {@link RefundBankTask }
     * 
     */
    public RefundBankTask createRefundBankTask() {
        return new RefundBankTask();
    }

    /**
     * Create an instance of {@link RefundTaskReply }
     * 
     */
    public RefundTaskReply createRefundTaskReply() {
        return new RefundTaskReply();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcceptTask }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.rb.gome.founder.com/", name = "acceptTask")
    public JAXBElement<AcceptTask> createAcceptTask(AcceptTask value) {
        return new JAXBElement<AcceptTask>(_AcceptTask_QNAME, AcceptTask.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcceptTaskResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.rb.gome.founder.com/", name = "acceptTaskResponse")
    public JAXBElement<AcceptTaskResponse> createAcceptTaskResponse(AcceptTaskResponse value) {
        return new JAXBElement<AcceptTaskResponse>(_AcceptTaskResponse_QNAME, AcceptTaskResponse.class, null, value);
    }

}

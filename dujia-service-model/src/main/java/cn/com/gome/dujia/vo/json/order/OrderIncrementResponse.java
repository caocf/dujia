package cn.com.gome.dujia.vo.json.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by sunming on 2016/4/26.
 */
public class OrderIncrementResponse implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "ret_code")
    private String retCode;
	
	@JsonProperty(value = "err_msg")
    private String errMsg;
    
    @JsonProperty(value = "result")
    private OrderIncrementInner result;


    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public OrderIncrementInner getResult() {
        return result;
    }

    public void setResult(OrderIncrementInner result) {
        this.result = result;
    }
}

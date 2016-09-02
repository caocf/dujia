package cn.com.gome.dujia.vo.json.productinfo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sunming on 2016/4/26.
 */
public class ProductDetailInfoResponse  implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "ret_code")
    private String retCode;
	
	@JsonProperty(value = "err_msg")
    private String errMsg;
    
    @JsonProperty(value = "result")
    private GetProductDetailInfoResultInner result;

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

	public GetProductDetailInfoResultInner getResult() {
		return result;
	}

	public void setResult(GetProductDetailInfoResultInner result) {
		this.result = result;
	}

}

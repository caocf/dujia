package cn.com.gome.dujia.vo.order;

import cn.com.gome.dujia.vo.json.order.OrderIncrementInner;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;

/**
 * @author sunming
 * 
 * 取消订单
 */
public class CancelResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	@JsonProperty(value = "ret_code")
	private String retCode;

	@JsonProperty(value = "err_msg")
	private String errMsg;

	@JsonProperty(value = "result")
	private CancelResult result;

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

	public CancelResult getResult() {
		return result;
	}

	public void setResult(CancelResult result) {
		this.result = result;
	}
}

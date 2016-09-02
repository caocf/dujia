package cn.com.gome.dujia.vo.json.productinfo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 增量线路套餐id 列表 结果
 * 
 * @author xiongyan
 * @date 2016年5月12日 下午5:23:19
 */
public class IncrementProductListResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "ret_code")
	private String retCode;
	
	@JsonProperty(value = "err_msg")
	private String errMsg;
	
	@JsonProperty(value = "result")
	private IncrementProductListResult result;

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

	public IncrementProductListResult getResult() {
		return result;
	}

	public void setResult(IncrementProductListResult result) {
		this.result = result;
	}
}

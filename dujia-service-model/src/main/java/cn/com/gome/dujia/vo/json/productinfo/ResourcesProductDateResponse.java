package cn.com.gome.dujia.vo.json.productinfo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 资源产品预定日期 结果
 * 
 * @author xiongyan
 * @date 2016年5月12日 下午5:23:19
 */
public class ResourcesProductDateResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "ret_code")
	private String retCode;
	
	@JsonProperty(value = "err_msg")
	private String errMsg;
	
	@JsonProperty(value = "result")
	private ResourcesProductDateResult result;

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

	public ResourcesProductDateResult getResult() {
		return result;
	}

	public void setResult(ResourcesProductDateResult result) {
		this.result = result;
	}
}

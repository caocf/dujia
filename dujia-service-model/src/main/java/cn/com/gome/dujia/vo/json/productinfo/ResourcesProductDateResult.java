package cn.com.gome.dujia.vo.json.productinfo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 资源产品预定日期 列表 结果
 * 
 * @author xiongyan
 * @date 2016年5月12日 下午5:23:19
 */
public class ResourcesProductDateResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "Msg")
	private String msg;
	
	@JsonProperty(value = "IsTrueSineRes")
	private Boolean isTrueSineRes;
	
	@JsonProperty(value = "ResourcesProductDate")
	private List<ResourcesProductDate> resourcesProductDate;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Boolean getIsTrueSineRes() {
		return isTrueSineRes;
	}

	public void setIsTrueSineRes(Boolean isTrueSineRes) {
		this.isTrueSineRes = isTrueSineRes;
	}

	public List<ResourcesProductDate> getResourcesProductDate() {
		return resourcesProductDate;
	}

	public void setResourcesProductDate(
			List<ResourcesProductDate> resourcesProductDate) {
		this.resourcesProductDate = resourcesProductDate;
	}

}

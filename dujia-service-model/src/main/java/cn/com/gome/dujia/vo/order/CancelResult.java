package cn.com.gome.dujia.vo.order;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;

/**
 * @author sunming
 * 
 * 取消订单
 */
public class CancelResult implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 提示信息
	 */
	@JacksonXmlProperty(localName = "Msg")
	private String msg;

	/**
	 * 返回码
	 */
	@JacksonXmlProperty(localName = "IsSuccessed")
	private Boolean isSuccessed;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Boolean getIsSuccessed() {
		return isSuccessed;
	}

	public void setIsSuccessed(Boolean isSuccessed) {
		this.isSuccessed = isSuccessed;
	}
}

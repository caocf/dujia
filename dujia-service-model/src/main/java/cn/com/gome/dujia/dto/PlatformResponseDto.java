package cn.com.gome.dujia.dto;

/**
 * 
 * Description : 对外平台响应dto
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月29日 下午2:24:54 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public class PlatformResponseDto extends BaseDto {


	/**
	 * @author WenJie Mai
	 *
	 * Created Time : 2016年4月29日 下午2:25:05 <br/>
	 */
	private static final long serialVersionUID = -6343499843056646146L;

	/**
	 * 返回结果
	 */
	private String state;
	
	/**
	 * 返回结果编码
	 */
	private String reasonCode;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	
}

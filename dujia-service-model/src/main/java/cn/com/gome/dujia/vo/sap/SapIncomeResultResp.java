package cn.com.gome.dujia.vo.sap;

import java.io.Serializable;
/**
 * 
 * Description : sap收入回调接口返回参数
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月29日 下午2:21:32 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public class SapIncomeResultResp implements Serializable {

	/**
	 * @author WenJie Mai
	 *
	 * Created Time : 2016年4月29日 下午2:22:12 <br/>
	 */
	private static final long serialVersionUID = -8060081968530375804L;

	/**
	 * 状态码 200：成功 
	 *      500：失败
	 */
	private String code;
	
	/**
	 * 描述
	 */
	private String desc;
	
	public SapIncomeResultResp() {
		
	}
	
	public SapIncomeResultResp(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}

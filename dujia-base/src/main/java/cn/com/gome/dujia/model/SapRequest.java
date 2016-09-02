package cn.com.gome.dujia.model;

import java.io.Serializable;
import java.util.List;
/**
 * Description : sap请求参数实体
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月20日 下午2:17:49 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public class SapRequest implements Serializable {

	/**
	 * @author WenJie Mai
	 *
	 * Created Time : 2016年4月20日 下午2:17:53 <br/>
	 */
	private static final long serialVersionUID = -5613135976710949703L;
	
	/**
	 * SAP收款接口地址
	 */
	private String sapPayAddress;
	
	/**
	 * SAP收款接口用户名
	 */
	private String sapPayUsername;
	
	/**
	 * SAP收款接口密码
	 */
	private String sapPayPassword;
	
	/**
	 * SAP收入接口地址
	 */
	private String sapIncomeAddress;
	
	/**
	 * SAP收入接口用户名
	 */
	private String sapIncomeUsername;
	
	/**
	 * SAP收入接口密码
	 */
	private String sapIncomePassword;
	
	/**
	 * 交易信息
	 */
	private List<SapData> sapDataList;


	/**
	 * @return the sapPayAddress
	 */
	public String getSapPayAddress() {
		return sapPayAddress;
	}

	/**
	 * @param sapPayAddress the sapPayAddress to set
	 */
	public void setSapPayAddress(String sapPayAddress) {
		this.sapPayAddress = sapPayAddress;
	}

	/**
	 * @return the sapPayUsername
	 */
	public String getSapPayUsername() {
		return sapPayUsername;
	}

	/**
	 * @param sapPayUsername the sapPayUsername to set
	 */
	public void setSapPayUsername(String sapPayUsername) {
		this.sapPayUsername = sapPayUsername;
	}

	/**
	 * @return the sapPayPassword
	 */
	public String getSapPayPassword() {
		return sapPayPassword;
	}

	/**
	 * @param sapPayPassword the sapPayPassword to set
	 */
	public void setSapPayPassword(String sapPayPassword) {
		this.sapPayPassword = sapPayPassword;
	}

	/**
	 * @return the sapIncomeAddress
	 */
	public String getSapIncomeAddress() {
		return sapIncomeAddress;
	}

	/**
	 * @param sapIncomeAddress the sapIncomeAddress to set
	 */
	public void setSapIncomeAddress(String sapIncomeAddress) {
		this.sapIncomeAddress = sapIncomeAddress;
	}

	/**
	 * @return the sapIncomeUsername
	 */
	public String getSapIncomeUsername() {
		return sapIncomeUsername;
	}

	/**
	 * @param sapIncomeUsername the sapIncomeUsername to set
	 */
	public void setSapIncomeUsername(String sapIncomeUsername) {
		this.sapIncomeUsername = sapIncomeUsername;
	}

	/**
	 * @return the sapIncomePassword
	 */
	public String getSapIncomePassword() {
		return sapIncomePassword;
	}

	/**
	 * @param sapIncomePassword the sapIncomePassword to set
	 */
	public void setSapIncomePassword(String sapIncomePassword) {
		this.sapIncomePassword = sapIncomePassword;
	}

	/**
	 * @return the sapDataList
	 */
	public List<SapData> getSapDataList() {
		return sapDataList;
	}

	/**
	 * @param sapDataList the sapDataList to set
	 */
	public void setSapDataList(List<SapData> sapDataList) {
		this.sapDataList = sapDataList;
	}

}

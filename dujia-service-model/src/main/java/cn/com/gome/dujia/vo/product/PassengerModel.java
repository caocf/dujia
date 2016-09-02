package cn.com.gome.dujia.vo.product;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import cn.com.gome.dujia.enums.IdType;
/**
 * Description : 出游人
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月5日 下午2:18:29 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public class PassengerModel implements Serializable {

	/**
	 * @author WenJie Mai
	 *
	 * Created Time : 2016年4月5日 下午2:18:33 <br/>
	 */
	private static final long serialVersionUID = -2863374145931951014L;
	
	/**
	 * 客人姓名
	 */
	@JacksonXmlProperty(localName = "Name")
	private String name;
	
	/**
	 * 客人手机号
	 */
	@JacksonXmlProperty(localName = "Mobile")
	private String mobile;
	
	/**
	 * 证件类型
	 */
	@JacksonXmlProperty(localName = "IdType")
	private IdType idType;
	
	/**
	 * 证件号
	 */
	@JacksonXmlProperty(localName = "IdNo")
	private String idNo;
	
	/**
	 * 性别
	 */
	@JacksonXmlProperty(localName = "Sex")
	private Integer sex;
	
	/**
	 * 生日
	 */
	@JacksonXmlProperty(localName = "BirthDay")
	private Date birthDay;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the idType
	 */
	public IdType getIdType() {
		return idType;
	}

	/**
	 * @param idType the idType to set
	 */
	public void setIdType(IdType idType) {
		this.idType = idType;
	}

	/**
	 * @return the idNo
	 */
	public String getIdNo() {
		return idNo;
	}

	/**
	 * @param idNo the idNo to set
	 */
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	/**
	 * @return the sex
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * @return the birthDay
	 */
	public Date getBirthDay() {
		return birthDay;
	}

	/**
	 * @param birthDay the birthDay to set
	 */
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

}

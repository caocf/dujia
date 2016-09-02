package cn.com.gome.dujia.vo.product;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import cn.com.gome.dujia.enums.ResourceType;
/**
 * Description : 资源
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月5日 下午2:17:53 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public class ResourceModel implements Serializable {

	/**
	 * @author WenJie Mai
	 *
	 * Created Time : 2016年4月5日 下午2:18:03 <br/>
	 */
	private static final long serialVersionUID = 8706047337768809017L;
	
	/**
	 * 资源Id
	 */
	@JacksonXmlProperty(localName = "ResourceId")
	private Integer resourceId;
	
	/**
	 * 资源名称
	 */
	@JacksonXmlProperty(localName = "Name")
	private String name;
	
	/**
	 * 产品名称
	 */
	@JacksonXmlProperty(localName = "ProductName")
	private String productName;
	
	/**
	 * 类型
	 */
	@JacksonXmlProperty(localName = "Type")
	private ResourceType type;
	
	/**
	 * 份数
	 */
	@JacksonXmlProperty(localName = "PriceFraction")
	private Integer priceFraction;
	
	/**
	 * 资源消费开始时间
	 */
	@JacksonXmlProperty(localName = "UseStartDate")
	private Date useStartDate;
	
	/**
	 * 资源消费结束时间
	 */
	@JacksonXmlProperty(localName = "UseEndDate")
	private Date useEndDate;
	
	/**
	 * 备注/特殊要求
	 */
	@JacksonXmlProperty(localName = "Remark")
	private String remark;

	/**
	 * @return the resourceId
	 */
	public Integer getResourceId() {
		return resourceId;
	}

	/**
	 * @param resourceId the resourceId to set
	 */
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

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
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the type
	 */
	public ResourceType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ResourceType type) {
		this.type = type;
	}

	/**
	 * @return the priceFraction
	 */
	public Integer getPriceFraction() {
		return priceFraction;
	}

	/**
	 * @param priceFraction the priceFraction to set
	 */
	public void setPriceFraction(Integer priceFraction) {
		this.priceFraction = priceFraction;
	}

	/**
	 * @return the useStartDate
	 */
	public Date getUseStartDate() {
		return useStartDate;
	}

	/**
	 * @param useStartDate the useStartDate to set
	 */
	public void setUseStartDate(Date useStartDate) {
		this.useStartDate = useStartDate;
	}

	/**
	 * @return the useEndDate
	 */
	public Date getUseEndDate() {
		return useEndDate;
	}

	/**
	 * @param useEndDate the useEndDate to set
	 */
	public void setUseEndDate(Date useEndDate) {
		this.useEndDate = useEndDate;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}

package cn.com.gome.dujia.model;

import java.io.Serializable;
/**
 * Description : 收银台
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月22日 下午1:43:42 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public class CashierRequest implements Serializable {

	/**
	 * @author WenJie Mai
	 *
	 * Created Time : 2016年4月22日 下午1:43:46 <br/>
	 */
	private static final long serialVersionUID = -6124567392745385015L;

	/**
	 * 商户号
	 */
	private String siteAccount;

    /**
     * 站点名称
     */
    private String siteName;
	
	/**
	 * 订单编号
	 */
	private String orderNo;
	
	/**
	 * 订单总金额(单位：分)
	 */
	private String orderMoney;
	
	/**
	 * 支付金额(单位：分)
	 */
	private String payMoney;
	
	/**
	 * 会员编码
	 */
	private String userNo;
	
	/**
	 * 手机号码
	 */
	private String telphone;
	
	/**
	 * SKU编码
	 */
	private String skuCode;
	
	/**
	 * 商品品类
	 */
	private String productType;
	
	/**
	 * 页面回调地址
	 */
	private String pageBackUrl;
	
	/**
	 * 点对点回调地址
	 */
	private String notifyUrl;
	
	/**
	 * 订单类型
	 */
	private String orderType;
	
	/**
	 * 商品是否支持分期
	 */
	private String isSupportStages;
	
	/**
	 * 用户选择支付方式
	 */
	private String isByStages;
	
	/**
	 * 站点保留域
	 */
	private String remark;
	
	/**
	 * 过期时长
	 */
	private String orderExpireMsg;
	
	/**
	 * 过期时间
	 */
	private String orderExpireTime;
	
	/**
	 * 商户分账信息
	 */
	private String partDetail;
	
	/**
	 * 密钥
	 */
	private String payMd5Key;
	
	/**
	 * 密钥
	 */
	private String payAesKey;
	
	/**
	 * 收银台支付地址
	 */
	private String payUrl;
	
	/**
	 * 收银台支付查询接口地址 
	 */
	private String payQueryUrl;

    /**
     * 是否正常支付(0:下单即支付;1:从订单列表跳转)
     */
    private Integer isNormalPay;

	/**
	 * @return the siteAccount
	 */
	public String getSiteAccount() {
		return siteAccount;
	}

	/**
	 * @param siteAccount the siteAccount to set
	 */
	public void setSiteAccount(String siteAccount) {
		this.siteAccount = siteAccount;
	}

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    /**
	 * @return the orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * @return the orderMoney
	 */
	public String getOrderMoney() {
		return orderMoney;
	}

	/**
	 * @param orderMoney the orderMoney to set
	 */
	public void setOrderMoney(String orderMoney) {
		this.orderMoney = orderMoney;
	}

	/**
	 * @return the payMoney
	 */
	public String getPayMoney() {
		return payMoney;
	}

	/**
	 * @param payMoney the payMoney to set
	 */
	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}

	/**
	 * @return the userNo
	 */
	public String getUserNo() {
		return userNo;
	}

	/**
	 * @param userNo the userNo to set
	 */
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	/**
	 * @return the telphone
	 */
	public String getTelphone() {
		return telphone;
	}

	/**
	 * @param telphone the telphone to set
	 */
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	/**
	 * @return the skuCode
	 */
	public String getSkuCode() {
		return skuCode;
	}

	/**
	 * @param skuCode the skuCode to set
	 */
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	/**
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * @param productType the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * @return the pageBackUrl
	 */
	public String getPageBackUrl() {
		return pageBackUrl;
	}

	/**
	 * @param pageBackUrl the pageBackUrl to set
	 */
	public void setPageBackUrl(String pageBackUrl) {
		this.pageBackUrl = pageBackUrl;
	}

	/**
	 * @return the notifyUrl
	 */
	public String getNotifyUrl() {
		return notifyUrl;
	}

	/**
	 * @param notifyUrl the notifyUrl to set
	 */
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	/**
	 * @return the orderType
	 */
	public String getOrderType() {
		return orderType;
	}

	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	/**
	 * @return the isSupportStages
	 */
	public String getIsSupportStages() {
		return isSupportStages;
	}

	/**
	 * @param isSupportStages the isSupportStages to set
	 */
	public void setIsSupportStages(String isSupportStages) {
		this.isSupportStages = isSupportStages;
	}

	/**
	 * @return the isByStages
	 */
	public String getIsByStages() {
		return isByStages;
	}

	/**
	 * @param isByStages the isByStages to set
	 */
	public void setIsByStages(String isByStages) {
		this.isByStages = isByStages;
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

	/**
	 * @return the orderExpireMsg
	 */
	public String getOrderExpireMsg() {
		return orderExpireMsg;
	}

	/**
	 * @param orderExpireMsg the orderExpireMsg to set
	 */
	public void setOrderExpireMsg(String orderExpireMsg) {
		this.orderExpireMsg = orderExpireMsg;
	}

	/**
	 * @return the orderExpireTime
	 */
	public String getOrderExpireTime() {
		return orderExpireTime;
	}

	/**
	 * @param orderExpireTime the orderExpireTime to set
	 */
	public void setOrderExpireTime(String orderExpireTime) {
		this.orderExpireTime = orderExpireTime;
	}

	/**
	 * @return the partDetail
	 */
	public String getPartDetail() {
		return partDetail;
	}

	/**
	 * @param partDetail the partDetail to set
	 */
	public void setPartDetail(String partDetail) {
		this.partDetail = partDetail;
	}

	/**
	 * @return the payMd5Key
	 */
	public String getPayMd5Key() {
		return payMd5Key;
	}

	/**
	 * @param payMd5Key the payMd5Key to set
	 */
	public void setPayMd5Key(String payMd5Key) {
		this.payMd5Key = payMd5Key;
	}

	/**
	 * @return the payAesKey
	 */
	public String getPayAesKey() {
		return payAesKey;
	}

	/**
	 * @param payAesKey the payAesKey to set
	 */
	public void setPayAesKey(String payAesKey) {
		this.payAesKey = payAesKey;
	}

	/**
	 * @return the payUrl
	 */
	public String getPayUrl() {
		return payUrl;
	}

	/**
	 * @param payUrl the payUrl to set
	 */
	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}

	/**
	 * @return the payQueryUrl
	 */
	public String getPayQueryUrl() {
		return payQueryUrl;
	}

	/**
	 * @param payQueryUrl the payQueryUrl to set
	 */
	public void setPayQueryUrl(String payQueryUrl) {
		this.payQueryUrl = payQueryUrl;
	}

    public void setIsNormalPay(Integer isNormalPay) {
        this.isNormalPay = isNormalPay;
    }

    public Integer getIsNormalPay() {
        return isNormalPay;
    }
}

package cn.com.gome.dujia.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * Description : sap数据
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月20日 下午4:25:32 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public class SapData implements Serializable {

	/**
	 * @author WenJie Mai
	 *
	 * Created Time : 2016年4月20日 下午4:25:38 <br/>
	 */
	private static final long serialVersionUID = -2295579642520157149L;
	
	/**
	 * sap类型(0:收款;1:收入)
	 */
	private Integer sapType;
	
	/**
	 * 订单类型(0:正向;1:逆向)
	 */
	private Integer orderType;
	
	/**
	 * 订单id
	 */
	private String orderId;
	
	/**
	 * 供应商订单号
	 */
	private String venderOrderId;
	
	/**
     * 支付供应商交易流水号
     */
	private String merchantNo;
	
	/**
	 * 支付id
	 */
	private String orderPayId;
	
	/**
	 * 退款id
	 */
	private String refundId;
	
	 /**
     * 退款银行交易流水号
     */
    private String refundBankTransNum;
    
    /**
     * 购买数量
     */
    private Integer ticketNum;
	
	/**
	 * 支付方式
	 */
	private String payModeSap;
	
	/**
	 * 支付金额
	 */
	private BigDecimal payAmount;
	
	/**
	 * 订单金额
	 */
	private BigDecimal orderAmount;
	
	/**
	 * 支付时间
	 */
	private Date payTime;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/*
	 * 是否重复支付
	 */
	private Boolean isRepeatPay;
	
	/**
	 * 凭证日期
	 */
	private String BLDAT;
	
	/**
	 * 交易参考号
	 */
	private String ZJYCK;
	
	/**
	 * 订单类型(正向:SO;逆向:RO)
	 */
	private String ZSAUT;
	
	/**
	 * 收款类型(10:正常收款;11:异常收款;21:原路返回)
	 */
	private String ZYWLX;
	
	/**
	 * 订单销售来源(需要业务申请)
	 */
	private String ZXSLY;
	
	/**
	 * 销售渠道类型(25虚拟业务)
	 */
	private String ZSKLX;
	
	/**
	 * 调用接口日期
	 */
	private String BUDAT;
	
	/**
	 * 合同商编码
	 */
	private String ZYYLM;
	
	/**
	 * 店铺编号
	 */
	private String LIFNR;
	
	/**
	 * 销售站点来源
	 */
	private String ZSITE;
	
	/**
	 * SKU编号
	 */
	private String MATNR;
	
	/**
	 * SKU名称
	 */
	private String MAKTX;
	
	/**
	 * 商品品类
	 */
	private String MATKL;
	
	/**
	 * 商品品牌
	 */
	private String ZPP;
	
	/**
	 * 销售数量
	 */
	private Integer ZMENGE;
	
	/**
	 * 单价
	 */
	private String NETWR;
	
	/**
	 * 实收用户金额
	 */
	private String ZYSZK;
	
	/**
	 * 佣金计算方案
	 */
	private String ZJSFA;
	
	/**
	 * 第三方订单号
	 */
	private String ZDSFH1;
	
	/**
	 * 价格类型(销售单价)
	 */
	private String ZPRTY_SALE_PRICE;
	
	/**
	 * 价格类型(应收金额)
	 */
	private String ZPRTY_AMOUNT;
	
	/**
	 * 金额
	 */
	private String ZPRJE;
	
	/**
	 * 支付ID
	 */
	private String ZPAYID;

	/**
	 * @return the sapType
	 */
	public Integer getSapType() {
		return sapType;
	}

	/**
	 * @param sapType the sapType to set
	 */
	public void setSapType(Integer sapType) {
		this.sapType = sapType;
	}

	/**
	 * @return the orderType
	 */
	public Integer getOrderType() {
		return orderType;
	}

	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the venderOrderId
	 */
	public String getVenderOrderId() {
		return venderOrderId;
	}

	/**
	 * @param venderOrderId the venderOrderId to set
	 */
	public void setVenderOrderId(String venderOrderId) {
		this.venderOrderId = venderOrderId;
	}

	/**
	 * @return the merchantNo
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * @param merchantNo the merchantNo to set
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * @return the orderPayId
	 */
	public String getOrderPayId() {
		return orderPayId;
	}

	/**
	 * @param orderPayId the orderPayId to set
	 */
	public void setOrderPayId(String orderPayId) {
		this.orderPayId = orderPayId;
	}

	/**
	 * @return the refundId
	 */
	public String getRefundId() {
		return refundId;
	}

	/**
	 * @param refundId the refundId to set
	 */
	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	/**
	 * @return the refundBankTransNum
	 */
	public String getRefundBankTransNum() {
		return refundBankTransNum;
	}

	/**
	 * @param refundBankTransNum the refundBankTransNum to set
	 */
	public void setRefundBankTransNum(String refundBankTransNum) {
		this.refundBankTransNum = refundBankTransNum;
	}
	
	/**
	 * @return the ticketNum
	 */
	public Integer getTicketNum() {
		return ticketNum;
	}

	/**
	 * @param ticketNum the ticketNum to set
	 */
	public void setTicketNum(Integer ticketNum) {
		this.ticketNum = ticketNum;
	}

	/**
	 * @return the payModeSap
	 */
	public String getPayModeSap() {
		return payModeSap;
	}

	/**
	 * @param payModeSap the payModeSap to set
	 */
	public void setPayModeSap(String payModeSap) {
		this.payModeSap = payModeSap;
	}

	/**
	 * @return the payAmount
	 */
	public BigDecimal getPayAmount() {
		return payAmount;
	}

	/**
	 * @param payAmount the payAmount to set
	 */
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	/**
	 * @return the orderAmount
	 */
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	/**
	 * @param orderAmount the orderAmount to set
	 */
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * @return the payTime
	 */
	public Date getPayTime() {
		return payTime;
	}

	/**
	 * @param payTime the payTime to set
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the isRepeatPay
	 */
	public Boolean getIsRepeatPay() {
		return isRepeatPay;
	}

	/**
	 * @param isRepeatPay the isRepeatPay to set
	 */
	public void setIsRepeatPay(Boolean isRepeatPay) {
		this.isRepeatPay = isRepeatPay;
	}

	/**
	 * @return the bLDAT
	 */
	public String getBLDAT() {
		return BLDAT;
	}

	/**
	 * @param bLDAT the bLDAT to set
	 */
	public void setBLDAT(String bLDAT) {
		BLDAT = bLDAT;
	}

	/**
	 * @return the zJYCK
	 */
	public String getZJYCK() {
		return ZJYCK;
	}

	/**
	 * @param zJYCK the zJYCK to set
	 */
	public void setZJYCK(String zJYCK) {
		ZJYCK = zJYCK;
	}

	/**
	 * @return the zSAUT
	 */
	public String getZSAUT() {
		return ZSAUT;
	}

	/**
	 * @param zSAUT the zSAUT to set
	 */
	public void setZSAUT(String zSAUT) {
		ZSAUT = zSAUT;
	}

	/**
	 * @return the zYWLX
	 */
	public String getZYWLX() {
		return ZYWLX;
	}

	/**
	 * @param zYWLX the zYWLX to set
	 */
	public void setZYWLX(String zYWLX) {
		ZYWLX = zYWLX;
	}

	/**
	 * @return the zXSLY
	 */
	public String getZXSLY() {
		return ZXSLY;
	}

	/**
	 * @param zXSLY the zXSLY to set
	 */
	public void setZXSLY(String zXSLY) {
		ZXSLY = zXSLY;
	}

	/**
	 * @return the zSKLX
	 */
	public String getZSKLX() {
		return ZSKLX;
	}

	/**
	 * @param zSKLX the zSKLX to set
	 */
	public void setZSKLX(String zSKLX) {
		ZSKLX = zSKLX;
	}

	/**
	 * @return the bUDAT
	 */
	public String getBUDAT() {
		return BUDAT;
	}

	/**
	 * @param bUDAT the bUDAT to set
	 */
	public void setBUDAT(String bUDAT) {
		BUDAT = bUDAT;
	}

	/**
	 * @return the zYYLM
	 */
	public String getZYYLM() {
		return ZYYLM;
	}

	/**
	 * @param zYYLM the zYYLM to set
	 */
	public void setZYYLM(String zYYLM) {
		ZYYLM = zYYLM;
	}

	/**
	 * @return the lIFNR
	 */
	public String getLIFNR() {
		return LIFNR;
	}

	/**
	 * @param lIFNR the lIFNR to set
	 */
	public void setLIFNR(String lIFNR) {
		LIFNR = lIFNR;
	}

	/**
	 * @return the zSITE
	 */
	public String getZSITE() {
		return ZSITE;
	}

	/**
	 * @param zSITE the zSITE to set
	 */
	public void setZSITE(String zSITE) {
		ZSITE = zSITE;
	}

	/**
	 * @return the mATNR
	 */
	public String getMATNR() {
		return MATNR;
	}

	/**
	 * @param mATNR the mATNR to set
	 */
	public void setMATNR(String mATNR) {
		MATNR = mATNR;
	}

	/**
	 * @return the mAKTX
	 */
	public String getMAKTX() {
		return MAKTX;
	}

	/**
	 * @param mAKTX the mAKTX to set
	 */
	public void setMAKTX(String mAKTX) {
		MAKTX = mAKTX;
	}

	/**
	 * @return the mATKL
	 */
	public String getMATKL() {
		return MATKL;
	}

	/**
	 * @param mATKL the mATKL to set
	 */
	public void setMATKL(String mATKL) {
		MATKL = mATKL;
	}

	/**
	 * @return the zPP
	 */
	public String getZPP() {
		return ZPP;
	}

	/**
	 * @param zPP the zPP to set
	 */
	public void setZPP(String zPP) {
		ZPP = zPP;
	}

	/**
	 * @return the zMENGE
	 */
	public Integer getZMENGE() {
		return ZMENGE;
	}

	/**
	 * @param zMENGE the zMENGE to set
	 */
	public void setZMENGE(Integer zMENGE) {
		ZMENGE = zMENGE;
	}

	/**
	 * @return the nETWR
	 */
	public String getNETWR() {
		return NETWR;
	}

	/**
	 * @param nETWR the nETWR to set
	 */
	public void setNETWR(String nETWR) {
		NETWR = nETWR;
	}

	/**
	 * @return the zYSZK
	 */
	public String getZYSZK() {
		return ZYSZK;
	}

	/**
	 * @param zYSZK the zYSZK to set
	 */
	public void setZYSZK(String zYSZK) {
		ZYSZK = zYSZK;
	}

	/**
	 * @return the zJSFA
	 */
	public String getZJSFA() {
		return ZJSFA;
	}

	/**
	 * @param zJSFA the zJSFA to set
	 */
	public void setZJSFA(String zJSFA) {
		ZJSFA = zJSFA;
	}

	/**
	 * @return the zDSFH1
	 */
	public String getZDSFH1() {
		return ZDSFH1;
	}

	/**
	 * @param zDSFH1 the zDSFH1 to set
	 */
	public void setZDSFH1(String zDSFH1) {
		ZDSFH1 = zDSFH1;
	}
	
	/**
	 * @return the zPRTY_SALE_PRICE
	 */
	public String getZPRTY_SALE_PRICE() {
		return ZPRTY_SALE_PRICE;
	}

	/**
	 * @param zPRTY_SALE_PRICE the zPRTY_SALE_PRICE to set
	 */
	public void setZPRTY_SALE_PRICE(String zPRTY_SALE_PRICE) {
		ZPRTY_SALE_PRICE = zPRTY_SALE_PRICE;
	}

	/**
	 * @return the zPRTY_AMOUNT
	 */
	public String getZPRTY_AMOUNT() {
		return ZPRTY_AMOUNT;
	}

	/**
	 * @param zPRTY_AMOUNT the zPRTY_AMOUNT to set
	 */
	public void setZPRTY_AMOUNT(String zPRTY_AMOUNT) {
		ZPRTY_AMOUNT = zPRTY_AMOUNT;
	}

	/**
	 * @return the zPRJE
	 */
	public String getZPRJE() {
		return ZPRJE;
	}

	/**
	 * @param zPRJE the zPRJE to set
	 */
	public void setZPRJE(String zPRJE) {
		ZPRJE = zPRJE;
	}

	/**
	 * @return the zPAYID
	 */
	public String getZPAYID() {
		return ZPAYID;
	}

	/**
	 * @param zPAYID the zPAYID to set
	 */
	public void setZPAYID(String zPAYID) {
		ZPAYID = zPAYID;
	}
	
}

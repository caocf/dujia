package cn.com.gome.dujia.vo.sap;

import java.io.Serializable;
/**
 * 
 * Description : sap收入回调接口接收参数
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月29日 下午2:21:22 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public class SapIncomeResultItem implements Serializable {

	/**
	 * @author WenJie Mai
	 *
	 * Created Time : 2016年4月29日 下午2:22:02 <br/>
	 */
	private static final long serialVersionUID = -3251016378300445734L;

	/**
	 * xml交易ID
	 */
	private String transaction_ID;
	
	/**
	 * 销售订单号
	 */
	private String ZXNDDH; 
	
	/**
	 * 行项目状态
	 */
	private String transaction_status; 
	
	/**
	 * 描述
	 */
	private String transaction_dsp;

	public String getTransaction_ID() {
		return transaction_ID;
	}

	public void setTransaction_ID(String transaction_ID) {
		this.transaction_ID = transaction_ID;
	}

	public String getZXNDDH() {
		return ZXNDDH;
	}

	public void setZXNDDH(String zXNDDH) {
		ZXNDDH = zXNDDH;
	}

	public String getTransaction_status() {
		return transaction_status;
	}

	public void setTransaction_status(String transaction_status) {
		this.transaction_status = transaction_status;
	}

	public String getTransaction_dsp() {
		return transaction_dsp;
	}

	public void setTransaction_dsp(String transaction_dsp) {
		this.transaction_dsp = transaction_dsp;
	} 
}

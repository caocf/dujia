package cn.com.gome.dujia.dto;

import java.util.List;
import cn.com.gome.dujia.model.Order;
/**
 * 
 * Description : 用户订单返回对象
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年5月6日 上午10:48:33 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public class QueryUserOrderResp extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5123209704816588725L;

	/**
	 * 搜索记录总数
	 */
	private int tc;

	/**
	 * 每页记录数
	 */
	private int ps;

	/**
	 * 当前页
	 */
	private int pi;

	/**
	 * 总页数
	 */
	private int tp;
	/**
	 * 用户订单列表
	 */
	private List<Order> ol;

	public int getTc() {
		return tc;
	}

	public void setTc(int tc) {
		this.tc = tc;
	}

	public int getPs() {
		return ps;
	}

	public void setPs(int ps) {
		this.ps = ps;
	}

	public int getPi() {
		return pi;
	}

	public void setPi(int pi) {
		this.pi = pi;
	}

	public int getTp() {
		return tp;
	}

	public void setTp(int tp) {
		this.tp = tp;
	}

	public List<Order> getOl() {
		return ol;
	}

	public void setOl(List<Order> ol) {
		this.ol = ol;
	}

}

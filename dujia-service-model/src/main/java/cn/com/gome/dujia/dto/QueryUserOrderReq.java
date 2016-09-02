package cn.com.gome.dujia.dto;

import java.util.Date;
import java.util.TimeZone;
import org.apache.commons.lang3.time.DateUtils;
/**
 * 
 * Description : 用户订单请求对象
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年5月6日 上午10:45:13 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public class QueryUserOrderReq extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -538535832896928362L;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 订单编号
	 */
	private String orderNo;

	/**
	 * 日期类型 1.近三个月 2.一年内的 3.大于一年
	 */
	private int dateType = 1;

	/**
	 * dateType == 1 三个月前日期，dateType == 2 六个月前日期
	 */
	private Date dateBeforeNow;

	/**
	 * 
	 */
	private Date dateAfterNow;
	/**
	 * 当前页
	 */
	private int pageIndex = 1;

	/**
	 * 每页记录数
	 */
	private int pageSize = 8;

	/**
	 * 国美订单状态
	 */
	private Integer orderStatus;

	public Date getDateBeforeNow() {

		long current = System.currentTimeMillis();// 当前时间毫秒数
		long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();// 今天零点零分零秒的毫秒数

		if (getDateType() == 2) {
			// 一年内的日期
			this.dateBeforeNow = DateUtils.addDays(new Date(zero), -365);
		} else if (getDateType() == 3) {
			// 一年以上的
			this.dateAfterNow = DateUtils.addDays(new Date(zero), -365);
		} else {
			// 三个月前日期
			this.dateBeforeNow = DateUtils.addDays(new Date(zero), -90);
		}

		return dateBeforeNow;
	}

	public void setDateBeforeNow(Date dateBeforeNow) {
		this.dateBeforeNow = dateBeforeNow;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getDateType() {
		return dateType;
	}

	public void setDateType(int dateType) {
		this.dateType = dateType;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getDateAfterNow() {
		return dateAfterNow;
	}

	public void setDateAfterNow(Date dateAfterNow) {
		this.dateAfterNow = dateAfterNow;
	}

	/**
	 * 分页查询开始记录
	 * 
	 * @return
	 */
	public int getStartRecord() {
		return (pageIndex - 1) * pageSize;
	}

}

package cn.com.gome.dujia.vo.order;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
/**
 * @author wangweiran
 *
 * 查询订单请求参数
 */
public class QueryOrderRequest implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 订单流水号/客户订单号
	 */
	@JacksonXmlProperty(localName = "OrderId")
	private String orderId;
	/**
	 * 订单状态：默认状态-1，待确认库存 0，待支付 1，已取消 2，已支付 3，待同程确认5，同程已确认 10，
	 * 部分退款(有人出游)15，申请全额退款 20，全额退款结束 25，部分退款结束 30，已结算 35
	 */
	@JacksonXmlProperty(localName = "OrderStatus")
	private Integer orderStatus;
	/**
	 * 线路标题
	 */
	@JacksonXmlProperty(localName = "ProductTitle")
	private String productTitle;
	/**
	 * 查询开始时间，以出游开始日期为准，默认'1900-01-01 00:00:00'
	 */
	@JacksonXmlProperty(localName = "StartTime")
	private Date startTime;
	/**
	 * 查询结束时间，以出游开始日期为准，默认当前时间加一年
	 */
	@JacksonXmlProperty(localName = "EndTime")
	private Date endTime;
	/**
	 * 查询开始时间， 以创建日期为准， 默认'1900-01-01 00:00:00'
	 */
	@JacksonXmlProperty(localName = "CreateStartTime")
	private Date createStartTime;
	/**
	 * 查询结束时间，以创建日期为准，默认当前时间
	 */
	@JacksonXmlProperty(localName = "CreateEndTime")
	private Date createEndTime;
	/**
	 * 当前页码，默认值：1
	 */
	@JacksonXmlProperty(localName = "PageIndex")
	private Integer pageIndex = 1;
	/**
	 * 每页条数，默认值：10
	 */
	@JacksonXmlProperty(localName = "PageSize")
	private Integer pageSize = 10;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getCreateStartTime() {
		return createStartTime;
	}
	public void setCreateStartTime(Date createStartTime) {
		this.createStartTime = createStartTime;
	}
	public Date getCreateEndTime() {
		return createEndTime;
	}
	public void setCreateEndTime(Date createEndTime) {
		this.createEndTime = createEndTime;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}

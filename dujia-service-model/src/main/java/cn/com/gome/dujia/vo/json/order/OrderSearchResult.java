package cn.com.gome.dujia.vo.json.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangweiran
 * 
 * 获取同程订单返回信息
 */
public class OrderSearchResult implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 是否查询成功
	 */
	@JsonProperty(value = "IsSuccess")
	private Boolean isSuccess;
	/**
	 * 错误信息；成功消息为空
	 */
	@JsonProperty(value = "Message")
	private Integer message;
	/**
	 * 当前页码
	 */
	@JsonProperty(value = "PageIndex")
	private String pageIndex;
	/**
	 * 每页条数
	 */
	@JsonProperty(value = "PageSize")
	private Integer pageSize;
	/**
	 * 查询总条数
	 */
	@JsonProperty(value = "TotalCount")
	private String totalCount;
	/**
	 * 订单信息
	 */
	@JsonProperty(value = "OrderInfos")
	private List<OrderInfoModel> orderInfos;
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public Integer getMessage() {
		return message;
	}
	public void setMessage(Integer message) {
		this.message = message;
	}
	public String getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public List<OrderInfoModel> getOrderInfos() {
		return orderInfos;
	}
	public void setOrderInfos(List<OrderInfoModel> orderInfos) {
		this.orderInfos = orderInfos;
	}
}

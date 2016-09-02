package cn.com.gome.dujia.dto;

import java.util.List;

import cn.com.gome.dujia.model.OrderDetail;
import cn.com.gome.trip.unite.model.UserConcat;
import cn.com.gome.trip.unite.model.UserTraveler;

/**
 * @author wangweiran
 *
 * 提交订单请求参数DTO
 */
public class OrderDto extends BaseDto {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	private String userId;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 线路id
	 */
	private String productId;
	
	/**
	 * 套餐id
	 */
	private String packageId;
	
	/**
	 * 出游成人人数
	 */
	private Integer adultNum;
	
	/**
	 * 出游儿童人数
	 */
	private Integer childNum;
	
	/**
	 * 套餐数量
	 */
	private Integer buyCount;
	
	/**
	 * 出行人列表
	 */
	private List<UserTraveler> travelerList;
	
	/**
	 * 联系人信息
	 */
	private UserConcat userConcat;
	
	/**
	 * 订单-游玩详情
	 */
	private List<OrderDetail> orderDetails;

	/**
	 * 预定套餐开始日期
	 */
	private String saleDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public Integer getAdultNum() {
		return adultNum;
	}

	public void setAdultNum(Integer adultNum) {
		this.adultNum = adultNum;
	}

	public Integer getChildNum() {
		return childNum;
	}

	public void setChildNum(Integer childNum) {
		this.childNum = childNum;
	}

	public Integer getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}

	public List<UserTraveler> getTravelerList() {
		return travelerList;
	}

	public void setTravelerList(List<UserTraveler> travelerList) {
		this.travelerList = travelerList;
	}

	public UserConcat getUserConcat() {
		return userConcat;
	}

	public void setUserConcat(UserConcat userConcat) {
		this.userConcat = userConcat;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public String getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}
}

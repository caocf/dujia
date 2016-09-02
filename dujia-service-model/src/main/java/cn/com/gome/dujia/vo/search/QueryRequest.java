package cn.com.gome.dujia.vo.search;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.QueryParam;

/**
 * 搜索请求参数
 * 
 * @author xiongyan
 * @date 2016年4月14日 上午11:51:09
 */
public class QueryRequest implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 关键字（中文 或者 英文）
     */
	@QueryParam("keyword")
    private String keyword;
	
	/**
     * 当前城市拼音（拼音全拼）
     */
	@QueryParam("cityPy")
	private String cityPy;
	
	/**
	 * 当前城市ID（城市id）
	 */
	@QueryParam("cityId")
	private String cityId;
    
    /**
     * 主题（拼音简拼）
     */
	@QueryParam("title")
    private String title;
    
    /**
     * 行程天数（数字）
     */
	@QueryParam("days")
    private Integer days;
    
    /**
     * 景点（景点资源id）
     */
	@QueryParam("scenic")
    private String scenic;
	
	/**
	 * 酒店等级（拼音简拼）
	 */
	@QueryParam("hotelGrade")
	private String hotelGrade;
	
	/**
	 * 目的地城市ID（城市id）
	 */
	@QueryParam("targetCityId")
	private String targetCityId;
    
    /**
     * 排序字段
     */
	@QueryParam("sortField")
    private String sortField;
	
	/**
	 * 排序顺序
	 */
	@QueryParam("sortOrder")
	private String sortOrder; 
    
    /**
     * 开始页
     */
	@QueryParam("pageIndex")
    private Integer pageIndex;
    
    /**
     * 每页数量
     */
	@QueryParam("pageSize")
    private Integer pageSize;
	
	/**
	 * 最小价格
	 */
	@QueryParam("minPrice")
	private Integer minPrice;
	
	/**
	 * 最大价格
	 */
	@QueryParam("maxPrice")
	private Integer maxPrice;
	
	/**
	 * 模块编号
	 */
	@QueryParam("module")
	private String module;
	
	/**
	 * 排除已有的线路
	 */
	@QueryParam("productIds")
	private List<String> productIds;
	
	/**
	 * 主题信息
	 */
	@QueryParam("titles")
	private List<String> titles;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getCityPy() {
		return cityPy;
	}

	public void setCityPy(String cityPy) {
		this.cityPy = cityPy;
	}
	
	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}
	
	public String getScenic() {
		return scenic;
	}

	public void setScenic(String scenic) {
		this.scenic = scenic;
	}

	public String getHotelGrade() {
		return hotelGrade;
	}

	public void setHotelGrade(String hotelGrade) {
		this.hotelGrade = hotelGrade;
	}

	public String getTargetCityId() {
		return targetCityId;
	}

	public void setTargetCityId(String targetCityId) {
		this.targetCityId = targetCityId;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Integer getPageIndex() {
		if(null == pageIndex) {
			return 1;
		}
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		if(null == pageSize) {
			return 10;
		}
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}

	public Integer getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public List<String> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<String> productIds) {
		this.productIds = productIds;
	}

	public List<String> getTitles() {
		return titles;
	}

	public void setTitles(List<String> titles) {
		this.titles = titles;
	}

}

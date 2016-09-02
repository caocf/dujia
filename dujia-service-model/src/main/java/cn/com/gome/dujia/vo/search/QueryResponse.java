package cn.com.gome.dujia.vo.search;

import java.io.Serializable;
import java.util.List;

/**
 * 搜索列表响应数据
 * 
 * @author xiongyan
 * @date 2016年4月14日 上午11:51:26
 */
public class QueryResponse implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 请求
     */
    private QueryRequest request;
    
    /**
     * 主题结果集
     */
    private List<FilterItem> titles;
    
    /**
     * 景点结果集
     */
    private List<FilterItem> scenics;
    
    /**
     * 行程天数结果集
     */
    private List<FilterItem> days;
    
    /**
     * 酒店等级
     */
    private List<FilterItem> hotelGrades;
    
    /**
     * 目的地结果集
     */
    private List<FilterItem> citys;
    
    /**
     * 产品列表
     */
    private List<Product> products;
    
    /**
     * 搜索总数
     */
    private Long count;

	public QueryRequest getRequest() {
		return request;
	}

	public void setRequest(QueryRequest request) {
		this.request = request;
	}

	public List<FilterItem> getTitles() {
		return titles;
	}

	public void setTitles(List<FilterItem> titles) {
		this.titles = titles;
	}

	public List<FilterItem> getScenics() {
		return scenics;
	}

	public void setScenics(List<FilterItem> scenics) {
		this.scenics = scenics;
	}

	public List<FilterItem> getDays() {
		return days;
	}

	public void setDays(List<FilterItem> days) {
		this.days = days;
	}

	public List<FilterItem> getHotelGrades() {
		return hotelGrades;
	}

	public void setHotelGrades(List<FilterItem> hotelGrades) {
		this.hotelGrades = hotelGrades;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<FilterItem> getCitys() {
		return citys;
	}

	public void setCitys(List<FilterItem> citys) {
		this.citys = citys;
	}

}

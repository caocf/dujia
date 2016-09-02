package cn.com.gome.dujia.vo.search;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 产品列表信息
 * 
 * @author xiongyan
 * @date 2016年4月15日 下午1:52:37
 */
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 线路ID
     */
	private String productId;
	
	/**
     * 线路主标题
     */
    private String mainName;
    
    /**
     * 线路主标题 html上的title属性
     */
    private String mainTitle;
    
    /**
     * 线路副标题
     */
    private String subName;
    
    /**
     * 线路副标题 html上的title属性
     */
    private String subTitle;

    /**
     * App 标题
     */
    private String appTitle;

    /**
     * 行程天数
     */
    private Integer days;
    
    /**
     * 线程天数中文
     */
    private String daysName;

    /**
     * 线路开始时间
     */
    private String beginDate;

    /**
     * 线路结束时间
     */
    private String overDate;

    /**
     * 图片原图地址
     */
    private String imageUrl;
    
    /**
     * 首页图片地址
     */
    private String indexImageUrl;
    
    /**
     * 列表图片地址
     */
    private String listImageUrl;
    
    /**
     * 右侧图片地址
     */
    private String rightImageUrl;

    /**
     * 线路主目的地城市id
     */
    private String cityId;
    
    /**
     * 线路主目的地城市名称
     */
    private String cityName;

    /**
     * 线路最小价(同程价)
     */
    private BigDecimal productMinPrice;

    /**
     * 线路最大价(同程价)
     */
    private BigDecimal productMaxPrice;

    /**
     * 卖价单位：起/份(同程价)
     */
    private String sellUnit;
    
    /**
     * 销量
     */
    private Integer saleCount;
	
    /**
     * 浏览量
     */
	private Integer browseCount;
    
    /**
     * 主题结果集
     */
    private List<Map<String, Object>> titles;
    
    /**
     * 酒店结果集
     */
    private List<Map<String, Object>> hotels;
    
    /**
     * 景点结果集
     */
    private List<Map<String, Object>> scenics;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getMainName() {
		return mainName;
	}

	public void setMainName(String mainName) {
		this.mainName = mainName;
	}

	public String getMainTitle() {
		return mainTitle;
	}

	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getAppTitle() {
		return appTitle;
	}

	public void setAppTitle(String appTitle) {
		this.appTitle = appTitle;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public String getDaysName() {
		return daysName;
	}

	public void setDaysName(String daysName) {
		this.daysName = daysName;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getOverDate() {
		return overDate;
	}

	public void setOverDate(String overDate) {
		this.overDate = overDate;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getIndexImageUrl() {
		return indexImageUrl;
	}

	public void setIndexImageUrl(String indexImageUrl) {
		this.indexImageUrl = indexImageUrl;
	}

	public String getListImageUrl() {
		return listImageUrl;
	}

	public void setListImageUrl(String listImageUrl) {
		this.listImageUrl = listImageUrl;
	}

	public String getRightImageUrl() {
		return rightImageUrl;
	}

	public void setRightImageUrl(String rightImageUrl) {
		this.rightImageUrl = rightImageUrl;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public BigDecimal getProductMinPrice() {
		return productMinPrice;
	}

	public void setProductMinPrice(BigDecimal productMinPrice) {
		this.productMinPrice = productMinPrice;
	}

	public BigDecimal getProductMaxPrice() {
		return productMaxPrice;
	}

	public void setProductMaxPrice(BigDecimal productMaxPrice) {
		this.productMaxPrice = productMaxPrice;
	}

	public String getSellUnit() {
		return sellUnit;
	}

	public void setSellUnit(String sellUnit) {
		this.sellUnit = sellUnit;
	}

	public Integer getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(Integer saleCount) {
		this.saleCount = saleCount;
	}

	public Integer getBrowseCount() {
		return browseCount;
	}

	public void setBrowseCount(Integer browseCount) {
		this.browseCount = browseCount;
	}

	public List<Map<String, Object>> getTitles() {
		return titles;
	}

	public void setTitles(List<Map<String, Object>> titles) {
		this.titles = titles;
	}

	public List<Map<String, Object>> getHotels() {
		return hotels;
	}

	public void setHotels(List<Map<String, Object>> hotels) {
		this.hotels = hotels;
	}

	public List<Map<String, Object>> getScenics() {
		return scenics;
	}

	public void setScenics(List<Map<String, Object>> scenics) {
		this.scenics = scenics;
	}

}

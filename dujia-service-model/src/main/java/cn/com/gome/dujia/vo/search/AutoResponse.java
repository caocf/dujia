package cn.com.gome.dujia.vo.search;

import java.io.Serializable;
import java.util.List;

/**
 * 搜索自动联想响应数据
 * 
 * @author xiongyan
 * @date 2016年4月14日 上午11:51:26
 */
public class AutoResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	/**
	 * 城市结果集
	 */
	private List<Res> citys;
	
	/**
	 * 景点结果集
	 */
	private List<Res> scenics;
	
    /**
     * 酒店结果集
     */
    private List<Res> hotels;
    

	public List<Res> getHotels() {
		return hotels;
	}

	public void setHotels(List<Res> hotels) {
		this.hotels = hotels;
	}

	public List<Res> getScenics() {
		return scenics;
	}

	public void setScenics(List<Res> scenics) {
		this.scenics = scenics;
	}

	public List<Res> getCitys() {
		return citys;
	}

	public void setCitys(List<Res> citys) {
		this.citys = citys;
	}

}

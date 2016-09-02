package cn.com.gome.dujia.vo.search;

import java.io.Serializable;

/**
 * 搜索列表，聚合信息
 * 
 * @author xiongyan
 * @date 2016年4月15日 下午1:51:18
 */
public class FilterItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 名称（页面显示名称）
	 */
	private String name;
	
	/**
	 * 值（id 或者 拼音）
	 */
	private String value;
	
	/**
	 * 总数（聚合数量）
	 */
	private Long count;
	
	public FilterItem() {
		
	}
	
	public FilterItem(String name, String value, Long count) {
		this.name = name;
		this.value = value;
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}

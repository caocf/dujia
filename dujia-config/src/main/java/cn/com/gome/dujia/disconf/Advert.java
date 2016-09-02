package cn.com.gome.dujia.disconf;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 广告
 * 
 * @author xiongyan
 * @date 2016年4月22日 上午9:36:26
 */
public class Advert implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 名称
	 */
	@JsonProperty(value = "name")
	private String name;
	
	/**
	 * 值
	 */
	@JsonProperty(value = "value")
	private String value;
	
	/**
	 * 模块
	 */
	@JsonProperty(value = "module")
	private String module;
	
	/**
	 * 位置
	 */
	@JsonProperty(value = "positions")
	private List<Advert> positions;
	
	/**
	 * 标签
	 */
	@JsonProperty(value = "labels")
	private List<Advert> labels;

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

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public List<Advert> getPositions() {
		return positions;
	}

	public void setPositions(List<Advert> positions) {
		this.positions = positions;
	}

	public List<Advert> getLabels() {
		return labels;
	}

	public void setLabels(List<Advert> labels) {
		this.labels = labels;
	}

}

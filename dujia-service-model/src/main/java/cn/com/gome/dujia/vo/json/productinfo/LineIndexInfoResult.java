package cn.com.gome.dujia.vo.json.productinfo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import cn.com.gome.dujia.model.ZbyLine;

/**
 * 获取线路套餐Id 列表 结果
 * 
 * @author xiongyan
 * @date 2016年5月12日 下午5:23:19
 */
public class LineIndexInfoResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "LineInfoList")
	private List<ZbyLine> lines;

	public List<ZbyLine> getLines() {
		return lines;
	}

	public void setLines(List<ZbyLine> lines) {
		this.lines = lines;
	}

}

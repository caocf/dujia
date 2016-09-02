package cn.com.gome.dujia.vo.json.productinfo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 增量线路套餐id 列表 结果
 * 
 * @author xiongyan
 * @date 2016年5月12日 下午5:23:19
 */
public class IncrementProductListResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "EndIncrementId")
	private String endIncrementId;
	
	@JsonProperty(value = "ProductList")
	private List<IncrementProduct> productList;

	public String getEndIncrementId() {
		return endIncrementId;
	}

	public void setEndIncrementId(String endIncrementId) {
		this.endIncrementId = endIncrementId;
	}

	public List<IncrementProduct> getProductList() {
		return productList;
	}

	public void setProductList(List<IncrementProduct> productList) {
		this.productList = productList;
	}

}

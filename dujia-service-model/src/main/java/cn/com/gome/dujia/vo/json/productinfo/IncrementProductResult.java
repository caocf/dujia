package cn.com.gome.dujia.vo.json.productinfo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sunming on 2016/5/12.
 */
public class IncrementProductResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<IncrementProduct> productList;

    @JsonProperty(value = "ProductList")
    public List<IncrementProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<IncrementProduct> productList) {
        this.productList = productList;
    }
}

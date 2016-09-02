package cn.com.gome.dujia.vo.json.productinfo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sunming on 2016/5/5.
 */
public class IncrementProduct implements Serializable  {

    private static final long serialVersionUID = 1L;


    @JsonProperty(value = "ProductId")
    private String productId;

    @JsonProperty(value = "PackageIdList")
    private List<String> packageIdList;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public List<String> getPackageIdList() {
        return packageIdList;
    }

    public void setPackageIdList(List<String> packageIdList) {
        this.packageIdList = packageIdList;
    }
}

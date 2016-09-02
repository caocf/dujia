package cn.com.gome.dujia.vo.json.productinfo;

import java.io.Serializable;

/**
 * Created by sunming on 2016/5/5.
 */
public class RequestPara implements Serializable {
    private static final long serialVersionUID = 1L;

    private String productIds;
    private String productPriceIds;

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }

    public String getProductPriceIds() {
        return productPriceIds;
    }

    public void setProductPriceIds(String productPriceIds) {
        this.productPriceIds = productPriceIds;
    }
}

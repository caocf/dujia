package cn.com.gome.dujia.vo.json.productinfo;

import cn.com.gome.dujia.model.ZbyProduct;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sunming on 2016/4/26.
 */
public class GetProductDetailInfoResultInner implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "LineProductDetailList")
    private List<ZbyProduct> lineProductDetailList;

    public List<ZbyProduct> getLineProductDetailList() {
        return lineProductDetailList;
    }

    public void setLineProductDetailList(List<ZbyProduct> lineProductDetailList) {
        this.lineProductDetailList = lineProductDetailList;
    }
}

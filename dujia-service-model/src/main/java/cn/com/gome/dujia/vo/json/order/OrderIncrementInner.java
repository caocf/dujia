package cn.com.gome.dujia.vo.json.order;

import cn.com.gome.dujia.model.ZbyProduct;
import cn.com.gome.dujia.vo.order.OrderIncrementInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sunming on 2016/4/26.
 */
public class OrderIncrementInner implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "OrderIncrementList")
    private List<OrderIncrementInfo> orderIncrementList;

    public List<OrderIncrementInfo> getOrderIncrementList() {
        return orderIncrementList;
    }

    public void setOrderIncrementList(List<OrderIncrementInfo> orderIncrementList) {
        this.orderIncrementList = orderIncrementList;
    }
}

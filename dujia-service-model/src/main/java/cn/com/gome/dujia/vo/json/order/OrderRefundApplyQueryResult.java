package cn.com.gome.dujia.vo.json.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sunming on 2016/4/26.
 */
public class OrderRefundApplyQueryResult implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonProperty(value="MaxApplyCount")
    private String MaxApplyCount;

    @JsonProperty(value="RefundChangeApplyList")
    private List<RefundorChangeDetail> refundChangeApplyList;

    public String getMaxApplyCount() {
        return MaxApplyCount;
    }

    public void setMaxApplyCount(String maxApplyCount) {
        MaxApplyCount = maxApplyCount;
    }

    public List<RefundorChangeDetail> getRefundChangeApplyList() {
        return refundChangeApplyList;
    }

    public void setRefundChangeApplyList(List<RefundorChangeDetail> refundChangeApplyList) {
        this.refundChangeApplyList = refundChangeApplyList;
    }
}

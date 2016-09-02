package cn.com.gome.dujia.vo.json.order;

import cn.com.gome.dujia.vo.json.productinfo.PackageDetail;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sunming on 2016/4/26.
 */
public class LineSaleInfoCalenderResult   implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 商品套餐详情信息列表
     */
    @JsonProperty(value = "PackageDetails")
    private List<PackageDetail> packageDetails;
    /**
     * 商品 id
     */
    @JsonProperty(value = "LineId")
    private String lineId;
    public List<PackageDetail> getPackageDetails() {
        return packageDetails;
    }
    public void setPackageDetails(List<PackageDetail> packageDetails) {
        this.packageDetails = packageDetails;
    }
    public String getLineId() {
        return lineId;
    }
    public void setLineId(String lineId) {
        this.lineId = lineId;
    }
}

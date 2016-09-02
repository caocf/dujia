package cn.com.gome.dujia.vo.json.productinfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gome.plan.tools.utils.DateUtils;
import com.gome.plan.tools.utils.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by sunming on 2016/4/26.
 */
public class ResourcesProductDate implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty(value="LineProductId")
    private String lineProductId;
    @JsonProperty(value="SupplierId")
    private String supplierId;
    @JsonProperty(value="ResourceId")
    private String resourceId;
    @JsonProperty(value="ResProId")
    private String resProId;
    @JsonProperty(value="ResType")
    private String resType;
    @JsonProperty(value="SupPolicyId")
    private String supPolicyId;
    @JsonProperty(value="Days")
    private String daysRaw;

    private String days;

    @JsonProperty(value="ListDays")
    private List<String> ListDays;
    @JsonProperty(value="TcPriceInfos")
    private List<TcPriceInfo> tcPriceInfos;


    public String getDaysRaw() {
        return daysRaw;
    }

    public void setDaysRaw(String daysRaw) {
        this.daysRaw = daysRaw;
    }

    public String getLineProductId() {
        return lineProductId;
    }

    public void setLineProductId(String lineProductId) {
        this.lineProductId = lineProductId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResProId() {
        return resProId;
    }

    public void setResProId(String resProId) {
        this.resProId = resProId;
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }

    public String getSupPolicyId() {
        return supPolicyId;
    }

    public void setSupPolicyId(String supPolicyId) {
        this.supPolicyId = supPolicyId;
    }

    public String getDays() {
        if (StringUtils.isNotEmpty(getDaysRaw()) && getDaysRaw().length() > 18) {
            return DateUtils.format(new Date(Long.valueOf(getDaysRaw().substring(6, 19))), DateUtils.SHORT_FORMAT);
        }
        return "";
    }

    public void setDays(String days) {
        this.days = days;
    }

    public List<String> getListDays() {
        return ListDays;
    }

    public void setListDays(List<String> listDays) {
        ListDays = listDays;
    }

    public List<TcPriceInfo> getTcPriceInfos() {
        return tcPriceInfos;
    }

    public void setTcPriceInfos(List<TcPriceInfo> tcPriceInfos) {
        this.tcPriceInfos = tcPriceInfos;
    }
}

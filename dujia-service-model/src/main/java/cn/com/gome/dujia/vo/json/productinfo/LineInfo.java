package cn.com.gome.dujia.vo.json.productinfo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sunming on 2016/4/19.
 */
public class LineInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "LineId")
    private String lineId;

    @JsonProperty(value = "MainName")
    private String mainName;


    @JsonProperty(value = "CityName")
    private String cityName;

    @JsonProperty(value = "ProvinceName")
    private String provinceName;
    
    @JsonProperty(value = "PackageIdList")
    private List<String> packageIdList;

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<String> getPackageIdList() {
        return packageIdList;
    }
    public void setPackageIdList(List<String> packageIdList) {
        this.packageIdList = packageIdList;
    }
}

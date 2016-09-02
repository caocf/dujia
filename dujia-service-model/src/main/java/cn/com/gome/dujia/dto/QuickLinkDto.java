package cn.com.gome.dujia.dto;

import java.io.Serializable;

/**
 * Created by zhaoxiang-ds on 2016/4/25.
 */
public class QuickLinkDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String value;

    private String url;

    private String cityId;

    private String cityName;

    private String cityPy;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityPy() {
        return cityPy;
    }

    public void setCityPy(String cityPy) {
        this.cityPy = cityPy;
    }
}

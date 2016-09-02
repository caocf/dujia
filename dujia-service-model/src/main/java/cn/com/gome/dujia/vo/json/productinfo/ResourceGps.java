package cn.com.gome.dujia.vo.json.productinfo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by sunming on 2016/4/21.
 */
public class ResourceGps implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty(value= "GpsType")
    private Integer gpsType;

    @JsonProperty(value= "Lon")
    private String lon;

    @JsonProperty(value= "Lat")
    private String lat;

    public Integer getGpsType() {
        return gpsType;
    }

    public void setGpsType(Integer gpsType) {
        this.gpsType = gpsType;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}

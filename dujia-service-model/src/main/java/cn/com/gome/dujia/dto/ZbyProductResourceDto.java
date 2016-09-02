package cn.com.gome.dujia.dto;

import java.io.Serializable;

/**
 * 资源-DTO
 * Created by zhaoxiang-ds on 2016/4/21.
 */
public class ZbyProductResourceDto implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 资源id
     */
    private String resId;
    
    /**
     * 关联id
     */
    private String relatedId;

    /**
     * 资源名称
     */
    private String resName;
    
    /**
     * 资源产品名称
     */
    private String resProName;

    /**
     * 资源类型:0酒店，1景区
     */
    private String resType;

    /**
     * gps类型
     */
    private String gpsType;

    /**
     * 经度
     */
    private String lon;

    /**
     * 纬度
     */
    private String lat;

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(String relatedId) {
		this.relatedId = relatedId;
	}

	public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }

    public String getGpsType() {
        return gpsType;
    }

    public void setGpsType(String gpsType) {
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

	public String getResProName() {
		return resProName;
	}

	public void setResProName(String resProName) {
		this.resProName = resProName;
	}
}

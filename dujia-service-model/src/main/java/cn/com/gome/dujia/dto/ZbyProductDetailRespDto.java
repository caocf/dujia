package cn.com.gome.dujia.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 线路详情DTO
 * Created by zhaoxiang-ds on 2016/4/21.
 */
public class ZbyProductDetailRespDto implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 线路详情
     */
    private ZbyProductDetailDto detail;

    /**
     * 线路资源信息(酒店、景区)
     */
    private List<ZbyProductResourceDto> resource;

    /**
     * 线路酒店
     */
    private List<ZbyProductResourceDto> hotels;
    /**
     * 线路景区
     */
    private List<ZbyProductResourceDto> sceneries;

    /**
     * 线路所属主题
     */
    private List<String> theme;

    /**
     * 线路图片，map<小图url,大图url>
     */
    private Map<String, String> imgUrl;

    /**
     * 线路是否下架
     */
    private boolean isDelete;


    public ZbyProductDetailDto getDetail() {
        return detail;
    }

    public void setDetail(ZbyProductDetailDto detail) {
        this.detail = detail;
    }

    public List<ZbyProductResourceDto> getResource() {
        return resource;
    }

    public void setResource(List<ZbyProductResourceDto> resource) {
        this.resource = resource;
    }

    public List<String> getTheme() {
        return theme;
    }

    public void setTheme(List<String> theme) {
        this.theme = theme;
    }

    public Map<String, String> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(Map<String, String> imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<ZbyProductResourceDto> getHotels() {
        return hotels;
    }

    public void setHotels(List<ZbyProductResourceDto> hotels) {
        this.hotels = hotels;
    }

    public List<ZbyProductResourceDto> getSceneries() {
        return sceneries;
    }

    public void setSceneries(List<ZbyProductResourceDto> sceneries) {
        this.sceneries = sceneries;
    }

    public boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }
}

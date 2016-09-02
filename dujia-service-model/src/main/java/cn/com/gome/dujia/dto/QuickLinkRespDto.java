package cn.com.gome.dujia.dto;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页快速链接 dto
 * Created by zhaoxiang-ds on 2016/4/25.
 */
public class QuickLinkRespDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cityPy;
    private String cityId;
    private String cityName;

    // 可玩天数
    private List<QuickLinkDto> days;

    // 特色玩法
    private List<QuickLinkDto> plays;

    // 周边热门目的地
    private List<QuickLinkDto> destination;

    // 周边热门景区
    private Map<String, List<QuickLinkDto>> AroundScenery = new LinkedHashMap<String, List<QuickLinkDto>>();

    // 当前城市热门景区
    private List<QuickLinkDto> hotScenery;

    public List<QuickLinkDto> getDays() {
        return days;
    }

    public void setDays(List<QuickLinkDto> days) {
        this.days = days;
    }

    public List<QuickLinkDto> getPlays() {
        return plays;
    }

    public void setPlays(List<QuickLinkDto> plays) {
        this.plays = plays;
    }

    public List<QuickLinkDto> getDestination() {
        return destination;
    }

    public void setDestination(List<QuickLinkDto> destination) {
        this.destination = destination;
    }

    public Map<String, List<QuickLinkDto>> getAroundScenery() {
        return AroundScenery;
    }

    public void setAroundScenery(Map<String, List<QuickLinkDto>> aroundScenery) {
        AroundScenery = aroundScenery;
    }

    public List<QuickLinkDto> getHotScenery() {
        return hotScenery;
    }

    public void setHotScenery(List<QuickLinkDto> hotScenery) {
        this.hotScenery = hotScenery;
    }

    public String getCityPy() {
        return cityPy;
    }

    public void setCityPy(String cityPy) {
        this.cityPy = cityPy;
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
}

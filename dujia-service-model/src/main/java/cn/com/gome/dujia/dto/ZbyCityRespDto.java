package cn.com.gome.dujia.dto;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 城市响应DTO
 * Created by zhaoxiang-ds on 2016/4/20.
 */
public class ZbyCityRespDto implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 城市信息Map<类别, 城市>
     */
    private Map<String, List<ZbyCityDto>> cityMap = new LinkedHashMap<String, List<ZbyCityDto>>();

    public Map<String, List<ZbyCityDto>> getCityMap() {
        return cityMap;
    }

    public void setCityMap(Map<String, List<ZbyCityDto>> cityMap) {
        this.cityMap = cityMap;
    }
}

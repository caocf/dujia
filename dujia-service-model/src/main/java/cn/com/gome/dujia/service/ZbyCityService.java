package cn.com.gome.dujia.service;

import cn.com.gome.dujia.dto.ZbyCityDto;
import cn.com.gome.dujia.dto.ZbyCityRespDto;
import cn.com.gome.dujia.model.AroundCity;
import cn.com.gome.dujia.model.ZbyCity;

import java.util.List;
import java.util.Map;

/**
 * 周边游城市对外接口
 * Created by zhaoxiang-ds on 2016/4/5.
 */
public interface ZbyCityService {


    /**
     * 获取城市(热门+全部)
     *
     * @return
     */
    public ZbyCityRespDto queryCity();

    /**
     * 获取全部可定位的城市列表，规则：城市下的线路数量大于24
     *
     * @return
     */
    public List<ZbyCityDto> queryAllEffectiveCity();

    /**
     * 获取全部城市列表
     *
     * @return
     */
    public List<ZbyCityDto> getAllZbyCity();

    /**
     * 根据线路更新城市状态，如果城市下有线路则状态=1，否则=0
     */
    public void updateCityStatusByProduct();

    /**
     * 根据国美城市编码获取城市信息
     *
     * @param code
     * @return
     */
    public ZbyCity queryCityByGomeCode(String code);

    /**
     * 根据城市名称查询城市
     *
     * @return
     */
    public List<ZbyCityDto> queryCityByNameOrPinYin(ZbyCity zbyCity);


    /**
     * 获取默认城市
     *
     * @return
     */
    public ZbyCity queryDefaultCity();

    /**
     * 获取热门周边城市:首先获取周边热门城市如果热门城市不存在则获取周边城市
     *
     * @param platform
     * @param cityId
     * @return
     */
    public List<AroundCity> getHotAroundCity(Integer platform, String cityId);

    /**
     * 获取周边城市
     *
     * @param platform
     * @param cityId
     * @return
     */
    public List<AroundCity> getAroundCity(Integer platform, String cityId);

    /**
     * 周边库获取周末去哪玩数据并缓存
     *
     * @param platform
     * @param cityId
     * @return
     */
    public List<AroundCity> getWeekendCity(Integer platform, Integer cityId);

    /**
     * 周边库获取周末去哪玩数据
     *
     * @param platform
     * @param cityId
     * @return
     */
    public List<AroundCity> getWeekendCityData(Integer platform, String cityId);

    /**
     * 根据城市拼音 查询城市ID，缓存1天
     *
     * @param cityPY
     * @return
     */
    public ZbyCity cityPY2CityId(String cityPY);

    /**
     * 根据 CityId获取城市信息
     *
     * @param cityId
     * @return
     */
    public ZbyCity selectCityById(String cityId);

    /**
     * 获取城市-省
     *
     * @return <cityId,getProvinceName>
     */
    public Map<String, String> getProvinceCityMap();
}

package cn.com.gome.dujia.service.impl;

import cn.com.gome.dujia.dto.ZbyCityDto;
import cn.com.gome.dujia.dto.ZbyCityRespDto;
import cn.com.gome.dujia.enums.AroundCityType;
import cn.com.gome.dujia.mapper.business.AroundCityMapper;
import cn.com.gome.dujia.mapper.business.ZbyCityMapper;
import cn.com.gome.dujia.model.AroundCity;
import cn.com.gome.dujia.model.ZbyCity;
import cn.com.gome.dujia.redis.CacheExpire;
import cn.com.gome.dujia.service.ZbyCityService;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.gome.plan.tools.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoxiang-ds on 2016/4/14.
 */
@Service
@Path("city")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class ZbyCityServiceImpl implements ZbyCityService {

    private static final Logger logger = LoggerFactory.getLogger(ZbyCityServiceImpl.class);
    @Autowired
    private ZbyCityMapper cityMapper;

    @Autowired
    private AroundCityMapper aroundCityMapper;

    /**
     * 获取城市(热门+全部)
     *
     * @return
     */
    @Override
    @GET
    @Path("/query")
    @Cacheable(value = {CacheExpire.EXPIRE01D}, key = "#root.targetClass + '.'+ #root.methodName")
    public ZbyCityRespDto queryCity() {
        try {
            ZbyCityRespDto respDto = new ZbyCityRespDto();
            Map<String, List<ZbyCityDto>> map = new HashMap<String, List<ZbyCityDto>>();
            List<ZbyCityDto> hot = cityMapper.queryHotCity();
            List<ZbyCityDto> all = cityMapper.queryAllCity();
            map.put("hot", hot);
            map.put("all", all);
            respDto.setCityMap(map);
            return respDto;
        } catch (Exception e) {
            logger.error("获取城市失败", e);
        }
        return null;
    }


    /**
     * 获取热门城市，zby_city 表里的热门城市
     */
    public List<ZbyCityDto> queryHotCity() {
        try {
            List<ZbyCityDto> hot = cityMapper.queryHotCity();
            if (hot == null || hot.isEmpty()) {
                hot = cityMapper.queryCityByLineCount();
            }
            return hot;
        } catch (Exception e) {
            logger.error("获取热门城市失败", e);
        }
        return null;
    }

    /**
     * 获取全部可定位的城市列表，规则：城市下的线路数量大于24
     *
     * @return
     */
    public List<ZbyCityDto> queryAllEffectiveCity() {
        try {
            return cityMapper.queryAllCity();
        } catch (Exception e) {
            logger.error("获取全部可定位的城市列表失败", e);
        }
        return null;
    }

    @Override
    public List<ZbyCityDto> getAllZbyCity() {
        return cityMapper.queryAllCity();
    }


    @Override
    public void updateCityStatusByProduct() {
        try {
            cityMapper.updateCityStatus();
        } catch (Exception e) {
            logger.error("更新城市状态失败", e);
        }
    }

    @Override
    @Cacheable(value = {CacheExpire.EXPIRE02H}, key = "#root.targetClass + '.'+ #root.methodName + '_' + #code ")
    public ZbyCity queryCityByGomeCode(String code) {
        ZbyCity res = null;
        try {
            if (StringUtils.isNotEmpty(code)) {
                ZbyCity cityp = new ZbyCity();
                cityp.setCode(code);
                res = cityMapper.selectCity(cityp);
            }
            // 取默认城市
            if (res == null) {
                res = queryDefaultCity();
            }
        } catch (Exception e) {
            logger.error("根据城市编码获取城市信息出错", e);
        }
        return res;
    }

    /**
     * 获取默认城市,缓存1天
     *
     * @return
     */
    @Cacheable(value = {CacheExpire.EXPIRE01D}, key = "#root.targetClass + '.'+ #root.methodName ")
    public ZbyCity queryDefaultCity() {
        try {
            ZbyCity cityp = new ZbyCity();
            cityp.setCityName("北京");
            return cityMapper.selectCity(cityp);
        } catch (Exception e) {
            logger.error("获取默认定位城市失败", e);
        }
        return null;
    }

    @Override
    public List<ZbyCityDto> queryCityByNameOrPinYin(ZbyCity zbyCity) {
        return cityMapper.queryCityByNameOrPinYin(zbyCity);
    }

    /**
     * 获取城市的热门周边城市:首先获取周边热门城市如果热门城市不存在则获取周边城市
     *
     * @return
     */
    @Cacheable(value = {CacheExpire.EXPIRE02H}, key = "#root.targetClass + '.'+ #root.methodName + '_' + #platform + '_' + #cityId")
    public List<AroundCity> getHotAroundCity(Integer platform, String cityId) {
        logger.info("根据城市cityId={}获取热门周边城市", cityId);
        try {
            AroundCity aroundCity = new AroundCity();
            aroundCity.setCityId(cityId);
            aroundCity.setPlateform(platform); // 平台
            aroundCity.setStates(true);// 状态展示中
            // 1-获取热门周边城市
            aroundCity.setAroundCity(AroundCityType.HOT.getValue()); // 类型:热门城市
            List<AroundCity> acs = aroundCityMapper.querySimpleAroundCityList(aroundCity);
            if (acs == null || acs.isEmpty()) {
                logger.info("城市id={}热门周边城市不存在，获取周边城市");
                // 2-热门城市不存在时获取周边城市
                return this.getAroundCity(platform, cityId);
            }
            return acs;
        } catch (Exception e) {
            logger.error("根据城市cityId={}获取热门周边城市失败", cityId, e);
        }
        return null;
    }


    @Cacheable(value = {CacheExpire.EXPIRE02H}, key = "#root.targetClass + '.'+ #root.methodName + '_' + #platform + '_' + #cityId")
    public List<AroundCity> getAroundCity(Integer platform, String cityId) {
        try {
            AroundCity aroundCity = new AroundCity();
            aroundCity.setCityId(cityId);
            aroundCity.setPlateform(platform); // 平台
            aroundCity.setStates(true);// 状态展示中
            aroundCity.setAroundCity(AroundCityType.AROUND.getValue()); // 类型:周边城市
            List<AroundCity> acs = aroundCityMapper.querySimpleAroundCityList(aroundCity);
            return acs;
        } catch (Exception e) {
            logger.error("根据城市cityId={}获取周边城市失败", cityId, e);
        }
        return null;
    }

    /**
     * 获取首页周末去哪玩
     *
     * @return
     */
    @GET
    @Path("/weekend/{platform}/{cityId}")
    @Cacheable(value = {CacheExpire.EXPIRE01D}, key = "#root.targetClass + '.'+ #root.methodName + '_' + #platform + '_' + #cityId")
    public List<AroundCity> getWeekendCity(@PathParam("platform") Integer platform, @PathParam("cityId") Integer cityId) {
        //获取数据
        return this.getWeekendCityData(platform, cityId.toString());
    }


    public List<AroundCity> getWeekendCityData(Integer platform, String cityId) {
        logger.info("根据城市cityId={}获取周边去哪玩", cityId);
        try {
            int showSize = 7;
            AroundCity aroundCity = new AroundCity();
            aroundCity.setCityId(cityId);
            aroundCity.setAroundCity(AroundCityType.WEEKEND.getValue()); // 类型:周末去哪玩
            aroundCity.setPlateform(platform); // 平台
            aroundCity.setStates(true);// 状态展示中
            List<AroundCity> cities = aroundCityMapper.querySimpleAroundCityList(aroundCity);
            if (cities.size() > showSize) {
                // 只显示前7个
                List<AroundCity> tmp = new ArrayList<>();
                for (int i = 0; i < showSize; i++) {
                    tmp.add(cities.get(i));
                }
                return tmp;
            }
            // 后台配置的周边城市小于6是取全国数据补位
            if (cities.size() < showSize) {
                // 查询全国数据
                List<AroundCity> tempCity = this.queryWeekendCityQuanGuo(platform);
                ZbyCity city = this.selectCityById(cityId);
                if (tempCity != null && city != null) {
                    // 判断长度
                    int len = (showSize - cities.size()) < tempCity.size() ? (showSize - cities.size()) : tempCity.size();
                    for (int i = 0; i < len; i++) {
                        tempCity.get(i).setCityPinyin(city.getCityPinyin());
                        cities.add(tempCity.get(i));
                    }
                }
            }
            return cities;
        } catch (Exception e) {
            logger.error("根据城市cityId={}获取周边去哪玩失败", cityId, e);
        }
        return null;
    }

    /**
     * 查询周末去哪玩(全国数据)
     *
     * @param platform
     */
    @Cacheable(value = {CacheExpire.EXPIRE01D}, key = "#root.targetClass + '.'+ #root.methodName + '_' + #platform")
    private List<AroundCity> queryWeekendCityQuanGuo(Integer platform) {
        try {
            AroundCity aroundCity = new AroundCity();
            aroundCity.setCityName("全国");
            aroundCity.setAroundCity(AroundCityType.WEEKEND.getValue()); // 类型:周末去哪玩
            aroundCity.setPlateform(platform); // 平台
            aroundCity.setStates(true);// 状态展示中
            return aroundCityMapper.querySimpleAroundCityList(aroundCity);
        } catch (Exception e) {
            logger.error("查询周末去哪玩(全国数据)失败", e);
        }
        return null;
    }

    /**
     * 根据城市拼音(全拼或简拼) 查询城市ID，缓存1天
     *
     * @param cityPy
     * @return
     */
    @GET
    @Path("/py2id/{cityPy}")
    @Cacheable(value = {CacheExpire.EXPIRE01D}, key = "#root.targetClass + '.'+ #root.methodName + '_' + #cityPy")
    public ZbyCity cityPY2CityId(@PathParam("cityPy") String cityPy) {
        logger.info("根据城市拼音{} 查询城市", cityPy);
        try {
            return cityMapper.queryCityIdByCityPy(cityPy);
        } catch (Exception e) {
            logger.error("根据城市拼音{} 查询城市ID失败", cityPy, e);
        }
        return null;
    }

    /**
     * 根据 CityId获取城市信息
     *
     * @param cityId
     * @return
     */
    @Cacheable(value = {CacheExpire.EXPIRE01D}, key = "#root.targetClass + '.'+ #root.methodName + '_' + #cityId")
    public ZbyCity selectCityById(String cityId) {
        try {
            ZbyCity city = new ZbyCity();
            city.setCityId(Integer.parseInt(cityId));
            return cityMapper.selectCity(city);
        } catch (Exception e) {
            logger.error("根据CityId={}获取城市信息失败", cityId, e);
        }
        return null;
    }

    /**
     * 获取城市-省<cityId,getProvinceName>
     *
     * @return
     */
    @Cacheable(value = {CacheExpire.EXPIRE01D}, key = "#root.targetClass + '.'+ #root.methodName")
    public Map<String, String> getProvinceCityMap() {
        try {
            Map<String, String> provinceCityMap = new HashMap<>();
            List<ZbyCity> list = cityMapper.selectAll();
            for (ZbyCity city : list) {
                provinceCityMap.put(city.getCityId().toString(), city.getProvinceName());
            }
            return provinceCityMap;
        } catch (Exception e) {
            logger.error("获取城市、省信息失败", e);
        }
        return null;
    }
}

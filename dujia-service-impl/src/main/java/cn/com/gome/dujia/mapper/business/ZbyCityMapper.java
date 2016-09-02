package cn.com.gome.dujia.mapper.business;

import cn.com.gome.dujia.dto.ZbyCityDto;
import cn.com.gome.dujia.model.ZbyCity;
import com.gome.plan.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ZbyCityMapper extends Mapper<ZbyCity> {

    /**
     * 查询热门(线路最多前20个)城市
     *
     * @return
     */
    List<ZbyCityDto> queryCityByLineCount();

    /**
     * 查询热门(线路最多)城市
     *
     * @return
     */
    List<ZbyCityDto> queryHotCity();

    /**
     * 查询全部城市
     *
     * @return
     */
    List<ZbyCityDto> queryAllCity();

    /**
     * 根据线路更新城市表状态，如果城市下有线路则状态=1，否则=0
     */
    int updateCityStatus();

    /**
     * 根据城市名称查询城市
     *
     * @return
     */
    List<ZbyCityDto> queryCityByNameOrPinYin(ZbyCity zbyCity);

    /**
     * 根据城市拼音(全拼或简拼) 查询城市ID
     *
     * @param cityPy
     * @return
     */
    ZbyCity queryCityIdByCityPy(String cityPy);

    /**
     * @param city
     * @return
     */
    ZbyCity selectCity(ZbyCity city);
}
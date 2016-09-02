package cn.com.gome.dujia.mapper.business;

import java.util.List;

import com.gome.plan.mybatis.mapper.common.Mapper;

import cn.com.gome.dujia.model.AroundCity;
import cn.com.gome.dujia.model.Recommend;

public interface AroundCityMapper extends Mapper<Recommend> {
	 /**
     * 根据条件获取周边城市列表
     *
     * @param advert
     * @return
     */
    public List<AroundCity> queryAroundCityList(AroundCity aroundCity);


    /**
     * 新增周边城市
     *
     * @param aroundCity
     */
    void insertAroundCity(AroundCity aroundCity);


    /**
     * 更新周边城市信息
     *
     * @param record
     */
    void updateByAroundCity(AroundCity aroundCity);
    
    /**
     * 获取周边城市信息
     *
     * @param record
     */
    public AroundCity selectAroundCityById( Integer id );

    /**
     * 首页-动态查询周边城市
     * @param aroundCity
     * @return
     */
    public List<AroundCity> querySimpleAroundCityList(AroundCity aroundCity);

    /**
     * 校验是否重复
     *
     * @param record
     * @return
     */
    int findCount(AroundCity record);
}
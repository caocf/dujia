package cn.com.gome.dujia.service;

import java.util.List;

import cn.com.gome.dujia.model.AroundCity;

/**
* @Author: lzx
* @date：2016年4月29日  下午3:03:32
* @Description：周边城市接口
*/
public interface AroundCityService {

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
    void insert(AroundCity aroundCity);


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
     * 校验是否重复
     *
     * @param record
     * @return
     */
    int findCount(AroundCity record);
}

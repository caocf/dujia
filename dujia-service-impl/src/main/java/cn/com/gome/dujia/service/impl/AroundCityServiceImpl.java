package cn.com.gome.dujia.service.impl;

import java.util.List;

import com.gome.plan.mybatis.mapper.entity.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.gome.dujia.mapper.business.AroundCityMapper;
import cn.com.gome.dujia.model.AroundCity;
import cn.com.gome.dujia.service.AroundCityService;

/**
 * @Author: lzx
 * @date：2016年4月29日 下午3:03:32
 * @Description：周边城市接口实现
 */
@Service
public class AroundCityServiceImpl implements AroundCityService {

    @Autowired
    private AroundCityMapper aroundCityMapper;


    /**
     * 根据条件获取周边城市列表
     *
     * @param aroundCity
     * @return
     */

    @Override
    public List<AroundCity> queryAroundCityList(AroundCity aroundCity) {
        return aroundCityMapper.queryAroundCityList(aroundCity);
    }

    public List<AroundCity> querySimpleAroundCityList(AroundCity aroundCity) {
        return aroundCityMapper.queryAroundCityList(aroundCity);
    }

    /**
     * 新增周边城市
     *
     * @param aroundCity
     */
    @Override
    public void insert(AroundCity aroundCity) {
        aroundCityMapper.insertAroundCity(aroundCity);
    }

    /**
     * 更新周边城市信息
     *
     * @param aroundCity
     */
    @Override
    public void updateByAroundCity(AroundCity aroundCity) {
        aroundCityMapper.updateByAroundCity(aroundCity);
    }

    /**
     * @Title selectAroundCityById
     * @Description：获取周边城市信息
     */
    @Override
    public AroundCity selectAroundCityById(Integer id) {
        return aroundCityMapper.selectAroundCityById(id);
    }

    /**
     * 校验是否重复
     * @param record
     * @return
     */
    @Override
    public int findCount(AroundCity record) {
        return aroundCityMapper.findCount(record);
    }
}

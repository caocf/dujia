package cn.com.gome.dujia.mapper.business;

import cn.com.gome.dujia.model.Advert;
import com.gome.plan.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AdvertMapper extends Mapper<Advert> {


    /**
     * 查询所有
     *
     * @return
     */
    List<Advert> queryAll();

    /**
     * 根据条件获取广告
     *
     * @param advert
     * @return
     */
    List<Advert> queryAdvertList(Advert advert);

    /**
     * 根据广告主键ID，获取广告信息
     *
     * @param id
     * @return
     */
    Advert selectAdvertById(Integer id);

    /**
     * 新增广告，新写入数据库记录,st_advert
     *
     * @param record
     * @return
     */
    int insertAdvert(Advert record);

    /**
     * 更新广告信息
     *
     * @param record
     * @return
     */
    int updateAdvertById(Advert record);

    /**
     * 校验投放是否重复
     *
     * @param record
     * @return
     */
    int findCount(Advert record);

    /**
     * 前台根据城市、平台获取对应推荐内容
     *
     * @param advert
     * @return
     */
    List<Advert> queryRecommendListByCity(Advert advert);

    /**
     * 查询待排期中今天启用的以及今天过期广告和推荐列表
     */
    List<Advert> queryAdRecommendList();

    /**
     * 批量更新
     * @param adverts
     */
    void batchUpdate(List<Advert> adverts);

}
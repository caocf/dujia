package cn.com.gome.dujia.service;

import cn.com.gome.dujia.model.Advert;
import cn.com.gome.dujia.model.ZbyProduct;

import java.util.List;

/**
 * 广告对外接口
 * Created by zhaoxiang-ds on 2016/4/5.
 */
public interface AdvertService {

    /**
     * 根据条件获取广告列表
     *
     * @param advert
     * @return
     */
    public List<Advert> getAdvertList(Advert advert);


    /**
     * 新增广告内容
     *
     * @param record
     * @return
     */
    int insert(Advert record);

    /**
     * 根据广告主键ID，获取广告信息
     *
     * @param id
     * @return
     */
    Advert selectAdvertById(Integer id);

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
     * 查询待排期中今天启用的以及今天过期广告和推荐列表
     */
    List<Advert> queryAdRecommendList();

    /**
     * 根据条件获取推荐列表
     *
     * @param advert
     * @return
     */
    public List<Advert> getRecommendList(Advert advert);


    /**
     * 获取有效期内的广告
     *
     * @param advert
     * @return
     */
    public List<Advert> queryAdvertList4Web(Advert advert);

    /**
     * 获取指定城市下，指定页面位置广告内容
     *
     * @param plateform
     * @param pageModelType
     * @return
     */
    public List<Advert> queryAdvertListByPlateform(Integer plateform, String pageModelType);

    /**
     * 通过产品id检索产品
     *
     * @param productId
     * @return
     */
    public ZbyProduct findProductById(String productId);

    /**
     * 批量更新
     *
     * @param adverts
     */
    void batchUpdate(List<Advert> adverts);


    /**
     * 根据城市、平台、投放页面，获取推荐线路
     *
     * @param plateform
     * @param cityId
     * @param pageType  页面类型
     * @return
     */
    public List<Advert> queryRecommendList(Integer plateform, String cityId, String pageType);

    public List<Advert> queryRecommendDatas(Integer plateform, String cityId, String pageType);
}

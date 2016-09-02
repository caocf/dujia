package cn.com.gome.dujia.service;

import java.util.List;

import cn.com.gome.dujia.model.Recommend;

/**
 * 推荐信息接口
 * Created by zhaoxiang-ds on 2016/4/5.
 */
public interface RecommendService {


    /**
     * 根据条件获取推荐信息列表
     *
     * @param recommend
     * @return
     */
    public List<Recommend> getRecommendList(Recommend recommend);

    /**
     * 新增推荐位内容
     *
     * @param record
     * @return
     */
    int insert(Recommend record);

    /**
     * 根据主键获取推荐位
     *
     * @param id
     * @return
     */
    Recommend selectByPrimaryKey(Integer id);

    /**
     * 更新推荐位信息
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Recommend record);


    /**
     * 查询待排期中今天启用的推荐列表
     *
     * @return
     */
    List<Recommend> queryStartRecommendList();

    /**
     * 查询启用中今天过期的推荐列表
     *
     * @return
     */
    List<Recommend> queryEndRecommendList();


}

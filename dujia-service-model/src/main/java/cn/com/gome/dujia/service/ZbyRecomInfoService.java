package cn.com.gome.dujia.service;

import java.util.List;

import cn.com.gome.dujia.model.ZbyRecomInfo;


/**
 * 周边游主题分类接口
 * Created by zhaoxiang-ds on 2016/4/5.
 */
public interface ZbyRecomInfoService {

    /**
     * 获取城市下的主题分类信息
     *
     * @param cityId
     * @return
     */
    public List<ZbyRecomInfo> getRecomInfoList(String cityId);

    /**
     * @param recomInfo
     * @return List<ZbyRecomInfo>
     * @Title queryRecomInfoList
     * @date：2016年4月27日 下午3:33:01
     * @Description：分页查询主题分类
     */
    public List<ZbyRecomInfo> queryRecomInfoList(ZbyRecomInfo recomInfo);

    /**
     * @Title updateRecomInfoStatus
     * @Author: lzx
     * @date：2016年4月29日 上午10:51:09
     * @Description：更改标签状态
     */
    public void updateRecomInfoStatus(ZbyRecomInfo recomInfo);

    /**
     * 根据productId获取主题信息
     *
     * @param recomInfo
     * @return
     */
    public List<ZbyRecomInfo> getRecomInfoList(ZbyRecomInfo recomInfo);
    
    /**
     * 查询更新的主题信息
     * @param recomInfo
     * @return
     */
    public List<ZbyRecomInfo> queryUpdateRecomInfoList(ZbyRecomInfo recomInfo);
}

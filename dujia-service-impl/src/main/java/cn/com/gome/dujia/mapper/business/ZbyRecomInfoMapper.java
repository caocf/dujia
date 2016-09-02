package cn.com.gome.dujia.mapper.business;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.gome.dujia.dto.QuickLinkDto;
import cn.com.gome.dujia.model.ZbyRecomInfo;

import com.gome.plan.mybatis.mapper.common.Mapper;

public interface ZbyRecomInfoMapper extends Mapper<ZbyRecomInfo> {
    
	void batchInsert(List<ZbyRecomInfo> ZbyProductPackages);

    /**
     * 获取城市下线路最多的主题列表
     *
     * @param cityId
     * @return
     */
    List<QuickLinkDto> queryRecomByCity(String cityId);
    
    /**
     * @Title queryRecomInfoList
     * @date：2016年4月27日  下午3:33:01
     * @Description：分页查询主题分类
     * @param recomInfo
     * @return List<ZbyRecomInfo>
     */
    List<ZbyRecomInfo> queryRecomInfoList(ZbyRecomInfo recomInfo);
     
     /**
      * @Title updateRecomInfoStatus
      * @Author: lzx
      * @date：2016年4月29日  上午10:51:09
      * @Description：更改标签状态
      */
     void updateRecomInfoStatus(ZbyRecomInfo recomInfo);

     void updateIsDelete(ZbyRecomInfo var);

    List<ZbyRecomInfo> selectByProductId(@Param(value = "productId")String productId);
    
    /**
     * 查询线路主题信息
     * @param productIds
     * @return
     */
    List<ZbyRecomInfo> queryRecomInfoByPIds(List<String> productIds);
    
    /**
     * 查询更新的主题信息
     * @param recomInfo
     * @return
     */
    public List<ZbyRecomInfo> queryUpdateRecomInfoList(ZbyRecomInfo recomInfo);
}
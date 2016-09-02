package cn.com.gome.dujia.mapper.business;

import cn.com.gome.dujia.dto.QuickLinkDto;
import cn.com.gome.dujia.dto.ZbyPackageResourceSimpleDto;
import cn.com.gome.dujia.dto.ZbyProductResourceDto;
import cn.com.gome.dujia.model.ZbyProductPackage;
import cn.com.gome.dujia.model.ZbyProductPackageDetail;
import com.gome.plan.mybatis.mapper.common.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ZbyProductPackageDetailMapper extends Mapper<ZbyProductPackageDetail> {


    /**
     * 根据线路id,获取资源(酒店、景区)
     *
     * @param productId
     * @return
     */
    List<ZbyProductResourceDto> queryResourceByProductId(String productId);

    /**
     * 获取城市下的热门景区
     *
     * @param map [<cityid,""><size,>]
     * @return
     */
    List<QuickLinkDto> queryCityHotSceneryBySize(Map map);


    /**
     * 根据线路id,套餐id列表，批量查询套餐的资源
     *
     * @param param
     * @return
     */
    List<ZbyPackageResourceSimpleDto> queryPackageSimpleRes(Map param);


    /**
     * 批量插入
     *
     * @param zbyProductPackageDetails
     */
    void batchInsert(List<ZbyProductPackageDetail> zbyProductPackageDetails);


    //删除套餐明细，根据套餐ID
    void deleteByPackageId(ZbyProductPackage packageId);


    /**
     * 获取资源信息
     *
     * @param productIds
     * @return
     */
    List<ZbyProductPackageDetail> queryResInfoByPIds(List<String> productIds);


    /**
     * 根据线路 资源id 获取资源信息
     *
     * @param resId
     * @return
     */
    ZbyProductPackageDetail queryResInfoById(String resId);

    /**
     * 根据线路id,套餐id，查询套餐的资源
     *
     * @param packageDetail
     * @return
     */
    List<ZbyProductPackageDetail> queryPackageRes(ZbyProductPackageDetail packageDetail);

    /**
     * 根据套餐id，获取套餐详情列表
     *
     * @param packageId
     * @return
     */
    List<ZbyProductPackageDetail> queryPackageDetailByPackageId(String packageId);

    /**
     * 根据线路id,获取资源(酒店、景区)
     *
     * @return
     */
    List<ZbyProductResourceDto> queryResourceByPackageId(String packageId);

    Integer insertReturnPri(ZbyProductPackageDetail tcDetail);


    /**
     * 查询城市下销量、浏览量最多的前10个景区
     *
     * @param cityId
     * @return
     */
    List<ZbyProductResourceDto> querySceneryBySaleOrBrowseCount(String cityId);

    /**
     * 根据订单编号，产品编号，套餐编号查询信息
     * @param param
     * @return
     */
    List<ZbyProductPackageDetail> getPackageDetailList(Map param);

    /**
     * 查询接口不存在的套餐详情
     * @param productId
     * @param packageResId
     * @return
     */

    List<ZbyProductPackageDetail> selectByProductIdAndRedId(@Param("productId")String productId, @Param("list")List<String> packageResId);
}
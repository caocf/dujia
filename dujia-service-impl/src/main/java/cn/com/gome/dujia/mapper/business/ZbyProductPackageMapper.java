package cn.com.gome.dujia.mapper.business;

import cn.com.gome.dujia.model.ZbyProduct;

import com.gome.plan.mybatis.mapper.common.Mapper;

import cn.com.gome.dujia.model.ZbyProductPackage;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ZbyProductPackageMapper extends Mapper<ZbyProductPackage> {

    void batchInsert(List<ZbyProductPackage> ZbyProductPackages);

    /**
     * 根据产品id,售卖日期获取套餐列表
     *
     * @param param
     * @return
     */
    List<ZbyProductPackage> queryPackages(Map<String, String> param);
    
    /**
     * 获取线路套餐列表
     * @param param
     * @return
     */
    List<ZbyProductPackage> queryProductPackages(Map<String, Object> param);

    void deleteByProductId(String productId);
    void deleteByPackageId(String packageId);

    /**
     * 根据套餐id查询套餐信息
     *
     * @param packageId
     * @return
     */
    ZbyProductPackage selectByPackageId(String packageId);

    List<ZbyProductPackage> selectInPackageIds(@Param("list") List<ZbyProductPackage> list, @Param("isDelete") Boolean isDelete);

    void updateBySelective(ZbyProductPackage productPackage);

    List<ZbyProductPackage> getPackageByProductIDAndPackageID(String productId, String packageId);

    /**
     * 更新套餐销量
     * @param productPackage
     */
    void updateSellCount(ZbyProductPackage productPackage);
}
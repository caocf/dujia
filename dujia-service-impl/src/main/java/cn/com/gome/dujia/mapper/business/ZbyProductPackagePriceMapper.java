package cn.com.gome.dujia.mapper.business;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.com.gome.dujia.dto.ZbyPackagePriceDto;
import cn.com.gome.dujia.model.ZbyProductPackagePrice;

import com.gome.plan.mybatis.mapper.common.Mapper;

public interface ZbyProductPackagePriceMapper extends Mapper<ZbyProductPackagePrice> {
    void batchInsert(List<ZbyProductPackagePrice> zbyProductPackagePrices);


    /**
     * 根据线路Id,获取线路有效套餐班期列表
     *
     * @param productId
     * @return
     */
    List<ZbyPackagePriceDto> queryPriceListByProductId(String productId);

    /**
     * 根据线路id,套餐id,获取价格班期列表
     *
     * @param map<productId,><pakcageId,>
     * @return
     */
    List<ZbyPackagePriceDto> queryPriceListByProIdAndPackId(Map map);


    /**
     * 根据产品id、售卖日期 获取产品含有套餐的最低价和最高价
     *
     * @param params
     * @return
     */
    ZbyProductPackagePrice queryMinAndMaxPrice(Map params);

    /**
     * 获取产品默认最近可售卖日期
     * @param productId
     * @return
     */
    Date queryDefaultSaleDate(String productId);

    void deleteBeforeDate(@Param("beginDate")String beginDate);

    void batchUpdate(List<ZbyProductPackagePrice> updates);

    /**
     * 根据线路id，套餐id，和出游日期查询套餐价格
     * @return
     */
    ZbyProductPackagePrice selectPackagePriceByIdDate(@Param("productId")String productId, @Param("packageId")String packageId ,@Param("packageSaleDate")String packageSaleDate);

    void deleteInProductId(String productId);

    void deleteByProductIdAndPackageId(@Param("productId")String productId, @Param("packageIds")List<String> packageIds);
    
    /**
     * 获取线路的最低价格
     * @return
     */
    List<ZbyProductPackagePrice> queryProductPrice();
}
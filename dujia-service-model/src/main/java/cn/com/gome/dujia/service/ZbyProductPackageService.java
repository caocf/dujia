package cn.com.gome.dujia.service;

import cn.com.gome.dujia.dto.ZbyPackagePriceDto;
import cn.com.gome.dujia.dto.ZbyPackageRespDto;
import cn.com.gome.dujia.exception.BizException;
import cn.com.gome.dujia.model.ZbyProductPackage;
import cn.com.gome.dujia.model.ZbyProductPackageDetail;
import cn.com.gome.dujia.model.ZbyProductPackagePrice;

import java.util.Date;
import java.util.List;

/**
 * 周边游线路套餐对外接口
 * reated by zhaoxiang-ds on 2016/4/5.
 */
public interface ZbyProductPackageService {

    /**
     * 获取指定线路的套餐列表
     *
     * @param productId
     * @return
     */
    public ZbyPackageRespDto getProductPackageList(String productId);

    /**
     * 获取产品默认最近可售卖日期
     *
     * @param productId
     * @return 格式：yyyyMMdd
     */
    public Date queryDefaultSaleDate(String productId);

    /**
     * 根据线路id，获取有效价格班期列表
     *
     * @param productId
     * @return
     */
    public List<ZbyPackagePriceDto> getPackagePriceList(Integer productId);

    /**
     * 根据线路id,套餐id,获取价格班期列表
     *
     * @param productId
     * @param packageId
     * @return
     */
    public List<ZbyPackagePriceDto> queryPriceListByProIdAndPackId(Integer productId, Integer packageId);

    /**
     * 根据套餐id，查询套餐详情
     *
     * @param packageId
     * @return
     */
    public ZbyProductPackage selectByPackageId(String packageId);

    /**
     * 根据线路id，套餐id，和出游日期查询套餐价格
     *
     * @param productId       线路id
     * @param packageId       套餐id
     * @param packageSaleDate 出游日期(yyyy-MM-dd)
     * @return
     */
    public ZbyProductPackagePrice selectPackagePriceByIdDate(String productId, String packageId, String packageSaleDate) throws BizException;

    /**
     * 根据prductId,packageId,获取套餐包含的资源详情列表
     *
     * @param productId
     * @param packageId
     * @return
     */
    public List<ZbyProductPackageDetail> getPackageResources(Integer productId, Integer packageId);

    /**
     * 根据resourceId获取资源详情
     *
     * @param resId
     * @return
     */
    public ZbyProductPackageDetail getPackageResourceDetail(Integer resId);

    /**
     * 根据productId,packageId，获取套餐详情
     *
     * @param productId
     * @param packageId
     * @return
     */
    public ZbyProductPackage getPackageByProductIDAndPackageID(String productId, String packageId);

}

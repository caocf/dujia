package cn.com.gome.dujia.service;

import cn.com.gome.dujia.dto.ZbyPackageResourceSimpleDto;
import cn.com.gome.dujia.model.ZbyProductPackageDetail;

import java.util.List;

/**
 * 套餐详情对外接口
 * Created by zhaoxiang-ds on 2016/4/5.
 */
public interface ZbyProductPackageDetailService {

    /**
     * 根据套餐id，查询套餐详情
     *
     * @param packageId
     * @return
     */
    List<ZbyProductPackageDetail> queryPackageDetailByPackageId(String packageId);

    /**
     * 根据prductId,packageId,获取套餐详情列表
     *
     * @param productId
     * @param packageId
     * @return
     */
    public List<ZbyProductPackageDetail> queryPackageDetailList(String productId, String packageId);

    /**
     * 批量查询套餐的资源列表，缓存
     *
     * @param productId
     * @param packageIds
     * @return
     */
    public List<ZbyPackageResourceSimpleDto> queryPackageResList(String productId, List<String> packageIds);

    /**
     * 根据线路 资源id 获取资源信息
     *
     * @param resId
     * @return
     */
    public ZbyProductPackageDetail queryResourceDetailByResid(String resId);

    /**
     * 根据prductId,packageId,获取套餐详情列表
     *
     * @param productId
     * @param packageId
     * @return
     */
    public List<ZbyProductPackageDetail> getPackageDetailList(String productId, String packageId, String orderId);
}

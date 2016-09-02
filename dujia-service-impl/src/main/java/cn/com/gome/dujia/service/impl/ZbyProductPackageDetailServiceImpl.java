package cn.com.gome.dujia.service.impl;

import cn.com.gome.dujia.dto.ZbyPackageResourceSimpleDto;
import cn.com.gome.dujia.mapper.business.ZbyProductPackageDetailMapper;
import cn.com.gome.dujia.model.ZbyProductPackageDetail;
import cn.com.gome.dujia.redis.CacheExpire;
import cn.com.gome.dujia.service.ZbyProductPackageDetailService;
import com.gome.plan.tools.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 线路套餐资源
 * Created by zhaoxiang-ds on 2016/4/18.
 */
@Service
public class ZbyProductPackageDetailServiceImpl implements ZbyProductPackageDetailService {
    private static final Logger logger = LoggerFactory.getLogger(ZbyProductPackageDetailServiceImpl.class);

    @Autowired
    private ZbyProductPackageDetailMapper packageDetailMapper;

    @Override
    public List<ZbyProductPackageDetail> queryPackageDetailByPackageId(String packageId) {
        return packageDetailMapper.queryPackageDetailByPackageId(packageId);
    }

    /**
     * 根据prductId,packageId,获取套餐详情列表
     */
    @Cacheable(value = {CacheExpire.EXPIRE30M}, key = "#root.targetClass + '.'+ #root.methodName + '_' + #productId + '_' + #packageId")
    public List<ZbyProductPackageDetail> queryPackageDetailList(String productId, String packageId) {
        try {
            logger.info("根据prductId={},packageId={},获取套餐详情列表", productId, packageId);
            ZbyProductPackageDetail packageDetail = new ZbyProductPackageDetail();
            packageDetail.setProductId(productId);
            packageDetail.setPackageId(packageId);
            List<ZbyProductPackageDetail> list = packageDetailMapper.queryPackageRes(packageDetail);
            if (null != list) {
                // 格式化附加资源
                for (ZbyProductPackageDetail pd : list) {
                    pd.setProProps(this.formatProps(pd.getProProps()));
                }
            }
            return list;
        } catch (Exception e) {
            logger.error("根据prductId={},packageId={},获取套餐详情列表失败", productId, packageId, e);
        }
        return null;
    }


    /**
     * 酒店附属信息格式化
     *
     * @param props
     * @return
     */
    private String formatProps(String props) {
        if (StringUtils.isEmpty(props)) {
            return "";
        }
        StringBuffer sbf = new StringBuffer();
        String ss = props.substring(1, props.length() - 1);
        List<String> list = Arrays.asList(ss.split(","));
        for (String s : list) {
            String[] tmp = s.split("=");
            if (tmp.length < 2) {
                continue;
            }
            String key = tmp[0].trim();
            String value = tmp[1].trim();
            if (key.equals("房间面积")) {
                sbf.append(key + "：" + value + "㎡　");
            }
            if (key.equals("床宽")) {
                sbf.append(key + "：" + value + "m　");
            }
            if (key.equals("加床价")) {
                sbf.append(key + "：" + value + "元/张　");
            }
            if (s.contains("床型")) {
                sbf.append(key + "：" + value + "　");
            }
            if (key.equals("宽带")) {
                sbf.append(key + "：" + value + "　");
            }
            if (key.equals("加床")) {
                sbf.append(key + "：" + value + "　");
            }
        }
        for (String s : list) {
            String[] tmp = s.split("=");
            if (tmp.length < 2) {
                continue;
            }
            String key = tmp[0].trim();
            String value = tmp[1].trim();
            if (key.equals("卫浴设施")) {
                sbf.append("</br>" + key + "：" + value + "　");
            }
        }
        for (String s : list) {
            String[] tmp = s.split("=");
            if (tmp.length < 2) {
                continue;
            }
            String key = tmp[0].trim();
            String value = tmp[1].trim();
            if (key.equals("备注")) {
                sbf.append("</br>" + key + "：" + value + "　");
            }
        }
        return StringUtils.isEmpty(sbf) ? "" : sbf.toString();
    }

    /**
     * 批量查询套餐的资源列表，缓存
     *
     * @param productId
     * @param packageIds
     * @return
     */
    @Cacheable(value = {CacheExpire.EXPIRE30M}, key = "#root.targetClass + '.'+ #root.methodName + '_' + #productId+'_'+#packageIds")
    public List<ZbyPackageResourceSimpleDto> queryPackageResList(String productId, List<String> packageIds) {
        logger.info("根据 productId={},packageId={},查询套餐的资源列表", productId, packageIds);
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("productId", productId);
            params.put("packageIdList", packageIds);
            return packageDetailMapper.queryPackageSimpleRes(params);
        } catch (Exception e) {
            logger.error("根据 productId={},packageId={},查询套餐的资源列表失败", productId, packageIds, e);
        }
        return null;
    }


    /**
     * 根据线路 资源id 获取资源信息
     *
     * @param resId
     * @return
     */
    public ZbyProductPackageDetail queryResourceDetailByResid(String resId) {
        try {
            logger.info("根据资源id={}获取资源信息", resId);
            return packageDetailMapper.queryResInfoById(resId);
        } catch (Exception e) {
            logger.error("根据资源id={}获取资源信息失败", resId, e);
        }
        return null;
    }

    /**
     * 根据订单编号，产品编号，套餐编号查询套餐详情列表
     *
     * @param productId
     * @param packageId
     * @return
     */
    public List<ZbyProductPackageDetail> getPackageDetailList(String productId, String packageId, String orderId) {
        if (StringUtils.isNotEmpty(productId) && StringUtils.isNotEmpty(packageId) && StringUtils.isNotEmpty(orderId)) {
            Map<String, Object> params = new HashMap<>();
            params.put("productId", productId);
            params.put("packageId", packageId);
            params.put("orderId", orderId);
            return packageDetailMapper.getPackageDetailList(params);
        }
        return null;
    }
}

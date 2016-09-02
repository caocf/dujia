package cn.com.gome.dujia.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.com.gome.dujia.dto.ZbyPackageDto;
import cn.com.gome.dujia.dto.ZbyPackagePriceDto;
import cn.com.gome.dujia.dto.ZbyPackageResourceSimpleDto;
import cn.com.gome.dujia.dto.ZbyPackageRespDto;
import cn.com.gome.dujia.enums.ResourceType;
import cn.com.gome.dujia.enums.TcImageType;
import cn.com.gome.dujia.enums.TrueFalseStatus;
import cn.com.gome.dujia.exception.BizException;
import cn.com.gome.dujia.mapper.business.ZbyProductPackageDetailMapper;
import cn.com.gome.dujia.mapper.business.ZbyProductPackageMapper;
import cn.com.gome.dujia.mapper.business.ZbyProductPackagePriceMapper;
import cn.com.gome.dujia.model.ZbyProductPackage;
import cn.com.gome.dujia.model.ZbyProductPackageDetail;
import cn.com.gome.dujia.model.ZbyProductPackagePrice;
import cn.com.gome.dujia.redis.CacheExpire;
import cn.com.gome.dujia.service.ZbyProductPackageDetailService;
import cn.com.gome.dujia.service.ZbyProductPackageService;
import cn.com.gome.dujia.service.ZbyProductService;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.gome.plan.tools.utils.StringUtils;

/**
 * 线路套餐服务
 * Created by zhaoxiang-ds on 2016/4/13.
 */
@Service
@Path("package")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class ZbyProductPackageServiceImpl implements ZbyProductPackageService {

    private static final Logger logger = LoggerFactory.getLogger(ZbyProductPackageServiceImpl.class);
    @Autowired
    private ZbyProductPackageMapper productPackageMapper;

    @Autowired
    private ZbyProductPackagePriceMapper priceMapper;

    @Autowired
    private ZbyProductPackageDetailMapper packageDetailMapper;

    @Autowired
    private ZbyProductPackageDetailService packageDetailService;
    
    @Autowired
    private ZbyProductService zbyProductService;

    /**
     * 获取指定线路的套餐列表
     *
     * @param productId
     * @return
     */
    @Cacheable(value = {CacheExpire.EXPIRE05M}, key = "#root.targetClass + '.'+ #root.methodName + '_' +   #productId")
    public ZbyPackageRespDto getProductPackageList(String productId) {
        try {
            if (StringUtils.isEmpty(productId)) {
                return null;
            }
            // 获取线路下的套餐列表
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("productId", productId);
            params.put("isDelete", TrueFalseStatus.FALSE);
            List<ZbyProductPackage> packages = productPackageMapper.queryProductPackages(params);
            if(null != packages && packages.size() > 0) {
            	ZbyPackageRespDto respDto = new ZbyPackageRespDto();
            	
            	// 产品下的套餐
                List<ZbyPackageDto> packageDtos = new ArrayList<ZbyPackageDto>();
                
            	// 根据套餐id,获取套餐下的资源(酒店、景区)列表
                List<String> packageIds = new ArrayList<String>();
                for (ZbyProductPackage pack : packages) {
                    packageIds.add(pack.getPackageId());
                }
                // 批量查询线路的套餐资源列表
                List<ZbyPackageResourceSimpleDto> resList = packageDetailService.queryPackageResList(productId, packageIds);

                // 遍历套餐下的资源，封装响应数据
                for (ZbyProductPackage pack : packages) {
                    String packageId = pack.getPackageId();
                    ZbyPackageDto packageDto = new ZbyPackageDto();
                    packageDto.setProductId(productId);
                    packageDto.setPackageId(packageId);
                    packageDto.setPackageName(pack.getPackageName());
                    packageDto.setPrice(pack.getTcDirectPrice_dis());
    
                    // 套餐
                    if (resList != null) {
                        List<ZbyPackageResourceSimpleDto> hotel = new ArrayList<>();
                        List<ZbyPackageResourceSimpleDto> scenery = new ArrayList<>();
                        for (ZbyPackageResourceSimpleDto resourceSimpleDto : resList) {
                            if (resourceSimpleDto.getPackageId().equals(packageId)) {
                                if (resourceSimpleDto.getResType() == ResourceType.HOTEL.getValue()) { //资源类型:0酒店，1景区
                                    hotel.add(resourceSimpleDto);
                                } else if (resourceSimpleDto.getResType() == ResourceType.SCENIC.getValue()) {
                                    scenery.add(resourceSimpleDto);
                                }
                            }
                        }
                        packageDto.setHotels(hotel);
                        packageDto.setSceneries(scenery);
                    }
                    packageDtos.add(packageDto);
                }
                //套餐详情
                respDto.setPackages(packageDtos);
                //最低价格
                Map<String, BigDecimal> map = zbyProductService.queryProductPrice();
                if (null != map) {
                	respDto.setMinPrice(map.get(productId));
                }
            	return respDto;
            }
        } catch (Exception e) {
            logger.error("根据线路productId={}，套餐列表失败", productId, e);
        }
        return null;
    }


    /**
     * 获取产品默认最近可售卖日期,根据套餐的提前预定日期计算
     *
     * @param productId
     * @return date
     */
    public Date queryDefaultSaleDate(String productId) {
        try {
            return priceMapper.queryDefaultSaleDate(productId);
        } catch (Exception e) {
            logger.error("获取产品{}默认最近可售卖日期失败", productId, e);
        }
        return null;
    }

    /**
     * 根据线路Id、售卖日期，获取套餐最低价、最高价，缓存5分钟
     *
     * @param productId
     * @param saleDate  格式：yyyyMMdd
     */
    @Cacheable(value = {CacheExpire.EXPIRE05M}, key = "#root.targetClass + '.'+ #root.methodName + '_' + #productId + '_' + #saleDate")
    private ZbyProductPackagePrice queryMinAndMaxPrice(String productId, String saleDate) {
        logger.info("根据线路productId={}、售卖日期={}，获取套餐最低价、最高价", productId, saleDate);
        try {
            Map<String, String> params = new HashMap<>();
            params.put("productId", productId);
            params.put("saleDate", saleDate);
            ZbyProductPackagePrice packagePrice = priceMapper.queryMinAndMaxPrice(params);
            return packagePrice;
        } catch (Exception e) {
            logger.error("根据线路productId={}、售卖日期={}，获取套餐最低价、最高价失败", productId, saleDate, e);
        }
        return null;
    }

    /**
     * 获取线路下的套餐列表
     *
     * @param productId
     * @param saleDate  格式：yyyyMMdd
     * @return
     */
    private List<ZbyProductPackage> queryPackagesById(String productId, String saleDate) {
        logger.info("根据线路productId={}、售卖日期={}，获取线路下的套餐列表", productId, saleDate);
        try {
            Map<String, String> params = new HashMap<>();
            params.put("productId", productId);
            params.put("saleDate", saleDate);
            List<ZbyProductPackage> packages = productPackageMapper.queryPackages(params);
            return packages;
        } catch (Exception e) {
            logger.error("根据线路productId={}、售卖日期={}，获取线路下的套餐列表失败", productId, saleDate, e);
        }
        return null;
    }


    /**
     * 根据线路id，获取有效价格班期列表
     *
     * @param productId
     * @return
     */
    @GET
    @Path("queryPrice/{productId}")
    @Cacheable(value = {CacheExpire.EXPIRE05M}, key = "#root.targetClass + '.'+ #root.methodName + '_' + #productId")
    public List<ZbyPackagePriceDto> getPackagePriceList(@PathParam("productId") Integer productId) {
        logger.info("根据线路productId={}获取有效价格班期列表", productId);
        try {
            if (productId == null) {
                return null;
            }
            return priceMapper.queryPriceListByProductId(productId.toString());
        } catch (Exception e) {
            logger.error("根据线路productId={}获取有效价格班期列表失败", productId, e);
        }
        return null;
    }

    /**
     * 根据线路id,套餐id,获取价格班期列表
     *
     * @param productId
     * @param packageId
     * @return
     */
    @GET
    @Path("queryPrice/{productId}/{packageId}")
    @Cacheable(value = {CacheExpire.EXPIRE05M}, key = "#root.targetClass + '.'+ #root.methodName + '_' + #productId +'_'+#packageId")
    public List<ZbyPackagePriceDto> queryPriceListByProIdAndPackId(@PathParam("productId") Integer productId, @PathParam("packageId") Integer packageId) {
        logger.info("根据线路productId={},packageId={}获取价格班期列表", productId, packageId);
        try {
            if (productId == null || packageId == null) {
                logger.info("不合法的请求参数:productId={},packageId={}", productId, packageId);
                return null;
            }
            Map<String, String> params = new HashMap<>();
            params.put("productId", productId.toString());
            params.put("packageId", packageId.toString());
            return priceMapper.queryPriceListByProIdAndPackId(params);
        } catch (Exception e) {
            logger.error("根据线路productId={}获取有效价格班期列表失败", productId, e);
        }
        return null;
    }


    /**
     * 根据prductId,packageId,获取套餐包含的资源详情列表
     *
     * @param productId
     * @param packageId
     * @return
     */
    @GET
    @Path("res/{productId}/{packageId}")
    public List<ZbyProductPackageDetail> getPackageResources(@PathParam("productId") Integer productId, @PathParam("packageId") Integer packageId) {
        logger.info("根据线路productId={},packageId={}获取套餐的资源详情", productId, packageId);
        if (productId == null || packageId == null) {
            logger.info("不合法的请求参数:productId={},packageId={}", productId, packageId);
            return null;
        }
        List<ZbyProductPackageDetail> list = packageDetailService.queryPackageDetailList(productId.toString(), packageId.toString());

        return list;
    }

    /**
     * 根据resourceId获取资源详情(酒店、景区详情页)
     *
     * @param resId
     * @return
     */
    @GET
    @Path("res/{resId}")
    public ZbyProductPackageDetail getPackageResourceDetail(@PathParam("resId") Integer resId) {
        if(resId==null){
            logger.info("获取资源详情非法参数：resId={}",resId);
            return null;
        }
        ZbyProductPackageDetail res = packageDetailService.queryResourceDetailByResid(resId.toString());
        // 图片尺寸转换
        if (StringUtils.isNotEmpty(res.getResImageList())) {
            Map<String, String> imgMap = new HashMap<>();
            List<String> sImg = new ArrayList<>();
            List<String> bImg = new ArrayList<>();
            List<String> imgs = Arrays.asList(res.getResImageList().split(","));
            for (String img : imgs) {
                if (StringUtils.isNotEmpty(img)) {
                    String s1 = img.substring(0, img.lastIndexOf("."));
                    String s2 = img.substring(img.lastIndexOf("."));
                    // 转小图
                    String smallUrl = s1 + TcImageType.WH120x120.getValue() + s2;
                    sImg.add(smallUrl);
                    // 转大图
                    String bigUrl = s1 + TcImageType.WH480x360.getValue() + s2;
                    bImg.add(bigUrl);
                }
            }
            res.setSmallImg(sImg);
            res.setBigImg(bImg);
        } else {

        }
        return res;
    }

    @Override
    public ZbyProductPackage selectByPackageId(String packageId) {
        return productPackageMapper.selectByPackageId(packageId);
    }


    @Override
    public ZbyProductPackagePrice selectPackagePriceByIdDate(String productId, String packageId,
                                                             String packageSaleDate) throws BizException {
        ZbyProductPackagePrice productPackagePrice = priceMapper.selectPackagePriceByIdDate(productId, packageId, packageSaleDate);
        if (null == productPackagePrice) {
            logger.error("查询套餐价格为空:productId：{},packageId：{}，packageSaleDate：{}", productId, packageId, packageSaleDate);
            throw new BizException("查询套餐价格为空!");
        }
        return productPackagePrice;
    }

    public ZbyProductPackage getPackageByProductIDAndPackageID(String productId, String packageId) {
        ZbyProductPackage productPackage = new ZbyProductPackage();
        productPackage.setProductId(productId);
        productPackage.setPackageId(packageId);
        productPackage.setIsDelete(false);
        ZbyProductPackage proPackage = productPackageMapper.selectOne(productPackage);
        return proPackage;
    }


}

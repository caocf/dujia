package cn.com.gome.dujia.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.disconf.RecommendDisconf;
import cn.com.gome.dujia.dto.QuickLinkDto;
import cn.com.gome.dujia.dto.QuickLinkRespDto;
import cn.com.gome.dujia.dto.ZbyProductDetailDto;
import cn.com.gome.dujia.dto.ZbyProductDetailRespDto;
import cn.com.gome.dujia.dto.ZbyProductResourceDto;
import cn.com.gome.dujia.dto.ZbyProductRespDto;
import cn.com.gome.dujia.dto.ZbyProductSimpleDto;
import cn.com.gome.dujia.enums.PageModelType;
import cn.com.gome.dujia.enums.PlatformType;
import cn.com.gome.dujia.enums.ResourceType;
import cn.com.gome.dujia.enums.TcImageType;
import cn.com.gome.dujia.mapper.business.ZbyProductMapper;
import cn.com.gome.dujia.mapper.business.ZbyProductPackageDetailMapper;
import cn.com.gome.dujia.mapper.business.ZbyProductPackagePriceMapper;
import cn.com.gome.dujia.mapper.business.ZbyRecomInfoMapper;
import cn.com.gome.dujia.model.Advert;
import cn.com.gome.dujia.model.AroundCity;
import cn.com.gome.dujia.model.ZbyCity;
import cn.com.gome.dujia.model.ZbyProduct;
import cn.com.gome.dujia.model.ZbyProductPackagePrice;
import cn.com.gome.dujia.model.ZbyRecomInfo;
import cn.com.gome.dujia.redis.CacheExpire;
import cn.com.gome.dujia.service.AdvertService;
import cn.com.gome.dujia.service.SearchService;
import cn.com.gome.dujia.service.ZbyCityService;
import cn.com.gome.dujia.service.ZbyProductService;
import cn.com.gome.dujia.vo.search.Product;
import cn.com.gome.dujia.vo.search.QueryRequest;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.gome.plan.tools.utils.PinyinUtils;
import com.gome.plan.tools.utils.StringUtils;

/**
 * 线路对外服务
 * Created by zhaoxiang-ds on 2016/4/13.
 */
@Service
@Path("line")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class ZbyProductServiceImpl implements ZbyProductService {

    private static final Logger logger = LoggerFactory.getLogger(ZbyProductServiceImpl.class);
    @Autowired
    private ZbyProductMapper zbyProductMapper;

    @Autowired
    private ZbyProductPackageDetailMapper packageDetailMapper;

    @Autowired
    private ZbyRecomInfoMapper recomInfoMapper;

    @Autowired
    private ZbyCityService cityService;

    @Autowired
    private AdvertService advertService;

    @Autowired
    private SearchService searchService;
    
    @Autowired
    private ZbyProductPackagePriceMapper zbyProductPackagePriceMapper;


    /**
     * 获取国美旅行首页推荐数据
     *
     * @param code
     */
    @GET
    @Path("travel/{code}")
    @Override
    public ZbyProductRespDto getGomeTravelIndexRes(@PathParam("code") Integer code) {

        try {
            logger.info("根据gomeCode={}获取国美旅行首页数据", code);

            ZbyProductRespDto respDto = new ZbyProductRespDto();
            String key = "T";// 旅行首页配置key,与配置中心一致

            // 根据国美城市code转换为度假城市
            ZbyCity city = cityService.queryCityByGomeCode(code.toString());
            if (city == null || city.getCityId() == null) {
                // 获取默认城市
                city = cityService.queryDefaultCity();
            }
            if (city == null || city.getCityId() == null) {
                return respDto;
            }
            String cityId = city.getCityId().toString();
            // 获取旅行主页推荐线路
            List<Advert> recommends = advertService.queryRecommendList(PlatformType.WEB.getValue(), cityId, PageModelType.PAGE_INDEX_TRAVEL.getValue());

            // 保存所有取到的线路数据
            Map<String, ZbyProductRespDto> tempMap = new LinkedHashMap<>();

            // 保存所有配置线路id，用于排除重复
            List<String> filterIds = new ArrayList<>();

            // 线路列表
            List<ZbyProductSimpleDto> sDtos = new LinkedList<>();

            for (Advert ad : recommends) {
                // 封装数据
                ZbyProductSimpleDto sDto = new ZbyProductSimpleDto();
                sDto.setProductId(ad.getProductId());
                sDto.setProductName(ad.getProductName());
                sDto.setCityName(ad.getProductCityName());
                sDto.setImageUrl(ad.getProductImage());
                sDto.setMinPrice(ad.getProductPrice());
                sDto.setProductUrl(ad.getUrl());
                sDto.setTitleName(ad.getTitleName());
                // 判断第一个位置是不是配置的广告
                if (ad.getPosition().equals("T1")) {
                    respDto.setFlag(true);
                }
                // 保存id排除重复
                filterIds.add(ad.getProductId());
                sDtos.add(sDto);
            }
            // 计算需要补几个数据
            int size = 0;
            if (null == recommends) {
                size = 8;
            } else if (recommends.size() < 8) {
                size = 8 - recommends.size();
            }
            // 后台配置了小于8，补全位置
            if (size > 0) {
                logger.info("调用搜索服务，根据cityId={}、楼层id={}、线路列表={}、待查询个数={}", cityId, key, filterIds, (8 - recommends.size()));
                QueryRequest queryRequest = new QueryRequest();
                queryRequest.setCityId(cityId);
                queryRequest.setModule(key);
                queryRequest.setProductIds(filterIds);
                queryRequest.setPageSize(8 - recommends.size());
                // 调用搜索服务
                List<Product> sResult = searchService.searchRelationLabel(queryRequest);
                for (Product res : sResult) {
                    ZbyProductSimpleDto sDto = new ZbyProductSimpleDto();
                    sDto.setProductId(res.getProductId());
                    sDto.setProductName(res.getSubName());
                    sDto.setCityName(res.getCityName());
                    sDto.setImageUrl(res.getIndexImageUrl());
                    sDto.setMinPrice(res.getProductMinPrice());
                    sDtos.add(sDto);
                }
            }
            respDto.setData(sDtos);
            return respDto;
        } catch (Exception e) {
            logger.error("根据gomeCode={}获取国美旅行首页数据失败", code, e);
        }
        return null;
    }

    /**
     * 获取首页 楼层数据
     *
     * @param plateform 平台
     * @param cityId    城市id
     * @return
     */
    @GET
    @Path("get/{plateform}/{cityId}")
    @Override
    public List<ZbyProductRespDto> queryProductRes(@PathParam("plateform") Integer plateform, @PathParam("cityId") String cityId) {

        List<ZbyProductRespDto> respDtos = new LinkedList<>();
        try {
            // 获取首页后台推荐线路
            List<Advert> recommends = advertService.queryRecommendList(plateform, cityId, PageModelType.PAGE_INDEX.getValue());

            // 保存所有取到的线路数据
            Map<String, ZbyProductRespDto> tempMap = new LinkedHashMap<>();

            // 保存所有配置线路id，用于排除重复
            List<String> filterIds = new ArrayList<>();

            // 获取后台配置
            List<cn.com.gome.dujia.disconf.Advert> advertConfs = RecommendDisconf.getRecommends();
            for (cn.com.gome.dujia.disconf.Advert conf : advertConfs) {
                // 排除首页其它位置
                if (!conf.getValue().toUpperCase().contains("F")) {
                    continue;
                }
                // 线路响应对象
                ZbyProductRespDto dto = new ZbyProductRespDto();
                dto.setName(conf.getName());
                dto.setValue(conf.getValue());

                // 线路列表
                List<ZbyProductSimpleDto> sDtos = new LinkedList<>();
                boolean hasF0 = false;//标记各楼层第一个位置是否配置，便于页面样式切换
                for (Advert ad : recommends) {
                    //  判断属于此楼层
                    if (ad.getModule().equals(conf.getValue())) {
                        //判断F0位置是否配置
                        if (ad.getPosition().equals(conf.getPositions().get(0).getValue())) {
                            hasF0 = true;
                        }
                        // 如果存在线路id，保存用于排重
                        if (!StringUtils.isEmpty(ad.getProductId())) {
                            filterIds.add(ad.getProductId());
                        }
                        // 封装数据
                        ZbyProductSimpleDto sDto = new ZbyProductSimpleDto();
                        sDto.setProductId(ad.getProductId());
                        sDto.setProductName(ad.getProductName());
                        sDto.setCityName(ad.getProductCityName());
                        sDto.setImageUrl(ad.getProductImage());
                        sDto.setMinPrice(ad.getProductPrice());
                        sDto.setProductUrl(ad.getUrl());
                        sDto.setTitleName(ad.getTitleName());
                        //sDto.setPosition(ad.getPosition());
                        //
                        sDtos.add(sDto);
                    }
                    if (hasF0) {
                        // 标记第一个位置配置广告
                        dto.setFlag(true);
                    }
                }
                dto.setData(sDtos);
                tempMap.put(conf.getValue(), dto);
            }

            // 数据排重
            for (String key : tempMap.keySet()) {
                ZbyProductRespDto obj = tempMap.get(key);
                // 楼层下配置的线路数据
                List<ZbyProductSimpleDto> prds = obj.getData();

                int size = 0;
                if (key.equalsIgnoreCase("F1")) {
                    if (obj.getFlag()) {//第一个位置有配置广告
                        size = 7 - obj.getData().size();
                    } else {
                        size = 8 - obj.getData().size();
                    }
                } else {
                    size = 4 - obj.getData().size();
                }
                if (size > 0) {
                    // 调用搜索服务，根据城市id、楼层id、待查询个数、全部线路id列表，排重
                    logger.info("调用搜索服务，根据cityId={}、楼层id={}、线路列表={}、待查询个数={}", cityId, key, filterIds, size);
                    QueryRequest queryRequest = new QueryRequest();
                    queryRequest.setCityId(cityId);
                    queryRequest.setModule(key);
                    queryRequest.setProductIds(filterIds);
                    queryRequest.setPageSize(size);
                    // 调用搜索服务
                    List<Product> sResult = searchService.searchRelationLabel(queryRequest);

                    // 查询楼层数据不足时，获取全国数据补位
                    if (sResult.size() < size) {
                        for (Product p : sResult) {
                            filterIds.add(p.getProductId());
                        }
                        QueryRequest tempRequest = new QueryRequest();
                        tempRequest.setProductIds(filterIds);
                        tempRequest.setModule(key);
                        tempRequest.setTargetCityId(cityId); // 目的地，搜索服务用于标记查询周边城市
                        tempRequest.setPageSize(size - sResult.size());
                        List<Product> tempResult = searchService.searchRelationLabel(tempRequest);
                        if (StringUtils.isNotObjectEmpty(tempRequest)) {
                            sResult.addAll(tempResult);
                        }
                    }

                    // 封装响应数据
                    if (sResult != null) {
                        for (Product res : sResult) {
                            ZbyProductSimpleDto sDto = new ZbyProductSimpleDto();
                            sDto.setProductId(res.getProductId());
                            sDto.setProductName(res.getSubName());
                            sDto.setCityName(res.getCityName());
                            sDto.setImageUrl(res.getIndexImageUrl());
                            sDto.setMinPrice(res.getProductMinPrice());
                            prds.add(sDto);
                            // 添加线路id，用于下次排重
                            filterIds.add(res.getProductId());
                        }
                        obj.setData(prds); // 重新设置数据
                    }
                }
                // 判断楼层数据,如果楼层数据为0，则不显示该楼层
                if (obj.getData().size() > 0) {
                    respDtos.add(obj);// 设置返回数据
                }
            }

        } catch (Exception e) {
            logger.error("根据ciytId={},platform={}获取首页数据失败", cityId, plateform, e);
        }
        return respDtos;
    }


    /**
     * 获取列表页推荐线路
     *
     * @param plateform
     * @param cityId
     * @return
     */
    @Cacheable(value = {CacheExpire.EXPIRE30M}, key = "#root.targetClass + '.'+ #root.methodName +'_'+ #plateform +'_'+ #cityId")
    public List<Product> queryRecommendProduct4List(Integer plateform, String cityId) {
        try {
            // 获取推荐
            List<Advert> list = advertService.queryRecommendList(plateform, cityId, PageModelType.PAGE_LIST.getValue());
            if (null == list || !(list.size() > 0)) {
                return null;
            }
            List<String> ids = new ArrayList<>();
            for (Advert ad : list) {
                ids.add(ad.getProductId());
            }
            List<Product> prds = searchService.searchProducts(ids);
            if (prds == null) {
                return null;
            }
            for (Product prd : prds) {
                for (Advert ad : list) {
                    if (prd.getProductId().equals(ad.getProductId())) {
                        // 如果后台配置，则使用配置的
                        if (StringUtils.isNotEmpty(ad.getProductName())) {
                            prd.setMainName(ad.getProductName());
                            prd.setMainTitle(ad.getProductName());
                            prd.setSubName(ad.getProductName());
                            prd.setSubTitle(ad.getProductName());
                        }
                        if (StringUtils.isNotEmpty(ad.getProductImage())) {
                            prd.setImageUrl(ad.getProductImage());
                        }
                    }
                }
            }
            return prds;
        } catch (Exception e) {
            logger.error("获取列表页推荐线路失败", e);
        }
        return null;
    }

    /**
     * 根据id获取线路详情
     *
     * @param productId
     * @return
     */
    @Override
    @Cacheable(value = {CacheExpire.EXPIRE30M}, key = "#root.targetClass + '.'+ #root.methodName +'_' + #productId")
    public ZbyProductDetailRespDto getProductDetailById(String productId) {
        try {
            ZbyProductDetailRespDto resp = new ZbyProductDetailRespDto();
            // 获取线路详情
            ZbyProductDetailDto productDetailDto = zbyProductMapper.getProductDetailDtoById(productId);
            if (productDetailDto == null) {
                return null;
            }
            // 线路行程推荐--图片样式替换
            productDetailDto.setProductRouteDes(this.replaceImgUrl(productDetailDto.getProductRouteDes()));
            // 线路特色--图片样式替换
            productDetailDto.setProductFeature(this.replaceImgUrl(productDetailDto.getProductFeature()));

            // 线路上下架标记
            resp.setIsDelete(productDetailDto.getDelete());
            // 线路详情
            resp.setDetail(productDetailDto);
            // 处理线路图片
            Map<String, String> imgMap = this.imgNameToUrl(productDetailDto.getProductImageUrlList());
            if (imgMap.size() > 0) {
                resp.setImgUrl(imgMap);
            }
            // 获取线路资源列表(酒店、景区、gps)
            List<ZbyProductResourceDto> resourceDtos = this.queryProductResource(productId);
            List<ZbyProductResourceDto> hotels = new ArrayList<>();
            List<ZbyProductResourceDto> sceneries = new ArrayList<>();
            if (resourceDtos.size() > 0) {
                for (ZbyProductResourceDto dto : resourceDtos) {
                    // 资源类型(0 酒店，1 景区，2 餐饮，3 门票 4 混搭)
                    if (dto.getResType().equals(ResourceType.HOTEL.getValue().toString())) {
                        hotels.add(dto);
                    } else if (dto.getResType().equals(ResourceType.SCENIC.getValue().toString())) {
                        sceneries.add(dto);
                    }
                }
                resp.setHotels(hotels);
                resp.setSceneries(sceneries);
                // resp.setResource(resourceDtos);
            }
            // 线路所属主题
            List<String> threms = this.queryRecoms(productId);
            if (null != threms && threms.size() > 0) {
                resp.setTheme(threms);
            }
            return resp;
        } catch (Exception e) {
            logger.error("根据id={}获取线路详情出错", productId, e);
        }
        return null;
    }

    /**
     * 替换文本中img标签中src的内容
     *
     * @param text
     * @return
     */
    private String replaceImgUrl(String text) {
        if (StringUtils.isNotEmpty(text)) {
            // 删除data-cke-saved-src标签
            Pattern pattern1 = Pattern.compile("data-cke-saved-src=\"([^\"]+)\"");
            Matcher matcher1 = pattern1.matcher(text);
            while (matcher1.find()) {
                String url1 = matcher1.group(1);
                String dd = "data-cke-saved-src=\"" + url1 + "\"";
                text = text.replace(dd, " ");
            }
            // 替换 img 标签
            Pattern pattern = Pattern.compile("src=\"([^\"]+)\"");
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                String url = matcher.group(1); //商户url
                //国美url
                String newUrl = GlobalDisconf.domainDujia + "/images/grey.gif\" data-lazy-init=\"" + url + "\"  width=\"898";
                text = text.replace(url, newUrl);
            }
            return text;
        }
        return null;
    }

    /**
     * 通过图片名称转换成访问地址
     *
     * @param imgNames
     * @return
     */
    private Map<String, String> imgNameToUrl(String imgNames) {
        Map<String, String> map = new HashMap<>();
        if (StringUtils.isEmpty(imgNames)) {
            return map;
        }
        int i = 1;
        for (String img : imgNames.split(",")) {
            if (i++ > 5) {
                break;
            }
            String s1 = img.substring(0, img.lastIndexOf("."));
            String s2 = img.substring(img.lastIndexOf("."));
            // 名称转小图
            String smallUrl = s1 + TcImageType.WH100x75.getValue() + s2;
            // 名称转大图
            String bigUrl = s1 + TcImageType.WH500x270.getValue() + s2;
            map.put(smallUrl, bigUrl);
        }
        return map;
    }

    /**
     * 获取线路资源信息列表(酒店、景区、gps)
     *
     * @param productId
     * @return
     */
    private List<ZbyProductResourceDto> queryProductResource(String productId) {
        List<ZbyProductResourceDto> resourceDtos = new ArrayList<>();
        try {
            resourceDtos = packageDetailMapper.queryResourceByProductId(productId);
        } catch (Exception e) {
            logger.error("获取线路{}资源信息列表", productId, e);
        }
        return resourceDtos;
    }

    /**
     * 获取线路主题列表
     *
     * @param productId
     * @return
     */
    @Cacheable(value = {CacheExpire.EXPIRE01H}, key = "#root.targetClass + '.'+ #root.methodName +'_' +#productId")
    public List<String> queryRecoms(String productId) {
        List<String> threms = new ArrayList<>();
        try {
            ZbyRecomInfo recomInfo = new ZbyRecomInfo();
            recomInfo.setProductId(productId);
            recomInfo.setIsDelete(false);// 排除不可用的
            List<ZbyRecomInfo> recomInfos = recomInfoMapper.select(recomInfo);
            for (ZbyRecomInfo t : recomInfos) {
                threms.add(t.getTitle());
            }
            if (threms.size() > 0) {
                return threms;
            }
        } catch (Exception e) {
            logger.error("获取线路{}主题列表失败", productId, e);
        }
        return null;
    }

    @Override
    @GET
    @Path("/quick/{platform}/{cityid}")
    @Cacheable(value = {CacheExpire.EXPIRE01D}, key = "#root.targetClass + '.'+ #root.methodName + '_' +#platform +'_'+#cityId")
    public QuickLinkRespDto getQuickLink(@PathParam("platform") Integer platform, @PathParam("cityid") Integer cityId) {
        // 获取数据
        return this.getQuickLinkData(platform, cityId.toString());
    }


    public QuickLinkRespDto getQuickLinkData(Integer platform, String cityId) {
        try {
            logger.info("根据cityId={}获取首页快速链接信息", cityId);
            QuickLinkRespDto respDto = new QuickLinkRespDto();
            ZbyCity city = cityService.selectCityById(cityId.toString());
            String cityPy = city.getCityPinyin();
            respDto.setCityId(city.getCityId().toString());
            respDto.setCityPy(cityPy);
            respDto.setCityName(city.getCityName());
            // 可玩天数
            List<QuickLinkDto> days = this.queryTravelDays(cityId, cityPy);
            // 特色玩法
            List<QuickLinkDto> plays = this.queryPlayTypes(cityId, cityPy);
            // 当前城市热门景区
            List<QuickLinkDto> scenery = this.queryCityHotScenery(cityId, cityPy);
            // 周边城市热门景区
            Map<String, List<QuickLinkDto>> aroundScenery = this.queryAroundCityHotScenery(cityId, cityPy);
            // 热门目的地
            List<QuickLinkDto> destination = this.queryHotDestination(platform, cityId, cityPy);
            if (days != null && days.size() > 0) {
                respDto.setDays(days);
            }
            if (plays != null && plays.size() > 0) {
                respDto.setPlays(plays);
            }
            if (scenery != null && scenery.size() > 0) {
                respDto.setHotScenery(scenery);
                aroundScenery.put(city.getCityName(), scenery);//首页浮层-周边热门景区中添加当前城市的景区
            }
            if (aroundScenery != null && aroundScenery.size() > 0) {
                respDto.setAroundScenery(aroundScenery);
            }
            if (destination != null && destination.size() > 0) {
                respDto.setDestination(destination);
            }
            return respDto;
        } catch (Exception e) {
            logger.error("根据城市id={}获取首页快速链接失败", cityId, e);
        }
        return null;
    }

    /**
     * 行程天数格式化
     *
     * @param days
     * @return
     */
    @Override
    public String travlDaysFormat(Integer days) {
        if (days > 0) {
            return days + "天" + (days - 1) + "晚";
        }
        return null;
    }


    /**
     * 首页 城市下游玩天数列表
     *
     * @param cityId
     * @return
     */
    private List<QuickLinkDto> queryTravelDays(String cityId, String cityPy) {
        try {
            List<QuickLinkDto> dtos = zbyProductMapper.queryTravelDays(cityId);
            for (QuickLinkDto dto : dtos) {
                dto.setCityPy(cityPy);
            }
            return dtos;
        } catch (Exception e) {
            logger.error("获取城市id={}下游玩天数列表失败", cityId, e);
        }
        return null;
    }

    /**
     * 首页 特色玩法列表
     *
     * @param cityId
     * @return
     */
    private List<QuickLinkDto> queryPlayTypes(String cityId, String cityPy) {
        try {
            List<QuickLinkDto> dtos = recomInfoMapper.queryRecomByCity(cityId);
            for (QuickLinkDto dto : dtos) {
                dto.setCityPy(cityPy);
                dto.setValue(PinyinUtils.toPinyin(dto.getName(), true, true));
            }
            return dtos;
        } catch (Exception e) {
            logger.error("获取城市id={}特色玩法列表失败", cityId, e);
        }
        return null;
    }

    /**
     * 获取周边城市 热门景点
     *
     * @param cityId
     * @return
     */
    private Map<String, List<QuickLinkDto>> queryAroundCityHotScenery(String cityId, String cityPy) {
        Map<String, List<QuickLinkDto>> map = new HashMap<>();
        try {
            // 获取热门-周边城市
            List<AroundCity> aroundCities = cityService.getHotAroundCity(PlatformType.WEB.getValue(), cityId);
            if(aroundCities==null){
                logger.info("根据：cityId={},未找到热门-周边城市",cityId);
                return null;
            }
            List<String> cityIds = new ArrayList<>();
            for (AroundCity city : aroundCities) {
                cityIds.add(city.getAroundCityId());
            }
            // 查询线路最多的前5个城市
            if (cityIds.size() > 0) {
                Map param = new HashMap();
                param.put("cityIds", cityIds);
                param.put("size", 5);
                List<ZbyProduct> prds = zbyProductMapper.queryMostLineByCity(param);
                if (prds != null && prds.size() > 0) {
                    // 查询城市下的热门景区
                    for (ZbyProduct tmp : prds) {
                        // 获取周边热门景区
                        List<QuickLinkDto> aroundHotScenery = this.queryCityHotScenery(tmp.getCityId(), tmp.getCityPinyin());
                        map.put(tmp.getCityName(), aroundHotScenery);
                    }
                }
            }
            return map;
        } catch (Exception e) {
            logger.error("获取城市id={}周边城市热门景点失败", cityId, e);
        }
        return null;
    }

    /**
     * 获取城市热门景区
     *
     * @param cityId
     * @return
     */
    public List<QuickLinkDto> queryCityHotScenery(String cityId, String cityPy) {
        try {
            logger.info("获取城市{}热门景区", cityId);
            Map map = new HashMap();
            map.put("cityId", cityId);
            map.put("size", 5);
            List<QuickLinkDto> hotScenery = packageDetailMapper.queryCityHotSceneryBySize(map);
            for (QuickLinkDto dto : hotScenery) {
                dto.setCityPy(cityPy);
            }
            return hotScenery;
        } catch (Exception e) {
            logger.error("获取城市{}热门景区失败", cityId, e);
        }
        return null;
    }

    /**
     * 首页浮层- 热门目的地(周边城市)
     *
     * @param cityId
     * @return
     */
    private List<QuickLinkDto> queryHotDestination(Integer platform, String cityId, String cityPy) {
        List<QuickLinkDto> dtos = new ArrayList<>();
        try {
            List<AroundCity> cities = cityService.getHotAroundCity(platform, cityId);
            for (AroundCity city : cities) {
                QuickLinkDto dto = new QuickLinkDto();
                dto.setId(city.getAroundCityId());
                dto.setName(city.getAroundCityName());
                dto.setValue(city.getAroundCityPinyin());
                dto.setCityPy(cityPy);
                dtos.add(dto);
            }
        } catch (Exception e) {
            logger.error("根据城市id={}获取热门目的地失败", cityId, e);
        }
        return dtos;
    }

    /**
     * 根据产品ID获取产品信息
     */

    public ZbyProduct selectByPrimaryKey(String productId) {
        return zbyProductMapper.selectByPrimaryKey(productId);
    }

    /**
     * 查询线路销量，浏览量
     *
     * @param product
     * @return
     */
    public List<ZbyProduct> querySaleBrowseCount(ZbyProduct product) {
        if (null != product) {
            return zbyProductMapper.querySaleBrowseCount(product);
        }
        return null;
    }

    /**
     * 获取城市下销量、浏览量最高的前10个景区列表
     * (列表页：看看大家去哪玩)
     *
     * @param cityId
     * @return
     */
    @GET
    @Path("/hotscenery/{cityId}")
    @Cacheable(value = {CacheExpire.EXPIRE01D}, key = "#root.targetClass + '.'+ #root.methodName + '_' + #cityId")
    public List<ZbyProductResourceDto> getSceneriesBySealViewCount(@PathParam("cityId") Integer cityId) {
        try {
            String cid = null;
            if (cityId != null) {
                cid = cityId.toString();
            }
            // 查询销量、浏览量最高的前10个套餐
            return packageDetailMapper.querySceneryBySaleOrBrowseCount(cid);
        } catch (Exception e) {
            logger.error("根据城市id={}获取销量、浏览量最高的前10个景区列表失败", cityId, e);
        }
        return null;
    }

    @Override
    public ZbyProductDetailRespDto getProductAndPackageByIds(String productId, String packageId) {
        ZbyProductDetailRespDto resp = new ZbyProductDetailRespDto();
        try {
            // 获取线路详情
            ZbyProductDetailDto productDetailDto = zbyProductMapper.getProductDetailDtoById(productId);
            resp.setDetail(productDetailDto);
            // 处理线路图片
            Map<String, String> imgMap = this.imgNameToUrl(productDetailDto.getProductImageUrlList());
            if (imgMap.size() > 0) {
                resp.setImgUrl(imgMap);
            }
            // 获取线路资源列表(酒店、景区、gps)
            List<ZbyProductResourceDto> resourceDtos = packageDetailMapper.queryResourceByPackageId(packageId);
            if (resourceDtos.size() > 0) {
                resp.setResource(resourceDtos);
            }
            // 线路所属主题
            List<String> threms = this.queryRecoms(productId);
            if (threms.size() > 0) {
                resp.setTheme(threms);
            }
            return resp;
        } catch (Exception e) {
            logger.error("根据id={}获取线路详情出错", productId, e);
        }
        return resp;
    }

    @Override
    public Integer getTravlDaysByProductId(String productId) {
        return zbyProductMapper.getTravlDaysByProductId(productId);
    }

    @Override
    public List<Integer> queryAllTravelDays() {
        return zbyProductMapper.queryAllTravelDays();
    }
    
    /**
     * 获取线路的最低价格
     * @return
     */
	@Cacheable(value = { CacheExpire.EXPIRE02H }, key = "#root.targetClass + '_' + #root.methodName")
	public Map<String, BigDecimal> queryProductPrice() {
		try {
			Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
			List<ZbyProductPackagePrice> list = zbyProductPackagePriceMapper.queryProductPrice();
			if (null != list && list.size() > 0) {
				for (ZbyProductPackagePrice ppp : list) {
					map.put(ppp.getProductId(), ppp.getTcDirectPrice());
				}
			}
			return map;
		} catch (Exception e) {
			logger.error("获取当前时间线路的最低价格失败", e);
		}
		return null;
	}
}

package cn.com.gome.dujia.service.impl;

import cn.com.gome.dujia.dto.QuickLinkRespDto;
import cn.com.gome.dujia.dto.ZbyCityDto;
import cn.com.gome.dujia.enums.PageModelType;
import cn.com.gome.dujia.enums.PlatformType;
import cn.com.gome.dujia.model.Advert;
import cn.com.gome.dujia.model.AroundCity;
import cn.com.gome.dujia.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaoxiang-ds on 2016/6/3.
 */
@Service
public class ProductCacheServiceImpl implements ProductCacheService {
    private static final Logger logger = LoggerFactory.getLogger(ProductCacheServiceImpl.class);
    @Autowired
    private ZbyCityService cityService;
    @Autowired
    private RedisCacheService cacheService;
    @Autowired
    private ZbyProductService productService;
    @Autowired
    private AdvertService advertService;

    /**
     * 更新首页浮层缓存
     */
    public void updateQuickLinkCache() {
        String key = "class cn.com.gome.dujia.service.impl.ZbyProductServiceImpl.getQuickLink_1_";
        try {
            logger.info("开始更新首页浮层缓存数据");
            // 获取全部可定位的城市列表
            List<ZbyCityDto> list = cityService.queryAllEffectiveCity();
            for (ZbyCityDto ct : list) {
                // 获取城市的浮层数据
                QuickLinkRespDto res = productService.getQuickLinkData(PlatformType.WEB.getValue(), ct.getId().toString());
                if (res != null) {
                    // 更新缓存数据，缓存1天
                    cacheService.set(key + ct.getId(), res, 24 * 60 * 60);
                }
            }
            logger.info("更新首页浮层缓存数据结束");

        } catch (Exception e) {
            logger.error("更新首页浮层缓存失败", e);
        }
    }

    /**
     * 更新周边去哪玩缓存
     */
    public void updateWeekendCache() {
        String key = "class cn.com.gome.dujia.service.impl.ZbyCityServiceImpl.getWeekendCity_1_";
        try {
            logger.info("更新首页[周边去哪玩]缓存开始");
            // 获取全部可定位的城市列表
            List<ZbyCityDto> list = cityService.queryAllEffectiveCity();
            for (ZbyCityDto ct : list) {
                List<AroundCity> res = cityService.getWeekendCityData(PlatformType.WEB.getValue(), ct.getId().toString());
                if (res != null) {
                    // 更新缓存数据，缓存1天
                    cacheService.set(key + ct.getId(), res, 24 * 60 * 60);
                }
            }
            logger.info("更新首页[周边去哪玩]缓存结束");
        } catch (Exception e) {
            logger.error("更新首页[周边去哪玩]缓存失败", e);
        }
    }

    /**
     * 更新首页[推荐线路和广告]缓存
     */
    public void updateRecommendCache() {
        String key = "class cn.com.gome.dujia.service.impl.AdvertServiceImpl.queryRecommendList_1_";
        try {
            logger.info("更新首页[推荐线路和广告]缓存开始");
            // 获取全部可定位的城市列表
            List<ZbyCityDto> list = cityService.queryAllEffectiveCity();
            for (ZbyCityDto ct : list) {
                List<Advert> res = advertService.queryRecommendDatas(PlatformType.WEB.getValue(), ct.getId().toString(), PageModelType.PAGE_INDEX.getValue());
                if (null != res && res.size() > 0) {
                    // 更新缓存数据，缓存30分钟
                    cacheService.set(key + ct.getId() + "_index", res, 30 * 60);
                }
            }
            logger.info("更新首页[推荐线路和广告]缓存结束");
        } catch (Exception e) {
            logger.error("更新首页[推荐线路和广告]缓存失败", e);
        }
    }
}

package cn.com.gome.dujia.service;

/**
 * 线路缓存服务
 * Created by zhaoxiang-ds on 2016/6/3.
 */
public interface ProductCacheService {

    /**
     * 更新首页浮层缓存
     */
    public void updateQuickLinkCache();

    /**
     * 更新首页周边去哪玩缓存
     */
    public void updateWeekendCache();

    /**
     * 更新首页[推荐线路和广告]缓存
     */
    public void updateRecommendCache();
}

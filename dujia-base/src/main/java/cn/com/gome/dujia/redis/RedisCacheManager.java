package cn.com.gome.dujia.redis;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;

import cn.com.gome.dujia.service.RedisCacheService;

/**
 * 扩展redisCacheManager
 */
public class RedisCacheManager extends AbstractCacheManager {

    /**
     * cache collection
     */
    private Collection<Cache> caches = null;

    @Autowired
    private RedisCacheService cacheService;

    protected Collection<? extends Cache> loadCaches() {
        if (caches == null) {
            synchronized (RedisCacheManager.class) {
                if (caches == null) {
                    caches = new ArrayList<>();
                    caches.add(cacheService);
                }
            }
        }
        return caches;
    }

    public void setCaches(Collection<Cache> caches) {
        this.caches = caches;
    }
}
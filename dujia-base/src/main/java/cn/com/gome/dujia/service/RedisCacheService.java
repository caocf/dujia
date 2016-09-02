package cn.com.gome.dujia.service;

import java.util.Map;

import org.springframework.cache.Cache;

/**
 * redis 缓存服务
 * 
 * @author xiongyan
 * @date 2016年4月25日 下午2:23:50
 */
public interface RedisCacheService extends Cache {

    /**
     * 给一个 KEY 设置一个 值 ，并且设置有效时间
     *
     * @param key    存储的KEY
     * @param value  存储KEY的值
     * @param expire 有效时间
     */
    void set(String key, Object value, int expire);

    /**
     * 获取 KEY对应的值
     *
     * @param key 查询的KEY
     * @param cls 返回的对象类型
     * @return
     */
    <T> T get(String key, Class<T> cls);

    /**
     * 删除 KEY -----此处可以删除 任意数据类型的KEY数据
     *
     * @param key 要删除的KEY
     */
    void evict(Object key);


    /**
     * get map
     *
     * @param key
     * @return
     */
    Map<String, String> getMap(String key);


    /**
     * set map value
     *
     * @param key
     * @param map
     * @return
     */
    int setMap(String key, Map<String, String> map);

    /**
     * 是否启用缓存
     *
     * @return
     */
    boolean enableCache();
    
    /**
     * 
     * @param key
     * @param field
     * @param increment
     * @return
     */
    public Long hincrBy(String key, String field, long increment);
    
    /**
     * 入队
     * @param key
     * @param value
     */
    void push(String key, String value);
    
    /**
     * 入队
     * @param key
     * @param value
     * @param time
     */
    void push(String key, String value, int time);
    
    /**
     * 出队
     * @param key
     * @return
     */
    String pop(String key);
    
    /**
     * 获取列表长度
     * @param key
     * @return
     */
    Long len(String key);
    
    /**
     * 计数器增加
     *
     * @param key
     * @return
     */
    Long increase(String key);
    
    /**
     * 设置过期时间
     *
     * @param key
     * @param time
     * @return
     */
    Long expire(String key, int time);
}

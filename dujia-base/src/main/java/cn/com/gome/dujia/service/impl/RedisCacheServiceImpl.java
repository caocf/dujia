package cn.com.gome.dujia.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.redis.RedisValueWrapper;
import cn.com.gome.dujia.service.RedisCacheService;
import cn.com.gome.ztghb.flasher.Command;
import cn.com.gome.ztghb.flasher.IRedis;
import cn.com.gome.ztghb.flasher.enums.CommandTypeEnum;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gome.plan.tools.utils.JsonUtils;

/**
 * redis 缓存服务
 *
 * @author xiongyan
 * @date 2016年5月7日 下午2:24:39
 */
@Service
public class RedisCacheServiceImpl implements RedisCacheService {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(RedisCacheServiceImpl.class);

    /**
     * redis cache
     */
    @Autowired
    private IRedis cacheService;

    /**
     * json util
     */
    private JsonUtils jsonUtils;

    /**
     * 构造器
     */
    public RedisCacheServiceImpl() {
        /**
         * jsonUtils初始化
         */
        jsonUtils = new JsonUtils();
        /**
         * 默认将type信息写入json
         */
        jsonUtils.objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
    }

    /**
     * 文章对象设置到Redis
     *
     * @param key
     * @param map
     * @return
     */
    public int setMap(String key, Map<String, String> map) {
        try {
            String res = cacheService.hmset(GlobalDisconf.cacheBusiness, key, map);
            if (null != res)
                return 1;
        } catch (Exception e) {
            logger.error("setMap:{}", e);
        }
        return 0;
    }


    public Map<String, String> getMap(String key) {
        return cacheService.hgetAll(GlobalDisconf.cacheBusiness, key);
    }

    /**
     * 设置普通值 ---此方法默认会设置一个失效期的
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        cacheService.set(GlobalDisconf.cacheBusiness, key, value);
        cacheService.expire(GlobalDisconf.cacheBusiness, key, 1000);
    }

    /**
     * 设置普通值 ---并设置过期时间
     *
     * @param key
     * @param value
     */
    public void set(String key, String value, int time) {
        cacheService.set(GlobalDisconf.cacheBusiness, key, value);
        cacheService.expire(GlobalDisconf.cacheBusiness, key, time);
    }


    @Override
    public void set(String key, Object value, int expire) {
        try {
            String strValue = jsonUtils.serialize(value);
            set(key, strValue, expire);
        } catch (Exception e) {
            logger.error("redis set key:{}, value:{}, expire:{}", key, value, expire, e);
        }

    }

    /**
     * 获取普通类型值
     *
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return cacheService.exists(GlobalDisconf.cacheBusiness, key);
    }

    /**
     * 获取普通类型值
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return cacheService.get(GlobalDisconf.cacheBusiness, key);
    }


    /**
     * 获取 KEY对应的值
     *
     * @param key 查询的KEY
     * @param cls 返回的对象类型
     * @return
     * @throws Exception 异常
     */
    public <T> T get(String key, Class<T> cls) {
        String json = get(key);
        return jsonUtils.deserialize(json, cls);
    }


    /**
     * 计数器增加
     *
     * @param key
     * @return
     */
    public Long increase(String key) {
        return cacheService.incr(GlobalDisconf.cacheBusiness, key);
    }

    /**
     * incrBy
     *
     * @param key
     * @param increment
     * @return
     */
    public Long incrBy(String key, long increment) {
        return cacheService.incrBy(GlobalDisconf.cacheBusiness, key, increment);
    }

    /**
     * incrBy
     *
     * @param key
     * @param increment
     * @param expireSencondTime
     * @return
     */
    public Long incrBy(String key, long increment, int expireSencondTime) {
        List<Command> commandList = new ArrayList<Command>();
        commandList.add(new Command(CommandTypeEnum.incrBy, key, increment));
        commandList.add(new Command(CommandTypeEnum.expire, key, expireSencondTime));
        List<Object> reList = cacheService.mutliExecute(GlobalDisconf.cacheBusiness, commandList);
        return (Long) reList.get(0);
    }

    /**
     * hincrBy
     *
     * @param key
     * @param field
     * @param increment
     * @return
     */
    public Long hincrBy(String key, String field, long increment) {
        return cacheService.hincrBy(GlobalDisconf.cacheBusiness, key, field, increment);
    }

    /**
     * hincrBy
     *
     * @param key
     * @param field
     * @param increment
     * @param expireSencondTime
     * @return
     */
    public Long hincrBy(String key, String field, long increment, int expireSencondTime) {
        List<Command> commandList = new ArrayList<Command>();
        commandList.add(new Command(CommandTypeEnum.hincrBy, key, field, increment));
        commandList.add(new Command(CommandTypeEnum.expire, key, expireSencondTime));
        List<Object> reList = cacheService.mutliExecute(GlobalDisconf.cacheBusiness, commandList);
        return (Long) reList.get(0);
    }

    /**
     * decrBy
     *
     * @param key
     * @param increment
     * @return
     */
    public Long decrBy(String key, long increment) {
        return cacheService.decrBy(GlobalDisconf.cacheBusiness, key, increment);
    }

    /**
     * hmset
     *
     * @param key
     * @param valMap
     * @return
     */
    public String hmset(String key, Map<String, String> valMap) {
        return cacheService.hmset(GlobalDisconf.cacheBusiness, key, valMap);
    }

    /**
     * hmset
     *
     * @param key
     * @param valMap
     * @param expireSencondTime
     * @return
     */
    public List<Object> hmset(String key, Map<String, String> valMap, int expireSencondTime) {
        List<Command> commandList = new ArrayList<Command>();
        commandList.add(new Command(CommandTypeEnum.hmset, key, valMap));
        commandList.add(new Command(CommandTypeEnum.expire, key, expireSencondTime));
        return cacheService.mutliExecute(GlobalDisconf.cacheBusiness, commandList);
    }

    /**
     * hset
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long hset(String key, String field, String value) {
        return cacheService.hset(GlobalDisconf.cacheBusiness, key, field, value);
    }

    /**
     * expire
     *
     * @param key
     * @param time
     * @return
     */
    public Long expire(String key, int time) {
        return cacheService.expire(GlobalDisconf.cacheBusiness, key, time);
    }

    /**
     * @param key
     * @return
     */
    public Boolean exist(String key) {
        return cacheService.exists(GlobalDisconf.cacheBusiness, key);
    }

    /**
     * 删除Key
     *
     * @param key
     */
    public void del(String key) {
        cacheService.del(GlobalDisconf.cacheBusiness, key);
    }


    /**
     * decr
     *
     * @param key
     */
    public void decr(String key) {
        cacheService.decr(GlobalDisconf.cacheBusiness, key);
    }

    /**
     * 入队
     *
     * @param key
     * @param value
     */
    public void push(String key, String value) {
        //插入到表头
        cacheService.lpush(GlobalDisconf.cacheBusiness, key, value);
    }

    /**
     * 入队
     *
     * @param key
     * @param value
     * @param time
     */
    public void push(String key, String value, int time) {
        //插入到表头
        cacheService.lpush(GlobalDisconf.cacheBusiness, key, value);
        //设置失效时间
        cacheService.expire(GlobalDisconf.cacheBusiness, key, time);
    }

    /**
     * 出队（先进先出）
     *
     * @param key
     * @return
     */
    public String pop(String key) {
        //移除并返回表尾的数据
        return cacheService.rpop(GlobalDisconf.cacheBusiness, key);
    }

    /**
     * 获取列表长度
     *
     * @param key
     * @return
     */
    public Long len(String key) {
        return cacheService.llen(GlobalDisconf.cacheBusiness, key);
    }

    /**
     * 是否启用缓存
     *
     * @return boolean 是否启用缓存
     */
    public boolean enableCache() {
        return GlobalDisconf.cacheEnable;
    }

    /**
     * 缓存过期时间，默认是5分钟
     * 自定义的属性
     */
    private int exp = 300;

    /**
     * 设置过期时间
     *
     * @param exp exp
     */
    public void setExp(int exp) {
        this.exp = exp;
    }

    /**
     * cache name
     */
    private String name;

    /**
     * 获取 cache name
     *
     * @return cache name
     */
    public String getName() {
        return name;
    }

    /**
     * spring 注入的name
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * 获取本地cache
     *
     * @return Object
     */
    public Object getNativeCache() {
        return cacheService;
    }

    /**
     * 获取对象
     *
     * @param key key
     * @return ValueWrapper
     */
    public ValueWrapper get(Object key) {
        if (enableCache() && null != key) {
            String _key = key.toString();
            String _value = get(_key);
            logger.debug("method={},key={},value={}", "get", _key, _value);
            Object obj = jsonUtils.deserialize(_value, Object.class);
            if (obj != null) {
                return new RedisValueWrapper(obj);
            }
        }
        return null;
    }

    /**
     * @param key   key
     * @param value value
     */
    public void put(Object key, Object value) {
        if (enableCache() && null != key && null != value) {
            String _key = key.toString();
            String _val = jsonUtils.serialize(value);
            logger.debug("method={},key={},value={}", "put", _key, _val);
            cacheService.set(GlobalDisconf.cacheBusiness, _key, _val);
            cacheService.expire(GlobalDisconf.cacheBusiness, _key, exp);
        }
    }

    /**
     * 删除缓存
     *
     * @param key key
     */
    public void evict(Object key) {
        if (enableCache()) {
            String _key = key == null ? "" : key.toString();
            logger.debug("method={},key={}", "evict", _key);
            cacheService.del(GlobalDisconf.cacheBusiness, _key);
        }
    }

    /**
     * 清空全部缓存
     */
    public void clear() {
        //分布式cache 禁止清空全部
    }

}

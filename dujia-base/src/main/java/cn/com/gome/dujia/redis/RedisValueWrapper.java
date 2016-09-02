package cn.com.gome.dujia.redis;

import org.springframework.cache.Cache;


public class RedisValueWrapper implements Cache.ValueWrapper {

    private Object val;

    public RedisValueWrapper() {
    }

    public RedisValueWrapper(Object val) {
        this.val = val;
    }

    @Override
    public Object get() {
        return val;
    }

    public void setVal(Object val) {
        this.val = val;
    }
}

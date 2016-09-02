package cn.com.gome.dujia.log;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gome.plan.tools.utils.JsonUtils;

/**
 * 记录api交互数据
 */
public class APILog {
    /**
     * 请求的参数
     */
    HashMap<Object, Object> mapReq = new LinkedHashMap<Object, Object>();
    /**
     * 响应的数据
     */
    HashMap<Object, Object> mapResp = new LinkedHashMap<Object, Object>();
    /**
     * 搜索用的关键字
     */
    String keyword;
    /**
     * 请求
     */
    Object req = null;
    /**
     * 响应
     */
    Object resp = null;
    /**
     * 日志
     */
    Logger logger = LoggerFactory.getLogger(APILog.class);
    /**
     * 日志类型
     */
    LogType type;

    /**
     * json serializer
     */
    static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON);

    /**
     * @param keyword 关键字
     * @param type
     */
    private APILog(String keyword, LogType type) {
        this.keyword = keyword;
        this.type = type;
    }

    /**
     * @param key
     * @param val
     * @return
     */
    public APILog appendRequest(Object key, Object val) {
        mapReq.put(key, val);
        return this;
    }

    /**
     * @param key
     * @param val
     * @return
     */
    public APILog appendResponse(Object key, Object val) {
        mapResp.put(key, val);
        return this;
    }

    /**
     * 设置request
     *
     * @param req
     */
    public APILog setRequest(Object req) {
        this.req = req;
        return this;
    }

    /**
     * 设置response
     *
     * @param resp
     * @return
     */
    public APILog setResponse(Object resp) {
        this.resp = resp;
        return this;
    }

    /**
     * slf4j记录日志
     */
    public void record() {

        if (req == null) {
            req = mapReq;
        }
        req = jsonUtils.serialize(req);
        if (resp == null) {
            resp = mapResp;
        }
        resp = jsonUtils.serialize(resp);
        logger.info("LOGTYPE={}\nKEY={}\nREQ=" + req + "\nRESP=" + resp + "\n", type.value, keyword);
    }

    /**
     * 创建一个apilog对象
     *
     * @param keyword
     * @param type
     * @return
     */
    public static APILog build(String keyword, LogType type) {
        return new APILog(keyword, type);
    }
}

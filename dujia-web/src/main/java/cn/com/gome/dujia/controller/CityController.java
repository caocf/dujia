package cn.com.gome.dujia.controller;

import cn.com.gome.dujia.dto.ZbyCityDto;
import cn.com.gome.dujia.model.ZbyCity;
import cn.com.gome.dujia.redis.CacheExpire;
import cn.com.gome.dujia.service.ZbyCityService;
import com.alibaba.fastjson.JSONObject;
import com.gome.ipquery.service.QueryIpInfoService;
import com.gome.plan.tools.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 城市定位
 * Created by zhaoxiang-ds on 2016/4/19.
 */
@Controller
@RequestMapping(produces = {"application/json;charset=UTF-8"})
public class CityController extends BaseController {

    public static final Logger logger = LoggerFactory.getLogger(CityController.class);

    @Autowired
    private ZbyCityService zbyCityService;

    @Autowired
    private QueryIpInfoService queryIpInfoService;


    @RequestMapping(value = "/city/locater", method = RequestMethod.POST)
    @ResponseBody
    public ZbyCityDto getCityByIP(HttpServletRequest request) {
        ZbyCityDto dto = new ZbyCityDto();
        try {
            String remoteHost = this.getRemoteHost(request);
            String cityCode = this.queryGomeCityCode(remoteHost);
            // 查询城市信息
            ZbyCity city = zbyCityService.queryCityByGomeCode(cityCode);
            if (StringUtils.isNotObjectEmpty(city)) {
                dto.setId(city.getCityId());
                dto.setName(city.getCityName());
                dto.setPinyin(city.getCityPinyin());
            }

        } catch (Exception e) {
            logger.error("根据用户请求定位城市失败", e);
        }

        return dto;
    }

    /**
     * 根据Ip转换成城市，缓存1天
     *
     * @param remoteHost
     * @return
     */
    @Cacheable(value = {CacheExpire.EXPIRE01D}, key = "#root.targetClass + #root.methodName + #remoteHost")
    private String queryGomeCityCode(String remoteHost) {
        String cityCode = null;
        logger.info("根据请求IP={},Dubbo方式调用gomeIp转换成城市", remoteHost);
        try {
            //Dubbo方式调用gomeIp转换成城市
            String responseStr = queryIpInfoService.queryIpInfojson(remoteHost);
            // 将接口返回对象转为JSON对象
            JSONObject jsonReuslt = JSONObject.parseObject(responseStr);
            cityCode = (String) jsonReuslt.get("cityCode");
        } catch (Exception e) {
            logger.error("根据请求IP={},Dubbo方式调用gomeIp转换成城市失败", remoteHost);
        }
        return cityCode;
    }

    /**
     * 获取调用方IP
     *
     * @param request
     * @return
     */
    private String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (validateIp(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (validateIp(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (validateIp(ip)) {
            ip = request.getRemoteAddr();
        }
        ip = ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
        if (ip.indexOf(",") != -1) {
            ip = ip.substring(0, ip.indexOf(","));
        }
        return ip;
    }

    /**
     * 验证ip是否获取到
     *
     * @param ip
     * @return
     */
    private boolean validateIp(String ip) {
        return ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip);
    }
}

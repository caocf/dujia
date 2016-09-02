package cn.com.gome.dujia.service.impl;

import cn.com.gome.dujia.enums.ADStates;
import cn.com.gome.dujia.enums.TrueFalseStatus;
import cn.com.gome.dujia.mapper.business.AdvertMapper;
import cn.com.gome.dujia.mapper.business.ZbyProductMapper;
import cn.com.gome.dujia.model.Advert;
import cn.com.gome.dujia.model.ZbyProduct;
import cn.com.gome.dujia.redis.CacheExpire;
import cn.com.gome.dujia.service.AdvertService;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


@Service
@Path("adt")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class AdvertServiceImpl implements AdvertService {

    private static final Logger logger = LoggerFactory.getLogger(AdvertServiceImpl.class);
    @Autowired
    private AdvertMapper advertMapper;

    @Autowired
    private ZbyProductMapper zbyProductMapper;

    @Override
    public List<Advert> getAdvertList(Advert advert) {
        advert.setDataType(0);
        return advertMapper.queryAdvertList(advert);
    }

    @Override
    public int insert(Advert record) {
        return advertMapper.insertAdvert(record);
    }

    @Override
    public Advert selectAdvertById(Integer id) {
        return advertMapper.selectAdvertById(id);
    }

    @Override
    public int updateAdvertById(Advert record) {
        return advertMapper.updateAdvertById(record);
    }

    @Override
    public int findCount(Advert record) {
        return advertMapper.findCount(record);
    }

    @Override
    public List<Advert> queryAdRecommendList() {
        return advertMapper.queryAdRecommendList();
    }

    @Override
    public void batchUpdate(List<Advert> adverts) {
        advertMapper.batchUpdate(adverts);
    }

    /**
     * 推荐列表
     */
    @Override
    public List<Advert> getRecommendList(Advert advert) {
        advert.setDataType(1);
        return advertMapper.queryAdvertList(advert);
    }

    /**
     * 获取有效期内的广告
     *
     * @return
     */
    public List<Advert> queryAdvertList4Web(Advert advert) {
        List<Advert> res = new ArrayList<>();
        try {
            res = advertMapper.queryRecommendListByCity(advert);
        } catch (Exception e) {
            logger.error("根据{}获取有效期内的广告失败", advert, e);
        }
        return res;
    }

    /**
     * 获取指定城市下，指定页面位置广告内容
     */
    @GET
    @Path("query/{plateform}/{pageModelType}")
    @Override
    @Cacheable(value = {CacheExpire.EXPIRE30M}, key = "#root.targetClass + '.'+ #root.methodName + '_' + #plateform + '_' + #pageModelType")
    public List<Advert> queryAdvertListByPlateform(@PathParam("plateform") Integer plateform, @PathParam("pageModelType") String pageModelType) {
        List<Advert> res = new ArrayList<>();
        try {
            Advert advert = new Advert();
            advert.setPlateform(plateform);
            advert.setModule(pageModelType);
            advert.setDataType(0);// 数据类型：0-广告,1-推荐
            advert.setStates(ADStates.DISPLAY.getValue()); //状态，0-待排期，1-展示中，3-已暂停，4-已过期
            // advert.setCityId(cityId); // 广告不区分城市
            res = this.queryAdvertList4Web(advert);
            if (null == res || res.size() == 0) {
                //城市未配置广告，获取默认广告
            }
        } catch (Exception e) {
            logger.error("获取城市={},页面{}广告内容失败{}", pageModelType, e);
        }
        return res;
    }

    /**
     * 通过产品id检索产品
     *
     * @param productId
     * @return
     */
    public ZbyProduct findProductById(String productId) {
        ZbyProduct zbyProduct = new ZbyProduct();
        zbyProduct.setProductId(productId);
        zbyProduct.setIsDelete(TrueFalseStatus.FALSE.getValue());
        return zbyProductMapper.selectOne(zbyProduct);
    }

    /**
     * 根据城市、平台、投放页面，获取推荐线路
     *
     * @return
     */
    @Cacheable(value = {CacheExpire.EXPIRE30M}, key = "#root.targetClass + '.'+ #root.methodName +'_' + #plateform +'_' + #cityId +'_' + #pageType")
    public List<Advert> queryRecommendList(Integer plateform, String cityId, String pageType) {
        return this.queryRecommendDatas(plateform, cityId, pageType);
    }



    public List<Advert> queryRecommendDatas(Integer plateform, String cityId, String pageType) {
        logger.info("根据plateform={},cityId={},pageType={}获取首页后台推荐线路", plateform, cityId, pageType);
        try {
            Advert advert = new Advert();
            advert.setPlateform(plateform);
            advert.setCityId(cityId);
            advert.setDataType(1);// 数据类型：0-广告,1-推荐
            advert.setStates(ADStates.DISPLAY.getValue()); //状态，0-待排期，1-展示中，3-已暂停，4-已过期
            advert.setPage(pageType);//页面类型
            return this.queryAdvertList4Web(advert);
        } catch (Exception e) {
            logger.error("根据plateform={},cityId={},pageType={}获取首页后台推荐线路失败", plateform, cityId, pageType, e);
        }
        return null;
    }
}

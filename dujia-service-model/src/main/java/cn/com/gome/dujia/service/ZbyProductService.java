package cn.com.gome.dujia.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import cn.com.gome.dujia.dto.QuickLinkRespDto;
import cn.com.gome.dujia.dto.ZbyProductDetailRespDto;
import cn.com.gome.dujia.dto.ZbyProductResourceDto;
import cn.com.gome.dujia.dto.ZbyProductRespDto;
import cn.com.gome.dujia.model.ZbyProduct;
import cn.com.gome.dujia.vo.search.Product;

/**
 * 周边游线路对外接口
 * Created by zhaoxiang-ds on 2016/4/5.
 */
public interface ZbyProductService {

    /**
     * 获取国美旅行首页数据
     *
     * @param code
     * @return
     */
    public ZbyProductRespDto getGomeTravelIndexRes(Integer code);

    /**
     * 获取首页线路列表
     *
     * @param plateform 平台
     * @param cityId
     * @return
     */
    public List<ZbyProductRespDto> queryProductRes(Integer plateform, String cityId);


    /**
     * 根据id 获取线路详情
     *
     * @param productId 线路id
     * @return
     */
    public ZbyProductDetailRespDto getProductDetailById(String productId);

    /**
     * 获取首页快速连接区信息并缓存
     *
     * @param platform
     * @param cityId
     * @return
     */
    public QuickLinkRespDto getQuickLink(Integer platform, Integer cityId);


    /**
     * 获取首页快速连接区数据
     *
     * @param platform
     * @param cityId
     * @return
     */
    public QuickLinkRespDto getQuickLinkData(Integer platform, String cityId);

    /**
     * 行程天数格式化
     *
     * @param days
     * @return
     */
    public String travlDaysFormat(Integer days);




    /**
     * 获取列表页推荐线路
     *
     * @param plateform
     * @param cityId
     * @return
     */
    public List<Product> queryRecommendProduct4List(Integer plateform, String cityId);

    /**
     * 根据指定主键获取一条数据库记录,zby_product
     *
     * @param productId
     */
    ZbyProduct selectByPrimaryKey(String productId);

    /**
     * 查询线路销量，浏览量
     *
     * @param product
     * @return
     */
    public List<ZbyProduct> querySaleBrowseCount(ZbyProduct product);

    /**
     * 获取城市下销量、浏览量最高的前10个景区列表
     *
     * @param cityId
     * @return
     */
    public List<ZbyProductResourceDto> getSceneriesBySealViewCount(Integer cityId);


    /**
     * 根据线路id，套餐id 获取线路、套餐信息
     *
     * @param productId 线路id
     * @return
     */
    public ZbyProductDetailRespDto getProductAndPackageByIds(String productId, String packageId);

    /**
     * 获取线路的游玩天数
     *
     * @param productId
     * @return
     */
    Integer getTravlDaysByProductId(String productId);

    /**
     * 根据线路ID，获取线路主题列表
     *
     * @param productId
     * @return
     */
    public List<String> queryRecoms(String productId);

    /**
     * 获取所有线路含有的游玩天数列表
     *
     * @return
     */
    List<Integer> queryAllTravelDays();
    
    /**
     * 获取线路的最低价格
     * @return
     */
    public Map<String, BigDecimal> queryProductPrice();

}

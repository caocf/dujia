package cn.com.gome.dujia.controller;

import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.dto.ZbyPackageRespDto;
import cn.com.gome.dujia.dto.ZbyProductDetailRespDto;
import cn.com.gome.dujia.dto.ZbyProductResourceDto;
import cn.com.gome.dujia.enums.PlatformType;
import cn.com.gome.dujia.enums.WebSortType;
import cn.com.gome.dujia.model.ZbyCity;
import cn.com.gome.dujia.service.SearchService;
import cn.com.gome.dujia.service.ZbyCityService;
import cn.com.gome.dujia.service.ZbyProductPackageService;
import cn.com.gome.dujia.service.ZbyProductService;
import cn.com.gome.dujia.vo.search.FilterItem;
import cn.com.gome.dujia.vo.search.Product;
import cn.com.gome.dujia.vo.search.QueryRequest;
import cn.com.gome.dujia.vo.search.QueryResponse;
import com.gome.plan.tools.utils.PinyinUtils;
import com.gome.plan.tools.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 线路首页、详情页
 * Created by zhaoxiang-ds on 2016/4/19.
 */
@Controller
@RequestMapping(produces = {"application/json;charset=UTF-8"})
public class ProductController extends BaseController {

    public static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ZbyProductService zbyProductService;

    @Autowired
    private ZbyProductPackageService zbyProductPackageService;

    @Autowired
    private SearchService searchService;

    @Autowired
    private ZbyCityService cityService;
    /**
     * 每页行数
     */
    private static Integer PAGE_SIZE = 10;

    // 搜索选中参数-行程天数
    private static String S_DAYS = "s_days";
    // 搜索选中参数-主题类别
    private static String S_THEME = "s_theme";
    // 搜索选中参数-城市
    private static String S_CITY = "s_city";
    // 搜索选中参数-城市ID
    private static String S_CITY_ID = "s_city_id";
    // 搜索选中参数-城市名称
    private static String S_CITY_NAME = "s_city_name";
    // 搜索选中参数-酒店等级
    private static String S_HOTELGRADE = "s_hotelGrade";
    // 搜索选中参数- 景点
    private static String S_SCENERY = "s_scenery";
    // 搜索选中参数- 页码
    private static String S_P = "s_p";
    // 搜索选中参数- 排序
    private static String S_M = "s_m";
    // 搜索选中参数- 关键字
    private static String S_K = "s_k";
    // 搜索选中参数- 最大价格
    private static String S_J_MAX = "s_j_max";
    // 搜索选中参数- 最小价格
    private static String S_J_MIN = "s_j_min";
    // 搜索选中参数- 每页显示行数
    private static String PRE_PAGE = "perPage";
    // 搜索选中参数- 总页数
    private static String TOTAL_PAGE = "totalPage";


    /**
     * 线路详情
     */
    @RequestMapping(value = "/{productId}", method = {RequestMethod.GET})
    public String getProductDetail(@PathVariable String productId, ModelMap model) {
        try {
            // 线路详情
        	ZbyProductDetailRespDto product = zbyProductService.getProductDetailById(productId);
            if (product != null) {
            	model.put("productDetail", product);
            	return "detail";
            }
        } catch (Exception e) {
            logger.error("根据id={}获取线路详情出错", productId, e);
        }
        return GlobalDisconf.errorPage;
    }

    /**
     * 获取线路套餐列表,参数d=售卖日期，格式yyyyMMdd
     *
     * @param d
     * @return
     */
    @RequestMapping(value = "/pks/{productId}", method = RequestMethod.GET)
    @ResponseBody
    public ZbyPackageRespDto getPackageList(@PathVariable String productId) {
        try {
            return zbyProductPackageService.getProductPackageList(productId);
        } catch (Exception e) {
            logger.error("根据id={}获取线路套餐列表失败", productId, e);
        }
        return null;
    }


    /**
     * 首页浮层跳转列表页
     *
     * @param cityPy 当前城市（拼音全拼）
     * @param d      游玩天数(数字)
     * @param t      主题（拼音简拼）
     * @param s      景点（景点资源id）
     * @param c      周边城市ID
     * @param map
     * @return
     */
    @RequestMapping(value = "/{cityPy}/list.html", method = {RequestMethod.GET})
    public String searchProductListPage(@PathVariable String cityPy,
                                        @RequestParam(value = "d", required = false) Integer d,
                                        @RequestParam(value = "t", required = false) String t,
                                        @RequestParam(value = "s", required = false) String s,
                                        @RequestParam(value = "c", required = false) String c,
                                        ModelMap map) {
        try {
            QueryRequest queryRequest = new QueryRequest();
            queryRequest.setDays(d);//游玩天数(数字)
            queryRequest.setTargetCityId(c);//周边城市ID
            queryRequest.setTitle(t);//主题（拼音简拼）
            queryRequest.setScenic(s);//景点（景点资源id）
            // 当前城市（拼音全拼）转 城市ID
            ZbyCity city = cityService.cityPY2CityId(cityPy);
            if (null == city) {
                logger.info("根据拼音{}没有找到对应的城市", cityPy);
                return GlobalDisconf.errorPage;
            }
            String cityId = city.getCityId().toString();
            queryRequest.setCityId(cityId); //当前城市（拼音全拼）
            queryRequest.setCityPy(cityPy);
            map.put(S_CITY_NAME, city.getCityName());

            // 获取推荐线路
            List<Product> recommendList = zbyProductService.queryRecommendProduct4List(PlatformType.WEB.getValue(), cityId);
            // 获取推荐内容的id
            List<String> filterIds = this.getFilterIds(recommendList);
            queryRequest.setProductIds(filterIds);

            // 计算请求数量
            int requestSize = PAGE_SIZE - filterIds.size();
            queryRequest.setPageSize(requestSize);

            // 搜索
            QueryResponse response = searchService.searchList(queryRequest);
            // 添加推荐
            if (recommendList != null && recommendList.size() > 0) {
                response.getProducts().addAll(0, recommendList);
            }
            // 选中处理
            map.put(S_DAYS, this.searchItem(response.getDays(), d));
            map.put(S_CITY, this.searchItem(response.getCitys(), c));
            map.put(S_CITY_ID, cityId);
            map.put(S_THEME, this.searchItem(response.getTitles(), t));
            map.put(S_SCENERY, this.searchItem(response.getScenics(), s));
            map.put(S_P, 1);
            map.put(PRE_PAGE, PAGE_SIZE);
            int totalPage = (int) Math.ceil((double) response.getCount() / PAGE_SIZE);
            map.put(TOTAL_PAGE, totalPage);
            map.put("search", response);
            return "list";
        } catch (Exception e) {
            logger.error("列表页异常", e);
        }
        return GlobalDisconf.errorPage;
    }

    /**
     * 搜索选中项处理
     *
     * @param items
     * @param key
     * @return
     */
    private String searchItem(List<FilterItem> items, Object key) {
        if (null != key && null != items) {
            for (FilterItem filterItem : items) {
                if (key.toString().equals(filterItem.getValue())) {
                    return filterItem.getName();
                }
            }
        }
        return null;
    }

    //获取推荐内容的id
    private List<String> getFilterIds(List<Product> recommendList) {
        List<String> filterIds = new ArrayList<String>();
        if (recommendList != null && recommendList.size() > 0) {
            for (Product product : recommendList) {
                filterIds.add(product.getProductId());
            }
        }
        return filterIds;
    }


    /**
     * 列表页
     *
     * @param cityPy 当前城市
     * @param sign   参数标记，格式：主题-行程天数-目的地城市-景点-酒店等级 [txx-d2-cxxx-s444-hvv]
     * @param p      页码
     * @param k      关键字
     * @param m      排序： m=0默认(可不传)、m=1销量,m2=价格高到低、m3=价格低到高
     * @param j      价格区间：价格1_价格2
     * @param map
     * @return
     */
    @RequestMapping(value = "/{cityPy}/{sign}", method = {RequestMethod.GET})
    public String searchProductListPage(@PathVariable String cityPy,
                                        @PathVariable String sign,
                                        @RequestParam(value = "p", required = false) Integer p,
                                        @RequestParam(value = "k", required = false) String k,
                                        @RequestParam(value = "m", required = false) Integer m,
                                        @RequestParam(value = "j", required = false) String j,
                                        ModelMap map) {
    	try {
    		ModelMap tmp = this.searchByParams(cityPy, sign, p, k, m, j, true);
            if (null != tmp) {
            	map.addAllAttributes(tmp);
            	return "list";
            }
		} catch (Exception e) {
			logger.error("列表页异常", e);
		}
    	return GlobalDisconf.errorPage;
    }


    /**
     * 首页搜索框  -  跳转
     *
     * @param k 查询关键字
     * @return
     */
    @RequestMapping(value = "/search.html", method = {RequestMethod.GET})
    public String indexSearch(@RequestParam(value = "k", required = true) String k,
                              @RequestParam(value = "p", required = false) String p,
                              ModelMap map) {
        try {
            QueryRequest queryRequest = new QueryRequest();

            if (StringUtils.isNotEmpty(k)) {// 查询关键字
                map.put(S_K, k);
                queryRequest.setKeyword(k);
            }
            if (StringUtils.isNotEmpty(p)) {
                try {
                    map.put(S_P, p);
                    queryRequest.setPageIndex(Integer.parseInt(p));//页码
                } catch (Exception e) {
                    logger.error("页码({})转换失败", p, e);
                }
            } else {
                map.put(S_P, 1);
            }
            // 搜索
            QueryResponse response = searchService.searchList(queryRequest);

            map.put(PRE_PAGE, PAGE_SIZE);
            int totalPage = (int) Math.ceil((double) response.getCount() / PAGE_SIZE);
            map.put(TOTAL_PAGE, totalPage);
            map.put("search", response);
            return "search";
        } catch (Exception e) {
            logger.error("首页搜索框出错", e);
        }
        return GlobalDisconf.errorPage;
    }

    /**
     * 搜索页
     *
     * @param sign 参数标记，格式：主题-行程天数-目的地城市-景点-酒店等级 [txx-d2-cxxx-s444-hvv]
     * @param p    页码
     * @param k    关键字
     * @param m    排序： m=0默认(可不传)、m=1销量,m2=价格高到低、m3=价格低到高
     * @param j    价格区间：价格1_价格2
     * @param map
     * @return
     */
    @RequestMapping(value = "/search/{sign}", method = {RequestMethod.GET})
    public String searchProductPage(@PathVariable String sign,
                                    @RequestParam(value = "p", required = false) Integer p,
                                    @RequestParam(value = "k", required = false) String k,
                                    @RequestParam(value = "m", required = false) Integer m,
                                    @RequestParam(value = "j", required = false) String j,
                                    ModelMap map) {
    	try {
    		ModelMap tmp = this.searchByParams(null, sign, p, k, m, j, false);
    		if (tmp != null) {
    			map.addAllAttributes(tmp);
    			return "search";
    		}
	    } catch (Exception e) {
			logger.error("搜索页异常", e);
		}
		return GlobalDisconf.errorPage;
    }


    /**
     * 根据参数查询数据
     *
     * @param cityPy
     * @param sign
     * @param p
     * @param k
     * @param m
     * @param j
     * @param isList 是否列表查询
     * @return
     */
    private ModelMap searchByParams(String cityPy, String sign, Integer p, String k, Integer m, String j, boolean isList) {
        ModelMap map = new ModelMap();
        try {
            // 请求参数转换
            QueryRequest queryRequest = new QueryRequest();
            Map<String, String> signMap = this.getQueryRequest(sign);
            if (signMap.size() == 0) {
                return null;
            }
            for (String sk : signMap.keySet()) {
                switch (sk.toCharArray()[0]) {
                    case 't': //主题
                        queryRequest.setTitle(signMap.get(sk));//主题（拼音简拼）
                        break;
                    case 'd'://行程天数
                        if (StringUtils.isNotEmpty(signMap.get(sk))) {
                            queryRequest.setDays(Integer.parseInt(signMap.get(sk)));//游玩天数(数字)
                        }
                        break;
                    case 'c'://目的地城市
                        queryRequest.setTargetCityId(signMap.get(sk));//周边城市ID
                        break;
                    case 's'://景点
                        queryRequest.setScenic(signMap.get(sk));//景点（景点资源id）
                        break;
                    case 'h'://酒店等级
                        queryRequest.setHotelGrade(signMap.get(sk));//酒店等级
                        break;
                }
            }
            String cityId = null;
            if (StringUtils.isNotEmpty(cityPy)) {
                queryRequest.setCityPy(cityPy); // 当前城市py
                // 根据 cityPy转cityId
                ZbyCity city = cityService.cityPY2CityId(cityPy);
                if (null == city) {
                    logger.info("根据拼音{}没有找到对应的城市", cityPy);
                    return null;
                }
                cityId = city.getCityId().toString();
                queryRequest.setCityId(cityId);
                map.put(S_CITY_ID, cityId);
                map.put(S_CITY_NAME, city.getCityName());
            }
            if (StringUtils.isNotEmpty(k)) {// 查询关键字
                map.put(S_K, k);
                queryRequest.setKeyword(k);
            }

            if (p != null) {
                try {
                    map.put(S_P, p);
                    queryRequest.setPageIndex(p);//页码
                } catch (Exception e) {
                    logger.error("页码({})转换失败", p, e);
                }
            } else {
                map.put(S_P, 1);
            }
            if (m != null) {//排序
                try {
                    map.put(S_M, m);
                    WebSortType sortType = WebSortType.getSortNameByKey(m);
                    if (sortType != null) {
                        queryRequest.setSortField(sortType.getSortField());
                        queryRequest.setSortOrder(sortType.getSortOrder());
                    }
                } catch (Exception e) {
                    logger.error("搜索:排序({})转换失败", m, e);
                }
            }
            if (StringUtils.isNotEmpty(j)) {//价格区间
                try {
                    String[] jg = j.split("_");
                    switch (jg.length) {
                        case 1:
                            int jg1 = Integer.parseInt(jg[0]) >= 0 ? Integer.parseInt(jg[0]) : 0;
                            map.put(S_J_MIN, jg1);
                            queryRequest.setMinPrice(jg1);
                            break;
                        case 2:
                            int jg21 = Integer.parseInt(jg[0]) >= 0 ? Integer.parseInt(jg[0]) : 0;
                            map.put(S_J_MIN, jg21);
                            queryRequest.setMinPrice(Integer.parseInt(jg[0]));
                            // 如果价格2 < 0 ,则不赋值
                            if (Integer.parseInt(jg[1]) >= 0) {
                                map.put(S_J_MAX, jg[1]);
                                queryRequest.setMaxPrice(Integer.parseInt(jg[1]));
                            }
                            break;
                    }
                } catch (Exception e) {
                    logger.error("搜索:价格区间({})转换失败", j, e);
                }
            }

            // 如果是列表页，则查询推荐数据
            List<Product> recommendList = null;
            if (isList && StringUtils.isNotEmpty(cityId)) {
                // 获取推荐线路
                recommendList = zbyProductService.queryRecommendProduct4List(PlatformType.WEB.getValue(), cityId);
                // 排重内容的id
                List<String> filterIds = this.getFilterIds(recommendList);
                queryRequest.setProductIds(filterIds);

                // 计算请求数量
                int requestSize = PAGE_SIZE - filterIds.size();
                queryRequest.setPageSize(requestSize);
            }
            // 搜索
            QueryResponse response = searchService.searchList(queryRequest);
            // 添加推荐
            if (null != recommendList && recommendList.size() > 0) {
                response.getProducts().addAll(0, recommendList);
            }
            // 选中查询条件处理，web展示
            for (String sk : signMap.keySet()) {
                switch (sk.toCharArray()[0]) {
                    case 't': //主题（拼音简拼）
                        map.put(S_THEME, this.searchItem(response.getTitles(), signMap.get(sk)));
                        break;
                    case 'd'://游玩天数(数字)
                        map.put(S_DAYS, this.searchItem(response.getDays(), signMap.get(sk)));
                        break;
                    case 'c'://目的地城市
                        map.put(S_CITY, this.searchItem(response.getCitys(), signMap.get(sk)));
                        break;
                    case 's'://景点（景点资源id）
                        map.put(S_SCENERY, this.searchItem(response.getScenics(), signMap.get(sk)));
                        break;
                    case 'h'://酒店等级
                        map.put(S_HOTELGRADE, this.searchItem(response.getHotelGrades(), signMap.get(sk)));
                        break;
                }
            }
            map.put(PRE_PAGE, PAGE_SIZE);
            int totalPage = (int) Math.ceil((double) response.getCount() / PAGE_SIZE);
            map.put(TOTAL_PAGE, totalPage);
            map.put("search", response);
        } catch (Exception e) {
            logger.error("根据参数city={},sign={}搜索出错", cityPy, sign, e);
            return null;
        }
        return map;
    }


    // 搜索参数格式化，格式：主题-行程天数-目的地城市-景点-酒店等级-页码 [t?-d?-c?-s?-h?]
    private Map<String, String> getQueryRequest(String sign) {
        Map<String, String> map = new HashMap<>();
        String[] signArr = sign.split("-");
        for (String s : signArr) {
            char[] chs = s.toCharArray();
            switch (chs[0]) {
                case 't': //主题
                    map.put("t", s.substring(1));
                    break;
                case 'd'://行程天数
                    map.put("d", s.substring(1));
                    break;
                case 'c'://目的地城市
                    map.put("c", s.substring(1));
                    break;
                case 's'://景点
                    map.put("s", s.substring(1));
                    break;
                case 'h'://酒店等级
                    map.put("h", s.substring(1));
                    break;
            }
        }
        return map;
    }

    /**
     * 通过搜索查询用户浏览历史记录
     *
     * @param productIds
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/history", method = {RequestMethod.GET})
    public List<Product> searchProductListPage(@RequestParam(value = "productIds", required = true) String productIds) {
        List<Product> res = new ArrayList<>();
        List<Product> list = new ArrayList<>();
        if (StringUtils.isNotEmpty(productIds)) {
            List<String> ids = Arrays.asList(productIds.split(","));
            // 通过搜索查询线路数据
            list = searchService.searchProducts(ids);
            if (list != null) {
                //  按浏览顺序排序
                for (String id : ids) {
                    for (Product p : list) {
                        if (p.getProductId().equals(id)) {
                            res.add(p);
                        }
                    }
                }
            }
        }
        return res;
    }

    /**
     * 详情页，你可能还喜欢
     *
     * @param productId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/enjoy", method = {RequestMethod.GET})
    public List<Product> queryEnjoyProduct(@RequestParam(value = "productId", required = true) Integer productId) {
        List<Product> list = new ArrayList<>();
        if(productId==null){
            return list;
        }
        try {
            if (StringUtils.isNotEmpty(productId.toString())) {
                List<String> themes = zbyProductService.queryRecoms(productId.toString());
                List<String> themePy = new ArrayList<>();
                if (null != themes && themes.size() > 0) {
                    for (String t : themes) {
                        themePy.add(PinyinUtils.toPinyin(t, true, false));
                    }
                }
                // 请求参数转换
                QueryRequest queryRequest = new QueryRequest();
                if (themePy.size() > 0) {
                    queryRequest.setTitles(themePy);//主题（拼音简拼）
                }
                queryRequest.setPageSize(5);// 请求个数
                // 通过搜索查询线路数据
                QueryResponse response = searchService.searchList(queryRequest);
                if (response.getProducts().size() > 0) {
                    list.addAll(response.getProducts());
                }
            }
        } catch (Exception e) {
            logger.error("根据productid={}获取详情页'你可能还喜欢'失败", productId, e);
        }
        return list;
    }


    /**
     * 获取城市下销量、浏览量最高的前10个景区列表
     * (列表页：看看大家去哪玩)
     *
     * @param cityId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/hotscenery", method = {RequestMethod.GET})
    public List<ZbyProductResourceDto> getSceneriesBySealViewCount(@RequestParam(value = "cityId", required = false) Integer cityId) {
        return zbyProductService.getSceneriesBySealViewCount(cityId);
    }
}

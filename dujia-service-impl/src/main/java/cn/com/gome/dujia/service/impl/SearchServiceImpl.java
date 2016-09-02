package cn.com.gome.dujia.service.impl;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.elasticsearch.action.search.MultiSearchRequestBuilder;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.nested.Nested;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.gome.dujia.disconf.Advert;
import cn.com.gome.dujia.disconf.CommonDisconf;
import cn.com.gome.dujia.disconf.RecommendDisconf;
import cn.com.gome.dujia.enums.PlatformType;
import cn.com.gome.dujia.enums.ResourceType;
import cn.com.gome.dujia.enums.TcImageType;
import cn.com.gome.dujia.enums.TrueFalseStatus;
import cn.com.gome.dujia.es.ElasticSearchHandler;
import cn.com.gome.dujia.mapper.business.ZbyProductMapper;
import cn.com.gome.dujia.mapper.business.ZbyProductPackageDetailMapper;
import cn.com.gome.dujia.mapper.business.ZbyRecomInfoMapper;
import cn.com.gome.dujia.model.AroundCity;
import cn.com.gome.dujia.model.ZbyProduct;
import cn.com.gome.dujia.model.ZbyProductPackageDetail;
import cn.com.gome.dujia.model.ZbyRecomInfo;
import cn.com.gome.dujia.service.SearchService;
import cn.com.gome.dujia.service.ZbyCityService;
import cn.com.gome.dujia.service.ZbyProductService;
import cn.com.gome.dujia.vo.search.AutoResponse;
import cn.com.gome.dujia.vo.search.FilterItem;
import cn.com.gome.dujia.vo.search.Product;
import cn.com.gome.dujia.vo.search.QueryRequest;
import cn.com.gome.dujia.vo.search.QueryResponse;
import cn.com.gome.dujia.vo.search.Res;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.gome.plan.tools.utils.JsonUtils;
import com.gome.plan.tools.utils.PinyinUtils;
import com.gome.plan.tools.utils.StringUtils;

/**
 * 搜索服务
 * 
 * @author xiongyan
 * @date 2016年4月15日 下午12:49:15
 */
@Service
@Path("search")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class SearchServiceImpl implements SearchService {
	
	private Logger logger = LoggerFactory.getLogger(ElasticSearchHandler.class);
	
	private static JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON);
	
	@Autowired
	private ElasticSearchHandler elasticSearchHandler;
	
	@Autowired
	private ZbyProductMapper zbyProductMapper;
	
	@Autowired
	private ZbyRecomInfoMapper zbyRecomInfoMapper;
	
	@Autowired
	private ZbyProductPackageDetailMapper zbyProductPackageDetailMapper;
	
	@Autowired
	private ZbyProductService zbyProductService;
	
	@Autowired
	private ZbyCityService zbyCityService;

	
	/**
	 * 设置索引字段
	 */
	public void indexMapping() {
		try {
			XContentBuilder mapping = XContentFactory.jsonBuilder()
				.startObject()
				.startObject(CommonDisconf.zbyType)
				.startObject("properties")
					.startObject("productId").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
					
					.startObject("mainName").field("type", "string").field("store", "yes").field("index", "analyzed").field("indexAnalyzer", "lowercase_keyword").field("index_options", "offsets").field("searchAnalyzer", "ik_smart").endObject()
					
					.startObject("mainNamePy").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
					
					.startObject("subName").field("type", "string").field("store", "yes").field("index", "analyzed").field("indexAnalyzer", "lowercase_keyword").field("index_options", "offsets").field("searchAnalyzer", "ik_smart").endObject()
					
					.startObject("subNamePy").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
					
					.startObject("appTitle").field("type", "string").field("store", "yes").field("index", "analyzed").field("indexAnalyzer", "lowercase_keyword").field("index_options", "offsets").field("searchAnalyzer", "ik_smart").endObject()
					
					.startObject("appTitlePy").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
					
					.startObject("days").field("type", "integer").field("store", "yes").field("index", "not_analyzed").endObject()
					
					.startObject("provinceName").field("type", "string").field("store", "yes").field("index", "analyzed").field("indexAnalyzer", "lowercase_keyword").field("index_options", "offsets").field("searchAnalyzer", "ik_smart").endObject()
					
					.startObject("provincePy").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
					
					.startObject("cityId").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
					
					.startObject("cityName").field("type", "string").field("store", "yes").field("index", "analyzed").field("indexAnalyzer", "lowercase_keyword").field("index_options", "offsets").field("searchAnalyzer", "ik_smart").endObject()
					
					.startObject("cityPy").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
					
					.startObject("cityIdName").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
					
					.startObject("beginDate").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
					
					.startObject("overDate").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
					
					.startObject("imageUrl").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
					
					.startObject("imageUrlLocal").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
					
					.startObject("productMinPrice").field("type", "double").field("store", "yes").field("index", "not_analyzed").endObject()
					
					.startObject("productMaxPrice").field("type", "double").field("store", "yes").field("index", "not_analyzed").endObject()
					
					.startObject("sellUnit").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
					
					.startObject("saleCount").field("type", "integer").field("store", "yes").field("index", "not_analyzed").endObject()
					
					.startObject("browseCount").field("type", "integer").field("store", "yes").field("index", "not_analyzed").endObject()
					
					.startObject("isDelete").field("type", "boolean").field("store", "yes").field("index", "not_analyzed").endObject()
					
					.startObject("titles").field("type", "nested").field("store", "yes").field("index", "not_analyzed")
						.startObject("properties")
							.startObject("titleName").field("type", "string").field("store", "yes").field("index", "analyzed").field("indexAnalyzer", "lowercase_keyword").field("index_options", "offsets").field("searchAnalyzer", "ik_smart").endObject()
							.startObject("titlePy").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
							.startObject("titlePyName").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
						.endObject()
					.endObject()
					
					.startObject("scenics").field("type", "nested").field("store", "yes").field("index", "not_analyzed")
						.startObject("properties")
							.startObject("resName").field("type", "string").field("store", "yes").field("index", "analyzed").field("indexAnalyzer", "lowercase_keyword").field("index_options", "offsets").field("searchAnalyzer", "ik_smart").endObject()
							.startObject("resId").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
							.startObject("resPy").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
							.startObject("resGrade").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
							.startObject("resInfo").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
						.endObject()
					.endObject()
					
					.startObject("hotels").field("type", "nested").field("store", "yes").field("index", "not_analyzed")
						.startObject("properties")
							.startObject("resName").field("type", "string").field("store", "yes").field("index", "analyzed").field("indexAnalyzer", "lowercase_keyword").field("index_options", "offsets").field("searchAnalyzer", "ik_smart").endObject()
							.startObject("resId").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
							.startObject("resPy").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
							.startObject("resGrade").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
							.startObject("resInfo").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
						.endObject()
					.endObject()
					
				.endObject()
				.endObject()
				.endObject();
			
			// 根据别名获取下一个索引名， 如果返回null，则使用默认索引名称
			String index = elasticSearchHandler.getNextIndex(CommonDisconf.zbyAlias, CommonDisconf.zbyIndex);
			// 设置索引字段
			elasticSearchHandler.putMapping(index, CommonDisconf.zbyType, mapping);
			logger.info("设置索引字段成功");
		} catch (Exception e) {
			logger.error("设置索引字段失败", e);
		}
	}

	/**
	 * 导入全量数据
	 */
	public void importFullData() {
		try {
			// 查询线路
			List<ZbyProduct> products = zbyProductMapper.queryProductByPIds(null);
			if (CollectionUtils.isNotEmpty(products)) {
				// 批量处理数据
				Map<String, Map<String, Object>> sourceMap = new HashMap<String, Map<String, Object>>();
				
				// 查询所有线路的主题分类
				List<ZbyRecomInfo> recomInfos = zbyRecomInfoMapper.queryRecomInfoByPIds(null); 
				
				// 查询所有线路的资源信息
				List<ZbyProductPackageDetail> resInfos = zbyProductPackageDetailMapper.queryResInfoByPIds(null);
				
				// 查询省市信息
				Map<String, String> provinceCityMap = zbyCityService.getProvinceCityMap();
				
				// 线路最低价格
				Map<String, BigDecimal> productMinPriceMap = zbyProductService.queryProductPrice();
				
				// 导数据
				for (ZbyProduct product : products) {
					Map<String, Object> source = new HashMap<String, Object>();
					source.put("productId", product.getProductId()); //线路id
					source.put("mainName", product.getMainName()); //线路主标题
					source.put("mainNamePy", PinyinUtils.toPinyin(product.getMainName(), true, true) + PinyinUtils.toPinyin(product.getMainName(), false, true)); //线路主标题拼音
					source.put("subName", product.getSubName()); //线路副标题
					source.put("subNamePy", PinyinUtils.toPinyin(product.getSubName(), true, true) + PinyinUtils.toPinyin(product.getSubName(), false, true)); //线路副标题拼音
					source.put("appTitle", product.getAppTitle()); //线路app标题
					source.put("appTitlePy", PinyinUtils.toPinyin(product.getAppTitle(), true, true) + PinyinUtils.toPinyin(product.getAppTitle(), false, true)); //线路app标题拼音
					source.put("days", product.getDays()); //线程天数
					String province = provinceCityMap.get(product.getCityId());
					source.put("provinceName", province); //省名称
					source.put("provincePy", PinyinUtils.toPinyin(province, true, true) + PinyinUtils.toPinyin(province, false, true)); //省拼音
					source.put("cityId", product.getCityId()); //城市id
					source.put("cityName", product.getCityName()); //城市名称
					source.put("cityPy", PinyinUtils.toPinyin(product.getCityName(), true, true) + PinyinUtils.toPinyin(product.getCityName(), false, true)); //城市拼音
					source.put("cityIdName", product.getCityId()+"-"+product.getCityName()); //城市id-名称
					source.put("beginDate", product.getBeginDate()); //开始时间
					source.put("overDate", product.getOverDate()); //结束时间
					source.put("imageUrl", product.getImageUrl()); //图片地址
					source.put("imageUrlLocal", product.getImageUrlLocal()); //国美图片地址
					BigDecimal minPrice = productMinPriceMap.get(product.getProductId());
					source.put("productMinPrice", null != minPrice ? minPrice : product.getProductMinPrice()); //最小价格
					source.put("productMaxPrice", product.getProductMaxPrice()); //最大价格
					source.put("sellUnit", product.getSellUnit()); //单位
					source.put("saleCount", null == product.getSaleCount() ? 0 : product.getSaleCount()); //销量
					source.put("browseCount", null == product.getBrowseCount() ? 0 : product.getBrowseCount()); //浏览量
					source.put("isDelete", product.getIsDelete()); //是否删除
					
					// 主题分类
					if (null != recomInfos) {
						List<Map<String, Object>> titleList = new ArrayList<Map<String, Object>>();
						for(ZbyRecomInfo recomInfo : recomInfos) {
							if(product.getProductId().equals(recomInfo.getProductId())) {
								Map<String, Object> recomInfoMap = new HashMap<String, Object>();
								recomInfoMap.put("titleName", recomInfo.getTitle()); //主题名称
								recomInfoMap.put("titlePy", PinyinUtils.toPinyin(recomInfo.getTitle(), true, true) + PinyinUtils.toPinyin(recomInfo.getTitle(), false, true)); //主题拼音
								recomInfoMap.put("titlePyName", PinyinUtils.toPinyin(recomInfo.getTitle(), true, true)+"-"+recomInfo.getTitle()); //主题拼音简拼-名称
								titleList.add(recomInfoMap);
							}
						}
						source.put("titles", titleList);
					}
					
					// 资源信息
					if (null != resInfos) {
						List<Map<String, Object>> scenicsList = new ArrayList<Map<String, Object>>(); //景点信息
						List<Map<String, Object>> hotelsList = new ArrayList<Map<String, Object>>(); //酒店信息
						Iterator<ZbyProductPackageDetail> iterator = resInfos.iterator();
						while (iterator.hasNext()) {
							ZbyProductPackageDetail res = iterator.next();
							if(product.getProductId().equals(res.getProductId())) {
								Map<String, Object> resMap = new HashMap<String, Object>();
								resMap.put("resName", res.getResName()); //资源名称
								resMap.put("resId", res.getResId()); //资源id
								resMap.put("resPy", PinyinUtils.toPinyin(res.getResName(), true, true) + PinyinUtils.toPinyin(res.getResName(), false, true)); //资源拼音
								resMap.put("resGrade", PinyinUtils.toPinyin(res.getResGrade(), true, true)+"-"+res.getResGrade()); //资源等级拼音-等级名称
								resMap.put("resInfo", res.getResId()+"-"+res.getResName()+"-"+res.getResCity()); //资源id-资源名称-资源城市
								if (ResourceType.SCENIC.getValue().equals(res.getResType())) {
									// 景区
									scenicsList.add(resMap);
								} else if (ResourceType.HOTEL.getValue().equals(res.getResType())) {
									// 酒店
									hotelsList.add(resMap);
								}
								iterator.remove();
							}
						}
						source.put("scenics", scenicsList);
						source.put("hotels", hotelsList);
					}
					// 添加到map中
					sourceMap.put(product.getProductId(), source);
				}
				// 根据别名获取下一个索引名， 如果返回null，则使用默认索引名称
				String index = elasticSearchHandler.getNextIndex(CommonDisconf.zbyAlias, CommonDisconf.zbyIndex);
				// 批量操作索引数据
				elasticSearchHandler.prepareBulk(index, CommonDisconf.zbyType, sourceMap);
				logger.info("全量数据导入成功");
			}
		} catch (Exception e) {
			logger.error("全量数据导入失败", e);
		}
	}
	
	/**
	 * 导入增量数据
	 * 
	 * @param productIds
	 */
	public void importIncrementData(List<String> productIds) {
		try {
			if (CollectionUtils.isNotEmpty(productIds)) {
				logger.info("线路{}导入增量数据", productIds.toString());
				// 查询线路
				List<ZbyProduct> products = zbyProductMapper.queryProductByPIds(productIds);
				if (null != products) {
					// 批量处理数据
					Map<String, Map<String, Object>> sourceMap = new HashMap<String, Map<String, Object>>();
					
					// 新增或修改的线路
					List<String> ids = new ArrayList<String>();
					Iterator<ZbyProduct> iters = products.iterator();
					while (iters.hasNext()) {
						ZbyProduct product = iters.next();
						if (TrueFalseStatus.TRUE.getValue().equals(product.getIsDelete())) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("isDelete", product.getIsDelete()); //是否删除
							sourceMap.put(product.getProductId(), map);
							// 已经删除的不在处理
							iters.remove();
						} else {
							ids.add(product.getProductId());
						}
					}
					
					// 查询所有线路的主题分类
					List<ZbyRecomInfo> recomInfos = zbyRecomInfoMapper.queryRecomInfoByPIds(ids);
					
					// 查询所有线路的资源信息
					List<ZbyProductPackageDetail> resInfos = zbyProductPackageDetailMapper.queryResInfoByPIds(ids);
					
					// 查询省市信息
					Map<String, String> provinceCityMap = zbyCityService.getProvinceCityMap();
					
					// 线路最低价格
					Map<String, BigDecimal> productMinPriceMap = zbyProductService.queryProductPrice();
					
					// 导数据
					for (ZbyProduct product : products) {
						Map<String, Object> source = new HashMap<String, Object>();
						source.put("productId", product.getProductId()); //线路id
						source.put("mainName", product.getMainName()); //线路主标题
						source.put("mainNamePy", PinyinUtils.toPinyin(product.getMainName(), true, true) + PinyinUtils.toPinyin(product.getMainName(), false, true)); //线路主标题拼音
						source.put("subName", product.getSubName()); //线路副标题
						source.put("subNamePy", PinyinUtils.toPinyin(product.getSubName(), true, true) + PinyinUtils.toPinyin(product.getSubName(), false, true)); //线路副标题拼音
						source.put("appTitle", product.getAppTitle()); //线路app标题
						source.put("appTitlePy", PinyinUtils.toPinyin(product.getAppTitle(), true, true) + PinyinUtils.toPinyin(product.getAppTitle(), false, true)); //线路app标题拼音
						source.put("days", product.getDays()); //线程天数
						String province = provinceCityMap.get(product.getCityId());
						source.put("provinceName", province); //省名称
						source.put("provincePy", PinyinUtils.toPinyin(province, true, true) + PinyinUtils.toPinyin(province, false, true)); //省拼音
						source.put("cityId", product.getCityId()); //城市id
						source.put("cityName", product.getCityName()); //城市名称
						source.put("cityPy", PinyinUtils.toPinyin(product.getCityName(), true, true) + PinyinUtils.toPinyin(product.getCityName(), false, true)); //城市拼音
						source.put("cityIdName", product.getCityId()+"-"+product.getCityName()); //城市id-名称
						source.put("beginDate", product.getBeginDate()); //开始时间
						source.put("overDate", product.getOverDate()); //结束时间
						source.put("imageUrl", product.getImageUrl()); //图片地址
						source.put("imageUrlLocal", product.getImageUrlLocal()); //国美图片地址
						BigDecimal minPrice = productMinPriceMap.get(product.getProductId());
						source.put("productMinPrice", null != minPrice ? minPrice : product.getProductMinPrice()); //最小价格
						source.put("productMaxPrice", product.getProductMaxPrice()); //最大价格
						source.put("sellUnit", product.getSellUnit()); //单位
						source.put("saleCount", null == product.getSaleCount() ? 0 : product.getSaleCount()); //销量
						source.put("browseCount", null == product.getBrowseCount() ? 0 : product.getBrowseCount()); //浏览量
						source.put("isDelete", product.getIsDelete()); //是否删除
						
						// 主题分类
						if (null != recomInfos) {
							List<Map<String, Object>> titleList = new ArrayList<Map<String, Object>>();
							for(ZbyRecomInfo recomInfo : recomInfos) {
								if(product.getProductId().equals(recomInfo.getProductId())) {
									Map<String, Object> recomInfoMap = new HashMap<String, Object>();
									recomInfoMap.put("titleName", recomInfo.getTitle()); //主题名称
									recomInfoMap.put("titlePy", PinyinUtils.toPinyin(recomInfo.getTitle(), true, true) + PinyinUtils.toPinyin(recomInfo.getTitle(), false, true)); //主题拼音
									recomInfoMap.put("titlePyName", PinyinUtils.toPinyin(recomInfo.getTitle(), true, true)+"-"+recomInfo.getTitle()); //主题拼音简拼-名称
									titleList.add(recomInfoMap);
								}
							}
							source.put("titles", titleList);
						}
						
						// 资源信息
						if (null != resInfos) {
							List<Map<String, Object>> scenicsList = new ArrayList<Map<String, Object>>(); //景点信息
							List<Map<String, Object>> hotelsList = new ArrayList<Map<String, Object>>(); //酒店信息
							Iterator<ZbyProductPackageDetail> iterator = resInfos.iterator();
							while (iterator.hasNext()) {
								ZbyProductPackageDetail res = iterator.next();
								if(product.getProductId().equals(res.getProductId())) {
									Map<String, Object> resMap = new HashMap<String, Object>();
									resMap.put("resName", res.getResName()); //资源名称
									resMap.put("resId", res.getResId()); //资源id
									resMap.put("resPy", PinyinUtils.toPinyin(res.getResName(), true, true) + PinyinUtils.toPinyin(res.getResName(), false, true)); //资源拼音
									resMap.put("resGrade", PinyinUtils.toPinyin(res.getResGrade(), true, true)+"-"+res.getResGrade()); //资源等级拼音-等级名称
									resMap.put("resInfo", res.getResId()+"-"+res.getResName()+"-"+res.getResCity()); //资源id-资源名称-资源城市
									if (ResourceType.SCENIC.getValue().equals(res.getResType())) {
										// 景区
										scenicsList.add(resMap);
									} else if (ResourceType.HOTEL.getValue().equals(res.getResType())) {
										// 酒店
										hotelsList.add(resMap);
									}
									iterator.remove();
								}
							}
							source.put("scenics", scenicsList);
							source.put("hotels", hotelsList);
						}
						// 添加到map中
						sourceMap.put(product.getProductId(), source);
					}
					// 批量处理
					this.batchIndexData(sourceMap);
					logger.info("增量数据导入成功");
				}
			}
		} catch (Exception e) {
			logger.error("增量数据导入失败，增量线路参数{}", productIds.toString(), e);
		}
	}
	
	/**
	 * 批量处理索引数据
	 * 
	 * @param source
	 */
	public void batchIndexData(Map<String, Map<String, Object>> source) {
		try {
			if (MapUtils.isNotEmpty(source)) {
				// 根据别名获取索引名， 如果返回null，则使用默认索引名称
				String index = elasticSearchHandler.getIndex(CommonDisconf.zbyAlias, CommonDisconf.zbyIndex);
				// 批量处理
				elasticSearchHandler.prepareBulk(index, CommonDisconf.zbyType, source);
				logger.info("批量处理索引数据成功");
			}
		} catch (Exception e) {
			logger.error("批量处理索引数据失败，批量处理索引参数{}", source, e);
		}
	}
	
	/**
	 * 批量更新索引数据
	 * 
	 * @param source
	 */
	public void batchUpdateIndexData(Map<String, Map<String, Object>> source) {
		try {
			if (MapUtils.isNotEmpty(source)) {
				// 根据别名获取索引名， 如果返回null，则使用默认索引名称
				String index = elasticSearchHandler.getIndex(CommonDisconf.zbyAlias, CommonDisconf.zbyIndex);
				// 批量更新
				elasticSearchHandler.prepareBulk(index, CommonDisconf.zbyType, null, source, null);
				logger.info("批量更新索引数据成功");
			}
		} catch (Exception e) {
			logger.error("批量更新索引数据失败，批量更新索引参数{}", source, e);
		}
	}
	
	/**
	 * 搜索列表信息
	 * 
	 * @param request
	 * @return
	 */
	@GET
	@Path("list")
	public QueryResponse searchList(@BeanParam QueryRequest request) {
		try {
			QueryResponse response = new QueryResponse();
			// request信息
			response.setRequest(request);
			
			// 搜索生成器
			SearchRequestBuilder searchBuilder = getSearchBuilder();

			// 设置查询条件
			BoolQueryBuilder queryBuilder = getQueryBuilder();
			
			// 关键字
			String keyword = request.getKeyword();
			if(StringUtils.isNotEmpty(keyword)) {
				// 过滤特殊字符
				keyword = QueryParser.escape(keyword);
				
				// 关键字查询
				if (StringUtils.checkOnlyLetterNumber(keyword)) {
					// 拼音不做分词，只模糊查询
					queryBuilder.must(QueryBuilders.boolQuery()
							.should(new WildcardQueryBuilder("subNamePy", "*" + keyword + "*").boost(5))
							.should(QueryBuilders.termQuery("productId", keyword))
							.should(new WildcardQueryBuilder("provincePy", "*" + keyword + "*"))
							.should(QueryBuilders.nestedQuery("titles", QueryBuilders.boolQuery().must(new WildcardQueryBuilder("titles.titlePy", "*" + keyword + "*"))))
							.should(QueryBuilders.nestedQuery("scenics", QueryBuilders.boolQuery().must(new WildcardQueryBuilder("scenics.resPy", "*" + keyword + "*"))))
							.should(QueryBuilders.nestedQuery("hotels", QueryBuilders.boolQuery().must(new WildcardQueryBuilder("hotels.resPy", "*" + keyword + "*")))));
				} else {
					// 中文搜索
					queryBuilder.must(QueryBuilders.boolQuery()
							.should(QueryBuilders.matchQuery("subName", keyword).operator(Operator.AND).boost(5))
							.should(QueryBuilders.matchQuery("provinceName", keyword).operator(Operator.AND))
							.should(QueryBuilders.nestedQuery("titles", QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("titles.titleName", keyword).operator(Operator.AND))))
							.should(QueryBuilders.nestedQuery("scenics", QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("scenics.resName", keyword).operator(Operator.AND))))
							.should(QueryBuilders.nestedQuery("hotels", QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("hotels.resName", keyword).operator(Operator.AND)))));
				}
			}
			
			// 当前城市拼音
			//if (StringUtils.isNotEmpty(request.getCityPy())) {
			//	queryBuilder.must(new WildcardQueryBuilder("cityPy", "*" + request.getCityPy() + "*"));
			//}
			
			// 当前城市ID
			if (StringUtils.isNotEmpty(request.getTargetCityId())) {
				// 如果目的地城市存在，只搜索目的地城市下的线路
				queryBuilder.must(QueryBuilders.termQuery("cityId", request.getTargetCityId()));
			} else {
				if (StringUtils.isNotEmpty(request.getCityId())) {
					// 如果主题 或 线程天数存在，需要辐射到当前城市的周边城市线路
					BoolQueryBuilder aroundCityQueryBuilder = QueryBuilders.boolQuery(); 
					// 当前城市
					aroundCityQueryBuilder.should(QueryBuilders.termQuery("cityId", request.getCityId()).boost(5));
					// 周边城市
					List<AroundCity> aroundCities = zbyCityService.getAroundCity(PlatformType.WAP.getValue(), request.getCityId());
					if (null != aroundCities) {
						for (AroundCity aroundCity : aroundCities) {
							aroundCityQueryBuilder.should(QueryBuilders.termQuery("cityId", aroundCity.getAroundCityId()));
						}
					}
					queryBuilder.must(aroundCityQueryBuilder);
				}
			}
			
			// 主题
			if (StringUtils.isNotEmpty(request.getTitle())) {
				queryBuilder.must(QueryBuilders.nestedQuery("titles", QueryBuilders.boolQuery().must(new WildcardQueryBuilder("titles.titlePy", "*" + request.getTitle() + "*"))));
			}
			// 行程天数
			if (null != request.getDays()) {
				queryBuilder.must(QueryBuilders.termQuery("days", request.getDays()));
			}
			// 景点
			if (StringUtils.isNotEmpty(request.getScenic())) {
				queryBuilder.must(QueryBuilders.nestedQuery("scenics", QueryBuilders.boolQuery().must(QueryBuilders.termQuery("scenics.resId", request.getScenic()))));
			}
			// 酒店等级
			if (StringUtils.isNotEmpty(request.getHotelGrade())) {
				queryBuilder.must(QueryBuilders.nestedQuery("hotels", QueryBuilders.boolQuery().must(new WildcardQueryBuilder("hotels.resGrade", "*" + request.getHotelGrade() + "*"))));
			}
			// 最小价格
			if (null != request.getMinPrice()) {
				queryBuilder.must(QueryBuilders.rangeQuery("productMinPrice").gte(request.getMinPrice()));
			}
			// 最大价格
			if (null != request.getMaxPrice()) {
				queryBuilder.must(QueryBuilders.rangeQuery("productMinPrice").lte(request.getMaxPrice()));
			}
			
			// 排除已有的线路
			if (null != request.getProductIds() && request.getProductIds().size() > 0) {
				BoolQueryBuilder productQueryBuilder = QueryBuilders.boolQuery(); 
				for (String productId : request.getProductIds()) {
					productQueryBuilder.should(QueryBuilders.termQuery("productId", productId));
				}
				queryBuilder.mustNot(productQueryBuilder);
			}
			
			// 多主题信息查询
			if (null != request.getTitles() && request.getTitles().size() > 0) {
				BoolQueryBuilder titleQueryBuilder = QueryBuilders.boolQuery(); 
				for (String title : request.getTitles()) {
					titleQueryBuilder.should(QueryBuilders.nestedQuery("titles", QueryBuilders.boolQuery().must(new WildcardQueryBuilder("titles.titlePy", "*" + title + "*"))));
				}
				queryBuilder.must(titleQueryBuilder);
			}
			
			searchBuilder.setQuery(queryBuilder);
			
			// 设置排序
			if (StringUtils.isNotEmpty(request.getSortField()) && StringUtils.isNotEmpty(request.getSortOrder())) {
				searchBuilder.addSort(request.getSortField(), request.getSortOrder().equals(SortOrder.DESC.toString()) ? SortOrder.DESC : SortOrder.ASC);
			} else {
				if(StringUtils.isEmpty(keyword)) {
					searchBuilder.addSort("saleCount", SortOrder.DESC);
					searchBuilder.addSort("browseCount", SortOrder.DESC);
				}
			}
			
			// 城市聚合
			searchBuilder.addAggregation(AggregationBuilders.terms("cityAgg").field("cityIdName").size(20));
			// 线程天数聚合
			searchBuilder.addAggregation(AggregationBuilders.terms("dayAgg").field("days").size(20));
			// 主题聚合
			searchBuilder.addAggregation(AggregationBuilders.nested("titleAgg").path("titles").subAggregation(AggregationBuilders.terms("title").field("titles.titlePyName").size(20)));
			// 景点聚合
			searchBuilder.addAggregation(AggregationBuilders.nested("scenicAgg").path("scenics").subAggregation(AggregationBuilders.terms("resInfo").field("scenics.resInfo").size(20)));
			// 酒店等级聚合
			searchBuilder.addAggregation(AggregationBuilders.nested("hotelGradeAgg").path("hotels").subAggregation(AggregationBuilders.terms("resGrade").field("hotels.resGrade").size(20)));
			
			// 线路名称设置高亮
			if (StringUtils.isNotEmpty(keyword)) {
				BoolQueryBuilder mainNameBuilderHligh = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("mainName", keyword));
				searchBuilder.addHighlightedField(new HighlightBuilder.Field("mainName").highlightQuery(mainNameBuilderHligh));
				BoolQueryBuilder subNameBuilderHligh = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("subName", keyword));
				searchBuilder.addHighlightedField(new HighlightBuilder.Field("subName").highlightQuery(subNameBuilderHligh));
			}
			
			// 设置分页
			searchBuilder.setFrom((request.getPageIndex()-1) * request.getPageSize());
			searchBuilder.setSize(request.getPageSize());
			
			// 搜索，并设置超时时间
			SearchResponse searchResponse = searchBuilder.get("3000");
			if (null != searchResponse) {
				SearchHits searchHits = searchResponse.getHits();
				if (null != searchHits) {
					// 总记录数
					response.setCount(searchHits.getTotalHits());

					// 产品信息
					List<Product> products = new ArrayList<Product>();
					response.setProducts(products);
					for (SearchHit searchHit : searchHits.getHits()) {
						products.add(setProduct(searchHit));
					}
				}
				
				// 聚合
				Aggregations aggregations = searchResponse.getAggregations();
				if (null != aggregations) {
					// 城市结果集
					response.setCitys(getCityAggregation(aggregations));
					// 行程天数结果集
					response.setDays(getDayAggregation(aggregations));
					// 主题结果集
					response.setTitles(getTitleAggregation(aggregations));
					// 景点结果集
					response.setScenics(getScenicAggregation(aggregations));
					// 酒店等级结果集
					response.setHotelGrades(getHotelGradeAggregation(aggregations));
				}
			}
	        return response;
		} catch (Exception e) {
			logger.error("列表搜索失败，搜索参数{}", jsonUtils.serialize(request), e);
			return null;
		}
	}
	
	/**
	 * 获取BoolQueryBuilder 必须的查询条件
	 * 
	 * @return
	 */
	private BoolQueryBuilder getQueryBuilder() {
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		// 只显示未删除的产品
		queryBuilder.must(QueryBuilders.termQuery("isDelete", false));
		return queryBuilder;
	}
	
	/**
	 * 获取SearchRequestBuilder
	 * 
	 * @return
	 */
	private SearchRequestBuilder getSearchBuilder() {
		SearchRequestBuilder searchBuilder = elasticSearchHandler.searchRequestBuilder(CommonDisconf.zbyAlias, CommonDisconf.zbyType);
		// 设置ip定位
		String preference = "es";
		try {
			preference = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
		}
		searchBuilder.setPreference(preference);
		return searchBuilder;
	}
	
	/**
	 * 获取MultiSearchRequestBuilder
	 * 
	 * @return
	 */
	private MultiSearchRequestBuilder getMultiSearchBuilder() {
		return elasticSearchHandler.multiSearchRequestBuilder();
	}
	
	/**
	 * 获取主题分类聚合信息
	 * 
	 * @param aggregations
	 * @return
	 */
	private List<FilterItem> getTitleAggregation(Aggregations aggregations) {
		Nested nested = aggregations.get("titleAgg");
		if (null != nested) {
			Terms terms = nested.getAggregations().get("title");
			return getFilterItem(terms);
		}
		return null;
	}
	
	/**
	 * 获取景点聚合信息
	 * 
	 * @param aggregations
	 * @return
	 */
	private List<FilterItem> getScenicAggregation(Aggregations aggregations) {
		Nested nested = aggregations.get("scenicAgg");
		if (null != nested) {
			Terms terms = nested.getAggregations().get("resInfo");
			return getFilterItem(terms);
		}
		return null;
	}
	
	/**
	 * 获取酒店等级聚合信息
	 * 
	 * @param aggregations
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination 
	 */
	private List<FilterItem> getHotelGradeAggregation(Aggregations aggregations) throws BadHanyuPinyinOutputFormatCombination {
		Nested nested = aggregations.get("hotelGradeAgg");
		if (null != nested) {
			Terms terms = nested.getAggregations().get("resGrade");
			return getFilterItem(terms);
		}
		return null;
	}
	
	/**
	 * 获取城市聚合信息
	 * 
	 * @param aggregations
	 * @return
	 */
	private List<FilterItem> getCityAggregation(Aggregations aggregations) {
		Terms terms = aggregations.get("cityAgg");
		return getFilterItem(terms);
	}
	
	/**
	 * 获取聚合信息
	 * 
	 * @param terms
	 * @return
	 */
	private List<FilterItem> getFilterItem(Terms terms) {
		if (null != terms) {
			List<FilterItem> items = new ArrayList<FilterItem>();
			for (Bucket bucket : terms.getBuckets()) {
				String key = bucket.getKey();
				if(StringUtils.isNotEmpty(key) && key.split("-").length > 1) {
					items.add(new FilterItem(key.split("-")[1], key.split("-")[0], bucket.getDocCount()));
				}
			}
			return items;
		}
		return null;
	}

	/**
	 * 获取行程天数聚合信息
	 * 
	 * @param aggregations
	 * @return
	 */
	private List<FilterItem> getDayAggregation(Aggregations aggregations) {
		Terms terms = aggregations.get("dayAgg");
		if (null != terms) {
			List<FilterItem> items = new ArrayList<FilterItem>();
			for (Bucket bucket : terms.getBuckets()) {
				items.add(new FilterItem(zbyProductService.travlDaysFormat(Integer.valueOf(bucket.getKey())), bucket.getKey(), bucket.getDocCount()));
			}
			return items;
		}
		return null;
	}
	
	/**
	 * 获取产品信息
	 * 
	 * @param searchHit
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Product setProduct(SearchHit searchHit) {
		// 资源信息
		Map<String, Object> source = searchHit.getSource();
		if (MapUtils.isEmpty(source)) {
			return null;
		}
		// 高亮信息
		Map<String, HighlightField> hfs = searchHit.getHighlightFields();
		Product product = new Product();
		product.setProductId(String.valueOf(source.get("productId"))); // 线路id
		String mainName = String.valueOf(source.get("mainName")); // 线路主标题
		product.setMainTitle(mainName); //线路主标题
		if (null != hfs && hfs.containsKey("mainName")) {
			mainName = hfs.get("mainName").fragments()[0].toString(); // 线路主标题高亮
		}
		product.setMainName(mainName); // 线路主标题
		String subName = String.valueOf(source.get("subName")); // 线路副标题
		product.setSubTitle(subName); // 线路副标题
		if (null != hfs && hfs.containsKey("subName")) {
			subName = hfs.get("subName").fragments()[0].toString(); // 线路副标题高亮
		}
		product.setSubName(subName); // 线路副标题
		product.setAppTitle(String.valueOf(source.get("appTitle"))); // 线路app标题
		product.setDays(Integer.valueOf(String.valueOf(source.get("days")))); // 行程天数
		product.setDaysName(zbyProductService.travlDaysFormat(product.getDays())); // 线程天数中文
		product.setBeginDate(String.valueOf(source.get("beginDate"))); // 开始时间
		product.setOverDate(String.valueOf(source.get("overDate"))); // 结束时间
		String imageUrl = String.valueOf(source.get("imageUrl")); // 图片原图地址
		product.setImageUrl(imageUrl); //原图地址
		product.setIndexImageUrl(imageWH(imageUrl, TcImageType.WH360x250.getValue())); //首页图片地址
		product.setListImageUrl(imageWH(imageUrl, TcImageType.WH360x250.getValue())); //列表图片地址
		product.setRightImageUrl(imageWH(imageUrl, TcImageType.WH200x150.getValue())); //右侧图片地址
		product.setCityId(String.valueOf(source.get("cityId"))); // 城市id
		product.setCityName(String.valueOf(source.get("cityName"))); // 城市名称
		product.setProductMinPrice(new BigDecimal(String.valueOf(source.get("productMinPrice")))); // 最小价格
		product.setProductMaxPrice(new BigDecimal(String.valueOf(source.get("productMaxPrice")))); // 最大价格
		product.setSellUnit(String.valueOf(source.get("sellUnit"))); // 单位
		product.setSaleCount(Integer.valueOf(String.valueOf(source.get("saleCount")))); // 销量
		product.setBrowseCount(Integer.valueOf(String.valueOf(source.get("browseCount")))); // 浏览量
		product.setTitles((List<Map<String, Object>>) source.get("titles")); // 主题信息
		product.setScenics((List<Map<String, Object>>) source.get("scenics")); // 景点信息
		product.setHotels((List<Map<String, Object>>) source.get("hotels")); // 酒店信息
		return product;
	}
	
	/**
	 * 图片不同规格尺寸
	 * @param url  原图地址
	 * @param wh   尺寸
	 * @return
	 */
	private String imageWH(String url, String wh) {
		if (StringUtils.isEmpty(url) || StringUtils.isEmpty(wh)) {
			return null;
		}
		return url.substring(0, url.lastIndexOf(".")) + wh + url.substring(url.lastIndexOf("."));
	}
	
	/**
	 * 搜索自动联想信息
	 * 
	 * @param keyword
	 * @return
	 */
	@GET
	@Path("auto/{keyword}")
	public AutoResponse searchAuto(@PathParam("keyword") String keyword) {
		try {
			AutoResponse response = new AutoResponse();
			if (StringUtils.isEmpty(keyword)) {
				return null;
			}
			
			// 过滤特殊字符
			keyword = QueryParser.escape(keyword);
					
			// 搜索生成器
			SearchRequestBuilder searchBuilderCity = getSearchBuilder();
			SearchRequestBuilder searchBuilderScenic = getSearchBuilder();
			SearchRequestBuilder searchBuilderHotel = getSearchBuilder();

			// 设置查询条件
			BoolQueryBuilder queryBuilderCity = getQueryBuilder();
			BoolQueryBuilder queryBuilderScenic = getQueryBuilder();
			BoolQueryBuilder queryBuilderHotel = getQueryBuilder();
			
			// 关键字查询
			if (StringUtils.checkOnlyLetterNumber(keyword.replaceAll(" ", ""))) {
				// 拼音不做分词，只模糊查询
				queryBuilderCity.must(new WildcardQueryBuilder("cityPy", "*" + keyword.replaceAll(" ", "") + "*"));
				queryBuilderScenic.must(QueryBuilders.nestedQuery("scenics", QueryBuilders.boolQuery().must(new WildcardQueryBuilder("scenics.resPy", "*" + keyword.replaceAll(" ", "") + "*"))));
				queryBuilderHotel.must(QueryBuilders.nestedQuery("hotels", QueryBuilders.boolQuery().must(new WildcardQueryBuilder("hotels.resPy", "*" + keyword.replaceAll(" ", "") + "*"))));
			} else {
				// 中文搜索
				queryBuilderCity.must(QueryBuilders.matchQuery("cityName", keyword).operator(Operator.AND));
				queryBuilderScenic.must(QueryBuilders.nestedQuery("scenics", QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("scenics.resName", keyword).operator(Operator.AND))));
				queryBuilderHotel.must(QueryBuilders.nestedQuery("hotels", QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("hotels.resName", keyword).operator(Operator.AND))));
			}
			
			// 设置query
			searchBuilderCity.setQuery(queryBuilderCity);
			searchBuilderScenic.setQuery(queryBuilderScenic);
			searchBuilderHotel.setQuery(queryBuilderHotel);
			
			// 聚合
			searchBuilderCity.addAggregation(AggregationBuilders.terms("cityAgg").field("cityIdName").size(2));
			searchBuilderScenic.addAggregation(AggregationBuilders.nested("scenicAgg").path("scenics").subAggregation(AggregationBuilders.terms("resInfo").field("scenics.resInfo")));
			searchBuilderHotel.addAggregation(AggregationBuilders.nested("hotelAgg").path("hotels").subAggregation(AggregationBuilders.terms("resInfo").field("hotels.resInfo")));
			
			// 多组搜索，并设置超时时间
			MultiSearchResponse multiSearchResponse = getMultiSearchBuilder().add(searchBuilderCity).add(searchBuilderScenic).add(searchBuilderHotel).get("5000");
			if (null != multiSearchResponse) {
				for (MultiSearchResponse.Item item : multiSearchResponse.getResponses()) {
		          	SearchResponse searchResponse = item.getResponse();
		          	Aggregations aggregations = searchResponse.getAggregations();
		          	if (null != aggregations) {
		          		// 城市聚合结果集
						Terms cityTerms = aggregations.get("cityAgg");
						if (null != cityTerms) {
							List<Res> citys = new ArrayList<Res>();
							for (Bucket bucket : cityTerms.getBuckets()) {
								String key = bucket.getKey();
								if (StringUtils.isNotEmpty(key)) {
									citys.add(new Res(key.split("-")[0], setHighlight(keyword, key.split("-")[1]), null));
								}
							}
							response.setCitys(citys);
						}
						
						// 景点聚合结果集
						Nested scenicNested = aggregations.get("scenicAgg");
						if (null != scenicNested) {
							Terms scenicTerms = scenicNested.getAggregations().get("resInfo");
							if (null != scenicTerms) {
								Set<Res> scenics = new LinkedHashSet<Res>();
								for (Bucket bucket : scenicTerms.getBuckets()) {
									String key = bucket.getKey();
									if (StringUtils.isNotEmpty(key)) {
										String resName = null;
										if (StringUtils.checkOnlyLetterNumber(keyword.replaceAll(" ", ""))) {
											resName = PinyinUtils.toPinyin(key.split("-")[1], true, true) + PinyinUtils.toPinyin(key.split("-")[1], false, true);
										} else {
											resName = key.split("-")[1];
										}
										if (StringUtils.isNotEmpty(resName) && resName.startsWith(keyword.replaceAll(" ", ""))) {
											scenics.add(new Res(key.split("-")[0], setHighlight(keyword, key.split("-")[1]), key.split("-")[2]));
										}
										if (scenics.size() == 4) {
											break;
										}
									}
								}
								if (scenics.size() < 4) {
									for (Bucket bucket : scenicTerms.getBuckets()) {
										String key = bucket.getKey();
										if (StringUtils.isNotEmpty(key)) {
											String resName = null;
											if (StringUtils.checkOnlyLetterNumber(keyword.replaceAll(" ", ""))) {
												resName = PinyinUtils.toPinyin(key.split("-")[1], true, true) + PinyinUtils.toPinyin(key.split("-")[1], false, true);
												if (resName.contains(keyword.replaceAll(" ", ""))) {
													scenics.add(new Res(key.split("-")[0], setHighlight(keyword, key.split("-")[1]), key.split("-")[2]));
												}
											} else {
												resName = key.split("-")[1];
												if (StringUtils.isNotEmpty(resName) && strToList(resName).containsAll(strToList(keyword))) {
													scenics.add(new Res(key.split("-")[0], setHighlight(keyword, key.split("-")[1]), key.split("-")[2]));
												}
											}
											if (scenics.size() == 4) {
												break;
											}
										}
									}
								}
								response.setScenics(new ArrayList<Res>(scenics));
							}
						}
						
						// 酒店聚合结果集
						Nested hotelNested = aggregations.get("hotelAgg");
						if (null != hotelNested) {
							Terms hotelTerms = hotelNested.getAggregations().get("resInfo");
							if (null != hotelTerms) {
								Set<Res> hotels = new LinkedHashSet<Res>();
								for (Bucket bucket : hotelTerms.getBuckets()) {
									String key = bucket.getKey();
									if (StringUtils.isNotEmpty(key)) {
										String resName = null;
										if (StringUtils.checkOnlyLetterNumber(keyword.replaceAll(" ", ""))) {
											resName = PinyinUtils.toPinyin(key.split("-")[1], true, true) + PinyinUtils.toPinyin(key.split("-")[1], false, true);
										} else {
											resName = key.split("-")[1];
										}
										if (StringUtils.isNotEmpty(resName) && resName.startsWith(keyword.replaceAll(" ", ""))) {
											hotels.add(new Res(key.split("-")[0], setHighlight(keyword, key.split("-")[1]), key.split("-")[2]));
										}
										if (hotels.size() == 4) {
											break;
										}
									}
								}
								if (hotels.size() < 4) {
									for (Bucket bucket : hotelTerms.getBuckets()) {
										String key = bucket.getKey();
										if (StringUtils.isNotEmpty(key)) {
											String resName = null;
											if (StringUtils.checkOnlyLetterNumber(keyword.replaceAll(" ", ""))) {
												resName = PinyinUtils.toPinyin(key.split("-")[1], true, true) + PinyinUtils.toPinyin(key.split("-")[1], false, true);
												if (resName.contains(keyword.replaceAll(" ", ""))) {
													hotels.add(new Res(key.split("-")[0], setHighlight(keyword, key.split("-")[1]), key.split("-")[2]));
												}
											} else {
												resName = key.split("-")[1];
												if (StringUtils.isNotEmpty(resName) && strToList(resName).containsAll(strToList(keyword))) {
													hotels.add(new Res(key.split("-")[0], setHighlight(keyword, key.split("-")[1]), key.split("-")[2]));
												}
											}
											if (hotels.size() == 4) {
												break;
											}
										}
									}
								}
								response.setHotels(new ArrayList<Res>(hotels));
							}
						}
					}
				}
			}
	        return response;
		} catch (Exception e) {
			logger.error("自动联想搜索失败，联想关键字{}", keyword, e);
			return null;
		}
	}
	
	/**
	 * 字符串转成集合
	 * 
	 * @param str
	 * @return
	 */
	private List<String> strToList(String str) {
		if (StringUtils.isNotEmpty(str)) {
			List<String> strs = new ArrayList<String>();
			for (String s : str.split("")) {
				if (!" ".equals(s)) {
					strs.add(s);
				}
			}
			return strs;
		}
		return null;
	}
	
	/**
	 * 设置高亮
	 * 
	 * @param keyword
	 * @param str
	 * @return
	 */
	private String setHighlight(String keyword, String str) {
		if (StringUtils.isNotEmpty(keyword) && StringUtils.isNotEmpty(str)) {
			return str.replaceFirst(keyword, "<em>" + keyword + "</em>");
		}
		return null;
	}
	
	/**
	 * 搜索关联标签
	 * 
	 * @param request
	 * @return
	 */
	public List<Product> searchRelationLabel(QueryRequest request) {
		try {
			if (StringUtils.isEmpty(request.getModule())) {
				return null;
			}
			
			// 搜索生成器
			SearchRequestBuilder searchBuilder = getSearchBuilder();

			// 设置查询条件
			BoolQueryBuilder queryBuilder = getQueryBuilder();
			
			// 当前城市
			if (StringUtils.isNotEmpty(request.getCityId())) {
				queryBuilder.must(QueryBuilders.termQuery("cityId", request.getCityId()));
			} else if (StringUtils.isNotEmpty(request.getTargetCityId())) {
				// 查询周边城市线路
				BoolQueryBuilder aroundCityQueryBuilder = QueryBuilders.boolQuery(); 
				// 周边城市
				List<AroundCity> aroundCities = zbyCityService.getAroundCity(PlatformType.WAP.getValue(), request.getTargetCityId());
				if (null != aroundCities) {
					for (AroundCity aroundCity : aroundCities) {
						aroundCityQueryBuilder.should(QueryBuilders.termQuery("cityId", aroundCity.getAroundCityId()));
					}
				}
				queryBuilder.must(aroundCityQueryBuilder);
			}
			
			// 楼层模块关联的标签
			if (StringUtils.isNotEmpty(request.getModule())) {
				List<Advert> recommends = RecommendDisconf.getRecommends();
				if (null != recommends) {
					List<Advert> labels = null;
					for (Advert advert : recommends) {
						if (request.getModule().equals(advert.getValue())) {
							labels = advert.getLabels();
							break;
						}
					}
					
					if (null != labels) {
						BoolQueryBuilder titleQueryBuilder = QueryBuilders.boolQuery(); 
						for (Advert label : labels) {
							titleQueryBuilder.should(QueryBuilders.nestedQuery("titles", QueryBuilders.boolQuery().must(new WildcardQueryBuilder("titles.titlePy", "*" + label.getValue() + "*"))));
						}
						queryBuilder.must(titleQueryBuilder);
					}
				}
			}
			
			// 排除已有的线路
			if (null != request.getProductIds() && request.getProductIds().size() > 0) {
				BoolQueryBuilder productQueryBuilder = QueryBuilders.boolQuery(); 
				for (String productId : request.getProductIds()) {
					productQueryBuilder.should(QueryBuilders.termQuery("productId", productId));
				}
				queryBuilder.mustNot(productQueryBuilder);
			}
			
			searchBuilder.setQuery(queryBuilder);
			
			// 设置排序
			searchBuilder.addSort("saleCount", SortOrder.DESC);
			searchBuilder.addSort("browseCount", SortOrder.DESC);
			
			// 设置分页
			if (null != request.getPageSize() && request.getPageSize() > 0) {
				searchBuilder.setSize(request.getPageSize());
			}
			
			// 搜索，并设置超时时间
			SearchResponse searchResponse = searchBuilder.get("3000");
			if (null != searchResponse) {
				SearchHits searchHits = searchResponse.getHits();
				if (null != searchHits) {
					// 产品信息
					List<Product> products = new ArrayList<Product>();
					for (SearchHit searchHit : searchHits.getHits()) {
						products.add(setProduct(searchHit));
					}
					return products;
				}
			}
		} catch (Exception e) {
			logger.error("关联标签搜索失败，关联标签参数{}", jsonUtils.serialize(request), e);
		}
		return null;
	}
	
	/**
	 * 根据线路id搜索线路信息
	 * 
	 * @param productIds
	 * @return
	 */
	public List<Product> searchProducts(List<String> productIds) {
		try {
			if (CollectionUtils.isEmpty(productIds)) {
				return null;
			}
			
			// 搜索生成器
			SearchRequestBuilder searchBuilder = getSearchBuilder();

			// 设置查询条件
			BoolQueryBuilder queryBuilder = getQueryBuilder();
			
			// 查询线路
			BoolQueryBuilder productQueryBuilder = QueryBuilders.boolQuery(); 
			for (String productId : productIds) {
				productQueryBuilder.should(QueryBuilders.termQuery("productId", productId));
			}
			queryBuilder.must(productQueryBuilder);
			
			searchBuilder.setQuery(queryBuilder);
			
			// 搜索，并设置超时时间
			SearchResponse searchResponse = searchBuilder.get("3000");
			if (null != searchResponse) {
				SearchHits searchHits = searchResponse.getHits();
				if (null != searchHits) {
					// 产品信息
					List<Product> products = new ArrayList<Product>();
					for (SearchHit searchHit : searchHits.getHits()) {
						products.add(setProduct(searchHit));
					}
					return products;
				}
			}
		} catch (Exception e) {
			logger.error("根据线路id搜索线路信息失败，线路id搜索参数{}", productIds.toString(), e);
		}
		return null;
	}

}

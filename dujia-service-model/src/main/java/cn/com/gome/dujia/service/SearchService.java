package cn.com.gome.dujia.service;

import java.util.List;
import java.util.Map;

import cn.com.gome.dujia.vo.search.AutoResponse;
import cn.com.gome.dujia.vo.search.Product;
import cn.com.gome.dujia.vo.search.QueryRequest;
import cn.com.gome.dujia.vo.search.QueryResponse;

/**
 * 搜索服务接口
 * 
 * @author xiongyan
 * @date 2016年4月14日 下午2:11:10
 */
public interface SearchService {
	
	/**
	 * 索引字段
	 */
	public void indexMapping();

	/**
	 * 导入全量数据
	 */
	public void importFullData();
	
	/**
	 * 导入增量数据
	 * 
	 * @param productIds
	 */
	public void importIncrementData(List<String> productIds);
	
	/**
	 * 批量处理索引数据
	 * 
	 * @param source
	 */
	public void batchIndexData(Map<String, Map<String, Object>> source);
	
	/**
	 * 批量更新索引数据
	 * 
	 * @param source
	 */
	public void batchUpdateIndexData(Map<String, Map<String, Object>> source);
	
	/**
	 * 搜索列表信息
	 * 
	 * @param request
	 * @return
	 */
	public QueryResponse searchList(QueryRequest request);
	
	/**
	 * 搜索自动联想信息
	 * 
	 * @param keyword
	 * @return
	 */
	public AutoResponse searchAuto(String keyword);
	
	/**
	 * 搜索关联标签
	 * 
	 * @param request
	 * @return
	 */
	public List<Product> searchRelationLabel(QueryRequest request);
	
	/**
	 * 根据线路id搜索线路信息
	 * 
	 * @param productIds
	 * @return
	 */
	public List<Product> searchProducts(List<String> productIds);
}

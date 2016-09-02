package cn.com.gome.dujia.es;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.MapUtils;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesResponse;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.MultiSearchRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.metadata.AliasMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gome.plan.tools.utils.StringUtils;

/**
 * 搜索集群处理
 * 
 * @author xiongyan
 * @date 2016年4月14日 上午11:48:03
 */
public class ElasticSearchHandler {

	private Logger logger = LoggerFactory.getLogger(ElasticSearchHandler.class);
	
	/**
	 * TransportClient
	 */
	private TransportClient client;
	
	/**
	 * 集群名称
	 */
	private String clusterName;
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * 初始化
	 */
	public void init() {
		if (null == client && null != clusterName && null != address) {
			Map<String, String> settingMap = new HashMap<String, String>();  
			settingMap.put("client.transport.sniff", "true"); 
			settingMap.put("cluster.name", clusterName); 
			
			Settings settings = ImmutableSettings.settingsBuilder().put(settingMap).build();
			
			client = new TransportClient(settings);
			if (null != client) {
				String[] hps = address.split(",");
				for (String hp : hps) {
					String[] ss = hp.split(":");
					client.addTransportAddress(new InetSocketTransportAddress(ss[0], Integer.valueOf(ss[1])));
				}
				logger.info("搜索集群连接成功");
			} else {
				logger.error("搜索集群连接失败");
			}
		}
	}

	/**
	 * 释放连接
	 */
	public void destory() {
		if (null != client) {
			client.close();
			client = null;
			logger.info("搜索集群连接释放");
		}
	}
	
	/**
	 * 索引/别名是否存在
	 * 
	 * @param indices
	 * @return
	 */
	public Boolean indicesExists(String indices) {
		if (null == client || StringUtils.isEmpty(indices)) {
			logger.error("索引/别名是否存在，参数不能为空");
			throw new RuntimeException("索引/别名是否存在，参数不能为空");
		}
		IndicesExistsResponse existsResponse = client.admin().indices().prepareExists(indices).execute().actionGet();
		if (null != existsResponse && existsResponse.isExists()) { 
			logger.info("索引/别名{}已存在", indices);
			return true;
		} else {
			logger.info("索引/别名{}不存在", indices);
			return false;
		}
	}
	
	/**
	 * 创建索引
	 * 
	 * @param index
	 */
	public Boolean createIndex(String index) {
		if (null == client || StringUtils.isEmpty(index)) {
			logger.error("创建索引，参数不能为空");
			throw new RuntimeException("创建索引，参数不能为空");
		}
		// 索引是否存在
		if (indicesExists(index)) {
			return true;
		}
		CreateIndexResponse indexResponse = client.admin().indices().prepareCreate(index).execute().actionGet();
		if (null != indexResponse && indexResponse.isAcknowledged()) {
			logger.info("索引{}创建成功", index);
			return true;
		} else {
			logger.info("索引{}创建失败", index);
			return false;
		}
	}
	
	/**
	 * 删除索引
	 * 
	 * @param index
	 */
	public Boolean deleteIndex(String index) {
		if (null == client || StringUtils.isEmpty(index)) {
			logger.error("删除索引，参数不能为空");
			throw new RuntimeException("删除索引，参数不能为空");
		}
		// 索引是否存在
		if (!indicesExists(index)) {
			logger.error("删除索引{}，索引不存在", index);
			return false;
		}
		DeleteIndexResponse indexResponse = client.admin().indices().prepareDelete(index).execute().actionGet();
		if (null != indexResponse && indexResponse.isAcknowledged()) {
			logger.info("索引{}删除成功", index);
			return true;
		} else {
			logger.info("索引{}删除失败", index);
			return false;
		}
	}
	
	/**
	 * 在索引中添加别名
	 * 
	 * @param index
	 * @param alias
	 * @return
	 */
	public Boolean addAlias(String index, String alias) {
		if (null == client || StringUtils.isEmpty(index) || StringUtils.isEmpty(alias)) {
			logger.error("索引添加别名，参数不能为空");
			throw new RuntimeException("索引添加别名，参数不能为空");
		}
		// 索引是否存在
		if (!indicesExists(index)) {
			logger.error("索引{}添加别名{}，索引不存在", index, alias);
			return false;
		}
		IndicesAliasesResponse aliasesResponse = client.admin().indices().prepareAliases().addAlias(index, alias).execute().actionGet();
		if (null != aliasesResponse && aliasesResponse.isAcknowledged()) {
			logger.info("在索引{}中添加别名{}成功", index, alias);
			return true;
		} else {
			logger.info("在索引{}中添加别名{}失败", index, alias);
			return false;
		}
	}
	
	/**
	 * 从索引中移除别名
	 * 
	 * @param index
	 * @param alias
	 * @return
	 */
	public Boolean removeAlias(String index, String alias) {
		if (null == client || StringUtils.isEmpty(index) || StringUtils.isEmpty(alias)) {
			logger.error("索引移除别名，参数不能为空");
			throw new RuntimeException("索引移除别名，参数不能为空");
		}
		// 索引是否存在
		if (!indicesExists(index)) {
			logger.error("索引{}移除别名{}，索引不存在", index, alias);
			return false;
		}
		IndicesAliasesResponse aliasesResponse = client.admin().indices().prepareAliases().removeAlias(index, alias).execute().actionGet();
		if (null != aliasesResponse && aliasesResponse.isAcknowledged()) {
			logger.info("从索引{}中移除别名{}成功", index, alias);
			return true;
		} else {
			logger.info("从索引{}中移除别名{}失败", index, alias);
			return false;
		}
	}
	
	/**
	 * 获取别名对应的索引名称
	 * 
	 * @param alias
	 * @return
	 */
	public String getAliasIndex(String alias) {
		if (null == client || StringUtils.isEmpty(alias)) {
			logger.error("获取索引对应别名，参数不能为空");
			throw new RuntimeException("获取索引对应别名，参数不能为空");
		}
		// 别名是否存在
		if (!indicesExists(alias)) {
			logger.error("别名{}对应的索引名称，别名不存在", alias);
			return null;
		}
		GetAliasesResponse aliasesResponse = client.admin().indices().prepareGetAliases(alias).execute().actionGet();
		if (null != aliasesResponse) {
			ImmutableOpenMap<String, List<AliasMetaData>> map = aliasesResponse.getAliases();
			if (null != map) {
				String index = map.keys().toString().replace("[", "").replace("]", "");
				logger.info("别名{}对应的索引名称{}", alias, index);
				return index;
			}
		}
		return null;
	}
	
	/**
	 * 索引绑定别名
	 * 
	 * @param index
	 * @param alias
	 */
	public void indexBindAlias(String index, String alias) {
		if (null == client || StringUtils.isEmpty(index) || StringUtils.isEmpty(alias)) {
			logger.error("索引绑定别名，参数不能为空");
			throw new RuntimeException("索引绑定别名，参数不能为空");
		}
		try {
			// 获取别名对应的索引名称
			String oldIndex = getAliasIndex(alias);
			
			// 给新索引中添加别名
			if (addAlias(index, alias)) {
				if (StringUtils.isNotEmpty(oldIndex)) {
					// 删除旧索引
					deleteIndex(oldIndex);
				}
				logger.info("索引{}别名{}，绑定成功", index, alias);
			}
		} catch (Exception e) {
			logger.error("索引{}别名{}，绑定失败", index, alias, e);
		}
	}
	
	/**
	 * 设置索引字段
	 * 
	 * @param index
	 * @param type
	 * @param mapping
	 * @return
	 */
	public void putMapping(String index, String type, XContentBuilder mapping) {
		if (null == client || StringUtils.isEmpty(index) || StringUtils.isEmpty(type) || null == mapping) {
			logger.error("设置索引字段，参数不能为空");
			throw new RuntimeException("设置索引字段，参数不能为空");
		}
		PutMappingResponse mappingResponse = client.admin().indices().preparePutMapping(index).setType(type).setSource(mapping).execute().actionGet();
		if (null != mappingResponse && mappingResponse.isAcknowledged()) {
			logger.info("索引{}类型{}字段{}，设置索引字段成功", index, type, mapping);
		} else {
			logger.info("索引{}类型{}字段{}，设置索引字段成功", index, type, mapping);
		}
	}
	
	/**
	 * 搜索 生成器
	 * 
	 * @param index
	 * @param type
	 * @return
	 */
	public SearchRequestBuilder searchRequestBuilder(String index, String type) {
		if (null == client || StringUtils.isEmpty(index) || StringUtils.isEmpty(type)) {
			logger.error("搜索生成器，参数不能为空");
			throw new RuntimeException("搜索生成器，参数不能为空");
		}
		return client.prepareSearch(index).setTypes(type);
	}
	
	/**
	 * 多组查询
	 * 
	 * @return
	 */
	public MultiSearchRequestBuilder multiSearchRequestBuilder() {
		if (null == client) {
			logger.error("多组查询，参数不能为空");
			throw new RuntimeException("多组查询，参数不能为空");
		}
		return client.prepareMultiSearch();
	}
	
	/**
	 * 添加索引数据
	 * 
	 * @param index
	 * @param type
	 * @param id
	 * @param source
	 */
	public void insertData(String index, String type, String id, Map<String, Object> source) {
		if (null == client || StringUtils.isEmpty(index) || StringUtils.isEmpty(type) || StringUtils.isEmpty(id) || MapUtils.isEmpty(source)) {
			logger.error("添加索引数据，参数不能为空");
			throw new RuntimeException("添加索引数据，参数不能为空");
		}
		IndexResponse indexResponse = client.prepareIndex(index, type, id).setSource(source).execute().actionGet();
		if (null != indexResponse && indexResponse.isCreated()) {
			logger.info("索引{}类型{}主键{}数据{}，添加索引数据成功", index, type, id, source);
		} else {
			logger.info("索引{}类型{}主键{}数据{}，添加索引数据失败", index, type, id, source);
		}
	}
	
	/**
	 * 更新索引数据
	 * 
	 * @param index
	 * @param type
	 * @param id
	 * @param source
	 */
	public void updateData(String index, String type, String id, Map<String, Object> source) {
		if (null == client || StringUtils.isEmpty(index) || StringUtils.isEmpty(type) || StringUtils.isEmpty(id) || MapUtils.isEmpty(source)) {
			logger.error("更新索引数据，参数不能为空");
			throw new RuntimeException("更新索引数据，参数不能为空");
		}
		UpdateResponse updateResponse = client.prepareUpdate(index, type, id).setDoc(source).execute().actionGet();
		if (null != updateResponse) {
			logger.info("索引{}类型{}主键{}数据{}，更新索引数据成功", index, type, id, source);
		} else {
			logger.info("索引{}类型{}主键{}数据{}，更新索引数据失败", index, type, id, source);
		}
	}
	
	/**
	 * 删除索引数据
	 * 
	 * @param index
	 * @param type
	 * @param id
	 */
	public void deleteData(String index, String type, String id) {
		if (null == client || StringUtils.isEmpty(index) || StringUtils.isEmpty(type) || StringUtils.isEmpty(id)) {
			logger.error("删除索引数据，参数不能为空");
			throw new RuntimeException("删除索引数据，参数不能为空");
		}
		DeleteResponse deleteResponse = client.prepareDelete(index, type, id).execute().actionGet();
		if (null != deleteResponse && deleteResponse.isFound()) {
			logger.info("索引{}类型{}主键{}，删除索引数据成功", index, type, id);
		} else {
			logger.info("索引{}类型{}主键{}，删除索引数据失败", index, type, id);
		}
	}
	
	/**
	 * 批量操作索引数据
	 * 
	 * @param index
	 * @param type
	 * @param source
	 */
	public void prepareBulk(String index, String type, Map<String, Map<String, Object>> source) {
		if (null == client || StringUtils.isEmpty(index) || StringUtils.isEmpty(type) || MapUtils.isEmpty(source)) {
			logger.error("批量操作索引数据，参数不能为空");
			throw new RuntimeException("批量操作索引数据，参数不能为空");
		}
		// 批处理请求
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		// 批量添加索引数据库
		if (MapUtils.isNotEmpty(source)) {
			for (Entry<String, Map<String, Object>> entry : source.entrySet()) {
				GetResponse getResponse = client.prepareGet(index, type, entry.getKey()).execute().actionGet();
				if (null != getResponse && getResponse.isExists()) {
					bulkRequest.add(client.prepareUpdate(index, type, entry.getKey()).setDoc(entry.getValue()));
				} else {
					bulkRequest.add(client.prepareIndex(index, type, entry.getKey()).setSource(entry.getValue()));
				}
			}
			// 批量执行
			bulkRequest.execute().actionGet();
			logger.info("索引{}类型{}批量操作索引数据成功", index, type);
		}
	}
	
	/**
	 * 批量处理索引数据
	 * 
	 * @param index
	 * @param type
	 * @param insertSource
	 * @param updateSource
	 * @param deleteSource
	 */
	public void prepareBulk(String index, String type, Map<String, Map<String, Object>> insertSource, Map<String, Map<String, Object>> updateSource, List<String> deleteSource) {
		if (null == client || StringUtils.isEmpty(index) || StringUtils.isEmpty(type)) {
			logger.error("批量处理索引数据，参数不能为空");
			throw new RuntimeException("批量处理索引数据，参数不能为空");
		}
		// 批处理请求
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		boolean isExecute = false;
		// 批量添加索引数据库
		if (MapUtils.isNotEmpty(insertSource)) {
			isExecute = true;
			for (Entry<String, Map<String, Object>> entry : insertSource.entrySet()) {
				bulkRequest.add(client.prepareIndex(index, type, entry.getKey()).setSource(entry.getValue()));
			}
		}
		// 批量更新索引数据库
		if (MapUtils.isNotEmpty(updateSource)) {
			isExecute = true;
			for (Entry<String, Map<String, Object>> entry : updateSource.entrySet()) {
				bulkRequest.add(client.prepareUpdate(index, type, entry.getKey()).setDoc(entry.getValue()));
			}
		}
		// 批量删除索引数据库
		if (null != deleteSource) {
			isExecute = true;
			for (String id : deleteSource) {
				bulkRequest.add(client.prepareDelete(index, type, id));
			}
		}
		// 批量执行
		if (isExecute) {
			bulkRequest.execute().actionGet();
			logger.info("索引{}类型{}批量处理索引数据成功", index, type);
		}
	}
	
	/**
	 * 根据别名获取下一个索引名
	 * 
	 * @param index
	 * @param alias
	 * @return
	 */
	public String getNextIndex(String alias, String index) {
		String indexName = getAliasIndex(alias);
		if (StringUtils.isNotEmpty(indexName)) {
			String[] indexNames = indexName.split("_");
			if (indexNames.length > 1) {
				// 索引名称_(n+1)
				indexName = indexNames[0] + "_" + (Integer.parseInt(indexNames[1]) + 1);
			} else {
				// 索引名称_1
				indexName = indexNames[0] + "_1";
			}
			return indexName;
		}
		return index;
	}
	
	/**
	 * 根据别名获取索引名
	 * 
	 * @param index
	 * @param alias
	 * @return
	 */
	public String getIndex(String alias, String index) {
		String indexName = getAliasIndex(alias);
		if (StringUtils.isNotEmpty(indexName)) {
			return indexName;
		}
		return index;
	}
	

	public TransportClient getClient() {
		return client;
	}

	public void setClient(TransportClient client) {
		this.client = client;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}

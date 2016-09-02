package cn.com.gome.dujia.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.gome.dujia.disconf.CommonDisconf;
import cn.com.gome.dujia.es.ElasticSearchHandler;
import cn.com.gome.dujia.service.SearchService;
import cn.com.gome.dujia.vo.search.AutoResponse;
import cn.com.gome.dujia.vo.search.QueryRequest;
import cn.com.gome.dujia.vo.search.QueryResponse;

import com.gome.plan.tools.utils.JsonUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext*.xml"}) //用于指定配置文件所在的位置
public class SearchServiceImplTest {

	@Autowired
	private SearchService searchService;
	
	@Autowired
	private ElasticSearchHandler elasticSearchHandler;
	
	@Test
	public void createIndex() {
		String index = elasticSearchHandler.getNextIndex(CommonDisconf.zbyAlias, CommonDisconf.zbyIndex);
		// 创建索引
		boolean isSuccess = elasticSearchHandler.createIndex(index);
		if (isSuccess) {
			searchService.indexMapping();
			searchService.importFullData();
			elasticSearchHandler.indexBindAlias(index, CommonDisconf.zbyAlias);
		}
		
		/*// 索引名称
		String index = CommonDisconf.zbyIndex;
		// 类型名称
		String type = CommonDisconf.zbyType;
		
		Map<String, Object> source = new HashMap<String, Object>();
		source.put("cityName", "我爱北京天安门");

		// 添加数据
		elasticSearchHandler.insertData(index, type, "tc_58516", source);
		
		// 修改索引数据
		elasticSearchHandler.updateData(index, type, "tc_58516", source);
		
		// 删除索引数据
		elasticSearchHandler.deleteData(index, type, "tc_58516");*/
		
	}
	
	@Test
	public void list() {
		QueryRequest request = new QueryRequest();
		request.setCityPy("suzhou");
		//request.setKeyword("苏州");
		request.setDays(2);
		request.setTitle("kyhb");
		QueryResponse response = searchService.searchList(request);
		System.out.println("xxxxxxxxxxxxx:"+new JsonUtils().serialize(response));
	}
	
	@Test
	public void auto() {
		AutoResponse response = searchService.searchAuto("北京");
		System.out.println("xxxxxxxxxxxxx:"+new JsonUtils().serialize(response));
	}
	
	@Test
	public void batchIndexDate() {
		Map<String, Map<String, Object>> insertSource = new HashMap<String, Map<String,Object>>();
		Map<String, Object> insertMap = new HashMap<String, Object>();
		insertMap.put("mainName", "999感冒灵");
		insertSource.put("99999999", insertMap);
		
		Map<String, Map<String, Object>> updateSource = new HashMap<String, Map<String,Object>>(); 
		Map<String, Object> updateMap = new HashMap<String, Object>();
		updateMap.put("mainName", "98775---999感冒灵");
		updateSource.put("98775", updateMap);
		searchService.batchIndexData(insertSource);
	}
	
	@Test
	public void importIncrementData() {
		List<String> pids = new ArrayList<String>();
		pids.add("98775");
		pids.add("98777");
		pids.add("98778");
		pids.add("98786");
		pids.add("98787");
		searchService.importIncrementData(pids);
	}
}

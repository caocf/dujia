package cn.com.gome.dujia.task.test.es;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gome.plan.tools.utils.PinyinUtils;

import cn.com.gome.dujia.es.ElasticSearchHandler;
import cn.com.gome.dujia.service.SearchService;
import cn.com.gome.dujia.task.es.UpdateIndexTask;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext*.xml"}) //用于指定配置文件所在的位置
public class ImportIncrementData {

	@Autowired
	private SearchService searchService;
	
	@Autowired
	private UpdateIndexTask puIndexTask; 
	
	@Autowired
	private ElasticSearchHandler elasticSearchHandler;
	
	
	
	@Test
	public void test() throws Exception {
		/*List<String> pids = new ArrayList<>();
		pids.add("10860");
		pids.add("13848");
		pids.add("14349");
		
		searchService.importIncrementData(pids);*/
		
		puIndexTask.doExecute();
		
		/*Map<String, Map<String, Object>> sourceMap = new HashMap<String, Map<String,Object>>();
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("saleCount", 400);
		m.put("browseCount", 2500);
		List<Map<String, Object>> titleList = new ArrayList<Map<String, Object>>();
			Map<String, Object> recomInfoMap = new HashMap<String, Object>();
			recomInfoMap.put("titleName", "可用红包"); //主题名称
			recomInfoMap.put("titlePy", PinyinUtils.toPinyin("可用红包", true, true) + PinyinUtils.toPinyin("可用红包", false, true)); //主题拼音
			recomInfoMap.put("titlePyName", PinyinUtils.toPinyin("可用红包", true, true)+"-"+"可用红包"); //主题拼音简拼-名称
			titleList.add(recomInfoMap);
			Map<String, Object> recomInfoMap2 = new HashMap<String, Object>();
			recomInfoMap2.put("titleName", "游山玩水"); //主题名称
			recomInfoMap2.put("titlePy", PinyinUtils.toPinyin("游山玩水", true, true) + PinyinUtils.toPinyin("游山玩水", false, true)); //主题拼音
			recomInfoMap2.put("titlePyName", PinyinUtils.toPinyin("游山玩水", true, true)+"-"+"游山玩水"); //主题拼音简拼-名称
			titleList.add(recomInfoMap2);
		m.put("titles", titleList);
		sourceMap.put("46658", m);
		searchService.batchIndexData(sourceMap);*/
	}
}

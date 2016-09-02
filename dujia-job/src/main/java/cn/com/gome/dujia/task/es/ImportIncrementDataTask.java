package cn.com.gome.dujia.task.es;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.gome.dujia.service.SearchService;
import cn.com.gome.dujia.service.ZbyProductService;
import cn.com.gome.dujia.task.common.AbstractTask;

/**
 * 搜索导入增量数据 定时任务
 * 
 * @author xiongyan
 * @date 2016年5月9日 上午10:21:31
 */
@Component
public class ImportIncrementDataTask extends AbstractTask {
	
	private static final Logger logger = LoggerFactory.getLogger(ImportIncrementDataTask.class);

	@Autowired
	private ZbyProductService zbyProductService;
	
	@Autowired
	private SearchService searchService;
	
	/**
	 * 任务名称
	 */
	public String getTaskName() {
		return "搜索导入增量数据";
	}

	/**
	 * 执行任务
	 */
	public void doExecute() throws Exception {
		try {
			List<String> productIds = new ArrayList<String>();
			searchService.importIncrementData(productIds);
		} catch (Exception e) {
			logger.error("搜索导入增量数据失败", e);
		}
	}
	
}

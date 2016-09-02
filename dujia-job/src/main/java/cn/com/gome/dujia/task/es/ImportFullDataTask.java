package cn.com.gome.dujia.task.es;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.gome.dujia.disconf.CommonDisconf;
import cn.com.gome.dujia.es.ElasticSearchHandler;
import cn.com.gome.dujia.service.SearchService;
import cn.com.gome.dujia.service.ZbyProductService;
import cn.com.gome.dujia.task.common.AbstractTask;

/**
 * 搜索导入全量数据 定时任务
 * 
 * @author xiongyan
 * @date 2016年5月9日 上午10:21:31
 */
@Component
public class ImportFullDataTask extends AbstractTask {
	
	private static final Logger logger = LoggerFactory.getLogger(ImportFullDataTask.class);

	@Autowired
	private ZbyProductService zbyProductService;
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private ElasticSearchHandler elasticSearchHandler;
	
	/**
	 * 任务名称
	 */
	public String getTaskName() {
		return "搜索导入全量数据";
	}

	/**
	 * 执行任务
	 */
	public void doExecute() throws Exception {
		try {
			// 根据别名获取索引名称
			String index = elasticSearchHandler.getNextIndex(CommonDisconf.zbyAlias, CommonDisconf.zbyIndex);
			
			// 创建索引
			boolean isSuccess = elasticSearchHandler.createIndex(index);
			if (isSuccess) {
				searchService.indexMapping();
				searchService.importFullData();
				elasticSearchHandler.indexBindAlias(index, CommonDisconf.zbyAlias);
			}
			logger.info("搜索导入全量数据任务成功");
		} catch (Exception e) {
			logger.error("搜索导入全量数据任务失败", e);
		}
	}

}

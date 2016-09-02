package cn.com.gome.dujia.task.product;

import cn.com.gome.dujia.service.JobProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.com.gome.dujia.task.common.AbstractTask;

import javax.annotation.Resource;

/**
 * 每周更新一次
 * 
 * @author sunming
 * 
 */
@Component
public class AllProductUpdateTask extends AbstractTask {

	public static Logger logger = LoggerFactory.getLogger(AllProductUpdateTask.class);
	@Resource
	private JobProduct jobProduct;

	@Override
	public String getTaskName() {
		return "线路全量更新 ";
	}

	@Override
	public void doExecute() {
		try {
			jobProduct.allProductUpdate();
			logger.info("线路全量更新成功");
		} catch (Exception e) {
			logger.error("线路全量更新失败", e);
		}
	}

}

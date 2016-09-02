package cn.com.gome.dujia.task.product;

import cn.com.gome.dujia.service.JobProduct;
import cn.com.gome.dujia.task.common.AbstractTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 每10分钟更新一次，产品增量更新
 * 
 * @author sunming
 * 
 */
@Component
public class IncrementProductUpdateTask extends AbstractTask {

	public static Logger logger = LoggerFactory.getLogger(IncrementProductUpdateTask.class);
	@Resource
	private JobProduct jobProduct;

	@Override
	public String getTaskName() {
		return "线路增量更新 ";
	}

	@Override
	public void doExecute() {
		try {
			jobProduct.productIncrement();
			logger.info("线路增量更新成功");
		} catch (Exception e) {
			logger.error("线路增量更新失败", e);
		}
	}

}

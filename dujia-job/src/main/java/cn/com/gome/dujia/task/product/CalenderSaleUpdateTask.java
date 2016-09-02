package cn.com.gome.dujia.task.product;

import cn.com.gome.dujia.service.JobProduct;
import cn.com.gome.dujia.task.common.AbstractTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 每天 00:10:00 班期价格更新
 * 
 * @author sunming
 * 
 */
@Component
public class CalenderSaleUpdateTask extends AbstractTask {

	public static Logger logger = LoggerFactory.getLogger(CalenderSaleUpdateTask.class);
	@Resource
	private JobProduct jobProduct;

	@Override
	public String getTaskName() {
		return "班期价格更新";
	}

	@Override
	public void doExecute() {
		try {
			jobProduct.lineSaleInfoCalendarEveryDay();
			logger.info("班期价格更新成功");
		} catch (Exception e) {
			logger.error("班期价格更新失败", e);
		}
	}

}

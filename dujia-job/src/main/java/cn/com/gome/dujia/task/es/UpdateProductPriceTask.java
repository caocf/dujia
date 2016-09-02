package cn.com.gome.dujia.task.es;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.gome.dujia.mapper.business.ZbyProductMapper;
import cn.com.gome.dujia.model.ZbyProduct;
import cn.com.gome.dujia.service.SearchService;
import cn.com.gome.dujia.service.ZbyProductService;
import cn.com.gome.dujia.task.common.AbstractTask;

/**
 * 更新线路最低价格定时任务
 * 
 * @author xiongyan
 * @date 2016年5月9日 上午10:21:31
 */
@Component
public class UpdateProductPriceTask extends AbstractTask {
	
	private static final Logger logger = LoggerFactory.getLogger(UpdateProductPriceTask.class);

	@Autowired
	private ZbyProductMapper zbyProductMapper;
	
	@Autowired
	private ZbyProductService zbyProductService;
	
	@Autowired
	private SearchService searchService;
	
	/**
	 * 任务名称
	 */
	public String getTaskName() {
		return "更新线路最低价格";
	}

	/**
	 * 执行任务
	 */
	public void doExecute() throws Exception {
		try {
			// 查询线路
			List<ZbyProduct> products = zbyProductMapper.queryProductByPIds(null);
			if (CollectionUtils.isNotEmpty(products)) {
				// 批量处理数据
				Map<String, Map<String, Object>> sourceMap = new HashMap<String, Map<String, Object>>();
				
				// 当天线路最低价格
				Map<String, BigDecimal> productMinPriceMap = zbyProductService.queryProductPrice();
				
				// 导数据
				for (ZbyProduct product : products) {
					Map<String, Object> source = new HashMap<String, Object>();
					BigDecimal minPrice = productMinPriceMap.get(product.getProductId());
					source.put("productMinPrice", null != minPrice ? minPrice : product.getProductMinPrice()); //最小价格
					// 添加到map中
					sourceMap.put(product.getProductId(), source);
				}
				// 批量处理索引数据
				if (MapUtils.isNotEmpty(sourceMap)) {
					searchService.batchUpdateIndexData(sourceMap);
				}
				logger.info("更新线路最低价格成功");
			}
		} catch (Exception e) {
			logger.error("更新线路最低价格失败", e);
		}
	}
	
}

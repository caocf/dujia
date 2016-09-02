package cn.com.gome.dujia.task.es;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.gome.dujia.disconf.CommonDisconf;
import cn.com.gome.dujia.enums.TrueFalseStatus;
import cn.com.gome.dujia.model.ZbyProduct;
import cn.com.gome.dujia.model.ZbyRecomInfo;
import cn.com.gome.dujia.service.SearchService;
import cn.com.gome.dujia.service.ZbyProductService;
import cn.com.gome.dujia.service.ZbyRecomInfoService;
import cn.com.gome.dujia.task.common.AbstractTask;

import com.gome.plan.tools.curator.zkconfig.ZkConfigString;
import com.gome.plan.tools.curator.zkconfig.ZkConfigType;
import com.gome.plan.tools.utils.DateUtils;
import com.gome.plan.tools.utils.PinyinUtils;
import com.gome.plan.tools.utils.StringUtils;

/**
 * 更新索引定时任务
 * 
 * @author xiongyan
 * @date 2016年5月9日 上午10:21:31
 */
@Component
public class UpdateIndexTask extends AbstractTask {
	
	private static final Logger logger = LoggerFactory.getLogger(UpdateIndexTask.class);

	@Autowired
	private ZbyProductService zbyProductService;
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private ZbyRecomInfoService zbyRecomInfoService;
	
	/**
	 * zkconfig
	 */
	private static ZkConfigString zkConfig;
	
	/**
	 * 任务名称
	 */
	public String getTaskName() {
		return "更新索引";
	}

	/**
	 * 执行任务
	 */
	public void doExecute() throws Exception {
		try {
			// 当前时间
			Date nowTime = new Date();
			// 最后更新时间
			Date lastUpdateTime = getLastUpdateTime();
			// 资源信息
			Map<String, Map<String, Object>> sourceMap = new HashMap<String, Map<String,Object>>();
			
			// 查询线路销量，浏览量
			ZbyProduct product = new ZbyProduct();
			product.setUpdateTime(lastUpdateTime); //最后更新时间
			product.setIsDelete(TrueFalseStatus.FALSE.getValue()); //未删除
			List<ZbyProduct> products = zbyProductService.querySaleBrowseCount(product);
			if (null != product) {
				// 更新销量 浏览量
				for (ZbyProduct zbyProduct :products) {
					Map<String, Object> source = new HashMap<String, Object>();
					source.put("saleCount", null == zbyProduct.getSaleCount() ? 0 : zbyProduct.getSaleCount()); // 销量
					source.put("browseCount", null == zbyProduct.getBrowseCount() ? 0 : zbyProduct.getBrowseCount()); // 浏览量
					sourceMap.put(zbyProduct.getProductId(), source);
				}
			}
			
			// 查询更新的主题
			ZbyRecomInfo recomInfo = new ZbyRecomInfo();
			recomInfo.setUpdateTime(lastUpdateTime); //最后更新时间
			List<ZbyRecomInfo> recomInfos = zbyRecomInfoService.queryUpdateRecomInfoList(recomInfo);
			if (null != recomInfos && recomInfos.size() > 0) {
				Map<String, List<ZbyRecomInfo>> mapList = new HashMap<String, List<ZbyRecomInfo>>();
				for (ZbyRecomInfo recom : recomInfos) {
					List<ZbyRecomInfo> list = mapList.get(recom.getProductId());
					if (null == list) {
						list = new ArrayList<ZbyRecomInfo>();
						mapList.put(recom.getProductId(), list);
					}
					list.add(recom);
				}
				
				for (Entry<String, List<ZbyRecomInfo>> entry : mapList.entrySet()) {
					List<Map<String, Object>> titleList = new ArrayList<Map<String, Object>>();
					for (ZbyRecomInfo zpi : entry.getValue()) {
						if (!zpi.getIsDelete()) {
							Map<String, Object> recomInfoMap = new HashMap<String, Object>();
							recomInfoMap.put("titleName", zpi.getTitle()); //主题名称
							recomInfoMap.put("titlePy", PinyinUtils.toPinyin(zpi.getTitle(), true, true) + PinyinUtils.toPinyin(zpi.getTitle(), false, true)); //主题拼音
							recomInfoMap.put("titlePyName", PinyinUtils.toPinyin(zpi.getTitle(), true, true)+"-"+zpi.getTitle()); //主题拼音简拼-名称
							titleList.add(recomInfoMap);
						}
					}
					
					Map<String, Object> source = sourceMap.get(entry.getKey());
					if (null == source) {
						source = new HashMap<String, Object>();
					}
					source.put("titles", titleList);
					sourceMap.put(entry.getKey(), source);
				}
			}
			
			// 批量处理索引数据
			logger.info("更新索引{}", sourceMap);
			if (MapUtils.isNotEmpty(sourceMap)) {
				searchService.batchUpdateIndexData(sourceMap);
			}
			
			// 当前时间设置为最后更新时间
			setLastUpdateTime(nowTime);
			logger.info("更新索引成功");
		} catch (Exception e) {
			logger.error("更新索引失败", e);
		}
	}
	
	/**
	 * 获取最后更新时间
	 * 
	 * @return
	 * @throws ParseException
	 */
	private Date getLastUpdateTime() throws ParseException {
		if (null == zkConfig) {
			initZkConfig();
		}
		String lastUpdateTime = zkConfig.getConf();
		Date updateTime = null;
		if (StringUtils.isNotEmpty(lastUpdateTime)) {
			updateTime = DateUtils.parse(lastUpdateTime, DateUtils.LONG_WEB_FORMAT);
		} else {
			updateTime = new Date();
		}
		return updateTime;
	}
	
	/**
	 * 设置最后更新时间
	 * 
	 * @param lastUpdateTime
	 * @throws Exception
	 */
	private void setLastUpdateTime(Date lastUpdateTime) throws Exception {
		if (null == zkConfig) {
			initZkConfig();
		}
		zkConfig.update(DateUtils.format(lastUpdateTime, DateUtils.LONG_WEB_FORMAT));
	}

	/**
	 * 初始化zkconfig
	 */
	private void initZkConfig() {
		if (null == zkConfig) {
			UpdateIndexTask.zkConfig = new ZkConfigString(CommonDisconf.zookeeperAddress, CommonDisconf.businessName, "es.last.update.time", ZkConfigType.TXT);
		}
	}

}

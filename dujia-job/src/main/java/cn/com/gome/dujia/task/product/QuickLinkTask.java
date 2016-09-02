package cn.com.gome.dujia.task.product;

import cn.com.gome.dujia.dto.ZbyCityDto;
import cn.com.gome.dujia.enums.PlatformType;
import cn.com.gome.dujia.service.ZbyCityService;
import cn.com.gome.dujia.service.ZbyProductService;
import cn.com.gome.dujia.task.common.AbstractTask;
import cn.com.gome.dujia.task.order.PushVenderTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 将各城市的首页浮层数据缓存任务
 * Created by zhaoxiang-ds on 2016/5/24.
 */
@Component
public class QuickLinkTask extends AbstractTask {
	
	private static final Logger logger = LoggerFactory.getLogger(QuickLinkTask.class);

    @Autowired
    private ZbyProductService ZbyProductService;

    @Autowired
    private ZbyCityService cityService;

    @Override
    public String getTaskName() {
        return "刷新首页浮层缓存";
    }

    @Override
    public void doExecute() {
    	try {
    		// 获取全部可定位的城市列表
    		List<ZbyCityDto> list = cityService.queryAllEffectiveCity();
    		if (list != null) {
    			// 获取城市的浮层数据并缓存
    			for (ZbyCityDto c : list) {
    				if (c.getId() != null) {
    					ZbyProductService.getQuickLink(PlatformType.WEB.getValue(), c.getId());
    				}
    			}
    			logger.info("刷新首页浮层缓存成功");
    		}
    	} catch (Exception e) {
			logger.error("刷新首页浮层缓存失败", e);
		}
    }
}

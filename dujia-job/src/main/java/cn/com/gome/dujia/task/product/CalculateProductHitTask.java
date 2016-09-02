package cn.com.gome.dujia.task.product;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import cn.com.gome.dujia.constant.Constants;
import cn.com.gome.dujia.mapper.business.ZbyProductMapper;
import cn.com.gome.dujia.model.ZbyProduct;
import cn.com.gome.dujia.service.RedisCacheService;
import cn.com.gome.dujia.task.common.AbstractTask;

/**
 * Description : 统计票品点击数(PV)
 */
@Component
public class CalculateProductHitTask extends AbstractTask {

    private static final Logger log = LoggerFactory.getLogger(CalculateProductHitTask.class);

    @Autowired
    private RedisCacheService cacheService;

    @Autowired
    private ZbyProductMapper productMapper;

    @Override
    public String getTaskName() {
        return "统计线路浏览量(PV)";
    }

    @Override
    public void doExecute() {
        try {
            // 缓存取值
            Map<String, String> hitMap = cacheService.getMap(Constants.PRODUCT_HIT);
            log.info("统计线路浏览量(PV){}", hitMap);
            if (!CollectionUtils.isEmpty(hitMap)) {
                List<ZbyProduct> products = new ArrayList<>();
                for (String pid : hitMap.keySet()) {
                    ZbyProduct product = new ZbyProduct();
                    product.setProductId(pid);
                    product.setBrowseCount(Integer.parseInt(hitMap.get(pid)));
                    products.add(product);
                }
                // 批量更新
                productMapper.batchUpdateSBCount(products);
                //清空缓存
                cacheService.evict(Constants.PRODUCT_HIT);
                log.info("统计线路浏览量(PV)成功");
            }
        } catch (Exception e) {
            log.error("统计线路浏览量(PV)失败", e);
        }
    }

}

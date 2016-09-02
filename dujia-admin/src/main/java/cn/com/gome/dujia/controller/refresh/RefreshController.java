package cn.com.gome.dujia.controller.refresh;

import cn.com.gome.dujia.controller.venue.CommonController;
import cn.com.gome.dujia.disconf.CommonDisconf;
import cn.com.gome.dujia.es.ElasticSearchHandler;
import cn.com.gome.dujia.mapper.business.OrderMapper;
import cn.com.gome.dujia.model.Order;
import cn.com.gome.dujia.service.JobProduct;
import cn.com.gome.dujia.service.ProductCacheService;
import cn.com.gome.dujia.service.SearchService;
import cn.com.gome.dujia.thread.ThreadPoolUtil;
import com.gome.plan.tools.http.HttpClientUtils;
import com.gome.plan.tools.http.RequestPackage;
import com.gome.plan.tools.http.ResponsePackage;
import com.gome.plan.tools.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by liuhexin on 2016/6/3.
 */
@Controller
@RequestMapping(value = "/admin/refresh")
public class RefreshController extends CommonController {
    public static final Logger logger = LoggerFactory.getLogger(RefreshController.class);

    @Autowired
    private SearchService searchService;
    @Autowired
    private ElasticSearchHandler elasticSearchHandler;
    @Autowired
    private ProductCacheService pcService;

    @Autowired
    private JobProduct jobProduct;
    @Autowired
    private OrderMapper orderMapper;


    @RequestMapping(value = "/refresh")
    public String refresh() {
        return "/refresh/refresh";
    }


    @RequestMapping(value = "/refreshES")
    public void refreshES(HttpServletResponse response, Model model) {
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
            writeResult(response, "true");
        } catch (Exception e) {
            writeResult(response, "false");
        }
    }


    @RequestMapping(value = "/refreshData")
    public void refreshData(HttpServletResponse response, Model model) {
        try {
            ThreadPoolExecutor poll = ThreadPoolUtil.getInstance();
            poll.execute(new Runnable() {
                @Override
                public void run() {
                    jobProduct.allProductUpdate();
                }
            });
            writeResult(response, "true");
        } catch (Exception e) {
            writeResult(response, "false");
        }
    }

    /**
     * 更新首页浮层数据
     */
    @RequestMapping(value = "/qlc")
    public void refreshQuickLinkCache(HttpServletResponse response, Model model) {
        try {
            pcService.updateQuickLinkCache();
            writeResult(response, "true");
        } catch (Exception e) {
            writeResult(response, "false");
        }
    }

    /**
     * 更新首页周边去哪数据
     */
    @RequestMapping(value = "/wkc")
    public void refreshWeekendCache(HttpServletResponse response, Model model) {
        try {
            pcService.updateWeekendCache();
            writeResult(response, "true");
        } catch (Exception e) {
            writeResult(response, "false");
        }
    }

    /**
     * 更新首页楼层推荐
     */
    @RequestMapping(value = "/rir")
    public void refreshIndexRecommend(HttpServletResponse response, Model model) {
        try {
            pcService.updateRecommendCache();
            writeResult(response, "true");
        } catch (Exception e) {
            writeResult(response, "false");
        }
    }

    @RequestMapping(value = "/refund")
    public void refreshRefund(HttpServletResponse response, String orderId) {
        try {
            Order orderInfoObj = new Order();
            orderInfoObj.setOrderId(orderId);
            orderInfoObj.setOrderPay(1);
            Order order = orderMapper.selectOne(orderInfoObj);
            if (null != order) {
                RequestPackage pp = new RequestPackage();
                pp.setUrl("http://10.58.62.136:3133/order/notice");
                Map<String, String> postData = new HashMap<String, String>();
                postData.put("OperateType", "Refund");
                postData.put("orderId", order.getVenderOrderId());
                postData.put("OrderFlag", "true");
                postData.put("RefundNumber", DateUtils.format(new Date(), DateUtils.LONG_FORMAT));
                postData.put("RefundAmount", order.getOrderAmount().toString());
                pp.setPostData(postData);
                pp.setMethod("POST");
                ResponsePackage resp = HttpClientUtils.exec(pp);
                if (null != resp && 200 == resp.getHttpStatus()) {
                    writeResult(response, "true");
                }
            }
        } catch (Exception e) {

        }
        writeResult(response, "false");
    }
}

package cn.com.gome.dujia.service.impl.biz;

import cn.com.gome.dujia.constant.Constants;
import cn.com.gome.dujia.enums.PayStatus;
import cn.com.gome.dujia.mapper.business.OrderMapper;
import cn.com.gome.dujia.mapper.business.ZbyProductMapper;
import cn.com.gome.dujia.mapper.business.ZbyProductPackageMapper;
import cn.com.gome.dujia.model.Order;
import cn.com.gome.dujia.model.ZbyProduct;
import cn.com.gome.dujia.model.ZbyProductPackage;
import cn.com.gome.dujia.service.RedisCacheService;
import cn.com.gome.dujia.service.biz.ProductBizService;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.gome.plan.tools.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;

/**
 * 票品业务Service实现
 */
@Service
@Path("productBiz")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class ProductBizServiceImpl implements ProductBizService {

    private static final Logger log = LoggerFactory.getLogger(ProductBizServiceImpl.class);


    @Autowired
    private RedisCacheService cacheService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ZbyProductMapper productMapper;

    @Autowired
    private ZbyProductPackageMapper packageMapper;

    @Override
    public void addSaleCountForProduct(String orderId) {
        try {
            if (StringUtils.isNotEmpty(orderId)) {
                Order order = orderMapper.queryOrderInfo(orderId);
                if (order != null) {
                    // 只有已支付的订单、才能增加票品的销售数量
                    if (PayStatus.PAY_SUCCESS.getValue().equals(order.getOrderPay())) {
                        // 更新线路销量
                        ZbyProduct product = new ZbyProduct();
                        product.setProductId(order.getProductId());
                        product.setSaleCount(order.getBuyCount());
                        productMapper.batchUpdateSBCount(Arrays.asList(product));

                        // 更新套餐销量
                        ZbyProductPackage productPackage = new ZbyProductPackage();
                        productPackage.setProductId(order.getProductId());
                        productPackage.setPackageId(order.getPackageId());
                        productPackage.setSaleCount(order.getBuyCount());
                        packageMapper.updateSellCount(productPackage);
                    }
                }
            }
        } catch (Exception e) {
            log.error("记录线路销售量失败", e);
        }
    }

    @GET
    @Path("addPVForProduct/{productId}")
    @Override
    public void addPVForProduct(@PathParam("productId") String productId) {
        try {
            if (StringUtils.isNotEmpty(productId)) {
                cacheService.hincrBy(Constants.PRODUCT_HIT, productId, 1);
            }
        } catch (Exception e) {
            log.error("记录线路浏览量失败", e);
        }
    }

}

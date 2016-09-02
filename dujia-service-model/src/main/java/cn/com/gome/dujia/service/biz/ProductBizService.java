package cn.com.gome.dujia.service.biz;

/**
 *  Description : 票品业务Service
 */
public interface ProductBizService {

    /**
     * 下单成功后，添加线路和套餐的销售数量
     *
     * @param orderId
     */
    public void addSaleCountForProduct(String orderId);

    /**
     * 增加线路点击数(PV)
     *
     * @param productId
     * @return
     */
    public void addPVForProduct(String productId);

}

package cn.com.gome.dujia.vo.order;

import cn.com.gome.dujia.model.Order;
import org.elasticsearch.search.aggregations.metrics.geobounds.InternalGeoBounds;
import org.springframework.scheduling.quartz.SimpleTriggerBean;

import java.util.Date;

/**
 * Created by liuhexin on 2016/4/25.
 */
public class OrderQueryInfo extends Order {

    /**
     * 下单开始时间
     */
    private Date orderStartTime;
    /**
     * 下单结束时间
     */
    private Date orderEndTime;
    /**
     * 支付开始时间
     */
    private Date payStartTime;
    /**
     * 支付结束时间
     */
    private Date payEndTime;
    /**
     * 线路名称
     */
    private String productName;
    /**
     * 景点名称
     */
    private String packageName;
    /**
     * 景点所在城市ID
     */
    private Integer packageCityID;
    /**
     * 景点所在城市名称
     */
    private String packageCityName;
    /**
     * 订单状态
     */
    private Integer queryOrderStatus;
    /**
     * 支付状态
     */
    private Integer payStatus;
    /**
     * 主题标签
     */
    private String recomTitle;
    /**
     * 游玩天数
     */
    private Integer productDays;

    /**
     * 订单状态显示值
     */
    private String orderStatusDisplay;

    /**
     * 游玩天数显示值
     */
    private String travelDayDisplay;

    /**
     * 支付供应商交易流水号
     */
    private String merchantNo;

    /**
     * 支付订单号
     */
    private String transNo;

    /**
     * SAP类型(1sap正向收款，2sap正向收入，3sap逆向收款，4sap逆向收入),所属表字段为st_push_sap.sap_type
     */
    private Integer sapType;

    public Date getOrderStartTime() {
        return orderStartTime;
    }

    public void setOrderStartTime(Date orderStartTime) {
        this.orderStartTime = orderStartTime;
    }

    public Date getOrderEndTime() {
        return orderEndTime;
    }

    public void setOrderEndTime(Date orderEndTime) {
        this.orderEndTime = orderEndTime;
    }

    public Date getPayStartTime() {
        return payStartTime;
    }

    public void setPayStartTime(Date payStartTime) {
        this.payStartTime = payStartTime;
    }

    public Date getPayEndTime() {
        return payEndTime;
    }

    public void setPayEndTime(Date payEndTime) {
        this.payEndTime = payEndTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getPackageCityID() {
        return packageCityID;
    }

    public void setPackageCityID(Integer packageCityID) {
        this.packageCityID = packageCityID;
    }

    public String getPackageCityName() {
        return packageCityName;
    }

    public void setPackageCityName(String packageCityName) {
        this.packageCityName = packageCityName;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getProductDays() {
        return productDays;
    }

    public void setProductDays(Integer productDays) {
        this.productDays = productDays;
    }

    public Integer getQueryOrderStatus() {
        return queryOrderStatus;
    }

    public void setQueryOrderStatus(Integer queryOrderStatus) {
        this.queryOrderStatus = queryOrderStatus;
    }

    public String getRecomTitle() {
        return recomTitle;
    }

    public void setRecomTitle(String recomTitle) {
        this.recomTitle = recomTitle;
    }

    public String getOrderStatusDisplay() {
        return orderStatusDisplay;
    }

    public void setOrderStatusDisplay(String orderStatusDisplay) {
        this.orderStatusDisplay = orderStatusDisplay;
    }

    public String getTravelDayDisplay() {
        return travelDayDisplay;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public void setTravelDayDisplay(String travelDayDisplay) {
        this.travelDayDisplay = travelDayDisplay;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public Integer getSapType() {
        return sapType;
    }

    public void setSapType(Integer sapType) {
        this.sapType = sapType;
    }
}

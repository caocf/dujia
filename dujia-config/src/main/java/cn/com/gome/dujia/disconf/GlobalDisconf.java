package cn.com.gome.dujia.disconf;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;

/**
 * 全局 配置信息
 * 
 * @author xiongyan
 * @date 2016年4月14日 上午10:02:35
 */
@DisconfFile(filename = "global.properties")
public final class GlobalDisconf {

	//同程接口地址
	public static String tcPrefixUrl;
	
	//同程接口验签
	public static String tcAccessId;
	
	//同程接口秘钥
	public static String tcSecurityId;
	
	//供应商编号
	public static String venderId;
	
	//缓存业务名称
	public static String cacheBusiness;
	
	//是否启用缓存
	public static Boolean cacheEnable;
	
	//提醒支付短信模板
	public static String smsDiffidPayRemind;
	public static String smsDiffidPayRemindTemp;
	
	//超时取消短信模板
	public static String smsDiffidOutTimeCancel;
	public static String smsDiffidOutTimeCancelTemp;
	
	//供应商退款短信模板
	public static String smsDiffidVenderPayRefund;
	public static String smsDiffidVenderPayRefundTemp;
	
	//报警短信模板
    public static String smsDiffidAlarm;
    public static String smsDiffidAlarmTemp;

    //发短信频率限制次数
    public static String smsFrequencyLimitCount; 

    //发短信频率限制时间 （秒）
    public static String smsFrequencyLimitTime;
	
    //SAP 收款 WSDL 地址
    public static String sapPayAddress;

    //SAP 收款 用户名
    public static String sapPayUsername;

    //SAP 收款 密码
    public static String sapPayPassword;

    //SAP 收入 WSDL 地址
    public static String sapIncomeAddress;

    //SAP 收入 用户名
    public static String sapIncomeUsername;

    //SAP 收入 密码
    public static String sapIncomePassword;
    
    //短信接口响应状态码
    public static String smsResult;
    
    //SAP收款接口响应状态码
    public static String sapPayResult;

    //SAP收入接口响应状态码
    public static String sapIncomeResult;
    
    //SAP订单类型正向
    public static String sapOrderTypeSO;

    //SAP订单类型逆向
    public static String sapOrderTypeRO;
    
    //店铺编号
    public static String sapShopNum;

    //商品品类
    public static String sapProductCategory;

    //商品品牌
    public static String sapProductBrand;

    //正常收款
    public static String sapPositivePay;

    //异常收款
    public static String sapAbnormalPay;

    //原路退回
    public static String sapNegativePay;

    //销售渠道类型
    public static String sapSaleChannelType;

    //佣金方案
    public static String sapCommission;

    //价格类型(销售单价)
    public static String sapSalePrice;

    //价格类型(应收金额 )
    public static String sapReceiveAmount;

    //站点名称
    public static String sapSkuName;
    
    //rb地址
    public static String rbAddress;
    
    //rb机密key
    public static String rbMd5Key;
    
    //收银台商户号
    public static String siteAccount;
    
    //收银台支付请求接口地址 
    public static String payUrl;
    
    //收银台支付查询接口地址
    public static String payQueryUrl;
    
    //收银台支付MD5秘钥key
    public static String payMd5Key;
    
    //收银台支付AES秘钥key
    public static String payAesKey;
    
    //支付前缀
    public static String payPrefix;
    
    //订单来源web
    public static String orderSourceWeb;
    
    //订单来源app 
    public static String orderSourceApp;
    
    //#订单来源wap
    public static String orderSourceWap;
    
    //订单类型
    public static String orderType;

    //获取版期价格后几天
	public static Integer calendarAfterDays;
	
	//订单号前缀
	public static String orderPrefix;
		
	//退款单号前缀
    public static String refundPrefix;
    
    //推送sap最大次数
    public static Integer pushSapMaxNumber;
    
    //推送供应商最大次数
    public static Integer pushVenderMaxNumber;
    
    //下单锁定时间
    public static String orderLockTime;
    
    //下单提醒时间
    public static String orderRemindTime;

	//初始化流量数量
	public static Integer browseCount;

	//下单提示信息
	public static String ticketErrorMsg;
	
	//订单开关
	public static Boolean orderSwitch;
	
	//错误页面 
	public static String errorPage; 
	
	//错误信息 
	public static String errorMessage;

	//上传失败后的默认图片
	public static String defaultImage;
	
	//收银台回调地址
	public static String pageBackUrl;
	
	//点对点回调地址
	public static String notifyUrl;
	
	//订单每页个数
	public static String orderListSize;

    //环境
    public static String env;

	//job监控zk节点
	public static String jobMonNode;

    //资源环境
    public static String resourceEnv;
    
    //静态资源版本
    public static String resourceVersion;
    
    //度假域名 
    public static String domainDujia;

    //旅行首页域名
    public static String domainLvxing;

    //机票域名
    public static String domainJipiao;

    //火车票域名
    public static String domainTrain;

    //酒店域名
    public static String domainHotel;

    //业务报警短信
    public static String businessWarnPhones;
    
    //登录地址
    public static String loginUrl;


    @DisconfFileItem(name = "login_url")
    public static String getLoginUrl() {
		return loginUrl;
	}

	public static void setLoginUrl(String loginUrl) {
		GlobalDisconf.loginUrl = loginUrl;
	}

	@DisconfFileItem(name = "resource_version")
	public static String getResourceVersion() {
		return resourceVersion;
	}

	public static void setResourceVersion(String resourceVersion) {
		GlobalDisconf.resourceVersion = resourceVersion;
	}

	@DisconfFileItem(name = "default_image")
	public static String getDefaultImage() {
		return defaultImage;
	}

	public static void setDefaultImage(String defaultImage) {
		GlobalDisconf.defaultImage = defaultImage;
	}

	@DisconfFileItem(name = "order_switch")
	public static Boolean getOrderSwitch() {
		return orderSwitch;
	}

	public static void setOrderSwitch(Boolean orderSwitch) {
		GlobalDisconf.orderSwitch = orderSwitch;
	}

	@DisconfFileItem(name = "error_page")
	public static String getErrorPage() {
		return errorPage;
	}

	public static void setErrorPage(String errorPage) {
		GlobalDisconf.errorPage = errorPage;
	}

	@DisconfFileItem(name = "error_message")
	public static String getErrorMessage() {
		return errorMessage;
	}

	public static void setErrorMessage(String errorMessage) {
		GlobalDisconf.errorMessage = errorMessage;
	}

	@DisconfFileItem(name = "browse_count")
	public static Integer getBrowseCount() {
		return browseCount;
	}

	public static void setBrowseCount(Integer browseCount) {
		GlobalDisconf.browseCount = browseCount;
	}

	@DisconfFileItem(name = "calendar_after_days")
	public static Integer getCalendarAfterDays() {
		return calendarAfterDays;
	}

	public static void setCalendarAfterDays(Integer calendarAfterDays) {
		GlobalDisconf.calendarAfterDays = calendarAfterDays;
	}
	
	@DisconfFileItem(name = "tc_prefix_url")
	public static String getTcPrefixUrl() {
		return tcPrefixUrl;
	}

	public static void setTcPrefixUrl(String tcPrefixUrl) {
		GlobalDisconf.tcPrefixUrl = tcPrefixUrl;
	}

	@DisconfFileItem(name = "tc_access_id")
	public static String getTcAccessId() {
		return tcAccessId;
	}

	public static void setTcAccessId(String tcAccessId) {
		GlobalDisconf.tcAccessId = tcAccessId;
	}

	@DisconfFileItem(name = "tc_security_id")
	public static String getTcSecurityId() {
		return tcSecurityId;
	}

	public static void setTcSecurityId(String tcSecurityId) {
		GlobalDisconf.tcSecurityId = tcSecurityId;
	}

	@DisconfFileItem(name = "vender_id")
	public static String getVenderId() {
		return venderId;
	}

	public static void setVenderId(String venderId) {
		GlobalDisconf.venderId = venderId;
	}

	@DisconfFileItem(name = "cache_business")
	public static String getCacheBusiness() {
		return cacheBusiness;
	}

	public static void setCacheBusiness(String cacheBusiness) {
		GlobalDisconf.cacheBusiness = cacheBusiness;
	}

	@DisconfFileItem(name = "cache_enable")
	public static Boolean getCacheEnable() {
		return cacheEnable;
	}

	public static void setCacheEnable(Boolean cacheEnable) {
		GlobalDisconf.cacheEnable = cacheEnable;
	}
	
	@DisconfFileItem(name = "sap_pay_address")
	public static String getSapPayAddress() {
		return sapPayAddress;
	}

	public static void setSapPayAddress(String sapPayAddress) {
		GlobalDisconf.sapPayAddress = sapPayAddress;
	}

	@DisconfFileItem(name = "sap_pay_username")
	public static String getSapPayUsername() {
		return sapPayUsername;
	}

	public static void setSapPayUsername(String sapPayUsername) {
		GlobalDisconf.sapPayUsername = sapPayUsername;
	}

	@DisconfFileItem(name = "sap_pay_password")
	public static String getSapPayPassword() {
		return sapPayPassword;
	}

	public static void setSapPayPassword(String sapPayPassword) {
		GlobalDisconf.sapPayPassword = sapPayPassword;
	}

	@DisconfFileItem(name = "sap_income_address")
	public static String getSapIncomeAddress() {
		return sapIncomeAddress;
	}

	public static void setSapIncomeAddress(String sapIncomeAddress) {
		GlobalDisconf.sapIncomeAddress = sapIncomeAddress;
	}

	@DisconfFileItem(name = "sap_income_username")
	public static String getSapIncomeUsername() {
		return sapIncomeUsername;
	}

	public static void setSapIncomeUsername(String sapIncomeUsername) {
		GlobalDisconf.sapIncomeUsername = sapIncomeUsername;
	}

	@DisconfFileItem(name = "sap_income_password")
	public static String getSapIncomePassword() {
		return sapIncomePassword;
	}

	public static void setSapIncomePassword(String sapIncomePassword) {
		GlobalDisconf.sapIncomePassword = sapIncomePassword;
	}

	@DisconfFileItem(name = "sms_result")
	public static String getSmsResult() {
		return smsResult;
	}

	public static void setSmsResult(String smsResult) {
		GlobalDisconf.smsResult = smsResult;
	}

	@DisconfFileItem(name = "sap_pay_result")
	public static String getSapPayResult() {
		return sapPayResult;
	}

	public static void setSapPayResult(String sapPayResult) {
		GlobalDisconf.sapPayResult = sapPayResult;
	}

	@DisconfFileItem(name = "sap_income_result")
	public static String getSapIncomeResult() {
		return sapIncomeResult;
	}

	public static void setSapIncomeResult(String sapIncomeResult) {
		GlobalDisconf.sapIncomeResult = sapIncomeResult;
	}

	@DisconfFileItem(name = "sap_order_type_so")
	public static String getSapOrderTypeSO() {
		return sapOrderTypeSO;
	}

	public static void setSapOrderTypeSO(String sapOrderTypeSO) {
		GlobalDisconf.sapOrderTypeSO = sapOrderTypeSO;
	}

	@DisconfFileItem(name = "sap_order_type_ro")
	public static String getSapOrderTypeRO() {
		return sapOrderTypeRO;
	}

	public static void setSapOrderTypeRO(String sapOrderTypeRO) {
		GlobalDisconf.sapOrderTypeRO = sapOrderTypeRO;
	}

	@DisconfFileItem(name = "sap_shop_num")
	public static String getSapShopNum() {
		return sapShopNum;
	}

	public static void setSapShopNum(String sapShopNum) {
		GlobalDisconf.sapShopNum = sapShopNum;
	}

	@DisconfFileItem(name = "sap_product_category")
	public static String getSapProductCategory() {
		return sapProductCategory;
	}

	public static void setSapProductCategory(String sapProductCategory) {
		GlobalDisconf.sapProductCategory = sapProductCategory;
	}

	@DisconfFileItem(name = "sap_product_brand")
	public static String getSapProductBrand() {
		return sapProductBrand;
	}

	public static void setSapProductBrand(String sapProductBrand) {
		GlobalDisconf.sapProductBrand = sapProductBrand;
	}

	@DisconfFileItem(name = "sap_positive_pay")
	public static String getSapPositivePay() {
		return sapPositivePay;
	}

	public static void setSapPositivePay(String sapPositivePay) {
		GlobalDisconf.sapPositivePay = sapPositivePay;
	}

	@DisconfFileItem(name = "sap_abnormal_pay")
	public static String getSapAbnormalPay() {
		return sapAbnormalPay;
	}

	public static void setSapAbnormalPay(String sapAbnormalPay) {
		GlobalDisconf.sapAbnormalPay = sapAbnormalPay;
	}

	@DisconfFileItem(name = "sap_negative_pay")
	public static String getSapNegativePay() {
		return sapNegativePay;
	}

	public static void setSapNegativePay(String sapNegativePay) {
		GlobalDisconf.sapNegativePay = sapNegativePay;
	}

	@DisconfFileItem(name = "sap_sale_channel_type")
	public static String getSapSaleChannelType() {
		return sapSaleChannelType;
	}

	public static void setSapSaleChannelType(String sapSaleChannelType) {
		GlobalDisconf.sapSaleChannelType = sapSaleChannelType;
	}

	@DisconfFileItem(name = "sap_commission")
	public static String getSapCommission() {
		return sapCommission;
	}

	public static void setSapCommission(String sapCommission) {
		GlobalDisconf.sapCommission = sapCommission;
	}

	@DisconfFileItem(name = "sap_sale_price")
	public static String getSapSalePrice() {
		return sapSalePrice;
	}

	public static void setSapSalePrice(String sapSalePrice) {
		GlobalDisconf.sapSalePrice = sapSalePrice;
	}

	@DisconfFileItem(name = "sap_receive_amount")
	public static String getSapReceiveAmount() {
		return sapReceiveAmount;
	}

	public static void setSapReceiveAmount(String sapReceiveAmount) {
		GlobalDisconf.sapReceiveAmount = sapReceiveAmount;
	}

	@DisconfFileItem(name = "rb_address")
	public static String getRbAddress() {
		return rbAddress;
	}

	public static void setRbAddress(String rbAddress) {
		GlobalDisconf.rbAddress = rbAddress;
	}

	@DisconfFileItem(name = "rb_md5_key")
	public static String getRbMd5Key() {
		return rbMd5Key;
	}

	public static void setRbMd5Key(String rbMd5Key) {
		GlobalDisconf.rbMd5Key = rbMd5Key;
	}

	@DisconfFileItem(name = "site_account")
	public static String getSiteAccount() {
		return siteAccount;
	}

	public static void setSiteAccount(String siteAccount) {
		GlobalDisconf.siteAccount = siteAccount;
	}

	@DisconfFileItem(name = "pay_url")
	public static String getPayUrl() {
		return payUrl;
	}

	public static void setPayUrl(String payUrl) {
		GlobalDisconf.payUrl = payUrl;
	}

	@DisconfFileItem(name = "pay_query_url")
	public static String getPayQueryUrl() {
		return payQueryUrl;
	}

	public static void setPayQueryUrl(String payQueryUrl) {
		GlobalDisconf.payQueryUrl = payQueryUrl;
	}

	@DisconfFileItem(name = "pay_md5_key")
	public static String getPayMd5Key() {
		return payMd5Key;
	}

	public static void setPayMd5Key(String payMd5Key) {
		GlobalDisconf.payMd5Key = payMd5Key;
	}

	@DisconfFileItem(name = "pay_aes_key")
	public static String getPayAesKey() {
		return payAesKey;
	}

	public static void setPayAesKey(String payAesKey) {
		GlobalDisconf.payAesKey = payAesKey;
	}

	@DisconfFileItem(name = "order_source_web")
	public static String getOrderSourceWeb() {
		return orderSourceWeb;
	}

	public static void setOrderSourceWeb(String orderSourceWeb) {
		GlobalDisconf.orderSourceWeb = orderSourceWeb;
	}

	@DisconfFileItem(name = "order_source_app")
	public static String getOrderSourceApp() {
		return orderSourceApp;
	}

	public static void setOrderSourceApp(String orderSourceApp) {
		GlobalDisconf.orderSourceApp = orderSourceApp;
	}

	@DisconfFileItem(name = "order_source_wap")
	public static String getOrderSourceWap() {
		return orderSourceWap;
	}

	public static void setOrderSourceWap(String orderSourceWap) {
		GlobalDisconf.orderSourceWap = orderSourceWap;
	}

	@DisconfFileItem(name = "order_type")
	public static String getOrderType() {
		return orderType;
	}

	public static void setOrderType(String orderType) {
		GlobalDisconf.orderType = orderType;
	}

	@DisconfFileItem(name = "sms_frequency_limit_count")
	public static String getSmsFrequencyLimitCount() {
		return smsFrequencyLimitCount;
	}

	public static void setSmsFrequencyLimitCount(String smsFrequencyLimitCount) {
		GlobalDisconf.smsFrequencyLimitCount = smsFrequencyLimitCount;
	}

	@DisconfFileItem(name = "sms_frequency_limit_time")
	public static String getSmsFrequencyLimitTime() {
		return smsFrequencyLimitTime;
	}

	public static void setSmsFrequencyLimitTime(String smsFrequencyLimitTime) {
		GlobalDisconf.smsFrequencyLimitTime = smsFrequencyLimitTime;
	}
	
	@DisconfFileItem(name = "order_prefix")
	public static String getOrderPrefix() {
		return orderPrefix;
	}

	public static void setOrderPrefix(String orderPrefix) {
		GlobalDisconf.orderPrefix = orderPrefix;
	}

	@DisconfFileItem(name = "refund_prefix")
	public static String getRefundPrefix() {
		return refundPrefix;
	}

	public static void setRefundPrefix(String refundPrefix) {
		GlobalDisconf.refundPrefix = refundPrefix;
	}

	@DisconfFileItem(name = "push_sap_max_number")
	public static Integer getPushSapMaxNumber() {
		return pushSapMaxNumber;
	}

	public static void setPushSapMaxNumber(Integer pushSapMaxNumber) {
		GlobalDisconf.pushSapMaxNumber = pushSapMaxNumber;
	}

	@DisconfFileItem(name = "push_vender_max_number")
	public static Integer getPushVenderMaxNumber() {
		return pushVenderMaxNumber;
	}

	public static void setPushVenderMaxNumber(Integer pushVenderMaxNumber) {
		GlobalDisconf.pushVenderMaxNumber = pushVenderMaxNumber;
	}

	@DisconfFileItem(name = "order_lock_time")
	public static String getOrderLockTime() {
		return orderLockTime;
	}

	public static void setOrderLockTime(String orderLockTime) {
		GlobalDisconf.orderLockTime = orderLockTime;
	}
	
	@DisconfFileItem(name = "order_remind_time")
	public static String getOrderRemindTime() {
		return orderRemindTime;
	}

	public static void setOrderRemindTime(String orderRemindTime) {
		GlobalDisconf.orderRemindTime = orderRemindTime;
	}

	@DisconfFileItem(name = "ticket_error_msg")
	public static String getTicketErrorMsg() {
		return ticketErrorMsg;
	}

	public static void setTicketErrorMsg(String ticketErrorMsg) {
		GlobalDisconf.ticketErrorMsg = ticketErrorMsg;
	}

	@DisconfFileItem(name = "pay_prefix")
	public static String getPayPrefix() {
		return payPrefix;
	}

	public static void setPayPrefix(String payPrefix) {
		GlobalDisconf.payPrefix = payPrefix;
	}

	@DisconfFileItem(name = "order_list_size")
	public static String getOrderListSize() {
		return orderListSize;
	}

	public static void setOrderListSize(String orderListSize) {
		GlobalDisconf.orderListSize = orderListSize;
	}

	@DisconfFileItem(name = "page_back_url")
	public static String getPageBackUrl() {
		return pageBackUrl;
	}

	public static void setPageBackUrl(String pageBackUrl) {
		GlobalDisconf.pageBackUrl = pageBackUrl;
	}

	@DisconfFileItem(name = "notify_url")
	public static String getNotifyUrl() {
		return notifyUrl;
	}

	public static void setNotifyUrl(String notifyUrl) {
		GlobalDisconf.notifyUrl = notifyUrl;
	}

    @DisconfFileItem(name = "env")
    public static String getEnv() {
        return env;
    }

    public static void setEnv(String env) {
        GlobalDisconf.env = env;
    }

    @DisconfFileItem(name = "job_mon_node")
	public static String getJobMonNode() {
		return jobMonNode;
	}

	public static void setJobMonNode(String jobMonNode) {
		GlobalDisconf.jobMonNode = jobMonNode;
	}

	@DisconfFileItem(name = "resource_env")
	public static String getResourceEnv() {
		return resourceEnv;
	}

	public static void setResourceEnv(String resourceEnv) {
		GlobalDisconf.resourceEnv = resourceEnv;
	}

	@DisconfFileItem(name = "domain_dujia")
	public static String getDomainDujia() {
		return domainDujia;
	}

	public static void setDomainDujia(String domainDujia) {
		GlobalDisconf.domainDujia = domainDujia;
	}

	@DisconfFileItem(name = "domain_lvxing")
	public static String getDomainLvxing() {
		return domainLvxing;
	}

	public static void setDomainLvxing(String domainLvxing) {
		GlobalDisconf.domainLvxing = domainLvxing;
	}

	@DisconfFileItem(name = "domain_jipiao")
	public static String getDomainJipiao() {
		return domainJipiao;
	}

	public static void setDomainJipiao(String domainJipiao) {
		GlobalDisconf.domainJipiao = domainJipiao;
	}

	@DisconfFileItem(name = "domain_train")
	public static String getDomainTrain() {
		return domainTrain;
	}

	public static void setDomainTrain(String domainTrain) {
		GlobalDisconf.domainTrain = domainTrain;
	}

	@DisconfFileItem(name = "domain_hotel")
	public static String getDomainHotel() {
		return domainHotel;
	}

	public static void setDomainHotel(String domainHotel) {
		GlobalDisconf.domainHotel = domainHotel;
	}

    @DisconfFileItem(name = "sap_sku_name")
    public static String getSapSkuName() {
        return sapSkuName;
    }

    public static void setSapSkuName(String sapSkuName) {
        GlobalDisconf.sapSkuName = sapSkuName;
    }

    @DisconfFileItem(name = "sms_diffid_pay_remind")
	public static String getSmsDiffidPayRemind() {
		return smsDiffidPayRemind;
	}

	public static void setSmsDiffidPayRemind(String smsDiffidPayRemind) {
		GlobalDisconf.smsDiffidPayRemind = smsDiffidPayRemind;
	}

	@DisconfFileItem(name = "sms_diffid_out_time_cancel")
	public static String getSmsDiffidOutTimeCancel() {
		return smsDiffidOutTimeCancel;
	}

	public static void setSmsDiffidOutTimeCancel(String smsDiffidOutTimeCancel) {
		GlobalDisconf.smsDiffidOutTimeCancel = smsDiffidOutTimeCancel;
	}

	@DisconfFileItem(name = "sms_diffid_vender_pay_refund")
	public static String getSmsDiffidVenderPayRefund() {
		return smsDiffidVenderPayRefund;
	}

	public static void setSmsDiffidVenderPayRefund(String smsDiffidVenderPayRefund) {
		GlobalDisconf.smsDiffidVenderPayRefund = smsDiffidVenderPayRefund;
	}

	@DisconfFileItem(name = "sms_diffid_alarm")
	public static String getSmsDiffidAlarm() {
		return smsDiffidAlarm;
	}

	public static void setSmsDiffidAlarm(String smsDiffidAlarm) {
		GlobalDisconf.smsDiffidAlarm = smsDiffidAlarm;
	}

    @DisconfFileItem(name = "business_warn_phones")
    public static String getBusinessWarnPhones() {
        return businessWarnPhones;
    }

    public static void setBusinessWarnPhones(String businessWarnPhones) {
        GlobalDisconf.businessWarnPhones = businessWarnPhones;
    }

    @DisconfFileItem(name = "sms_diffid_pay_remind_temp")
	public static String getSmsDiffidPayRemindTemp() {
		return smsDiffidPayRemindTemp;
	}

	public static void setSmsDiffidPayRemindTemp(String smsDiffidPayRemindTemp) {
		GlobalDisconf.smsDiffidPayRemindTemp = smsDiffidPayRemindTemp;
	}

	@DisconfFileItem(name = "sms_diffid_out_time_cancel_temp")
	public static String getSmsDiffidOutTimeCancelTemp() {
		return smsDiffidOutTimeCancelTemp;
	}

	public static void setSmsDiffidOutTimeCancelTemp(
			String smsDiffidOutTimeCancelTemp) {
		GlobalDisconf.smsDiffidOutTimeCancelTemp = smsDiffidOutTimeCancelTemp;
	}

	@DisconfFileItem(name = "sms_diffid_vender_pay_refund_temp")
	public static String getSmsDiffidVenderPayRefundTemp() {
		return smsDiffidVenderPayRefundTemp;
	}

	public static void setSmsDiffidVenderPayRefundTemp(
			String smsDiffidVenderPayRefundTemp) {
		GlobalDisconf.smsDiffidVenderPayRefundTemp = smsDiffidVenderPayRefundTemp;
	}

	@DisconfFileItem(name = "sms_diffid_alarm_temp")
	public static String getSmsDiffidAlarmTemp() {
		return smsDiffidAlarmTemp;
	}

	public static void setSmsDiffidAlarmTemp(String smsDiffidAlarmTemp) {
		GlobalDisconf.smsDiffidAlarmTemp = smsDiffidAlarmTemp;
	}
}

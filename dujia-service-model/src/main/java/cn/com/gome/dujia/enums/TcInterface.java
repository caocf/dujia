package cn.com.gome.dujia.enums;

/**
 * @author wangweiran
 * 
 * 同程订单增量变化原因
 */
public enum TcInterface {

	LINE_INDEX_INFO_LIST("获取线路套餐Id接口", "LineIndexInfoList"),
	GET_PRODUCT_DETAIL_INFO("获取线路套餐详情接口", "GetProductDetailInfo"),
	ORDER_CREATE("套餐订单创建接口", "OrderCreate"),
	ORDER_SEARCH("订单查询接口", "OrderSearch"),
	GET_CANCEL_REASON("获取订单取消原因接口", "GetCancelReason"),
	LINE_SALEINFO_CALENDAR("获取套餐价格班期接口", "LineSaleInfoCalendar"),
	ORDER_REFUND_APPLY_QUERY("退改结果查询接口", "OrderRefundApplyQuery"),
	GET_RES_PRODUCT_VERIFY_DATE("获取资源产品预定日期接口", "GetResProductVerifyDate"),
	GET_INCREMENT_PRODUCT_LIST("获取增量线路套餐id接口", "GetIncrementProductList"),
	ORDER_CANCEL("订单支付前取消接口", "OrderCancel"),
	ORDER_REFUND_APPLY("退改结果查询接口", "OrderRefundApply"),

	INFO_CHANGE_ID("线路信息变更(全部变更)", "1"),
	PRICE_CHANGE_ID("价格信息变更（更新zby_product_package_price表）", "2"),
	INVALID_ID("把线路设置成无效（更新product表isDelete=1）", "3"),
	ONLINE_UPDATE("把线路设置有效（更新product表isDelete=0）", "4"),
	PACKAGE_UPDATE("把套餐详情变更（更新zby_product_package zby_product_addition zby_product_package_detail表 ）", "5"),
	INVENTORY_UPDATE("库存更新（更新zby_product_package_price表）", "7"),
	LINE_PRODUCT_UPDATE("线路字段更新（product 和 he zby_recom_info表）", "6"),
	PRODUCT_INCREMENT_TASK_KEY("定时任务更新最后一次时间", "PRODUCT_INCREMENT_TASK_KEY_1"),


	BED_TYPE("床型，从map读取", "床型");


	private String name;
	private String value;

	TcInterface(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public String getValue() {
		return this.value;
	}
	
	/**
	 * 根据value，获取名称
	 * 
	 * @param value
	 * @return
	 */
	public static String getName(String value) {
		if(null != value && !"".equals(value)) {
			for (TcInterface tcInterface : TcInterface.values()) {
				if (tcInterface.getValue().equals(value)) {
					return tcInterface.name;
				}
			}
		}
		return null;
	}
}

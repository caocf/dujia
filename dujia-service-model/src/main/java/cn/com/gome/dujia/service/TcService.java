package cn.com.gome.dujia.service;

import java.util.List;
import java.util.Map;

import cn.com.gome.dujia.exception.BizException;
import cn.com.gome.dujia.model.ZbyLine;
import cn.com.gome.dujia.model.ZbyProduct;
import cn.com.gome.dujia.vo.json.order.LineSaleInfoCalenderResult;
import cn.com.gome.dujia.vo.json.order.OrderCreateResult;
import cn.com.gome.dujia.vo.json.order.OrderRefundApplyQueryResult;
import cn.com.gome.dujia.vo.json.order.OrderSearchResult;
import cn.com.gome.dujia.vo.json.productinfo.IncrementProduct;
import cn.com.gome.dujia.vo.json.productinfo.OrderCreateResponse;
import cn.com.gome.dujia.vo.json.productinfo.ResourcesProductDate;
import cn.com.gome.dujia.vo.order.CancelResult;
import cn.com.gome.dujia.vo.order.OrderIncrementInfo;

/**
 * 同城对接接口 Created by sunming on 2016/4/5.
 */
public interface TcService {

	/**
	 * 获取线路套餐Id 列表接口
	 * 
	 * @param map
	 * @return
	 */
	List<ZbyLine> lineIndexInfoList(Map<String, String> map);

	/**
	 * 获取线路套餐详情接口
	 * 
	 * @param map
	 * @return
	 */
	List<ZbyProduct> getProductDetailInfo(Map<String, String> map);

	/**
	 * 订单查询接口
	 * 
	 * @param map
	 * @return
	 */
	OrderSearchResult orderSearch(Map<String, String> map);

	/**
	 * 套餐订单创建接口
	 * 
	 * @param map
	 * @return
	 */
	OrderCreateResult orderCreate(Map<String, String> map) throws BizException;
	
	/**
	 * 套餐订单创建接口
	 * 
	 * @param map
	 * @return
	 */
	OrderCreateResponse orderCreateResponse(Map<String, String> map) throws BizException;

	/**
	 * 获取套餐价格班期接口
	 * 
	 * @param map
	 * @return
	 */
	LineSaleInfoCalenderResult lineSaleInfoCalendar(Map<String, String> map);

	/**
	 * 退改结果查询接口
	 * 
	 * @param map
	 * @return
	 */
	OrderRefundApplyQueryResult orderRefundApplyQuery(Map<String, String> map);

	/**
	 * 获取资源产品预定日期接口
	 * 
	 * @param map
	 * @return
	 */
	List<ResourcesProductDate> getResProductVerifyDate(Map<String, String> map);

	/**
	 * 获取增量线路套餐id 列表接口
	 *
	 * @param map
	 * @return
	 */
	List<IncrementProduct> getIncrementProductList(Map<String, String> map);

	/**
	 * 订单增量接口
	 *
	 * @param map
	 * @return
	 */

	List<OrderIncrementInfo>  getOrderIncrementInfo(Map<String, String> map);


	/**
	 * 订单取消接口
	 *
	 * @param map
	 * @return
	 */
	CancelResult orderCancel(Map<String, String> map);

	/**
	 * 退款申请
	 *
	 * @param map
	 * @return
	 */
	CancelResult OrderRefundApply(Map<String, String> map);
}

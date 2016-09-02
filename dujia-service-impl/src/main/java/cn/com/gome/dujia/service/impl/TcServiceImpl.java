package cn.com.gome.dujia.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import cn.com.gome.dujia.vo.order.CancelResponse;
import cn.com.gome.dujia.vo.order.CancelResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.disconf.JsonMapDisconf;
import cn.com.gome.dujia.enums.TcInterface;
import cn.com.gome.dujia.exception.BizException;
import cn.com.gome.dujia.log.APILog;
import cn.com.gome.dujia.log.LogType;
import cn.com.gome.dujia.model.ZbyLine;
import cn.com.gome.dujia.model.ZbyProduct;
import cn.com.gome.dujia.service.TcService;
import cn.com.gome.dujia.vo.json.order.LineSaleInfoCalenderResult;
import cn.com.gome.dujia.vo.json.order.OrderCreateResult;
import cn.com.gome.dujia.vo.json.order.OrderIncrementResponse;
import cn.com.gome.dujia.vo.json.order.OrderRefundApplyQueryResult;
import cn.com.gome.dujia.vo.json.order.OrderSearchResult;
import cn.com.gome.dujia.vo.json.productinfo.IncrementProduct;
import cn.com.gome.dujia.vo.json.productinfo.IncrementProductListResponse;
import cn.com.gome.dujia.vo.json.productinfo.LineIndexInfoResponse;
import cn.com.gome.dujia.vo.json.productinfo.LineSaleInfoCalenderResponse;
import cn.com.gome.dujia.vo.json.productinfo.OrderCreateResponse;
import cn.com.gome.dujia.vo.json.productinfo.OrderRefundApplyQueryResponse;
import cn.com.gome.dujia.vo.json.productinfo.OrderSearchResponse;
import cn.com.gome.dujia.vo.json.productinfo.ProductDetailInfoResponse;
import cn.com.gome.dujia.vo.json.productinfo.ResourcesProductDate;
import cn.com.gome.dujia.vo.json.productinfo.ResourcesProductDateResponse;
import cn.com.gome.dujia.vo.order.OrderIncrementInfo;

import com.gome.plan.tools.utils.DateUtils;
import com.gome.plan.tools.utils.JsonUtils;
import com.gome.plan.tools.utils.StringUtils;
import com.tongcheng.zizhuyou.openapi.RequestEntity;
import com.tongcheng.zizhuyou.openapi.RequestUtil;

/**
 * Created by sunming on 2016/4/18.
 */
@Service
public class TcServiceImpl implements TcService {

	private static final Logger logger = LoggerFactory.getLogger(TcServiceImpl.class);

	private static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON).dateFormat(new SimpleDateFormat(DateUtils.LONG_WEB_FORMAT));


	/**
	 * 系统级错误代码
	 */
	enum RetCode {
		
		SUCCESS("请求成功", "200");
		
		private String name;
		private String value;

		RetCode(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return this.name;
		}

		public String getValue() {
			return this.value;
		}
	}
	
	/**
	 * 获取线路套餐Id 列表接口
	 * 
	 * @param map
	 * @return
	 */
	public List<ZbyLine> lineIndexInfoList(Map<String, String> map) {
		String json = getJsonResource(TcInterface.LINE_INDEX_INFO_LIST.getValue(), map);
		if (StringUtils.isNotEmpty(json)) {
			LineIndexInfoResponse response = jsonUtils.deserialize(json, LineIndexInfoResponse.class);
			if (null != response && RetCode.SUCCESS.getValue().equals(response.getRetCode()) && null != response.getResult()) {
				return response.getResult().getLines();
			}
		}
		return null;
	}

	/**
	 *  获取线路套餐详情接口
	 * 
	 * @param map
	 * @return
	 */
	public List<ZbyProduct> getProductDetailInfo(Map<String, String> map){
		if(null != map) {
			String json = getJsonResource(TcInterface.GET_PRODUCT_DETAIL_INFO.getValue(), map);
			if(StringUtils.isNotEmpty(json)) {
				ProductDetailInfoResponse response = jsonUtils.deserialize(json, ProductDetailInfoResponse.class);
				if (null != response && RetCode.SUCCESS.getValue().equals(response.getRetCode()) && null != response.getResult()) {
					return response.getResult().getLineProductDetailList();
				}
			}
		}
		return null;
	}

	/**
	 * 订单查询接口
	 * 
	 * @param map
	 * @return
	 */
	public OrderSearchResult orderSearch(Map<String, String> map) {
		String json = getJsonResource(TcInterface.ORDER_SEARCH.getValue(), map);
		if(StringUtils.isNotEmpty(json)) {
			OrderSearchResponse response = jsonUtils.deserialize(json, OrderSearchResponse.class);
			if (null != response && RetCode.SUCCESS.getValue().equals(response.getRetCode())) {
				return response.getResult();
			}
		}
		return null;
	}

	/**
	 * 套餐订单创建接口
	 * 
	 * @param map
	 * @return
	 */
	public OrderCreateResult orderCreate(Map<String, String> map) throws BizException {
		if (null != map) {
			if(StringUtils.isEmpty(map.get("OrderMoney"))){
				throw new BizException("OrderMoney为必填参数，请确认下单价格");
			}
			String json = getJsonResource(TcInterface.ORDER_CREATE.getValue(), map);
			if (StringUtils.isNotEmpty(json)) {
				OrderCreateResponse response = jsonUtils.deserialize(json, OrderCreateResponse.class);
				if (null != response && RetCode.SUCCESS.getValue().equals(response.getRetCode())) {
					return response.getResult();
				}
			}
		}
		return null;
	}
	
	/**
	 * 套餐订单创建接口
	 * 
	 * @param map
	 * @return
	 */
	public OrderCreateResponse orderCreateResponse(Map<String, String> map) throws BizException {
		if (null != map) {
			if(StringUtils.isEmpty(map.get("OrderMoney"))){
				throw new BizException("OrderMoney为必填参数，请确认下单价格");
			}
			String json = getJsonResource(TcInterface.ORDER_CREATE.getValue(), map);
			if (StringUtils.isNotEmpty(json)) {
				return jsonUtils.deserialize(json, OrderCreateResponse.class);
			}
		}
		return null;
	}

	/**
	 * 获取套餐价格班期接口
	 * 
	 * @param map
	 * @return
	 */
	public LineSaleInfoCalenderResult lineSaleInfoCalendar(Map<String, String> map) {
		if(null != map) {
			String json = getJsonResource(TcInterface.LINE_SALEINFO_CALENDAR.getValue(), map);
			if(StringUtils.isNotEmpty(json)) {
				LineSaleInfoCalenderResponse response = jsonUtils.deserialize(json, LineSaleInfoCalenderResponse.class);
				if (null != response && RetCode.SUCCESS.getValue().equals(response.getRetCode())) {
					return response.getResult();
				}
			}
		}
		return null;
	}

	/**
	 * 退改结果查询接口
	 * 
	 * @param map
	 * @return
	 */
	public OrderRefundApplyQueryResult orderRefundApplyQuery(Map<String, String> map) {
		if(null != map) {
			String json = getJsonResource(TcInterface.ORDER_REFUND_APPLY_QUERY.getValue(), map);
			if(StringUtils.isNotEmpty(json)) {
				OrderRefundApplyQueryResponse response = jsonUtils.deserialize(json, OrderRefundApplyQueryResponse.class);
				if (null != response && RetCode.SUCCESS.getValue().equals(response.getRetCode())) {
					return response.getResult();
				}
			}
		}
		return null;
	}

	/**
	 * 获取资源产品预定日期接口
	 * 
	 * @param map
	 * @return
	 */
	public List<ResourcesProductDate> getResProductVerifyDate(Map<String, String> map) {
		if(null != map) {
			String json = getJsonResource(TcInterface.GET_RES_PRODUCT_VERIFY_DATE.getValue(), map);
			if(StringUtils.isNotEmpty(json)) {
				ResourcesProductDateResponse response = jsonUtils.deserialize(json, ResourcesProductDateResponse.class);
				if (null != response && RetCode.SUCCESS.getValue().equals(response.getRetCode()) && null != response.getResult()) {
					return response.getResult().getResourcesProductDate();
				}
			}
		}
		return null;
	}

	/**
	 * 获取增量线路套餐id 列表接口
	 * 
	 * @param map
	 * @return
	 */
	public List<IncrementProduct> getIncrementProductList(Map<String, String> map) {
		if(null != map) {
			String json = getJsonResource(TcInterface.GET_INCREMENT_PRODUCT_LIST.getValue(), map);
			if(StringUtils.isNotEmpty(json)) {
				IncrementProductListResponse response = jsonUtils.deserialize(json, IncrementProductListResponse.class);
				if (null != response && RetCode.SUCCESS.getValue().equals(response.getRetCode()) && null != response.getResult()) {
					return response.getResult().getProductList();
				}
			}
		}
		return null;
	}

	/**
	 * 订单增量接口
	 *
	 * @param map
	 * @return
	 */
	public List<OrderIncrementInfo> getOrderIncrementInfo(Map<String, String> map) {
		if(null != map) {
			String json = getJsonResource(TcInterface.GET_PRODUCT_DETAIL_INFO.getValue(), map);
			if(StringUtils.isNotEmpty(json)) {
				OrderIncrementResponse response = jsonUtils.deserialize(json, OrderIncrementResponse.class);
				if (null != response && RetCode.SUCCESS.getValue().equals(response.getRetCode()) && null != response.getResult()) {
					return response.getResult().getOrderIncrementList();
				}
			}
		}
		return null;
	}

	/**
	 * 订单取消接口
	 *
	 * @param map
	 * @return
	 */
	public CancelResult orderCancel(Map<String, String> map) {
		if(null != map) {
			String json = getJsonResource(TcInterface.ORDER_CANCEL.getValue(), map);
			if(StringUtils.isNotEmpty(json)) {
				CancelResponse response = jsonUtils.deserialize(json, CancelResponse.class);
				if (null != response && RetCode.SUCCESS.getValue().equals(response.getRetCode())) {
					return response.getResult();
				}
			}
		}
		return null;
	}

	/**
	 * 退款申请
	 *
	 * @param map
	 * @return
	 */
	public CancelResult OrderRefundApply(Map<String, String> map) {
		if(null != map) {
			String json = getJsonResource(TcInterface.ORDER_REFUND_APPLY.getValue(), map);
			if(StringUtils.isNotEmpty(json)) {
				CancelResponse response = jsonUtils.deserialize(json, CancelResponse.class);
				if (null != response && RetCode.SUCCESS.getValue().equals(response.getRetCode())) {
					return response.getResult();
				}
			}
		}
		return null;
	}


	/**
	 * 调用同程接口获取json结果
	 * 
	 * @param methodName
	 * @param requestMap
	 * @return
	 */
	private String getJsonResource(String methodName, Map<String,String> requestMap) {
		//接口中文名
		String methodChineseName = TcInterface.getName(methodName);
		//调用接口开始时间
		Long startTime = System.currentTimeMillis();

		RequestEntity request = new RequestEntity();
		request.setUrl(String.format(GlobalDisconf.tcPrefixUrl, methodName));
		request.setAccessId(GlobalDisconf.tcAccessId);
		request.setSecurityId(GlobalDisconf.tcSecurityId);
		if (requestMap != null) {
			for (String var : requestMap.keySet()) {
				if (StringUtils.isNotEmpty(requestMap.get(var))) {
					request.getRequestParameter().put(var, requestMap.get(var));
				}
			}
		}
		String response = null;
		try {
			response = RequestUtil.getJsonResource(request);
			return response;
		} catch (Exception e) {
			logger.error("请求同城接口【{}】失败", methodChineseName, e);
			return null;
		} finally {
			//调用接口结束时间
			Long endTime = System.currentTimeMillis();
			
			Map<String, Boolean>  map = JsonMapDisconf.getTcInterfaceLogMap();
			//接口是否记录日志
			if(null != map && map.get(methodName)) {
				//日志文件
				logger.info("{}：request请求{}，response响应{}", methodChineseName, jsonUtils.serialize(request), response);

				//记录接口日志
				APILog.build(methodName, LogType.TC)
						.appendRequest("executeTime", (endTime-startTime)/1000.000+"秒")
						.appendRequest("request", jsonUtils.serialize(request))
						.appendResponse("response", response)
						.record();
			}
		}
	}

}

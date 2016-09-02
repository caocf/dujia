package cn.com.gome.dujia.service.impl;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.gome.plan.tools.http.HttpClientUtils;
import com.gome.plan.tools.utils.EncrUtil;
import com.gome.plan.tools.utils.JsonUtils;
import com.gome.plan.tools.utils.Md5Util;
import com.gome.plan.tools.utils.StringUtils;

import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.enums.CashierStatus;
import cn.com.gome.dujia.exception.BizException;
import cn.com.gome.dujia.log.APILog;
import cn.com.gome.dujia.log.LogType;
import cn.com.gome.dujia.model.CashierRequest;
import cn.com.gome.dujia.service.CashierService;
/**
 * Description : 收银台服务
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月22日 上午10:45:30 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
@Service
public class CashierServiceImpl implements CashierService {

	private static final Logger logger = LoggerFactory.getLogger(CashierServiceImpl.class);
	
	private static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON);
	
	
	/**
	 * 收银台支付请求接口地址，参数签名过程
	 * 1，参数按照顺序进行MD5签名
	 * 2，(参数+MD5签名串)按照顺序进行AES签名
	 * @param cashierRequest     参数
	 * @return
	 */
	@Override
	public String cashierPay(CashierRequest cashierRequest) throws BizException {
		//验证收银台支付参数
		checkPayParam(cashierRequest);

		//拼参数
		String paramString = assembleParameter(cashierRequest);
		logger.info("收银台支付请求参数{}", paramString);

		//添加MD5key参数->MD5签名-->（必须是大写）
		String md5Sign = Md5Util.md5(paramString + "&key=" + cashierRequest.getPayMd5Key()).toUpperCase();
		logger.info("收银台支付请求参数MD5签名结果{}", md5Sign);

        paramString = paramString + "&isNormalPay=" + cashierRequest.getIsNormalPay() + "&siteName=" + cashierRequest.getSiteName();

		//添加订单信息和MD5签名参数->AES签名
        String aesSign = EncrUtil.encrypt(cashierRequest.getPayAesKey(), paramString + "&securitySign=" + md5Sign);
        logger.info("收银台支付请求参数AES签名结果{}", aesSign);

		//收银台支付请求接口地址
		String payUrl = cashierRequest.getPayUrl() + "?reqInfo=" + aesSign;
		logger.info("收银台支付请求接口地址：{}", payUrl);
		
		return payUrl;
	}
	
	
	@Override
	public String cashiderQuery(CashierRequest cashierRequest) throws BizException {
		
		//验证收银台查询参数
		checkQueryParam(cashierRequest);
				
		//拼参数
		String paramString = assembleParameter(cashierRequest);
				
		return cashierRequest.getPayQueryUrl() + "?" + paramString;
	}
	
	/**
	 * 收银台支付请求参数验证
	 * @param cashierRequest
	 * @throws BizException
	 */
	private void checkPayParam(CashierRequest cashierRequest) throws BizException {
		if (cashierRequest == null) {
			logger.error("收银台支付请求参数不能为空");
			throw new BizException("收银台支付请求参数不能为空");
		}
		if (StringUtils.isBlank(cashierRequest.getSiteAccount())) {
			logger.error("收银台支付请求商户号不能为空");
			throw new BizException("收银台支付请求商户号不能为空");
		}
		if (StringUtils.isBlank(cashierRequest.getOrderNo())) {
			logger.error("收银台支付请求订单编号不能为空");
			throw new BizException("收银台支付请求订单编号不能为空");
		}
		if (StringUtils.isBlank(cashierRequest.getOrderMoney())) {
			logger.error("收银台支付请求订单总金额不能为空");
			throw new BizException("收银台支付请求订单总金额不能为空");
		}
		if (StringUtils.isBlank(cashierRequest.getPayMoney())) {
			logger.error("收银台支付请求支付金额不能为空");
			throw new BizException("收银台支付请求支付金额不能为空");
		}
		if (StringUtils.isBlank(cashierRequest.getUserNo())) {
			logger.error("收银台支付请求会员编码不能为空");
			throw new BizException("收银台支付请求会员编码不能为空");
		}
		if (StringUtils.isBlank(cashierRequest.getPageBackUrl())) {
			logger.error("收银台支付请求页面回调地址不能为空");
			throw new BizException("收银台支付请求页面回调地址不能为空");
		}
		if (StringUtils.isBlank(cashierRequest.getNotifyUrl())) {
			logger.error("收银台支付请求点对点回调地址不能为空");
			throw new BizException("收银台支付请求点对点回调地址不能为空");
		}
		if (StringUtils.isBlank(cashierRequest.getOrderType())) {
			logger.error("收银台支付请求订单类型不能为空");
			throw new BizException("收银台支付请求订单类型不能为空");
		}
		if (StringUtils.isBlank(cashierRequest.getIsSupportStages())) {
			logger.error("收银台支付请求商品是否支持分期不能为空");
			throw new BizException("收银台支付请求商品是否支持分期不能为空");
		}
		if (StringUtils.isBlank(cashierRequest.getIsByStages())) {
			logger.error("收银台支付请求用户选择支付方式不能为空");
			throw new BizException("收银台支付请求用户选择支付方式不能为空");
		}
		if (StringUtils.isBlank(cashierRequest.getOrderExpireMsg())) {
			logger.error("收银台支付请求过期时长不能为空");
			throw new BizException("收银台支付请求过期时长不能为空");
		}
		if (StringUtils.isBlank(cashierRequest.getOrderExpireTime())) {
			logger.error("收银台支付请求过期时间不能为空");
			throw new BizException("收银台支付请求过期时间不能为空");
		}
	}
	
	/**
	 * 收银台查询请求参数验证
	 * @param cashierRequest
	 * @throws BizException
	 */
	private void checkQueryParam(CashierRequest cashierRequest) throws BizException {
		if (cashierRequest == null) {
			logger.error("收银台支付请求参数不能为空");
			throw new BizException("收银台支付请求参数不能为空");
		}
		if (StringUtils.isBlank(cashierRequest.getSiteAccount())) {
			logger.error("收银台支付请求商户号不能为空");
			throw new BizException("收银台支付请求商户号不能为空");
		}
		if (StringUtils.isBlank(cashierRequest.getOrderNo())) {
			logger.error("收银台支付请求订单编号不能为空");
			throw new BizException("收银台支付请求订单编号不能为空");
		}
		if (StringUtils.isBlank(cashierRequest.getUserNo())) {
			logger.error("收银台支付请求会员编码不能为空");
			throw new BizException("收银台支付请求会员编码不能为空");
		}
	}
	
	/**
	 * 注意：按照以下参数顺序进行md5签名，即为签名值，空值不参加签名
	 * 
	 * siteAccount
	 * orderNo
	 * orderMoney
	 * payMoney
	 * userNo
	 * telphone
	 * sukCode
	 * productType
	 * pageBackUrl
	 * notifyUrl
	 * orderType
	 * isSupportStages
	 * isByStages
	 * remark
	 * orderExpireMsg
	 * orderExpireTime
	 * partDetail
	 * 
	 */
	private String assembleParameter(CashierRequest request){
		
		Map<String, String> orderParam = new LinkedHashMap<String, String>();

		if (!StringUtils.isBlank(request.getSiteAccount())) {
			orderParam.put("siteAccount", request.getSiteAccount());
		}

		if (!StringUtils.isBlank(request.getOrderNo())) {
			orderParam.put("orderNo", request.getOrderNo());
		}

		if (!StringUtils.isBlank(request.getOrderMoney())) {
			orderParam.put("orderMoney", request.getOrderMoney());
		}

		if (!StringUtils.isBlank(request.getPayMoney())) {
			orderParam.put("payMoney", request.getPayMoney());
		}

		if (!StringUtils.isBlank(request.getUserNo())) {
			orderParam.put("userNo", request.getUserNo());
		}

		if (!StringUtils.isBlank(request.getTelphone())) {
			orderParam.put("telphone", request.getTelphone());
		}

		if (!StringUtils.isBlank(request.getSkuCode())) {
			orderParam.put("skuCode", request.getSkuCode());
		}

		if (!StringUtils.isBlank(request.getProductType())) {
			orderParam.put("productType", request.getProductType());
		}

		if (!StringUtils.isBlank(request.getPageBackUrl())) {
			orderParam.put("pageBackUrl", request.getPageBackUrl());
		}

		if (!StringUtils.isBlank(request.getNotifyUrl())) {
			orderParam.put("notifyUrl", request.getNotifyUrl());
		}

		if (!StringUtils.isBlank(request.getOrderType())) {
			orderParam.put("orderType", request.getOrderType());
		}

		if (!StringUtils.isBlank(request.getIsSupportStages())) {
			orderParam.put("isSupportStages", request.getIsSupportStages());
		}

		if (!StringUtils.isBlank(request.getIsByStages())) {
			orderParam.put("isByStages", request.getIsByStages());
		}

		if (!StringUtils.isBlank(request.getRemark())) {
			orderParam.put("remark", request.getRemark());
		}

		if (!StringUtils.isBlank(request.getOrderExpireMsg())) {
			orderParam.put("orderExpireMsg", request.getOrderExpireMsg());
		}

		if (!StringUtils.isBlank(request.getOrderExpireTime())) {
			orderParam.put("orderExpireTime", request.getOrderExpireTime());
		}

		if (!StringUtils.isBlank(request.getPartDetail())) {
			orderParam.put("partDetail", request.getPartDetail());
		}

		StringBuilder params = new StringBuilder();
		for (String key : orderParam.keySet()) {
			if (params.length() > 0) {
				params.append("&");
			}
			params.append(key).append("=").append(orderParam.get(key));
		}
		return params.toString();
	}
	


	
	/**
	 * 收银台支付查询
	 * @param siteAccount  商户号
	 * @param orderNo      订单号
	 * @param userNo       用户编码
	 * @return
	 */
	public String queryPay(String siteAccount, String orderNo, String userNo) {
		if(StringUtils.isEmpty(siteAccount) || StringUtils.isEmpty(orderNo) || StringUtils.isEmpty(userNo)) {
			logger.error("收银台支付查询参数为空");
			return null;
		}
		logger.info("收银台支付查询：商户号{}，订单号{}，用户编码{}", siteAccount, orderNo, userNo);
		
		//请求参数
		String request = "siteAccount="+siteAccount+"&orderNo="+orderNo+"&userNo="+userNo;
		
		//查询地址
		String queryUrl = GlobalDisconf.getPayQueryUrl() + "?" + request;
		logger.info("收银台支付查询地址{}", queryUrl);
		
		//响应参数
		String response = HttpClientUtils.doGet(queryUrl);
		
		//日志
		logger.info("收银台支付查询接口：request请求{}，response响应{}", request, response);
		
		//记录接口日志
		APILog.build("收银台支付查询接口", LogType.PAY)
				.appendRequest("url", GlobalDisconf.getPayQueryUrl())
				.appendRequest("request", request)
				.appendResponse("response", response)
				.record();
		
		return response;
	}


	/**
	 * 是否支付成功
	 * 
	 * @param siteAccount  商户号
	 * @param orderNo      订单号
	 * @param userNo       用户编码
	 * @return
	 */
	public boolean isPaySuccess(String siteAccount, String orderNo, String userNo) {
		//调用收银台支付查询接口
		String response = this.queryPay(siteAccount, orderNo, userNo);
		if(StringUtils.isEmpty(response)) {
			logger.error("收银台支付查询接口：返回结果为空，订单号{}", orderNo);
			return false;
		}
		logger.info("收银台支付查询接口：返回结果{}，订单号{}", response, orderNo);
		
		//把返回的json转成map
		Map<String, String> map = jsonUtils.deserialize(response, new TypeReference<Map<String, String>>() {});
		if(CollectionUtils.isEmpty(map)) {
			logger.error("收银台支付查询接口：返回结果json转map失败，订单号{}", orderNo);
			return false;
		}
		
		//获取收银状态
		String cashierStatus = map.get("cashierStatus");
		if(StringUtils.isEmpty(cashierStatus)) {
			logger.error("收银台支付查询接口：状态为空，订单号{}", orderNo);
			return false;
		}
		logger.info("收银台支付查询接口：状态{}，订单号{}", CashierStatus.getNameByValue(cashierStatus), orderNo);
		
		//判断是否支付成功
		if(CashierStatus.PAY_SUCCESS.getValue().equals(cashierStatus) || CashierStatus.EXPIRED_PAY_SUCCESS.getValue().equals(cashierStatus)) {
			//支付成功 或者 过期支付成功 都返回true
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 参数解析
	 */
	public Map<String, String> payResponse(String rspInfo) {
		if(StringUtils.isEmpty(rspInfo)) {
			logger.error("收银台支付成功回调方法参数不能为空");
			return null;
		}
		logger.info("收银台支付成功回调方法：参数{}", rspInfo);
		
		//AES解密
		String decrypt = EncrUtil.decrypt(GlobalDisconf.payAesKey, rspInfo);
		if(StringUtils.isEmpty(decrypt)) {
			logger.error("收银台支付成功回调方法AES解密失败");
			return null;
		}
		logger.info("收银台支付成功回调方法：AES解密结果{}", decrypt);
		
		//把参数解析存放到LinkedHashMap中（有序的）
		Map<String, String> paramsMap = new LinkedHashMap<String, String>();
		for(String str : decrypt.split("&")) {
			paramsMap.put(str.split("=")[0], str.split("=")[1]);
		}
		//map中添加MD5签名的key
		paramsMap.put("key", GlobalDisconf.payMd5Key);
		
		//将以下参数拼成字符串进行MD5签名
		List<String> paramKeys = Arrays.asList("orderNo", "cashierNo", "payMoney", "payMode", "payBank", "stagingCount", "counterFee", "payTime", "remark", "key");
		StringBuffer params = new StringBuffer();
		for(String key : paramsMap.keySet()) {
			if(paramKeys.contains(key)) {
				if(params.length() > 0) {
					params.append("&");
				}
				params.append(key).append("=").append(paramsMap.get(key));
			}
		}
		logger.info("收银台支付成功回调方法：MD5签名数据{}", params);
		
		//MD5签名
		String md5Sign = Md5Util.md5(params.toString());
		if(StringUtils.isEmpty(md5Sign)) {
			logger.error("收银台支付成功回调方法MD5签名失败");
			return null;
		}
		logger.info("收银台支付成功回调方法：MD5签名结果{}", md5Sign);
		
		//验签
		if(!md5Sign.equalsIgnoreCase(paramsMap.get("securitySign"))) {
			logger.error("收银台支付成功回调方法验签失败");
			return null;
		}
		return paramsMap;
	}

}

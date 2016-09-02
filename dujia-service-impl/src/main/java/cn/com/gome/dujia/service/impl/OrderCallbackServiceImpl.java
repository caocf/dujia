package cn.com.gome.dujia.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import cn.com.gome.dujia.enums.SapType;
import cn.com.gome.dujia.model.PushSap;
import cn.com.gome.dujia.service.PushSapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.gome.dujia.constant.Constants;
import cn.com.gome.dujia.dto.OrderNoticeDto;
import cn.com.gome.dujia.enums.OrderStatus;
import cn.com.gome.dujia.enums.OrderType;
import cn.com.gome.dujia.enums.PayStatus;
import cn.com.gome.dujia.enums.RefundForm;
import cn.com.gome.dujia.enums.RefundReason;
import cn.com.gome.dujia.enums.RefundSource;
import cn.com.gome.dujia.enums.RefundStatus;
import cn.com.gome.dujia.enums.TrueFalseStatus;
import cn.com.gome.dujia.exception.BizException;
import cn.com.gome.dujia.exception.ErrorCode;
import cn.com.gome.dujia.log.APILog;
import cn.com.gome.dujia.log.LogType;
import cn.com.gome.dujia.mapper.business.OrderMapper;
import cn.com.gome.dujia.model.Order;
import cn.com.gome.dujia.model.OrderRefund;
import cn.com.gome.dujia.model.OrderStatusLog;
import cn.com.gome.dujia.service.CashierService;
import cn.com.gome.dujia.service.OrderCallbackService;
import cn.com.gome.dujia.service.OrderRefundService;
import cn.com.gome.dujia.service.OrderService;
import cn.com.gome.dujia.service.TcService;
import cn.com.gome.dujia.vo.json.order.OrderSearchResult;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.gome.plan.tools.utils.JsonUtils;
import com.gome.plan.tools.utils.MonitorUtil;
import com.gome.plan.tools.utils.StringUtils;
/**
 * Description : 订单回调 包括：收银台回调，供应商回调
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年5月5日 下午3:01:37 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
@Service
@Path("order")
public class OrderCallbackServiceImpl implements OrderCallbackService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderCallbackServiceImpl.class);

    private JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON);

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderRefundService orderRefundService;

	@Autowired
	private TcService tcService;
	
	@Autowired
    private CashierService cashierService;

    @Autowired
    private PushSapService pushSapService;
	
	/**
	 * 收银台支付回调方法
	 * 
	 * @param rspInfo
	 * @return
	 */
    @POST
    @Path("payResponse")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(ContentType.TEXT_PLAIN_UTF_8)
    public String payResponse(@FormParam("rspInfo") String rspInfo) {
    	String response = Constants.PAY_CALLBACK_ERROR;
    	try {
    		if(StringUtils.isEmpty(rspInfo)) {
				logger.error("收银台支付成功回调方法参数不能为空");
				return response;
			}
			logger.info("收银台支付成功回调方法：参数{}", rspInfo);
            
            //参数解析
            Map<String, String> result = cashierService.payResponse(rspInfo);
            if (null == result) {
            	logger.error("收银台支付成功回调方法参数解析失败");
                return response;
            } else {
            	logger.info("收银台支付成功回调方法参数解析成功{}", result);
                //更新订单支付状态
            	response = orderService.updateOrderPayCallBack(result);
                return response;
            }
		} catch (Exception e) {
			logger.error("收银台支付成功回调方法处理失败：", e);
			MonitorUtil.recordOne("pay_callback_exception");
			return response;
		} finally {
			//记录接口日志
			APILog.build("收银台支付成功回调接口", LogType.PAY)
					.appendRequest("request", rspInfo)
					.appendResponse("response", response)
					.record();
		}
    }

    /**
	 * 同程回调通知接口
	 * 
	 * @param orderNoticeDto
	 * @return
	 */
    @POST
    @Path("notice")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(ContentType.TEXT_PLAIN_UTF_8)
    public String orderNotice(@BeanParam OrderNoticeDto orderNoticeDto){
    	Map<String,Object> resultMap = new HashMap<String,Object>();
    	resultMap.put("code", 500);
    	resultMap.put("msg", "操作失败");

    	if (null == orderNoticeDto) {
    		logger.error("同程回调通知接口参数为空");
    		return jsonUtils.serialize(resultMap);
    	}
    	logger.info("同程-订单状态通知接口{}", new JsonUtils().serialize(orderNoticeDto));

		try {
			// 1. 参数验证
			String OperateType = orderNoticeDto.getOperateType();
			if (StringUtils.isBlank(OperateType)) {
				logger.error("操作类型不能为空");
				return jsonUtils.serialize(resultMap);
			}

			String orderId = orderNoticeDto.getOrderId();
			if (StringUtils.isBlank(orderId)) {
				logger.error("操作类型不能为空");
				return jsonUtils.serialize(resultMap);
			}

			Boolean OrderFlag = orderNoticeDto.getOrderFlag();
			if (null == OrderFlag) {// OrderFlag : 确认退款后,订单是否还有效。如果确认退款后，订单无效，视为该订单已全额退款
				logger.error("订单有效性不能为空");
				return jsonUtils.serialize(resultMap);
			}

			// 2.验证订单是否存在
			Order orderParam = new Order();
			orderParam.setVenderOrderId(orderId); // 同程订单号
			//国美订单id
			String ExternalOrderId = orderNoticeDto.getExternalOrderId();
			if (!StringUtils.isBlank(ExternalOrderId)) {
				orderParam.setOrderId(ExternalOrderId);
			}
			//已支付
			orderParam.setOrderPay(PayStatus.PAY_SUCCESS.getValue());
			Order orderInfo = orderMapper.selectOne(orderParam);
			if (null == orderInfo) {
				logger.error("同程订单号{}，国美订单号{}，查询订单不存在", orderId, ExternalOrderId);
				return jsonUtils.serialize(resultMap);
			}
			
			// 操作类型
			if ("Refund".equals(OperateType)) {
				//退款单号
				String RefundNumber = orderNoticeDto.getRefundNumber();
				if (StringUtils.isBlank(RefundNumber)) {
					throw new BizException(ErrorCode.E50002);
				}
				// 验证同程退款单号是否存在
				OrderRefund refundParam = new OrderRefund();
				refundParam.setVenderRefundId(RefundNumber); // 同程退款单号
				OrderRefund orderRefund = orderRefundService.getOrderRefund(refundParam);
				if (null != orderRefund) {
					logger.error("退款单号{}已经存在", RefundNumber);
					return jsonUtils.serialize(resultMap);
				}

				//本次退款金额
				BigDecimal RefundAmount = orderNoticeDto.getRefundAmount();
				//所有退款金额
				//BigDecimal AllRefundAmount = orderNoticeDto.getAllRefundAmount();
				if (null == RefundAmount) {
					logger.error("本次退款金额不能为空");
					return jsonUtils.serialize(resultMap);
				}

				//根据订单号查询退款单
				OrderRefund refund = new OrderRefund();
				refund.setOrderId(orderInfo.getOrderId());
				List<OrderRefund> refundList = orderRefundService.queryOrderRefundList(refund);
				//总退款金额
				BigDecimal tatalRefundAmount = new BigDecimal(0);
				if (null != refundList) {
					for (OrderRefund or : refundList) {
                        if(or.getRefundReason() != RefundReason.REPEAT_PAY_REFUND.ordinal()){
                            tatalRefundAmount = tatalRefundAmount.add(or.getReturnAmount());
                        }
					}
				}
				tatalRefundAmount = tatalRefundAmount.add(RefundAmount);
				
				// 退款总金额=历史退款金额 + 本次退款金额 > 订单金额(异常)
				if (tatalRefundAmount.compareTo(orderInfo.getOrderAmount()) > 0) {
					logger.error("退款总金额{}大于订单总金额{}", tatalRefundAmount, orderInfo.getOrderAmount());
					return jsonUtils.serialize(resultMap);
				}

				OrderRefund newRefund = orderRefundService.createOrderRefund(orderInfo, null, RefundReason.TC_REFUND.ordinal(),RefundSource.TC.ordinal());
				newRefund.setVenderRefundId(RefundNumber);// 同程退款单号
				newRefund.setRefundAmount(RefundAmount); // 退款金额已同程传过来的金额为准
				newRefund.setReturnAmount(RefundAmount); // 退款金额已同程传过来的金额为准
				newRefund.setRefundNote(orderNoticeDto.getRemark());

                // 如果订单金额=退款金额 （全额退款）
                if (orderInfo.getOrderAmount().compareTo(RefundAmount) == 0) {
                    newRefund.setRefundForm(RefundForm.FULL_REFUND.ordinal());
                }else{
                    newRefund.setRefundForm(RefundForm.PART_REFUND.ordinal());
                }
                
                Order newOrder = null;
                if(orderInfo.getOrderAmount().compareTo(tatalRefundAmount) == 0){
                    newOrder = new Order();
                    newOrder.setOrderId(orderInfo.getOrderId());
                    newOrder.setOrderStatus(OrderStatus.WAIT_REFUND.getValue());
                    newOrder.setUpdateTime(new Date());
                }

				//同程退款直接审核通过
				newRefund.setRefundStatus(RefundStatus.AUDIT_SUCCESS.ordinal());
				newRefund.setAuditName("系统审核");
				newRefund.setAuditTime(new Date());
				newRefund.setAuditNote("同程申请退款，系统自动审核通过");

                PushSap negativeSapIncome = null;
                // 如果是已完成的订单又发起退款，直接新增逆向sap收入记录
                if(OrderStatus.ORDER_SUCCESS.getValue() == orderInfo.getOrderStatus()){
                    negativeSapIncome = pushSapService.createPushSap(orderInfo.getOrderId(),newRefund.getRefundId(),SapType.NEGATIVE_SAP_INCOME.getValue());
                    negativeSapIncome.setUpdateTime(new Date());
                }

				//修改订单，新增退款单, 新增逆向sap收入
                orderService.updateOrderRefund(newOrder,newRefund,negativeSapIncome);
			} else if ("CanTravel".equals(OperateType)) {
				// 订单确认可出游
				Map<String, String> map = new HashMap<String, String>();
				map.put("OrderId", orderId); // 同程订单号
				map.put("OrderStatus", "10"); // 同程已确认
				// 调用同程订单查询接口
				OrderSearchResult result = tcService.orderSearch(map);
				if (null == result || null == result.getOrderInfos() || result.getOrderInfos().size() == 0) {
					logger.error("同程订单号{}已确认状态在同程查询不到", orderId);
					return jsonUtils.serialize(resultMap);
				} 
				
				orderInfo.setOrderStatus(OrderStatus.HAS_TC_CONFIRMED.getValue());
				orderInfo.setUpdateTime(new Date());

				// 订单状态表
		        OrderStatusLog orderStatusLog = new OrderStatusLog();
		        orderStatusLog.setOrderId(orderInfo.getOrderId()); // 订单id
		        orderStatusLog.setIsShow(TrueFalseStatus.TRUE.getValue()); // 是否前台显示
		        orderStatusLog.setOrderType(OrderType.ORDER.getValue()); // 订单类型 订单
		        orderStatusLog.setOrderStatus(orderInfo.getOrderStatus()); // 订单状态
		        orderStatusLog.setRecordTime(new Date()); // 记录时间
		        orderStatusLog.setDetails("同程已确认"); // 状态说明
		        
				orderService.updateOrderAndPushSap(orderInfo, null, orderStatusLog);
			}
			resultMap.put("code", 200);
			resultMap.put("msg", "操作成功");
			return jsonUtils.serialize(resultMap);
		} catch (Exception e) {
			logger.error("同程回调通知接口失败", e);
			MonitorUtil.recordOne("tc_order_notice_callback_exception");
			return jsonUtils.serialize(resultMap);
		} finally {
			//记录接口日志
			APILog.build("同程回调通知接口", LogType.TC)
					.appendRequest("request", orderNoticeDto)
					.appendResponse("response", jsonUtils.serialize(resultMap))
					.record();
		}
	}
    
    /*public static void main(String[] args) throws Exception {
    	RequestPackage reqPack = new RequestPackage();
    	reqPack.setUrl("http://localhost:9200/order/notice");
    	Map<String, String> postData = new HashMap<>();
    	postData.put("OperateType", "CanTravel");
    	postData.put("orderId", "100000");
    	postData.put("OrderFlag", "true");
    	reqPack.setPostData(postData);
    	reqPack.setMethod("POST");
    	ResponsePackage res = HttpClientUtils.exec(reqPack);
    	System.out.println(res.getContent());
	}*/

}

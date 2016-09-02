package cn.com.gome.dujia.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.gome.dujia.enums.*;
import cn.com.gome.dujia.service.*;
import cn.com.gome.dujia.vo.refund.OrderRefundInfo;
import com.gome.plan.mybatis.pagehelper.Page;
import com.gome.plan.mybatis.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.mapper.business.OrderMapper;
import cn.com.gome.dujia.mapper.business.OrderRefundMapper;
import cn.com.gome.dujia.mapper.business.OrderStatusLogMapper;
import cn.com.gome.dujia.model.Order;
import cn.com.gome.dujia.model.OrderPay;
import cn.com.gome.dujia.model.OrderRefund;
import cn.com.gome.dujia.model.OrderStatusLog;
import cn.com.gome.dujia.model.PushSap;
import com.gome.plan.tools.utils.BigDecimalUtil;
import com.gome.plan.tools.utils.DateUtils;

/**
 * Description : 退款服务
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月27日 下午5:57:52 <br/>
 *
 * @author WenJie Mai
 * @version 1.0
 */
@Service
public class OrderRefundServiceImpl implements OrderRefundService {

    private static final Logger logger = LoggerFactory.getLogger(OrderRefundServiceImpl.class);

    @Autowired
    private OrderRefundMapper orderRefundMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderStatusLogMapper orderStatusLogMapper;

    @Autowired
    private PushSapService pushSapService;

    @Autowired
    private OrderPayService orderPayService;

    @Autowired
    private IdGeneratorService idGeneratorService;

    @Autowired
    private SmsLogService smsLogService;


    @Override
    public int insert(OrderRefund orderRefund) {
        return orderRefundMapper.insert(orderRefund);
    }

    @Override
    public OrderRefund selectByPrimaryKey(Integer id) {
        return orderRefundMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<OrderRefund> queryOrderRefundList(OrderRefund orderRefund) {

        List<OrderRefund> orderRefundList = orderRefundMapper.queryOrderRefundList(orderRefund);

        if (null == orderRefundList || orderRefundList.size() == 0) {
            return null;
        }
        return orderRefundList;
    }


    @Override
    public void updateRefundAndOrder(Order orderInfo, OrderStatusLog orderLog, OrderRefund orderRefund, OrderStatusLog orderRefundLog, Boolean isSendSapPay, Boolean isSendSapIncome) {

        //1. 修改订单记录
        if (orderInfo != null) {
            orderMapper.updateOrderInfo(orderInfo);
        }

        //2. 创建订单日志记录(订单)
        if (orderLog != null) {
            orderStatusLogMapper.insert(orderLog);
        }

        //3. 创建退款单记录
        if (orderRefund != null) {
            orderRefundMapper.updateOrderRefund(orderRefund);
        }

        //4. 创建订单日志记录(退款单)
        if (orderRefundLog != null) {
            orderStatusLogMapper.insert(orderRefundLog);
        }

        //5.推送sap逆向收款
        if (isSendSapPay) {
            PushSap sapPay = pushSapService.createPushSap(orderRefund.getOrderId(), orderRefund.getRefundId(), SapType.NEGATIVE_SAP_PAY.getValue());
            if (sapPay != null) {
                sapPay.setUpdateTime(new Date());
                pushSapService.insert(sapPay);
            }
        }

        //6.推送sap逆向收入
        if (isSendSapIncome) {
            PushSap sapIncome = pushSapService.createPushSap(orderRefund.getOrderId(), orderRefund.getRefundId(), SapType.NEGATIVE_SAP_INCOME.getValue());
            if (sapIncome != null) {
                sapIncome.setUpdateTime(new Date());
                pushSapService.insert(sapIncome);
            }
        }
    }

    public List<OrderRefund> queryOrderRefundByPushSap(Map<String, Object> map) {
        if (!CollectionUtils.isEmpty(map)) {
            return orderRefundMapper.queryOrderRefundByPushSap(map);
        }
        return null;
    }

    @Override
    public void updateAndinsert(OrderRefund orderRefund, OrderStatusLog orderRefundLog) {

        // 1. 更新退款单记录
        if (orderRefund != null) {
            orderRefundMapper.updateOrderRefund(orderRefund);
        }

        // 2. 创建订单日志记录(退款单)
        if (orderRefundLog != null) {
            orderStatusLogMapper.insert(orderRefundLog);
        }
    }


    @Override
    public OrderRefund createOrderRefund(Order orderInfo, Map<String, String> map, Integer refundReason, Integer refundSource) {

        if (null != orderInfo && null != refundReason && null != refundSource) {
            OrderRefund orderRefund = new OrderRefund();
            orderRefund.setUserId(orderInfo.getUserId());     // 用户id
            orderRefund.setUserName(orderInfo.getUserName()); // 用户名
            orderRefund.setOrderId(orderInfo.getOrderId());  // 订单id
            orderRefund.setVenderOrderId(orderInfo.getVenderOrderId());   // 供应商订单id
            orderRefund.setRefundId(idGeneratorService.getRefundId());   // 退款单id
            orderRefund.setDeductMount(new BigDecimal(0));      // 扣款金额
            orderRefund.setCompensateAmount(new BigDecimal(0)); // 赔款金额
            orderRefund.setPenaltyAmount(new BigDecimal(0));    // 手续费
            orderRefund.setVenderRefundId("");                  // 供应商退款单号
            orderRefund.setRefundNote("");
            BigDecimal payMoney = null;
            String tranNo = "";

            if (CollectionUtils.isEmpty(map)) {
                // 根据订单id查询正常支付的支付信息
                OrderPay orderPay = orderPayService.getOrderPayByOrderId(orderInfo.getOrderId());
                if (null != orderPay) {
                    orderRefund.setPayMode(orderPay.getPayMode());         // 支付方式
                    orderRefund.setPayModeSap(orderPay.getPayModeSap()); // sap支付方式
                    orderRefund.setPayTime(orderPay.getPayTime());         // 支付时间
                    orderRefund.setTransNo(orderPay.getMerchantNo());     // 交易流水号 供应商交易流水号
                    payMoney = orderPay.getPayMoney();                     // 支付金额
                    tranNo = orderPay.getId();
                }
            } else {
                orderRefund.setPayMode(map.get("payMode"));              // 支付方式
                orderRefund.setPayModeSap(map.get("sapCode"));              // sap支付方式
                orderRefund.setPayTime(strToDate(map.get("payTime")));    // 支付时间
                orderRefund.setTransNo(map.get("merchantNo"));              // 交易流水号 供应商交易流水号
                payMoney = BigDecimalUtil.changeF2Y(map.get("payMoney")); // 支付金额
                tranNo = GlobalDisconf.getPayPrefix() + map.get("transNo");
            }

            orderRefund.setPayAmount(payMoney);        // 支付总金额
            orderRefund.setRefundReason(refundReason); // 退款原因
            orderRefund.setRefundMode(RefundMode.RETURN_BY_THE_WAY.ordinal());     // 目前默认是：原路返回
            orderRefund.setRefundSource(refundSource);                             // 退款单来源
            orderRefund.setCreateTime(new Date());                                 // 创建时间
            orderRefund.setUpdateTime(new Date());

            PushSap sap = new PushSap(orderInfo.getOrderId(), SapType.POSITIVE_SAP_INCOME.getValue(), tranNo);
            List<PushSap> sapList = pushSapService.queryPushSap(sap);

            logger.info("+++++++++++++++++createOrderRefund_orderId:" + orderInfo.getOrderId() + "_sapList_Size:" + sapList == null ? "0" : String.valueOf(sapList.size()));

            // 同程退款
            if (refundReason == 0 && RefundSource.TC.ordinal() == refundSource) {
                orderRefund.setRefundStatus(RefundStatus.AUDIT_SUCCESS.ordinal());// 退款单状态:财务审核通过
            } else {
                orderRefund.setRefundStatus(RefundStatus.INIT.ordinal());// 退款单状态 等待财务审核
                orderRefund.setRefundAmount(payMoney); // 退款金额
                orderRefund.setReturnAmount(payMoney); // 最终退款的金额
                orderRefund.setRefundForm(RefundForm.FULL_REFUND.ordinal());//国美的退款都是全额退款
            }

            // 通过正向订单是否有正向收入,来区分退款类型
            if (sapList != null && sapList.size() > 0) {
                orderRefund.setRefundType(RefundPayType.REFUND_INCOME.ordinal());    // 退款类型:收入
            } else {
                orderRefund.setRefundType(RefundPayType.REFUND_PAY.ordinal());       // 退款类型:收款
            }

            OrderStatusLog orderStatusLog = new OrderStatusLog(orderRefund.getRefundId(), false, orderRefund.getRefundStatus(), OrderType.REFUND.getValue(), "申请退款", new Date(), new Date());

            // 订单状态表-申请退款单
            if (RefundSource.GOME.ordinal() == refundSource) {
                orderStatusLog.setOrderStatus(RefundStatus.INIT.ordinal()); // 退款单状态
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("orderId", orderInfo.getOrderId());
                paramMap.put("refundAmount", orderRefund.getRefundAmount());
                smsLogService.sendSms(SmsType.VENDER_PAY_REFUND.getDiffId(), orderInfo.getContactsTelphone(), paramMap);
            }

            orderStatusLogMapper.insert(orderStatusLog);

            return orderRefund;
        }
        return null;
    }

    /**
     * 把字符串转成日期类型
     *
     * @param time
     * @return
     */
    private Date strToDate(String time) {
        try {
            return DateUtils.parse(time, DateUtils.LONG_FORMAT);
        } catch (ParseException e) {
            logger.error("收银台支付成功回调方法支付时间{}格式错误", time);
            return null;
        }
    }

    @Override
    public Page<OrderRefundInfo> queryOrderRefundInfoList(OrderRefundInfo refundInfo, int currentPage, int pageSize) {

        Page<OrderRefundInfo> pageVenderList = PageHelper.startPage(currentPage, pageSize);

        orderRefundMapper.queryOrderRefundInfoList(refundInfo);

        return pageVenderList;
    }

    @Override
    public List<OrderRefundInfo> queryOrderRefundInfo(OrderRefundInfo refundInfo) {
        return orderRefundMapper.queryOrderRefundInfoList(refundInfo);
    }

    @Override
    public OrderRefund queryOrderRefund(String refundId) {
        return orderRefundMapper.queryOrderRefund(refundId);
    }

    @Override
    public OrderRefund getOrderRefund(OrderRefund orderRefund) {
        return orderRefundMapper.selectOne(orderRefund);
    }

	@Override
	public int updateOrderRefund(OrderRefund orderRefund) {
		return orderRefundMapper.updateOrderRefund(orderRefund);
	}

    @Override
    public List<OrderRefund> queryRefundList(OrderRefundInfo refundInfo) {
        return orderRefundMapper.queryRefundList(refundInfo);
    }
}

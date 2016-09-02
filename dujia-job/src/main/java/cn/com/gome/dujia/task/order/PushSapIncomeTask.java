package cn.com.gome.dujia.task.order;

import cn.com.gome.dujia.enums.OrderStatus;
import cn.com.gome.dujia.enums.PayStatus;
import cn.com.gome.dujia.enums.RefundStatus;
import cn.com.gome.dujia.enums.SapType;
import cn.com.gome.dujia.model.Order;
import cn.com.gome.dujia.model.OrderPay;
import cn.com.gome.dujia.model.OrderRefund;
import cn.com.gome.dujia.model.PushSap;
import cn.com.gome.dujia.service.OrderPayService;
import cn.com.gome.dujia.service.OrderRefundService;
import cn.com.gome.dujia.service.OrderService;
import cn.com.gome.dujia.service.PushSapService;
import cn.com.gome.dujia.task.common.AbstractTask;
import cn.com.gome.dujia.vo.order.OrderQueryParam;
import cn.com.gome.dujia.vo.refund.OrderRefundInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Description  : sap收入(包括正、逆向)
 * Copyright    : Copyright (c) 2008- 2016 All rights reserved;
 * Created Time : 2016/6/16 14:00;
 *
 * @author WenJie Mai
 * @version 1.0
 */
@Component
public class PushSapIncomeTask extends AbstractTask {

    private static final Logger logger = LoggerFactory.getLogger(PushSapIncomeTask.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private PushSapService pushSapService;

    @Autowired
    private OrderPayService orderPayService;

    @Autowired
    private OrderRefundService orderRefundService;

    @Override
    public String getTaskName() {
        return "sap收入推送";
    }

    @Override
    public void doExecute() {

        logger.info("----------------sap收入推送...开始----------------");

        try {
              List<PushSap> positiveSapIncomes = new ArrayList<PushSap>();   //正向sap收入
              List<PushSap> negativeSapIncomes = new ArrayList<PushSap>();  // 逆向sap收入

              OrderQueryParam orderParam = new OrderQueryParam();
              orderParam.setOrderStatus(OrderStatus.ORDER_SUCCESS.getValue());     // 已完成
              orderParam.setPayStatus(PayStatus.PAY_SUCCESS.getValue());          //  已支付
              orderParam.setVenderOrderId("venderOrderId");                       //  判断第三方订单号是否为空
              orderParam.setJudgeEndTime("travelEndTime");                        //  非空判断出游结束日期
              orderParam.setOrderSapParam("orderSapParam");                       //  判断dj_push_sap条件
              List<Order> orderList = orderService.queryOrderListByParam(orderParam);

              // 正向sap收入
              if(!CollectionUtils.isEmpty(orderList)){
                  for(Order orderInfo : orderList){
                      // 根据订单id查询正常支付的支付信息
                      OrderPay orderPay = orderPayService.getOrderPayByOrderId(orderInfo.getOrderId());
                      if (null != orderPay) {
                          logger.info("+++++++++++++positiveIncome_orderId_"+orderInfo.getOrderId()+"+++++++++++++++++");
                          PushSap positiveIncome = pushSapService.createPushSap(orderInfo.getOrderId(),String.valueOf(orderPay.getId()),SapType.POSITIVE_SAP_INCOME.getValue());
                          positiveIncome.setUpdateTime(new Date());
                          positiveSapIncomes.add(positiveIncome);
                      }
                  }
              }


              OrderQueryParam refundParam = new OrderQueryParam();
              refundParam.setOrderStatus(OrderStatus.ORDER_SUCCESS.getValue());     // 已完成
              refundParam.setPayStatus(PayStatus.PAY_SUCCESS.getValue());          //  已支付
              refundParam.setVenderOrderId("venderOrderId");                       //  判断第三方订单号是否为空
              refundParam.setJudgeEndTime("travelEndTime");                        //  非空判断出游结束日期
              refundParam.setRefundSapParam("refundSapParam");                     //  判断dj_push_sap条件
              List<Order> rfList = orderService.queryOrderListByParam(refundParam);

              // 逆向sap收入
              if(!CollectionUtils.isEmpty(rfList)){
                  List<String> orderIdList = new ArrayList<String>();
                  for(Order ox : rfList){
                      orderIdList.add(ox.getOrderId());
                  }

                  OrderRefundInfo refundInfo = new OrderRefundInfo();
                  refundInfo.setOrderIdList(orderIdList);
                  refundInfo.setRefundStatus(RefundStatus.REFUND_COMPLETE.ordinal());
                  List<OrderRefund> refundInfoList = orderRefundService.queryRefundList(refundInfo);

                  if(!CollectionUtils.isEmpty(refundInfoList)){
                      for(OrderRefund fund : refundInfoList){
                          PushSap sapIncome = pushSapService.createPushSap(fund.getOrderId(),fund.getRefundId(),SapType.NEGATIVE_SAP_INCOME.getValue());
                          sapIncome.setUpdateTime(new Date());
                          negativeSapIncomes.add(sapIncome);
                      }
                  }
              }

              pushSapService.batchInsert(positiveSapIncomes,negativeSapIncomes);
        } catch (Exception e) {
            logger.error("sap收入推送", e);
        }

        logger.info("----------------sap收入推送...结束----------------");
    }
}

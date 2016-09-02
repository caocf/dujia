package cn.com.gome.dujia.task.order;

import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.enums.OrderStatus;
import cn.com.gome.dujia.enums.PayStatus;
import cn.com.gome.dujia.enums.SmsType;
import cn.com.gome.dujia.model.Order;
import cn.com.gome.dujia.model.OrderPay;
import cn.com.gome.dujia.service.OrderPayService;
import cn.com.gome.dujia.service.OrderService;
import cn.com.gome.dujia.service.RedisCacheService;
import cn.com.gome.dujia.service.SmsLogService;
import cn.com.gome.dujia.task.common.AbstractTask;
import com.gome.plan.tools.utils.StringUtils;
import com.gome.plan.tools.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Description  : 未通知订单状态
 * Copyright    : Copyright (c) 2008- 2016 All rights reserved;
 * Created Time : 2016/5/18 20:23;
 *
 * @author WenJie Mai
 * @version 1.0
 */
@Component
public class OrderNoNoticeStatusTask extends AbstractTask {

    private static final Logger logger = LoggerFactory.getLogger(OrderNoNoticeStatusTask.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderPayService orderPayService;

    @Resource
    private SmsLogService smsLogService;

    @Autowired
    private RedisCacheService redisCacheService;
    
    @Override
    public String getTaskName() {
        return "同程未确认提醒";
    }


    /**
     * 1. 查询订单状态=WAIT_TC_CONFIRM(待同程确认) AND 支付状态=已支付
     * 2. 支付时间超过3个小时
     * 3. 发送业务短信
     */
    @Override
    public void doExecute() {

        logger.info("----------------未通知订单状态任务...开始----------------");

        try{
            Order orderParam = new Order();
            orderParam.setOrderStatus(OrderStatus.WAIT_TC_CONFIRM.getValue());
            orderParam.setOrderPay(PayStatus.PAY_SUCCESS.getValue());
            List<Order> orderList = orderService.queryOrderList(orderParam);

            if(!CollectionUtils.isEmpty(orderList)){
                Date nowDate = new Date();
                for (Order ox : orderList){
                	String key = "ALARM_" + ox.getOrderId();
                	if(null == redisCacheService.get(key)){// 如果缓存中不存在,再查数据库
                		OrderPay orderPay = new OrderPay();
                        orderPay.setOrderId(ox.getOrderId()); // 订单号
                        orderPay.setIsRepeatPay(false);      // 正常支付
                        OrderPay pay = orderPayService.getOrderPay(orderPay);//查询退款单对应支付信息
                        if(pay != null){
                            Date payTime  = pay.getPayTime();
                            Date warnDate = DateUtils.addMinutes(payTime,180);
                            if(warnDate.before(nowDate)){
                                String phones = GlobalDisconf.getBusinessWarnPhones();
                                if(!StringUtils.isBlank(phones)){
                                    String px[] = phones.split(",");
                                    Map<String, Object> paramMap = new HashMap<String,Object>();
                                    paramMap.put("orderId",ox.getOrderId());
                                    paramMap.put("venderOrderId",ox.getVenderOrderId());
                                    paramMap.put("paytime",DateUtils.format(payTime, DateUtils.LONG_WEB_FORMAT));
                                    for(int i=0;i<px.length;i++){
                                    	smsLogService.sendSms(SmsType.ALARM.getDiffId(),px[i],paramMap);
                                    }
                                    
                                    try{
                                    	redisCacheService.set(key,"1",86400);//有效期：24小时
                                    }catch (Exception e){
                                        continue;
                                    }
                                }
                            }
                        }
                	}
                }
            }
        } catch (Exception e) {
            logger.error("未通知订单状态任务失败", e);
        }

        logger.info("----------------未通知订单状态任务...结束----------------");
    }
}

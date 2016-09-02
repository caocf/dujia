package cn.com.gome.dujia.service;

import cn.com.gome.dujia.model.OrderStatusLog;
import java.util.List;
/**
 * Description : 订单状态日志
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月5日 上午11:30:48 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public interface OrderStatusLogService {


    /**
     * 查询去重后订单状态
     * @param orderStatusLog
     * @return
     */
    List<OrderStatusLog> queryOrderStatusLogNoRepeat(OrderStatusLog orderStatusLog);
    
    /**
     * 批量新增订单状态记录
     * @param record
     * @return
     */
     void batchInsert(List<OrderStatusLog> orderStatusLogs);
}

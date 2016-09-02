package cn.com.gome.dujia.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.gome.plan.tools.utils.JsonUtils;

import cn.com.gome.dujia.mapper.business.OrderStatusLogMapper;
import cn.com.gome.dujia.model.OrderStatusLog;
import cn.com.gome.dujia.service.OrderStatusLogService;
/**
 * Description  : 订单状态服务
 * Copyright    : Copyright (c) 2008- 2016 All rights reserved;
 * Created Time : 2016/5/13 10:19;
 *
 * @author WenJie Mai
 * @version 1.0
 */
@Service
public class OrderStatusLogServiceImpl implements OrderStatusLogService {
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	private static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON).ignoreEmpty();
	
    @Autowired
    private OrderStatusLogMapper orderStatusLogMapper;

    @Override
    public List<OrderStatusLog> queryOrderStatusLogNoRepeat(OrderStatusLog orderStatusLog) {

        List<OrderStatusLog> orderStatusLogList = orderStatusLogMapper.queryOrderStatusLogNoRepeat(orderStatusLog);

        if(CollectionUtils.isEmpty(orderStatusLogList)){
            return null;
        }

        return orderStatusLogList;
    }

	@Override
	public void batchInsert(List<OrderStatusLog> orderStatusLogs) {
		try {
			orderStatusLogMapper.batchInsert(orderStatusLogs);
		} catch (Exception e) {
			logger.error("批量插入订单状态失败订单状态：{}", jsonUtils.serialize(orderStatusLogs));
			
		}
	}

}

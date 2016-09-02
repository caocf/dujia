package cn.com.gome.dujia.task.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.gome.plan.tools.utils.DateUtils;
import com.gome.plan.tools.utils.StringUtils;

import cn.com.gome.dujia.enums.IncrementReason;
import cn.com.gome.dujia.enums.OrderType;
import cn.com.gome.dujia.model.Order;
import cn.com.gome.dujia.model.OrderStatusLog;
import cn.com.gome.dujia.service.OrderService;
import cn.com.gome.dujia.service.OrderStatusLogService;
import cn.com.gome.dujia.service.RedisCacheService;
import cn.com.gome.dujia.service.TcService;
import cn.com.gome.dujia.task.common.AbstractTask;
import cn.com.gome.dujia.task.rb.RefundSendRbTask;
import cn.com.gome.dujia.vo.order.OrderIncrementInfo;
/**
 * @author wangweiran
 *
 * 同步第三方订单增量任务
 */
@Component
public class OrderIncrementTask extends AbstractTask{
	private static final Logger logger = LoggerFactory.getLogger(RefundSendRbTask.class);
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderStatusLogService orderStatusLogService;
	
	@Autowired
	private TcService tcService;
	
	@Autowired
	private RedisCacheService redisCacheService;
	
	private static final String ORDER_INCREMENT_TASK_KEY = "DUJIA_ORDER_INCREMENT_TASK_KEY";
	
	@Override
	public String getTaskName() {
		return "订单增量任务";
	}

	@Override
	public void doExecute() throws Exception {
		String lastTime = DateUtils.format(new Date(), DateUtils.LONG_WEB_FORMAT);
		logger.info("同程订单增量任务执行，执行时间：{}", lastTime);
		Map<String, String> map = new HashMap<>();
		//最后一次更新时间，存缓存
		redisCacheService.set(ORDER_INCREMENT_TASK_KEY, lastTime, 0);
		//时间格式2015-8-26 00:00:00
		String prevTime = redisCacheService.get(ORDER_INCREMENT_TASK_KEY, String.class);
		if(StringUtils.isEmpty(prevTime)){
			prevTime = "2015-05-26 00:00:00";
		}
		map.put("StartTime", prevTime);
		map.put("EndTime", lastTime);
		List<OrderIncrementInfo> incrementInfos = tcService.getOrderIncrementInfo(map);
		if(!CollectionUtils.isEmpty(incrementInfos)){
			StringBuffer orderIds = new StringBuffer();
			for(int i = 0; i < incrementInfos.size(); i++){
				orderIds.append(incrementInfos.get(i).getOrderId());
				if(i < incrementInfos.size())
					orderIds.append(",");
			}
			List<Order> orderList = orderService.getOrderByVenderOrderIds(orderIds.toString());
			if(!CollectionUtils.isEmpty(orderList)){
				List<OrderStatusLog> orderStatusLogs = new ArrayList<>();
				for(OrderIncrementInfo info : incrementInfos){
					Integer orderType = 0;
					if(info.getIncrementReason().equals(IncrementReason.ORDER_CANCEL) || 
							info.getIncrementReason().equals(IncrementReason.ORDER_REFUND) || 
								info.getIncrementReason().equals(IncrementReason.ORDER_REFUND_FINISH)){
						//退款
						orderType = OrderType.REFUND.getValue();
					}
					else{
						orderType = OrderType.ORDER.getValue();
					}
					for(Order order : orderList){
						if(order.getVenderOrderId().equals(info.getOrderId())){
							OrderStatusLog orderStatusLog = new OrderStatusLog();
							orderStatusLog.setDetails("同程状态：" + info.getOrderFlag().getName());
							orderStatusLog.setIsShow(false);
							orderStatusLog.setOrderId(order.getOrderId());
							orderStatusLog.setOrderStatus(info.getOrderFlag().getValue());
							orderStatusLog.setOrderType(orderType);
							orderStatusLog.setRecordTime(new Date());
							orderStatusLogs.add(orderStatusLog);
						}
					}
				}
				if(!CollectionUtils.isEmpty(orderStatusLogs)){
					orderStatusLogService.batchInsert(orderStatusLogs);
				}
			}
		}
		else{
			logger.info("同程订单增量任务执行结果为空！");
		}
	}

}

package cn.com.gome.dujia.task.order;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.enums.PushStatus;
import cn.com.gome.dujia.model.PushVender;
import cn.com.gome.dujia.service.OrderService;
import cn.com.gome.dujia.service.PushVenderService;
import cn.com.gome.dujia.task.common.AbstractTask;
/**
 * 推送供应商下单
 * 
 * @author xiongyan
 * @date 2016年5月12日 下午12:07:20
 */
@Component
public class PushVenderTask extends AbstractTask {

	private static final Logger logger = LoggerFactory.getLogger(PushVenderTask.class);

	@Autowired
	private PushVenderService pushVenderService;
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 任务名称
	 */
	public String getTaskName() {
		return "推送供应商下单";
	}

	/**
	 * 具体要执行的任务
	 */
	public void doExecute() {
		try {
			// 获取推送供应商下单任务 推送状态（待推送和推送失败），推送次数（最大重推次数）
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("statusList", Arrays.asList(PushStatus.WAIT_PUSH.getValue(), PushStatus.PUSH_FAILD.getValue()));// 待推送和推送失败
			map.put("pushNum", GlobalDisconf.pushVenderMaxNumber); // 最大重推次数
			List<PushVender> pushVenderList = pushVenderService.queryPushVender(map);

			if (!CollectionUtils.isEmpty(pushVenderList)) {
				for (PushVender vender : pushVenderList) {
					logger.info("推送供应商下单任务：订单号{}", vender.getOrderId());
					try {
						orderService.updatePushVender(vender);
					} catch (Exception e) {
						logger.error("推送供应商下单任务失败：订单号{}", vender.getOrderId(), e);
					}
				}
			}
		} catch (Exception e) {
			logger.error("推送供应商下单失败", e);
		}
	}

}

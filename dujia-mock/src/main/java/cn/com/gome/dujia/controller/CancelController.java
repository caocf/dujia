package cn.com.gome.dujia.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.gome.dujia.dto.ResponseDto;
import cn.com.gome.dujia.enums.OrderStatus;
import cn.com.gome.dujia.mapper.business.OrderMapper;
import cn.com.gome.dujia.model.Order;
import cn.com.gome.dujia.service.OrderService;
import cn.com.gome.dujia.service.TcService;
import cn.com.gome.dujia.vo.order.CancelResult;

import com.gome.plan.tools.utils.StringUtils;

@Controller
@RequestMapping(value = "/mock/cancel")
public class CancelController {

	public static final Logger logger = LoggerFactory.getLogger(CancelController.class);

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private TcService tcService;
	
	/**
	 * 订单列表
	 * @param model
	 * @param orderStatus
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(Model model, String orderId) {
		if(StringUtils.isNotEmpty(orderId)) {
			Order orderInfo = new Order();
			orderInfo.setOrderId(orderId);
			orderInfo.setOrderPay(1);
			List<Order> orders = orderService.queryOrderList(orderInfo);
			if(null != orders && orders.size() > 0) {
				List<Order> newOrders = new ArrayList<Order>();
				for (Order order : orders) {
					if (OrderStatus.WAIT_TC_CONFIRM.getValue().equals(order.getOrderStatus())
							|| OrderStatus.HAS_TC_CONFIRMED.getValue().equals(order.getOrderStatus())
							|| OrderStatus.ORDER_SUCCESS.getValue().equals(order.getOrderStatus())) {
						newOrders.add(order);
					}
				}
				if (newOrders.size() > 0) {
					model.addAttribute("orders", newOrders);
				}else{
					model.addAttribute("error", "无查询结果");
				}
			}else{
				model.addAttribute("error", "无查询结果");
			}
		}
		model.addAttribute("orderId", orderId);
		return "cancel_list";
	}
	
	
	/**
	 * 取消订单
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDto cancel(@RequestParam(value = "orderId", required = true) String orderId) {
		try {
			Order orderInfoObj = new Order();
			orderInfoObj.setOrderId(orderId);
			orderInfoObj.setOrderPay(1);
			Order order = orderMapper.selectOne(orderInfoObj);
			if(null != order) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("OrderId", order.getVenderOrderId()); //同程订单号
				CancelResult result = tcService.OrderRefundApply(map);
				if(null != result && result.getIsSuccessed()) {
					return ResponseDto.bulidSuccess();
				}
			}
		} catch (Exception e) {
			logger.error("修改订单状态失败：订单号{}", orderId, e);
		}
		return ResponseDto.bulidFail();
	}
	
}

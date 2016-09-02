package cn.com.gome.dujia.controller;

import java.util.Date;
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
import cn.com.gome.dujia.mapper.business.OrderMapper;
import cn.com.gome.dujia.model.Order;
import cn.com.gome.dujia.model.OrderRefund;
import cn.com.gome.dujia.service.OrderRefundService;
import cn.com.gome.dujia.service.OrderService;

import com.gome.plan.tools.http.HttpClientUtils;
import com.gome.plan.tools.http.RequestPackage;
import com.gome.plan.tools.http.ResponsePackage;
import com.gome.plan.tools.utils.DateUtils;
import com.gome.plan.tools.utils.StringUtils;

@Controller
@RequestMapping(value = "/mock/refund")
public class RefundController {

	public static final Logger logger = LoggerFactory.getLogger(RefundController.class);

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderRefundService orderRefundService;
	
	@Autowired
	private OrderMapper orderMapper;
	
	/**
	 * 订单列表
	 * @param model
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(Model model, String orderId) {
		if(StringUtils.isNotEmpty(orderId)) {
			OrderRefund orderRefund = new OrderRefund();
			orderRefund.setOrderId(orderId);
			List<OrderRefund> orderRefunds = orderRefundService.queryOrderRefundList(orderRefund);
			if(null != orderRefunds) {
				model.addAttribute("error", "已经申请退款");
			}else{
				Order orderInfo = new Order();
				orderInfo.setOrderId(orderId);
				orderInfo.setOrderPay(1);
				List<Order> orders = orderService.queryOrderList(orderInfo);
				if(null != orders && orders.size() > 0) {
					model.addAttribute("orders", orders);
				}else{
					model.addAttribute("error", "无查询结果");
				}
			}
		}
		model.addAttribute("orderId", orderId);
		return "refund_list";
	}
	
	/**
	 * 申请退款	
	 * @param orderId
	 * @param orderStatus
	 * @return
	 */
	@RequestMapping(value = "/applyRefund", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDto applyRefund(@RequestParam(value = "orderId", required = true) String orderId) {
		try {
			Order orderInfoObj = new Order();
			orderInfoObj.setOrderId(orderId);
			orderInfoObj.setOrderPay(1);
			Order order = orderMapper.selectOne(orderInfoObj);
			if(null != order) {
	    		RequestPackage pp = new RequestPackage();
	    		pp.setUrl("http://dujia.atguat.com.cn/api/order/notice");
	    		Map<String, String> postData = new HashMap<String, String>();
	    		postData.put("OperateType", "Refund");
	    		postData.put("orderId", order.getVenderOrderId());
	    		postData.put("OrderFlag", "true");
	    		postData.put("RefundNumber", DateUtils.format(new Date(), DateUtils.LONG_FORMAT));
	    		postData.put("RefundAmount", order.getOrderAmount().toString());
	    		pp.setPostData(postData);
	    		pp.setMethod("POST");
	    		ResponsePackage resp = HttpClientUtils.exec(pp);
	    		if(null != resp) {
    				if(200 == resp.getHttpStatus()) {
    					return ResponseDto.bulidSuccess(orderId + " 申请退款成功");
    				}else{
    					return ResponseDto.bulidFail(orderId + " 申请退款失败");
	    			}
	    		}
			}
		} catch (Exception e) {
			logger.error("订单申请退款失败：订单号{}", orderId, e);
		}
		return ResponseDto.bulidFail();
	}
	
}

package cn.com.gome.dujia.service;

import cn.com.gome.dujia.dto.OrderNoticeDto;
/**
 * Description : Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年5月5日 下午3:01:19 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public interface OrderCallbackService {

	/**
	 * 收银台支付回调方法
	 * 
	 * @param rspInfo
	 * @return
	 */
	public String payResponse(String rspInfo);

	/**
	 * 同程回调通知接口
	 * 
	 * @param orderNoticeDto
	 * @return
	 */
	public String orderNotice(OrderNoticeDto orderNoticeDto);
}

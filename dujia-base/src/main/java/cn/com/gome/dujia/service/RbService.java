package cn.com.gome.dujia.service;

import cn.com.gome.dujia.dto.ResponseDto;
import cn.com.gome.dujia.exception.BizException;
import cn.com.gome.dujia.model.RbRefund;
import cn.com.gome.dujia.ws.client.rb.AcceptRefundTask;
/**
 * Description  :
 * Copyright    : Copyright (c) 2008- 2016 All rights reserved;
 * Created Time : 2016/4/18 12:10;
 *
 * @author WenJie Mai
 * @version 1.0
 */
public interface RbService {

	/**
	 * 建立rb连接
	 * @author WenJie Mai
	 *
	 * @param url
	 * @return
	 * @throws BizException
	 */
	public AcceptRefundTask buildAcceptRefundTaskProxy(String url) throws BizException;
	
    /**
     *  推送rb
     */
    public ResponseDto sendRb(RbRefund rb);
}

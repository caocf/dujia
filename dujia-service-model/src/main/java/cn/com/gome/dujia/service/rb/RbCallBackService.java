package cn.com.gome.dujia.service.rb;

import cn.com.gome.dujia.dto.PlatformResponseDto;
/**
 * Description : rb回调
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月29日 下午2:23:46 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public interface RbCallBackService {

	/**
	 * rb退款回调方法
	 * 
	 * @author WenJie Mai
	 *
	 * @param refundNo           业务编号ID
	 * @param state			            业务状态
	 * @param refundFinishTime   完成时间
	 * @param refundTransId      业务流水号
	 * @param sign				  签名
	 * @return
	 */
	public PlatformResponseDto rbRefundCallBack(String refundNo,String state,String refundFinishTime,String refundTransId,String sign);
}

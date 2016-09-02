package cn.com.gome.dujia.vo.sap;

import cn.com.gome.dujia.model.PushSap;
/**
 * 
 * Description : sap业务实体
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月29日 下午2:21:01 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public class PushSapInfo extends PushSap {

	/**
	 * @author WenJie Mai
	 *
	 * Created Time : 2016年4月29日 下午2:21:47 <br/>
	 */
	private static final long serialVersionUID = -3283827368888305877L;
	
	/**
	 * 最大推送次数
	 */
	private Integer maxPushNum;
	
	public Integer getMaxPushNum() {
		return maxPushNum;
	}

	public void setMaxPushNum(Integer maxPushNum) {
		this.maxPushNum = maxPushNum;
	}

}

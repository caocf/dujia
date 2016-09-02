package cn.com.gome.dujia.service.sap;

import java.util.List;
import cn.com.gome.dujia.vo.sap.SapIncomeResultItem;
import cn.com.gome.dujia.vo.sap.SapIncomeResultResp;
/**
 * 
 * Description : sap收入回调
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年4月29日 下午2:22:53 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
public interface SapIncomeCallBackService {

	/**
	 * 提供给sap收入回调的服务方法
	 * @param sapIncomeResultItems
	 * @return
	 */
	SapIncomeResultResp sapIncomeAuditResult(List<SapIncomeResultItem> sapIncomeResultItems); 
}

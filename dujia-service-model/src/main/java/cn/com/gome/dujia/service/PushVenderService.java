package cn.com.gome.dujia.service;

import java.util.List;
import java.util.Map;

import cn.com.gome.dujia.model.PushVender;

/**
 * 推送供应商下单服务
 * 
 * @author xiongyan
 * @date 2016年5月12日 下午12:11:20
 */
public interface PushVenderService {

	/**
	 * 保存
	 * 
	 * @param pushVender
	 */
	public void insert(PushVender pushVender);
	
	/**
	 * 保存
	 * 
	 * @param pushVender
	 */
	public void update(PushVender pushVender);
	
	/**
	 * 查询
	 * 
	 * @param map
	 * @return
	 */
	public List<PushVender> queryPushVender(Map<String, Object> map);

    /**
     * 查询第三方推送信息
     * @param orderId
     * @return
     */
    public PushVender queryPushVenderInfo(String orderId);
}

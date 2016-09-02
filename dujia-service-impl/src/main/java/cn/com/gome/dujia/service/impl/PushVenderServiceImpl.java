package cn.com.gome.dujia.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.gome.dujia.mapper.business.PushVenderMapper;
import cn.com.gome.dujia.model.PushVender;
import cn.com.gome.dujia.service.PushVenderService;

/**
 * 推送供应商下单服务
 * 
 * @author xiongyan
 * @date 2016年5月12日 下午12:15:06
 */
@Service
public class PushVenderServiceImpl implements PushVenderService {

	@Autowired
	private PushVenderMapper pushVenderMapper;
	
	/**
	 * 保存
	 * 
	 * @param pushVender
	 */
	public void insert(PushVender pushVender) {
		if (null != pushVender) {
			pushVenderMapper.insertSelective(pushVender);
		}
	}
	
	/**
	 * 保存
	 * 
	 * @param pushVender
	 */
	public void update(PushVender pushVender) {
		if (null != pushVender) {
			pushVenderMapper.updateByPrimaryKeySelective(pushVender);
		}
	}
	
	/**
	 * 查询
	 * 
	 * @param map
	 * @return
	 */
	public List<PushVender> queryPushVender(Map<String, Object> map) {
		if (null != map) {
			return pushVenderMapper.queryPushVender(map);
		}
		return null;
	}

    @Override
    public PushVender queryPushVenderInfo(String orderId) {
        return pushVenderMapper.queryPushVenderInfo(orderId);
    }
}

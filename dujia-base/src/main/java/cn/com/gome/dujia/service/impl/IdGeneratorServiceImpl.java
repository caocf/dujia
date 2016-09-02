package cn.com.gome.dujia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.service.IdGeneratorService;

import com.gome.plan.tools.curator.IdGenerator.IIdGenerator;

/**
 * id生成器
 * 
 * @author xiongyan
 * @date 2016年5月6日 上午9:51:03
 */
@Service
public class IdGeneratorServiceImpl implements IdGeneratorService {

	@Autowired
	private IIdGenerator orderIdGenerator;
	
	@Autowired
	private IIdGenerator refundIdGenerator;
	
	/**
	 * 订单号生成器重置seed
	 * 
	 * @param seed
	 */
	public void orderReset(long seed) {
		orderIdGenerator.reset(seed);
	}
	
	/**
	 * 获取下一个订单id
	 * 
	 * @return
	 */
	public String getOrderId() {
		return GlobalDisconf.orderPrefix + orderIdGenerator.genClusterUniqueLongId();
	}
	
	/**
	 * 退款单号生成器重置seed
	 * 
	 * @param seed
	 */
	public void refundReset(long seed) {
		refundIdGenerator.reset(seed);
	}
	
	/**
	 * 获取下一个退款单id
	 * 
	 * @return
	 */
	public String getRefundId() {
		return GlobalDisconf.refundPrefix + refundIdGenerator.genClusterUniqueLongId();
	}

}

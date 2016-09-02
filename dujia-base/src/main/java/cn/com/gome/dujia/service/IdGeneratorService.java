package cn.com.gome.dujia.service;

/**
 * id生成器
 * 
 * @author xiongyan
 * @date 2016年5月6日 上午9:49:38
 */
public interface IdGeneratorService {
	
	/**
	 * 订单号生成器重置seed
	 * 
	 * @param seed
	 */
	public void orderReset(long seed);

	/**
	 * 获取下一个订单id
	 * 
	 * @return
	 */
	public String getOrderId();
	
	/**
	 * 退款单号生成器重置seed
	 * 
	 * @param seed
	 */
	public void refundReset(long seed);
	
	/**
	 * 获取下一个退款单id
	 * 
	 * @return
	 */
	public String getRefundId();
	
}

package cn.com.gome.dujia.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.gome.dujia.mapper.business.OrderMapper;
import cn.com.gome.dujia.mapper.business.OrderRefundMapper;
import cn.com.gome.dujia.mapper.business.ZbyLineMapper;
import cn.com.gome.dujia.model.Order;
import cn.com.gome.dujia.model.OrderRefund;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext*.xml"}) //用于指定配置文件所在的位置
public class LineTest {

	@Autowired
	private ZbyLineMapper mapper;
	
	@Autowired
	private OrderRefundMapper orderRefundMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Test
    public void test() throws Exception {
		/*ZbyLine l = new ZbyLine();
		l.setLineId("xxxx");
		mapper.insert(l);*/

		/*List<Advert> adverts = AdvertDisconf.getAdverts();
		System.out.println(adverts);
		List<Advert> a = AdvertDisconf.getAdverts();
		
		List<Advert> recommends = RecommendDisconf.getRecommends();
		System.out.println(recommends);
		List<Advert> b = RecommendDisconf.getRecommends();*/
		/*OrderRefund or = orderRefundMapper.queryOrderRefund("VR80000009");
		System.out.println(or.getRefundId());
		
		Order o = orderMapper.queryOrderInfo("V800000024");
		System.out.println(o.getOrderId());*/
		
		Order orderInfo = new Order();
		orderInfo.setOrderId("V800000040");
		orderInfo.setVenderOrderId("V800000040");
		orderMapper.updateOrderInfo(orderInfo);
	}
	
}

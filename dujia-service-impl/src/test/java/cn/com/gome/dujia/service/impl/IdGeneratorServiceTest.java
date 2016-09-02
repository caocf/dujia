package cn.com.gome.dujia.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.gome.dujia.service.IdGeneratorService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext*.xml"}) //用于指定配置文件所在的位置
public class IdGeneratorServiceTest {
	
	@Autowired
	private IdGeneratorService idGeneratorService;

	@Test
    public void test() {
		System.out.println(idGeneratorService.getOrderId());
		System.out.println(idGeneratorService.getRefundId());
	}
}

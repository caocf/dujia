package cn.com.gome.dujia.task.test.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import cn.com.gome.dujia.task.order.UpdateOrderStatusTask;
/**
 * Description : 修改订单状态---测试类
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年5月14日 下午3:49:44 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext*.xml"}) //用于指定配置文件所在的位置
public class UpdateOrderStatusTaskTest {

	
	@Autowired
	private UpdateOrderStatusTask updateOrderStatusTask; 
	
	@Test
	public void execute(){
		updateOrderStatusTask.doExecute();
	}
	
}

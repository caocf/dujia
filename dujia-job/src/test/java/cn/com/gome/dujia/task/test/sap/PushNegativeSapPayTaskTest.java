package cn.com.gome.dujia.task.test.sap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import cn.com.gome.dujia.task.sap.PushNegativeSapPayTask;
/**
 * Description : 推送逆向SAP收款任务---测试类
 * Copyright : Copyright (c) 2008- 2016 All rights reserved. <br/>
 * Created Time : 2016年5月17日 上午10:02:48 <br/>
 * 
 * @author WenJie Mai
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext*.xml"}) //用于指定配置文件所在的位置
public class PushNegativeSapPayTaskTest {
	
	
	@Autowired
	private PushNegativeSapPayTask pushNegativeSapPayTask;
	
	
	@Test
	public void doExecuteTest(){
		pushNegativeSapPayTask.doExecute();
	}

}

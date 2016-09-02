package cn.com.gome.dujia.task.test.order;

import cn.com.gome.dujia.task.order.PushVenderTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * Description  : 第三方推送测试类
 * Copyright    : Copyright (c) 2008- 2016 All rights reserved;
 * Created Time : 2016/6/7 14:37;
 *
 * @author WenJie Mai
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext*.xml"}) //用于指定配置文件所在的位置
public class PushVenderTaskTest {

    @Autowired
    private PushVenderTask pushVenderTask;

    @Test
    public void execute(){
        pushVenderTask.doExecute();
    }
}

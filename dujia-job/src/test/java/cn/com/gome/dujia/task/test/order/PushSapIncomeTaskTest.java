package cn.com.gome.dujia.task.test.order;

import cn.com.gome.dujia.service.OrderRefundService;
import cn.com.gome.dujia.task.order.PushSapIncomeTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * Description  :
 * Copyright    : Copyright (c) 2008- 2016 All rights reserved;
 * Created Time : 2016/6/16 17:21;
 *
 * @author WenJie Mai
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext*.xml"}) //用于指定配置文件所在的位置
public class PushSapIncomeTaskTest {

    @Autowired
    private PushSapIncomeTask pushSapIncomeTask;

    @Autowired
    private OrderRefundService orderRefundService;

    @Test
    public void execute(){
        pushSapIncomeTask.doExecute();
    }
}


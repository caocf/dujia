package cn.com.gome.dujia.task.test.order;

import cn.com.gome.dujia.enums.SmsType;
import cn.com.gome.dujia.task.order.OrderNoNoticeStatusTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * Description  : 未通知订单状态---测试类
 * Copyright    : Copyright (c) 2008- 2016 All rights reserved;
 * Created Time : 2016/5/26 21:34;
 *
 * @author WenJie Mai
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext*.xml"}) //用于指定配置文件所在的位置
public class OrderNoNoticeStatusTaskTest {


    @Autowired
    private OrderNoNoticeStatusTask orderNoNoticeStatusTask;

    @Test
    public void execute(){
        orderNoNoticeStatusTask.doExecute();
    }

}

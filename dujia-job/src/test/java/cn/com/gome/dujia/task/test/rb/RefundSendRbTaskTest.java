package cn.com.gome.dujia.task.test.rb;

import cn.com.gome.dujia.task.rb.RefundSendRbTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * Description  : 推送rb(退款)---测试类
 * Copyright    : Copyright (c) 2008- 2016 All rights reserved;
 * Created Time : 2016/5/18 18:30;
 *
 * @author WenJie Mai
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext*.xml"}) //用于指定配置文件所在的位置
public class RefundSendRbTaskTest {

    @Autowired
    private RefundSendRbTask refundSendRbTask;

    @Test
    public void execute(){
        refundSendRbTask.doExecute();
    }
}

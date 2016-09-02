package cn.com.gome.dujia.service.impl;

/**
 * Created by sunming on 2016/4/22.
 */

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by sunming on 2016/4/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext*.xml"}) //用于指定配置文件所在的位置
public class ImageTest {

    @Resource
    public ImageServiceImpl imageServiceImpl;

    @Test
    public void updateImage(){
        String name=imageServiceImpl.uploadRemoteFile2GFS("http://pic3.40017.cn/zzy/rimage/2015/03/17/11/pNY4d5.jpg");
        String name2=imageServiceImpl.uploadRemoteFile2GFS("http://pic3.40017.cn/zzy/rimage/2015/03/17/11/pNY4d5.jpg");
        System.out.println("+++++++++"+name);
        System.out.println("+++++++++"+name2);
    }



}

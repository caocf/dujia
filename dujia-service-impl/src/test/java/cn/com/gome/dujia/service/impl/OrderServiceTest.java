package cn.com.gome.dujia.service.impl;

import cn.com.gome.dujia.service.OrderService;
import cn.com.gome.dujia.service.OrderStatusLogService;
import com.gome.plan.tools.http.HttpClientUtils;
import com.gome.plan.tools.http.RequestPackage;
import com.gome.plan.tools.http.ResponsePackage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.HashMap;
import java.util.Map;
/**
 * Description  :
 * Copyright    : Copyright (c) 2008- 2016 All rights reserved;
 * Created Time : 2016/6/1 10:09;
 *
 * @author WenJie Mai
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext*.xml"}) //用于指定配置文件所在的位置
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderStatusLogService orderStatusLogService;

    @Test
    public void orderNoticeTest(){
        RequestPackage request = new RequestPackage();
        request.setUrl("http://dujia.atguat.com.cn/api/order/notice");
        Map<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("OperateType","Refund");
        paramMap.put("orderId","141712227");
        paramMap.put("ExternalOrderId","V800000417");
        paramMap.put("OrderFlag","true");
        paramMap.put("RefundAmount","1");
        paramMap.put("RefundNumber","20160624162439");
        request.setPostData(paramMap);
        request.setMethod("POST");
        try {
              ResponsePackage result = HttpClientUtils.exec(request);
              if(result != null){
                  System.out.println("----------->" + result.getContent());
              }else{
                  System.out.println("##################");
              }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void rbRefundCallBack(){
        RequestPackage request = new RequestPackage();
        request.setUrl("http://localhost:9200/rbBizService/rbRefundCallBack");
        Map<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("refundNo","");
        paramMap.put("state","");
        paramMap.put("refundFinishTime","");
        paramMap.put("refundTransId","");
        paramMap.put("sign","");
        request.setPostData(paramMap);
        request.setMethod("POST");
        try {
            ResponsePackage result = HttpClientUtils.exec(request);
            if(result != null){
                System.out.println("----------->" + result.getContent());
            }else{
                System.out.println("##################");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

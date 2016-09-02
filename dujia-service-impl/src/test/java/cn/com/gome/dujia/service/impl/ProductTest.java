package cn.com.gome.dujia.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Resource;

import cn.com.gome.dujia.model.ZbyLine;
import cn.com.gome.dujia.redis.CacheExpire;
import cn.com.gome.dujia.service.RedisCacheService;
import cn.com.gome.dujia.service.RedisCacheService;
import cn.com.gome.dujia.thread.ThreadPoolUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.gome.dujia.model.ZbyProduct;
import cn.com.gome.dujia.service.ImageService;
import cn.com.gome.dujia.service.TcService;

import com.gome.plan.tools.utils.DateUtils;
import com.gome.plan.tools.utils.JsonUtils;

/**
 * Created by sunming on 2016/4/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext*.xml"}) //用于指定配置文件所在的位置
public class ProductTest {

    @Resource
    private TcService tcService;




    @Resource
    private JobProductImpl jobProductImpl;

    @Resource
    private ImageService imageService;




//    @Resource
//    private JobInitData   jobInitData;

    private static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON).dateFormat(new SimpleDateFormat(DateUtils.LONG_WEB_FORMAT));

    @Test
    public void productInitProcessor() throws Exception {
        //获取线程池，通过ProductInitProcessor把线路信息更新到数据库中
        List<Future> futures = new ArrayList<Future>();
        ThreadPoolExecutor poll = ThreadPoolUtil.getInstance();
        Future future = poll.submit(new ProductInitProcessor("62409", jobProductImpl, tcService));//提交任务，一个线路一个任务
        future.get();
    }


    @Test
    public void allProductUpdate() throws Exception {
        //调用接口结束时间
        Long startTime = System.currentTimeMillis();
        jobProductImpl.allProductUpdate();
        //调用接口开始时间
        Long endTime = System.currentTimeMillis();
        System.out.println("++++++++++++++++++++++++++++++++"+(endTime-startTime)/1000.000+"秒");
    }


    @Test
    public void productIncrement() throws Exception {
        //调用接口结束时间
        Long startTime = System.currentTimeMillis();
        jobProductImpl.productIncrement();
        //调用接口开始时间
        Long endTime = System.currentTimeMillis();
        System.out.println("++++++++++++++++++++++++++++++++"+(endTime-startTime)/1000.000+"秒");
    }

    @Test
    public void lineSaleInfoCalendarEveryDay() throws Exception {
        //调用接口结束时间
        Long startTime = System.currentTimeMillis();
        jobProductImpl.lineSaleInfoCalendarEveryDay();
        //调用接口开始时间
        Long endTime = System.currentTimeMillis();
        System.out.println("++++++++++++++++++++++++++++++++"+(endTime-startTime)/1000.000+"秒");
    }





    @Test
    public void lineSaleInfoCalendarTest()throws Exception{
       Map requstMap= new HashMap<String,String>();
       requstMap.put("StartTime", "2016-06-17");
       requstMap.put("EndTime", "2016-06-17");
       requstMap.put("LineId", "100030");
        System.out.println("============"+jsonUtils.serialize(tcService.lineSaleInfoCalendar(requstMap)));
    }





    @Test
    public void getProductDetailInfo()throws Exception{
        Map<String,String> requstMap= new HashMap<String,String>();
        requstMap.put("ProductIds", "62409");
        System.out.println("======================="+tcService.getProductDetailInfo(requstMap).get(0).getAttentionInfo());
    }

    @Test
    public void ordercreate()throws Exception{
        Map<String,String> requstMap= new HashMap<String,String>();
        requstMap.put("LineId","36000");
        requstMap.put("ComboId","1000199");
        requstMap.put("TravelStartDate","2016-05-22");
        requstMap.put("BookCount","1");
        requstMap.put("LinkManName","国美测试");
        requstMap.put("LinkManMobile","18962159529");
        requstMap.put("AdultNum","1");
        requstMap.put("LinkManSex","1");
        requstMap.put("TravelUseInfoDetails","[{\"RelateId\":\"646831\",\"TravelDate\":\"2016-05-22\",\"SpecialRequest\":\"自\n" +
                "国美测试\"},{\"RelateId\":\"646830\",\"TravelDate\":\"2016-05-\n" +
                "22\",\"SpecialRequest\":\"国美测试\"}]");

        requstMap.put("PassengerInfos","[{\"Name\":\"国美测试\n" +
                "\",\"Mobile\":\"18962159529\",\"CertType\":0,\"idNo\":\"330726196507040016\"\n" +
                ",\"Sex\":0,\"BirthDay\":\"1990-04-25\"}]");

        tcService.orderCreate(requstMap);
        //System.out.println(jsonUtils.serialize(tcService.orderCreate(requstMap)));;
        System.out.println(1111);
    }

    @Test
    public void orderSearch()throws Exception{
        Map<String,String> requstMap= new HashMap<String,String>();
        requstMap.put("OrderId","149653832");

        System.out.println(tcService.orderSearch(requstMap));
    }

    @Test
    public void orderRefundApplyQuery()throws Exception{
        Map<String,String> requstMap= new HashMap<String,String>();
        requstMap.put("OrderId", "149653832");
        System.out.println(tcService.orderRefundApplyQuery(requstMap));
    }

    @Test
    public void getResProductVerifyDate()throws Exception{
        Map<String,String> requstMap= new HashMap<String,String>();
        requstMap.put("LineProductId", "332668");
        requstMap.put("Date", "2016-06-10");
        System.out.println(tcService.getResProductVerifyDate(requstMap));
    }


    @Test
    public void updateImage()throws Exception{
        System.out.println(imageService.uploadRemoteFile2GFS("http://pic3.40017.cn/zzy/rimage/2015/03/17/11/j7WUNV.jpg"));

    }


    @Test
    public void orderCancel()throws Exception{
        Map<String,String> requstMap= new HashMap<String,String>();
        requstMap.put("OrderId", "149653832");
        System.out.println("111111111111111111J111111111111111111111111");
        System.out.println(tcService.orderCancel(requstMap).getMsg());
        System.out.println("2222222222222222222222222222222222222222");

    }

    @Test
    public void aaa()throws Exception{
        Map<String,String> requstMap= new HashMap<String,String>();

    }


    public static void main(String[] args)  {

        try {
            System.out.println(DateUtils.parse(DateUtils.format(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}

package cn.com.gome.dujia.service.impl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.dom4j.Document;
import org.dom4j.Element;


import com.gome.plan.tools.utils.DateUtils;
import com.gome.plan.tools.utils.JsonUtils;
import com.gome.plan.tools.utils.StringUtils;
import com.tongcheng.zizhuyou.openapi.RequestEntity;
import com.tongcheng.zizhuyou.openapi.RequestUtil;
/**
 * Created by sunming on 2016/4/21.
 */
public class GetUrlThread  {

    private static final long serialVersionUID = 3229454730272104736L;
    private static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON).dateFormat(new SimpleDateFormat(DateUtils.LONG_WEB_FORMAT));
    public static final String PREFIX_URL = "http://api.lvcang.cn/zizhuyou/standard/1.0/";
    public static final String ACCESS_ID = "GMZXDZSWYXGS";
    public static final String SECURITY_ID = "77053f2a-6a5a-4acd-923f-ae4fdf665ac7";

//    public static void main(String[] args) {
//        try {
//            List<RequestEntity> reqList = new ArrayList<RequestEntity>();
//            List<String> list=getProductInfo().subList(0,20);
//            for(String var:list){
//                RequestEntity re = new RequestEntity();
//                re.setUrl(PREFIX_URL + "GetProductDetailInfo" + "?style=json");
//                re.setAccessId(ACCESS_ID);
//                re.setSecurityId(SECURITY_ID);
//                re.getRequestParameter().put("ProductIds", var);
//                reqList.add(re);
//            }
//          // System.out.println(new GetUrlThread().requestInterface(reqList, 10));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<Response> reqUrlTread(List<RequestEntity> list, final int nThreads) throws Exception {
//        if (list == null || list.isEmpty()) {
//            return null;
//        }
//        int size = list.size();
//        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
//
//        List<Future<List<Response>>> futures = new ArrayList<Future<List<Response>>>(nThreads);
//
//        for (int i = 0; i < nThreads; i++) {
//            final List<RequestEntity> subList = list.subList(size / nThreads * i, size / nThreads * (i + 1));
//            Callable<List<Response>> task = new Callable<List<Response>>() {
//                @Override
//                public List<Response> call() throws Exception {
//                    List<Response> subArray = new ArrayList<Response>();
//                    for (RequestEntity req : subList) {
//                        String productJson= RequestUtil.getJsonResource(req);
//                        Response productInfoResponse=jsonUtils.deserialize(productJson, Response.class);
//                        subArray.add(productInfoResponse);
//                        System.out.println(Thread.currentThread().getName());
//                    }
//                    return subArray;
//                }
//            };
//            futures.add(executorService.submit(task));
//        }
//
//        List<Response> listResponse = new ArrayList<Response>();
//        for (Future<List<Response>> future : futures) {
//            listResponse.addAll(future.get());
//        }
//        executorService.shutdown();
//        return listResponse;
//    }
//
//
//
//    public static List<String>  getProductInfo() throws Exception{
//        List<String> strList= new ArrayList<String>();
//        RequestEntity re = initRequestEntity("LineIndexInfoList");
//        Document document = parse(re);
//        List<Element> list = document.getRootElement().element("result").element("LineInfoList").elements();
//        String productids="";
//        for(int i=0;i<list.size();i++){
//            if((i!=0) && ( i % 25 ==0)){
//                strList.add(productids.substring(0,(productids.length()-1)));
//                productids="";
//            }else{
//                productids=productids+list.get(i).element("LineId").getText()+",";
//            }
//            if(i==(list.size()-1)){
//                if(StringUtils.isNotEmpty(productids)){
//                    strList.add(productids.substring(0,productids.length()-1));
//                }
//            }
//        }
//        return strList;
//    }
//
//
//    public static  RequestEntity  initRequestEntity(String urlString) throws Exception {
//        RequestEntity re = new RequestEntity();
//        re.setUrl(PREFIX_URL + urlString +"?style=xml");
//        re.setAccessId(ACCESS_ID);
//        re.setSecurityId(SECURITY_ID);
//        return re;
//    }
//
//
//    public static Document parse(RequestEntity re) throws Exception {
//        org.w3c.dom.Document doc=RequestUtil.getXmlResource(re);
//        if (doc == null) {
//            return (null);
//        }
//        org.dom4j.io.DOMReader xmlReader = new org.dom4j.io.DOMReader();
//        return (xmlReader.read(doc));
//    }
}

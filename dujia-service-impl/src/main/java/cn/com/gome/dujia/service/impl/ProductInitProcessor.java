package cn.com.gome.dujia.service.impl;

import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.model.ZbyLine;
import cn.com.gome.dujia.model.ZbyProduct;
import cn.com.gome.dujia.service.JobProduct;
import cn.com.gome.dujia.service.TcService;
import com.gome.plan.tools.utils.BeanUtils;
import com.gome.plan.tools.utils.SpringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by sunming on 2016/5/5.
 */
@Service
public class ProductInitProcessor implements  Runnable{
    private String productId=null;
    private JobProduct jobProduct=null;
    private TcService tcService=null;

    ProductInitProcessor(){}
    ProductInitProcessor(String productId,JobProduct jobProduct,TcService tcService){
        this.productId=productId;
        this.jobProduct=jobProduct;
        this.tcService=tcService;
    }

    @Override
    public void run() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("ProductIds", productId);
        jobProduct.infoChange(tcService.getProductDetailInfo(map));
    }

}

package cn.com.gome.dujia.service.impl;

import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.model.ZbyLine;
import cn.com.gome.dujia.model.ZbyProduct;
import cn.com.gome.dujia.service.JobProduct;
import cn.com.gome.dujia.service.TcService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunming on 2016/5/5.
 */
@Service
public class CalenderProcessor implements  Runnable{
    private ZbyLine zbyLine=null;
    private JobProduct jobProduct=null;

    CalenderProcessor(){}
    CalenderProcessor(ZbyLine zbyLine, JobProduct jobProduct){
        this.zbyLine=zbyLine;
        this.jobProduct=jobProduct;
    }

    @Override
    public void run() {
        jobProduct.lineSaleInfoCalendarEveryDay(zbyLine);
    }
}

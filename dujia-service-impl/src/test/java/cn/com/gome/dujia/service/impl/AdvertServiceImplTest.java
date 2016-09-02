package cn.com.gome.dujia.service.impl;

import cn.com.gome.dujia.enums.ADRecommendStatus;
import cn.com.gome.dujia.mapper.business.AdvertMapper;
import cn.com.gome.dujia.model.Advert;
import com.gome.plan.tools.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liuhexin on 2016/5/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext*.xml"}) //用于指定配置文件所在的位置
public class AdvertServiceImplTest {

    @Autowired
    private AdvertMapper advertMapper;

    @Test
    public void execADRecommondInfo() {
        try {
            List<Advert> adverts = advertMapper.queryAdRecommendList();
            if (null != adverts) {
                List<Advert> newadverts = new ArrayList<Advert>();
                for (Advert ad : adverts) {
                    Date nowDate = DateUtils.parse(DateUtils.formatWeb(new Date()), "yyyy-MM-dd");
                    if (ad.getStates() == 0 && ad.getStartTime().getTime() <= nowDate.getTime() && ad.getEndTime().getTime() >= nowDate.getTime()) {
                        ad.setStates(ADRecommendStatus.START.getValue());//展示中
                        newadverts.add(ad);
                    } else if (ad.getEndTime().before(nowDate)) {
                        ad.setStates(ADRecommendStatus.EXPIRE.getValue());// 已过期
                        newadverts.add(ad);
                    }
                }
                if (null != newadverts && newadverts.size() > 0) {
                    advertMapper.batchUpdate(newadverts);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}

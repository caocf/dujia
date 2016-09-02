package cn.com.gome.dujia.task.ad;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.gome.dujia.enums.ADRecommendStatus;
import cn.com.gome.dujia.model.Advert;
import cn.com.gome.dujia.service.AdvertService;
import cn.com.gome.dujia.task.common.AbstractTask;

import com.gome.plan.tools.utils.DateUtils;

/**
 * 每天 00:10:00 更新广告、推荐位投放状态
 *
 * @author zhaoxiang-ds
 */
@Component
public class UpdateADRecommendStatusTask extends AbstractTask {

    public static Logger logger = LoggerFactory.getLogger(UpdateADRecommendStatusTask.class);

    @Autowired
    private AdvertService advertService;

    @Override
    public String getTaskName() {
        return "更新推荐广告状态";
    }

    @Override
    public void doExecute() {
        // 更新广告和推荐状态
        this.updateAdRecommend();
    }

    /**
     * 更新广告和推荐状态
     */
    public void updateAdRecommend() {
    	try {
            List<Advert> adverts = advertService.queryAdRecommendList();
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
                	advertService.batchUpdate(newadverts);
                }
            }
            logger.info("更新广告、推荐投放状态任务成功");
        } catch (Exception e) {
        	logger.error("更新广告、推荐投放状态任务失败", e);
        }
    }
}

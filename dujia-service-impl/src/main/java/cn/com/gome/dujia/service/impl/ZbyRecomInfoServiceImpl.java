package cn.com.gome.dujia.service.impl;

import java.util.Date;
import java.util.List;

import com.gome.plan.tools.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.gome.dujia.mapper.business.ZbyRecomInfoMapper;
import cn.com.gome.dujia.model.ZbyRecomInfo;
import cn.com.gome.dujia.service.ZbyRecomInfoService;

@Service
public class ZbyRecomInfoServiceImpl implements ZbyRecomInfoService {

    @Autowired
    private ZbyRecomInfoMapper recomInfoMapper;

    @Override
    public List<ZbyRecomInfo> getRecomInfoList(String cityId) {
        return null;
    }

    @Override
    public List<ZbyRecomInfo> queryRecomInfoList(ZbyRecomInfo recomInfo) {
        return recomInfoMapper.queryRecomInfoList(recomInfo);
    }

    @Override
    public void updateRecomInfoStatus(ZbyRecomInfo recomInfo) {
        String[] titleArray = StringUtils.split(recomInfo.getTitle(), ",");
        recomInfo.setTitleArray(titleArray);
        recomInfo.setUpdateTime(new Date());
        recomInfoMapper.updateRecomInfoStatus(recomInfo);
    }

    @Override
    public List<ZbyRecomInfo> getRecomInfoList(ZbyRecomInfo recomInfo) {
        return recomInfoMapper.select(recomInfo);
    }
    
    /**
     * 查询更新的主题信息
     * @param recomInfo
     * @return
     */
    public List<ZbyRecomInfo> queryUpdateRecomInfoList(ZbyRecomInfo recomInfo) {
    	if (null != recomInfo) {
    		return recomInfoMapper.queryUpdateRecomInfoList(recomInfo);
    	}
    	return null;
    }
}

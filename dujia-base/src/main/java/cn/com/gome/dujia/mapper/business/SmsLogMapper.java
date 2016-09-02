package cn.com.gome.dujia.mapper.business;

import cn.com.gome.dujia.model.SmsLog;

import com.gome.plan.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SmsLogMapper extends Mapper<SmsLog> {

    /**
     * 获取 短信类型分类下最新的短信记录
     *
     * @param record
     * @return
     */
    List<SmsLog> queryLatestSmsLogList(SmsLog record);
}
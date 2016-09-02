package cn.com.gome.dujia.mapper.business;

import cn.com.gome.dujia.model.ZbyLine;

import cn.com.gome.dujia.model.ZbyProductAddition;
import com.gome.plan.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ZbyLineMapper extends Mapper<ZbyLine> {
    void batchInsert(List<ZbyLine> zbyLines);
}
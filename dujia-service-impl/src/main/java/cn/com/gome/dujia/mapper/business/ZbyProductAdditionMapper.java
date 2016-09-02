package cn.com.gome.dujia.mapper.business;

import cn.com.gome.dujia.model.ZbyProductPackage;
import com.gome.plan.mybatis.mapper.common.Mapper;

import cn.com.gome.dujia.model.ZbyProductAddition;

import java.util.List;

public interface ZbyProductAdditionMapper extends Mapper<ZbyProductAddition> {
    void batchInsert(List<ZbyProductAddition> ZbyProductPackages);

   // void deleteInPackageId(List<ZbyProductPackage> list);

    void deleteByDetailId(Integer productDetailId);
}
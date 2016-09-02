package cn.com.gome.dujia.service;

import cn.com.gome.dujia.model.ZbyLine;
import cn.com.gome.dujia.model.ZbyProduct;

import java.util.List;
import java.util.Map;

/**
 * Created by sunming on 2016/4/28.
 */
public interface JobProduct {
    /**
     * 产品增量更新
     */
    void productIncrement();

    /**
     * 班期价格更新
     */
    void lineSaleInfoCalendarEveryDay();

    /**
     * 全量更新
     */
    void allProductUpdate();
    /**
     * 产品增量更新-多线程内部使用
     */
    void infoChange(List<ZbyProduct> productDetailInfo);
    /**
     * 产品增量更新-班期价格内部使用
     */
    void lineSaleInfoCalendarEveryDay(ZbyLine zbyLine);
}

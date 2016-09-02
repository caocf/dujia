package cn.com.gome.dujia.mapper.business;

import java.util.List;

import com.gome.plan.mybatis.mapper.common.Mapper;

import cn.com.gome.dujia.model.OrderDetail;

public interface OrderDetailMapper extends Mapper<OrderDetail> {

    /**
     * 通过订单号查询出游人信息
     * @author Liu Hexin
     *
     * @param orderId
     * @return
     */
    public OrderDetail SelectOrderDetailByOrderID(String orderId);
    
    /**
     * 批量新增
     * @param orderDetails
     * @return
     */
    void batchInsert(List<OrderDetail> orderDetails);
}
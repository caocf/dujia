package cn.com.gome.dujia.service.impl;

import cn.com.gome.dujia.mapper.business.OrderDetailMapper;
import cn.com.gome.dujia.model.OrderDetail;
import cn.com.gome.dujia.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liuhexin on 2016/5/9.
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public int insert(OrderDetail orderDetail) {
        return orderDetailMapper.insert(orderDetail);
    }

    @Override
    public List<OrderDetail> getOrderOrderDetail(OrderDetail orderDetail) {
        return orderDetailMapper.select(orderDetail);
    }

    @Override
    public int updateOrderDetail(OrderDetail orderDetail) {
        return orderDetailMapper.updateByPrimaryKeySelective(orderDetail);
    }

}

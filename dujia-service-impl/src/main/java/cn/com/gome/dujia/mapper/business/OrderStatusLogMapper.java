package cn.com.gome.dujia.mapper.business;

import java.util.List;
import com.gome.plan.mybatis.mapper.common.Mapper;
import cn.com.gome.dujia.model.OrderStatusLog;

public interface OrderStatusLogMapper extends Mapper<OrderStatusLog> {


    /**
     * 批量新增
     * @param orderStatusLogs
     * @return
     */
    void batchInsert(List<OrderStatusLog> orderStatusLogs);

    /**
     * 根据订单ID，获取订单状态流程信息
     *
     * @param orderId
     * @return
     */
    List<OrderStatusLog> queryOrderStatusLogsByOrderId(String orderId);

    /**
     * 新增订单状态记录
     * @param record
     * @return
     */
    int insertOrderStatusLog(OrderStatusLog record);

    /**
     * 查询去重后订单状态
     * @param orderStatusLog
     * @return
     */
    List<OrderStatusLog> queryOrderStatusLogNoRepeat(OrderStatusLog orderStatusLog);
}
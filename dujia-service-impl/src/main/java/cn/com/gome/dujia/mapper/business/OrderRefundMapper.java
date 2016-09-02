package cn.com.gome.dujia.mapper.business;

import java.util.List;
import java.util.Map;
import cn.com.gome.dujia.vo.refund.OrderRefundInfo;
import com.gome.plan.mybatis.mapper.common.Mapper;
import cn.com.gome.dujia.model.OrderRefund;

public interface OrderRefundMapper extends Mapper<OrderRefund> {
	
	/**
	 * 查询退款
	 * @author WenJie Mai
	 *
	 * @param orderRefund
	 * @return
	 */
	public List<OrderRefund> queryOrderRefundList(OrderRefund orderRefund);
	
    /**
	 * 根据推送sap信息查询退款单信息
	 * @param map
	 * @return
	 */
	List<OrderRefund> queryOrderRefundByPushSap(Map<String, Object> map);

	/**
	 * 此方法涉及到多表关联
	 *
	 * @param refundInfo
	 * @return
	 */
	List<OrderRefundInfo> queryOrderRefundInfoList(OrderRefundInfo refundInfo);
	
	/**
	 * 查询退款信息
	 * @author WenJie Mai
	 *
	 * @param refundId
	 * @return
	 */
	OrderRefund queryOrderRefund(String refundId);
	
    /**
     * 更新退款单
     * @param orderRefund
     * @return
     */
    int updateOrderRefund(OrderRefund orderRefund);

    List<OrderRefund> queryRefundList(OrderRefundInfo refundInfo);
}
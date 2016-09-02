package cn.com.gome.dujia.mapper.business;

import java.util.List;
import com.gome.plan.mybatis.mapper.common.Mapper;
import cn.com.gome.dujia.model.PushSap;

public interface PushSapMapper extends Mapper<PushSap> {
	
    /**
     * 根据条件查询推送sap信息
     * @author WenJie Mai
     *
     * @param pushSap
     * @return
     */
	public PushSap getPushSap(PushSap pushSap);
	
    /**
	 * 根据条件查询推送SAP列表
	 * @param pushSap
	 * @return
	 */
	public List<PushSap> queryPushSap(PushSap pushSap);
	
    /**
     * 批量更新
     * @param pushSaps
     */
	public void batchUpdate(List<PushSap> pushSaps);

    /**
     * 查询推送正向SAP信息,sap_type=1 or sap_type=2
     *
     * @param pushSapInfo
     * @return
     */
    List<PushSap> queryPushPositiveSapByInfo(PushSap pushSapInfo);

    void batchInsert(List<PushSap> list);
}
package cn.com.gome.dujia.mapper.business;

import java.util.List;
import java.util.Map;
import cn.com.gome.dujia.model.PushVender;
import com.gome.plan.mybatis.mapper.common.Mapper;
import org.apache.ibatis.annotations.Param;

public interface PushVenderMapper extends Mapper<PushVender> {
	
	/**
	 * 查询
	 * 
	 * @param map
	 * @return
	 */
	public List<PushVender> queryPushVender(Map<String, Object> map);

    /**
     * 查询第三方推送信息
     * @param orderId
     * @return
     */
    public PushVender queryPushVenderInfo(@Param("orderId") String orderId);
}
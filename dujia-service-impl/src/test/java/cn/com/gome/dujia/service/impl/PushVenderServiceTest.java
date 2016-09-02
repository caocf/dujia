package cn.com.gome.dujia.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.enums.PushStatus;
import cn.com.gome.dujia.mapper.business.PushVenderMapper;
import cn.com.gome.dujia.model.PushVender;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext*.xml"}) //用于指定配置文件所在的位置
public class PushVenderServiceTest {

	@Autowired
	private PushVenderMapper pushVenderMapper;
	
	@Test
	public void test() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("statusList", Arrays.asList(PushStatus.WAIT_PUSH.getValue(), PushStatus.PUSH_FAILD.getValue()));// 待推送和推送失败
		map.put("pushNum", Integer.valueOf(GlobalDisconf.pushVenderMaxNumber)); // 最大重推次数
		List<PushVender> pushVenders = pushVenderMapper.queryPushVender(map);
		System.out.println(pushVenders);
	}
}

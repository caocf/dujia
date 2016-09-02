package cn.com.gome.dujia.disconf;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import com.baidu.disconf.client.common.update.IDisconfUpdate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.gome.plan.tools.utils.JsonUtils;
import com.gome.plan.tools.utils.StringUtils;

/**
 * 推荐信息 配置文件
 * 
 * @author xiongyan
 * @date 2016年4月22日 下午1:55:01
 */
@DisconfFile(filename = "recommend.json")
@DisconfUpdateService(confFileKeys = {"recommend.json"})
public class RecommendDisconf implements IDisconfUpdate {

	private static final Logger logger = LoggerFactory.getLogger(RecommendDisconf.class);
	
	/**
	 * 推荐
	 */
	private static List<Advert> recommends;

	public static List<Advert> getRecommends() {
		if(null == recommends) {
			synchronized (RecommendDisconf.class) {
				if(null == recommends) {
					readFile();
				}
			}
		}
		return recommends;
	}

	public static void setRecommends(List<Advert> recommends) {
		RecommendDisconf.recommends = recommends;
	}
	
	/**
	 * 回调函数
	 * 
	 * @throws Exception
	 */
	public void reload() throws Exception {
		readFile();
	}
	
	/**
	 * 读取文件内容
	 */
	private static void readFile() {
		InputStream input = RecommendDisconf.class.getResourceAsStream("/recommend.json");
		try {
			if (null != input) {
				String json = IOUtils.toString(input, "UTF-8");
				if (StringUtils.isNotEmpty(json)) {
					logger.info("首页推荐和列表推荐{}", json);
					setRecommends(new JsonUtils().deserialize(json, new TypeReference<List<Advert>>() {}));
				}
			}
		} catch (Exception e) {
			logger.error("首页推荐和列表推荐配置失败", e);
		} finally {
			if (null != input) {
				try {
					input.close();
				} catch (IOException e) {
				}
			}
		}
	}

}

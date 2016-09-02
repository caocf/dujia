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
 * 广告信息 配置文件
 * 
 * @author xiongyan
 * @date 2016年4月22日 下午1:55:01
 */
@DisconfFile(filename = "advert.json")
@DisconfUpdateService(confFileKeys = {"advert.json"})
public class AdvertDisconf implements IDisconfUpdate {
	
	private static final Logger logger = LoggerFactory.getLogger(AdvertDisconf.class);
	
	/**
	 * 首页轮播图和搜索页推荐
	 */
	private static List<Advert> adverts;

	public static List<Advert> getAdverts() {
		if(null == adverts) {
			synchronized (AdvertDisconf.class) {
				if(null == adverts) {
					readFile();
				}
			}
		}
		return adverts;
	}

	public static void setAdverts(List<Advert> adverts) {
		AdvertDisconf.adverts = adverts;
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
		InputStream input = AdvertDisconf.class.getResourceAsStream("/advert.json");
		try {
			if (null != input) {
				String json = IOUtils.toString(input, "UTF-8");
				if (StringUtils.isNotEmpty(json)) {
					logger.info("首页广告和列表广告{}", json);
					setAdverts(new JsonUtils().deserialize(json, new TypeReference<List<Advert>>() {}));
				}
			}
		} catch (Exception e) {
			logger.error("首页广告和列表广告配置失败", e);
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

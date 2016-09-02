package cn.com.gome.dujia.disconf;

import com.baidu.disconf.client.common.annotations.DisconfFile;

/**
 * logback.xml 不做任何处理，只是单纯的下载
 * 文件有更新会下载到本地，就会更新日志的配置
 * @author xiongyan
 * @date 2016年4月14日 上午11:27:26
 */
@DisconfFile(filename = "logback.xml")
public class LogbackDisconf {

}

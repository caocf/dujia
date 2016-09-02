package cn.com.gome.dujia.task.mon;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 监控运行状态,将数据存储到zk
 */
public class MonitorTask extends TimerTask {

    /**
     * logger
     */
    static Logger logger = LoggerFactory.getLogger(MonitorTask.class);

    /**
     * execute
     */
    public void run() {
        try {
            String content = JobMonitorUtil.getUpdatedMonContent();
            //logger.info("监控到的内容:" + content);
            JobMonitorUtil.monConfig.update(content);
        } catch (Exception e) {
            logger.error("监控运行状态执行出错", e);
        }
    }
}

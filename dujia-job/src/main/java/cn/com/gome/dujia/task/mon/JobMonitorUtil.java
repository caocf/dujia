package cn.com.gome.dujia.task.mon;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.gome.dujia.disconf.CommonDisconf;
import cn.com.gome.dujia.disconf.GlobalDisconf;

import com.gome.plan.tools.curator.zkconfig.ZkConfigString;
import com.gome.plan.tools.curator.zkconfig.ZkConfigType;
import com.gome.plan.tools.utils.DateUtils;
import com.gome.plan.tools.utils.StringUtils;

/**
 * Created by yuanyue on 2015/12/23.
 */
public class JobMonitorUtil {
    /**
     * logger
     */
    static final Logger logger = LoggerFactory.getLogger(JobMonitorUtil.class);

    /**
     * job 状态存储
     */
    static ConcurrentHashMap<String, String> jobStates = new ConcurrentHashMap<String, String>();

    /**
     * 记录job执行状态
     *
     * @param jobName job name
     * @param start   start time
     * @param end     end time
     */
    public static void record(String jobName, Date start, Date end) {
        jobStates.put("\n\n[" + jobName + "]",
                "\n开始:" + DateUtils.format(start, DateUtils.LONG_WEB_FORMAT) +
                "\n结束:" + DateUtils.format(end, DateUtils.LONG_WEB_FORMAT)
        );
    }

    /**
     * 获取更新后的监控数据
     *
     * @return
     */
    public static String getUpdatedMonContent() {
        String recordSplitter = ";;";
        String keyValueSplitter = "::";
        String content = monConfig.getConf();
        if (StringUtils.isNotBlank(content)) {
            String[] records = content.split(recordSplitter);
            for (String record : records) {
                if (StringUtils.isNotBlank(record)) {
                    String[] kv = record.split(keyValueSplitter);
                    if (!jobStates.containsKey(kv[0])) {
                        jobStates.put(kv[0], kv[1]);
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : jobStates.entrySet()) {
            sb.append(entry.getKey())
                    .append(keyValueSplitter)
                    .append(entry.getValue())
                    .append(recordSplitter);
        }
        return sb.toString();
    }

    static ZkConfigString monConfig;

    static {
        String hostAddress = "";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            logger.error("记录失败,未知的host", e);
        }
        monConfig = new ZkConfigString(
                CommonDisconf.zookeeperAddress,
                CommonDisconf.businessName,
                GlobalDisconf.jobMonNode + "." + hostAddress,
                ZkConfigType.TXT);
    }
}

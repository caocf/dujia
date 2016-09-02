package cn.com.gome.dujia.task.common;

import java.util.Date;
import java.util.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import cn.com.gome.dujia.task.mon.JobMonitorUtil;
//import cn.com.gome.dujia.service.ISmsLogService;
//import cn.com.gome.dujia.spring.SpringContextHolder;
import cn.com.gome.dujia.task.mon.MonitorTask;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;

/**
 * 任务的抽象类，所有任务都需要继承此类
 *
 * @author xiongyan
 * @date 2015年9月17日 下午12:13:15
 */

public abstract class AbstractTask extends AbstractSimpleElasticJob {

    /**
     * timer 监控用
     */
    static Timer timer = new Timer();

    static {
        MonitorTask task = new MonitorTask();
        timer.schedule(task, 60000, 60000);
    }

    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(AbstractTask.class);

    /**
     * 从SchedulerFactoryBean注入的applicationContext
     */
    public ApplicationContext applicationContext;

    /**
     * 执行作业.
     *
     * @param shardingContext 作业分片规则配置上下文
     */
    public void process(JobExecutionMultipleShardingContext shardingContext) {
        //开始计时
        long start = System.currentTimeMillis();
        try {
            logger.info("任务【{}】执行开始！", this.getTaskName());
            //执行任务
            this.doExecute();
        } catch (Exception e) {
            logger.error("任务【{}】执行失败！", this.getTaskName(), e);
        } finally {
            //结束计时
            long end = System.currentTimeMillis();
            logger.info("任务【{}】执行结束！耗时：{}秒", this.getTaskName(), (end - start) / 1000.000);
            JobMonitorUtil.record(this.getTaskName(), new Date(start), new Date(end));
        }
    }


    /**
     * 任务名称
     *
     * @return
     */
    public abstract String getTaskName();

    /**
     * 具体执行的任务
     */
    public abstract void doExecute() throws Exception;

}

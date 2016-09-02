package cn.com.gome.dujia.task.common;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

public final class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory {

    private transient AutowireCapableBeanFactory beanFactory;

    @Autowired
    ApplicationContext context;


    @Override
    protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
        final Object job = super.createJobInstance(bundle);
        if (beanFactory == null) {
            synchronized (AutowiringSpringBeanJobFactory.class) {
                if (beanFactory == null) {
                    beanFactory = context.getAutowireCapableBeanFactory();
                }
            }
        }
        beanFactory.autowireBean(job);
        return job;
    }
}
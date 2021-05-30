package com.henvealf.learn.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.quartz.listeners.JobListenerSupport;
import org.quartz.listeners.SchedulerListenerSupport;

/**
 * @author hongliang.yin/Henvealf
 * @date 2019-07-24
 */
public class HelloSchedulerListener extends SchedulerListenerSupport {
    @Override
    public void schedulerError(String msg, SchedulerException cause) {
        System.out.println("HelloSchedulerListener" + msg);
        cause.printStackTrace();
    }
}

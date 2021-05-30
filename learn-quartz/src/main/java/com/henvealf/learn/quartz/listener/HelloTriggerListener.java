package com.henvealf.learn.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;
import org.quartz.listeners.TriggerListenerSupport;

/**
 * @author hongliang.yin/Henvealf
 * @date 2019-07-24
 */
public class HelloTriggerListener extends TriggerListenerSupport {
    @Override
    public String getName() {
        return "helloListener";
    }


}

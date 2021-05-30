package com.henvealf.learn.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;


/**
 * @author hongliang.yin/Henvealf
 * @date 2019-07-24
 */
public class HelloJobListener extends JobListenerSupport {

    private int num = 0;
    @Override
    public String getName() {
        return "helloListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        System.out.println("helloListener jobToBeExecuted");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        System.out.println("helloListener jobWasExecuted");
        System.out.println("num in listener: " + num ++);
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println("helloListener jobExecutionVetoed");
    }
}

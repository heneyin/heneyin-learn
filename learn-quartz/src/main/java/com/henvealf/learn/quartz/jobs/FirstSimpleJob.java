package com.henvealf.learn.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author hongliang.yin/Henvealf
 * @date 2019-07-23
 */
public class FirstSimpleJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("123131");
    }
}

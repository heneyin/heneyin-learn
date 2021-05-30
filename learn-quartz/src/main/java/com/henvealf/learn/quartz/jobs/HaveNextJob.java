package com.henvealf.learn.quartz.jobs;

import org.quartz.*;


/**
 * @author hongliang.yin/Henvealf
 * @date 2019-07-23
 */
public class HaveNextJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        // jobDetail 与 Trigger中的JobDataMap 的集合，后者会覆盖前者。
        JobDataMap mergedJobDataMap = context.getMergedJobDataMap();

        String nextJobName =  jobDetail.getJobDataMap().getString("nextJobName");

        // Trigger trigger = newTrigger().startNow().build();
        try {
            if (nextJobName != null) {
                context.getScheduler().triggerJob(JobKey.jobKey(nextJobName));
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        System.out.println("jobName:" + context.getJobDetail().getKey().getName());
    }

}

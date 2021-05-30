package com.henvealf.learn.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 长时间运行的 job。
 * @author hongliang.yin/Henvealf
 * @date 2019-08-28
 */
public class LongRunJob implements Job {

    public static final String SLEEP_TIME = "sleepTime";

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String id = UUID.randomUUID().toString();
        JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
        long aLong = 10;
        if (mergedJobDataMap.containsKey(SLEEP_TIME)) {
            aLong = context.getMergedJobDataMap().getLong(SLEEP_TIME);
        }
        System.out.println("Begin run long job " + id + " on " + LocalDateTime.now());
        try {
            Thread.sleep(aLong * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End run long job " + id + " on " + LocalDateTime.now());
    }
}

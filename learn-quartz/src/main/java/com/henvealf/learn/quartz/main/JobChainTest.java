package com.henvealf.learn.quartz.main;

import com.henvealf.learn.quartz.jobs.HaveNextJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 测试依赖链。
 * @author hongliang.yin/Henvealf
 * @date 2019-07-23
 */
public class JobChainTest {

    public static void main(String[] args) throws SchedulerException, InterruptedException {

        JobDetail rootJob = JobBuilder.newJob(HaveNextJob.class)
                .storeDurably()
                .withIdentity("rootJob")
                .usingJobData("nextJobName", "childJob").build();

        JobDetail childJob = JobBuilder.newJob(HaveNextJob.class)
                .storeDurably()
                .withIdentity("childJob").build();

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();

        scheduler.addJob(rootJob, false);
        scheduler.addJob(childJob, false);

        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger2", "group1")
                .forJob("rootJob")
                .withSchedule(CronScheduleBuilder.cronSchedule("10 * * * * ?"))
                .build();

        scheduler.scheduleJob( trigger );

        Thread.sleep(100000);

        scheduler.shutdown();
    }

}

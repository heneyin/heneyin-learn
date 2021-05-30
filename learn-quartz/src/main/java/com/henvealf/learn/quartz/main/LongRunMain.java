package com.henvealf.learn.quartz.main;

import com.henvealf.learn.quartz.jobs.LongRunJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 测试长时间运行的 Main方法。
 * 结果显示，线程池满了，之后的待调度 job 会一直阻塞到有线程空间。
 * @author hongliang.yin/Henvealf
 * @date 2019-08-28
 */
public class LongRunMain {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        // and start it off
        scheduler.start();


        // define the job and tie it to our HelloJob class
        JobDetail job = JobBuilder.newJob(LongRunJob.class)
                .withIdentity("long_run_job", "long_run_group")
                .build();

        // Trigger the job to run now, and then repeat every 40 seconds
        Trigger trigger = newTrigger()
                .withIdentity("long_run_trigger", "long_run_trigger_group")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(1)
                        .repeatForever())
                .build();

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(job, trigger);
        Thread.sleep(10000000);
        scheduler.shutdown();
    }
}

package com.henvealf.learn.quartz.main;

import com.henvealf.learn.quartz.jobs.LongRunJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 测试在job在运行时候，取消调度的行为。
 *
 * 取消调度后，已经提交的调度会继续执行完毕，没有提交就不再提交。
 *
 * shutdown 会等待已经提交的调度计划完成。
 *
 * @author hongliang.yin/Henvealf
 * @date 2019-08-29
 */
public class UnScheduleTest {

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
        Thread.sleep(1000);
        System.out.println("unscheduled");
        scheduler.unscheduleJob(trigger.getKey());
        while (!scheduler.getCurrentlyExecutingJobs().isEmpty()) {
            System.out.println("Now there is some jobs on the schedule, wait 1 second");
            Thread.sleep(500);
        }
        System.out.println("All job is stopped");
        Thread.sleep(1000);
        System.out.println("ReBegin start job");
        scheduler.scheduleJob(job, trigger);
        Thread.sleep(2000);
        System.out.println("Begin shutdown scheduler");
        scheduler.shutdown();
        System.out.println("Finish shutdown scheduler, wait schedule thead end");
    }
}

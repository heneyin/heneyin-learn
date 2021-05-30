package com.henvealf.learn.quartz.main;

import com.henvealf.learn.quartz.jobs.LongRunJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 测试暂停任务。
 * 暂停会停止调度任务。
 * 算是轻量级的 unschedule
 * @author hongliang.yin/Henvealf
 * @date 2019-08-29
 */
public class PauseScheduleTest {

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
        Thread.sleep(2000);
        System.out.println("Begin paused");
        scheduler.pauseAll();
        while (!scheduler.getCurrentlyExecutingJobs().isEmpty()) {
            System.out.println("Now there is some jobs on the schedule, wait 1 second");
            Thread.sleep(500);
        }
        System.out.println("All job is paused");
        Thread.sleep(4000);
        System.out.println("resume all job");
        scheduler.resumeAll();
        Thread.sleep(20000);
        System.out.println("Begin shutdown scheduler");
        scheduler.shutdown();
        System.out.println("Finish shutdown scheduler, wait schedule thead end");
    }

}

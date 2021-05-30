package com.henvealf.learn.quartz.main;

import com.henvealf.learn.quartz.jobs.FirstSimpleJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author hongliang.yin/Henvealf
 * @date 2019-07-23
 */
public class FirstSimpleMain {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        // and start it off
        scheduler.start();


        // define the job and tie it to our HelloJob class
        JobDetail job = JobBuilder.newJob(FirstSimpleJob.class)
                .withIdentity("job1", "group1")
                .build();

        JobDetail job1 = JobBuilder.newJob(FirstSimpleJob.class)
                .withIdentity("job1", "group1")
                .build();

        // Trigger the job to run now, and then repeat every 40 seconds
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(1)
                        .repeatForever())
                .build();

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(job, trigger);
        Thread.sleep(10000);
        scheduler.shutdown();

    }
}

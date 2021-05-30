package com.henvealf.learn.quartz.listener;

import com.henvealf.learn.quartz.jobs.WillFailedJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author hongliang.yin/Henvealf
 * @date 2019-07-24
 */
public class LearnListener {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        // and start it off
        scheduler.start();

        // define the job and tie it to our HelloJob class
        JobDetail job = JobBuilder.newJob(WillFailedJob.class)
                .withIdentity("job1", "group1")
                .build();

        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(1)
                        .repeatForever())
                .build();

        scheduler.getListenerManager()
                .addJobListener(new HelloJobListener());
        scheduler.getListenerManager().addSchedulerListener(new HelloSchedulerListener());

        scheduler.scheduleJob(job, trigger);

        Thread.sleep(10000);
        scheduler.shutdown();
    }
}

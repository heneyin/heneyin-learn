package com.henvealf.learn.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Random;

/**
 * @author hongliang.yin/Henvealf
 * @date 2019-07-24
 */
public class WillFailedJob implements Job {


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Random r = new Random();
        int num = r.nextInt(4);
        System.out.println("now num: " + num);
        if (num == 2) {
            throw new RuntimeException("error");
        }
    }
}

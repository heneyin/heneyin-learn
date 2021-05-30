package com.henvealf.learn.curator.elections;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <p>
 *
 * <p>
 *
 * @author hongliang.yin/Henvealf on 2018/12/7
 */
public class LeaderLearn extends LeaderSelectorListenerAdapter {

    public static void main(String[] args) throws IOException {
        new LeaderLearn().startLeaderElection();
    }

    public void startLeaderElection() throws IOException {
        CuratorFramework cf = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(1000,3 ));
        cf.start();

        LeaderSelector leaderSelector = new LeaderSelector(cf,"/henvealf/elections/leader", this);
        leaderSelector.start();

        leaderSelector.autoRequeue();

        System.out.println("Press enter/return to quit\n");
        new BufferedReader(new InputStreamReader(System.in)).readLine();

        leaderSelector.close();
        cf.close();
    }


    @Override
    public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
        System.out.println("I am leader, I will do something");

        Thread.sleep(1000);

        System.out.println("I do end, i give up as a leader");
    }
}

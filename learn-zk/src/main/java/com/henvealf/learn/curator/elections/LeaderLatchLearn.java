package com.henvealf.learn.curator.elections;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.framework.recipes.leader.Participant;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * leader 闩
 * @author hongliang.yin/Henvealf on 2018/12/5
 */
public class LeaderLatchLearn {

    /**
     * 得到 leader 信息。
     * @param leaderLatch
     */
    public static void getLeaderInfo(LeaderLatch leaderLatch) {
        System.out.println("---Leader info:----");
        boolean hasLeadership = leaderLatch.hasLeadership();
        System.out.println("hasLeadership:" + hasLeadership);

        Participant leader = null;
        try {
            leader = leaderLatch.getLeader();
        } catch (Exception e) {

        }

        System.out.println("leader.getId(): " + leader.getId());
        System.out.println("leader.toString(): " + leader.toString());
        System.out.println("leader.hashCode(): " + leader.hashCode());
    }

    public static void main(String[] args) throws Exception {

        String zkUrl = "localhost:2181";
        String path = "/henvealf/elections/leaderlatch";


        List<LeaderLatch> leaderLatchLearns = new ArrayList<>();
        List<CuratorFramework> curatorFrameworks = new ArrayList<>();

        for (int i = 0; i < 1; i ++) {

            CuratorFramework cf = CuratorFrameworkFactory.newClient(zkUrl, new ExponentialBackoffRetry(1000, 3));
            cf.start();

            if (cf.checkExists().forPath(path) == null) {
                cf.create().creatingParentsIfNeeded().forPath(path);
            }

            LeaderLatch leaderLatch = new LeaderLatch(cf, path,"leader latch " + i + System.currentTimeMillis());

            leaderLatch.addListener(new LeaderLatchListener() {
                @Override
                public void isLeader() {
                    System.out.println("I am leader: " + leaderLatch.getId());
                    getLeaderInfo(leaderLatch);

                }

                @Override
                public void notLeader() {
                    System.out.println("I am not leader: " + leaderLatch.getId());
                    getLeaderInfo(leaderLatch);
                }
            });

            leaderLatch.start();
            leaderLatchLearns.add(leaderLatch);
            curatorFrameworks.add(cf);
        }

        try {

//
//            while (true) {
//                for (LeaderLatch leaderLatch: leaderLatchLearns) {
//                    System.out.println("leaderLatch" + leaderLatch.getId() + " wait new leader");
//                    getLeaderInfo(leaderLatch);
//                }
//                Thread.sleep(1000 );
//            }
////        });
////
////


            System.out.println("Press enter/return to quit\n");
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } finally {
            for (LeaderLatch ll : leaderLatchLearns) {
                ll.close();
            }

            for (CuratorFramework ll : curatorFrameworks) {
                ll.close();
            }
        }
    }

}

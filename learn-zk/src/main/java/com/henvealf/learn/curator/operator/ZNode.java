package com.henvealf.learn.curator.operator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;
import org.apache.zookeeper.data.Stat;

import java.io.File;

/**
 * @author hongliang.yin/Henvealf on 2018/12/5
 */
public class ZNode {

    public static void main(String[] args) throws Exception {

        TestingServer testingServer = new TestingServer(2181, new File("testZkServerDir/"));

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

        CuratorFramework cf = CuratorFrameworkFactory.newClient(testingServer.getConnectString(), retryPolicy);
        cf.start();

        String path = "/henvealf"+System.currentTimeMillis();

        cf.create().forPath(path,"hello".getBytes());
//        cf.create().forPath(path);
        cf.setData().forPath(path, "hello".getBytes());

        byte[] data = cf.getData().forPath(path);
        String s = new String(data);
        System.err.println("getData: " + s);

        Stat stat = cf.checkExists().forPath(path + "/ex");

        System.err.println("checkExists: " + stat);

        cf.delete().forPath(path);

        cf.create().creatingParentsIfNeeded().forPath("/henvealf/a/b");
        cf.create().forPath("/henvealf/a1/b1");

        cf.close();
        testingServer.close();
    }
}

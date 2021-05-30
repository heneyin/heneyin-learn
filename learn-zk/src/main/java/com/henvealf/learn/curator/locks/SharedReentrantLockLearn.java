package com.henvealf.learn.curator.locks;

import com.henvealf.learn.curator.CuratorConstants;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.RevocationListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * <p>
 *  分布式的可重入共享锁。
 * <p>
 *
 * @author hongliang.yin/Henvealf on 2018/12/9
 */
public class SharedReentrantLockLearn {

    public static void main(String[] args) throws Exception {

        String path = "/henvealf/locks/SharedReentrantLock";

        CuratorFramework cf = CuratorFrameworkFactory.newClient(CuratorConstants.ZK_URL, new ExponentialBackoffRetry(1000, 3));
        cf.start();

        if (null == cf.checkExists().forPath(path)) {
            cf.create().creatingParentsIfNeeded().forPath(path);
        }

        InterProcessMutex interProcessLock = new InterProcessMutex(cf, path);
        interProcessLock.makeRevocable(new RevocationListener<InterProcessMutex>() {
            @Override
            public void revocationRequested(InterProcessMutex interProcessMutex) {

            }
        });

        try {
            System.out.println("begin get locking");
            interProcessLock.acquire();
            System.out.println("get lock success");
            Thread.sleep(1000 * 10);
        } finally {
            interProcessLock.release();
            System.out.println("release lock success");
        }

        cf.close();

    }
}

package com.henvealf.learn.curator.locks;

import com.henvealf.learn.curator.CuratorConstants;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * <p>
 * 共享锁，不可重入。
 * <p>
 *
 * @author hongliang.yin/Henvealf on 2018/12/10
 */
public class SharedLockLearn {

    public static void main(String[] args) throws Exception {
        String path = "/henvealf/locks/SharedLock";

        CuratorFramework cf = CuratorFrameworkFactory.newClient(CuratorConstants.ZK_URL, new ExponentialBackoffRetry(1000, 3));
        cf.start();

        if (null == cf.checkExists().forPath(path)) {
            cf.create().creatingParentsIfNeeded().forPath(path);
        }

        InterProcessSemaphoreMutex semaphoreMutex = new InterProcessSemaphoreMutex(cf, path);

        try {
            System.out.println("begin get locking");
            semaphoreMutex.acquire();
            System.out.println("get lock success");
            Thread.sleep(1000 * 10);
        } finally {
            semaphoreMutex.release();
            System.out.println("release lock success");
        }

        cf.close();
    }
}

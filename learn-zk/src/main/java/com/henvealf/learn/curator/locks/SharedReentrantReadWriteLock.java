package com.henvealf.learn.curator.locks;

import com.henvealf.learn.curator.CuratorConstants;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * <p>
 * 进程间共享的可重入读写锁。
 * <p>
 *
 * @author hongliang.yin/Henvealf on 2018/12/10
 */
public class SharedReentrantReadWriteLock {

    public static void main(String[] args) throws Exception {
        String path = "/henvealf/locks/SharedReentrantReadWriteLock";

        CuratorFramework cf = CuratorFrameworkFactory.newClient(CuratorConstants.ZK_URL, new ExponentialBackoffRetry(1000,3 ));
        cf.start();

        InterProcessReadWriteLock interProcessReadWriteLock =
                new InterProcessReadWriteLock(cf,  path);

        // 这里不是应用实例，仅仅是把方法罗列一下。
        try {
            interProcessReadWriteLock.readLock().acquire();
            interProcessReadWriteLock.writeLock().acquire();
        } finally {
            interProcessReadWriteLock.readLock().release();
            interProcessReadWriteLock.writeLock().release();
            cf.close();
        }

    }

}

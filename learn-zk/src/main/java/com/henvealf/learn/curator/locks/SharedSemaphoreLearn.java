package com.henvealf.learn.curator.locks;

import com.henvealf.learn.curator.CuratorConstants;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreV2;
import org.apache.curator.framework.recipes.locks.Lease;
import org.apache.curator.framework.recipes.shared.SharedCount;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * <p>
 *
 * <p>
 *
 * @author hongliang.yin/Henvealf on 2018/12/10
 */
public class SharedSemaphoreLearn {

    public static void main(String[] args) throws Exception {

        String path = "/henvealf/locks/SharedSemaphore";
        CuratorFramework cf = CuratorFrameworkFactory.newClient(CuratorConstants.ZK_URL, new ExponentialBackoffRetry(1000, 3));
        cf.start();

        if (null == cf.checkExists().forPath(path)) {
            cf.create().creatingParentsIfNeeded().forPath(path);
        }

        int leaseNum = 2;

        // 直接使用数字。
        // InterProcessSemaphoreV2 semaphoreV2 = new InterProcessSemaphoreV2(cf, path, leaseNum);
        // 使用SharedCount来作为租约个数。
         InterProcessSemaphoreV2 semaphoreV2 = new InterProcessSemaphoreV2(cf, path, new SharedCount(cf, path, leaseNum));

        Lease acquire = null;
        try {

            acquire = semaphoreV2.acquire();
            acquire.getData();
            System.out.println("I get the lease");
            System.out.println("lease num: " + leaseNum);
            System.out.println("lease data:" + new String(acquire.getData()));
            System.out.println("lease path:" + acquire.getNodeName());

            Thread.sleep(1000 * 50);
        } finally {
            System.out.println("returned lease.");
            semaphoreV2.returnLease(acquire);
        }
    }

}

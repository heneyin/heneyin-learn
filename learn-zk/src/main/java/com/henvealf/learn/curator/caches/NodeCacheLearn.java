package com.henvealf.learn.curator.caches;

import com.henvealf.learn.curator.CuratorConstants;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * <p>
 *  Curator Node Cache
 * <p>
 *
 * @author hongliang.yin/Henvealf on 2018/12/9
 */
public class NodeCacheLearn {

    public static void main(String[] args) throws Exception {
        String path = "/henvealf/caches/node";

        CuratorFramework cf = CuratorFrameworkFactory.newClient(CuratorConstants.ZK_URL, new ExponentialBackoffRetry(1000, 3));
        cf.start();

        if (null == cf.checkExists().forPath(path)) {
            cf.create().creatingParentsIfNeeded().forPath(path);
        }

        // 创建节点实例
        NodeCache nodeCache = new NodeCache(cf, path);

        // 监听器
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("node is changed");
                System.out.println("now data: " + new String(nodeCache.getCurrentData().getData()));
            }
        });

        // 启动
        nodeCache.start(true);

        System.out.println("Press enter/return to quit\n");
        new BufferedReader(new InputStreamReader(System.in)).readLine();

        // 关闭
        nodeCache.close();
        cf.close();

    }
}

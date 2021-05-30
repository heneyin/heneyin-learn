package com.henvealf.learn.curator.caches;

import com.henvealf.learn.curator.CuratorConstants;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * <p>
 *
 * <p>
 *
 * @author hongliang.yin/Henvealf on 2018/12/9
 */
public class TreeCacheLearn {

    public static void main(String[] args) throws Exception {

        String path = "/henvealf/caches/tree";

        CuratorFramework cf = CuratorFrameworkFactory.newClient(CuratorConstants.ZK_URL, new ExponentialBackoffRetry(1000, 3));
        cf.start();

        if (null == cf.checkExists().forPath(path)) {
            cf.create().creatingParentsIfNeeded().forPath(path);
        }

        TreeCache treeCache = new TreeCache(cf, path);

        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {
                switch (treeCacheEvent.getType()) {
                    case NODE_ADDED:
                        ChildData data = treeCacheEvent.getData();
                        System.out.println("add path: " + data.getPath());
                        System.out.println("add path data: " + new String(data.getData()));
                        System.out.println();
                        break;
                    case NODE_REMOVED:
                        ChildData dataRemoved = treeCacheEvent.getData();
                        System.out.println("dataRemoved path: " + dataRemoved.getPath());
                        System.out.println("dataRemoved path data: " + new String(dataRemoved.getData()));
                        System.out.println();
                        break;
                }
            }
        });



        treeCache.start();

        System.out.println("Press enter/return to quit\n");
        new BufferedReader(new InputStreamReader(System.in)).readLine();

        treeCache.close();
        cf.close();
    }

}

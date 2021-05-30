package com.henvealf.learn.curator.caches;

import com.henvealf.learn.curator.CuratorConstants;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * <p>
 *
 * <p>
 *
 * @author hongliang.yin/Henvealf on 2018/12/8
 */
public class PathCacheLearn {


    public static final String PATH = "/henvealf/caches/path";

    public static void main(String[] args) throws Exception {

        // 设置客户端
        CuratorFramework cf = CuratorFrameworkFactory.newClient(CuratorConstants.ZK_URL, new ExponentialBackoffRetry(1000, 3));
        cf.start();

        // 创建测试目录
        if (cf.checkExists().forPath(PATH) == null) {
            cf.create().creatingParentsIfNeeded().forPath(PATH);

            for (int i = 0; i < 3; i ++) {
                cf.create().forPath(PATH + "/cache-"+i, (" i ma cache data " + i).getBytes(StandardCharsets.UTF_8));
            }

        }

        // 设置 PathChildrenCache 实例
        PathChildrenCache pathChildrenCache = new PathChildrenCache(cf, PATH, true);

        // 为缓存添加事件监听器
        pathChildrenCache.getListenable().addListener((curatorFramework, pathChildrenCacheEvent) -> {
            switch (pathChildrenCacheEvent.getType()) {
                case INITIALIZED:
                    System.out.println(" path cache have INITIALIZED event");
                    printCurrentData(pathChildrenCache, "INITIALIZED listener");
                    break;
                case CHILD_ADDED:
                    System.out.println(" path child has add");
                    printCurrentData(pathChildrenCache, "CHILD_ADDED listener");

                    break;
                case CHILD_REMOVED:
                    System.out.println(" path child has remove");
                    break;
                case CHILD_UPDATED:
                    System.out.println(" path child has update");
                    break;
            }
        });

        // start 可以设置模式
        pathChildrenCache.start(PathChildrenCache.StartMode.NORMAL);

        printCurrentData(pathChildrenCache, "");


        // 开始 cache 并手动添加新的节点。
        // System.out.println("begin add..");
        // cf.create().forPath(PATH + "/cache-" + System.currentTimeMillis(), ("i am new " + System.currentTimeMillis()).getBytes());
        // System.out.println("end add..");

        System.out.println("begin sleep..");
        Thread.sleep(1000);

        pathChildrenCache.close();
        cf.close();

    }

    public static void printCurrentData(PathChildrenCache pathChildrenCache, String flag) {

        System.out.printf("--------- " + flag + "--------------");
        List<ChildData> currentDatas = pathChildrenCache.getCurrentData();

        for (ChildData childData: currentDatas) {
            System.out.println("childData.getPath(): " + childData.getPath());
            System.out.println("childData.getData(): " + new String(childData.getData()));
            System.out.println("childData.getStat(): " + childData.getStat().toString());
        }

        System.out.println("Child size in " + flag + ": "+ currentDatas.size());
    }

}

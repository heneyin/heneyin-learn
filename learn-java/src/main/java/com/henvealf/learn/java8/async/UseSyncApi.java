package com.henvealf.learn.java8.async;

import com.henvealf.learn.java8.TimeWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static java.util.stream.Collectors.toList;

/**
 * 使用异步的方式使用同步接口，以免受阻塞之苦。
 *
 * @author hongliang.yin/Henvealf
 * @date 2018/1/25
 */
public class UseSyncApi {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        UseSyncApi useSyncApi = new UseSyncApi();
        //useSyncApi.findPricesQueue("123");
        //useSyncApi.findPricesParallelStream("hello");
//        useSyncApi.findPricesUseFuture("qweqe");
//        useSyncApi.findPricesParallelStream("qweqe");
        useSyncApi.useCustomExecutorPoll("asda");
    }

    private List<Shop> shopList;
    private Executor executor;
    public UseSyncApi() {
        this.shopList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            shopList.add(new Shop());
        }
        this.executor =
                Executors.newFixedThreadPool(Math.min(shopList.size(), 100),
                        r -> {
                            Thread t = new Thread(r);
                            t.setDaemon(true);
                            return t;
                        });
    }

    // 同步的方式，串行顺序查询各个同步商店的数据。
    // 会跟慢，需要4160ms
    public List<String> findPricesQueue(String product) {
        TimeWatch timeWatch = new TimeWatch();
        List<String> result =  shopList.stream()
                .map(shop -> String.format("%s price is %.2f",product, shop.getPrice(product)))
                .collect(toList());
        System.out.println(result);
        System.out.println("queue query price use time: " + timeWatch);
        return result;
    }

    // 使用并行流的方式处理
    // 4个 shop 1 s ;  5 个 shop 2 秒，并行度为4，等于核数
    public List<String> findPricesParallelStream(String product) {
        TimeWatch timeWatch = new TimeWatch();
        List<String> result =  shopList.parallelStream()
                .map(shop -> String.format("%s price is %.2f",product, shop.getPrice(product)))
                .collect(toList());
        System.out.println(result);
        System.out.println("parallel stream query price use time: " + timeWatch);
        return result;
    }

    // 使用Java8异步 API ，
    // 3个 shop 1 秒。 4 个 shop 2111ms, 所以平行度为3，可见其中一个核被主线程占用了，或许也可能是 stream 占用的。
    public List<String> findPricesUseFuture(String product) {
        TimeWatch timeWatch = new TimeWatch();
        List<CompletableFuture<String>> result =  shopList.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f",product, shop.getPrice(product))))
                .collect(toList());
        List<String> finalResult = result.stream().map(CompletableFuture::join).collect(toList());
        System.out.println(finalResult);
        System.out.println("stream query price use time: " + timeWatch);
        return finalResult;
    }

    // 为 Java8 的异步 api 定制Executor
    public List<String> useCustomExecutorPoll(String product) {
        TimeWatch timeWatch = new TimeWatch();
        List<CompletableFuture<String>> result =  shopList.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f",product, shop.getPrice(product)),this.executor))
                .collect(toList());
        List<String> finalResult = result.stream().map(CompletableFuture::join).collect(toList());
        System.out.println(finalResult);
        System.out.println("stream query price use time: " + timeWatch);
        return finalResult;
    }


}

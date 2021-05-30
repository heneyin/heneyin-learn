package com.henvealf.learn.java8.async.streamOperators;

import com.henvealf.learn.java8.TimeWatch;
import com.henvealf.learn.java8.async.Shop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * 使用折扣服务拿到商品的最终价格。
 * @author hongliang.yin/Henvealf
 * @date 2018/1/30
 */
public class UseDiscountApi {

    private static List<Shop> shopList = new ArrayList<>();
    private static Executor executor;
    static {
        for (int i = 0; i < 10; i++) {
            shopList.add(new Shop());
        }
        executor =
                Executors.newFixedThreadPool(Math.min(shopList.size(), 100),
                        r -> {
                            Thread t = new Thread(r);
                            t.setDaemon(true);
                            return t;
                        });
    }

    // 使用不好的方式获得最终价格
    // 最终需要 10 秒。
    public static List<String> findPricesUseBad(String product) {
        return shopList.stream()
                .map(shop -> shop.getPriceWithDiscount(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(toList());
    }

    // 使用 CompletableFuture 提供的特性异步的执行流水操作
    // 2 秒
    public static List<String> findPricesUseJavaAsync(String product) {
        List<CompletableFuture<String>> priceFutures =
                shopList.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceWithDiscount(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenApply(Discount::applyDiscount))
//                .map(future -> future.thenCompose(quote ->   // 看起来多此一举了。直接用 thenApply 也可以实现。
//                        CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)
//                ))
                .collect(toList());
        return priceFutures.stream().map(CompletableFuture::join)
                .collect(toList());
    }

    // 得到获取价格操作的流。
    // 并且其中获取价格的操作的延迟是随机的，不是固定的。
    public static Stream<CompletableFuture> findPricesStream(String product) {
        return shopList.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceRandomDelayWithDiscount(product)))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenApply(Discount::applyDiscount));
    }



    public static void main(String[] args) {
        TimeWatch timeWatch = new TimeWatch();
//        System.out.println(findPricesUseBad("2321"));
//        System.out.println(timeWatch.toString());
//
//        System.out.println(findPricesUseJavaAsync("ASDAA"));
//        System.out.println(timeWatch.toString());

        TimeWatch time = new TimeWatch();
        CompletableFuture[] futures =
                findPricesStream("12321")
                .map(f -> f.thenAccept( s -> System.out.println(s + " (done in " + time + " )")))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();
        System.out.println("all use time: " + time);
    }

}

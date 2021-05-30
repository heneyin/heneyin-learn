package com.henvealf.learn.java8.async;

import com.henvealf.learn.java8.async.streamOperators.Discount;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * 模拟一个商店接口，或者同步，或者异步。要从中获取商品的价格
 * @author hongliang.yin/Henvealf
 * @date 2018/1/25
 */
public class Shop {

    private Random r = new Random(47);

    // 同步的方式获取价格
    public double getPrice(String product) {
        return calculatePrice(product);
    }

    // 超级简单的方式来构建异步api
    // 只需要提供相应对象的 Supplier 便可。
    // 在其中会自动对异常进行处理。
    public Future<Double> getPriceAsyncSimple(String price) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(price));
    }

    // 异步的方式获取价格
    public Future<Double> getPriceAsync(String product) {
        // 用来代表一个一定能在将来获取到的值。
        // CompletableFuture 比  Future 新增了大量的方法，方便我们去使用异步 api。
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            double price = calculatePrice(product);
            futurePrice.complete(price);
        }).start();
        return futurePrice;
    }

    // 异步的方式获取价格,并且获取异常信息
    public Future<Double> getPriceAsyncHasExceptionInfo(String product) {
        // 用来代表一个一定能在将来获取到的值。
        // CompletableFuture 比  Future 新增了大量的方法，方便我们去使用异步 api。
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception e) {
                // 获取异常信息
                futurePrice.completeExceptionally(e);
            }

        }).start();
        return futurePrice;
    }

    /**
     *
     * @param product
     * @return 返回一个随机的 商品名称:商品价格:折扣代码
     */
    public String getPriceWithDiscount(String product) {
        System.out.println("getPriceWithDiscount thread " + Thread.currentThread().getName());
        double price = calculatePrice(product);
        Discount.Code code  = Discount.Code.values()[r.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s",product, price, code);
    }

    /**
     *
     * @param product
     * @return 返回一个随机的 商品名称:商品价格:折扣代码
     */
    public String getPriceRandomDelayWithDiscount(String product) {
        System.out.println("getPriceWithDiscount thread " + Thread.currentThread().getName());
        double price = calculatePriceRandomDelay(product);
        Discount.Code code  = Discount.Code.values()[r.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s",product, price, code);
    }

    private double calculatePrice(String product) {
        if (product == null) {
            throw new RuntimeException("product is not available!");
        }
        delay();
        return r.nextDouble() * product.charAt(0) * product.charAt(1);
    }

    private double calculatePriceRandomDelay(String product) {
        if (product == null) {
            throw new RuntimeException("product is not available!");
        }
        randomDelay();
        return r.nextDouble() * product.charAt(0) * product.charAt(1);
    }

    public static void delay() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void randomDelay() {
        Random random = new Random();
        int delay = 500 + random.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

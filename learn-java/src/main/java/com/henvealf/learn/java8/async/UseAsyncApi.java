package com.henvealf.learn.java8.async;

import com.henvealf.learn.java8.TimeWatch;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 *
 * 使用Shop异步接口来获取数据。
 * @author hongliang.yin/Henvealf
 * @date 2018/1/25
 */
public class UseAsyncApi {

    public static void main(String[] args) {
        testNormalUseAsyncApi();
//        testThrowExceptionAsyncApi();
//        testSimpleAsyncApi();

    }

    public static void testNormalUseAsyncApi() {
        Shop shop = new Shop();
        TimeWatch timeWatch = new TimeWatch();
        Future<Double> futurePriceOne = shop.getPriceAsync("my favorite product");
        System.out.println("use async api query one time: " + timeWatch);

        Future<Double> futurePriceTwo = shop.getPriceAsync("my favorite product");
        System.out.println("use async api query two time: " + timeWatch);

        try {
            // 如果这里面发生异常，接受不到值就会一直阻塞。
            double priceOne = futurePriceOne.get();
            // 如果5秒阻塞没有拿到结果，就会抛出超时异常。
            double priceTwo = futurePriceTwo.get(5,TimeUnit.SECONDS);
            System.out.println("one: " +priceOne);
            System.out.println("two: " +priceTwo);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("use async api query total time: " + timeWatch);
    }

    public static void testThrowExceptionAsyncApi() {
        Shop shop = new Shop();
        TimeWatch timeWatch = new TimeWatch();
        Future<Double> futurePriceOne = shop.getPriceAsyncHasExceptionInfo(null);
        System.out.println("use async api query one time: " + timeWatch);
        try {
            // 这里捕获到错误的话，会直接退出。
            double priceOne = futurePriceOne.get();
            System.out.println("one: " +priceOne);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("use async api query total time: " + timeWatch);
    }

    public static void testSimpleAsyncApi() {
        Shop shop = new Shop();
        TimeWatch timeWatch = new TimeWatch();
        Future<Double> futurePriceOne = shop.getPriceAsyncSimple("goods");
        System.out.println("use async api query one time: " + timeWatch);
        try {
            // 这里捕获到错误的话，会直接退出。
            double priceOne = futurePriceOne.get();
            System.out.println("one: " +priceOne);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("use async api query total time: " + timeWatch);
    }

}

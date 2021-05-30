package com.henvealf.learn.java8.async.streamOperators;

import com.henvealf.learn.java8.async.Shop;

/**
 * 一个折扣服务。
 * @author hongliang.yin/Henvealf
 * @date 2018/1/29
 */
public class Discount {

    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLANTUNM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        System.out.println("applyDiscount thread " + Thread.currentThread().getName());
        return quote.getName() + " price is " +
                Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static double apply(double price, Code code) {
        Shop.delay();
        return price * (100 - code.percentage) / 100;
    }

}

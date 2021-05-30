package com.henvealf.learn.java8.async.streamOperators;

/**
 * 代表商品折扣信息的类。
 * @author hongliang.yin/Henvealf
 * @date 2018/1/30
 */
public class Quote {
    private final String name;      // shop 的名称
    private final double price;     // 折扣前的价格
    private final Discount.Code discountCode;   // 折折扣代码

    public Quote(String name, double price, Discount.Code discountCode) {
        this.name = name;
        this.price = price;
        this.discountCode = discountCode;
    }

    public static Quote parse(String s) {
        System.out.println("parseToQuote thread " + Thread.currentThread().getName());
        String[] split = s.split(":");
        String shopName = split[0];
        double price = Double.parseDouble(split[1]);
        Discount.Code discountCode = Discount.Code.valueOf(split[2]);
        return new Quote(shopName, price, discountCode);
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Discount.Code getDiscountCode() {
        return discountCode;
    }
}

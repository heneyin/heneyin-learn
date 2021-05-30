package com.henvealf.learn.java8.five;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 *
 * Created by hongliang.yin/Henvealf on 2017/11/23.
 */
public class Demo {

    static List<Transaction> transactions = null;

    static {
        Trader luxun = new Trader("鲁迅", "绍兴");
        Trader cunshang = new Trader("村上春树", "日本");
        Trader aoweier = new Trader("奥威尔", "法国");
        Trader kafka = new Trader("kafka", "法国");

        transactions = Arrays.asList(
                new Transaction(kafka, 2011, 300),
                new Transaction(luxun, 2012, 1000),
                new Transaction(luxun, 2011, 400),
                new Transaction(cunshang, 2012, 710),
                new Transaction(cunshang, 2012, 700),
                new Transaction(aoweier, 2012, 950)
        );
    }


    //2011 年发生的交易，按照交易额排序
    public static void one() {
        List<Transaction> trs = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(toList());

        System.out.println(trs);
    }

    // 交易员都在哪些城市工作过
    public static void two() {
        List<String> citys = transactions.stream()
                .map(tr -> tr.getTrader().getName())
                .distinct()
                .collect(toList());
        System.out.println(citys);
    }

    // 在法国的交易员的名字，并排序
    public static void three() {
        List<Trader> names = transactions.stream()
                .map(Transaction::getTrader)
                .filter( tr -> tr.getCity().equals("法国"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(toList());
        System.out.println(names);
    }

    // 所有交易员的姓名，并排序
    public static void four() {
        List<String> names = transactions.stream()
                .map(tr -> tr.getTrader().getName())
                .distinct()
                .sorted()
                .collect(toList());
        System.out.println(names);
    }

    // 有没有在日本的
    public static void five() {
        boolean inJapan = transactions.stream()
                .anyMatch(tr -> "日本".equals(tr.getTrader().getCity()));
        System.out.println(inJapan + "");
    }

    // 打印所有在法国的交易员的交易额
    public static void six() {
        System.out.println("---");
        transactions.stream()
                .filter(tr -> "法国".equals(tr.getTrader().getCity()))
                .map(tr -> tr.getValue())
                .forEach(System.out::println);
        System.out.println("---");
    }

    // 最高交易额
    public static void seven() {
        Optional<Integer> em = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        System.out.println(em.toString());
    }

    // 交易额最小的交易
    public static void eight() {
        Optional<Transaction> transaction = transactions.stream()
                .max(Comparator.comparing(Transaction::getValue));
        System.out.println(transaction);
    }

    public static void main(String[] args) {
        one();
        two();
        three();
        four();
        five();

        six();
        seven();
        eight();

    }

}

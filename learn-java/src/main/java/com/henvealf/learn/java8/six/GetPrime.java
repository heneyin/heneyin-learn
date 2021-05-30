package com.henvealf.learn.java8.six;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

/**
 * 使用分区分区质数与非质数
 * Created by hongliang.yin/Henvealf on 2017/11/30.
 */
public class GetPrime {

    public static void main(String[] args) {
        System.out.println(partitionPrimes(23));
    }

    // 是质数
    public static boolean isPrime(int candidate) {
        final int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch( i -> candidate % i == 0);
    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(partitioningBy(GetPrime::isPrime));
    }

    // 获取列表中第一次不符合谓词的元素之前的元素
    public static <A> List<A> takeWhile(List<A> list, Predicate<A> predicate) {
        int i = 0;

        for (A ele: list) {
            if (!predicate.test(ele))
                return list.subList(0, i);
            i++;
        }

        return list;
    }


    public static boolean isPrime(List<Integer> primes, int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return takeWhile(primes, i -> i <= candidateRoot)
                .stream()
                .noneMatch(p -> candidate % p == 0);
    }

}

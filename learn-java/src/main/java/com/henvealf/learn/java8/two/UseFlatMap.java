package com.henvealf.learn.java8.two;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by hongliang.yin/Henvealf on 2017/11/23.
 */
public class UseFlatMap {

    // 得到单词中的字母的去重
    public static void main(String[] args) {
        String[] arrayOfWords = {"hello", "world"};
        List<String> list = Arrays.asList(arrayOfWords);

        List<String> result = list.stream()
                .map( w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(result);

        pairs();

        findAny();
    }



    public static void pairs() {
        List<Integer> numbers1 = Arrays.asList(1,2,3);
        List<Integer> numbers2 = Arrays.asList(3,6,8);

        List<int[]> pairs =
                numbers1.stream().flatMap(
                        i -> numbers2.stream().map( j -> new int[] {i, j} )
                ).collect(Collectors.toList());

        pairs.forEach(x -> System.out.print("(" + x[0] + " " + x[1] + ") "));
    }


    public static void findAny() {
        List<Integer> numbers1 = Arrays.asList(44,12,5,12,1,2,3,3,4,14,13);
        Optional<Integer> i
                = numbers1.stream().filter(x -> x > 10).findAny();

        System.out.println(i.orElse(-1));
    }




}

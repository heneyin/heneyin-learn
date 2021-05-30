package com.henvealf.learn.algorithm.sort;

import java.util.Arrays;

/**
 * <p>
 * 主测试类
 * <p>
 *
 * @author hongliang.yin/Henvealf on 2019-04-06
 */
public class SortMain {

    public static void main(String[] args) {
//        int[] nums = {3,1,5,12,34,123,54,12,75};
        int[] nums = {1,2,3,4,5};
        Sortor sortor = new QuickSort();
        sortor.sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}

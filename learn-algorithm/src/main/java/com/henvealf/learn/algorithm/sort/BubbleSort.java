package com.henvealf.learn.algorithm.sort;


/**
 * <p>
 * 冒泡排序。
 * 时间复杂度 O(N方)
 * <p>
 *
 * @author hongliang.yin/Henvealf on 2019-04-06
 */
public class BubbleSort implements Sortor{

    public void sort(int[] nums) {

        for (int i = 0; i < nums.length - 1; i ++) {
            for (int j = 0; j < nums.length - 1 - i; j ++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }

}

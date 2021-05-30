package com.henvealf.learn.algorithm.sort;

import java.util.Arrays;

/**
 * <p>
 * 归并算法，。
 * <p>
 *
 * @author hongliang.yin/Henvealf on 2019-04-06
 */
public class MergingSort implements Sortor{
    /**
     * 递归版
     * @param nums
     */
    @Override
    public void sort(int[] nums) {
        // 递归版
        sortRecusive(nums);
    }

    /**
     * 非递归版
     * @param nums
     */
    public void sortUnrecusive(int[] nums) {
        int i = 1;  // 初始序列长度

        while (i < nums.length) {
            int j = 0;    // 成对序列的第一个元素 index
            while (i + j < nums.length) {
                // mergingTwoNums(nums, j, j + i, j + i, min(j + 2 * i, n));
                j = 2 * i + j;
            }
            i = 2 * i;
        }
    }

    /**
     * 递归版
     * @param nums
     */
    public void sortRecusive(int[] nums) {
        if (nums.length <= 1) {
            return;
        }

        int[] left = Arrays.copyOfRange(nums, 0, nums.length / 2);
        int[] right = Arrays.copyOfRange(nums, nums.length / 2, nums.length);

        sortRecusive(left);
        sortRecusive(right);

        mergingTwoNums(nums, left, right);
    }

    /**
     * 合并有序两个数组
     * @param numLeft
     * @param numRight
     * @return
     */
    public void mergingTwoNums(int[] des, int[] numLeft, int[] numRight ) {

        int[] temp = new int[des.length];
        int i = 0;
        int j = 0;

        while (i < numLeft.length && j < numRight.length ) {
            if (numLeft[i] < numRight[j]) {
                temp[i + j] = numLeft[i++];
            } else {
                temp[i + j] = numRight[j++];
            }
        }

        if (i < numLeft.length) {
            System.arraycopy(numLeft, i, temp,i + j, numLeft.length - i);
        }

        if (j < numRight.length) {
            System.arraycopy(numRight, j, temp,i + j, numRight.length - j);
        }

        System.arraycopy(temp, 0, des,0, temp.length);
    }

}

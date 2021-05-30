package com.henvealf.learn.algorithm.sort;

/**
 * <p>
 * 梳排序。
 * 算是冒泡的变形。是将几个数进行 跨步分组，分组内进行冒泡排序，跨度 不断减少，最小为 1，为 1 即相邻的两个元素进行比较交换，就退化为了冒泡排序。
 * 一般在退化到冒泡排序之前排序就会完成。
 *
 * 时间复杂度：因为每组的数目
 * <p>
 *
 * @author hongliang.yin/Henvealf on 2019-04-06
 */
public class CombSort implements Sortor{

    @Override
    public void sort(int[] nums) {
        int j = nums.length; // 跨度
        double s = 3; // 跨度递减倍数
        boolean notFinish = false; // 判断是否完成

        while (j > 1 || notFinish) {   // j 开始只是为了判断跨步是否正常，终止循环的关键是 flag。
            int i = 0;
            j = Math.max(1, (int)(j / s));
            notFinish = false;
            while (i + j < nums.length) {
                if (nums[i] > nums[i + j]) {
                    int temp = nums[i];
                    nums[i] = nums[i + j];
                    nums[i + j] = temp;
                    notFinish = true;
                }
                i ++;
            }
        }
    }

}

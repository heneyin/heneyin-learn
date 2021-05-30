package com.henvealf.learn.algorithm.sort;

/**
 * <p>
 * 快速排序
 * <p>
 *
 * @author hongliang.yin/Henvealf on 2019-04-06
 */
public class QuickSort implements Sortor{

    @Override
    public void sort(int[] nums) {

        recusiveSort(nums, 0 , nums.length - 1);
    }

    public void recusiveSort(int[] nums, int start, int end) {
        if (start > end) {
            return;
        }
        int p = partition(nums, start, end);
        recusiveSort(nums, start, p - 1);
        recusiveSort(nums, p + 1, end);
    }

    private static int partition(int[] arr, int low, int high){
        int pivot = arr[low];     //枢轴记录
        while (low < high){
            while (low < high && arr[high] >= pivot)
                --high;
            arr[low] = arr[high];             //交换比枢轴小的记录到左端
            while (low < high && arr[low] <= pivot)
                ++low;
            arr[high] = arr[low];           //交换比枢轴小的记录到右端
        }
        //扫描完成，枢轴到位
        arr[low] = pivot;
        System.out.println("low " + low + " high " + high);
        //返回的是枢轴的位置
        return low;
    }

}

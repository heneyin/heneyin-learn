package com.henvealf.learn.datastructure.array;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;

/**
 * 测试一下 arrayMap 和 HashMap 性能
 * array 遍历 key 也还是比不上 hash
 * @author hongliang.yin/Heneyin
 * @date 2022/2/17
 */
public class TinyArrayVsMap {

    @Ignore
    @Test
    public void normal() {
        ArrayMap<String, String> arrayMap = new ArrayMap<>(5);
        HashMap<String, String> hashMap = new HashMap<>(5);

        arrayMap.put("a", "a1");
        arrayMap.put("b", "b1");
        arrayMap.put("c", "c1");
        arrayMap.put("d", "d1");
        arrayMap.put("e", "d1");
        arrayMap.put("f", "d1");

        hashMap.put("a", "a1");
        hashMap.put("b", "b1");
        hashMap.put("c", "c1");
        hashMap.put("d", "d1");
        hashMap.put("e", "d1");
        hashMap.put("f", "d1");

        for (int i = 0; i < 1000; i++) {
            arrayMap.get("d");
        }

        long count = 90000000;

        long startTime = System.currentTimeMillis();
        for (long i = 0; i < count; i++) {
            arrayMap.get("e");
        }
        long totalTime = System.currentTimeMillis() - startTime;

        double result = count / ((double)(totalTime) / 1000);
        System.out.printf("result for arrayMap: %.2f\n", result);

        long startTime1 = System.currentTimeMillis();
        for (long i = 0; i < count; i++) {
            hashMap.get("e");
        }
        long totalTime1 = System.currentTimeMillis() - startTime1;

        double result1 = count / ((double)(totalTime1) / 1000);
        System.out.printf("result for hashMap:  %.2f ", result1);
    }

}

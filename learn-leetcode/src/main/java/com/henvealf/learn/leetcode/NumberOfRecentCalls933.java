package com.henvealf.learn.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *
 * <p>
 *
 * @author hongliang.yin/Henvealf on 2019-02-15
 */
public class NumberOfRecentCalls933 {


    private LinkedList<Integer> queue = new LinkedList<>();
    public NumberOfRecentCalls933() {

    }

    public int ping(int t) {
        queue.add(t);
        queue.addLast(t);
        if(t > 3000) {
            if(queue.size() > 0 && queue.getFirst() > t - 3000) {
                queue.pop();
            }
        }
        return queue.size();
    }


}

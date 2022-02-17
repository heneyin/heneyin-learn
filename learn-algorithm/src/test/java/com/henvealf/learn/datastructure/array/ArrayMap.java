package com.henvealf.learn.datastructure.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hongliang.yin/Heneyin
 * @date 2022/2/17
 */
public class ArrayMap<K, V> {

    private List<K> keys;
    private List<V> values;

    public ArrayMap(int cap) {
        keys = new ArrayList<>(cap);
        values = new ArrayList<>(cap);
    }

    public ArrayMap() {
        keys = new ArrayList<>();
        values = new ArrayList<>();
    }

    public V put(K k, V v) {
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i).equals(k)) {
                V oldv = values.get(i);
                values.set(i, v);
                return oldv;
            }
        }

        keys.add(k);
        values.add(v);
        return null;
    }

    public V get(K k) {
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i).equals(k)) {
                return values.get(i);
            }
        }
        return null;
    }

}

package com.heneyin.learn.java21.patternmatching;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SequencedCollection;
import java.util.SequencedMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author hongliang.yin/Heneyin
 * @date 2025/8/4
 */
public class SequencedCollectionsTest {

    @Test
    public void testSequencedCollectionOperations() {
        SequencedCollection<String> list = new ArrayList<>(List.of("B", "C"));

        // 新添加的方法
        list.addFirst("A");  // [A, B, C]
        list.addLast("D");   // [A, B, C, D]

        assertEquals("A", list.getFirst());
        assertEquals("D", list.getLast());

        assertEquals("A", list.removeFirst());
        assertEquals("D", list.removeLast());
        assertEquals(List.of("B", "C"), list);
    }

    @Test
    void testSequencedMapOperations() {
        SequencedMap<Integer, String> map = new LinkedHashMap<>();
        map.put(1, "One");
        map.put(2, "Two");

        // 新添加的方法
        map.putFirst(0, "Zero");
        map.putLast(3, "Three");

        assertEquals(Map.entry(0, "Zero"), map.firstEntry());
        assertEquals(Map.entry(3, "Three"), map.lastEntry());

        assertEquals(Map.entry(0, "Zero"), map.pollFirstEntry());
        assertEquals(Map.entry(3, "Three"), map.pollLastEntry());
    }
}

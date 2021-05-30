package com.henvealf.learn.java.bitmap;

import org.junit.Test;
import org.roaringbitmap.RoaringBitmap;
import org.roaringbitmap.longlong.LongBitmapDataProvider;
import org.roaringbitmap.longlong.Roaring64NavigableMap;
import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author hongliang.yin/Henvealf
 * @date 2020/9/16
 */
public class BitmapTest {

    @Test
    public void test_count_distinct() {
        RoaringBitmap roaringBitmap = new RoaringBitmap();
        for (int i = 1; i <= 1000000; i++) {
            roaringBitmap.add(i);
        }
        long longCardinality = roaringBitmap.getLongCardinality();
        System.out.println("longCardinality: " + longCardinality);
    }

    @Test
    public void test_merge() {
        RoaringBitmap roaringBitmap1 = new RoaringBitmap();
        for (int i = 1; i <= 1000; i++) {
            roaringBitmap1.add(i);
        }
        RoaringBitmap roaringBitmap2 = new RoaringBitmap();
        for (int i = 501; i <= 2000; i++) {
            roaringBitmap2.add(i);
        }

        roaringBitmap1.or(roaringBitmap2);
        long longCardinality = roaringBitmap1.getLongCardinality();
        System.out.println("longCardinality: " + longCardinality);
    }

    @Test
    public void test_save_and_load() throws IOException {
        RoaringBitmap roaringBitmap1 = new RoaringBitmap();
        for (int i = 1; i <= 1000000; i++) {
            roaringBitmap1.add(i);
        }

        String s = roaringBitmap1.toString();
        // System.out.println("toString: " + s);
        int[] ints = roaringBitmap1.toArray();
        // System.out.println("toArray: " + Arrays.stream(ints).boxed().map(String::valueOf).collect(Collectors.joining(",")));
        long longCardinality = roaringBitmap1.getLongCardinality();

        int serializedSizeInBytes = roaringBitmap1.serializedSizeInBytes();
        System.out.println("serializedSizeInMb: " + (double)serializedSizeInBytes / 1024 / 1024 + "MB");
        System.out.println("serializedSizeInKb: " + (double)serializedSizeInBytes / 1024);
        System.out.println("serializedSizeInBytes: " + (double)serializedSizeInBytes );
        ByteBuffer byteBuffer = ByteBuffer.allocate(serializedSizeInBytes);
        roaringBitmap1.serialize(byteBuffer);

        byte[] array = byteBuffer.array();
        RoaringBitmap loaded = RoaringBitmap.bitmapOf();
        loaded.deserialize(ByteBuffer.wrap(array));

        long loadedLongCardinality = roaringBitmap1.getLongCardinality();
        System.out.println("loadedLongCardinality: " + loadedLongCardinality);

    }

    @Test
    public void test_long() {
        LongBitmapDataProvider r = new Roaring64NavigableMap();
        for (long i = 1; i <= 100000_000; i++) {
            r.addLong(i);
        }
        long serializedSizeInBytes = r.serializedSizeInBytes();
        System.out.println("serializedSizeInBytes: " + serializedSizeInBytes / 1024 / 1024 + "MB");
    }

}

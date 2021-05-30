package com.henvealf.learn.dbmap.test;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.mapdb.*;
import org.mapdb.serializer.SerializerArrayTuple;
import org.mapdb.serializer.SerializerCompressionWrapper;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;


/**
 * @author hongliang.yin/Henvealf
 * @date 2019-06-28
 */
public class HelloWorld {

    @Test
    public void test_memoryDB() {
        DB db = DBMaker.memoryDB().make();
        ConcurrentMap<String, String> map = db
                .hashMap("map", Serializer.STRING, Serializer.STRING)
                .createOrOpen();
        map.put("something", "here");
        System.out.println(map.getOrDefault("something", "nonde"));
        db.close();
    }

    @Test
    public void test_FileDb() {
        // 创建一个存储在 file 上的 db。
        DB db = DBMaker.fileDB("file.db").make();
        // 从 db 中创建一个 HashMap。
        ConcurrentMap<String, String> map = db
                .hashMap("map", Serializer.STRING, Serializer.STRING)
                .createOrOpen();

        String something = map.get("something");
        System.out.println(something);
        //map.put("something", "here");
        db.close();
    }

    @Test
    public void test_memory() throws InterruptedException {
        // 创建一个存储在 file 上的 db。
        DB db = DBMaker.memoryDB().make();
        // 从 db 中创建一个 HashMap。
        HTreeMap<String, String> map = db
                .hashMap("map", Serializer.STRING, Serializer.STRING)
                .createOrOpen();
        long start = System.currentTimeMillis();
        for (int i =0 ; i < 10000000; i ++) {
            map.put("something" + i, "here");
        }
        System.out.println(System.currentTimeMillis() - start);
        Thread.sleep(1000000);
        db.close();
    }

    @Test
    public void test_memory1() throws InterruptedException {
        // 创建一个存储在 file 上的 db。
        DB db = DBMaker.memoryDB()
                .concurrencyDisable()
                .make();
        // 从 db 中创建一个 HashMap。
        HTreeMap<String, String> map = db
                .hashMap("map", Serializer.STRING, Serializer.STRING)
                .createOrOpen();
        long start = System.currentTimeMillis();
        for (int i =0 ; i < 10000000; i ++) {
            map.put("something" + i, "here");
        }
        System.out.println(System.currentTimeMillis() - start);
        Thread.sleep(1000000);
        db.close();
    }

    @Test
    public void test_memory2() throws InterruptedException {
        DB db = DBMaker.fileDB("file3.db")
                .fileChannelEnable()
                .allocateStartSize( 1024 * 1024 * 1024L ) // 10GB
                .allocateIncrement(1024 * 1024)
                .cleanerHackEnable()
                .closeOnJvmShutdown()
                .fileMmapEnableIfSupported()
                .concurrencyDisable()
                .make();

        HTreeMap<String,String> hmap = db.hashMap("test")
                .keySerializer(Serializer.STRING)
                .valueSerializer(Serializer.STRING)
                .createOrOpen();
        long start = System.currentTimeMillis();
        for (int i = 1000000 ; i < 10000000; i ++) {
            hmap.put("something" + i, "here");
        }
        System.out.println(System.currentTimeMillis() - start);
        long start1 = System.currentTimeMillis();
        hmap.getOrDefault("something1", "123");
        hmap.getOrDefault("something2", "123");
        hmap.getOrDefault("something3", "123");
        hmap.getOrDefault("something4", "123");
        hmap.getOrDefault("something5", "123");
        System.out.println(System.currentTimeMillis() - start1);
        Thread.sleep(1000000);
        db.close();
    }

    @Test
    public void test_getCPU() {
        DB db = DBMaker.fileDB("file3.db")
                .fileChannelEnable()
//                .allocateStartSize(  // 10GB
//                .allocateIncrement(1024 * 1024)
                .cleanerHackEnable()
                .closeOnJvmShutdown()
                .fileMmapEnableIfSupported()
                .concurrencyDisable()
                .make();
        HTreeMap<String,String> hmap = db.hashMap("test")
                .keySerializer(Serializer.STRING)
                .valueSerializer(Serializer.STRING)
                .createOrOpen();
        System.out.println(hmap.size());
    }

    /**
     * 测试 BTreeMap 的基本用法。
     */
    @Test
    public void test_bTreeMap() {
        DB db = DBMaker.fileDB("file4")
                .fileMmapEnableIfSupported()
                .closeOnJvmShutdown()
                .make();

        BTreeMap<Object[], Entity> mapThree = db.treeMap("towns")
                .keySerializer(new SerializerArrayTuple(
                        Serializer.STRING, Serializer.STRING, Serializer.INTEGER))
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();

        BTreeMap<Object[], Entity> mapOne = db.treeMap("one")
                .keySerializer(new SerializerArrayTuple(
                        Serializer.INTEGER))
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();

        Entity en1 =  new Entity("en1");
        Entity en2 =  new Entity("en2");
        Entity en3 =  new Entity("en3");

        mapThree.put(new Object[]{"12345", "54321", 12345}, en1);
        mapThree.put(new Object[]{"12346", "54321", 12346}, en2);
        mapThree.put(new Object[]{"12346", "54322", 12346}, en2);
        mapThree.put(new Object[]{"12346", "54323", 12346}, en2);
        mapThree.put(new Object[]{"12347", "54321", 12347}, en3);
        mapThree.put(new Object[]{"12347", "54322", 12347}, en3);
        mapThree.put(new Object[]{"12347", "54323", 12347}, en3);
        mapThree.put(new Object[]{"12347", "54323", 12347}, en3);

        mapOne.put(new Object[]{12345}, en1);
        mapOne.put(new Object[]{12346}, en2);
        mapOne.put(new Object[]{12347}, en3);

        Map<Object[], Entity> cong1 = mapThree.subMap(new Object[]{null,"54322", null}, true,
                                                     new Object[]{null,"54323",  null}, true);
//        Map<Object[], Entity> cong1 = mapThree.prefixSubMap(new Object[]{"12346","54322",12346});

        Map<Object[], Entity> cong2 =  mapOne.prefixSubMap(new Object[]{12345});

        System.out.println(cong1.values());
        System.out.println(cong2.values());

        System.out.println(Lists.newArrayList(cong1.values()).get(0).hashCode());
        System.out.println(Lists.newArrayList(cong2.values()).get(0).hashCode());
        db.close();
    }


    /**
     * 测试什么都没有
     *
     * 第一个 10万 :66864ms
     * total space: 28MB
     * ----------------------
     * 第二个 10万 :56285ms
     * total space: 53MB
     * ----------------------
     * @throws InterruptedException
     */
    @Test
    public void test_bTreeMap_storage_use_insert_time1() throws InterruptedException {
        // 测试什么都没有
        DB db = DBMaker.fileDB("file5")
                .closeOnJvmShutdown()
                .make();

        test_bTreeMap_storage(db);

        File file = new File("file5");
        file.deleteOnExit();
    }

    /**
     * 测试加了 fileMMap
     * 第一个 10万 :13898ms
     * total space: 28MB
     * ----------------------
     * 第二个 10万 :12711ms
     * total space: 53MB
     * ----------------------
     * @throws InterruptedException
     */
    @Test
    public void test_bTreeMap_storage_use_insert_time2() throws InterruptedException {
        //
        DB db = DBMaker.fileDB("file5")
                .closeOnJvmShutdown()
                .fileMmapEnableIfSupported()
                .fileMmapPreclearDisable()
                .cleanerHackEnable()
                .make();

        test_bTreeMap_storage(db);

        File file = new File("file5");
        file.deleteOnExit();
    }

    /**
     * 测试加了 fileMMap 与 filechannel
     * 第一个 10万 :12419ms
     * total space: 28MB
     * ----------------------
     * 第二个 10万 :11851ms
     * total space: 53MB
     * ----------------------
     * @throws InterruptedException
     */
    @Test
    public void test_bTreeMap_storage_use_insert_time3() throws InterruptedException {
        //
        DB db = DBMaker.fileDB("file5")
                .closeOnJvmShutdown()
                .fileMmapEnableIfSupported()
                .fileMmapPreclearDisable()
                .cleanerHackEnable()
                .fileChannelEnable()
                .make();

        test_bTreeMap_storage(db);

        File file = new File("file5");
        file.deleteOnExit();
    }

    /**
     * 测试加了 fileMMap 与 filechannel 与 提前分配
     * ----------------------
     * 第一个 10万 :12161ms
     * total space: 50MB
     * ----------------------
     * 第二个 10万 :11493ms
     * total space: 53MB
     * ----------------------
     * @throws InterruptedException
     */
    @Test
    public void test_bTreeMap_storage_use_insert_time4() throws InterruptedException {
        //
        DB db = DBMaker.fileDB("file5")
                .closeOnJvmShutdown()
                .fileMmapEnableIfSupported()
                .fileMmapPreclearDisable()
                .cleanerHackEnable()
                .fileChannelEnable()
                .allocateStartSize( 50 * 1024 * 1024)
                .make();

        test_bTreeMap_storage(db);

        File file = new File("file5");
        file.deleteOnExit();
    }

    /**
     * 测试加了 fileMMap 与 filechannel 与 提前分配 与 分在外部与压缩
     * 第一个 10万 :2232ms
     * total space: 50MB
     * ----------------------
     * 第二个 10万 :1549ms
     * total space: 66MB
     * ----------------------
     * @throws InterruptedException
     */
    @Test
    public void test_bTreeMap_storage_use_insert_time5() throws InterruptedException {
        //
        DB db = DBMaker.fileDB("file5")
                .closeOnJvmShutdown()
                .fileMmapEnableIfSupported()
                .fileMmapPreclearDisable()
                .cleanerHackEnable()
                .fileChannelEnable()
                .allocateStartSize( 50 * 1024 * 1024)
                .make();

        BTreeMap<Object[], Entity> mapOne = db.treeMap("one")
                .keySerializer(new SerializerArrayTuple(
                        Serializer.STRING))
                .valuesOutsideNodesEnable()
                .valueSerializer(new SerializerCompressionWrapper(Serializer.JAVA))
                .createOrOpen();

        BTreeMap<Object[], Entity> mapTwo = db.treeMap("two")
                .keySerializer(new SerializerArrayTuple(
                        Serializer.STRING))
                .valuesOutsideNodesEnable()
                .valueSerializer(new SerializerCompressionWrapper(Serializer.JAVA))
                .createOrOpen();

        Entity entity = new Entity("entityOne");
        printFileSpaceUse("file5");
        int total = 100000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < total; i ++) {
            mapOne.put(new Object[]{"one" + i}, entity);
        }
        System.out.println("第一个 10万 :" + (System.currentTimeMillis() - start) + "ms");

        printFileSpaceUse("file5");
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < total; i ++) {
            mapTwo.put(new Object[]{"mapTwo" + i}, entity);
        }
        System.out.println("第二个 10万 :" + (System.currentTimeMillis() - start1) + "ms");
        printFileSpaceUse("file5");
        System.out.println(mapOne.getOrDefault(new Object[]{"one" + 10}, null));
        File file = new File("file5");
        file.deleteOnExit();
        db.close();
    }

    /**
     * 测试压缩 String
     */
    @Test
    public void test_bTreeMap_storage_use_insert_10000000_time() {

        DB db = DBMaker.fileDB("file5")
                .closeOnJvmShutdown()
                .fileMmapEnableIfSupported()
                .fileMmapPreclearDisable()
                .cleanerHackEnable()
                .fileChannelEnable()
                .allocateStartSize( 2 * 1024 * 1024 * 1024L)
                .allocateIncrement( 500 * 1024  * 1024)
                .make();

        BTreeMap<Object[], String> mapOne = db.treeMap("one")
                .keySerializer(new SerializerArrayTuple(
                        Serializer.STRING))
                .valuesOutsideNodesEnable()
                .valueSerializer(new SerializerCompressionWrapper(Serializer.STRING))
                .createOrOpen();

        Entity entity = new Entity("entityOne");
        printFileSpaceUse("file5");
        int total = 10000000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < total; i ++) {
            mapOne.put(new Object[]{"one" + i}, "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
        }
        System.out.println("第一个 1000万 :" + (System.currentTimeMillis() - start) + "ms");

        printFileSpaceUse("file5");
        System.out.println(mapOne.getOrDefault(new Object[]{"one" + 10}, null));
        File file = new File("file5");
        file.deleteOnExit();
        db.close();
    }

    private void printFileSpaceUse(String fileName) {
        File file = new File(fileName);
        System.out.println("total space: " + file.length() / 1024 / 1024 + "MB");
        System.out.println("----------------------");
    }

    private void test_bTreeMap_storage(DB db) {

        BTreeMap<Object[], Entity> mapOne = db.treeMap("one")
                .keySerializer(new SerializerArrayTuple(
                        Serializer.STRING))
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();

        BTreeMap<Object[], Entity> mapTwo = db.treeMap("two")
                .keySerializer(new SerializerArrayTuple(
                        Serializer.STRING))
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();

        Entity entity = new Entity("entityOne");
        printFileSpaceUse("file5");
        int total = 100000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < total; i ++) {
            mapOne.put(new Object[]{"one" + i}, entity);
        }
        System.out.println("第一个 10万 :" + (System.currentTimeMillis() - start) + "ms");

        printFileSpaceUse("file5");
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < total; i ++) {
            mapTwo.put(new Object[]{"mapTwo" + i}, entity);
        }
        System.out.println("第二个 10万 :" + (System.currentTimeMillis() - start1) + "ms");
        printFileSpaceUse("file5");
    }

    static class Entity implements Serializable {
        private String name;
        private List<String> src_ip_ueba_eids;
        private List<String> mds;

        public Entity(String name) {
            this.name = name;
            this.src_ip_ueba_eids = Lists.newArrayList("123","456");
            this.mds = Lists.newArrayList("md1","md2","md3");
        }

        public String getName() {
            return name;
        }

        public List<String> getSrc_ip_ueba_eids() {
            return src_ip_ueba_eids;
        }

        public List<String> getMds() {
            return mds;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSrc_ip_ueba_eids(List<String> src_ip_ueba_eids) {
            this.src_ip_ueba_eids = src_ip_ueba_eids;
        }

        public void setMds(List<String> mds) {
            this.mds = mds;
        }

        @Override
        public String toString() {
            return "Entity{" +
                    "name='" + name + '\'' +
                    ", src_ip_ueba_eids=" + src_ip_ueba_eids +
                    ", mds=" + mds +
                    '}';
        }
    }

}

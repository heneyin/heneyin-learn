package com.henvealf.learn.flink.java.join;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Duration;
import java.util.Properties;
import java.util.UUID;

public class TestJsonSender {
    private static KafkaProducer PRODUCER;
    static {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9093");
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        PRODUCER = new KafkaProducer<>(props);
    }

    public static void main(String[] args) throws InterruptedException {
         sendB(66 * 1000 + 1);
//        sendWithOrderness();
        PRODUCER.close();
    }

    /**
     * 测试延时
     */
    public static void sendWithOrderness() throws InterruptedException {
        // sendB(Duration.ofMinutes(5).toMillis() + 3001);
        //
//        Thread.sleep(10 * 1000);
        sendA(1000);
        sendA(5000);
        sendA(10000);
        // a 走到 10 秒，b 才发出第一秒数据
        sendB(1000);

        sendB(5000);

        sendA(11000);

        sendB(4000);

        sendA(13000);

        sendB(5000);

//        sendA(61 * 1000);
        sendB(61 * 1000);
//        sendB(4000);
    }

    /**
     * 一a 对 多b
     * 结果为三条记录
     * a b1
     * a b2
     * a b3
     */
    private static void testOneASomeB() {
        sendA();

        sendB();
        sendB();
        sendB();
    }

    /**
     * 多a 一b
     * 结果为三条记录
     * a1 b
     * a2 b
     * a3 b
     */
    private static void testSomeBOneA() {
        sendA();
        sendA();
        sendA();

        sendB();
    }

    /**
     * 只有 a
     * 结果为三条记录
     */
    private static void testOnlyA() {
        sendA();
        sendA();
        sendA();
    }

    /**
     * 只有 a
     * 结果为三条记录
     */
    private static void testOnlyB() {
        sendB();
        sendB();
        sendB();
    }

    private static void sendA() {
        sendA(System.currentTimeMillis());
    }


    private static void sendB() {
        sendB(System.currentTimeMillis());
    }

    private static void sendA(long time) {
        JSONObject a = new JSONObject();
        a.put("ceventtime", time);
        a.put("csrcip", "192.168.23.11");
        a.put("ceventname", "i_am_flow");
        a.put("ceventtype", "a");
        a.put("id", UUID.randomUUID().toString());
        String s = a.toJSONString();
        send(IntervalJoinMain.INTERVAL_JOIN_A, s);
    }

    private static void sendB(long time) {
        JSONObject a = new JSONObject();
        a.put("ceventtime", time);
        a.put("csrcip", "192.168.23.11");
        a.put("ceventname", "i_am_flow");
        a.put("ceventtype", "b");
        a.put("id", UUID.randomUUID().toString());
        String s = a.toJSONString();
        send(IntervalJoinMain.INTERVAL_JOIN_B, s);
    }

    private static void send(String topic, String message) {
        PRODUCER.send(new ProducerRecord<>(topic, message));
    }

}

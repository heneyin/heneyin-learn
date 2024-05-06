package com.henvealf.learn.flink.java.join;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.LocalDateTime;
import java.util.Properties;
import java.util.UUID;

public class TestSender {
    private static KafkaProducer PRODUCER;
    static {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9093");
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        PRODUCER = new KafkaProducer<>(props);
    }

    public static void main(String[] args) {
         testOneASomeB();
//        testSomeBOneA();
//        testOnlyA();
        // testOnlyB();
        PRODUCER.close();
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
        send(IntervalJoinMain.INTERVAL_JOIN_A, "hello-a__" + UUID.randomUUID());
    }

    private static void sendB() {
        send(IntervalJoinMain.INTERVAL_JOIN_B, "hello-b__" + UUID.randomUUID());
    }


    private static void send(String topic, String message) {
        PRODUCER.send(new ProducerRecord<>(topic, message));
    }

}

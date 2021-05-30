package com.henvealf.learn.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * 测试 kafka 生产者连接异常的表现。
 *
 * topic 创建：
 * kafka-topics.sh --create --bootstrap-server kafka:9092 --topic  test_producer1 --partitions 3 --replication-factor 1
 *
 * @author hongliang.yin/Henvealf
 * @date 2019-09-07
 */
public class ProducerDisconnectionTest {

    private Logger logger = LoggerFactory.getLogger(ProducerDisconnectionTest.class);
    /**
     * 测试一只没有 server 的情况。
     *
     * acks = all
     *
     * 结果：因为 channel 连接失败。发送线程在会抛出以下异常，并不断重试。
     *
     * java.net.ConnectException: Connection refused
     * 	at sun.nio.ch.SocketChannelImpl.checkConnect(Native Method)
     * 	at sun.nio.ch.SocketChannelImpl.finishConnect(SocketChannelImpl.java:717)
     * 	at org.apache.kafka.common.network.PlaintextTransportLayer.finishConnect(PlaintextTransportLayer.java:50)
     * 	at org.apache.kafka.common.network.KafkaChannel.finishConnect(KafkaChannel.java:106)
     * 	at org.apache.kafka.common.network.Selector.pollSelectionKeys(Selector.java:444)
     * 	at org.apache.kafka.common.network.Selector.poll(Selector.java:398)
     * 	at org.apache.kafka.clients.NetworkClient.poll(NetworkClient.java:460)
     * 	at org.apache.kafka.clients.producer.internals.Sender.run(Sender.java:239)
     * 	at org.apache.kafka.clients.producer.internals.Sender.run(Sender.java:163)
     * 	at java.lang.Thread.run(Thread.java:748)
     *
     * 	简单看了代码，发送大概分为两步，
     *
     *  第一步将数据发送到 nio channel 中。
     *  第二步检查 channel 中返回的响应结果。这里在检查时抛出的异常。
     */
    @Test
    public void test_no_server_when_start_acks_all() {
        Properties properties = getProperties();
        properties.setProperty(TestConstants.CONFIG_ACKS, "all");
        KafkaProducer<String, String> producer
                = new KafkaProducer<>(properties);
        logger.info("Begin send message..");
        int i = 0;
        producer.send(new ProducerRecord<>(TestConstants.TOPIC_NAME, "i am test " + i));
    }

    /**
     * acks = 0
     * 与 acks = all 表现相同。
     */
    @Test
    public void test_no_server_when_start_acks_0() {
        Properties properties = getProperties();
        properties.setProperty(TestConstants.CONFIG_ACKS, "0");
        KafkaProducer<String, String> producer
                = new KafkaProducer<>(properties);
        logger.info("Begin send message..");
        int i = 0;
        producer.send(new ProducerRecord<>(TestConstants.TOPIC_NAME, "i am test " + i));
    }

    /**
     * 测试服务起初未启动，发送一会后服务启动的情况。
     *
     * server 启动之前：报错：WARN Connection to node -1 (localhost/127.0.0.1:9092) could not be established. Broker may not be available.
     * server 启动之后， 正常发送。
     *
     * 测试 server 中途断开又恢复的情况。
     *
     * server 恢复后，会继续发送，数据不丢失。
     */
    @Test
    public void test_server_start_after_send_while() throws InterruptedException {
        Properties properties = getProperties();
        properties.put("bootstrap.servers", "localhost:9092");
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        logger.info("Begin send message..");
        int i = 0;
        while (true) {
            producer.send(new ProducerRecord<>(TestConstants.TOPIC_NAME, "i am test " + i));
            i ++;
            logger.info("send end " + i);
            Thread.sleep(1000);
        }
    }

    /**
     * 测试发送
     */
    @Test
    public void test_server_shutdown_after_a_while() {

    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put(TestConstants.CONFIG_ACKS, "all");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return properties;
    }

}

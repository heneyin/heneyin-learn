package com.henvealf.learn.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author hongliang.yin/Henvealf
 * @date 2019-09-07
 */
public class ConsumerDisconnectionTest {

    private Logger logger = LoggerFactory.getLogger(ConsumerDisconnectionTest.class);


    @Test
    public void consumer_normal() {
        Properties properties = KafkaTestUtil.getConsumerProperties();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Collections.singletonList(TestConstants.TOPIC_NAME));
        while (true) {
            ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> record : poll) {
                logger.info(record.value());
            }
        }
    }

    /**
     * 测试消费者长时间没有消费到数据的情况。
     *
     * 23:03 开始
     * 第二天 9 点 发送能正常接收。
     */
    @Test
    public void test_consumer_long_time_no_message() {
        Properties properties = KafkaTestUtil.getConsumerProperties();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList(TestConstants.TOPIC_NAME));
        while (true) {
            ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(10));
            for (ConsumerRecord<String, String> record : poll) {
                logger.info(record.value());
            }
        }
    }



}

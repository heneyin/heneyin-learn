package com.henvealf.learn.kafka;

import org.apache.kafka.clients.consumer.CommitFailedException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * 消费者提交测试
 * @author hongliang.yin/Henvealf
 * @date 2019-10-10
 */
public class ConsumerCommitTest {

    private Logger logger = LoggerFactory.getLogger(ConsumerCommitTest.class);


    /**
     * 测试由于处理延迟导致的提交失败, 会抛出 CommitFailedException
     */
    @Test
    public void testCommitSync_CommitFailedException() throws InterruptedException {
        Properties kafkaProp = KafkaTestUtil.getConsumerProperties();
        kafkaProp.put("enable.auto.commit", "false");
        kafkaProp.put("max.poll.interval.ms", "10000");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(kafkaProp);
        consumer.subscribe(Collections.singleton("test"));
        try {
            ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(1));
            Thread.sleep(11 * 1000);
            consumer.commitSync();
        } catch (CommitFailedException e) {
          e.printStackTrace();
        } finally {
            consumer.close();
        }
    }

    /**
     * 测试 commitAsync 在处理超时时候的行为。
     * 效果为: 超时后，下一次拉取会重新处理超时的数据。直到不再超时。
     * @throws InterruptedException
     */
    @Test
    public void testCommitAsync_process_timeout() throws InterruptedException {
        Properties kafkaProp = KafkaTestUtil.getConsumerProperties();
        kafkaProp.put("enable.auto.commit", "false");
        kafkaProp.put("max.poll.interval.ms", "10000");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(kafkaProp);
        consumer.subscribe(Collections.singleton("test"));
        try {
            int second = 7;
            int plus = 1;
            while (true) {
                ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(1));
                for (TopicPartition partition : poll.partitions()) {
                    logger.info("---------- offset ppp: " + partition.toString());
                }
                for (ConsumerRecord<String, String> record : poll) {
                    logger.info("------------ value" + record.value());
                }
                logger.info("second: " + second);
                Thread.sleep(second * 1000);
                if (second > 12) plus = -1;
                second += plus;

                consumer.commitAsync();
                logger.info("------- process end ------------");
            }
        } finally {
            consumer.close();
        }
    }



}

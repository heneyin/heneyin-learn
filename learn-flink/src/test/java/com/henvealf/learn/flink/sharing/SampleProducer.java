package com.henvealf.learn.flink.sharing;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import java.util.Properties;

/**
 * @author hongliang.yin/Heneyin
 * @date 2021/5/30
 */
public class SampleProducer {

    @Test
    public void normal() throws InterruptedException {
        Properties properties = WordCountExample.kafkaProducerProperties();
        properties.setProperty("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<>(KafkaConstant.SOURCE_TOPIC, "hello you"));
            producer.send(new ProducerRecord<>(KafkaConstant.SOURCE_TOPIC, "hello me"));
            producer.send(new ProducerRecord<>(KafkaConstant.SOURCE_TOPIC, "hello world"));
            producer.send(new ProducerRecord<>(KafkaConstant.SOURCE_TOPIC, "hello good world"));
            producer.send(new ProducerRecord<>(KafkaConstant.SOURCE_TOPIC, "hello bad world"));
            System.out.println("---");
            Thread.sleep(5000);
        }

    }

    @Test
    public void normalWatermark() {

    }

}

package com.henvealf.learn.kafka;

import java.util.Properties;

/**
 * @author hongliang.yin/Henvealf
 * @date 2019-10-10
 */
public class KafkaTestUtil {

    static Properties getConsumerProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", "test1");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return properties;
    }
}

package com.henvealf.learn.flink.java.join;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.ProcessJoinFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;
import org.apache.flink.util.Preconditions;

import java.time.Duration;
import java.util.Properties;

public class IntervalJoinMain {

    public static final String INTERVAL_JOIN_A = "interval-join-a";
    public static final String INTERVAL_JOIN_B = "interval-join-b";

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        configuration.setInteger("rest.port", 8081);

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(configuration);
        env.setParallelism(1);

        KafkaSource<String> kafkaSourceA = createKafkaSource(INTERVAL_JOIN_A, "flink-group-a");
        KafkaSource<String> kafkaSourceB = createKafkaSource(INTERVAL_JOIN_B, "flink-group-b");

        DataStreamSource<String> streamA =
                env.fromSource(kafkaSourceA, WatermarkStrategy.forBoundedOutOfOrderness(Duration.ZERO), "stream-a")
                   .setParallelism(1);
        DataStreamSource<String> streamB =
                env.fromSource(kafkaSourceB, WatermarkStrategy.forBoundedOutOfOrderness(Duration.ZERO), "stream-b")
                    .setParallelism(1);

        streamA.print("steam-a");
        streamB.print("steam-b");

        SingleOutputStreamOperator<String> result =
            streamA
                .keyBy(str -> str.substring(0, 1))
                .intervalJoin(streamB.keyBy(str -> str.substring(0, 1)))
                .between(Time.milliseconds(-100000), Time.milliseconds(100000))
                .process(new ProcessJoinFunction<String, String, String>() {
                    @Override
                    public void processElement(String s1,
                                               String s2,
                                               ProcessJoinFunction<String, String, String>.Context context,
                                               Collector<String> collector) throws Exception {
                        collector.collect("s1: " + s1 + ", s2: " + s2);
                    }
                });

        result.print("result");

        env.execute("interval join");
    }

    private static KafkaSource<String> createKafkaSource(String topic, String groupId) {
        Preconditions.checkNotNull(topic);
        Properties kafkaConsumerProps = new Properties();
        kafkaConsumerProps.setProperty("bootstrap.servers", "localhost:9093");
        kafkaConsumerProps.setProperty("group.id", groupId);

        return KafkaSource.<String>builder()
                .setProperties(kafkaConsumerProps)
                .setValueOnlyDeserializer(new SimpleStringSchema())
                .setStartingOffsets(OffsetsInitializer.latest())
                .setTopics(topic)
                .build();
    }


}

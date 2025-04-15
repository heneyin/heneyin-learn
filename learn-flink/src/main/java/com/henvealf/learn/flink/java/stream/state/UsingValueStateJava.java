package com.henvealf.learn.flink.java.stream.state;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.shaded.guava30.com.google.common.collect.Lists;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author hongliang.yin/Heneyin
 * @date 2024/8/21
 */
public class UsingValueStateJava {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env
            .fromCollection(Lists.newArrayList(
                        Tuple2.of(1L, 2L),
                        Tuple2.of(1L, 3L),
                        Tuple2.of(1L, 4L),
                        Tuple2.of(1L, 5L),
                        Tuple2.of(2L, 1L),
                        Tuple2.of(2L, 2L),
                        Tuple2.of(2L, 4L)
            )).keyBy((KeySelector<Tuple2<Long, Long>, Long>) value -> value.f0)
            .map(new AddValueFunc())
            .print();
        env.execute("UsingValueStateOnJava");
    }

}

package com.henvealf.learn.flink.java.stream.state;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;

public class AddValueFunc extends RichMapFunction<Tuple2<Long, Long>, Tuple2<Long, Long>> {

    private transient ValueState<Long> sum;

    @Override
    public Tuple2<Long, Long> map(Tuple2<Long, Long> in) throws Exception {
        if (sum.value() == null) {
            sum.update(1L);
        } else {
            sum.update(sum.value() + 1);
        }
        return new Tuple2<>(in.f0, sum.value());
    }

    @Override
    public void open(Configuration parameters) {
        ValueStateDescriptor<Long> descriptor =
                new ValueStateDescriptor<>(
                        "sum", // the state name
                        TypeInformation.of(Long.class) // type information
                );
        sum = getRuntimeContext().getState(descriptor);
    }
}
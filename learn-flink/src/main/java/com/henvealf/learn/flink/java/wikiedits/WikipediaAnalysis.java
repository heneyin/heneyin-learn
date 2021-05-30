package com.henvealf.learn.flink.java.wikiedits;


import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.wikiedits.WikipediaEditEvent;
import org.apache.flink.streaming.connectors.wikiedits.WikipediaEditsSource;

/**
 * https://ci.apache.org/projects/flink/flink-docs-release-1.9/getting-started/tutorials/datastream_api.html
 * 官方提供的流例子。
 *
 * @author hongliang.yin/Henvealf
 * @date 2019-08-31l
 */
public class WikipediaAnalysis {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<WikipediaEditEvent> wikipediaEditEvents = env.addSource(new WikipediaEditsSource());
        KeyedStream<WikipediaEditEvent, String> wikieditByUser =
                wikipediaEditEvents.keyBy(WikipediaEditEvent::getUser);

        DataStream<Tuple2<String, Integer>> result = wikieditByUser.timeWindow(Time.seconds(5))
                .aggregate(new AggregateFunction<WikipediaEditEvent, Tuple2<String, Integer>, Tuple2<String, Integer>>() {
                    @Override
                    public Tuple2<String, Integer> createAccumulator() {
                        return new Tuple2<>("", 0);
                    }

                    @Override
                    public Tuple2<String, Integer> add(WikipediaEditEvent wikipediaEditEvent, Tuple2<String, Integer> acc) {
                        acc.f0 = wikipediaEditEvent.getUser();
                        acc.f1 += wikipediaEditEvent.getByteDiff();
                        return acc;
                    }

                    @Override
                    public Tuple2<String, Integer> getResult(Tuple2<String, Integer> acc) {
                        return acc;
                    }

                    @Override
                    public Tuple2<String, Integer> merge(Tuple2<String, Integer> acc1, Tuple2<String, Integer> acc2) {
                        return new Tuple2<>(acc1.f0, acc1.f1 + acc2.f1);
                    }
                });
        result.print();
        env.execute();
    }
}

package com.henvealf.learn.java8.six;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.*;

/**
 * 自定义的 collector
 * 第一个为要收集的项目的类型
 * 第二个为过程中累积器返回的类型
 * 第三个是整个collector规约后返回的类型
 * Created by hongliang.yin/Henvealf on 2017/11/30.
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    // 空流时候的返回
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    // 累积器
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    // 合并两个容器
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    // 最终的转换操作
    @Override
    public Function<List<T>, List<T>> finisher() {
        // 返回输入
        return Function.identity();
    }

    // 定义该 collection 的行为
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH,CONCURRENT));
    }
}

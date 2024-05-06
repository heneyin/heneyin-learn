package com.henvealf.learn.java.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

/**
 * 基础用法
 * 创建生产者并进行生产。
 */
public class LongEventMain {
    public static void main(String[] args) throws InterruptedException {
        int bufferSize = 1024;

        // 设置事件预分配工厂、bufferSize 与线程 Factory
        Disruptor<LongEvent> disruptor = new Disruptor<>(new LongEventFactory(),
                bufferSize,
                DaemonThreadFactory.INSTANCE);
        // ProducerType.SINGLE, new BlockingWaitStrategy()

        // 绑定处理器
        disruptor.handleEventsWith(new LogEventHandler());

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        for (long l = 0; true; l++)
        {
            // 生产
            ringBuffer.publishEvent((event, sequence) -> event.setValue(1));
            Thread.sleep(1000);
        }

        // 也可以额外添加参数作为数据源。
//        ByteBuffer bb = ByteBuffer.allocate(8);
//        for (long l = 0; true; l++)
//        {
//            bb.putLong(0, l);
//            ringBuffer.publishEvent((event, sequence, buffer) -> event.setValue(buffer.getLong(0)), bb);
//            Thread.sleep(1000);
//        }
    }

}

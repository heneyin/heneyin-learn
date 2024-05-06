package com.henvealf.learn.java.disruptor;


import com.lmax.disruptor.EventHandler;

/**
 * 一个消费者，用于处理队列中的数据
 * <p>
 * 这里简单输出消费到的事件
 */
public class LogEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("Event: " + event);
    }
}

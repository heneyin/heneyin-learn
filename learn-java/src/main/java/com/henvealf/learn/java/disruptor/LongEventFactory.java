package com.henvealf.learn.java.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * 用于创建用于预分配的对象，需要实现 EventFactory
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}

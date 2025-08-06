package com.heneyin.learn.jpms.greetings.internal;

import com.heneyin.learn.jpms.greetings.GreetingService;

/**
 * 英语问候服务实现
 *
 * 注意: 这个类在internal包中，模块外部无法直接访问
 * 只能通过服务提供者机制或模块导出的工厂类获取实例
 *
 * @author hongliang.yin/Heneyin
 * @date 2025/8/5
 */
public class EnglishGreetingImpl implements GreetingService {

    @Override
    public String greet(String name) {
        return "Hello, " + name + "!";
    }

    @Override
    public String getProviderName() {
        return "English Greeting Service";
    }

}
package com.henvealf.learn.spring.amqp;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.hook.ShutdownHookBase;
import org.slf4j.LoggerFactory;

/**
 * @author hongliang.yin/Henvealf
 * @date 2019-08-23
 */
public class DefaultShutdownHook extends ShutdownHookBase {
    @Override
    public void run() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.stop();
    }
}

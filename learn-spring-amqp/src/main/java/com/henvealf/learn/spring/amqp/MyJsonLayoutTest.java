package com.henvealf.learn.spring.amqp;

import ch.qos.logback.classic.LoggerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hongliang.yin/Henvealf
 * @date 2019-08-23
 */
public class MyJsonLayoutTest {

    private static Logger logger = LoggerFactory.getLogger(MyJsonLayoutTest.class);

    public static void main(String[] args) {
         logger.info("123ASDDDDDD");
        try {
            try {
                Integer.parseInt("asfd");
            } catch (Exception e) {
                throw new RuntimeException("error when parse int ", e);
            }
        } catch (Exception e) {
            logger.error("error happened when parse int out", e);
        }
        System.out.println("-----");
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.stop();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("addShutdownHook");
            }
        });
    }
}

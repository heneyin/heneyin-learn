package com.henvealf.learn.spring.amqp;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeoutException;

public class AmqpDiagonseSender {

    private static Logger logger = LoggerFactory.getLogger(AmqpDiagonseSender.class);

    private ConnectionFactory factory = new ConnectionFactory();

    public AmqpDiagonseSender() {

        String rabbitMqHost =  "localhost";
        int rabbitMqPort = 5672;
        String rawUser =  "guest";
        String rawPassword = "guest";

        factory.setHost(rabbitMqHost);
        factory.setPort(rabbitMqPort);
        factory.setUsername(rawUser);
        factory.setPassword(rawPassword);
    }

    public void send(String type, String level, String desc, String detail, long time) {

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            JSONObject message = new JSONObject();

            message.put("logType", type);
            message.put("logLevel", level);
            message.put("logDesc", desc);
            message.put("logDetail", detail);
            message.put("logTime", time);

            channel.basicPublish("teste", "test", null, message.toJSONString().getBytes(Charset.forName("utf-8")));
            channel.basicPublish("teste", "test", null, message.toJSONString().getBytes(Charset.forName("utf-8")));

        } catch (TimeoutException | IOException e) {
            logger.error("Send diagonse to amqp error", e);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AmqpDiagonseSender sender = new AmqpDiagonseSender();
        sender.send("11","22", "wqerw", "wrewq", 1231231);
    }
}

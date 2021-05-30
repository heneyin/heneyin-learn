package com.henvealf.learn.spring.amqp;

import com.rabbitmq.client.*;
import org.springframework.amqp.core.ExchangeTypes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Recv  {

    //队列名称  
    public static void main(String[] argv) throws java.io.IOException,
            java.lang.InterruptedException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");


        //给客户端的connetction命名
        Connection connection = connectionFactory.newConnection();

        //给channel起个编号
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("teste", ExchangeTypes.TOPIC);
        channel.queueDeclareNoWait("test1", false, true, true, new HashMap<>());
        channel.queueBind("test1", "teste", "test");
        //返回consumerTag，也可以通过重载方法进行设置consumerTag
        channel.basicConsume("test", false, new DefaultConsumer(channel) {
            @Override
            public void handleConsumeOk(String consumerTag) {
                super.handleConsumeOk(consumerTag);
            }

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println(new String(body));
            }
        });

        TimeUnit.SECONDS.sleep(300000);

        channel.close();
        connection.close();

    }  
} 

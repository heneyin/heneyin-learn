package com.henvealf.learn.spring.amqp;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.Layout;
import com.rabbitmq.client.AlreadyClosedException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.*;

/**
 * @author hongliang.yin/Henvealf
 * @date 2019-08-23
 */
public class AmqpAppender extends AppenderBase<ILoggingEvent> {

    private Connection conn;
    private String host;
    private int port;
    private String username;
    private String password;
    private String queue;
    private String exchangeName = "";
    private String exchangeType;
    private String routingKey;

    private int sendThreadNum = 2;

    private Channel channel;
    private ConnectionFactory factory;

    private BlockingQueue<String> blockingQueue;
    private ExecutorService executorService;

    private Layout<ILoggingEvent> layout;
    public AmqpAppender() {
        blockingQueue = new LinkedBlockingQueue<>();
        this.executorService = Executors.newCachedThreadPool();
    }

    @Override
    public void start() {
        if (sendThreadNum < 1) {
            throw new RuntimeException("sendThreadNum must > 0");
        }
        super.start();
        factory = new ConnectionFactory();
        factory.setUsername(username);
        factory.setPassword(password);
        // factory.setVirtualHost(virtualHost);
        factory.setHost(host);
        factory.setPort(port);
        factory.setAutomaticRecoveryEnabled(true);
        factory.setTopologyRecoveryEnabled(true);
        //factory.setThreadFactory(daemonThreadFactory);
        try {
            conn = factory.newConnection();
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException("Error then create amqp connection", e);
        }
        createChannel();
        for (int i = 0; i < sendThreadNum; i++) {
            this.executorService.submit(new QueueConsumer());
        }
        addInfo("Started AmqpAppender");
    }

    private void createChannel() {
        try {
            this.channel = conn.createChannel();
        } catch (IOException e) {
            throw new RuntimeException("Error then create amqp channel", e);
        }
    }

    @Override
    public void stop() {
        super.stop();
        sendEnd();
        executorService.shutdownNow();
        stopConnection();

        addInfo("Stopped AmqpAppender");
        System.out.println("Stopped AmqpAppender");
    }

    private void stopConnection() {
        if (channel != null) {
            try {
                channel.close();
            } catch (IOException | TimeoutException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Stopped amqp channel");
        if (conn != null) {
            try {
                conn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Stopped amqp conn");
    }

    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        if (!isStarted()) return;
        String message = layout.doLayout(iLoggingEvent);
        blockingQueue.offer(message);
    }

    @Override
    public boolean isStarted() {
        return super.isStarted();
    }

    public void sendEnd() {
        if (!isStarted()) return;
        String message;
        int i = 0;
        while ((message = blockingQueue.poll()) != null) {
            try {
                i ++;
                channel.basicPublish(exchangeName, routingKey, null, message.getBytes("UTF-8"));
            } catch (IOException | AlreadyClosedException e) {
                // no-op
            }
        }
        System.out.println("Send end in AmqpAppender, total " + i);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public Layout<ILoggingEvent> getLayout() {
        return layout;
    }

    public void setLayout(Layout<ILoggingEvent> layout) {
        this.layout = layout;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public int getSendThreadNum() {
        return sendThreadNum;
    }

    public void setSendThreadNum(int sendThreadNum) {
        this.sendThreadNum = sendThreadNum;
    }

    public class QueueConsumer implements Runnable {
        @Override
        public void run() {
            try {
                int recoverNum = 0;
                while (true) {
                    try {
                        String message = blockingQueue.take();
                        channel.basicPublish(exchangeName, routingKey, null, message.getBytes(Charset.defaultCharset()));
                    } catch (IOException e) {
                        if (recoverNum > 3) {
                            stop();
                            throw new RuntimeException("recover channel more than 3 times");
                        }
                        createChannel();
                        recoverNum ++;
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}

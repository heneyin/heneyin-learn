package com.henvealf.learn.java.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * 3.
 * 多路复用，使用 java Nio 实现的多端口监听Echo服务。
 * 用到的有 channel 与 buffer
 * @author hongliang.yin/Henvealf on 2018/8/2
 */
public class MultiPortEcho {

    private int[] ports;
    private ByteBuffer echoBuffer = ByteBuffer.allocate(1024);
    private String outFileName;

    public MultiPortEcho(int[] ports, String outFileName) {
        this.ports = ports;
        this.outFileName = outFileName;
    }

    public void go() throws IOException {
        // selector 会监听各种感兴趣的IO事件。
        Selector selector = Selector.open();
        System.out.println("---open selector-----");
        FileOutputStream fo = new FileOutputStream(new File(outFileName));
        // get channel
        FileChannel outChannle = fo.getChannel();
        System.out.println("---get out channel-----");

        for (int i = 0; i < ports.length; i ++) {
            ServerSocketChannel ssChannel = ServerSocketChannel.open();
            // 如果不设置成异步模式，channel 会在调用相关方法的时候一直等待，阻止其他channel。
            ssChannel.configureBlocking(false);

            // 设置 ServerSocketChannel 监听的端口
            ServerSocket serverSocket = ssChannel.socket();
            InetSocketAddress address = new InetSocketAddress(ports[i]);
            serverSocket.bind(address);

            SelectionKey key = ssChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Going to listen on " + ports[i]);
        }
        System.out.println("---set channel on selector----");
        while (true) {
            // 阻塞到有新的事件从channel中发生， num 为发生的事件的数量
            int num = selector.select();
            System.out.println("---select event from selector----");
            // 事件的集合
            Set<SelectionKey> selectKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectKeys.iterator();

            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();

                if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                    // 接受了一个新连接,作为一个 channel, 把接受到的连接注册到 selector 中。去接受数据。
                    ServerSocketChannel acceptChannel = (ServerSocketChannel) key.channel();
                    SocketChannel newSocketChannel = acceptChannel.accept();
                    newSocketChannel.configureBlocking(false);
                    newSocketChannel.register(selector, SelectionKey.OP_READ);
                    // 归还给 selector;
                    keyIterator.remove();
                    System.out.println("Got connection from " + acceptChannel);
                }else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                    // Read the data
                    SocketChannel sc = (SocketChannel) key.channel();
                    // Echo data
                    int bytesEchoed = 0;
                    while (true) {
                        echoBuffer.clear();
                        int r = sc.read(echoBuffer);
                        if (r <= 0) {
                            break;
                        }
                        echoBuffer.flip();
                        outChannle.write(echoBuffer);
                        bytesEchoed += r;
                    }
                    System.out.println("Echoed " + bytesEchoed + " from " + sc + " to file " + outFileName);
                    keyIterator.remove();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // win10下同时访问两个端口，idea 会很卡
        int[] ports = {9095, 9096};
        MultiPortEcho multiPortEcho = new MultiPortEcho(ports, "learn-java" + File.separatorChar + "nio-multiEcho-out.txt" );
        multiPortEcho.go();
    }

}

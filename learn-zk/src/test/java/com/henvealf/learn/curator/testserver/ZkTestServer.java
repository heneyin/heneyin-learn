package com.henvealf.learn.curator.testserver;

import org.apache.curator.test.TestingServer;
import org.junit.Test;

import java.io.File;

/**
 * @author hongliang.yin/Henvealf on 2018/12/5
 */
public class ZkTestServer {


    @Test
    public void startZkServer() throws Exception {

        TestingServer testingServer = new TestingServer(2181, new File("testZkServerDir/"));


//        testingServer.start();

    }

    @Test
    public void stopZkServer() throws Exception {

        TestingServer testingServer = new TestingServer(2181, new File("testZkServerDir/"));

        testingServer.stop();

    }
}

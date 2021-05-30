//package com.henvealf.learn.java.jmx;
//
//import com.sun.jdmk.comm.HtmlAdaptorServer;
//
//import javax.management.*;
//import javax.management.remote.JMXConnectorServer;
//import javax.management.remote.JMXConnectorServerFactory;
//import javax.management.remote.JMXServiceURL;
//import java.io.IOException;
//import java.lang.management.ManagementFactory;
//import java.rmi.registry.LocateRegistry;
//import java.rmi.registry.Registry;
//import java.util.Hashtable;
//
///**
// * @author hongliang.yin/Henvealf on 2018/9/27
// */
//public class HelloJmxServer {
//    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, IOException {
//        // 下面这种方式不能在JConsole中使用
////         MBeanServer mBeanServer = MBeanServerFactory.createMBeanServer();
//        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
//        String domainName = "MyBean";
//
//        ObjectName helloName =  new ObjectName(domainName,"name", "helloWorld");
//        mBeanServer.registerMBean(new HelloJmx(), helloName);
//
//        Hashtable<String, String> table = new Hashtable<>();
//        table.put("name", "htmlAdaptor");
//        table.put("port", "8083");
//        ObjectName adapterName = new ObjectName(domainName, table);
//        HtmlAdaptorServer adaptorServer = new HtmlAdaptorServer();
//        adaptorServer.start();
//
//        mBeanServer.registerMBean(adaptorServer, adapterName);
//
//        int rmiPort = 1099;
//        Registry registry = LocateRegistry.createRegistry(rmiPort);
//
//        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:"+rmiPort+"/"+domainName);
//        JMXConnectorServer jmxConnector = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mBeanServer);
//        jmxConnector.start();
//
//    }
//}

package com.henvealf.learn.commonpool2.test;

import com.henvealf.learn.commonpool2.ReaderUtil;
import com.henvealf.learn.commonpool2.ReaderUtilUsePool;
import com.henvealf.learn.commonpool2.StringBufferFactoryUsePool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * @author hongliang.yin/Henvealf on 2018/9/27
 */
public class ReaderUtilSuit {

    @Test
    public void testBaseReaderUtil() throws IOException {
        Reader reader = new FileReader("pom.xml");
        System.out.println(new ReaderUtil().readToString(reader));
    }

    @Test
    public void testUsePoolReaderUtil() throws IOException {
        ReaderUtilUsePool readerUtilUsePool = new ReaderUtilUsePool(new GenericObjectPool<StringBuffer>(new StringBufferFactoryUsePool()));
        Reader reader = new FileReader("pom.xml");
        System.out.println(readerUtilUsePool.readToString(reader));
    }
}

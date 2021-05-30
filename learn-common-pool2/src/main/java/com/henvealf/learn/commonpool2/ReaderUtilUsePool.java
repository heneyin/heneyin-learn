package com.henvealf.learn.commonpool2;

import org.apache.commons.pool2.ObjectPool;

import java.io.IOException;
import java.io.Reader;

/**
 * 在 {@link ReaderUtil} 基础之上，为其中的 {@link StringBuffer} 创建池。
 * 当然这只是一个简单的例子，在实际应用中不会缓存 {@link StringBuffer}
 * @author hongliang.yin/Henvealf on 2018/9/27
 */
public class ReaderUtilUsePool {
    private ObjectPool<StringBuffer> pool;

    public ReaderUtilUsePool(ObjectPool<StringBuffer> pool) {
        this.pool = pool;
    }

    public String readToString(Reader in) throws IOException {
        StringBuffer buf = null;
        try {
            buf = pool.borrowObject();
            for (int c = in.read(); c != -1; c = in.read()) {
                buf.append((char) c);
            }
            return buf.toString();
        } catch (IOException e ) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Unable to borrow buffer from pool" + e.toString());
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                // no op
            }

            try {
                if (null != buf) {
                    pool.returnObject(buf);
                }
            } catch (Exception e) {
                // no op
            }
        }
    }


}

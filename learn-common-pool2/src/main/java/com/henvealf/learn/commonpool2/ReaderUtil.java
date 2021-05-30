package com.henvealf.learn.commonpool2;

import java.io.IOException;
import java.io.Reader;

/**
 * the base use case that  without use ObjectPool
 * @author hongliang.yin/Henvealf on 2018/9/27
 */
public class ReaderUtil {

    /**
     * Dumps the contents of the {@link Reader} to a
     * String, closing the {@link Reader} when done.
     */
    public String readToString(Reader in) throws IOException {
        StringBuffer buf = new StringBuffer();
        try {
            for (int c = in.read(); c != -1; c = in.read()) {
                buf.append((char) c);
            }
            return buf.toString();
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                // no op
            }
        }
    }
}

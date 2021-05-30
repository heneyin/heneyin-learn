package com.henvealf.learn.commonpool2;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * An object pool of StringBuffer
 * @author hongliang.yin/Henvealf on 2018/9/27
 */
public class StringBufferFactoryUsePool extends BasePooledObjectFactory<StringBuffer> {

    /**
     * Use the default PooledObject implementation.
     */
    @Override
    public StringBuffer create() throws Exception {
        return new StringBuffer();
    }

    /**
     * Use the default PooledObject implementation.
     */
    @Override
    public PooledObject<StringBuffer> wrap(StringBuffer stringBuffer) {
        return new DefaultPooledObject<StringBuffer>(stringBuffer);
    }

    /**
     * When an object is returned to the pool, clear the buffer.
     */
    @Override
    public void passivateObject(PooledObject<StringBuffer> p) throws Exception {
        p.getObject().setLength(0);
    }

    // for all other methods, the no-op implementation
    // in BasePooledObjectFactory will suffice
}

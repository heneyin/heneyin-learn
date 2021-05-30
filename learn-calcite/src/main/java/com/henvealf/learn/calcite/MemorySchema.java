package com.henvealf.learn.calcite;

import org.apache.calcite.schema.Table;
import org.apache.calcite.schema.impl.AbstractSchema;

import java.util.HashMap;
import java.util.Map;

/**
 * schema 代表一个数据库
 * @author hongliang.yin/Henvealf
 * @date 2021/5/8
 */
public class MemorySchema extends AbstractSchema {

    private String dbName;

    public MemorySchema(String dbName) {
        this.dbName = dbName;
    }

    /**
     * 获取 table
     */
    @Override
    protected Map<String, Table> getTableMap() {
        Map<String, Table> tableMap = new HashMap<String, Table>();
        MemoryData.Database database = MemoryData.MAP.get(this.dbName);
    }
}

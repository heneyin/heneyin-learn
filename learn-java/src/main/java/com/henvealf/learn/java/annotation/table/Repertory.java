package com.henvealf.learn.java.annotation.table;


import com.henvealf.learn.java.annotation.tablean.IntegerCol;
import com.henvealf.learn.java.annotation.tablean.StringCol;
import com.henvealf.learn.java.annotation.tablean.Table;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by hongliang.yin/Henvealf on 2017/7/22.
 */

public class Repertory {

    private Class tableClazz;
    private String tableName;
    // 表名与类中字段的映射。
    private Map<String, Field> colAnnMap = null;


    public Repertory(Class tableClazz) {
        this.tableClazz = tableClazz;
        Table table = (Table) tableClazz.getAnnotation(Table.class);

        if ( table == null ) {
            System.out.println("Dont find define table on class " + tableClazz.getName());
            System.exit(1);
        }
        colAnnMap = new TreeMap<>();

        // 获取表名
        tableName = table.value();
        if (tableName.equals("")){
            tableName = tableClazz.getSimpleName().toLowerCase();
        }

        Field[] fields = tableClazz.getFields();
        for(Field f : fields) {
            Annotation[] annotations = f.getAnnotations();
            if (annotations.length < 1){
                 continue;
            }

            Annotation colAnn = annotations[0];

            colAnn.getClass().getName();
            String colName = null;
            if(colAnn instanceof StringCol) {
                colName = ((StringCol) colAnn).value();
            } else if (colAnn instanceof IntegerCol) {
                colName = ((IntegerCol) colAnn).value();
            }

            if (colName.equals("")){
                colName = f.getName();
            }
            colAnnMap.put(colName, f);
        }
    }
    public List selectAll() throws IllegalAccessException, InstantiationException {

        TableReader reader = new TableReader(tableName);
        boolean isHead = true;
        List<String> heads = null;              // 列名
        List resultList = new ArrayList();

        while (reader.hasNext()) {

            String[] inTable = reader.next();

            if(inTable.length != colAnnMap.size()){
                throw new RuntimeException("table length is not equal");
            }

            if (isHead) {
                heads = Arrays.asList(inTable);
                isHead = false;
                continue;
            }

            Object o = tableClazz.newInstance();
            for(int i = 0; i < heads.size(); i ++ ) {

                String colName = heads.get(i);
                Field field = colAnnMap.get(colName);

                if(field == null) {
                    throw new RuntimeException("Can't find col [" + colName + "] in AnnTable " + tableName + "!");
                }

                String colValue = inTable[i];
                Annotation colAnn = field.getAnnotations()[0];

                if(colAnn instanceof StringCol) {
                    field.set(o, colValue);
                } else if (colAnn instanceof IntegerCol){
                    field.setInt(o, Integer.parseInt(colValue));
                }
            }
            resultList.add(o);
        }
        return resultList;
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        Repertory repertory = new Repertory(User.class);

        List userList = repertory.selectAll();

        System.out.println(userList);
    }

}

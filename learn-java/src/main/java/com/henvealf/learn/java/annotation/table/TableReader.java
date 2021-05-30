package com.henvealf.learn.java.annotation.table;


import java.io.*;
import java.util.Iterator;

/**
 * Created by hongliang.yin/Henvealf on 2017/7/22.
 */

public class TableReader implements Iterator<String[]>{

    BufferedReader reader = null;
    public TableReader(String tableName) {
        try{
            reader = new BufferedReader(new FileReader(tableName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasNext() {
        try {
            return reader.ready();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                reader.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public String[] next() {
        try {
            String line = reader.readLine();
            return  line.split(" ");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void remove() {
    }


}

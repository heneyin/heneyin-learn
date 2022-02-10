package com.henvealf.learn.java.jprofiler;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author hongliang.yin/Heneyin
 * @date 2021/9/25
 */
public class Processor {

    private static final Map<Integer, String> ID_TO_NAME = Maps.newHashMap();
    private static final String FIELD_NAME = "name";
    private static final String FIELD_P_TIME = "p_time";
    public static final String FIELD_ID = "id";

    static {
        ID_TO_NAME.put(0, "于忠肃");
        ID_TO_NAME.put(1, "罗辑");
        ID_TO_NAME.put(2, "张居正");
        ID_TO_NAME.put(3, "程心");
        ID_TO_NAME.put(4, "丁一");
        ID_TO_NAME.put(5, "云天明");
        ID_TO_NAME.put(6, "章北海");
        ID_TO_NAME.put(7, "叶文洁");
        ID_TO_NAME.put(8, "徐阶");
        ID_TO_NAME.put(9, "史强");
    }

    public Processor() {
        new Thread(new PrintTimeRunnable(), "fillArrayThread").start();
    }

    public String process(String content) {
        JSONObject jsonObject = deserializeToJson(content);
        calProcessTime(jsonObject);
        enrichNameSlow(jsonObject, FIELD_ID);
        return serializeToString(jsonObject);
    }

    private JSONObject deserializeToJson(String content) {
        return JSONObject.parseObject(content);
    }

    private String serializeToString(JSONObject jsonObject) {
        return jsonObject.toJSONString();
    }

    private void calProcessTime(JSONObject json) {
        json.put(FIELD_P_TIME, System.currentTimeMillis());
    }

    /**
     * 使用 map 的 get 方法获取名称。
     */
    private void enrichNameFast(JSONObject json, String idField) {
        Integer id = json.getInteger(idField);
        String name = ID_TO_NAME.get(id);
        if (name != null) {
            json.put(FIELD_NAME, name);
        }
    }

    /**
     * 使用遍历的方式获取名称。
     */
    private void enrichNameSlow(JSONObject json, String idField) {
        Integer idInData = json.getInteger(idField);
        if (idInData == null) {
            return;
        }
        for (Map.Entry<Integer, String> idToNameEntry : ID_TO_NAME.entrySet()) {
            Integer id = idToNameEntry.getKey();
            String name = idToNameEntry.getValue();
            if (idInData.equals(id)) {
                json.put(FIELD_NAME, name);
            }
        }
    }

    private static class PrintTimeRunnable implements Runnable {

        // List<String> dates = Lists.newArrayList();
        @Override
        public void run() {
//            while (!Thread.interrupted()) {
//                dates.add("Now Time: " + new Date());
//                if (dates.size() % 1000000 == 0) {
//                    System.out.println("clear thread data");
//                    dates.clear();
//                }
//            }
        }
    }
}

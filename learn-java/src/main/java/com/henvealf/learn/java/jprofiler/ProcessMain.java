package com.henvealf.learn.java.jprofiler;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author hongliang.yin/Heneyin
 * @date 2021/9/25
 */
public class ProcessMain {

    public static void main(String[] args) throws InterruptedException {
        Processor processor = new Processor();

        List<String> datas = makeData();
        Random r = new Random();
        while (true) {

            int i = r.nextInt(datas.size());
            String jsonStr = datas.get(i);

            String processResult = processor.process(jsonStr);
            System.out.println("result: " + processResult);
            Thread.sleep(500);
        }
    }

    public static List<String> makeData() {
        return IntStream.range(0, 10).boxed().map(id -> {
            JSONObject json = new JSONObject();
            json.put(Processor.FIELD_ID, id);
            return json.toJSONString();
        }).collect(Collectors.toList());
    }

}

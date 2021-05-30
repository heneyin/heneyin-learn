package com.henvealf.learn.java;

import com.alibaba.fastjson.JSONObject;
import com.henvealf.learn.java8.TimeWatch;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 测试普通 writer 与 nio 写入的性能差别。
 * @author hongliang.yin/Henvealf
 * @date 2019-12-13
 */
public class WriterAndNioTest {

    @Test
    public void testWriter() throws IOException {
        File tmpFile = File.createTempFile("java-test" + UUID.randomUUID().toString(), null);
        String data = generateData(30000);
        TimeWatch timeWatch = new TimeWatch();
        try(Writer writer = new FileWriter(tmpFile)) {
            writer.write(data);
        }
        System.out.println("Writer total use " + timeWatch.toString());
        System.out.println("File size: " + tmpFile.getTotalSpace() / 1024 / 1024 / 1024);
        System.out.println("File path: " + tmpFile.getAbsolutePath());
        //tmpFile.deleteOnExit();
    }

    private String generateData(int num) {
        JSONObject jsonObject = new JSONObject();

        List<String> data = new ArrayList<>();
        for (int i = 0; i < num; i ++) {
            data.add("12345678901234");
        }
        jsonObject.put("data", data);
        return jsonObject.toJSONString();
    }

}

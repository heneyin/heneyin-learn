package com.henvealf.learn.java.fastjson;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @author hongliang.yin/Heneyin
 * @date 2024/11/18
 */
public class FastjsonParserTest {

    @Test
    public void parseArray() {
        // 将对象解析为数组， 会报错
        JSONArray jsonArray = JSONObject.parseArray("{}");
    }

}

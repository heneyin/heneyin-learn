package com.henvealf.learn.java.jackson;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * https://github.com/FasterXML/jackson-databind/
 * @author hongliang.yin/Henvealf
 * @date 2021/3/28
 */
public class JacksonTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void poloAndJson() throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();

        User user = new User();
        user.setName("hello");
        user.setAge(123);

        String jsonString = objectMapper.writeValueAsString(user);
        System.out.println("json: " + jsonString);

        User user1 = objectMapper.readValue(jsonString, User.class);
        System.out.println("user name: " + user1.getName());
        System.out.println("user age: " + user1.getAge());
    }

    /**
     * 获取 json 值
     * @throws JsonProcessingException
     */
    @Test
    public void jsonNode() throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "{\"name\":\"hello\",\"age\":123}";
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        JsonNode jsonNodeName = jsonNode.get("name");
        String s = jsonNodeName.asText();
        System.out.println("s: " + s);
    }

    @Test
    public void testNest() throws JsonProcessingException {
        String jsonString = "{\"name\":\"hello\",\"age\":123, \"hello\": {\"a\": \"b\"}}";
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        JsonNode helloNode = jsonNode.get("hello");
        String a = helloNode.get("a").asText();
        System.out.println(a);
    }

    @Test
    public void jsonListFromJsonString() throws JsonProcessingException {
        String jsonString = "[{\"name\":\"hello\",\"age\":123},{\"name\":\"hello\",\"age\":123}]";
//        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users = objectMapper.readValue(jsonString, new TypeReference<List<User>>() {});
        System.out.println("length: " + users.size());
    }

    @Test
    public void mapFromJsonString() throws JsonProcessingException {
        String jsonString = "{\"name\":\"hello\",\"age\":123}";
//        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {});
        System.out.println("name: " + map.get("name"));
        System.out.println("age:  " + map.get("age"));
    }

    @Test
    public void testFeature() throws JsonProcessingException {
        String jsonString = "{\"name\":\"hello\",\"age\":123, \"other\": \"123\"}";
//        ObjectMapper objectMapper = new ObjectMapper();
        // 发现不存在的字段，不会报错
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.readValue(jsonString, User.class);

    }

    /**
     * 测试自定的序列化器。
     */
    @Test
    public void customSerializer() throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();

        SimpleModule simpleModule = new SimpleModule("CustomUserSerializer", new Version(1, 0, 0, null, null, null));
        simpleModule.addSerializer(User.class, new CustomUserSerializer());

        objectMapper.registerModule(simpleModule);
        User user = new User();
        user.setName("hahe");

        String s = objectMapper.writeValueAsString(user);
        System.out.println(s);
    }

    @Test
    public void customDeserializer() {

    }

    @Test
    public void buildMapToFastjson() throws JsonProcessingException {

        String jsonString = "{\"name\":\"hello\",\"age\":12311111111111}";

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {});

        JSONObject fastJson = new JSONObject(map);
        String name = fastJson.getString("name");
        double age = fastJson.getDouble("age");
        System.out.println("name: " + name);
        System.out.println("age: " + age);
    }

    @Test
    public void testScientificNotation() throws JsonProcessingException {
        String jsonString = "{\"name\":\"hello\",\"age\":12311111111111213324242342412421}";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN,
                JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS);

        JsonNode root = objectMapper.readTree(jsonString);
        ((ObjectNode)root).put("time", 12333333333333.11231D);
        String jsonStr = root.toString();
        System.out.println(jsonStr);
    }

    public static class CustomUserSerializer extends StdSerializer<User> {

        protected CustomUserSerializer() {
            this(null);
        }

        protected CustomUserSerializer(Class<User> t) {
            super(t);
        }

        @Override
        public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("lastname", user.getName());
            jsonGenerator.writeEndObject();
        }
    }

}

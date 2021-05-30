package com.henvealf.learn.java.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * enum 学习
 * @author hongliang.yin/Henvealf
 * @date 2019-07-11
 */
public class EnumLearn {

    public enum TYPE {
        BASIC("basic"), BASELING("baseline");

        String value;

        TYPE(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        // 将 value 与 enum 的映射存为 map。
        private static final Map<String, TYPE> lookup = new HashMap<>();

        static {
            for (TYPE env : TYPE.values()) {
                lookup.put(env.getValue(), env);
            }
        }

        // 通过 map 获得字符串对应的 Enum
        public static TYPE getEnum(String value)
        {
            return lookup.get(value);
        }

        // 通过遍历 types 的方式获得。复杂度 N 。
        public static TYPE getEnum2(String value) {
            for(TYPE v : values())
                if(v.getValue().equalsIgnoreCase(value)) return v;
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {

        TYPE type = TYPE.getEnum("basic");
        TYPE type1 = TYPE.valueOf("BASIC");

        System.out.println(type == TYPE.BASIC);
        System.out.println(type1 == TYPE.BASIC);

    }

}

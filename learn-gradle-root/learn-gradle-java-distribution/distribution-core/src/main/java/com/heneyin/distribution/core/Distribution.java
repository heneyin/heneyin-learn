package com.heneyin.distribution.core;

import com.heneyin.distribution.common.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author hongliang.yin/Heneyin
 * @date 2022/4/16
 */
public class Distribution {

    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            System.out.println("args must set");
            System.exit(1);
        }
        System.out.println("args detail: " + Arrays.asList(args));
        System.out.println("args after upper: " +
                Arrays.stream(args).map(org.apache.commons.lang3.StringUtils::upperCase).collect(Collectors.toList()));
    }

}

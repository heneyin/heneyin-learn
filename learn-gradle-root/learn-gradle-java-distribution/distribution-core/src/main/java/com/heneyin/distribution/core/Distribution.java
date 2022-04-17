package com.heneyin.distribution.core;

import com.heneyin.distribution.common.StringUtils;

/**
 * @author hongliang.yin/Heneyin
 * @date 2022/4/16
 */
public class Distribution {

    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            System.out.println("args must set");
            return;
        }
        if (StringUtils.isEmpty(args[0])) {
            System.out.println("arg be empty");
        } else {
            System.out.println("arg be not empty");
        }
    }

}

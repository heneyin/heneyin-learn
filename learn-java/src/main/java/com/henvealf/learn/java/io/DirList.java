package com.henvealf.learn.java.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * 匹配正则表达式来显示一个目录中的文件
 * Created by Henvealf on 2016/8/30.
 */
public class DirList {
    public static void main(String[] args){
        File path = new File(".");
        String[] list;
        if(args.length == 0)
            list = path.list();
        else
            list = path.list(new DirFilter(args[0]));
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for ( String dirItem: list ) {
            System.out.println(dirItem);
        }
    }
}

class DirFilter implements FilenameFilter {

    private Pattern pattern;

    public DirFilter(String regex) {
        pattern = Pattern.compile(regex);   //编译字符型的正则表达式为Pattern;
    }
    @Override
    public boolean accept(File dir, String name) {
        //使用pattern生成一个对name进行匹配的匹配器，匹配器的matches方法会判断是否匹配成功，匹配了返回true;
        return pattern.matcher(name).matches();
    }
}

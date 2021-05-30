package com.henvealf.learn.java.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 一个目录实用工具
 * Created by Henvealf on 2016/8/31.
 */
public final class Directory {
    /**
     * 获取本目录下所有文件个目录的名字
     * @param dir
     * @param regex
     * @return
     */
    public  static File[] local(File dir , final String regex) {
        return dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);
            @Override
            public boolean accept(File dir, String name) {
                //return pattern.matcher(name).matches();
                //不明白为什么还要加个new File(),然后再getName()
                return pattern.matcher(new File(name).getName()).matches();
            }
        });
    }

    public static File[] local(String path, final String regex) {
        return local(new File(path),regex);
    }

    /**
     * 目录树信息记录器
     */
    public static class TreeInfo implements Iterable<File> {
        public List<File> files = new ArrayList<>();    //文件
        public List<File> dirs = new ArrayList<>();     //目录

        public Iterator<File> iterator() {
            return files.iterator();
        }

        void addAll(TreeInfo other){
            //将指定集合中的所有的元素追加到本集合之后。
            files.addAll(other.files);
            dirs.addAll(other.dirs);
        }

        public String toString(){
            return "dirs: " + PPrint.pformat(dirs) +
                    "\n\nfiles: " + PPrint.pformat(files);
        }
    }

    public static TreeInfo walk(String start, String regex) {
        return recurseDirs(new File(start),regex);
    }

    public static TreeInfo walk(File start) {
        return recurseDirs(start,".*");
    }
    public static TreeInfo walk(String start) {
        return recurseDirs(new File(start),".*");
    }

    static TreeInfo recurseDirs(File startDir, String regex) {
        TreeInfo result = new TreeInfo();
        for(File items: startDir.listFiles()) {
            if(items.isDirectory()) {
                result.dirs.add(items);
                result.addAll(recurseDirs(items,regex));
                //recurseDirs(items,regex);
            } else {
                if (items.getName().matches(regex))
                    result.files.add(items);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        /*for(int i = 0; i < local(".",".*").length; i ++)
            System.out.println(local(".",".*")[i].getName());*/
        System.out.println(walk("."));
    }
}


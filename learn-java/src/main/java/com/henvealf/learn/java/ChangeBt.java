package com.henvealf.learn.java;

import com.henvealf.learn.java.util.BinaryFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *
 * <p>
 *
 * @author hongliang.yin/Henvealf on 2019-02-10
 */
public class ChangeBt {
    public static void main(String[] args) throws IOException {
        byte[] read = BinaryFile.read("learn-java/ll.torrent");

        System.out.println(new String(read));

        int i = getIndex(read, "49e4");
        List<Byte> byteList = new ArrayList<>();

        addToList(byteList, Arrays.copyOfRange(read, 0, i));

        String name = ":11.mp4";
        String str = "name" + name + name.getBytes().length;
        System.out.println(str);
        addToList(byteList, str.getBytes());

        int k = getIndex(read, ".mp4");
        System.out.println("kkkkkk: " + k);
        addToList(byteList, Arrays.copyOfRange(read, k, read.length));

        // 输出
        byte[] bb = new byte[byteList.size()];
        for (int j = 0; j < byteList.size(); j ++) {
            bb[j] = byteList.get(j);
        }
        System.out.println(new String(bb));

        BinaryFile.writeToFile(bb, "learn-java/lldq.torrent");
//        addToList(byteList, );
    }

    public static void addToList(List<Byte> byteList, byte[] bytes) {
        for (Byte b : bytes) {
            byteList.add(b);
        }
    }

    public static int getIndex(byte[] read, String needIndex) {
        int i ;
        for (i = 0; i < read.length; i ++ ) {
            String str = new String(read, 0 , i);
            if (str.contains(needIndex)) {
                System.out.println("----------:" + i);
                System.out.println(new String(read, 0 , i));
                break;
            }
        }
        return i;
    }

}

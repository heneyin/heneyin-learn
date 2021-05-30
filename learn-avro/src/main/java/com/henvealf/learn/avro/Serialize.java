package com.henvealf.learn.avro;

import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

/**
 * Created by hongliang.yin/Henvealf on 2017/7/2.
 */
public class Serialize {

    public static void serialize() throws IOException {
        User user1 = new User();
        user1.setName("henvealf");
        user1.setFavoriteColor("blue");
        user1.setFavoriteNumber(7);

        User user2 = new User();
        user2.setName("go");
        user2.setFavoriteNumber(22);
        user2.setFavoriteColor("red");


        User user3 = User.newBuilder()
                .setName("three")
                .setFavoriteColor("33")
                .setFavoriteNumber(null)
                .build();

        // 我们创建一个 DatumWriter,将 Java 对象转化为内存中的序列格式。
        // SpecificDatumWriter 使用 之前生成的类并且从提取出指定的模式。
        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);

        // 我们创建了一个 DataFileWriter，将已经序列化的数据，写入调用 dataFileWriter.create 时指定的文件中。
        DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
        dataFileWriter.create(user1.getSchema(), new File(Constants.USER_AVRO_FILE));

        // 最后使用 dataFileWriter.append 将我们的user对象写入到序列化文件中。
        dataFileWriter.append(user1);
        dataFileWriter.append(user2);
        dataFileWriter.append(user3);
        dataFileWriter.close();
    }


    public static void main(String[] args) throws IOException {
        Serialize.serialize();
    }


}

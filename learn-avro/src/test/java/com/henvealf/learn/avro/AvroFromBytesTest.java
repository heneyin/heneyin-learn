package com.henvealf.learn.avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class AvroFromBytesTest {

    @Test
    public void test() throws IOException {

        // 写
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

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BinaryEncoder binaryEncoder = EncoderFactory.get().binaryEncoder(outputStream, null);

        userDatumWriter.write(user1, binaryEncoder);

        binaryEncoder.flush();
//        outputStream.flush();
        byte[] bytes = outputStream.toByteArray();
        System.out.println("bytes length: " + bytes.length);
        outputStream.close();


        // 读取
        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse(new File("/Users/heneyin/projects/my-projects/Learn/learn-avro/src/main/resources/user.json"));

        DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);

//        for (int i = 0; i < 30000; i++) {
//
//        }
        BinaryDecoder binaryDecoder = DecoderFactory.get().binaryDecoder(bytes, null);
        GenericRecord read1 = datumReader.read(null, binaryDecoder);

        System.out.println("read1:" + read1);
    }

}

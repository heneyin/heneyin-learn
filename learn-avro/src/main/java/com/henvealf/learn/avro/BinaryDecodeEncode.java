package com.henvealf.learn.avro;

import org.apache.avro.Schema;
import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * <p>
 *
 * <p>
 *
 * @author hongliang.yin/Henvealf on 2019-06-01
 */
public class BinaryDecodeEncode {

    public static void main(String[] args) throws IOException {
        User userRaw = new User();
        userRaw.setName("342");
        userRaw.setFavoriteNumber(12312);
        userRaw.setFavoriteColor("sdfs");
        BinaryDecodeEncode decodeEncode = new BinaryDecodeEncode();
        byte[] data = decodeEncode.encode(userRaw);
        User decodeUser = decodeEncode.decode(data);
        System.out.println(decodeUser);
    }

    Schema.Parser parser = new Schema.Parser();
    Schema schema;

    public BinaryDecodeEncode() throws IOException {
        schema = parser.parse(new File(Constants.USER_SCHEMA_FILE));
    }

    public User decode(byte[] bytes) throws IOException {

        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(bytes);

        int id = inputStream.read();

        System.out.println(id);

        DatumReader<User> datumReader = new SpecificDatumReader<User>(schema);
        BinaryDecoder decoder =  DecoderFactory.get().directBinaryDecoder(new ByteArrayInputStream(bytes, 1, bytes.length - 1), null);
        return datumReader.read(null, decoder);
    }

    public byte[] encode(User user) throws IOException {
        DatumWriter<User> writer = new SpecificDatumWriter<User>(schema);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int id = 123;
        outputStream.write(id);

        BinaryEncoder encoder = EncoderFactory.get().directBinaryEncoder(outputStream,null);
        writer.write(user, encoder);
        return outputStream.toByteArray();
    }
}

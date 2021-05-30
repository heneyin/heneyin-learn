package com.henvealf.learn.avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.specific.SpecificDatumReader;

import java.io.File;
import java.io.IOException;

/**
 * Created by hongliang.yin/Henvealf on 2017/7/2.
 */
public class Deserialize {
    public static void deserialize(String file) throws IOException {

        // Schema schema = Schema.parse(file);
        // Deserialize users from disk
        DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>();
        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(new File(file), datumReader);
        GenericRecord user = null;

        int count = 0;
        while (dataFileReader.hasNext()) {
            // Reuse user object by passing it to next(). This saves us from
            // allocating and garbage collecting many objects for files with
            // many items.

            user = dataFileReader.next(user);
            System.out.println(count ++ + "  : " +  user);
        }
    }

    public static void main(String[] args){
        try {
            Deserialize.deserialize(Constants.USER_AVRO_FILE);
            //Deserialize.deserialize("users.avro");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

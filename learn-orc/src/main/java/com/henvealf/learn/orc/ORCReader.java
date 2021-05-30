package com.henvealf.learn.orc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.exec.vector.*;
import org.apache.orc.OrcFile;
import org.apache.orc.Reader;
import org.apache.orc.RecordReader;
import org.apache.orc.TypeDescription;

import java.io.IOException;

/**
 * <p>
 * ORC 文件读取器例子。
 * <p>
 *
 * @author hongliang.yin/Henvealf on 2018/12/31
 */
public class ORCReader {

    public static void main(String[] args) throws IOException {

        Reader reader = OrcFile.createReader(new Path("learn-orc/foo.orc"), OrcFile.readerOptions(new Configuration()));

        // 使用 RecordReader 获得数据，使用 VectorizedRowBatch 控制一次读取的数目。
        RecordReader rows = reader.rows();
        VectorizedRowBatch batch = reader.getSchema().createRowBatch();
        TypeDescription schema = reader.getSchema();



        while (rows.nextBatch(batch)) {

            for (int i = 0 ; i < batch.size; ++i) {
                ColumnVector[] ver = batch.cols;
                LongColumnVector longColumnVectors = (LongColumnVector) ver[0];
                System.out.println(longColumnVectors.vector[i] + "");;


                // 读取map
                MapColumnVector mapColumnVector = (MapColumnVector) ver[2];
                BytesColumnVector keys = (BytesColumnVector) mapColumnVector.keys;
                LongColumnVector values = (LongColumnVector) mapColumnVector.values;
                System.out.println("key:" + keys.toString(i) + " value:" + values.vector[i]);

            }
        }
    }
}

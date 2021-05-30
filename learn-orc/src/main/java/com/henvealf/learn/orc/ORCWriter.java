package com.henvealf.learn.orc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.MapColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.orc.OrcFile;
import org.apache.orc.TypeDescription;
import org.apache.orc.Writer;
import org.apache.orc.impl.WriterImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * <p>
 * 生成 orc 文件的例子
 * <p>
 *
 * @author hongliang.yin/Henvealf on 2018/12/31
 */
public class ORCWriter {

    public static void main(String[] args) throws IOException {

        Configuration conf = new Configuration();

        // 设置 schema， 注意这里不能有空格存在。
        TypeDescription schema = TypeDescription.fromString("struct<first:int,second:int,third:map<string,int>>");
        // 添加一列
        schema.addField("forth", TypeDescription.createInt());

        Writer writer = OrcFile.createWriter(new Path("learn-orc/foo.orc"), OrcFile.writerOptions(conf).setSchema(schema));

        VectorizedRowBatch batch = schema.createRowBatch();
        LongColumnVector first = (LongColumnVector) batch.cols[0];
        LongColumnVector second = (LongColumnVector) batch.cols[1];

        MapColumnVector third = (MapColumnVector) batch.cols[2];
        BytesColumnVector mapKey = (BytesColumnVector) third.keys;
        LongColumnVector mapValue = (LongColumnVector) third.values;

        LongColumnVector forth = (LongColumnVector) batch.cols[3];

        // map
        final int MAP_SIZE = 5;
        final int BATCH_SIZE = batch.getMaxSize();
        System.out.println("batch size: " + BATCH_SIZE);

        // 检查map空间是否充足，
        mapKey.ensureSize(BATCH_SIZE * MAP_SIZE, false);
        mapValue.ensureSize(BATCH_SIZE * MAP_SIZE, false);

        for (int r = 0; r< 1500; r++) {
            int row = batch.size ++;

            first.vector[row] = r;
            second.vector[row] = r * 3;

            third.offsets[row] = third.childCount;
            third.lengths[row] = MAP_SIZE;

            // 该列一共的元素数目
            third.childCount += MAP_SIZE;
            System.out.println("childCount: " + third.childCount);

            for (int mapEle = (int) third.offsets[row]; mapEle < third.offsets[row] + MAP_SIZE; mapEle ++) {
                String key = "row" + r +"." + (mapEle - third.offsets[row]);  // rown.keyoffset
                mapKey.setVal(mapEle, key.getBytes(StandardCharsets.UTF_8));
                mapValue.vector[mapEle] = mapEle;
            }

            if (row == BATCH_SIZE - 1) {
                writer.addRowBatch(batch);
                batch.reset();
            }
        }

        if (batch.size != 0) {
            writer.addRowBatch(batch);
            batch.reset();
        }

        writer.close();

    }
}

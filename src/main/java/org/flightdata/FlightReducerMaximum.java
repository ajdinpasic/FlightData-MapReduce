package org.flightdata;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

public  class FlightReducerMaximum extends Reducer<Text, FloatWritable, Text, FloatWritable> {

    public void reduce(Text word, Iterable<FloatWritable> values, Context context) throws IOException, InterruptedException {
        float max = 0;
        int count = 0;
        for (FloatWritable value : values) {
            if(count == 0) {
                max = value.get();
            }
            if(value.get() > max && count != 0) {
                max = value.get();
            }
            count++;
        }
        context.write(word, new FloatWritable(max));
    }
}
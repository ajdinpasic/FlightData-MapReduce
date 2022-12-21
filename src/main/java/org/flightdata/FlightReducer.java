package org.flightdata;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

public  class FlightReducer extends Reducer<Text, FloatWritable, Text, FloatWritable> {

    public void reduce(Text word, Iterable<FloatWritable> values, Context context) throws IOException, InterruptedException {
        float sum = 0;
        float count = 0;
        for (FloatWritable value : values) {
            sum += value.get();
            count = count + 1;
        }
        context.write(word, new FloatWritable(sum));
    }
}
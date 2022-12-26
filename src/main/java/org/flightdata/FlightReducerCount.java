package org.flightdata;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public  class FlightReducerCount extends Reducer<Text, FloatWritable, Text, Text> {

    public void reduce(Text word, Iterable<FloatWritable> values, Context context) throws IOException, InterruptedException {
        String result = "";
        for (FloatWritable value : values) {
            if(value.get() > 406866) {
                result = TressholdStatus.GREAT.getValue();
            }
            else if(value.get() > 204363) {
                result = TressholdStatus.AVERAGE.getValue();
            }
            else {
                result = TressholdStatus.POOR.getValue();
            }
        }
        context.write(word, new Text(result));
    }
}

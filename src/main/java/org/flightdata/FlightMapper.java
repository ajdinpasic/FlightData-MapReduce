package org.flightdata;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;
public class FlightMapper extends Mapper<LongWritable, Text, Text, FloatWritable> {

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] words = line.split(",");
        Text outputKey = new Text((words[3]+",").toUpperCase().trim());
        FloatWritable outputValue = new FloatWritable(Float.parseFloat(words[12]));
        context.write(outputKey, outputValue);
    }
}

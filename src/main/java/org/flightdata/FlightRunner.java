package org.flightdata;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;


public class FlightRunner {
    public static void main(String[] args) throws Exception {
        //set up configurations
        Configuration c = new Configuration();
        String[] files = new GenericOptionsParser(c, args).getRemainingArgs();
        Path input = new Path(files[0]);
        Path output = new Path(files[1]);
        //Comment or uncomment based on the task you wish to perform
        Job j = new Job(c, "Total base pay");
        j.setJarByClass(FlightRunner.class);
        j.setMapperClass(FlightMapper.class);
        j.setReducerClass(FlightReducer.class);
        j.setOutputKeyClass(Text.class);
        j.setOutputValueClass(FloatWritable.class);

        FileInputFormat.addInputPath(j, input);
        FileOutputFormat.setOutputPath(j, output);

        long startTime = System.currentTimeMillis();
        j.waitForCompletion(true);
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Time Elapsed for first job : " + estimatedTime);
/*
        Job j2 = new Job(c,"Count satisfied records");
        j2.setJarByClass(FlightRunner.class);
        j2.setMapperClass(FlightMapperCount.class);
        j2.setReducerClass(FlightReducerCount.class);
        j2.setOutputKeyClass(Text.class);
        j2.setOutputValueClass(FloatWritable.class);

        FileInputFormat.addInputPath(j2, new Path("/ticket-prices"));
        FileOutputFormat.setOutputPath(j2, new Path("/ticket-report"));

        long startTimeJob2 = System.currentTimeMillis();
        j2.waitForCompletion(true);
        long estimatedTimeJob2 = System.currentTimeMillis() - startTimeJob2;
        System.out.println("Time Elapsed for second job : " + estimatedTimeJob2); */

        System.exit(0);
    }
}
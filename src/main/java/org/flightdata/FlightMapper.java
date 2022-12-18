package org.flightdata;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;
public class FlightMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
    private Text startingAiportName = new Text();
    private DoubleWritable basePayForAirport = new DoubleWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String splitRecords[] = value.toString().split(",");
        startingAiportName.set(splitRecords[3]);
        basePayForAirport.set(Double.parseDouble(splitRecords[12]));
        context.write(startingAiportName, basePayForAirport); // <key-value> : <airportName-basePayForThatAiport>
    }
}

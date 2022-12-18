package org.flightdata;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlightReducer<KEY> extends Reducer<KEY,DoubleWritable, KEY, DoubleWritable> {
    private DoubleWritable totalBasePay = new DoubleWritable();

    public void reduce(KEY key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
        double sum = 0;
        for (DoubleWritable item: values) {
            sum += item.get();
        }
        totalBasePay.set(sum);
        context.write(key, totalBasePay);
    }
}

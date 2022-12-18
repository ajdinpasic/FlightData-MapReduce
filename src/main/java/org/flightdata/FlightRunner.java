package org.flightdata;
import org.apache.hadoop.mapred.JobConf;

import java.io.IOException;

public class FlightRunner {
    public static void main(String[] args) throws IOException {
        JobConf conf = new JobConf(FlightRunner.class);
        conf.setJobName("FlightData-MapReduce");
    }
}

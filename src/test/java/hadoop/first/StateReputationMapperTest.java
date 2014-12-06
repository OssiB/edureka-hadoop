package hadoop.meetup.first;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

public class StateReputationMapperTest {
	MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
	@Before
	public void setUp(){
		StateReputationMapper mapper = new StateReputationMapper();
		mapDriver = MapDriver.newMapDriver(mapper);
	}
	@Test
	public void processValidRecord() throws IOException{
		Text value = new Text("\"127320\",\"Cumming, GA\",\"38230\",\"1089\",\"0.0007320565478391\"");
		mapDriver.withInput(new LongWritable(),value);
		mapDriver.withOutput(new Text("GA"),new  IntWritable(38230));
		mapDriver.runTest();
	}
	@Test 
	public void ignoreWrongRecord() throws IOException{
		Text value = new Text("\"2568338\",\"Bangalore, INDIA\",\"1\",\"2241161\",\"1.5065717032246788\"");
		mapDriver.withInput(new LongWritable(),value);
		mapDriver.runTest();
	}
	@Test
	public void ignoreHeader() throws IOException{
		Text value = new Text("Id,Location,Reputation,Ranking,Percentile");
		mapDriver.withInput(new LongWritable(), value);
		mapDriver.runTest();
	}
}

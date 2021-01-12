package storm.test;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.StringTokenizer;

/**
 * @author xucheng.liu
 * @date 2019/4/19
 */
public class SplitBolt extends BaseBasicBolt {

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        String sentence = input.getString(0);
        StringTokenizer stringTokenizer = new StringTokenizer(sentence);
        while(stringTokenizer.hasMoreElements()) {
            collector.emit(new Values(stringTokenizer.nextToken()));
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }
}

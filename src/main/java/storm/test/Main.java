package storm.test;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;

/**
 * @author xucheng.liu
 * @date 2019/6/5
 */
public class Main {

    public static void main(String[] args) {
        TopologyBuilder topologyBuilder = new TopologyBuilder();
        topologyBuilder.setSpout("sentenceSpout", new RandomSentenceSpout(), 1);
        topologyBuilder.setBolt("splitBolt", new SplitBolt(), 1).shuffleGrouping("sentenceSpout");
        topologyBuilder.setBolt("wordCountBolt", new WordCountBolt(), 1).shuffleGrouping("splitBolt");
        StormTopology topology = topologyBuilder.createTopology();

        Config config = new Config();
        config.setNumWorkers(2);

        LocalCluster local = new LocalCluster();
        local.submitTopology("wordCount", config, topology);
    }
}
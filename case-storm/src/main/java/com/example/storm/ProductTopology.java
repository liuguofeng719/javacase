package com.example.storm;

import com.example.storm.bolt.LogParseBolt;
import com.example.storm.bolt.ProductCountBolt;
import com.example.storm.spolt.AccessLogSpolt;

import org.apache.kafka.common.utils.Utils;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/9/11 下午6:04
 * @see jdk 1.7
 **/
public class ProductTopology {
    public static void main(String[] args) {

        TopologyBuilder tb = new TopologyBuilder();

        tb.setSpout("access-log", new AccessLogSpolt(), 1);

        tb.setBolt("logparse", new LogParseBolt(), 5)
                .setNumTasks(5)
                .shuffleGrouping("accessLog");

        tb.setBolt("productCount", new ProductCountBolt(), 5)
                .setNumTasks(10)
                .fieldsGrouping("logparse", new Fields("productId"));

        Config config = new Config();
        if (args != null && args.length > 0) {
            config.setNumWorkers(3);
            try {
                StormSubmitter.submitTopology(args[0],config,tb.createTopology());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology(args[0],config,tb.createTopology());
            Utils.sleep(30000);
            cluster.shutdown();
        }
    }
}

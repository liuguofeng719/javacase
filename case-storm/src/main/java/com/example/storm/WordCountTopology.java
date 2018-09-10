package com.example.storm;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.shade.com.google.common.collect.Maps;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Random;


/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/8/30 下午6:16
 * @see jdk 1.7
 **/
public class WordCountTopology {

    public static class RandomSentenceSpout extends BaseRichSpout {

        private SpoutOutputCollector soc;

        private Random rd;

        public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
            this.soc = spoutOutputCollector;
            this.rd = new Random();
        }

        public void nextTuple() {
            Utils.sleep(100);
            String[] sentences = new String[]{
                    sentence("the cow jumped over the moon"),
                    sentence("an apple a day keeps the doctor away"),
                    sentence("four score and seven years ago"),
                    sentence("snow white and the seven dwarfs"),
                    sentence("i am at two with nature")};
            final String sentence = sentences[rd.nextInt(sentences.length)];
            soc.emit(new Values(sentence));
        }

        protected String sentence(String input) {
            return input;
        }

        public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
            outputFieldsDeclarer.declare(new Fields("sentence"));
        }
    }

    public static class SplitSentence extends BaseRichBolt {

        OutputCollector outputCollector;

        public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
            this.outputCollector = outputCollector;
        }

        public void execute(Tuple tuple) {
            final String sentence = tuple.getStringByField("sentence");
            final String[] split = sentence.split(" ");
            for (final String s : split) {
                outputCollector.emit(tuple,new Values(s));
            }
        }

        public void cleanup() {

        }

        public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
            outputFieldsDeclarer.declare(new Fields("word"));
        }
    }

    public static class WordCount extends BaseRichBolt {
        private static final Logger LOGGER = LoggerFactory.getLogger(WordCount.class);
        Map<String, Long> mapWords = Maps.newConcurrentMap();
        OutputCollector outputCollector;

        public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
            this.outputCollector = outputCollector;
        }

        public void execute(Tuple tuple) {
            final String word = tuple.getStringByField("word");
            Long count = mapWords.get(word);
            if (count == null) {
                count = 0L;
            }
            count++;
            LOGGER.info("【单词计数】" + word + "出现的次数是" + count);
            mapWords.put(word, count);
            outputCollector.emit(tuple,new Values(word, count));
        }

        public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
            outputFieldsDeclarer.declare(new Fields("word","count"));
        }
    }

    public static void main(String[] args) {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("RandomSentenceSpout",new RandomSentenceSpout(),2);
        builder.setBolt("SplitSentence",new SplitSentence(),5)
                .setNumTasks(10)
                .shuffleGrouping("RandomSentenceSpout");
        builder.setBolt("WordCount",new WordCount(),10)
                .setNumTasks(20)
                .fieldsGrouping("SplitSentence",new Fields("word"));

        Config config = new Config();
        // 说明是在命令行执行，打算提交到storm集群上去
        if(args != null && args.length > 0) {
            config.setNumWorkers(3);
            try {
                StormSubmitter.submitTopology(args[0], config, builder.createTopology());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            config.setMaxTaskParallelism(20);

            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("WordCountTopology", config, builder.createTopology());

            Utils.sleep(60000);

            cluster.shutdown();
        }
    }
}

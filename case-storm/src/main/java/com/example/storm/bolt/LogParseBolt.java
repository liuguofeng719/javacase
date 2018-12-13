package com.example.storm.bolt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/9/11 下午5:55
 * @see jdk 1.7
 **/
public class LogParseBolt extends BaseRichBolt {

    OutputCollector outputCollector;

    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector = outputCollector;
    }

    public void execute(Tuple tuple) {
        final String message = tuple.getStringByField("message");
        final JSONObject jsonObject = JSON.parseObject(message);
        final JSONObject uriArgs = jsonObject.getJSONObject("uri_args");
        final Long productId = uriArgs.getLong("productId");
        if (productId != null) {
            outputCollector.emit(tuple, new Values(productId));
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("productId"));
    }
}

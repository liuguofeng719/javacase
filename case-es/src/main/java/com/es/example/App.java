package com.es.example;

import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.Date;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws Exception {

        //集群链接
        Settings settings = Settings.builder()
                .put("cluster.name", "cluster-es-prod")
                .build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("10.211.55.4"), 9300));

        preData(client);
//        addEmployee(client);
//        findEmployee(client);
//        modifyDocument(client);
//        deleteDocument(client);
//        searchDocument(client);
//        searchConditionDocument(client);
//        aggregation(client);

    }

    private static void preData(TransportClient client) {

    }

    private static void aggregation(TransportClient client) {

    }

    //（1）搜索职位中包含technique的员工
    //（2）同时要求age在30到40岁之间
    //（3）分页查询，查找第一页
    private static void searchConditionDocument(TransportClient client){
        final SearchRequestBuilder prepareSearch = client.prepareSearch("company");
        prepareSearch.setTypes("employee");
        prepareSearch.setQuery(QueryBuilders.termQuery("name","tom")); //query
        prepareSearch.setFetchSource(Arrays.asList("name","age").toArray(new String[0]),
                Arrays.asList("address").toArray(new String[0]));//指定那些字段显示
        final SearchResponse response =
                prepareSearch.setPostFilter(QueryBuilders.rangeQuery("age").from(10).to(30))
                .setFrom(0) //页码
                .setSize(3) //每页1条
                .get();
        final SearchHit[] hits = response.getHits().getHits();
        for (final SearchHit hit : hits) {
            final String sourceAsString = hit.getSourceAsString();
            System.out.println(sourceAsString);
        }
    }

    /**
     * 查询文档
     * @param client
     */
    private static void searchDocument(TransportClient client) {

        final SearchRequestBuilder prepareSearch = client.prepareSearch("test_index", "company");
        prepareSearch.setTypes("test_type","employee");
        prepareSearch.setQuery(QueryBuilders.matchQuery("test_field1","test"));
        final SearchResponse searchResponse = prepareSearch.get();
        System.out.println(searchResponse);

    }

    /**
     * @desc 创建document
     * @param client
     * @throws Exception
     */
    private static void addEmployee(TransportClient client) throws Exception {

        final IndexRequestBuilder prepareIndex = client.prepareIndex("case_index", "case_type", "1");

        final IndexResponse indexResponse = prepareIndex.setSource(
                XContentFactory.jsonBuilder()
                .startObject()
                        .field("user","kimcy")
                        .field("postDate",new Date())
                        .field("message","trying out elasticsearch")
                .endObject()).get();

        System.out.println(indexResponse);
    }

    /**
     * 查询document
     * * {"_index":"case_index","_type":"case_type","_id":"1","_version":3,"found":true,
     * "_source":
     *  {"user":"kimcy","postDate":"2018-09-20T03:36:02.361Z","message":"trying out elasticsearch"}
     * }
     * @param client
     */
    private static void findEmployee(TransportClient client){
        final GetResponse response = client.prepareGet("case_index", "case_type", "1").get();
        System.out.println(response);
    }

    /**
     * 修改文档
     * {"_index":"case_index","_type":"case_type","_id":"1","_version":3,"found":true,
     * "_source":
     *  {"user":"kimcy","postDate":"2018-09-20T03:36:02.361Z","message":"trying out elasticsearch","gender":"male"}
     * }
     *
     * @param client
     */
    private static void modifyDocument(TransportClient client) throws Exception{
        final UpdateRequestBuilder builder = client.prepareUpdate("case_index", "case_type", "1");
        final UpdateResponse updateResponse = builder.setDoc(XContentFactory.jsonBuilder()
                .startObject()
                .field("gender", "male")
                .endObject())
                .get();
        System.out.println(updateResponse);
    }

    /**
     * 删除文档
     * DeleteResponse[index=case_index,type=case_type,id=1,version=4,result=deleted,shards=ShardInfo{total=2, successful=1, failures=[]}]
     * status OK
     * @param client
     */
    private static void deleteDocument(TransportClient client){
        final DeleteRequestBuilder prepareDelete = client.prepareDelete("case_index", "case_type", "1");
        final DeleteResponse deleteResponse = prepareDelete.get();
        System.out.println(deleteResponse);
        System.out.println(deleteResponse.status());
    }
}

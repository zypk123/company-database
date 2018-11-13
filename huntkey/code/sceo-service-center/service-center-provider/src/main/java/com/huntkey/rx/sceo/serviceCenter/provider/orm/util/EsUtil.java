package com.huntkey.rx.sceo.serviceCenter.provider.orm.util;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.huntkey.rx.sceo.serviceCenter.common.model.ConditionNode;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest.AliasActions;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.model.Criteria;

/**
 * ES处理的模板类
 *
 * @ClassName:
 * @Description:
 */
@Component
public class EsUtil {

    private static Logger logger = LoggerFactory.getLogger(EsUtil.class);
    /////////////////////////常量/////////////////////////////////////////////////
    public final static String TOTAL = "total";
    public final static String HITS = "hits";
    public final static String LIST = "list";
    /////////////////////////////////////////////////////////////////////////

    /**
     * 构建ES索引
     *
     * @param client
     * @param indexName
     * @return
     */
    public boolean createIndex(Client client, String indexName) {
        return client.admin().indices().create(Requests.createIndexRequest(indexName))
                .actionGet().isAcknowledged();
    }

    /**
     * 构建索引同义词
     *
     * @param client
     * @param index
     * @param alias_index
     * @return
     */
    public boolean createAlias(Client client, String index, String alias_index) throws Exception {
        IndicesAliasesRequest request = new IndicesAliasesRequest();
        request.addAliasAction(AliasActions.add().alias(alias_index).index(index));
        IndicesAliasesResponse response = client.admin().indices().aliases(request).get();
        return response.isAcknowledged();
    }

    /**
     * 删除索引同义词
     *
     * @param client
     * @param index
     * @param alias_index
     * @return
     */
    public boolean removeAlias(Client client, String index, String alias_index) throws Exception {

        IndicesAliasesRequest request = new IndicesAliasesRequest();
        request.addAliasAction(AliasActions.remove().alias(alias_index).index(index));
        IndicesAliasesResponse response = client.admin().indices().aliases(request).get();

        return response.isAcknowledged();
    }

    /**
     * 删除指定的索引
     *
     * @param indexName
     * @return boolean
     */
    public boolean deleteIndex(Client client, String indexName) {
        if (indexExists(client, indexName)) {
            return client.admin().indices().prepareDelete(indexName).execute().actionGet().isAcknowledged();
        }
        return false;
    }

    /**
     * 指定的索引的名称是否存在
     *
     * @param indexName
     * @return boolean
     */
    public boolean indexExists(Client client, String indexName) {
        return client.admin().indices().exists(Requests.indicesExistsRequest(indexName)).actionGet().isExists();
    }

    /**
     * 构建索引  根据索引的名称和设置信息
     *
     * @param indexName
     * @param settings
     * @return boolean
     */
    public boolean createIndex(Client client, String indexName, Object settings) {
        CreateIndexRequestBuilder requestBuilder = client.admin().indices().prepareCreate(indexName);
        if (settings != null) {
            if (settings instanceof String) {
                requestBuilder.setSettings(String.valueOf(settings),XContentType.JSON);
            } else if (settings instanceof Map) {
                requestBuilder.setSettings((Map) settings);
            } else if (settings instanceof XContentBuilder) {
                requestBuilder.setSettings((XContentBuilder) settings);
            }
        }
        return requestBuilder.execute().actionGet().isAcknowledged();
    }

    /**
     * 使用prepareMapping形式进行创建mapping
     *
     * @param indexName
     * @param type
     * @param mappings
     * @return
     * @throws IOException
     */
    public boolean putMapping(Client client, String indexName, String type, Map<String, Map<String, String>> mappings) throws IOException {
        XContentBuilder mappingSource = XContentFactory.jsonBuilder()
                .startObject().startObject(type)
                //.startObject("_all").field("analyzer", "ik_max_word")
                //.field("search_analyzer", "ik_max_word").field("term_vector", "no").endObject()
                //----初始
                .startObject("properties");

        Map<String, String> currentMap = null;
        for (Map.Entry<String, Map<String, String>> entry : mappings.entrySet()) {
            currentMap = entry.getValue();
            mappingSource.startObject(entry.getKey());
            for (Map.Entry<String, String> secondEntry : currentMap.entrySet()) {
                mappingSource.field(secondEntry.getKey(), secondEntry.getValue());
            }
            mappingSource.endObject();
        }

        mappingSource.endObject().endObject().endObject();

        boolean res = client.admin().indices().preparePutMapping(indexName).setType(type)
                    .setSource(mappingSource).execute().actionGet().isAcknowledged();

        return res;
    }

    /**
     * 使用prepareMapping形式进行创建mapping
     * 预先定义mapping 映射关系
     * @param indexName
     * @param mappings
     * @return
     * @throws IOException
     */
    public void setIndexMapping(Client client, String indexName, String type, Map<String, Map<String, String>> mappings) throws Exception {
        XContentBuilder mappingSource = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("properties");

        Map<String, String> currentMap = null;
        for (Map.Entry<String, Map<String, String>> entry : mappings.entrySet()) {
            currentMap = entry.getValue();
            mappingSource.startObject(entry.getKey());
            for (Map.Entry<String, String> secondEntry : currentMap.entrySet()) {
                mappingSource.field(secondEntry.getKey(), secondEntry.getValue());
            }
            mappingSource.endObject();
        }

        mappingSource.endObject().endObject();
        String str = mappingSource.string();

        //1.先创建索引
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        client.admin().indices().create(request);
        Thread.sleep(2000);
        //2.再创建mapping
    //    PutMappingRequest putMappingRequest = Requests.putMappingRequest(indexName).type("post").source(mappingSource);
        PutMappingRequest putMappingRequest = Requests.putMappingRequest(indexName).type(type).source(mappingSource);
        client.admin().indices().putMapping(putMappingRequest).actionGet();

        return;
    }

    /**
     * 获取mapping
     *
     * @param indexName
     * @param type
     * @return Map
     */
    public Map<String, Object> getMapping(Client client, String indexName, String type) {
        Map<String, Object> mappings = null;
        try {
            mappings = client.admin().indices().getMappings(new GetMappingsRequest().indices(indexName).types(type))
                    .actionGet().getMappings().get(indexName).get(type).getSourceAsMap();
        } catch (Exception e) {
            String errMsg ="Error while getting mapping for indexName : " + indexName + " type : " + type + " " + e.getMessage();
            logger.error(errMsg);
            throw new ElasticsearchException(errMsg);
        }
        return mappings;
    }

    /**
     * 判断mapping是否存在
     *
     * @param client
     * @param indexName
     * @param type
     * @return
     */
    public boolean mappingExists(Client client, String indexName, String type) {
        return client.admin().cluster().prepareState().execute()
                .actionGet().getState().metaData().index(indexName)
                .getMappings().containsKey(type);
    }

    /**
     * 创建一个文档
     * @param index index
     * @param type type
     * @param jsonObject
     */
    public void createDoc(Client client,String index, String type, String id, JSONObject jsonObject) {
        try {
            //IndexResponse indexResponse =
                    client.prepareIndex()
                    .setIndex(index)
                    .setType(type)
                    .setId(id) // 如果没有设置id，则ES会自动生成一个id
                    .setSource(jsonObject.toJSONString(),XContentType.JSON)
                    .get();
        } catch (ElasticsearchException e) {
            e.printStackTrace();
        }
    }

    /**
     * 小数据量的批量一次提交
     * 批量添加索引数据 ，这里keyId可以允许为空，为空则使用ES为我们默认生成的id，
     * keyId不为空，那么该字段为后面map数据中某个key，然后程序会根据该key获取的值作为id进行加入ES
     *
     * @param indexName
     * @param type
     * @param keyId     @Nullable Object keyId,boolean keyVal2Column,
     */
    public void bulkIndex(Client client, String indexName, String type, String keyId, List<Map<String, Object>> listMap) {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        IndexRequestBuilder indexRequest = null;

        if (StringUtils.isNotBlank(keyId)) {
            for (Map<String, ?> currentMap : listMap) {
                String id = (String) currentMap.get(keyId);
                Map<String, ?> tempMap = currentMap;
                if(currentMap.containsKey(keyId)) {
                    tempMap.remove(keyId);
                }
                indexRequest = client.prepareIndex(indexName, type, id);
                indexRequest.setSource(JSONObject.toJSONString(tempMap), XContentType.JSON);
                bulkRequest.add(indexRequest);
            }
        } else {
            for (Map<String, ?> currentMap : listMap) {
                indexRequest = client.prepareIndex(indexName, type);
                indexRequest.setSource(currentMap);
                bulkRequest.add(indexRequest);
            }
        }

        BulkResponse bulkResponse = bulkRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE).get();
        if (bulkResponse.hasFailures()) {
            // process failures by iterating through each bulk response item
            Map<String, String> failedDocuments = new HashMap<String, String>();
            for (BulkItemResponse item : bulkResponse.getItems()) {
                if (item.isFailed())
                    failedDocuments.put(item.getId(), item.getFailureMessage());
            }

            ElasticsearchException elasticsearchException = new ElasticsearchException( "Bulk indexing has failures. Use ElasticsearchException.getFailedDocuments() for detailed messages ["
                    + failedDocuments + "]", failedDocuments);
            logger.error(elasticsearchException.getMessage());
            throw new ElasticsearchException(elasticsearchException.getMessage());
        }
    }

    /**
     * 细粒度的批量提交   BackoffPolicy 批量请求重试失败
     * 有失败会抛出异常     throw new ElasticsearchException(failure);
     *
     * @param indexName
     * @param type
     * @param bulkSize           单位为MB，批量提交总大小，设置-1可禁用
     * @param bulkActions        提交的批次量，默认是1000，设置-1可以禁用
     * @param flushInterval      提交时间间隔
     * @param concurrentRequests 线程数
     * @param listMap
     */
    public void bulkProcessorIndex(Client client, String indexName, String type, String keyId, long bulkSize, int bulkActions,
                                   long flushInterval, int concurrentRequests, List<Map<String, Object>> listMap) {
        IndexRequest indexRequest = null;
        BulkProcessor bulkProcessor = BulkProcessor.builder(client, new BulkProcessor.Listener() {
            public void beforeBulk(long executionId, BulkRequest request) {
                // TODO Auto-generated method stub
            }

            public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
                // TODO Auto-generated method stub
            }

            public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
                // TODO Auto-generated method stub
                throw new ElasticsearchException(failure);
            }

        }).setBulkActions(bulkActions).setBulkSize(new ByteSizeValue(bulkSize, ByteSizeUnit.MB))
                .setFlushInterval(TimeValue.timeValueSeconds(flushInterval))
                .setConcurrentRequests(concurrentRequests)
                .setBackoffPolicy(
                        BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(500), 3))
                .build();
        if (StringUtils.isNotBlank(keyId)) {
            for (Map<String, ?> currentMap : listMap) {
                indexRequest = new IndexRequest(indexName, type, currentMap.get(keyId).toString());
                indexRequest.source(JSONObject.toJSONString(currentMap),XContentType.JSON);
                //System.out.println(JSONObject.toJSONString(currentMap));
                bulkProcessor.add(indexRequest);
            }
        } else {
            for (Map<String, ?> currentMap : listMap) {
                indexRequest = new IndexRequest(indexName, type);
                indexRequest.source(JSONObject.toJSONString(currentMap),XContentType.JSON);
                bulkProcessor.add(indexRequest);
            }
        }

        bulkProcessor.flush();//收尾刷新一次
        bulkProcessor.close();
    }

    /**
     * 细粒度的批量更新   BackoffPolicy 批量请求重试失败
     * 有失败会抛出异常     throw new ElasticsearchException(failure);
     *
     * @param indexName
     * @param type
     * @param bulkSize           单位为MB，批量提交总大小，设置-1可禁用
     * @param bulkActions        提交的批次量，默认是1000，设置-1可以禁用
     * @param flushInterval      提交时间间隔
     * @param concurrentRequests 线程数
     * @param listMap
     */
    public void bulkProcessorUpdate(Client client, String indexName, String type, String keyId, long bulkSize, int bulkActions,
                                    long flushInterval, int concurrentRequests, List<Map<String, Object>> listMap) {
        if (StringUtils.isBlank(keyId)) {
            return;
        }
        UpdateRequest updateRequest = null;
        //这种写法是做啥的？
        BulkProcessor bulkProcessor = BulkProcessor.builder(client, new BulkProcessor.Listener() {
            public void beforeBulk(long executionId, BulkRequest request) {
                // TODO Auto-generated method stub
            }

            public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
                // TODO Auto-generated method stub
            }

            public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
                // TODO Auto-generated method stub
                throw new ElasticsearchException(failure);
            }

        }).setBulkActions(bulkActions).setBulkSize(new ByteSizeValue(bulkSize, ByteSizeUnit.MB))
                .setFlushInterval(TimeValue.timeValueSeconds(flushInterval))
                .setConcurrentRequests(concurrentRequests)
                .setBackoffPolicy(
                        BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(500), 3))
                .build();
        if (StringUtils.isNotBlank(keyId)) {
            for (Map<String, ?> currentMap : listMap) {
                updateRequest = new UpdateRequest(indexName, type, currentMap.get(keyId).toString());
                updateRequest.doc(JSONObject.toJSONString(currentMap),XContentType.JSON);
                //System.out.println(JSONObject.toJSONString(currentMap));
                bulkProcessor.add(updateRequest);
            }
        }

        bulkProcessor.flush();//收尾刷新一次
        bulkProcessor.close();
    }

    /**
     * 细粒度的批量删除
     * 有失败会抛出异常     throw new ElasticsearchException(failure);
     *
     * @param indexName
     * @param type
     * @param bulkSize           单位为MB，批量提交总大小，设置-1可禁用
     * @param bulkActions        提交的批次量，默认是1000，设置-1可以禁用
     * @param flushInterval      提交时间间隔
     * @param concurrentRequests 线程数
     * @param ids                要删除的id集合
     */
    public void bulkDelete(Client client, String indexName, String type, long bulkSize, int bulkActions,
                           long flushInterval, int concurrentRequests, List<Object> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        DeleteRequest deleteRequest = null;
        BulkProcessor bulkProcessor = BulkProcessor.builder(client, new BulkProcessor.Listener() {
            public void beforeBulk(long executionId, BulkRequest request) {
                // TODO Auto-generated method stub
            }

            public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
                // TODO Auto-generated method stub
            }

            public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
                // TODO Auto-generated method stub
                throw new ElasticsearchException(failure);
            }

        }).setBulkActions(bulkActions).setBulkSize(new ByteSizeValue(bulkSize, ByteSizeUnit.MB))
                .setFlushInterval(TimeValue.timeValueSeconds(flushInterval))
                .setConcurrentRequests(concurrentRequests)
                .setBackoffPolicy(
                        BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(500), 3))
                .build();

        for (Object id : ids) {
            deleteRequest = new DeleteRequest(indexName, type, String.valueOf(id));
            bulkProcessor.add(deleteRequest);
        }

        bulkProcessor.flush();//收尾刷新一次
        bulkProcessor.close();
    }

    /**
     * 根据传入的index，type和批量的id来进行获取相应的数据
     * @param index
     * @param type
     * @param ids
     * @return
     */
    public List<Map<String, Object>> queryMultiGet(Client client, String index, String type, String... ids) {
        List<Map<String, Object>> listData = null;
        GetResponse response = null;
        MultiGetResponse multiGetResponse = client.prepareMultiGet()
                .add(index, type, ids).get();
        listData = new ArrayList<Map<String, Object>>();
        for (MultiGetItemResponse itemResponse : multiGetResponse) {
            response = itemResponse.getResponse();
            if (response.isExists()) {
                listData.add(response.getSourceAsMap());
            }
        }
        return listData;
    }

    /**
     * 多条件查询
     *
     * @param indexName
     * @param type
     * @param field
     * @param fieldNames
     */
    public void queryMultiMatch(Client client, String indexName, String type, String field, String... fieldNames) {
        QueryBuilder qb = QueryBuilders.multiMatchQuery(field, fieldNames);
        SearchRequestBuilder srb = client.prepareSearch();
        srb.setIndices(indexName).setTypes(type).setQuery(qb);
        SearchResponse searchResponse = srb.execute().actionGet();
        SearchHits searchHits = searchResponse.getHits();
        for (int i = 0; i < searchHits.getHits().length; i++) {
            System.out.println(searchHits.getHits()[i].getSourceAsString());
        }
    }

    /**
     *  同一关键字多条件查询
     *
     * @param indexName
     * @param type
     * @param field
     * @param fieldNames
     */
    public List<String> matchPhrase(Client client, String indexName, String type, String fieldNames, String field) {
        List<String> listData = null;
        QueryBuilder qb = QueryBuilders.matchPhrasePrefixQuery(field, fieldNames);
        SearchRequestBuilder srb = client.prepareSearch();
        srb.setIndices(indexName).setTypes(type).setQuery(qb).setFrom(0).setSize(200);
        SearchResponse searchResponse = srb.execute().actionGet();
        SearchHits searchHits = searchResponse.getHits();

        listData = new ArrayList<String>();
        for (int i = 0; i < searchHits.getHits().length; i++) {
            listData.add(searchHits.getHits()[i].getId());
        }

        return listData;
    }

    /**
     * 多条件组合查询  and关系
     * @param client
     * @param indexName
     * @param entries
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<String> matchMorePhrase(Client client, String indexName,Iterator entries, int pageNum, int pageSize) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();

            BoolQueryBuilder qb = QueryBuilders.boolQuery();
            String name = entry.getKey().toString();
            String value = entry.getValue().toString();
            qb.should(QueryBuilders.matchPhrasePrefixQuery(name, value));

            boolQueryBuilder.must(qb);
        }

        List<String> listData = null;
        SearchRequestBuilder srb = client.prepareSearch();
        srb.setIndices(indexName).setTypes(indexName).setQuery(boolQueryBuilder).setFrom(pageNum).setSize(pageSize);
        SearchResponse searchResponse = srb.execute().actionGet();
        SearchHits searchHits = searchResponse.getHits();

        listData = new ArrayList<String>();
        for (int i = 0; i < searchHits.getHits().length; i++) {
            listData.add(searchHits.getHits()[i].getId());
        }

        return listData;
    }

    /**
     * 多条件组合查询  and关系
     * @param client
     * @param indexName
     * @param criteria
     * @return
     */
    public List<String> matchMorePhrase(Client client, String indexName, Criteria criteria) {
        //  条件
        JSONArray conditions = criteria.getConditions();
        // 分页信息
        JSONObject pagenation = criteria.getPagenation();
        // 排序信息
        JSONArray orderBys = criteria.getOrderBy();

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        Iterator<Object> entries = conditions.iterator();

        List<String> listData = null;
        //es 初始化,设置index 和 type
        SearchRequestBuilder srb = client.prepareSearch().setIndices(indexName).setTypes(indexName);
        if (pagenation != null && !pagenation.isEmpty()){
           srb = setPagenation(srb,pagenation);
        }
        //条件设置
        //设置where 查询条件
        if (conditions.size() > 0 ){
            //进行数据过滤组装
            while (entries.hasNext()) {
                // 数据行
                JSONObject json = (JSONObject)entries.next();
                // 字段名称
                String attr = json.getString(NodeConstant.ATTR);
                //字段值
                String value = json.getString(NodeConstant.VALUE);
                //操作符
                String operator = json.getString(NodeConstant.OPERATOR);

                BoolQueryBuilder qb = QueryBuilders.boolQuery();
                if ("=".equals(operator)) {
                    qb.should(QueryBuilders.termQuery(attr, value));//完全匹配(字符)
                    //  非完全匹配
                    //   qb.should(QueryBuilders.matchQuery(attr,value));
                    //     qb.should(QueryBuilders.matchPhraseQuery(attr,value));
                }else if ("like".equals(operator)){
                    qb.should(QueryBuilders.matchPhrasePrefixQuery(attr, value));
                }
                boolQueryBuilder.must(qb);
            }
            srb.setQuery(boolQueryBuilder);
        }
        SearchResponse searchResponse = srb.execute().actionGet();
        SearchHits searchHits = searchResponse.getHits();

        listData = new ArrayList<String>();
        int len = searchHits.getHits().length;
        for (int i = 0; i < len; i++) {
            String rowkey = searchHits.getHits()[i].getId();
            listData.add(rowkey);
            System.out.println("rowkey:" + rowkey);
        }
        return listData;
    }

    /**
     * 返回总记录数  and关系
     * @param client
     * @param indexName
     * @param criteria
     * @return
     */
    public Long getTotalSize(Client client, String indexName, Criteria criteria) {
        //  条件
        JSONArray conditions = criteria.getConditions();
        // 分页信息
        JSONObject pagenation = criteria.getPagenation();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        Iterator<Object> entries = conditions.iterator();
        while (entries.hasNext()) {
            JSONObject json = (JSONObject)entries.next();
            // 字段名称
            String attr  = json.getString(NodeConstant.ATTR);
            // 字段值
            String value = json.getString(NodeConstant.VALUE);
            // 操作符
            String operator = json.getString(NodeConstant.OPERATOR);

            BoolQueryBuilder qb = QueryBuilders.boolQuery();
            if ("=".equals(operator)) {
                //qb.should(QueryBuilders.termQuery(attr, value));
                qb.should(QueryBuilders.matchQuery(attr,value));
            }else if ("like".equals(operator)){
                qb.should(QueryBuilders.matchPhrasePrefixQuery(attr, value));
            }
            boolQueryBuilder.must(qb);
        }

        List<String> listData = null;
        SearchRequestBuilder srb = client.prepareSearch();
        srb.setIndices(indexName).setTypes(indexName).setQuery(boolQueryBuilder);


        SearchResponse searchResponse = srb.execute().actionGet();
        SearchHits searchHits = searchResponse.getHits();

        return searchHits.getTotalHits();
    }

    /**
     * 过滤字符串,callFields可为null，为null返回全部字段，否则返回指定的字段
     *
     * @param indexName
     * @param type
     * @param filterMap
     * @param callFields
     * @return List<Map>
     */
    public List<Map> queryStringQuery(Client client, String indexName, String type, Map<String, Object> filterMap,
                                      int from, int limit, String... callFields) {
        SearchRequestBuilder requestBuilder = client.prepareSearch(indexName);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        QueryStringQueryBuilder queryString = null;
        MatchAllQueryBuilder matchAll = null;
        if (filterMap != null && !filterMap.isEmpty()) {
            for (Map.Entry entry : filterMap.entrySet()) {
                queryString = QueryBuilders.queryStringQuery(String.valueOf(entry.getValue()));
                queryString.field(String.valueOf(entry.getKey()));
                boolQueryBuilder.must(queryString);
            }
        } else {//检索全部
            matchAll = QueryBuilders.matchAllQuery();
            boolQueryBuilder.must(matchAll);
        }

        requestBuilder.setTypes(type).setQuery(boolQueryBuilder);
        requestBuilder.setFrom(from).setSize(limit);

        requestBuilder.setFetchSource(callFields, null);    //显示指定字段的内容

        SearchResponse searchResponse = requestBuilder.execute().actionGet();

        return result2MapData(searchResponse);
    }

    /**
     * 该方法是response中的数据进行处理成List<Map>数据进行返回
     *
     * @param searchResponse
     * @return List<Map>
     * queryStringQuery  不能使用searchHit.getSourceAsString()来取值，只能使用迭代遍历的方式
     */
    public List<Map> result2MapData(SearchResponse searchResponse) {
        List<Map> listData = new ArrayList<Map>();
        //Map currentMap = null;
        if (searchResponse.status().getStatus() == 200) {
            for (SearchHit searchHit : searchResponse.getHits().getHits()) {
                /*Map<String, SearchHitField> mapHit = searchHit.getFields();
				currentMap = new HashMap<>();
				for(Map.Entry<String, SearchHitField> hitEntry : mapHit.entrySet()){
					currentMap.put(hitEntry.getKey(), hitEntry.getValue().getValue());
				}
				listData.add(currentMap);
				*/
                listData.add(JSONObject.parseObject(searchHit.getSourceAsString(), Map.class));
            }
        }
        return listData;
    }

    /**
     * 测试检索到的记录数
     *
     * @param indexName
     * @param type
     * @param filterMap
     * @param callFields
     * @return
     */
    public long queryCountByStringQuery(Client client, String indexName, String type,
                                        Map<String, Object> filterMap, String... callFields) {
        long totalCount = 0;
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        QueryStringQueryBuilder queryString = null;
        MatchAllQueryBuilder matchAll = null;
        if (filterMap != null && !filterMap.isEmpty()) {
            for (Map.Entry entry : filterMap.entrySet()) {
                queryString = QueryBuilders.queryStringQuery(String.valueOf(entry.getValue()));
                queryString.field(String.valueOf(entry.getKey()));
                boolQueryBuilder.must(queryString);
            }
        } else {//检索全部
            matchAll = QueryBuilders.matchAllQuery();
            boolQueryBuilder.must(matchAll);
        }
        SearchResponse response = client.prepareSearch(indexName).setTypes(type)
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setQuery(boolQueryBuilder).execute().actionGet();
        int statusCode = response.status().getStatus();
        if (statusCode == 200 || statusCode == 201) {
            totalCount = response.getHits().getTotalHits();
        }
        return totalCount;
    }

    /**
     * 根据id删除数据
     * @param client
     * @param index
     * @param type
     * @param id
     * @return
     */
    public RestStatus deleteDoc(Client client, String index, String type, String id) {

        DeleteResponse deleteResponse  = client
                .prepareDelete()
                .setIndex(index)
                .setType(type)
                .setId(id)
                .get();

        return deleteResponse.status();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 组装排序sort
     */
    private SearchRequestBuilder addSort(SearchRequestBuilder searchRequestBuilder,JSONArray esSortFileds){
        SortBuilder sortBuilder = null;
        if( esSortFileds == null || esSortFileds.isEmpty()){
         //   sortBuilder = SortBuilders.fieldSort("id").order(org.elasticsearch.search.sort.SortOrder.ASC);
          //  searchRequestBuilder.addSort(sortBuilder);
            return searchRequestBuilder;// 按照es 的默认顺序
        }else{
            Iterator<Object> it = esSortFileds.iterator();
            while(it.hasNext()){
                org.elasticsearch.search.sort.SortOrder sortOrder = org.elasticsearch.search.sort.SortOrder.ASC;
                JSONObject jsonObject = (JSONObject)it.next();
                //字段名称
                String attr = jsonObject.getString(NodeConstant.ATTR);
                //字段值
                String type = jsonObject.getString(NodeConstant.SORT);
                if("desc".equals(type)){
                    sortBuilder = SortBuilders.fieldSort(attr).order(org.elasticsearch.search.sort.SortOrder.DESC);
                    searchRequestBuilder.addSort(sortBuilder);
                }else if ("asc".equals(type)){
                    sortBuilder = SortBuilders.fieldSort(attr).order(org.elasticsearch.search.sort.SortOrder.ASC);
                    searchRequestBuilder.addSort(sortBuilder);
                }
            }
            return searchRequestBuilder;
        }
    }

    //es where 条件
    private SearchRequestBuilder filter(SearchRequestBuilder searchRequestBuilder,JSONArray esSortFileds){
     //   FilterBuilder filterBuilder;
        return searchRequestBuilder;
    }

    /*聚合查询,结合统计*/
    //group by
    private SearchRequestBuilder group(SearchRequestBuilder searchRequestBuilder,JSONArray groupByColumns){
        if (groupByColumns == null || groupByColumns.isEmpty()){
            return searchRequestBuilder;
        } else {
            //AggregationBuilder 聚合索
            Iterator<Object> it = groupByColumns.iterator();
            JSONObject jsonObject = null;
            AggregationBuilder addAggregation = null;
            while(it.hasNext()){
                jsonObject = (JSONObject) it.next();
                //字段名称
                String attr = jsonObject.getString(NodeConstant.ATTR) ;
                //聚合名称
                String aggName = attr +"Agg";
                if (addAggregation != null){
                    AggregationBuilder terms = AggregationBuilders.terms(aggName).field(attr);
                    addAggregation = addAggregation.subAggregation(terms);
                }else{
                    addAggregation = AggregationBuilders.terms(aggName).field(attr) ;
                }
            }
            searchRequestBuilder = searchRequestBuilder.addAggregation(addAggregation);
        }
        return searchRequestBuilder;
    }

    private SearchRequestBuilder setPagenation(SearchRequestBuilder searchRequestBuilder,JSONObject pagenation){
        //设置分页查询
        int page = pagenation.getInteger(NodeConstant.START_PAGE);
        int size = pagenation.getInteger(NodeConstant.ROWS);
        page =(1 <= page ) ? page : 1;
        int startrow = (page-1) * size ;
      //  long totalPage = (totalSize % row) == 0 ? (totalSize / row) : (totalSize / row + 1);
      //  currenSize = startPage < totalPage ? row : (totalSize - (startPage - 1) * row);
        //轻分页，以后有时间改成深度分页优化大数据的查询
        searchRequestBuilder.setFrom(startrow).setSize(size);
        return searchRequestBuilder;
    }
    /**
     * @param field
     * @param operator
     * @param value
     * @return
     */
    private AggregationBuilder rangfind(String field,String operator,String value){
        Double doubleValue = Double.valueOf(value);
        AggregationBuilder aggregationBuilder = null;
        String agg  = field + "_agg";
        if ("<".equals(operator)){
            //doubleValue到正无穷
            aggregationBuilder = AggregationBuilders.range(agg).field(field).addUnboundedTo(doubleValue);
        }else if("<=".equals(operator)) {
            //doubleValue到正无穷
            aggregationBuilder = AggregationBuilders.range(agg).field(field).addUnboundedTo(doubleValue);
        }else if (">".equals(operator)){
            //负无穷到doubleValue
            aggregationBuilder = AggregationBuilders.range(agg).field(field).addUnboundedFrom(doubleValue);
        }else if (">=".equals(operator)){
            //负无穷到doubleValue
            aggregationBuilder = AggregationBuilders.range(agg).field(field).addUnboundedFrom(doubleValue);
        }else{
            //aggregationBuilder = AggregationBuilders.range(agg).field(field).addRange(min,max);
            return  null;
        }
        return aggregationBuilder;
    }

    /**
     * 分组分类(组装有问题)
     * @param field
     * @param operator
     * @param value
     * @return
     */
    private AggregationBuilder dataRangFind(String field,String operator,String value){
        String agg = "agg_" + field ;
        AggregationBuilder aggregationBuilder = null;
        if ("<=".equals(operator)){
            value = value.substring(0,4);
            aggregationBuilder= AggregationBuilders.dateRange(agg).field(field).format("yyyy").addUnboundedTo(value);
            //"yyyy-MM-dd HH:mm:ss"
        }else if(">=".equals(operator)){
            aggregationBuilder= AggregationBuilders.dateRange(agg).field(field).format("yyyy-MM-dd").addUnboundedFrom(value);
        }
        return aggregationBuilder;
    }

    /**
     * 获取字符串格式
     * @param dateStr
     * @return
     */
    String getDateFromat(String dateStr){
        String dateFormat = null;
        if (isMatch(dateStr,"[0-9]{4}-[0-9]{1,2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}")){
            dateFormat ="yyyy-MM-dd HH:mm:ss";
        } else if(isMatch(dateStr,"[0-9]{4}-[0-9]{1,2}-[0-9]{2}")){
            dateFormat = "yyyy-MM-dd";
        }
        return dateFormat;
    }

    /**
     * 字符串大小比较
     * @param field
     * @param operator
     * @param value
     * @return
     */
    private QueryBuilder rangQbFind(String field,String operator,String value) {
        QueryBuilder rangQb = null;
        if (">".equals(operator)){
            rangQb =  QueryBuilders.rangeQuery(field).gt(value);
        }else if("<".equals(operator)){
            rangQb =  QueryBuilders.rangeQuery(field).lt(value);
        }else if("<=".equals(operator)){
            rangQb =  QueryBuilders.rangeQuery(field).lte(value);
        }else if(">=".equals(operator)){
            rangQb =  QueryBuilders.rangeQuery(field).gte(value);
        }
        return rangQb;
    }

    /**
     * 时间比较
     * @param field
     * @param operator
     * @param dateStr
     * @return
     */
    private QueryBuilder dateRangeQbFind(String field,String operator,String dateStr) {
        String dateFormat  = getDateFromat(dateStr);
        if (dateFormat == null){
            return null;
        }
        QueryBuilder rangQb = null;
        if (">".equals(operator)){
            rangQb =  QueryBuilders.rangeQuery(field).format(dateFormat).gt(dateStr);
        }else if("<".equals(operator)){
            rangQb =  QueryBuilders.rangeQuery(field).format(dateFormat).lt(dateStr);
        }else if("<=".equals(operator)){
            rangQb =  QueryBuilders.rangeQuery(field).format(dateFormat).lte(dateStr);
        }else if(">=".equals(operator)){
            rangQb =  QueryBuilders.rangeQuery(field).format(dateFormat).gte(dateStr);
        }else if("=".equals(operator)){
         //   rangQb = QueryBuilders.termQuery(field, dateStr);
            //es 5.4 的日期查询有bug,低版本的可以
            rangQb = QueryBuilders.rangeQuery(field).format(dateFormat).gte(dateStr).lte(dateStr);
        }else if("!=".equals(operator)){
            rangQb = QueryBuilders.rangeQuery(field).format(dateFormat).lt(dateStr).gt(dateStr);
        }
        return rangQb;
    }

    /**
     *
     * @param src
     * @param regex
     * @return
     */
    public static boolean isMatch(String src,String regex){
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(src);
        return matcher.matches();//全部匹配
    }

    /**
     * 根据条件创建conditionBqb  and 关系
     * @param conditions
     * @param indexName
     * @return
     */
    BoolQueryBuilder getConditionsBqb(JSONArray conditions,String indexName){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        HashSet<String> esIdInSet = new HashSet<String>();
        HashSet<String> esIdNotInSet = new HashSet<String>();
        Map<String,HashSet<String>> esInMap = new HashMap<>();
        List<BoolQueryBuilder> inConditionsList = new ArrayList<>();

        //进行数据过滤组装
        Iterator<Object> entries = conditions.iterator();
        while (entries.hasNext()) {
            JSONObject json = (JSONObject)entries.next();
            String attr = json.getString(NodeConstant.ATTR);
            String value = json.getString(NodeConstant.VALUE);
            String operator = json.getString(NodeConstant.OPERATOR);
            String type = json.getString(NodeConstant.TYPE);
            if("id".equals(attr)){
                if ("!in".equals(operator)){
                    String[] esIdArray = value.split(",");
                    List<String> list = Arrays.asList(esIdArray);
                    esIdNotInSet.addAll(list);
                }else if("in".equals(operator)){
                    String[] esIdArray = value.split(",");
                    List<String> list = Arrays.asList(esIdArray);
                    esIdInSet.addAll(list);
                } else if ("=".equals(operator)) {
                    esIdInSet.add(value);
                }else if ("!=".equals(operator)){
                    //添加不等
                    esIdNotInSet.add(value);
                }else{
                    String errMsg = "thera is not support the operator ["+operator+"]";
                    logger.error(errMsg);
                    throw new IllegalArgumentException(errMsg);
                }
                continue;
            }

            BoolQueryBuilder qb = QueryBuilders.boolQuery();
            //////////////////////date 类型特殊处理(后面可能不使用date了)///////////////////////////////////////////////////////////
            if ("date".equals(type)){
                QueryBuilder dateRangQb = null;
                dateRangQb = dateRangeQbFind(attr,operator,value);
                if(null != dateRangQb){
                    qb.must(dateRangQb);
                    boolQueryBuilder.must(qb);
                    continue;
                }
            }

            ////////////////////////////////////////////////////////////////////////////////////////////
            if ("=".equals(operator)) {
                //完全匹配(字符),如果es 为分词形式
                qb.should(QueryBuilders.termQuery(attr, value));
                //  非完全匹配
                //   qb.should(QueryBuilders.matchQuery(attr,value));
                //     qb.should(QueryBuilders.matchPhraseQuery(attr,value));
            }else if ("like".equals(operator)){
                //qb.should(QueryBuilders.matchPhrasePrefixQuery(attr, value));
                value = value.replaceAll("\\*","\\\\*");
                  qb.should(QueryBuilders.wildcardQuery(attr,"*" + value + "*"));
            }else if (">".equals(operator) || "<".equals(operator) || "<=".equals(operator) || ">=".equals(operator)){
                //判断是否时间类型
                QueryBuilder rangQb = null;
                rangQb = rangQbFind(attr,operator,value);
                if(null != rangQb){
                    qb.must(rangQb);
                }
                //   listRangQb.add(rangQb);
            }else if("!=".equals(operator)){  // 不等于
                qb.mustNot(QueryBuilders.termQuery(attr,value));
            } else if("is".equals(operator)){
                if ("null".equals(value)){
                   qb.mustNot(isExists(attr));
                }else{
                    String errMsg = "Orm JSONError:The value of the operator symbol 'is' must be 'null'";
                    logger.error(errMsg);
                    throw new IllegalArgumentException(errMsg);
                }
            }else if ("!is".equals(operator)){
                if ("null".equals(value)) {
                    qb.must(isExists(attr));
                }else{
                    String errMsg = "Orm JSONError:The value of the operator symbol '!is' must be 'null'";
                    logger.error(errMsg);
                    throw new IllegalArgumentException(errMsg);
                }
            }else if("in".equals(operator)){
                String[] valueArray = value.split(",");
                if (valueArray.length != 0){
                    for(String valueData:valueArray ) {
                        qb.should(QueryBuilders.termQuery(attr, valueData));
                    }
                }else{
                    String errMsg = "Orm JSONError:The value of the operator symbol ["+ operator +"] is not support";
                    logger.error(errMsg);
                    throw new IllegalArgumentException(errMsg);
                }
            }else if("!in".equals(operator)){
                String[] valueArray = value.split(",");
                BoolQueryBuilder inBoolQuery = QueryBuilders.boolQuery();
                if (valueArray.length != 0){
                    for(String valueData:valueArray ) {
                        inBoolQuery.should(QueryBuilders.termQuery(attr, valueData));
                    }
                    qb.mustNot(inBoolQuery);
                }else{
                    String errMsg ="Orm JSONError:The value of the operator symbol ["+ operator +"] is not support";
                    logger.error(errMsg);
                    throw new IllegalArgumentException(errMsg);
                }
            } else{
                String errMsg ="Orm Rules:The value of the operator symbol ["+ operator +"] is not support";
                logger.error(errMsg);
                throw new IllegalArgumentException(errMsg);
            }
            //else if("match".equals(operator)){
            //    qb.should(QueryBuilders.matchPhrasePrefixQuery(attr, value));
            // }
            // must 是and 操作  should 是 or 操作 mustNot 是 ！= 操作
            boolQueryBuilder.must(qb);
        }
        //添加in 和not
        //in
        if (0 < esIdInSet.size()){
            List<String> idsList = new ArrayList<String>(esIdInSet);
            String[] ids = (String[])idsList.toArray(new String[idsList.size()]);
            QueryBuilder qbId =  QueryBuilders.idsQuery(indexName).addIds(ids);
            boolQueryBuilder.must(qbId);
        }
        //not in
        if ( 0 < esIdNotInSet.size()){
            List<String> idsList = new ArrayList<String>(esIdNotInSet);
            String[] ids = (String[])idsList.toArray(new String[idsList.size()]);
            QueryBuilder qbId =  QueryBuilders.idsQuery(indexName).addIds(ids);
            boolQueryBuilder.mustNot(qbId);
        }
        return boolQueryBuilder;
    }

    /**
     * 组装判空查询
     * @param attr
     * @return
     */
    QueryBuilder isExists(String attr){
     //   return QueryBuilders.constantScoreQuery(QueryBuilders.existsQuery(attr));
        return QueryBuilders.existsQuery(attr); //kibana 可以
    }

    /**
     * 查询
     * @param client
     * @param indexName
     * @param criteria
     * @return
     */
    public JSONObject query(Client client, String indexName, Criteria criteria){
        JSONObject esResult = new JSONObject();
        JSONArray conditions = criteria.getConditions();        //  条件
        JSONObject pagenation = criteria.getPagenation();       // 分页信息
        JSONArray orderBys = criteria.getOrderBy();             // 排序信息

        //List<QueryBuilder> listRangQb = new ArrayList<QueryBuilder>();
        //条件设置
        //设置where 查询条件
        // AggregationBuilder totalAgg = AggregationBuilders.range("aggs");
        boolean isConditionSearch = true;
        ////////////////////////条件查询/////////////////////////////////
        BoolQueryBuilder boolQueryBuilder = null;
        if (conditions != null && conditions.size() > 0 ){
            boolQueryBuilder = getConditionsBqb(conditions,indexName);
        }else{
            isConditionSearch = false;
        }
        //设置SearchRequestBuilder
        SearchRequestBuilder srb = client.prepareSearch().setIndices(indexName).setTypes(indexName);
        //设置查询条件
        if (!isConditionSearch) {
            //全查询
            QueryBuilder qb = QueryBuilders.matchAllQuery();
            srb.setQuery(qb);
        }else{
            srb.setQuery(boolQueryBuilder);
        }
        //范围过滤(范围过滤好像只能进行一列条件过滤),两列好像组装不了(待研究)
        // 过滤不能两列
        // srb = srb.setPostFilter(rangQb);
        //添加排序SortBuilder
        if(orderBys != null){
            srb = addSort(srb,orderBys);
        }
        // srb = group(srb,orderBys);
        //获取命中的总数
        long total = srb.execute().actionGet().getHits().getTotalHits();
        esResult.put(TOTAL,total);
        //分页
        if (pagenation != null && !pagenation.isEmpty()){
            srb = setPagenation(srb,pagenation);
        }else{
            srb.setSize(new Long(total).intValue());//long 超过int 最大值的话会怎么样呢
        }
        SearchResponse searchResponse = srb.execute().actionGet();
        SearchHits searchHits = searchResponse.getHits();
        List<String> listData  = new ArrayList<String>();
        int count = searchHits.getHits().length;
        for (int i = 0; i < count; i++) {
            String rowkey = searchHits.getHits()[i].getId();
            listData.add(rowkey);
            System.out.println("rowkey:"+rowkey);
        }
        esResult.put(HITS,count);//查询到的数据
        esResult.put(LIST,listData);//查询到的数据
        return esResult;
    }


    /**
     * 根据条件统计数量
     * @param client
     * @param indexName
     * @param criteria
     * @return
     */
    public long count(Client client, String indexName, Criteria criteria){
        JSONArray conditions = criteria.getConditions();        //  条件
        SearchRequestBuilder srb = client.prepareSearch().setIndices(indexName).setTypes(indexName);
        BoolQueryBuilder boolQueryBuilder = null;
        if (conditions != null && conditions.size() > 0 ){
            boolQueryBuilder = getConditionsBqb(conditions,indexName);
            srb.setQuery(boolQueryBuilder);
        }else{
            //全查询
            QueryBuilder qb = QueryBuilders.matchAllQuery();
            srb.setQuery(qb);
        }

        long total = srb.execute().actionGet().getHits().getTotalHits();
        return total;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *  and or not 优先级查询
     * @param client
     * @param indexName
     * @param criteria
     * @return
     */
     public JSONObject richfind(Client client, String indexName, Criteria criteria) {
        JSONObject esResult = new JSONObject();
        JSONObject pagenation = criteria.getPagenation();       // 分页信息
        JSONArray orderBys = criteria.getOrderBy();             // 排序信息
        String conditions = criteria.getConditionsInWhere();    // 实现 and or not 条件
         //String 字符串转 orm 格式
        JSONObject conditionObject = null;
        if (conditions != null && !conditions.isEmpty()){
            SqlConditionAnalyze sqlConditionAnalyze = new SqlConditionAnalyze(conditions);
            conditionObject = sqlConditionAnalyze.SqlConditionAnalyze();
            System.out.println("///////////////////////////////////////////////////////////////////////");
            System.out.println(conditionObject.toJSONString());
        }
        //orm 格式转
        boolean isConditionSearch = true;
        BoolQueryBuilder boolQueryBuilder =null;
        if (null != conditionObject){
            boolQueryBuilder =  getConditionsBqb(conditionObject);
        }else{
            isConditionSearch = false;
        }
        //设置SearchRequestBuilder
         SearchRequestBuilder srb = client.prepareSearch().setIndices(indexName).setTypes(indexName);

         //设置查询条件
         if (!isConditionSearch) {
             //全查询
             QueryBuilder qb = QueryBuilders.matchAllQuery();
             srb.setQuery(qb);
         }else{
             srb.setQuery(boolQueryBuilder);
         }
         //范围过滤(范围过滤好像只能进行一列条件过滤),两列好像组装不了(待研究)
         // 过滤不能两列
         // srb = srb.setPostFilter(rangQb);
         //添加排序SortBuilder
         if(orderBys != null){
             srb = addSort(srb,orderBys);
         }
         //获取命中的总数
         long total = srb.execute().actionGet().getHits().getTotalHits();
         esResult.put(TOTAL,total);
         //分页
         if (pagenation != null && !pagenation.isEmpty()){
             srb = setPagenation(srb,pagenation);
         }else{
             srb.setSize(new Long(total).intValue());//long 超过int 最大值的话会怎么样呢
         }

         SearchResponse searchResponse = srb.execute().actionGet();
         SearchHits searchHits = searchResponse.getHits();
         List<String> listData = new ArrayList<String>();
         int count = searchHits.getHits().length;
         for (int i = 0; i < count; i++) {
             String rowkey = searchHits.getHits()[i].getId();
             listData.add(rowkey);
             System.out.println("rowkey:"+rowkey);
         }
         esResult.put(HITS,count);//查询到的数据
         esResult.put(LIST,listData);//查询到的数据
         return esResult;
    }
    /////////////////////////////////and or not 格式转换//////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////orm 格式转Bq///////////////////////////////////////////////////////////////////////////////////////
    /**
     * and or not orm 的json 格式 获取BoolQueryBuilder
     * @param conditionsJsonObject
     * @return
     */
     BoolQueryBuilder getConditionsBqb(JSONObject conditionsJsonObject){
        BoolQueryBuilder resultBqb = QueryBuilders.boolQuery();
        if (conditionsJsonObject.containsKey(ConditionNode.AND)){
            resultBqb =  setRelationBqb(conditionsJsonObject.getJSONArray(ConditionNode.AND),true);
        }else if(conditionsJsonObject.containsKey(ConditionNode.OR)){
            resultBqb = setRelationBqb(conditionsJsonObject.getJSONArray(ConditionNode.OR),false);
        }else if(conditionsJsonObject.containsKey(ConditionNode.NOT)){
            JSONObject notJsonObject = conditionsJsonObject.getJSONObject(ConditionNode.NOT);
            BoolQueryBuilder _bqb = getConditionsBqb(notJsonObject);
            if (_bqb != null){
                resultBqb.mustNot(_bqb);
            }
        }else {
            //属性节点 返回的是qb
            if (!conditionsJsonObject.containsKey(ConditionNode.ATTR)
                    || !conditionsJsonObject.containsKey(ConditionNode.OPERATOR)
                    || !conditionsJsonObject.containsKey(ConditionNode.VALUE)) {
                String errMsg = "there is something wrong with conditions format ";
                logger.error(errMsg);
                return null;
            }
            //处理单条件
            JSONObject isNot = new JSONObject() ;
            isNot.put("boolean",false);
            QueryBuilder qb = getConditionQb(conditionsJsonObject,isNot);
            if (qb == null){
                return null;
            }
            if (isNot.getBoolean("boolean")){
                resultBqb.mustNot(qb);
            }else{
                resultBqb.must(qb);
            }
        }
        return resultBqb;
    }

    /**
     * 根据and or 关系来组装
     * @param relationArray
     * @param isAnd  标识and 或者 or 关系
     */
    private BoolQueryBuilder setRelationBqb( JSONArray relationArray,boolean isAnd){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolean isStruct = false;
        for ( Iterator<Object> iterator = relationArray.iterator();iterator.hasNext();){
            JSONObject ob = (JSONObject) iterator.next();
            BoolQueryBuilder _qb = getConditionsBqb(ob);
            if (_qb == null){
                continue;
            }
            if (isAnd){
                //and 关系
                boolQueryBuilder.must(_qb);
            }else {
                //or 关系
                boolQueryBuilder.should(_qb);
            }
            isStruct = true;
        }
        if (isStruct){
            return boolQueryBuilder;
        }else{
            return  null;
        }
    }

    /**
     * and or not 条件的处理返回
     * @param conditionsJsonObject
     * @return
     */
    private QueryBuilder getConditionQb(JSONObject conditionsJsonObject,JSONObject isNot) {
        QueryBuilder qb = null;
        String attr = conditionsJsonObject.getString(ConditionNode.ATTR);
        String value = conditionsJsonObject.getString(ConditionNode.VALUE);
        String operator = conditionsJsonObject.getString(ConditionNode.OPERATOR);
        if ("=".equals(operator)) {
            if (!"".equals(value)) {
                //完全匹配(字符),如果es 为分词形式
                qb = QueryBuilders.termQuery(attr, value);
            }else{
                String errMsg = "The value of the key ["+attr+"] in the condition's jsonobject is empty.";
                logger.error(errMsg);
                throw new  IllegalArgumentException(errMsg);
            }
        } else if ("like".equals(operator)) {
       //    qb = QueryBuilders.matchPhrasePrefixQuery(attr, value);
            value = value.replaceAll("\\*","\\\\*");
             qb = QueryBuilders.wildcardQuery(attr,"*" + value + "*");
        } else if (">".equals(operator) || "<".equals(operator) || "<=".equals(operator) || ">=".equals(operator)) {
            qb = rangQbFind(attr, operator, value);
        } else if ("!=".equals(operator)){
            if (!"".equals(value)) {
                qb = QueryBuilders.termQuery(attr, value);//外面再not
                isNot.put("boolean",true);
            }else{
                String errMsg = "The value of the key ["+attr+"] in the condition's jsonobject is empty.";
                logger.error(errMsg);
                throw new  IllegalArgumentException(errMsg);
            }
        }else{
            //不支持的符号操作
            return null;
        }
        return qb;
    }

}

package com.huntkey.rx.sceo.serviceCenter.provider;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.serviceCenter.ServiceCenterProviderApplication;
import com.huntkey.rx.sceo.serviceCenter.common.emun.EdmType;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import com.huntkey.rx.sceo.serviceCenter.provider.client.EDMClient;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.core.DataSet;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.db.hbase.HBaseHandler;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.model.FullCriteria;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.tools.EsTool;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.util.EsUtil;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.util.HbaseUtil;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * Created by linziy on 2017/8/23.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServiceCenterProviderApplication.class)
public class EsTest {
    @Autowired
    private HbaseUtil hbaseUtil; // hbase 工具类
    @Autowired
    private EsUtil esUtil; // es 工具类
    @Autowired
    private TransportClient client; // es 客户端
    @Autowired
    private HBaseHandler hBaseHandler;
    @Autowired
    private EDMClient edmClient;
    @Autowired
    private EsTool esTool;
    /**
     * esAddDelayedTest()测试显示:es 插入数据，并不能立刻查询所有的数据,es 有同步延时问题
     */
    @Test
    @Ignore
    public void esAddDelayedTest(){
        List<Map<String, Object>> esList = new ArrayList<Map<String, Object>>();
        String str = "str";
        String index = "lex_index";
        String type = index;
        for (int i = 0;i<10;i++){
            JSONObject _esJson = new JSONObject();
            String rowKey = UUID.randomUUID().toString().replace("-","");
            str = str + Integer.valueOf(i).toString();
            _esJson.put("id",rowKey);
            _esJson.put("lex01",i);
            _esJson.put("lex02",str);
            Map<String, Object> map = (Map<String, Object>) _esJson;
            esList.add(map);
        }
        //es 查询所有的数据
        SearchRequestBuilder srb = client.prepareSearch().setIndices(index).setTypes(type);
        QueryBuilder qb = QueryBuilders.matchAllQuery();
        srb.setQuery(qb);
        //////////////////////////////////////////////////////////////////////////////////////
        // es索引中新增数据
        esUtil.bulkIndex(client, index, type, NodeConstant.ID, esList);
        long total = srb.execute().actionGet().getHits().getTotalHits();
        System.out.println("总数："+ Long.valueOf(total).toString());

        return;
    }

    /**
     * 添加测试
     */
    @Test
    @Ignore
    public void addDelayedTest(){
        try{
            List<JSONObject> hbaseList = new ArrayList<JSONObject>();
            List<Map<String, Object>> esList = new ArrayList<Map<String, Object>>();
            String str = "str";
            String index = "lex_es_hbase";
            String type = index;
            List<String> hashSet = new ArrayList<String>();
            for (int i = 0;i<10;i++){
                JSONObject _esJson = new JSONObject();
                String rowKey = UUID.randomUUID().toString().replace("-","");
                //
                str = str + Integer.valueOf(i).toString();
                _esJson.put(NodeConstant.ID,rowKey);
                _esJson.put("lex01",i);
                _esJson.put("lex02",str);
                Map<String, Object> map = (Map<String, Object>) _esJson;
                esList.add(map);
                hashSet.add(rowKey);

                JSONObject _hbaseJson = (JSONObject) _esJson.clone();
                hbaseList.add(_hbaseJson);
            }

            if(hashSet.size() < 10){
                throw new IllegalArgumentException("haseSet Is smaller than the hashSet");
            }
            //es 查询所有的数据
            SearchRequestBuilder srb = client.prepareSearch().setIndices(index).setTypes(type);
            QueryBuilder qb = QueryBuilders.matchAllQuery();
            srb.setQuery(qb);
            //组装 hbase 查询

            //组装load 参数
            FullCriteria criteria = new FullCriteria();
            criteria.setTableName("lex_es_hbase");
            JSONArray ids = new JSONArray();
            for(String rowkey: hashSet){
                ids.add(rowkey);
            }
            criteria.setIDs(ids);
            //////////////////////////////////////////////////////////////////////////////////////
            // es索引中新增数据
            esUtil.bulkIndex(client, index, type, NodeConstant.ID, esList);

            long total = srb.execute().actionGet().getHits().getTotalHits();
            System.out.println("//////////es 结果///////////////////////////////////////////////////////////////");
            System.out.println("Es总数："+ Long.valueOf(total).toString());

            //使用load 方法到hbase get 数据
            //hbase 新增数据
            hbaseUtil.putData(index, HbaseUtil.COLUMNFAMILY, hbaseList);
            DataSet dataSet = hBaseHandler.load(criteria);

            JSONObject jsonObject  = dataSet.getJsonObject();
            JSONArray jsonArray = jsonObject.getJSONArray(NodeConstant.DATASET);

            System.out.println("//////////hbase 结果///////////////////////////////////////////////////////////////");
            System.out.println(jsonArray.toJSONString());

        }catch(Exception e){
            e.printStackTrace();
        }
        return;
    }

    @Test
    @Ignore
    public void addMappingTest(){
        String index = "staff";
    //    Result result = edmClient.getOrmBaseEdmCode("V1.0","staff");
        Result result = edmClient.getEdmDataType("V1.0","staff");
        Object object = result.getData();
        String str = object.toString();
        if (str.startsWith("[{") && str.endsWith("}]")){
            str = str.replaceAll("\\[\\{","");
            str = str.replaceAll("\\}\\]","");
        }
        System.out.println(str);
        /////////////////////////////////////////////////////////////////////////////////

        Map<String, Map<String, String>> settingMaps = new HashMap<>();
        String[] strlist = str.split(",");
        for (String edmColumnIndex:strlist){
            JSONObject jsonObject = new JSONObject();
            String[] tmp = edmColumnIndex.split("=");
            //列名
            String[] property = tmp[0].toLowerCase().split("\\.");
            int _index = property.length - 1;
            String attr = property[_index];
            //类型dataType
            String dataType =  tmp[1];
            Map<String, String> columnsSetting = new HashMap<>();
            if (EdmType.EDM_ASSEMBLE.getDataType().equals(dataType)){
                continue;
            }else if(EdmType.EDM_INT.getDataType().equals(dataType)){
                columnsSetting.put("type","integer");
            }else if(EdmType.EDM_FLOAT.getDataType().equals(dataType)||EdmType.EDM_DECIMAL.getDataType().equals(dataType)){
                columnsSetting.put("type","float");
            }else if(EdmType.EDM_LONG.getDataType().equals(dataType)){
                columnsSetting.put("type","long");
            }else{
                columnsSetting.put("type","keyword");
            }
            settingMaps.put(attr,columnsSetting);
        }
        //////////组织es mapping//////////////////////////////////////////////////////////////////
        String testIndex = "lex_index_mapping";
        esUtil.deleteIndex(client,testIndex);
        try{
            esUtil.setIndexMapping(client, testIndex,testIndex,settingMaps);
        }catch(Exception e){
            e.printStackTrace();
        }

        return;
    }


    @Test
    @Ignore
    public void refreshTest(){
        //根据edmName 刷新数据
     //   String edmNameListString = "supplier";//
   //    String edmNameListString = "supplier.supp117";
        String edmNameListString = "salesorder.saor045.saor080";
    //    String edmNameListString = "period";
      // String edmNameListString = "supplier_link_records";//联动表
     //  String edmNameListString = "supplier.reso003";//父类的属性集
        //少龙831 测试 清理数据
   //     String edmNameListString = "enterprise,people,monitortreeorder,reportingline,entryofstaff,orgcreated,positioncreated,staff,dept,jobposition,depttree,staffofdept,statistics,staff_link_records,dept_link_records,depttree_link_records,staffofdept_link_records,depttree.moni015,staffofdept.moni015,reportingline.moni015";
        try{
            String[] edmNameList = edmNameListString.split(",");
            for (String edmName:edmNameList){
                //1.创建索引
                System.out.println("//////////////edmName:"+edmName);
                esTool.createIndexAndMapping("V1.0",edmName);//不删索引则将此处注释
                //2.同步数据
               esTool.synchrodataFromHbase("V1.0",edmName);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("///////////////////////////////////////////////////////////////////////");
        return ;
    }

    //hbase 建表
    @Test
    @Ignore
    public void createHbaseTable(){
        //edm 类
        String edmNameListString = "a";
        try{
            String[] edmNameList = edmNameListString.split(",");
            for (String edmName:edmNameList){
                String tableName= esTool.getTableName(edmName);
                if (!hbaseUtil.isTableExists(edmName)) {
                    hbaseUtil.createTable(edmName);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("////////////////end of hbase create table ///////////////////////////////////////////////////////");
        return ;
    }

}

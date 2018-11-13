package com.huntkey.rx.sceo.serviceCenter.provider.orm.db.hbase;/**
 * Created by yangcong on 2017/6/22.
 */

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.enums.ResultEnum;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.util.EsUtil;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.util.HbaseUtil;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.util.Bytes;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author yangcong
 * @create 2017-06-22 18:34
 * @description
 **/
@Repository
public class EsAndHbaseDaoImpl implements EsAndHbaseDao {

    @Autowired
    private HbaseUtil hbaseUtil;               //hbase 工具类

    @Autowired
    private EsUtil esUtil;                      //es 工具类

    @Autowired
    private TransportClient client;             //es 客户端

    /**
     * 创建es索引和hbase表
     * @param json
     * @return
     * @throws Exception
     */
    @Override
    public String createEsIndexAndHbaseTable(JSONObject json) throws Exception{
        //es 索引名和hbase 表名
        String name = json.getString("tableName");

        // 创建es索引
        if (!esUtil.indexExists(client,name)){
            esUtil.createIndex(client,name);

            //新增mapping，将字段设置进去
            Map<String, Map<String, String>> mappings = new HashMap<String, Map<String, String>>();
            String es_fileds = json.getString("esFileds");          // 需要索引的字段
            String [] es_fileds_array = es_fileds.split(",");      // 根据“，”拆分成数组
            int len = es_fileds_array.length;

            //循环设值
            for(int i = 0; i < len; i++){
                Map<String, String> map = new HashMap<String, String>();
                map.put("index", "not_analyzed");
                map.put("type", "string");
                mappings.put(es_fileds_array[i], map);
            }

            esUtil.putMapping(client, name, name, mappings);
        }else{
             return name + ResultEnum.INDEX_ISEXIST.getValue();
        }

        //创建hbase表
        if(!hbaseUtil.isTableExists(name)){
            hbaseUtil.createTable(name);
        }else{
            return name + ResultEnum.TABLE_ISEXIST.getValue();
        }

        return ResultEnum.INDEX_TABLE_CREATE_SUCCESS.getValue();
    }

    @Override
    public String updateDatasToEsAndHbase(JSONObject json) throws Exception{
        //需要修改的地方
        String name = json.getString("tableName");
        if(name == null || name.isEmpty()){
            throw new Exception("hbase table is null");
        }
        //不支持row
        String rowKey = null;
        rowKey = json.getString("id");
        int flag = 0;   // 表示修改
        if (null != rowKey && 0 == flag){
          if(!hbaseUtil.isRowExists(name,rowKey)){
              throw new Exception("row is not exists");
          }
        }else{
            throw new Exception("not allow update ");
        }
        ///////////////////////////////////////////////////////
        //如果接受到的数据有rowKey，则更新数据，否则生成新的rowKey
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        JSONObject esJson = json.getJSONObject("esJson");
        Map map = (Map) esJson;
        map.put("id",rowKey);
        listMap.add(map);

        // es索引中新增数据
        esUtil.bulkIndex(client, name, name, "id", listMap);

        //hbase表中新增数据
        HTable table = hbaseUtil.getTable(name);
        JSONObject hbaseJson = json.getJSONObject("hbaseJson");

        hbaseUtil.putData(table, "cf", rowKey, hbaseJson);

        return ( ResultEnum.MODIFY_SUCCESS.getValue());
    }

    /**
     * 新增数据到es和hbase
     * @param json
     * @return
     * @throws Exception
     */
    @Override
    public String addDatasToEsAndHbase(JSONObject json) throws Exception{
        //需要修改的地方
        String name = json.getString("tableName");
        if(name == null || name.isEmpty()){
            throw new Exception("hbase table is null");
        }
       //不支持row
        String rowKey = null;
        rowKey = json.getString("id");
          // 表示修改

        if ((null == rowKey ||rowKey.isEmpty())){
            do {
             //   flag = 1;   // 表示新增
                rowKey = UUID.randomUUID().toString();           // rowKey
            }while(hbaseUtil.isRowExists(name,rowKey));
        }else{
            throw new Exception("row key is not self create ");
        }
        //如果接受到的数据有rowKey，则更新数据，否则生成新的rowKey
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        JSONObject esJson = json.getJSONObject("esJson");
        Map map = (Map) esJson;
        map.put("id",rowKey);
        listMap.add(map);

        // es索引中新增数据
        esUtil.bulkIndex(client, name, name, "id", listMap);

        //hbase表中新增数据
        HTable table = hbaseUtil.getTable(name);
        JSONObject hbaseJson = json.getJSONObject("hbaseJson");

        hbaseUtil.putData(table, "c1", rowKey, hbaseJson);

        return (ResultEnum.SAVE_SUCCESS.getValue());
    }

    /**
     * 查询
     * @param query     查询条件
     * @param pageNum   页码
     * @param pageSize  每页显示多少数据
     */
    @Override
    public List<JSONObject> queryFromEsAndHbase(String query, int pageNum, int pageSize) throws Exception{

        JSONObject json = JSONObject.parseObject(query);
        String indexName = json.getString("tableName");

        JSONObject contions = (JSONObject) json.get("conditions");
        Map map = (Map) contions;
        Iterator entries = map.entrySet().iterator();

        //es 查询
        List<String> listData = esUtil.matchMorePhrase(client,indexName,entries,pageNum,pageSize);

        //hbase 查询
        List<Get> listGet = new ArrayList<Get>();

        for(String rowKey : listData){
            Get getRecord = new Get(Bytes.toBytes(rowKey));
            listGet.add(getRecord);
        }

        List<JSONObject> list = null ; //hbaseUtil.getByGetList(indexName,listGet);

        return list;
    }

    /**
     * 根据id删除es索引和hbase表
     * @param tableName
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public String deleteEsAndHbase(String tableName,String id) throws Exception {

        //es索引删除
        esUtil.deleteDoc(client,tableName,tableName,id);

        //hbase删除
        hbaseUtil.deleteRow(tableName,id);

        return ResultEnum.DELETE_SUCCESS.getValue();
    }
}

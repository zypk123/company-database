package com.huntkey.rx.sceo.serviceCenter.provider.orm.tools;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.serviceCenter.common.emun.EdmType;
import com.huntkey.rx.sceo.serviceCenter.common.emun.TableType;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import com.huntkey.rx.sceo.serviceCenter.provider.client.EDMClient;
import com.huntkey.rx.sceo.serviceCenter.provider.core.ServiceParse;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.config.PersistanceConstant;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.util.DBUtil;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.util.EsUtil;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.util.HbaseUtil;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.elasticsearch.client.transport.TransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by linziy on 2017/8/30.
 * elasticsearch 工具类
 */
@Service
public class EsTool {

    private static Logger logger = LoggerFactory.getLogger(EsTool.class);

    @Autowired
    private HbaseUtil hbaseUtil; // hbase 工具类
    @Autowired
    private EsUtil esUtil; // es 工具类
    @Autowired
    private TransportClient client; // es 客户端
    @Autowired
    private EDMClient edmClient;
    @Autowired
    private ServiceParse serviceParse;
    /**
     * 获取link 表的es 属性: linkagedetail 类中获取属性
     * @return
     */
    List<String> getLinkEsFields(){
        List<String> list = new ArrayList<>();
        //获取linkagedetail 的属性


        return list;
    }

    public  Map<String, Map<String, String>>  getEsColumnsMapping(String edmdVer,String edmNameStr){
       // if (edmNameStr.isEmpty()){
       //     throw new IllegalArgumentException("EdmName is empty!");
        //}
        Map<String, Map<String, String>> settingMaps = new HashMap<>();
        ////////////////////////////////////////////////////////////////////////////////////////////
        //1.添加系统字段的map
        Map<String,String> keyWordMap = new HashMap<>();
        keyWordMap.put("type","keyword");
        settingMaps.put("addtime",keyWordMap);
        settingMaps.put("adduser",keyWordMap);
        settingMaps.put("modtime",keyWordMap);
        settingMaps.put("moduser",keyWordMap);
        //2.根据表的类型来填写
        //获取主表或者子表的索引
        //0.判断edmNameStr 是主表或者子表或者link表
        String[] edmList = edmNameStr.split("\\.");
        String edmName = edmList[0];
        if(edmNameStr.endsWith(PersistanceConstant.LINK_SUFFIX)){
            edmName = "linkagedetail";//link 表的字段属性改为从此类中
        }

        boolean isMainTable = true;
        if(1 != edmList.length){
            isMainTable = false;
            settingMaps.put(NodeConstant.PID,keyWordMap);//pid 索引必须存在
        }
        ///1.获取此类的list
        Result result = edmClient.getEdmDataType(edmdVer,edmName);
        Object object = result.getData();
        String str = object.toString();
        if (str.startsWith("[{") && str.endsWith("}]")){
            str = str.replaceAll("\\[\\{","");
            str = str.replaceAll("\\}\\]","");
        }
        /////////////////////////////////////////////////////////////////////////////////
        String[] strlist = str.split(",");
        for (String edmColumnIndex:strlist){
            JSONObject jsonObject = new JSONObject();
            String[] tmp = edmColumnIndex.split("=");
            //获取列名
            String attr = tmp[0].trim().toLowerCase();
            //判断attr是否本表的字段
            int pos = attr.indexOf(edmNameStr);
            if (pos == 0) {
                attr = attr.substring(edmNameStr.length() + 1);
                if (-1 != attr.indexOf(".")) {
                    //子表下的属性集字段
                    continue;
                }
            } else {
                //非此子表的字段
                continue;
            }

            //类型dataType
            String dataType = tmp[1];
            Map<String, String> columnsSetting = new HashMap<>();
            if (EdmType.EDM_INT.getDataType().equals(dataType)) {
                columnsSetting.put("type", "integer");
            } else if (EdmType.EDM_FLOAT.getDataType().equals(dataType) || EdmType.EDM_DECIMAL.getDataType().equals(dataType)) {
                columnsSetting.put("type", "float");
            } else if (EdmType.EDM_LONG.getDataType().equals(dataType)) {
                columnsSetting.put("type", "long");
                //      columnsSetting.put("nullvalue","0");
            } else if (EdmType.EDM_ASSEMBLE.getDataType().equals(dataType)) {
                continue;
//                }else if(EdmType.EDM_DATE.getDataType().equals(dataType)){
//                    columnsSetting.put("type","date");
//                    columnsSetting.put("format","yyyy-MM-dd");
            } else {
                columnsSetting.put("type", "keyword");
            }
            settingMaps.put(attr, columnsSetting);
        }
      //  }
        return settingMaps;
    }

    /**
     * 根据edm 返回的字段类型创建es
     * @param edmdVer
     * @param edmName
     */
    public void createIndexAndMapping(String edmdVer,String edmName){
        if (edmName.isEmpty()){
            String errMsg = "edmName is empty!";
            logger.error(errMsg);
            throw new IllegalArgumentException(errMsg);
        }
        String index = edmName;
        Map<String, Map<String, String>> settingMaps = getEsColumnsMapping(edmdVer,edmName);
        //////////组织es mapping//////////////////////////////////////////////////////////////////
   //     String testIndex = "lex_index_mapping";
   //     index = testIndex;
        try{
            //edmName 转为表名
            // 如果是_link_records 结尾的edmName参数，不需要根据简称获取物理表名称
            String tableName = null;
            if (edmName.endsWith(PersistanceConstant.LINK_SUFFIX)) {
                tableName = edmName;
            }else{
                tableName = DBUtil.getTableName(edmName,serviceParse.getShortName(edmName));
            }
            esUtil.deleteIndex(client,tableName);
            esUtil.setIndexMapping(client,tableName,"post",settingMaps);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * hbase 同步数据到es
     * @param edmdVer
     * @param edmName
     */
    public void synchrodataFromHbase(String edmdVer,String edmName) throws Exception{

        //1.根据edm name 获取表名
        String tableName = DBUtil.getTableName(edmName,serviceParse.getShortName(edmName));
        if (!esUtil.indexExists(client, tableName)) {
            String errMsg ="The index named ["+tableName+"] is not exists!";
            logger.error(errMsg);
            throw new IllegalArgumentException(errMsg);
        }
        //2.表的类型
        TableType tableType = TableType.getTableType(edmName);
        //3.获取es 索引
        List<String> fieldsList = null;
        if (TableType.LINK_TABLE != tableType){
            Map<String, Object> map = (Map<String, Object>) edmClient.getOrmIndexs(edmdVer, edmName).getData();
            fieldsList = (List<String>) map.get(edmName);
            if (TableType.SON_TABLE == tableType){
                fieldsList.add(NodeConstant.PID);
            }
        }else{
            fieldsList = getLinkEsFields();
        }
        ///////////////添加系统字段////////////////////////////////
        fieldsList.add("addtime");
        fieldsList.add("adduser");
        fieldsList.add("modtime");
        fieldsList.add("moduser");
        ///////////////////////////////////////////////////////////
        //4.从hbase 中获取数据
        JSONArray hbaseJsonArray = null;
        hbaseJsonArray = GetHbaseData(tableName);

        //5.导入数据
        List<Map<String, Object>> esListMap = new ArrayList<Map<String, Object>>();
        int i = 0;
        int maxSize = hbaseJsonArray.size();
        Iterator<Object> iters = hbaseJsonArray.iterator();
        while (iters.hasNext()) {
            JSONObject jsonObject = (JSONObject) iters.next();
            String rowKey = jsonObject.getString("id");
            //组织插入到json 里面的数据
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", rowKey);
            for (String f : fieldsList) {
                String str = jsonObject.getString(f);
                //非null 字符串进es
                if(!"null".equals(str)){
                    map.put(f,str);
                }
            }
            esListMap.add(map);
            //////////批量提交数据////////////////////////////////////////////////////////////////////////////
            i ++;
            int c = 0;
            if(i == maxSize ){
                c = i / 10000 > 0 ? (i - 10000 * (i / 10000)) : i;
            }

            // es每1w笔数据提交一次
            if (i > 1 && i % 10000 == 0 || c > 0) {
                // 批量提交数据到es
                esUtil.bulkIndex(client, tableName, tableName, "id", esListMap);
                esListMap.clear();
            }
            ///////////////////////////////////////////////////////////////////////////////
        }
        //////////数据导入完毕////////////////////////////////////////////////////////
        return ;
    }

    /**
     * 扫描 Hbase 某表数据
     * @param tableName
     * @return
     * @throws Exception
     */
    private JSONArray GetHbaseData(String tableName) throws Exception {
        HTable table = hbaseUtil.getTable(tableName);
        Scan scan = new Scan();
        ResultScanner rs = table.getScanner(scan);

        String column; // 列名
        String columVal; // 列值
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = null;

        for (org.apache.hadoop.hbase.client.Result r : rs) {
            //对每条数据进行返回
            jsonObject = new JSONObject();
            jsonObject.put("id", Bytes.toString((r.getRow())));
            for (Cell cell : r.rawCells()) {
                column = Bytes.toString(CellUtil.cloneQualifier(cell));
                columVal = Bytes.toString(CellUtil.cloneValue(cell));
                jsonObject.put(column, columVal);
            }
            jsonArray.add(jsonObject);
        }
        rs.close();
        return jsonArray;
    }

    /**
     *  根据索引字段获取es
     * @param tableName
     * @param fieldsArray
     * @return
     * @throws Exception
     */

    private JSONArray GetHbaseDataByEsFields(String tableName, String[] fieldsArray) throws Exception {
        HTable table = hbaseUtil.getTable(tableName);
        Scan scan = new Scan();
        ResultScanner rs = table.getScanner(scan);
        String column; // 列名
        String columVal; // 列值

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = null;

        for (org.apache.hadoop.hbase.client.Result r : rs) {
            jsonObject = new JSONObject();
            jsonObject.put("", Bytes.toString((r.getRow())));

            for (Cell cell : r.rawCells()) {
                column = Bytes.toString(CellUtil.cloneQualifier(cell));

                for (String f : fieldsArray) {
                    if (f.equals(column)) {
                        columVal = Bytes.toString(CellUtil.cloneValue(cell));
                        jsonObject.put(column, columVal);
                    }
                }
            }

            jsonArray.add(jsonObject);
        }

        rs.close();

        return jsonArray;
    }

    public String getTableName(String edmName){
        String tableName = null;
        if (edmName.endsWith(PersistanceConstant.LINK_SUFFIX)) {
            tableName = edmName;
        }else{
            tableName = DBUtil.getTableName(edmName,serviceParse.getShortName(edmName));
        }
        return tableName;
    }
}

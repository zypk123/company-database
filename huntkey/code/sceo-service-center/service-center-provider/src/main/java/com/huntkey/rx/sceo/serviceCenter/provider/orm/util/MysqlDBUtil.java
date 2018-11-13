package com.huntkey.rx.sceo.serviceCenter.provider.orm.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.serviceCenter.common.emun.TableType;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import com.huntkey.rx.sceo.serviceCenter.provider.core.vallidate.MysqlDataValidate;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.config.PersistanceConstant;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.model.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by zhanglb on 2017/6/26.
 */
public class MysqlDBUtil {

    private static Logger logger = LoggerFactory.getLogger(MysqlDBUtil.class);

    /**
     * 获取richfind的map
     * @param criteria
     * @return
     */
    public static Map<String, String> getRichFindMap(Criteria criteria){
        //
        Map<String, String> map = new HashMap<>();
        //1.获取表名
        String tableName =criteria.getTableName();

        //2.查询的列
        String columns = "";
        JSONArray columnArray = criteria.getColumns();
        if(!StringUtil.isNullOrEmpty(columnArray)){
            for(Object column : columnArray){
                columns += column.toString() + ",";
            }
            columns = columns.substring(0, columns.length()-PersistanceConstant.P_ONE);
        }

        if(StringUtil.isNullOrEmpty(columns)){
            columns = " * ";
        }

        //3.查询条件
        String condition = criteria.getConditionsInWhere();
        if (StringUtil.isNullOrEmpty(condition)){
            condition = "1 = 1";
        }
        //添加 className
        String edmName =criteria.getEdmName();
        int pos = edmName.indexOf(".");
        if ( -1 != pos) {
            //属性
            String className = edmName.substring(0,pos);
            condition = condition + " AND classname = '"+className +"'";
        }

        //4.order by
        String orderStr = "";
        JSONArray orderArray = criteria.getOrderBy();
        if(!StringUtil.isNullOrEmpty(orderArray)){
            Iterator<Object> iterator = orderArray.iterator();
            while(iterator.hasNext()){
                Map<String, Object> orderMap = (Map<String, Object>)iterator.next();
                String column = orderMap.get(NodeConstant.ATTR).toString();
                String sort = orderMap.get(NodeConstant.SORT).toString();
                orderStr += column + " " + sort + ",";
            }
            if(!StringUtil.isNullOrEmpty(orderStr)){
                orderStr = orderStr.substring(0, orderStr.length() - PersistanceConstant.P_ONE);
                orderStr = "ORDER BY " +orderStr;
            }
        }
        //分页
        JSONObject jsonObject = criteria.getPagenation();

        if (jsonObject != null  ){
          int page =  jsonObject.getInteger(NodeConstant.START_PAGE);
          int rows = jsonObject.getInteger(NodeConstant.ROWS);
          int starRow = (page - 1) * rows;
          String limitStr = String.format("%d,%d",starRow,rows);
          map.put(PersistanceConstant.P_LIMIT, limitStr);
        }

        //组装需要传递进去的map
        map.put(PersistanceConstant.TABLE_NAME, tableName);
        map.put(PersistanceConstant.P_COLUMNS, columns);
        map.put(PersistanceConstant.P_CONDITION, condition);
        map.put(PersistanceConstant.ORDER_BY_CONDITION, orderStr);


        return map;
    }

    /**
     * 获取插入  * @param criteria
     * @return
     */
    public static List<Map<String, Object>> getInsertDatas(Criteria criteria){
        List<Map<String, Object>> mapList = new ArrayList<>();

        //获取表名
        String tableName = criteria.getTableName();
        //获取需要插入表的data
        JSONObject jsonData = criteria.getData();
        //解析jsonData数组拼sql
        JSONArray jsonArray = criteria.getDataset();
        //循环jsonArray
        Iterator<Object> iterator = jsonArray.iterator();
        while(iterator.hasNext()){
            String value = "";
            String columns = "";
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> dataMap = (Map<String, Object>)iterator.next();
            value += "(";
            for(String key: dataMap.keySet()){
                if (MysqlDataValidate.DEL_COLUMNS.equals(key)){
                    continue;
                }
                if("id".equals(key)){
                    String id = dataMap.get(key).toString();
                    value += "'" + id + "'" + ",";
                    map.put("id", id);
                    columns += key + ",";
                }else{
                    Object tmpObj = dataMap.get(key);
                    if (tmpObj != null){
                        value += "'" + tmpObj.toString() + "'" + ",";
                        columns += key + ",";
                    }
                }
            }
            columns = columns.substring(0, columns.length() - PersistanceConstant.P_ONE);
            value = value.substring(0, value.length()-PersistanceConstant.P_ONE);
            value = value + ")";

            map.put(PersistanceConstant.TABLE_NAME, tableName);
            map.put(NodeConstant.COLUMNS, columns);
            map.put(PersistanceConstant.P_DATA, value);
            mapList.add(map);
        }

        return mapList;
    }

    /**
     * 获取查询的map
     * @param criteria
     * @return
     */
    public static Map<String,String> getCountMap(Criteria criteria) {
        Map<String,String> map = new HashMap<>();

        //获取表名
        String tableName =criteria.getTableName();
        //获取查询条件
        String condition = criteria.getConditionsInWhere();
        if (condition == null || condition.isEmpty()){
            JSONArray array = criteria.getConditions();
            if (array != null && array.size() != 0){
                condition = MysqlDBUtil.getCondition(array);
            }
        }

        //添加 className
        String edmName =criteria.getEdmName();
        int pos = edmName.indexOf(".");
        if ( -1 != pos) {
            //属性
            String className = edmName.substring(0,pos);
            condition = condition + " AND classname = '"+className +"'";
        }

        //组装需要传递进去的map
        map.put(PersistanceConstant.TABLE_NAME, tableName);
        if (condition != null && !condition.isEmpty()){
            map.put(PersistanceConstant.P_CONDITION, condition);
        }
        return map;
  }

    /**
     * 获取查询的Map
     * @param criteria
     * @return
     */
    public static Map<String,Object> getSelectMap(Criteria criteria) {
        Map<String, Object> map = new HashMap<String, Object>();
        //获取表名
        String tableName =criteria.getTableName();
        //添加 className
        String edmName =criteria.getEdmName();
        String condition = null;
        int pos = edmName.indexOf(".");
        if ( -1 != pos) {
            //属性
            String className = edmName.substring(0,pos);
            condition =  "classname = '"+className +"' ";
        }

        //查询条件
        String conditionInput = getCondition(criteria.getConditions());
        //如果条件为空或者没有传值
        if(!StringUtil.isNullOrEmpty(conditionInput)){
            if (condition == null || condition.isEmpty()){
                condition = conditionInput;
            }else{
                condition  = condition + " AND " + conditionInput;
            }
        }

        //查询的列
        String columns = "";
        JSONArray columnArray = criteria.getColumns();
        if(!StringUtil.isNullOrEmpty(columnArray)){
            for(Object column : columnArray){
                columns += column.toString() + ",";
            }
            columns = columns.substring(0, columns.length()-PersistanceConstant.P_ONE);
        }

        if(StringUtil.isNullOrEmpty(columns)){
            columns = " * ";
        }

        //取排序字段
        String orderStr = "";
        JSONArray orderArray = criteria.getOrderBy();
        if(!StringUtil.isNullOrEmpty(orderArray)){
            Iterator<Object> iterator = orderArray.iterator();
            while(iterator.hasNext()){
                Map<String, Object> orderMap = (Map<String, Object>)iterator.next();
                String column = orderMap.get("attr").toString();
                String sort = orderMap.get("sort").toString();
                orderStr += column + " " + sort + ",";
            }
            if(!StringUtil.isNullOrEmpty(orderStr)){
                orderStr = orderStr.substring(0, orderStr.length()-PersistanceConstant.P_ONE);
                orderStr = "ORDER BY " +orderStr;
            }
        }
        map.put(PersistanceConstant.TABLE_NAME, tableName);
        map.put(PersistanceConstant.P_CONDITION, condition);
        map.put(PersistanceConstant.P_COLUMNS, columns);
        map.put(PersistanceConstant.ORDER_BY_CONDITION, orderStr);

        return map;
    }

    /**
     * 获取删除的map
     * @param criteria
     * @return
     */
    public static Map<String,Object> getDeleteMap(Criteria criteria) {
        Map<String, Object> map = new HashMap<String, Object>();
        //获取表名
        String tableName = criteria.getTableName();
        //查询条件
        String condition = getCondition(criteria.getConditions());
        map.put(PersistanceConstant.TABLE_NAME, tableName);
        map.put(PersistanceConstant.P_CONDITION, condition);
        map.put(PersistanceConstant.SET_CONDITION, PersistanceConstant.SET_DELETE_CONDITION);
        return map;
    }


    private static String getValueForInOperator(String inValue) {
        String[] valueArray = inValue.split(",");
        String result = "";
        for (String v:valueArray){
            v.trim();
            if (v.startsWith("'")&& v.endsWith("'") || v.startsWith("\"") && v.endsWith("\"")){
                v = v.substring(1,v.length()-1);
            }
            result += "," + "'" + v + "'";
        }
        result = result.replaceFirst(",","");
        return result;
    }

    /**
     * 这个只能解析and 的关系的条件
     * @param jsonArray
     * @return
     */
    public static String getCondition(JSONArray jsonArray){
        String condition = "";
        if(!StringUtil.isNullOrEmpty(jsonArray) && jsonArray.size() > 0) {
            Iterator<Object> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                Map<String, Object> m = (Map<String, Object>) iterator.next();
                if (!StringUtil.isNullOrEmpty(m)) {
                    String attrValue = m.get(NodeConstant.ATTR).toString();
                    String operatorValue = m.get(NodeConstant.OPERATOR).toString();
                    String value = m.get(NodeConstant.VALUE).toString();
                    String oneCondition = null;
                    if (PersistanceConstant.LIKE.equals(operatorValue)) {
                        operatorValue = "LIKE";
                        value = value.replaceAll("\\%", "\\\\%");
                        value = "%" + value + "%";
                        oneCondition = attrValue + " " + operatorValue + " '" + value + "'";
                    }else if(PersistanceConstant.LLIKE.equals(operatorValue)){
                        operatorValue = "LIKE";
                        value = value.replaceAll("\\%", "\\\\%");
                        value = "%" + value ;
                        oneCondition = attrValue + " " + operatorValue + " '" + value + "'";
                    }else if(PersistanceConstant.RLIKE.equals(operatorValue)){
                        operatorValue = "LIKE";
                        value = value.replaceAll("\\%", "\\\\%");
                        value = value + "%";
                        oneCondition = attrValue + " " + operatorValue + " '" + value + "'";
                    }
                    else if ("in".equals(operatorValue)) {
                        operatorValue = "IN";
                        value = getValueForInOperator(value);
                        oneCondition = attrValue + " " + operatorValue + " (" + value + ")";
                    } else if ("!in".equals(operatorValue)) {
                        operatorValue = " NOT IN";
                        value = getValueForInOperator(value);
                        oneCondition = attrValue + operatorValue + " (" + value + ")";
                    } else if (PersistanceConstant.IS.equals(operatorValue)) {
                        if (PersistanceConstant.NULL.equals(value)){
                            oneCondition = attrValue + PersistanceConstant.ISNULL;
                        }else{
                            throw new IllegalArgumentException("There is something wrong for the value of the operator 'is'.");
                        }

                    } else if ("!is".equals(operatorValue)){
                        if (PersistanceConstant.NULL.equals(value)) {
                            oneCondition = attrValue + PersistanceConstant.ISNOTNULL;
                        }else{
                            throw new IllegalArgumentException("There is something wrong for the value of the operator '!is'.");
                        }
                    }else {
                        oneCondition = attrValue + " " + operatorValue + " '" + value + "'";
                    }
                    condition += oneCondition + PersistanceConstant.P_AND;
                }
            }
        }
        if(!StringUtil.isNullOrEmpty(condition)){
            //去掉最后的 AND
            condition = condition.substring(0, condition.length()-PersistanceConstant.P_FOUR);
        }
        return condition;
    }

    public static List<Map<String, Object>> getUpdateMap(Criteria criteria) {

        List<Map<String, Object>> list = new ArrayList<>();

        JSONArray jsonArray = criteria.getDataset();
        //循环jsonArray
        Iterator<Object> iterator = jsonArray.iterator();
        while(iterator.hasNext()){
            Map<String, Object> map = new HashMap<>();
            //获取表名
            String tableName = criteria.getTableName();
            String condition = "";

            //update 语句set 后面的更新内容
            String setCondition = "";
            Map<String, Object> dataMap = (Map<String, Object>)iterator.next();
            for(String key: dataMap.keySet()){
                //过滤字段DEL_COLUMNS
                if (MysqlDataValidate.DEL_COLUMNS.equals(key)){
                    continue;
                }

                if(PersistanceConstant.ID.equals(key)){
                    condition = PersistanceConstant.ID + PersistanceConstant.EQUALS + "'" + dataMap.get(key).toString() + "'";
                }else{
                    setCondition += key + PersistanceConstant.EQUALS + "'" + dataMap.get(key).toString() + "'" + ",";
                }
            }
            //去掉最后一个逗号
            setCondition = setCondition.substring(0, setCondition.length()-PersistanceConstant.P_ONE);

            map.put(PersistanceConstant.TABLE_NAME, tableName);
            map.put(PersistanceConstant.P_CONDITION, condition);
            map.put(PersistanceConstant.SET_CONDITION, setCondition);
            list.add(map);
        }

        return list;
    }

/*    public static void main(String[] args){
        JSONArray jsonArray = new JSONArray();

        String jsonMsg = "{ \"attr\": \"id\",\n" +
                "        \"operator\": \"=\",\n" +
                "        \"value\": \"1\"\n" +
                "      }";
        String jsonMsg1 = "{ \"attr\": \"sid\",\n" +
                "        \"operator\": \"=\",\n" +
                "        \"value\": \"1\"\n" +
                "      }";

        JSONObject jsonObject = JSONObject.parseObject(jsonMsg);
        jsonArray.add(jsonObject);

        JSONObject jsonObject1 = JSONObject.parseObject(jsonMsg1);
        jsonArray.add(jsonObject1);

        Iterator<Object> iterator = jsonArray.iterator();
        String condition = "";
        while (iterator.hasNext()) {
            Map<String, Object> m = (Map<String, Object>)iterator.next();
            String attrValue = m.get("attr").toString();
            String operatorValue = m.get("operator").toString();
            String value = m.get("value").toString();
            System.out.println("-----------attrValue="+attrValue);
            condition += attrValue + " " + operatorValue + " '" + value + "'" + PersistanceConstant.P_AND;
            if("id".equals(attrValue)){
                condition = attrValue + " " + operatorValue + " '" + value + "'" + PersistanceConstant.P_AND;
                break;
            }
            System.out.println("-----------");
        }
        //去掉最后的 AND
        condition = condition.substring(0, condition.length()-PersistanceConstant.P_FOUR);

        System.out.print(condition);
    }*/


}

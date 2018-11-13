package com.huntkey.rx.sceo.serviceCenter.provider.orm.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.sceo.serviceCenter.common.emun.ConditionsEnum;
import com.huntkey.rx.sceo.serviceCenter.common.emun.SqlKeywordEnum;
import com.huntkey.rx.sceo.serviceCenter.common.emun.SqlOperatorRegexEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by linziy on 2017/8/25.
 */
public class SqlConditionAnalyze {
    private static Logger logger = LoggerFactory.getLogger(SqlConditionAnalyze.class);

    private String conditionsSegment;
    ////////////////////////////sql 特殊符号///////////////////////////////////////
    private final static String SPACE_REGEX = "[\\s]+";
    private final static String LEFT_PARENTHESIS = "\\(";
    private final static String RIGHT_PARENTHESIS = "\\)";
    //private final static String Single_Colon = "\\'"; //单引号
    private final static String DOUBLE_COLON = "\""; //双引号 预查询
    /////////////////////////////////////////////////////////////////////
    private final static String ANY_SPACE_REGEX = "[\\s]{0,}";//一个或者多个空格
    //////////////////////////////条件关系/////////////////////////////////////////////////////////////////////////
    private final static String AND_REGEX =  "("+SPACE_REGEX + "and" + SPACE_REGEX + ")";
    private final static String OR_REGEX =  "("+SPACE_REGEX + "or" + SPACE_REGEX + ")";
    private final static String NOT_REGEX =  "("+"^not(" + SPACE_REGEX + "|"+ANY_SPACE_REGEX +LEFT_PARENTHESIS+"))";// not 前面不带空
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private final static String[] CONDITIONS_REGEX_ARRAY = {
            AND_REGEX ,OR_REGEX,                              //and or not 关系操作符
            LEFT_PARENTHESIS,RIGHT_PARENTHESIS,DOUBLE_COLON                     //特殊符号
    };
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    public SqlConditionAnalyze(String conditions){
        this.conditionsSegment = conditions;
    }
    /**
     * 运算符正则匹配
     * @return
     */
    private String getOperatorRegex(){
        String[] symbolList = {
                SqlOperatorRegexEnum.Like.getOperator(),SqlOperatorRegexEnum.NOTEQUALS.getOperator(),
                SqlOperatorRegexEnum.LTE.getOperator(),SqlOperatorRegexEnum.LT.getOperator(),
                SqlOperatorRegexEnum.GTE.getOperator(),SqlOperatorRegexEnum.GT.getOperator(),
                SqlOperatorRegexEnum.EQUALS.getOperator(),
                SqlOperatorRegexEnum.NOTIN.getOperator(),SqlOperatorRegexEnum.IN.getOperator(),
                SqlOperatorRegexEnum.NOTNULL.getOperator(),SqlOperatorRegexEnum.ISNULL.getOperator()
        };
        //1. attr is not null
        //2. attr not in(value1,value2,value3)
        //3. and not attr operator value
        String operatorRegex = "";
        for (String str : symbolList){
            operatorRegex = operatorRegex + "|" + str;
        }
        operatorRegex = operatorRegex.replaceFirst("\\|","");
        operatorRegex = "(" + operatorRegex + ")";
        return operatorRegex;
    }

    //////////////////正则相关函数///////////////////////////////////////////////////
    private String getConditionsRegex(){
        String regex = "";
        for (String str: CONDITIONS_REGEX_ARRAY) {
            regex += str + "|";
        }
        regex = regex.substring(0,regex.length() - 1);
        return regex;
    }
    /**
     * 正则匹配且截取
     * @param regex //正则匹配
     * @param src  //字符串源
     * @return
     */
    private String getMatchedOneString(String regex,String src){
        String result = null;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(src);
        if (m.find()){
            result =  m.group();
        }
        return result;
    }

    /**
     * 获取多个匹配的结果
     * @param regex
     * @param src
     * @return
     */
    private List<String> getMatchedMultipleString(String regex, String src){
        List<String> list = new ArrayList<String>();
        String result = null;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(src);
        while(m.find()) {
            result =  m.group();
            list.add(result);
        }
        return list;
    }

    /**
     *  正则查询字符串中是否包含某串数据
     * @param regex
     * @param src
     * @return
     */
    protected static boolean isContains(String src,String regex){
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(src);
        return matcher.find();//部分匹配
    }

    /**
     *
     * @param src
     * @param regex
     * @return
     */
    protected static boolean isMatch(String src,String regex){
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(src);
        return matcher.matches();//全部匹配
    }
    ////////////////////////////////////////////////////////////////////////
    /**
     * 分析条件: 入口
     * @return
     */
    public JSONObject SqlConditionAnalyze() {
        JSONObject result = new JSONObject();
        if (isContains(this.conditionsSegment, AND_REGEX + "|" + OR_REGEX)) {
            //多条件sql 语句
            return sqlConditionsAnalysis(this.conditionsSegment);
        } else {
            //单条件 sql 语句
            JSONObject jsonObject = formatConditons(this.conditionsSegment);
            int type = jsonObject.getInteger("type");
            String conditions = jsonObject.getString("res");

            JSONObject oneCondition = setSingleObject(conditions);
            if (type == ConditionsEnum.CONDTIONS_NOT.getType()) {
                result.put("not", oneCondition);
            } else {
                JSONArray orArray = new JSONArray();
                orArray.add(oneCondition);
                result.put("or", orArray);
            }
            return result;
        }
    }

    /**
     * 去掉外部的()
     * @param conditions
     * @return
     */
    private String removeExternal(String conditions){
        conditions = conditions.trim();
        while (conditions.startsWith("(") && conditions.endsWith(")")){
            String regex =  LEFT_PARENTHESIS + "|" + RIGHT_PARENTHESIS;
            List<String> list = getMatchedMultipleString(regex,conditions);
            //判断最外围的是否配套
            int len = list.size();
            int layer = 1;
            int i = 1;
            for ( ; i < len ; i++){
                String sign = list.get(i);
                if(isMatch(sign,RIGHT_PARENTHESIS)){
                    layer -- ;
                }else if (isMatch(sign,LEFT_PARENTHESIS)){
                    layer ++;
                }
                if (0 == layer){
                    break;
                }
            }
            if (layer == 0 && i == len -1 ){
                conditions = conditions.substring(1,conditions.length() - 1);
                conditions = conditions.trim();
            }else{
                break;
            }
        }
        return conditions;
    }
    /**
     * 对传入的conditions 进行化简和规格化
     * @return
     */
    private JSONObject formatConditons( String conditions ){
        //去除左右两个括号
        conditions = conditions.trim();
        ConditionsEnum conditionsEnum = null;
        String regexNot = NOT_REGEX + ".+";
        if (isMatch(conditions,regexNot)){
            //条件以not 开头
            conditions = conditions.substring(3);
            conditions =  removeExternal(conditions);
            conditionsEnum =  ConditionsEnum.CONDTIONS_NOT;
        } else{
            //条件不以not 开头
            if (!isContains(conditions,OR_REGEX) && !isContains(conditions,AND_REGEX)){
                //单个条件
                conditionsEnum =  ConditionsEnum.CONDTIONS_SINGLE;
                // is not null && not in
                conditions =  removeExternal(conditions);
            }else if (isContains(conditions,OR_REGEX) && isContains(conditions,AND_REGEX)){
                //混合格式,只能去掉前后的()
                conditionsEnum =  ConditionsEnum.CONDTIONS_MIXTRUE;
                conditions =  removeExternal(conditions);
            }else if(isContains(conditions,OR_REGEX)){
                //纯or 关系,去除所有的()
                conditionsEnum = ConditionsEnum.CONDTIONS_OR;
                String rpRegex = LEFT_PARENTHESIS +"|"+ RIGHT_PARENTHESIS;
                conditions = conditions.replaceAll(rpRegex,"");
            }else if(isContains(conditions,AND_REGEX)){
                //纯 and 关系,去除所有的()
                conditionsEnum = ConditionsEnum.CONDTIONS_AND;
                String rpRegex =LEFT_PARENTHESIS +"|"+ RIGHT_PARENTHESIS;
                conditions = conditions.replaceAll(rpRegex,"");
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type",conditionsEnum.getType());
        jsonObject.put("res",conditions);
        return jsonObject;
    }
    /**
     * 对一层sql 进行分解嵌套
     * @param conditions
     * @return
     * @throws Exception
     */
    private JSONObject sqlConditionsAnalysis(String conditions) {
        JSONObject result = conditionPartSplit(conditions);//not 进入此函数后会被替换掉了
        boolean isNot = !result.getBoolean("boolean");
        JSONArray jsonArray = result.getJSONArray("list");
        List<String> list = jsonArray.toJavaList(String.class);
        ///////////////////////////////////////////////////////////////////////////////////////
        JSONObject tmpJSONObject =  getOrmJson(list);
        JSONObject jsonObject = new JSONObject();
        if (isNot){
            jsonObject.put("not",tmpJSONObject);
        }else{
            jsonObject = tmpJSONObject;
        }
        return jsonObject;
    }


    /**
     *  对 conditions 进行数据分割处理
     * @param conditionsPart
     * @return
     * @throws Exception
     */
    private JSONObject conditionPartSplit(String conditionsPart) {
        JSONObject resultJsonObject = new JSONObject();
        List<String> resultList = new ArrayList<String>();
        String regex = getConditionsRegex();
        String  conditions = conditionsPart;
        JSONObject _jsonObject = formatConditons(conditions);
        conditions = _jsonObject.getString("res");
        List<String> list = getMatchedMultipleString(regex,conditions);
        int len = list.size();
        for (int j = 0;j<len;j++){
            System.out.println(Integer.valueOf(j).toString() + ":"+list.get(j));
        }
        //根据分割符号来查找和截取数据
        for(int i = 0;i < len; i++){
            String _str = list.get(i);
            int pos = conditions.indexOf(_str);
            if (pos == -1){
                System.out.println("_str:"+ _str+"conditions:"+ conditions);
            }
            String pre =  conditions.substring(0,pos);
            if (isMatch(_str,DOUBLE_COLON)){
                int _start = pos + 1;
                //冒号
                //查找闭合符号
                i = getClosedSymbol(DOUBLE_COLON,i+1,list);
                if ( -1 == i){
                    throw new IllegalArgumentException("it can't find the closed symbol by ["+_str+"]");
                }
                //字符串
                pos = conditions.indexOf(list.get(i),_start);
                String _oneCondition =  conditions.substring(0,pos+1);
                resultList.add(_oneCondition);
                conditions = conditions.substring(pos+ 1);
            }else if(isMatch(_str,AND_REGEX + "|" + OR_REGEX)){
                //and || or
                //判断左边是否有值
                String _oneCondition = pre.trim();
                if (!_oneCondition.isEmpty()){
                    resultList.add(_oneCondition);
                    //   stack.push(_oneCondition);
                }
                resultList.add(_str);
                //stack.push(_str);
                conditions = conditions.substring(pos+ _str.length());
            }else if(isMatch(_str,LEFT_PARENTHESIS)){
                int layer = 1;
                //层次关系,截取最顶层的关系
                String _oneCondition = pre.trim();
                //////////////////////////////////////////////////////////////////
                int _start = pos + _str.length();
                int _pos = -1;
                //对嵌套结构,获取最顶层的layer
                while(layer != 0){
                    i++;
                    _str = list.get(i);
                    if(isMatch(_str,RIGHT_PARENTHESIS)){
                        layer -- ;
                    }else if (isMatch(_str,LEFT_PARENTHESIS)){
                        layer ++;
                    }
                    _pos = conditions.indexOf(_str,_start);
                    _start = _pos + _str.length();
                }
                if(layer != 0 ){
                    //不闭合
                    throw new IllegalArgumentException("it's not a closed region");
                }
                ////////////////////////////////////////////////////
                int end = _start;
                //截取闭合区间数据
                String closePart = conditions.substring(pos,end);
                if (!_oneCondition.isEmpty()){
                    //  throw new IllegalArgumentException("it's not null that left of (");
                    closePart = _oneCondition + closePart;
                }
                resultList.add(closePart);
                conditions =  conditions.substring(end);
            }
        }
        //条件为空
        if (!conditions.isEmpty()){
            resultList.add(conditions);
            //  stack.push(conditions);
        }

        //语法分析,对返回的list 做初步判断
        if (0 == resultList.size()%2){
            throw new IllegalArgumentException("The grammer of sql is error");
        }

        if (_jsonObject.getInteger("type") == ConditionsEnum.CONDTIONS_NOT.getType() ){
            resultJsonObject.put("boolean",false);
        }else{
            resultJsonObject.put("boolean",true);
        }

        JSONArray resultArray =  JSONArray.parseArray(JSON.toJSONString(resultList));
        resultJsonObject.put("list",resultArray);

        return resultJsonObject;
    }
    /**
     * and or 与 条件块的判断
     * @param relationString
     * @return
     */
    private SqlKeywordEnum getRelation(String relationString){
        if (isMatch(relationString,AND_REGEX)){
            return SqlKeywordEnum.SQL_KEYWORD_AND;
        }else if(isMatch(relationString,OR_REGEX)){
            return SqlKeywordEnum.SQL_KEYWORD_OR;
        }else{
            return SqlKeywordEnum.SQL_CONDITION;
        }
    }
    /**
     * 对一层进行解析
     * @param list
     * @return
     */
    private JSONObject getOrmJson(List<String> list){
        JSONObject result = new JSONObject();
        JSONArray orArray = new JSONArray();
        int len = list.size();
        if (1 == len){
            JSONObject oneJsonObject = conditionDeal(list.get(0));//条件处理
            //   orArray.add(oneJsonObject);
            //     jsonObject.put("or",orArray);
            return oneJsonObject;
        }
        HashSet<Integer> orIndex = getOrSplitIndex(list);

        if (orIndex.size() == 0){
            //此层无or 关键字,只有and 关键字
            JSONArray andArray = new JSONArray();
            for (String condition : list){
                if (isMatch(condition,AND_REGEX) ){
                    //将两边的条件的序号标记出来
                    continue;
                }
                JSONObject _jsonObject = sqlConditionsAnalysis(condition);
                andArray.add(_jsonObject);
            }
            result.put("and",andArray);
        }else{
            //存在or 关系
            //根据orIndex 来划分块
            Integer pos = 0;
            for (Integer integer : orIndex){
                //根据or 来划分 sql 块
                String conditionPart = "";
                for (int i = pos;i <integer ;i++){
                    String _str = list.get(i);
                    conditionPart = conditionPart + _str;
                }
                conditionPart =  conditionPart ;
                //解释数据
                System.out.println("conditionPart:" + conditionPart);
                //使用括号将此部分括起来(规范格式,好处理)
                JSONObject _jsonObject = sqlConditionsAnalysis(conditionPart);
                orArray.add(_jsonObject);
                // orArray.add(conditionPart);//测试使用
                pos = integer + 1;
            }

            String conditionPart = "";
            for (int i = pos;i < len;i++){
                String _str = list.get(i);
                conditionPart = conditionPart + _str;
            }
            if (!conditionPart.isEmpty()){
                JSONObject _jsonObject = sqlConditionsAnalysis(conditionPart);
                orArray.add(_jsonObject);
                //  orArray.add(conditionPart); //测试使用
            }

            result.put("or",orArray);
        }
        return result;
    }

    /**
     * 根据or 关键字的位置对 sqlSplitList 进行分割
     * @param sqlSplitList
     * @return
     */
    private HashSet<Integer> getOrSplitIndex(List<String> sqlSplitList){
        HashSet<Integer> orHashSet = new HashSet<Integer>();
        int len = sqlSplitList.size();
        if (len <= 1 ){
            return orHashSet;
        }

        int y = 1;
        int indexEnd = len - 1;
        for( int i = 0; y < indexEnd; ){
            String str = sqlSplitList.get(y);
            SqlKeywordEnum sqlKeywordEnum = getRelation(str);
            if ( sqlKeywordEnum == SqlKeywordEnum.SQL_KEYWORD_OR){
                orHashSet.add(Integer.valueOf(y));
            }else if(sqlKeywordEnum == SqlKeywordEnum.SQL_KEYWORD_AND){
                //to do nothing
            }else {
                String errMsg = "It is not the keyword of sql by 'and' or 'or'!";
                logger.error(errMsg);
                throw new IllegalArgumentException(errMsg);
            }
            i++;
            y = 2 * i +1;

        }
        return orHashSet;
    }
    /**
     * 对条件进行处理
     * @param oneCondtion
     * @return
     */
    private JSONObject conditionDeal(String oneCondtion){
        //JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        //当条件处理
        JSONObject _jsonObject = formatConditons(oneCondtion);
        int type = _jsonObject.getInteger("type");
        oneCondtion = _jsonObject.getString("res");
        if (ConditionsEnum.CONDTIONS_NOT.getType() == type) {
            //带 not 的记录
            JSONObject notJsonObject = sqlConditionsAnalysis(oneCondtion);
            jsonObject.put("not", notJsonObject);
        }else if (ConditionsEnum.CONDTIONS_SINGLE.getType() == type){
            //单条记录
            jsonObject =  setSingleObject(oneCondtion);
        }else{
            //不带not 的记录
            jsonObject = sqlConditionsAnalysis(oneCondtion);
        }
        return jsonObject;
    }

    /**
     * 单一条件处理
     * @param oneCondtion
     * @return
     */
    private JSONObject setSingleObject(String oneCondtion){
        String regex = "";
        JSONObject singleObject = new JSONObject();
        //运算符号匹配
        String operatorRegex = getOperatorRegex();

        if(!isContains(oneCondtion,operatorRegex)) {
            String errMsg = "Grammer of sql: operato is not match!";
            logger.error(errMsg);
            throw new IllegalArgumentException(errMsg);
        }

        //将操作符匹配出来
        List<String>  operatorList = getMatchedMultipleString(operatorRegex, oneCondtion);
        if (operatorList.size() != 1){
            String errMsg = "Grammer of sql:it is multiple operators in one condition!";
            logger.error(errMsg);
            throw new IllegalArgumentException(errMsg);
        }
        //进行单条件的划分
        String operatorStr = operatorList.get(0);
        int pos = oneCondtion.indexOf(operatorStr);
        int len = operatorStr.length();
        String attr = oneCondtion.substring(0,pos);
        attr = attr.trim();
        String value = oneCondtion.substring(pos + len);
        value = value.trim();
        String operator = null;

        if (isMatch(operatorStr,SqlOperatorRegexEnum.IN.getOperator()) || isMatch(operatorStr,SqlOperatorRegexEnum.NOTIN.getOperator())){
            //匹配 in 或者 not in

            //判断是in 还是not in
            boolean isNotIn = false;
            if(isMatch(operatorStr,SqlOperatorRegexEnum.NOTIN.getOperator())){
                isNotIn = true;
            }
            //operator = "in";
            //函数formatConditons 已将 in 条件的() 删除，所以,这里不做删除
            if(value.startsWith("(") && value.endsWith(")")){
                value  = value.substring(1,value.length() - 1);
                value = value.trim();
            }
            /////////////////////////////////////////////////////////////////////////////
            // id 也作为普通列看待，只是id 与_id 的值需要一致
//            if ("id".equals(attr)){
//                if(isNotIn){
//                    operator = "!in";
//                }else{
//                    operator = "in";
//                }
//            }else{
                //普通属性的处理
                String[] valueArray =  value.split(",");
                List<String> valueList = Arrays.asList(valueArray);
                HashSet<String> valueSet = new HashSet<>();
                valueSet.addAll(valueList);

                JSONArray inJsonArray = new JSONArray();
                for (String oneValue : valueSet){
                    JSONObject oneJsonObject = new JSONObject();
                    oneJsonObject.put("attr",attr);
                    oneJsonObject.put("operator","=");
                    oneJsonObject.put("value",oneValue);
                    inJsonArray.add(oneJsonObject);
                }
                //
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("or",inJsonArray);
                if (isNotIn){
                    singleObject.put("not",jsonObject);
                    return singleObject;
                }else{
                    return jsonObject;
                }
//            }
        }else if(isMatch(operatorStr,SqlOperatorRegexEnum.ISNULL.getOperator())){
            //匹配 null
            operator = "is";
            value = value.toLowerCase();
            if(!"null".equals(value)){
                String errMsg = "Value is not the keyword of NULL";
                logger.error(errMsg);
                throw new IllegalArgumentException(errMsg);
            }
        }else if(isMatch(operatorStr, SqlOperatorRegexEnum.NOTNULL.getOperator())){
            //匹配not null
            operator = "!is";
            value = value.toLowerCase();
            if(!"null".equals(value)){
                String errMsg = "Value is not the keyword of NULL";
                logger.error(errMsg);
                throw new IllegalArgumentException(errMsg);
            }
        }else if(isMatch(operatorStr,SqlOperatorRegexEnum.Like.getOperator())){
            //匹配like
            operator = "like";
        }else{
            //删除空格
            operator = operatorStr.replace(" ","");
        }

        if (value.endsWith(DOUBLE_COLON) && value.startsWith(DOUBLE_COLON)){
            value  = value.substring(1,value.length() - 1);
            //不再去空格
        }
        singleObject.put("attr",attr);
        singleObject.put("operator",operator);
        singleObject.put("value",value);
        return singleObject;
    }
    /**
     * 从list 中找到与之对应的符号,无嵌套
     *  @param find
     * @param start
     * @param list
     * @return
     */
    private int getClosedSymbol(String find,int start,List<String> list){
        int len = list.size();
        for(int _i = start;_i< len; _i++){
            if(find.equals(list.get(_i))){
                return _i;
            }
        }
        return -1 ;
    }

}

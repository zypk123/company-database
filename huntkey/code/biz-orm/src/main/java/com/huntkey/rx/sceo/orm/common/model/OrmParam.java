package com.huntkey.rx.sceo.orm.common.model;

import com.huntkey.rx.sceo.orm.common.constant.Constant;
import com.huntkey.rx.sceo.orm.common.type.SQLFunctionEnum;
import com.huntkey.rx.sceo.orm.common.type.SQLOperatorEnum;
import com.huntkey.rx.sceo.orm.common.type.SQLRelationEnum;
import com.huntkey.rx.sceo.orm.common.type.SQLSortEnum;
import com.huntkey.rx.sceo.orm.common.util.OrmAccessUtil;

import java.util.*;

/**
 * Created by licj on 2017/11/17.
 */
public class OrmParam {

//    public final static void main(String[] args) {
//        List<String> list = new ArrayList<String>();
//        OrmParam ormParam = new OrmParam();
//        try {
//            ormParam.setOrderExp(SQLSortEnum.DESC,"c1","c2");
//            list.add(ormParam.getEqualXML("column", null));
//            list.add(ormParam.getUnequalXML("column", null));
//            list.add(ormParam.getLessThanXML("column", null));
//            list.add(ormParam.getLessThanAndEqualXML("column", null));
//            list.add(ormParam.getGreaterThanXML("column", null));
//            list.add(ormParam.getGreaterThanAndEqualXML("column", null));
//            list.add(ormParam.getMatchMiddleXML("column", null));
//            list.add(ormParam.getMatchLeftXML("column", null));
//            list.add(ormParam.getMathRightXML("column", null));
//            list.add(ormParam.getUnlikeXML("column", null));
//            list.add(ormParam.getLeftUnlikeXML("column", null));
//            list.add(ormParam.getRightUnlikeXML("column", null));
//            list.add(ormParam.getBetweenXML("column", null, "xx"));
//            list.add(ormParam.getNotBetweenXML("column", "1", null));
//            String[] inArray = {"null", "null", "null"};
//            list.add(ormParam.getInXML("column", inArray));
//            list.add(ormParam.getNotInXML("column", inArray));
//            //#####################################################################################
//            System.out.print("and:");
//            String and = OrmParam.and(list);
//            System.out.println(and);
//
//            System.out.print("or:");
//            String or = OrmParam.or(list);
//            System.out.println(or);
//
//            System.out.print("not:");
//            String not = OrmParam.not(null);
//            System.out.println(not);
//
//            System.out.print("not1:");
//            String not1 = OrmParam.not(and);
//            System.out.println(not1);
//
//            System.out.print("not2:");
//            String not2 = OrmParam.not(or);
//            System.out.println(not2);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return;
//    }

    /**
     * 查询条件的特殊标识：(true:无效数据;false: 有效数据）
     */
    private boolean isLogicDelete;

    /**
     * 标识DISTINCT 查询
     */
    private boolean isDistinct;
    /**
     * 查询列
     */
    private List<String> columns;

    /**
     * 列-值映射(插入或者更新的时候使用)
     */
    private Map<String, Object> columnValueMapping;

    /**
     * 查询条件 where 表达式
     * <p>不要写where</p>
     * <p>传入的参数格式为:#{whereParams.paramName}</p>
     */
    private String whereExp;

    /**
     * 查询条件 where 表达式中的参数集
     * <p>key:paramName</p>
     */
    private Map<String, Object> whereParams;

    /**
     * 每页的数据条数
     * <p>分页查询使用</p>
     */
    private Integer pageSize;

    /**
     * 页码
     * <p>分页查询使用</p>
     */
    private Integer pageNo;

    /**
     * group by 的列名
     */
    private Set<String> groupColumnNames;

    /**
     * having 统计条件
     */
    private String havingExp;
    /**
     * order by 表达式
     * <p>格式:columnName asc|desc</p>
     * <p>多个排序字段用逗号分隔</p>
     */
    private String orderExp;

    public OrmParam() {
        isLogicDelete = false;
        isDistinct = false;

        this.columns = new ArrayList<String>();
        this.whereParams = new HashMap<String, Object>();
        this.columnValueMapping = new HashMap<String, Object>();
        this.groupColumnNames = new HashSet<String>();

        whereExp = null;
        havingExp = null;
        orderExp = null;
        pageSize = null;
        pageNo = null;
    }

    /**
     * 添加whereParam 参数
     *
     * @param key
     * @param value
     * @return
     */
    public OrmParam addWhereParam(String key, Object value) {
        OrmAccessUtil.accessNullOrEmputy(key);
        OrmAccessUtil.accessNull(value);
        this.whereParams.put(key, value);
        return this;
    }

    /**
     * 添加返回的列
     *
     * @param queryColumn
     * @return
     */
    public OrmParam addColumn(String queryColumn) {
        OrmAccessUtil.accessNullOrEmputy(queryColumn);
        columns.add(queryColumn);
        return this;
    }

    public List<String> getColumns() {
        return columns;
    }

    /**
     * 设置返回列,null无效
     * @param columns
     * @return
     */
    public OrmParam setColumns(List<String> columns) {
        if (columns == null){
            columns.clear();
        }else{
            this.columns = columns;
        }
        return this;
    }

    public Map<String, Object> getColumnValueMapping() {
        return columnValueMapping;
    }

    public void setColumnValueMapping(Map<String, Object> columnValueMapping) {
        if (columnValueMapping == null){
            columnValueMapping.clear();
        }else{
            this.columnValueMapping = columnValueMapping;
        }
    }

    public String getWhereExp() {
        return whereExp;
    }

    /**
     * 设置查询条件语句格式
     *
     * @param whereExp
     */
    public OrmParam setWhereExp(String whereExp) {
        whereExp = "".equals(whereExp) ? null : whereExp;
        this.whereExp = whereExp;
        return this;
    }

    public Map<String, Object> getWhereParams() {
        return whereParams;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////分页的条件设置///////////////////////////////////////////////////////
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 设置分页大小
     *
     * @param pageSize 每页的记录数
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public Integer getPageNo() {
        return pageNo;
    }

    /**
     * 设置页码
     *
     * @param pageNo 页码
     */
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////group by 条件组装////////////////////////////////////////////////////

    /**
     * 判断返回字段性质
     *
     * @param fieldName
     * @return true 为非统计函数字段,false 为统计函数字段(SUM 之类的统计函数)
     */
    protected boolean isAggregateFunctionColumn(String fieldName) {
        OrmAccessUtil.accessNullOrEmputy(fieldName);
        if (-1 != fieldName.indexOf("(") || -1 != fieldName.indexOf(")")) {
            return true;
        }
        return false;
    }

    /**
     * 获取groupBy 的字段
     *
     * @return
     */
    public Set<String> getGroupByColumns() {
        return this.groupColumnNames;
    }

    /**
     * 添加groupBy 的字段
     *
     * @param groupByColumns
     * @return
     */

    public OrmParam addGroupByExp(Set<String> groupByColumns) {
        addGroupByColumn(groupByColumns.toArray(new String[groupByColumns.size()]));
        return this;
    }

    /**
     * 添加groupby 字段属性
     *
     * @param fieldNames
     * @return
     */
    public OrmParam addGroupByColumn(String... fieldNames) {
        for (String fieldName : fieldNames) {
            if (0 <= this.columns.indexOf(fieldName)) {
                //如果存在,可以插入
                this.groupColumnNames.add(fieldName);
            }
        }
        return this;
    }

    /**
     * 设置 group by 查询列
     *
     * @param groupColumnNames
     * @return
     */
    public OrmParam setGroupByColumns(Set<String> groupColumnNames) {
        this.groupColumnNames.clear();
        for (String fieldName : groupColumnNames) {
            if (0 <= this.columns.indexOf(fieldName)) {
                //如果存在,可以插入
                this.groupColumnNames.add(fieldName);
            }
        }
        return this;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////having 条件组装（having 使用group by 查询的时候结合使用）/////////////////

    /**
     * @return
     */
    public String getHavingExp() {
        return havingExp;
    }

    /**
     * 设置having 条件(多条件使用SQL的关系符号连接起来)
     *
     * @param havingExp
     */
    public OrmParam setHavingExp(String havingExp) {
        //后面添加校验:判断havingExp 里面的条件是否是统计查询
        havingExp = "".equals(havingExp) ? null : havingExp;
        this.havingExp = havingExp;
        return this;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////OrderBy 的条件组装////////////////////////////////////////////////////////////////////
    public String getOrderExp() {
        return orderExp;
    }

    /**
     * 添加一个排序元素
     *
     * @param sqlSortEnum
     * @param sortColumn
     * @return
     */
    public OrmParam addOrderExpElement(SQLSortEnum sqlSortEnum, String sortColumn) {
        OrmAccessUtil.accessNull(sqlSortEnum, sortColumn);
        if (this.orderExp == null || "".equals(this.orderExp)) {
            this.orderExp = sortColumn + sqlSortEnum.getSortTypeWithSpace();
        } else {
            this.orderExp = this.orderExp + "," + sortColumn + sqlSortEnum.getSortTypeWithSpace();
        }
        return this;
    }

    /**
     * 设置orderby 查询条件,多个字段同排序
     *
     * @param sqlSortEnum 排序类型
     * @param sortColumn1 排序字段1
     * @param sortColumns 排序字段不定参数数组
     * @return 返回自身对象
     */
    public OrmParam setOrderExp(SQLSortEnum sqlSortEnum, String sortColumn1, String... sortColumns) {
        OrmAccessUtil.accessNull(sqlSortEnum, sortColumn1);
        StringBuffer sortColumnExp = new StringBuffer();
        sortColumnExp.append(sortColumn1).append(sqlSortEnum.getSortTypeWithSpace());
        for (int i = 0; i < sortColumns.length; ++i) {
            sortColumnExp.append(",").append(sortColumns[i]).append(sqlSortEnum.getSortTypeWithSpace());
        }
        this.orderExp = sortColumnExp.toString();
        return this;
    }

    /**
     * 设置orderby 查询条件,多个字段同排序
     *
     * @param sqlSortEnum
     * @param orderList
     * @return
     */
    public OrmParam setOrderExp(SQLSortEnum sqlSortEnum, List<String> orderList) {
        OrmAccessUtil.accessNull(sqlSortEnum, orderList);
        if (0 == orderList.size()) {
            return this;
        }
        String sortColumn1 = orderList.remove(0);
        String orderArray[] = new String[orderList.size()];
        orderList.toArray(orderArray);
        setOrderExp(sqlSortEnum, sortColumn1, orderArray);
        return this;
    }


    /**
     * 清理查询参数
     */
    public void clearOrmParmas() {
        isLogicDelete = false;
        isDistinct = false;

        columns.clear();
        columnValueMapping.clear();
        whereParams.clear();
        groupColumnNames.clear();

        whereExp = null;
        havingExp = null;
        orderExp = null;
        pageSize = null;
        pageNo = null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////参数转xml 字符串//////////////////////////////////////////////////////////
    protected final static String formatValueXML(String mapKey, boolean isNeedPlaceholder) {
        if (isNeedPlaceholder) {
            //占位符号方式
            return "#{" + Constant.WHERE_PARAM + "." + mapKey + "}";
        } else {
            //非占位符方式
            return "${" + Constant.WHERE_PARAM + "." + mapKey + "}";
        }
    }

    //条件格式化
    public final static String formatCondtionXML(String fieldName, SQLOperatorEnum sqlOperatorEnum, String mapKey) {
        OrmAccessUtil.accessNull(fieldName, sqlOperatorEnum, mapKey);
        return fieldName + sqlOperatorEnum.getOperatorWithSpace() + formatValueXML(mapKey, true);
    }

    /**
     * 创建占位符的key 值
     *
     * @return
     */
    protected String createDistinctMapKey() {
        return String.valueOf(this.whereParams.size());//同列多条件值避免重名
    }

    /**
     * 创建占位符的key 值(带字段信息)
     *
     * @param fieldName
     * @return
     */
    protected String createDistinctMapKey(String fieldName) {
        return fieldName + String.valueOf(this.whereParams.size());//同列多条件值避免重名
    }

    /**
     * 添加条件参数并且返回xml 字符串
     *
     * @param fieldName
     * @param fieldValue
     * @return
     */
    protected String putParamMapAndBackValueXML(String fieldName, Object fieldValue) {
        String mapKey = createDistinctMapKey(fieldName);
        this.whereParams.put(mapKey, fieldValue);
        return formatValueXML(mapKey, true);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////条件封装//////////////////////////////////////////////////////////////////////

    /**
     * 是否逻辑删除
     *
     * @return
     */
    public boolean isInvalid() {
        return isLogicDelete;
    }

    /**
     * @param isInvalid 标识查询参数条件是否有效
     * @return 返回this对象
     */
    public OrmParam isInvalidCondition(boolean isInvalid) {
        this.isLogicDelete = isInvalid;
        return this;
    }

    public boolean isDistinct() {
        return isDistinct;
    }

    public OrmParam setDistinct(boolean distinct) {
        isDistinct = distinct;
        return this;
    }
    //单目运算

    /**
     * 组装XML : IS NULL 条件
     *
     * @param fieldName 数据表字段名
     * @return
     */
    public String getIsNull(String fieldName) {
        OrmAccessUtil.accessNull(fieldName);
        return fieldName + SQLOperatorEnum.ISNULL.getOperatorWithSpace();
    }

    /**
     * 组装XML : IS NOT NULL 条件
     *
     * @param fieldName 数据表字段名
     * @return
     */
    public String getIsNotNull(String fieldName) {
        OrmAccessUtil.accessNull(fieldName);
        return fieldName + SQLOperatorEnum.ISNOTNULL.getOperatorWithSpace();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////比较符号封装 条件封装////////////////////////////////////////////////////////////

    /**
     * 组装XML : 二目操作符的条件
     *
     * @param fieldName       数据表字段名
     * @param sqlOperatorEnum 符号类型
     * @param fieldValue      字段数值
     * @return
     */
    protected String setBinaryOperator(String fieldName, SQLOperatorEnum sqlOperatorEnum, Object fieldValue) {
        return fieldName + sqlOperatorEnum.getOperatorWithSpace() + putParamMapAndBackValueXML(fieldName, fieldValue);
    }

    /**
     * 组装XML : 等于条件
     *
     * @param fieldName  数据表字段名
     * @param fieldValue 字段数值
     * @return
     */
    public String getEqualXML(String fieldName, Object fieldValue) {
        OrmAccessUtil.accessNull(fieldName);
        if (null == fieldValue) {
            return null;
        }
        return setBinaryOperator(fieldName, SQLOperatorEnum.EQ, fieldValue);
    }

    /**
     * 组装XML : 不等于条件
     *
     * @param fieldName  数据表字段名
     * @param fieldValue 字段数值
     * @return
     */
    public String getUnequalXML(String fieldName, Object fieldValue) {
        OrmAccessUtil.accessNull(fieldName);
        if (null == fieldValue) {
            return null;
        }
        return setBinaryOperator(fieldName, SQLOperatorEnum.UNEQ, fieldValue);
    }

    /**
     * 组装XML : 小于条件
     *
     * @param fieldName  数据表字段名
     * @param fieldValue 字段数值
     * @return
     */
    public String getLessThanXML(String fieldName, Object fieldValue) {
        OrmAccessUtil.accessNull(fieldName);
        if (null == fieldValue) {
            return null;
        }
        return setBinaryOperator(fieldName, SQLOperatorEnum.LT, fieldValue);
    }

    /**
     * 组装XML  : 小于等于的条件
     *
     * @param fieldName  数据表字段名
     * @param fieldValue 字段数值
     * @return
     */
    public String getLessThanAndEqualXML(String fieldName, Object fieldValue) {
        OrmAccessUtil.accessNull(fieldName);
        if (null == fieldValue) {
            return null;
        }
        return setBinaryOperator(fieldName, SQLOperatorEnum.LTE, fieldValue);
    }

    /**
     * 组装XML : 大于条件
     *
     * @param fieldName  数据表字段名
     * @param fieldValue 字段数值
     * @return
     */
    public String getGreaterThanXML(String fieldName, Object fieldValue) {
        OrmAccessUtil.accessNull(fieldName);
        if (null == fieldValue) {
            return null;
        }
        return setBinaryOperator(fieldName, SQLOperatorEnum.GT, fieldValue);
    }

    /**
     * 组装XML : 大于等于条件
     *
     * @param fieldName  数据表字段名
     * @param fieldValue 字段数值
     * @return
     */
    public String getGreaterThanAndEqualXML(String fieldName, Object fieldValue) {
        OrmAccessUtil.accessNull(fieldName);
        if (null == fieldValue) {
            return null;
        }
        return setBinaryOperator(fieldName, SQLOperatorEnum.GTE, fieldValue);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////模糊匹配 条件封装////////////////////////////////////////////////////////////

    /**
     * 规范like 查询的字符串值
     *
     * @param likeString
     * @return
     */
    protected String formatLikeValue(String likeString) {
        return likeString.replaceAll("\\%", "\\\\%");
    }

    /**
     * 组装XML : 中间模糊匹配条件
     *
     * @param fieldName  数据表字段名
     * @param fieldValue 字段数值
     * @return
     */
    public String getMatchMiddleXML(String fieldName, String fieldValue) {
        OrmAccessUtil.accessNull(fieldName);
        if (null == fieldValue || "".equals(fieldValue)) {
            return null;
        }
        String valueMatch = "%" + formatLikeValue(fieldValue) + "%";
        return setBinaryOperator(fieldName, SQLOperatorEnum.LIKE, valueMatch);
    }

    /**
     * 组装XML : 左模糊匹配条件
     *
     * @param fieldName  数据表字段名
     * @param fieldValue 字段数值
     * @return
     */
    public String getMatchLeftXML(String fieldName, String fieldValue) {
        OrmAccessUtil.accessNull(fieldName);
        if (null == fieldValue || "".equals(fieldValue)) {
            return null;
        }
        String valueMatch = formatLikeValue(fieldValue) + "%";
        return setBinaryOperator(fieldName, SQLOperatorEnum.LIKE, valueMatch);
    }

    /**
     * 组装XML : 右模糊匹配条件
     *
     * @param fieldName  数据表字段名
     * @param fieldValue 字段数值
     * @return
     */
    public String getMathRightXML(String fieldName, String fieldValue) {
        OrmAccessUtil.accessNull(fieldName);
        if (null == fieldValue || "".equals(fieldValue)) {
            return null;
        }
        String valueMatch = "%" + formatLikeValue(fieldValue);
        return setBinaryOperator(fieldName, SQLOperatorEnum.LIKE, valueMatch);
    }

    /**
     * 组装XML : 中间模糊不匹配条件
     *
     * @param fieldName  数据表字段名
     * @param fieldValue 字段数值
     * @return
     */
    public String getUnlikeXML(String fieldName, String fieldValue) {
        OrmAccessUtil.accessNull(fieldName);
        if (null == fieldValue || "".equals(fieldValue)) {
            return null;
        }
        String valueMatch = "%" + formatLikeValue(fieldValue) + "%";
        return setBinaryOperator(fieldName, SQLOperatorEnum.UNLIKE, valueMatch);
    }

    /**
     * 组装XML : 左模糊不匹配条件
     *
     * @param fieldName  数据表字段名
     * @param fieldValue 字段数值
     * @return
     */
    public String getLeftUnlikeXML(String fieldName, String fieldValue) {
        OrmAccessUtil.accessNull(fieldName);
        if (null == fieldValue || "".equals(fieldValue)) {
            return null;
        }
        String valueMatch = formatLikeValue(fieldValue) + "%";
        return setBinaryOperator(fieldName, SQLOperatorEnum.UNLIKE, valueMatch);
    }

    /**
     * 组装XML:右模糊不匹配条件
     *
     * @param fieldName  数据表字段名
     * @param fieldValue 字段数值
     * @return
     */
    public String getRightUnlikeXML(String fieldName, String fieldValue) {
        OrmAccessUtil.accessNull(fieldName);
        if (null == fieldValue || "".equals(fieldValue)) {
            return null;
        }
        String valueMatch = "%" + formatLikeValue(fieldValue);
        return setBinaryOperator(fieldName, SQLOperatorEnum.UNLIKE, valueMatch);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////添加BETWEEN 条件的封装///////////////////////////////////////////////////////

    /**
     * 组装XML 格式: field AND field2 ,给between 操作符使用
     *
     * @param fieldName   数据表字段名
     * @param fieldValue1 字段数值1
     * @param fieldValue2 字段数值2
     * @return
     */
    protected String paramasToBetweenXML(String fieldName, Object fieldValue1, Object fieldValue2) {
        String valueXml1 = putParamMapAndBackValueXML(fieldName, fieldValue1);
        String valueXml2 = putParamMapAndBackValueXML(fieldName, fieldValue2);
        return valueXml1 + SQLRelationEnum.AND.getRelationWithSpace() + valueXml2;
    }

    /**
     * 组装XML : Between 条件
     *
     * @param fieldName   数据表字段名
     * @param fieldValue1 字段数值1
     * @param fieldValue2 字段数值2
     * @return
     */
    public String getBetweenXML(String fieldName, Object fieldValue1, Object fieldValue2) {
        OrmAccessUtil.accessNull(fieldName);
        if (null == fieldValue1 || null == fieldValue2) {
            return null;
        }
        return fieldName + SQLOperatorEnum.BETWEEN.getOperatorWithSpace() + paramasToBetweenXML(fieldName, fieldValue1, fieldValue2);
    }

    /**
     * 组装XML: Not Between 条件
     *
     * @param fieldName   数据表字段名
     * @param fieldValue1 字段数值1
     * @param fieldValue2 字段数值2
     * @return
     */
    public String getNotBetweenXML(String fieldName, Object fieldValue1, Object fieldValue2) {
        OrmAccessUtil.accessNull(fieldName);
        if (null == fieldValue1 || null == fieldValue2) {
            return null;
        }
        return fieldName + SQLOperatorEnum.NOTBETWEEN.getOperatorWithSpace() + paramasToBetweenXML(fieldName, fieldValue1, fieldValue2);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////添加IN 条件的封装////////////////////////////////////////////////////////////

    /**
     * 过滤值为null 的无效数据
     *
     * @param objects
     * @return
     */
    protected final static Object[] invalidDataFilterForIn(Object... objects) {
        Set<Object> list = new HashSet<Object>(Arrays.asList(objects));
        for (Object object : list) {
            if (null == object) {
                list.remove(object);
            }
        }
        return list.toArray();
    }


    /**
     * 组装格式: (x,y,z,...)结构的XML
     *
     * @param fieldName   数据表字段名
     * @param fieldValues 字段数值
     * @return
     */
    protected String paramsSpiltByCommaXML(String fieldName, Object[] fieldValues) {
        StringBuilder inValuesSetStringBuilder = new StringBuilder("(");
        int len = fieldValues.length;
        for (int i = 0; i < len; ++i) {
            String xmlValue = putParamMapAndBackValueXML(fieldName, fieldValues[i]);
            inValuesSetStringBuilder.append(",").append(xmlValue);
        }
        String inValuesSetString = inValuesSetStringBuilder.toString().replaceFirst(",", "");
        inValuesSetString = inValuesSetString + ")";
        return inValuesSetString;
    }

    @Deprecated
    public String getConditionForInXML(String fieldName, Object[] objects) {
        return getInXML(fieldName, objects);
    }

    @Deprecated
    public String getConditionForNotInXML(String fieldName, Object[] objects) {
        return getNotInXML(fieldName, objects);
    }

    /**
     * 组装IN 的SQL 条件
     *
     * @param fieldName 数据表字段名
     * @param objects   字段数值
     * @return
     */
    public String getInXML(String fieldName, Object[] objects) {
        OrmAccessUtil.accessNull(fieldName);
        objects = invalidDataFilterForIn(objects);
        if (objects.length == 0) {
            return null;
        }
        return fieldName + SQLOperatorEnum.IN.getOperatorWithSpace() + paramsSpiltByCommaXML(fieldName, objects);
    }

    /**
     * 组装NOT IN 的SQL 条件
     *
     * @param fieldName 数据表字段名
     * @param objects   字段数值
     * @return
     */
    public String getNotInXML(String fieldName, Object[] objects) {
        OrmAccessUtil.accessNull(fieldName);
        objects = invalidDataFilterForIn(objects);
        if (objects.length == 0) {
            return null;
        }
        return fieldName + SQLOperatorEnum.NOTIN.getOperatorWithSpace() + paramsSpiltByCommaXML(fieldName, objects);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////优先级封装/////////////////////////////////////////////////////////////////

    /**
     * 过滤值为null 的字符串
     *
     * @param array
     * @return
     */
    protected final static String[] invalidStringFilter(String... array) {
        List<String> list = new ArrayList<String>();
        for (String str : array) {
            if (null != str && !"".equals(str)) {
                list.add(str);
            }
        }
        String[] validStrings = new String[list.size()];
        return list.toArray(validStrings);
    }

    /**
     * 循环 拼接关系条件
     *
     * @param sqlRelationEnum
     * @param conditionXmls
     */
    protected final static String appendRelation(SQLRelationEnum sqlRelationEnum, String... conditionXmls) {
        int len = conditionXmls.length;
        boolean isMutipule = 1 < len;
        StringBuilder stringBuilder = new StringBuilder(isMutipule ? "(" : "").append(conditionXmls[0]);

        for (int i = 1; i < len; ++i) {
            stringBuilder.append(sqlRelationEnum.getRelationWithSpace()).append(conditionXmls[i]);
        }
        stringBuilder.append((isMutipule ? ")" : ""));
        return stringBuilder.toString();
    }

    /**
     * SQL优先级封装 : AND
     *
     * @param conditionList
     * @return
     */
    public final static String and(List<String> conditionList) {
        if (null == conditionList) {
            return null;
        }
        String conditions[] = new String[conditionList.size()];
        return and(conditionList.toArray(conditions));
    }

    /**
     * SQL优先级封装 : AND
     *
     * @param conditionXmls
     * @return
     */
    public final static String and(String... conditionXmls) {
        String[] xmls = invalidStringFilter(conditionXmls);
        int len = xmls.length;
        if (len == 0) {
            return null;
        }
        return appendRelation(SQLRelationEnum.AND, xmls);
    }

    /**
     * SQL优先级封装 : OR
     *
     * @param conditionList
     * @return
     */
    public final static String or(List<String> conditionList) {
        if (null == conditionList) {
            return null;
        }
        String conditions[] = new String[conditionList.size()];
        return or(conditionList.toArray(conditions));
    }

    /**
     * SQL优先级封装 : OR
     *
     * @param conditionXmls
     * @return
     */
    public final static String or(String... conditionXmls) {
        String[] xmls = invalidStringFilter(conditionXmls);
        int len = xmls.length;
        if (len == 0) {
            return null;
        }
        return appendRelation(SQLRelationEnum.OR, xmls);
    }

    /**
     * SQL优先级封装 : NOT
     *
     * @param conditionXml
     * @return
     */
    public final static String not(String conditionXml) {
        if (null == conditionXml || "".equals(conditionXml)) return null;
        StringBuilder notStringBuilder = new StringBuilder(SQLRelationEnum.NOT.getRelationWithSpace())
                .append("(").append(conditionXml).append(")");
        return notStringBuilder.toString();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////SQL 常用统计函数封装//////////////////////////////////////////////////////////
//    protected final static String AS = " AS ";

    /**
     * 组装函数名
     *
     * @param sqlFunctionEnum
     * @param columnName
     * @return
     */
    protected final static String getFunctionXML(SQLFunctionEnum sqlFunctionEnum, String columnName) {
        //函数名(列名)
        return sqlFunctionEnum.getFunctionName() + "(" + columnName + ")";
    }

    /**
     * SQL AVG 函数字符串
     *
     * @param columnName
     * @return
     */
    public final static String sqlAvgFunctionXML(String columnName) {
        OrmAccessUtil.accessNullOrEmputy(columnName);
        return getFunctionXML(SQLFunctionEnum.AVG, columnName);
    }

//    public final static String sqlAvgWithAliasXML(String columnName, String aliasName) {
//        OrmAccessUtil.accessNullOrEmputy(aliasName);
//        return sqlAvgFunctionXML(columnName) + AS + aliasName;
//    }

    /**
     * SQL COUNT 函数字符串
     *
     * @param columnName
     * @return
     */
    public final static String sqlCountFunctionXML(String columnName) {
        OrmAccessUtil.accessNullOrEmputy(columnName);
        return getFunctionXML(SQLFunctionEnum.COUNT, columnName);
    }

//    public final static String sqlCountWithAliasXML(String columnName, String aliasName) {
//        OrmAccessUtil.accessNullOrEmputy(aliasName);
//        return sqlCountFunctionXML(columnName) + AS + aliasName;
//    }
//

    /**
     * SQL MAX 函数字符串
     *
     * @param columnName
     * @return
     */
    public final static String sqlMaxFunctionXML(String columnName) {
        OrmAccessUtil.accessNullOrEmputy(columnName);
        return getFunctionXML(SQLFunctionEnum.MAX, columnName);
    }

//    public final static String sqlMaxWithAliasXML(String columnName, String aliasName) {
//        OrmAccessUtil.accessNullOrEmputy(aliasName);
//        return sqlMaxFunctionXML(columnName) + AS + aliasName;
//    }

    /**
     * SQL MIN 函数字符串
     *
     * @param columnName
     * @return
     */
    public final static String sqlMinFunctionXML(String columnName) {
        OrmAccessUtil.accessNullOrEmputy(columnName);
        return getFunctionXML(SQLFunctionEnum.MIN, columnName);
    }

//    public final static String sqlMinWithAliasXML(String columnName, String aliasName) {
//        OrmAccessUtil.accessNullOrEmputy(aliasName);
//        return sqlMinFunctionXML(columnName) + AS + aliasName;
//    }

    /**
     * SQL SUM 函数字符串
     *
     * @param columnName
     * @return
     */
    public final static String sqlSumFunctionXML(String columnName) {
        OrmAccessUtil.accessNullOrEmputy(columnName);
        return getFunctionXML(SQLFunctionEnum.SUM, columnName);
    }

    //    public final static String sqlSumWithAliasXML(String columnName, String aliasName) {
//        OrmAccessUtil.accessNullOrEmputy(aliasName);
//        return sqlSumFunctionXML(columnName) + AS + aliasName;
//    }

    /**
     * 判断查询的返回列中是否存在聚合函数
     *
     * @return
     */
    public boolean isHasAggregateColumn() {
        for (String column : this.columns) {
            if (0 <= column.indexOf("(") || 0 <= column.indexOf(")")) {
                return true;
            }
        }
        return false;
    }


}

package com.huntkey.rx.sceo.orm.common.util;

import com.huntkey.rx.sceo.orm.common.constant.ConstantRegex;
import com.huntkey.rx.sceo.orm.common.type.SQLCurdEnum;
import com.huntkey.rx.sceo.orm.common.type.SQLOperatorEnum;
import com.huntkey.rx.sceo.orm.common.type.SQLRelationEnum;
import com.huntkey.rx.sceo.orm.exception.ORMException;
import scala.collection.mutable.StringBuilder;

/**
 * Created by linziy on 2017/12/4.
 * sql 语句校验工具
 */
public class VerifySqlUtil {

    private final static String WHERE_REGEX = "(" + ConstantRegex.AT_LEAST_ONE_SPACE + "WHERE" + ConstantRegex.AT_LEAST_ONE_SPACE + ")";
    private final static String GROUP_REGEX = "(" + ConstantRegex.AT_LEAST_ONE_SPACE + "GROUP" + ConstantRegex.AT_LEAST_ONE_SPACE + "BY" + ConstantRegex.AT_LEAST_ONE_SPACE + ")";
    private final static String ORDER_REGEX = "(" + ConstantRegex.AT_LEAST_ONE_SPACE + "ORDER" + ConstantRegex.AT_LEAST_ONE_SPACE + "BY" + ConstantRegex.AT_LEAST_ONE_SPACE + ")";

    protected final static String[] illegalCharacterBySelect = {
            SQLCurdEnum.INSERT.getSqlOpRegex(), SQLCurdEnum.UPDATE.getSqlOpRegex(), SQLCurdEnum.DELETE.getSqlOpRegex()
    };

    protected final static String[] conditionsOperatorsRegex = {
            SQLOperatorEnum.EQ.getOperatorRegex(), SQLOperatorEnum.UNEQ.getOperatorRegex(),
            SQLOperatorEnum.LT.getOperatorRegex(), SQLOperatorEnum.LTE.getOperatorRegex(),
            SQLOperatorEnum.GT.getOperatorRegex(), SQLOperatorEnum.GTE.getOperatorRegex(),
            SQLOperatorEnum.UNLIKE.getOperatorRegex(), SQLOperatorEnum.LIKE.getOperatorRegex(),
            SQLOperatorEnum.NOTBETWEEN.getOperatorRegex(), SQLOperatorEnum.BETWEEN.getOperatorRegex(),
            SQLOperatorEnum.ISNULL.getOperatorRegex(), SQLOperatorEnum.ISNOTNULL.getOperatorRegex()
    };

    /**
     * 正则表达式
     *
     * @param regexArray
     * @return
     */
    protected final static String regexArrayToRegexString(String[] regexArray) {
        StringBuilder regex = new StringBuilder("");
        for (String str : regexArray) {
            regex.append("(").append(str).append(")").append("|");
        }
        String rs = regex.toString();
        rs = rs.substring(0, rs.length() - 1);
        return rs;
    }

    /**
     * 是否永恒成立条件:两边均为常量
     *
     * @param oneCondtition
     * @return
     */
    protected final static boolean isConstantCondition(String oneCondtition) {
        //删除括号
        oneCondtition = oneCondtition.replaceAll(ConstantRegex.LEFT_PARENTHESIS, "");
        oneCondtition = oneCondtition.replaceAll(ConstantRegex.RIGHT_PARENTHESIS, "");
        //删除not 条件
        oneCondtition = oneCondtition.trim();
        String regexNot = SQLRelationEnum.NOT.getRelationByRegex() + ".+";
        if (ConstantRegex.isMatch(oneCondtition, regexNot)) {
            //条件以not 开头
            oneCondtition = oneCondtition.substring(3);
        }
        oneCondtition = oneCondtition.trim();
        String sqlOpRegex = regexArrayToRegexString(conditionsOperatorsRegex);
        String[] conditionPart = oneCondtition.split(sqlOpRegex);

        if (conditionPart.length == 1) {
            return false;
        } else if (conditionPart.length == 2) {
            //比较两边是否常量
            boolean isSqlConstant = true;
            for (int i = 0; i < 2; i++) {
                isSqlConstant &= ConstantRegex.isMatch(conditionPart[i], ConstantRegex.SQL_CONST_VAL);
            }
            return isSqlConstant;
        } else if (conditionPart.length > 2) {
            return false;
        }
        return false;
    }

    /**
     * 获取需要查询的条件块
     */
    protected final static String getConditionsSegment(String sql) {
        sql = sql.toUpperCase();
        String conditionsSegment = "";
        if (ConstantRegex.isContains(sql, WHERE_REGEX)) {
            String regex = null;
            //带条件查询
            if (ConstantRegex.isContains(sql, GROUP_REGEX)) {
                //获取where 与 group 之间的
                regex = WHERE_REGEX + "(.+)" + GROUP_REGEX;
            } else if (ConstantRegex.isContains(sql, ORDER_REGEX)) {
                //where 与 order 之间
                regex = WHERE_REGEX + "(.+)" + ORDER_REGEX;
            } else {
                //纯条件
                regex = WHERE_REGEX + "(.+)($)";
            }
            conditionsSegment = ConstantRegex.getMatchedOneString(regex, sql);
            if (null != conditionsSegment) {
                conditionsSegment = conditionsSegment.replaceFirst(WHERE_REGEX, "");
                conditionsSegment = conditionsSegment.replaceFirst(GROUP_REGEX, "");
                conditionsSegment = conditionsSegment.replaceFirst(ORDER_REGEX, "");
            }
        } else {
            //不带条件
            conditionsSegment = null;
        }
        return conditionsSegment;
    }

    protected final static String[] conditionsSegmentSplit(String conditionsSegment) {
        String splitRegex = "(" + SQLRelationEnum.AND.getRelationByRegex()
                + "|" + SQLRelationEnum.OR.getRelationByRegex()
                //     + "|" + SQLRelationEnum.NOT.getRelationByRegex()
                + ")";
        conditionsSegment = conditionsSegment.toUpperCase();
        conditionsSegment = conditionsSegment.replaceAll(ConstantRegex.LEFT_PARENTHESIS, "");
        conditionsSegment = conditionsSegment.replaceAll(ConstantRegex.RIGHT_PARENTHESIS, "");

        String[] conditions = conditionsSegment.split(splitRegex);

        return conditions;
    }

    /**
     * 认证sql 语句,只允许select 语句
     *
     * @param sqlCurdEnum
     * @param sql
     * @return
     */
    public final static boolean verifySql(SQLCurdEnum sqlCurdEnum, String sql) {
        OrmAccessUtil.accessNullOrEmputy(sql);
        String keyWordSql = sql;
        keyWordSql = keyWordSql.replaceAll(ConstantRegex.VAR_WITH_DOUBLE_COLON, "");
        keyWordSql = keyWordSql.replaceFirst(ConstantRegex.VAR_WITH_INVERTED_COMMA, "");
        keyWordSql = keyWordSql.toUpperCase();
        if (sqlCurdEnum.SELECT.equals(sqlCurdEnum)) {
            String strRegex = regexArrayToRegexString(illegalCharacterBySelect);
            if (ConstantRegex.isContains(keyWordSql, strRegex)) {
                return false;
            }
            //根据查询条件进行判断,认为包含恒成立的条件为非法条件（操作符号的左右不能同时为常量）
            String conditionsSegment = getConditionsSegment(sql);
            if (null != conditionsSegment) {
                String[] conditions = conditionsSegmentSplit(conditionsSegment);
                for (String condition : conditions) {
                    if (isConstantCondition(condition)) {
                        throw new ORMException("There is a constant condition in the conditions.");
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }

//测试使用
//    public final static void main(String[] args) {
//        String sql = "SELECT * FROM employee wherE column1 =1 and column2  not like 'xxx' or not (1 >1)";
//        String sql2 = "SELECT * FROM employee wherE column1 =1 and column2  not like 'xxx'";
//        boolean b = verifySql(SQLCurdEnum.SELECT, sql);
//        System.out.println(b);
//        String str = " column2  not like 'xxx'".toUpperCase();
//        String regex = SQLOperatorEnum.UNLIKE.getOperatorRegex();
//        boolean d = ConstantRegex.isContains(str, regex);
//        System.out.println(d);
//        return;
//    }
}

package com.huntkey.rx.sceo.orm.common.type;

import com.huntkey.rx.sceo.orm.common.constant.ConstantRegex;

/**
 * Created by linziy on 2017/11/18.
 * 操作符
 */
public enum SQLOperatorEnum {

    //二目运算
    EQ(0, "=", "等于"),
    UNEQ(1, "<>", "不等于"),
    LT(2, "<", "小于"),
    LTE(3, "<=", "小于等于"),
    GT(4, ">", "大于"),
    GTE(5, ">=", "大于等于"),

    LIKE(6, "LIKE", "like"),
    UNLIKE(7, "NOT LIKE", "not like"),


    //三目运算
    BETWEEN(30, "BETWEEN", "between"),
    NOTBETWEEN(31, "NOT BETWEEN", "not between"),
    IN(32, "IN", "in"),
    NOTIN(33, "NOT IN", "not in"),

    //单目运算
    ISNULL(100, "IS NULL", "is null"),
    ISNOTNULL(101, "IS NOT NULL", "is not null");

    private int sequence; //序列
    private String operator;// 操作符
    private String desc; //描述

    private SQLOperatorEnum(int sequence, String operator, String desc) {
        this.sequence = sequence;
        this.operator = operator;
        this.desc = desc;
    }

    public int getSequence() {
        return sequence;
    }

    public String getDesc() {
        return desc;
    }

    public String getOperator() {
        return operator;
    }

    //操作符旁边两边空格
    public String getOperatorWithSpace() {
        return " " + operator + " ";
    }

    public String getOperatorRegex() {
        String opRegex = "";
        if (EQ.equals(this) || UNEQ.equals(this)
                || LT.equals(this) || LTE.equals(this)
                || GT.equals(this) || GTE.equals(this)) {
            opRegex = ConstantRegex.AT_LEAST_ZERO_SPACE + getOperator()
                    + ConstantRegex.AT_LEAST_ZERO_SPACE;
        } else if (IN.equals(this)) {
            opRegex = ConstantRegex.AT_LEAST_ONE_SPACE + this.getOperator()
                    + ConstantRegex.AT_LEAST_ONE_SPACE;
        } else if (NOTIN.equals(this)) {
            opRegex = ConstantRegex.AT_LEAST_ONE_SPACE + "NOT"
                    + ConstantRegex.AT_LEAST_ONE_SPACE + "IN"
                    + ConstantRegex.AT_LEAST_ONE_SPACE;
        } else if (ISNULL.equals(this)) {
            opRegex = ConstantRegex.AT_LEAST_ONE_SPACE + "IS"
                    + ConstantRegex.AT_LEAST_ONE_SPACE + "NULL"
                    + ConstantRegex.AT_LEAST_ONE_SPACE;
        } else if (ISNOTNULL.equals(this)) {
            opRegex = ConstantRegex.AT_LEAST_ONE_SPACE + "IS"
                    + ConstantRegex.AT_LEAST_ONE_SPACE + "NOT"
                    + ConstantRegex.AT_LEAST_ONE_SPACE + "NULL"
                    + ConstantRegex.AT_LEAST_ONE_SPACE;
        } else if (LIKE.equals(this)) {
            opRegex = ConstantRegex.AT_LEAST_ONE_SPACE + "LIKE"
                    + ConstantRegex.AT_LEAST_ONE_SPACE;
        } else if (UNLIKE.equals(this)) {
            opRegex = ConstantRegex.AT_LEAST_ONE_SPACE + "NOT"
                    + ConstantRegex.AT_LEAST_ONE_SPACE + "LIKE"
                    + ConstantRegex.AT_LEAST_ONE_SPACE;
        } else if (BETWEEN.equals(this)) {
            opRegex = ConstantRegex.AT_LEAST_ONE_SPACE + this.getOperator() + ConstantRegex.AT_LEAST_ONE_SPACE;
        } else if (NOTBETWEEN.equals(this)) {
            opRegex = ConstantRegex.AT_LEAST_ONE_SPACE + "NOT"
                    + ConstantRegex.AT_LEAST_ONE_SPACE + "BETWEEN"
                    + ConstantRegex.AT_LEAST_ONE_SPACE;
        } else {
            opRegex = ConstantRegex.AT_LEAST_ONE_SPACE + this.getOperator() + ConstantRegex.AT_LEAST_ONE_SPACE;
        }
        return opRegex;
    }
}

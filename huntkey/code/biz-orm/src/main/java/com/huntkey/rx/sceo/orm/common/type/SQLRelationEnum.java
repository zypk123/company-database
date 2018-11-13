package com.huntkey.rx.sceo.orm.common.type;

import com.huntkey.rx.sceo.orm.common.constant.ConstantRegex;

/**
 * Created by linziy on 2017/11/18.
 */
public enum SQLRelationEnum {
    AND(1, "AND"),
    OR(2, "OR"),
    NOT(3, "NOT");

    private int type;
    private String relation;


    private SQLRelationEnum(int type, String relation) {
        this.type = type;
        this.relation = relation;
    }

    public int getType() {
        return type;
    }

    public String getRelation() {
        return this.relation;
    }

    public String getRelationWithSpace() {
        return " " + this.relation + " ";
    }

    public String getRelationByRegex() {
        String regexStr = null;
        if (AND == this || OR == this) {
            regexStr = "(" + ConstantRegex.AT_LEAST_ONE_SPACE + getRelation() + ConstantRegex.AT_LEAST_ONE_SPACE + ")";
        } else if (NOT == this) {
            regexStr = "(" + "^" + getRelation() + "(" + ConstantRegex.AT_LEAST_ONE_SPACE + "|" + ConstantRegex.AT_LEAST_ZERO_SPACE + ConstantRegex.LEFT_PARENTHESIS + "))";
        }
        return regexStr;
    }
}

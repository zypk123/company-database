package com.huntkey.rx.sceo.serviceCenter.common.emun;

/**
 * Created by Lex on 2017/8/24.
 */
public enum SqlOperatorRegexEnum {

    EQUALS(0, "\\s{0,}=\\s{0,}", "equals"),
    NOTEQUALS(1, "\\s{0,}!=\\s{0,}", "not equqls"),
    LT(2, "\\s{0,}<\\s{0,}", "less than"),
    LTE(3,"\\s{0,}<=\\s{0,}","less than or equals"),
    GT(4, "\\s{0,}>\\s{0,}", "greater than"),
    GTE(5, "\\s{0,}>=\\s{0,}", "greater than or equals"),
    IN(6, "\\s{1,}in\\s{1,}", "in the area"),
    NOTIN(7, "\\s{1,}not\\s{1,}in\\s{1,}", "not in the area"),
    ISNULL(8, "\\s{1,}is\\s{1,}", "is null"),
    NOTNULL(9, "\\s{1,}is\\s{1,}not\\s{1,}", "is not null"),
    Like(10, "\\s{1,}is\\s{1,}like\\s{1,}", "like");

    private int index; //索引
    private String operator;//操作符
    private String desc;//描述

    private SqlOperatorRegexEnum(int index,String operator,String desc){
        this.index = index;
        this.operator = operator;
        this.desc = desc;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

}

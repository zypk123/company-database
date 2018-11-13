package com.huntkey.rx.sceo.serviceCenter.common.emun;

/**
 * Created by Lex on 2017/8/21.
 */
public enum SqlKeywordEnum {

    SQL_CONDITION(0,"条件块"),
    SQL_KEYWORD_AND(1,"AND 关键字"),
    SQL_KEYWORD_OR(2,"OR 关键字"),
    SQL_INIT(3,"初始化");

    private int type;
    private String dec;

    SqlKeywordEnum(int type,String dec) {
        this.type = type;
        this.dec = dec;
    }

    public int getType() {
        return this.type;
    }
}

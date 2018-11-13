package com.huntkey.rx.sceo.orm.common.type;

/**
 * Created by linziy on 2017/11/29.
 * SQL 中特殊意义的符号
 */
public enum SQLSymbolEnum {
    ALLCOLUMNS(1, "*", "所有列");

    private int num;
    private String symbol;
    private String desc;

    private SQLSymbolEnum(int num, String symbol, String desc) {
        this.num = num;
        this.symbol = symbol;
        this.desc = desc;
    }

    public int getNum() {
        return num;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDesc() {
        return desc;
    }


}

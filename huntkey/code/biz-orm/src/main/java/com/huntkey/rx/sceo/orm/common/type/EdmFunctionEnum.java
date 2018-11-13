package com.huntkey.rx.sceo.orm.common.type;

/**
 * Created by linziy on 2017/12/6.
 */
public enum EdmFunctionEnum {
    SET(1, "set", "Set 函数前缀"),
    GET(2, "get", "Get 函数前缀"),
    LOAD(3, "load", "Load 函数前缀");

    private int sequence;
    private String functionPreStuff;
    private String desc;

    private EdmFunctionEnum(int sequence, String functionPreStuff, String desc) {
        this.sequence = sequence;
        this.functionPreStuff = functionPreStuff;
        this.desc = desc;
    }

    public int getSequence() {
        return sequence;
    }

    public String getFunctionPreStuff() {
        return functionPreStuff;
    }

    public String getDesc() {
        return desc;
    }

}
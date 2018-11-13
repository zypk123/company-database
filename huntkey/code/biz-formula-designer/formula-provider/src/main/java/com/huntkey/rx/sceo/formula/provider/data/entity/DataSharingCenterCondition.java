package com.huntkey.rx.sceo.formula.provider.data.entity;

/**
 * @author chenfei on 2017/8/1.
 */
public class DataSharingCenterCondition {

    private String attr;
    private String operator;
    private String value;

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DataSharingCenterCondition(String attr, String operator, String value) {
        this.attr = attr;
        this.operator = operator;
        this.value = value;
    }
}

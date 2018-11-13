package com.huntkey.rx.sceo.formula.common.model.vo;
/**
 * @author chenfei on 2017/5/15.
 */
public class Condition {
    private String attr;
    private String operator;
    private String value;

    public Condition() {
    }

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
}

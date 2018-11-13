package com.huntkey.rx.sceo.formula.provider.data.entity;

/**
 * @author chenfei on 2017/8/1.
 */
public class FormulaStyle {

    private String type;
    private String val;
    private String label;
    private String returnType;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    @Override
    public String toString() {

        return "FormulaStyle{" +
                "type='" + type + '\'' +
                ", val='" + val + '\'' +
                ", label='" + label + '\'' +
                ", returnType='" + returnType + '\'' +
                '}';
    }
}

package com.huntkey.rx.sceo.formula.common.params;

/**
 * @author chenfei on 2017/7/6.
 */
public class FormulaItem {

    private String label;
    private String val;
    private String type;
    private String returnType;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    @Override
    public String toString() {
        return "FormulaItem{" +
                "label='" + label + '\'' +
                ", val='" + val + '\'' +
                ", type='" + type + '\'' +
                ", returnType='" + returnType + '\'' +
                '}';
    }
}

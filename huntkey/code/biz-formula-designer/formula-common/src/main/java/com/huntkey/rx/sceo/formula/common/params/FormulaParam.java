package com.huntkey.rx.sceo.formula.common.params;

/**
 * Param Entity for formula pre-calc.
 *
 * @author chenfei on 2017/7/5.
 *
 * @deprecated
 */
public class FormulaParam {

    private String paramName;
    private String paramValue;
    private String paramType;

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }
}

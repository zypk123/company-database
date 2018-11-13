package com.huntkey.rx.sceo.formula.common.params;

import com.huntkey.rx.sceo.formula.common.constant.EdmDataType;

/**
 * @author chenfei on 2017/7/6.
 */
public class FormulaItemValue {

    /** variant type */
    private String varType;
    /** value of variant */
    private String varVal;
    /** EDM data type */
    private EdmDataType dataValType;
    /** value of data */
    private String dataVal;

    public String getVarType() {
        return varType;
    }

    public void setVarType(String varType) {
        this.varType = varType;
    }

    public String getVarVal() {
        return varVal;
    }

    public void setVarVal(String varVal) {
        this.varVal = varVal;
    }

    public EdmDataType getDataValType() {
        return dataValType;
    }

    public void setDataValType(String dataValType) {
        this.dataValType = EdmDataType.getDataType(dataValType);
    }

    public String getDataVal() {
        return dataVal;
    }

    public void setDataVal(String dataVal) {
        this.dataVal = dataVal;
    }

    @Override
    public String toString() {
        return "FormulaItemValue{" +
                "varType='" + varType + '\'' +
                ", varVal='" + varVal + '\'' +
                ", dataValType=" + dataValType +
                ", dataVal='" + dataVal + '\'' +
                '}';
    }
}

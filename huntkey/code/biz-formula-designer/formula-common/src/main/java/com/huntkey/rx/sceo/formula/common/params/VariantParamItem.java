package com.huntkey.rx.sceo.formula.common.params;

import com.huntkey.rx.sceo.formula.common.constant.EdmDataType;

/**
 * @author chenfei on 2017/7/13.
 */
public class VariantParamItem {

    private String dataKey;
    private String dataValue;
    private EdmDataType dataType;

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public EdmDataType getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = EdmDataType.getDataType(dataType);;
    }

    @Override
    public String toString() {
        return "VariantParamItem{" +
                "dataKey='" + dataKey + '\'' +
                ", dataValue='" + dataValue + '\'' +
                ", dataType='" + dataType + '\'' +
                '}';
    }
}

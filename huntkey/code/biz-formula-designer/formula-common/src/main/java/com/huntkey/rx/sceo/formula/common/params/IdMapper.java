package com.huntkey.rx.sceo.formula.common.params;

/**
 * @author chenfei on 2017/8/25 0025.
 */
public class IdMapper {

    private String fieldId;
    private String variantId;

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getVariantId() {
        return variantId;
    }

    public void setVariantId(String variantId) {
        this.variantId = variantId;
    }

    @Override
    public String toString() {
        return "IdMapper{" +
                "fieldId='" + fieldId + '\'' +
                ", variantId='" + variantId + '\'' +
                '}';
    }
}

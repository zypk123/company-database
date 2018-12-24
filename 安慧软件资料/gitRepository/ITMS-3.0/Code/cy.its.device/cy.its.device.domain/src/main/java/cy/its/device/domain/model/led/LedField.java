package cy.its.device.domain.model.led;

public class LedField {
    private String fieldId;

    private String dataType;

    private String filedKey;

    private String fieldName;

    private String fieldType;

    private String fieldExampleValue;

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getFiledKey() {
        return filedKey;
    }

    public void setFiledKey(String filedKey) {
        this.filedKey = filedKey;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldExampleValue() {
        return fieldExampleValue;
    }

    public void setFieldExampleValue(String fieldExampleValue) {
        this.fieldExampleValue = fieldExampleValue;
    }
}
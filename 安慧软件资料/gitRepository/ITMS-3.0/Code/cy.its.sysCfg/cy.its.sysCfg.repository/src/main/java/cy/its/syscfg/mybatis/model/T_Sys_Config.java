package cy.its.syscfg.mybatis.model;

public class T_Sys_Config {
    private String configId;

    private String configName;

    private String configRemark;

    private String key;

    private String value;

    private String valueType;

    private String selectedValue;

    private String selectedValueDesc;

    private String defaultValue;

    private String type;
    
    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigRemark() {
        return configRemark;
    }

    public void setConfigRemark(String configRemark) {
        this.configRemark = configRemark;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
    }

    public String getSelectedValueDesc() {
        return selectedValueDesc;
    }

    public void setSelectedValueDesc(String selectedValueDesc) {
        this.selectedValueDesc = selectedValueDesc;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    } 

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
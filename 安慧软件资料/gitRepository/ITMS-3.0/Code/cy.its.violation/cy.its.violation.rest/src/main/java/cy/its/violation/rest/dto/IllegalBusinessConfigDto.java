package cy.its.violation.rest.dto;

public class IllegalBusinessConfigDto {
	//系统配置Id
	public String configId;
	//配置名称
    public String configName;
    //配置项名称
    public String configRemark;
    //值
    public String value;
    //值的类型
    public String valueType;
    //值的枚举值
    public String selectedValue;
    //枚举值说明
    public String selectedValueDesc;
    //默认值
    public String defaultValue;
    //配置类型
    public String type;
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

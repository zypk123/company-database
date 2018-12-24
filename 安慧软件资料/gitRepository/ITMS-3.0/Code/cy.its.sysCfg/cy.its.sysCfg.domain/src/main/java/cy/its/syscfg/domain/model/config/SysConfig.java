package cy.its.syscfg.domain.model.config;

import cy.its.com.domain.Entity;

public class SysConfig extends Entity<String>{

	public String configId;

    public String configName;

    public String configRemark;

    public String key;

    public String value;

    public String valueType;

    public String selectedValue;

    public String selectedValueDesc;

    public String defaultValue;
    
    public String type;
    
    public SysConfig()
    {
    	
    }
    
    
	public SysConfig(String configId, String configName, String configRemark, String key, String value,
			String valueType, String selectedValue, String selectedValueDesc, String defaultValue, String type) {
		super();
		this.configId = configId;
		this.configName = configName;
		this.configRemark = configRemark;
		this.key = key;
		this.value = value;
		this.valueType = valueType;
		this.selectedValue = selectedValue;
		this.selectedValueDesc = selectedValueDesc;
		this.defaultValue = defaultValue;
		this.type = type;
	}

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

	
	@Override
	public String getIdentityId() {
		// TODO Auto-generated method stub
		return configId;
	}

}

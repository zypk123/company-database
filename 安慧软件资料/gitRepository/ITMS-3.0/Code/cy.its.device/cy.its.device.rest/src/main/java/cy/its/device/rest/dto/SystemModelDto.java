package cy.its.device.rest.dto;

import cy.its.com.dto.BaseDto;

public class SystemModelDto extends BaseDto {
	
	private String deviceSysModelId;		//系统型号ID
	private String deviceSysModelName;		//系统型号名称
	private String deviceSysFunctionId;		//系统功能ID
    private String functionName;			//功能名称
    private String remark;					//备注
	
	
	
	 
	public String getDeviceSysModelId() {
		return deviceSysModelId;
	}
	public void setDeviceSysModelId(String deviceSysModelId) {
		this.deviceSysModelId = deviceSysModelId;
	}
	public String getDeviceSysModelName() {
		return deviceSysModelName;
	}
	public void setDeviceSysModelName(String deviceSysModelName) {
		this.deviceSysModelName = deviceSysModelName;
	}
	public String getDeviceSysFunctionId() {
		return deviceSysFunctionId;
	}
	public void setDeviceSysFunctionId(String deviceSysFunctionId) {
		this.deviceSysFunctionId = deviceSysFunctionId;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	 
	 
}

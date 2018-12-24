package cy.its.device.rest.dto;

import cy.its.com.dto.BaseDto;

public class SystemFunctionDto extends BaseDto {
    private String deviceSysFunctionId;		//系统功能ID

    private String functionName;			//功能名称

    private String functionCode;			//功能代码

    private String functionDesc;			//功能描述

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

	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	public String getFunctionDesc() {
		return functionDesc;
	}

	public void setFunctionDesc(String functionDesc) {
		this.functionDesc = functionDesc	;
	}
    
    
}

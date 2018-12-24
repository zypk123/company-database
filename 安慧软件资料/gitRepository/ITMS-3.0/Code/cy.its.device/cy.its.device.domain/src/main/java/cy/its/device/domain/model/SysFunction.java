package cy.its.device.domain.model;

public class SysFunction {
    private String deviceSysFunctionId;

    private String functionName;

    private String functionCode;

    private String functionDesc;

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
        this.functionDesc = functionDesc;
    }
}
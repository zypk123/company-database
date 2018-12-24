package cy.its.syscfg.mybatis.model;

public class T_Sys_Function {
    private String functionCode;

    private String menuCode;

    private String functionName;

    private String functionFlag;

    private String functionDependency;

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionFlag() {
        return functionFlag;
    }

    public void setFunctionFlag(String functionFlag) {
        this.functionFlag = functionFlag;
    }

    public String getFunctionDependency() {
        return functionDependency;
    }

    public void setFunctionDependency(String functionDependency) {
        this.functionDependency = functionDependency;
    }
}
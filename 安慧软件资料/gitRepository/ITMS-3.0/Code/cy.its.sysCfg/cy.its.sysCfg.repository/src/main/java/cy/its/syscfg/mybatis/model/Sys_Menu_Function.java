package cy.its.syscfg.mybatis.model;

public class Sys_Menu_Function {
    private String menuCode;

    private String sysConfigId;

    private String menuName;

    private String targetUrl;

    private String parentCode;

    private String sortIndex;

    private String menuEnableFlag;

    private String remark;  
    
    private String menuImageUrl;

    private String functionCode;

    private String functionName;

    private String functionFlag;

    private String functionDependency;
    

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getSysConfigId() {
        return sysConfigId;
    }

    public void setSysConfigId(String sysConfigId) {
        this.sysConfigId = sysConfigId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(String sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getMenuEnableFlag() {
        return menuEnableFlag;
    }

    public void setMenuEnableFlag(String menuEnableFlag) {
        this.menuEnableFlag = menuEnableFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
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

	public String getMenuImageUrl() {
		return menuImageUrl;
	}

	public void setMenuImageUrl(String menuImageUrl) {
		this.menuImageUrl = menuImageUrl;
	}
}
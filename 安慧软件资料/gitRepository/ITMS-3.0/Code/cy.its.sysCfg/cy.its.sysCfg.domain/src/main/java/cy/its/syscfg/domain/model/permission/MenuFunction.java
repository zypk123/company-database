package cy.its.syscfg.domain.model.permission;

import cy.its.com.domain.Entity;

public class MenuFunction extends Entity<String> {

	/**
	 * 系统功能编码
	 */
	private String functionCode;

	/**
	 * 菜单编码
	 */
	public String menuCode;

	/**
	 * 系统功能名称
	 */
	private String functionName;

	/**
	 * 0：未启用；1：启用
	 */
	private String functionFlag;

	/**
	 * 方便快速赋予权限。例如：删除功能依赖查询，分配删除功能，查询功能自动选中。
	 */
	public String functionDependency;

	@Override
	public String getIdentityId() {
		return functionCode;
	}

	public MenuFunction(String functionCode, String functionName,
			String functionFlag) throws Exception {		
		this.setFunctionCode(functionCode);
		this.setFunctionName(functionName);
		this.setFunctionFlag(functionFlag);		
	}
	
	
	public MenuFunction(String functionCode, String functionName,
			String functionFlag, String menuCode) throws Exception {		
		this(functionCode, functionName, functionFlag);
		this.menuCode = menuCode;		
	}

	public void setFunctionCode(String functionCode) throws Exception {
		NotNull(functionCode, "系统功能编码");
		this.functionCode = functionCode;
	}

	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionName(String functionName) throws Exception {
		NotNull(functionName, "系统功能名称");
		this.functionName = functionName;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionFlag(String functionFlag) throws Exception {
		NotNull(functionFlag, "启用标记");
		this.functionFlag = functionFlag;
	}

	public String getFunctionFlag() {
		return functionFlag;
	}
}

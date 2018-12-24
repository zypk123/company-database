package cy.its.syscfg.domain.model.permission;

import cy.its.com.domain.Value;

public class RoleFunctionKey extends Value {
	private String roleId;

	private String functionCode;

	public RoleFunctionKey(String roleId, String functionCode) {
		this.roleId = roleId;
		this.functionCode = functionCode;
	}

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @return the functionCode
	 */
	public String getFunctionCode() {
		return functionCode;
	}

	@Override
	protected boolean IsEmpty() {
		return stringIsEmpty(this.roleId) || stringIsEmpty(this.functionCode);
	}

}

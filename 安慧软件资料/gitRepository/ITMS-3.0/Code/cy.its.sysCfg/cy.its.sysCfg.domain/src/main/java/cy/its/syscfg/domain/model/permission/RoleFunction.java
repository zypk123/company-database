package cy.its.syscfg.domain.model.permission;

import cy.its.com.domain.Entity;

public class RoleFunction extends Entity<RoleFunctionKey> {

	/**
	 * 角色功能key
	 */
	private RoleFunctionKey roleFunctionKey;


	/**
	 * 数据权限表达式
	 */
	public java.lang.String dataAccessFormula;

	/**
	 * 备注
	 */
	public java.lang.String remark;

	@SuppressWarnings("unused")
	private RoleFunction() {
	}

	public RoleFunction(RoleFunctionKey roleFunctionKey)
			throws Exception {
		this.setRoleFunctionKey(roleFunctionKey);
	}
	
	public void setRoleFunctionKey(RoleFunctionKey roleFunctionKey)
			throws Exception {
		NotNull(roleFunctionKey, "角色权限关键字");
		this.roleFunctionKey = roleFunctionKey;
	}

	public RoleFunctionKey getRoleFunctionKey() {
		return roleFunctionKey;
	}

	@Override
	public RoleFunctionKey getIdentityId() {
		return roleFunctionKey;
	}
}

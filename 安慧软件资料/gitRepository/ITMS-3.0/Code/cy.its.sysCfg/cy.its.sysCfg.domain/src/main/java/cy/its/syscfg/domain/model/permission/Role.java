package cy.its.syscfg.domain.model.permission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cy.its.com.domain.AggregateRoot;

public class Role extends AggregateRoot {

	/**
	 * 角色ID
	 */
	private String roleId;

	/**
	 * 角色名
	 */
	private String roleName;

	/**
	 * 0：未启用；1：启用。
	 */
	private String roleEnableFlag;

	/**
	 * 备注
	 */
	public String roleRemark;
	
	/**
	 * 数据权限编码
	 */
	private String dataAccessType;
	
	private String orgCode;

	/**
	 * 功能列表
	 */
	private Map<String, RoleFunction> functionMap;

	public Role(String roleName, String roleEnableFlag) {
		// List<RoleFunction> functionList
		this.setRoleName(roleName);
		this.setRoleEnableFlag(roleEnableFlag);
		// functionMap = new HashMap<String, RoleFunction>();
		// for (RoleFunction roleFunction : functionList) {
		// if(functionMap.containsKey(roleFunction.getIdentityId().getFunctionCode())){
		//
		//
		// }
		// }

	}

	public Role(String roleId, String roleName, String roleEnableFlag,
			List<RoleFunction> functionList) {
		this(roleName, roleEnableFlag);
		this.setRoleId(roleId);

		functionMap = new HashMap<String, RoleFunction>();
		if (functionList != null) {
			for (RoleFunction roleFunction : functionList) {
				String key = roleFunction.getIdentityId().getFunctionCode();
				if (!functionMap.containsKey(key)) {
					functionMap.put(key, roleFunction);
				}
			}
		}
	}

	@Override
	public String getIdentityId() {
		return roleId;
	}

	/**
	 * 返回所有角色所用功能列表
	 * 
	 * @return
	 */
	public RoleFunction[] allFunctions() {
		if (functionMap == null) {
			return null;
		}

		RoleFunction[] roleFunctions = new RoleFunction[functionMap.size()];
		return functionMap.values().toArray(roleFunctions);
	}

	/**
	 * 为角色添加功能
	 * 
	 * @param functions
	 * @throws Exception
	 */
	public void addFunctions(RoleFunction[] roleFunctions) throws Exception {

		if (functionMap == null) {
			throw new Exception("角色尚未创建,不能为其添加角色功能");
		}

		String functionCode;
		for (RoleFunction roleFunction : roleFunctions) {
			if (!this.roleId.equals(roleFunction.getIdentityId().getRoleId())) {
				throw new Exception("添加的角色功能不属于当前角色");
			}

			functionCode = roleFunction.getIdentityId().getFunctionCode();

			if (this.functionMap.containsKey(functionCode)) {
				throw new Exception("添加的角色功能有重复");
			}

			this.functionMap.put(functionCode, roleFunction);
		}
	}

	public void removeFunctions(String[] functionCodes) {
		for (String code : functionCodes) {
			functionMap.remove(code);
		}
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleEnableFlag() {
		return roleEnableFlag;
	}

	public void setRoleEnableFlag(String roleEnableFlag) {
		this.roleEnableFlag = roleEnableFlag;
	}

	/**
	 * getter method
	 * @return the dataAccessType
	 */
	
	public String getDataAccessType() {
		return dataAccessType;
	}

	/**
	 * setter method
	 * @param dataAccessType the dataAccessType to set
	 */
	
	public void setDataAccessType(String dataAccessType) {
		this.dataAccessType = dataAccessType;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

}

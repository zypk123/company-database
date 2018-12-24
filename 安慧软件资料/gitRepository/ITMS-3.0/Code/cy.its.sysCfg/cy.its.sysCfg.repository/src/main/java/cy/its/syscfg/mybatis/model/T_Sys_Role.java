package cy.its.syscfg.mybatis.model;

public class T_Sys_Role {
    private String roleId;

    private String roleName;

    private String roleEnableFlag;

    private String roleRemark;
    
    private String dataAccessType;
    
    private String orgCode;

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

    public String getRoleRemark() {
        return roleRemark;
    }

    public void setRoleRemark(String roleRemark) {
        this.roleRemark = roleRemark;
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
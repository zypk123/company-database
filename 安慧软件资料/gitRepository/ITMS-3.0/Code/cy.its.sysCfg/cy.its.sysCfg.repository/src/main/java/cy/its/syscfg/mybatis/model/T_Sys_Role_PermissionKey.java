package cy.its.syscfg.mybatis.model;

public class T_Sys_Role_PermissionKey {
    private String functionCode;

    private String roleId;

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
package cy.its.syscfg.mybatis.model;

public class T_Sys_Role_Permission extends T_Sys_Role_PermissionKey {
    private String dataAccessFormula;

    private String remark;


    public String getDataAccessFormula() {
        return dataAccessFormula;
    }

    public void setDataAccessFormula(String dataAccessFormula) {
        this.dataAccessFormula = dataAccessFormula;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
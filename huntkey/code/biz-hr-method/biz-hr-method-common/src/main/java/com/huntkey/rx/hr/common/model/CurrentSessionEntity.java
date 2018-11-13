package com.huntkey.rx.hr.common.model;

/**
 * 当前登录用户对应的企业用户信息，包括当前登录企业中对应的员工信息、岗位信息、部门信息等，根据需要扩展，如岗位名称，部门名称
 * @author zhaomj
 */
public class CurrentSessionEntity {
    private String employeeId;
    private String positionId;
    private String enterpriseId;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}

package cy.its.syscfg.mybatis.model;

import java.util.Date;

public class T_Sys_User {
    private String userId;

    private String otherOrgId;

    private String orgId;

    private String policeId;

    private String loginName;

    private String loginPassword;

    private String permissionIpList;

    private String isOnline;

    private Date latestLoginTime;

    private Integer totalLoginCounts;

    private String name;

    private Date validDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOtherOrgId() {
        return otherOrgId;
    }

    public void setOtherOrgId(String otherOrgId) {
        this.otherOrgId = otherOrgId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getPermissionIpList() {
        return permissionIpList;
    }

    public void setPermissionIpList(String permissionIpList) {
        this.permissionIpList = permissionIpList;
    }

    public String getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(String isOnline) {
        this.isOnline = isOnline;
    }

    public Date getLatestLoginTime() {
        return latestLoginTime;
    }

    public void setLatestLoginTime(Date latestLoginTime) {
        this.latestLoginTime = latestLoginTime;
    }

    public Integer getTotalLoginCounts() {
        return totalLoginCounts;
    }

    public void setTotalLoginCounts(Integer totalLoginCounts) {
        this.totalLoginCounts = totalLoginCounts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }
}
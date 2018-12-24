package cy.its.syscfg.domain.model.traffic;

import cy.its.com.dto.BaseDto;

public class SysArea  extends BaseDto{
    private String areaId;

    private String areaName;

    private String areaType;

    private String siteCodeList;

    private String orgId;

    private String orgPrivilegeCode;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getSiteCodeList() {
        return siteCodeList;
    }

    public void setSiteCodeList(String siteCodeList) {
        this.siteCodeList = siteCodeList;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgPrivilegeCode() {
        return orgPrivilegeCode;
    }

    public void setOrgPrivilegeCode(String orgPrivilegeCode) {
        this.orgPrivilegeCode = orgPrivilegeCode;
    }
}
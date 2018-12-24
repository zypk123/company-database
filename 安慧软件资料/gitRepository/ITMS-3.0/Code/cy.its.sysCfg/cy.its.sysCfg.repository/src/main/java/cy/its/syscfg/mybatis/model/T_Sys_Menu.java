package cy.its.syscfg.mybatis.model;

public class T_Sys_Menu {
    private String menuCode;

    private String sysConfigId;

    private String menuName;

    private String targetUrl;

    private String parentCode;

    private String sortIndex;

    private String menuEnableFlag;

    private String remark;

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getSysConfigId() {
        return sysConfigId;
    }

    public void setSysConfigId(String sysConfigId) {
        this.sysConfigId = sysConfigId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(String sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getMenuEnableFlag() {
        return menuEnableFlag;
    }

    public void setMenuEnableFlag(String menuEnableFlag) {
        this.menuEnableFlag = menuEnableFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
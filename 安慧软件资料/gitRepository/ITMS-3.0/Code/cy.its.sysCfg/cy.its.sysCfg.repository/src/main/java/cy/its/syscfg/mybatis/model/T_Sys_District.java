package cy.its.syscfg.mybatis.model;

public class T_Sys_District {
    private String districtCode;

    private String districtName;

    private String parentDistrictCode;

    private String districtGrade;

    private String cancelFlag;

    private String remark;

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getParentDistrictCode() {
        return parentDistrictCode;
    }

    public void setParentDistrictCode(String parentDistrictCode) {
        this.parentDistrictCode = parentDistrictCode;
    }

    public String getDistrictGrade() {
        return districtGrade;
    }

    public void setDistrictGrade(String districtGrade) {
        this.districtGrade = districtGrade;
    }

    public String getCancelFlag() {
        return cancelFlag;
    }

    public void setCancelFlag(String cancelFlag) {
        this.cancelFlag = cancelFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
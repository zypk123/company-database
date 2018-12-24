package cy.its.device.domain.model;

import java.util.Date;

public class Contractor {
    private String contractorId;

    private String contractorName;

    private String addressDesc;

    private String contractorPerson;

    private String contractorTel;

    private String createBy;

    private Date createTime;

    private String remark;

    public String getContractorId() {
        return contractorId;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
    }

    public String getContractorName() {
        return contractorName;
    }

    public void setContractorName(String contractorName) {
        this.contractorName = contractorName;
    }

    public String getAddressDesc() {
        return addressDesc;
    }

    public void setAddressDesc(String addressDesc) {
        this.addressDesc = addressDesc;
    }

    public String getContractorPerson() {
        return contractorPerson;
    }

    public void setContractorPerson(String contractorPerson) {
        this.contractorPerson = contractorPerson;
    }

    public String getContractorTel() {
        return contractorTel;
    }

    public void setContractorTel(String contractorTel) {
        this.contractorTel = contractorTel;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
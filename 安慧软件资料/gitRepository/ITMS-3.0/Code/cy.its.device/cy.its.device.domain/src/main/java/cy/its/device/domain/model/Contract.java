package cy.its.device.domain.model;

import java.util.Date;
import java.util.List;

import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.ContractCriteria;
import cy.its.device.domain.repository.contract.IContractRepository;

public class Contract {
    private String contractId;

    private String contractorId;

    private String contractNbr;

    private String contractName;

    private String contractType;

    private String orgId;

    private String bidNbr;

    private Date contractTime;

    private String bidContactName;

    private String bidContactTel;

    private Date beginServiceDate;

    private Date endServiceDate;

    private String servicePerson;

    private String servicePersonTel;

    private String remark;

    private String createBy;

    private Date createTime;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractorId() {
        return contractorId;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
    }

    public String getContractNbr() {
        return contractNbr;
    }

    public void setContractNbr(String contractNbr) {
        this.contractNbr = contractNbr;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getBidNbr() {
        return bidNbr;
    }

    public void setBidNbr(String bidNbr) {
        this.bidNbr = bidNbr;
    }

    public Date getContractTime() {
        return contractTime;
    }

    public void setContractTime(Date contractTime) {
        this.contractTime = contractTime;
    }

    public String getBidContactName() {
        return bidContactName;
    }

    public void setBidContactName(String bidContactName) {
        this.bidContactName = bidContactName;
    }

    public String getBidContactTel() {
        return bidContactTel;
    }

    public void setBidContactTel(String bidContactTel) {
        this.bidContactTel = bidContactTel;
    }

    public Date getBeginServiceDate() {
        return beginServiceDate;
    }

    public void setBeginServiceDate(Date beginServiceDate) {
        this.beginServiceDate = beginServiceDate;
    }

    public Date getEndServiceDate() {
        return endServiceDate;
    }

    public void setEndServiceDate(Date endServiceDate) {
        this.endServiceDate = endServiceDate;
    }

    public String getServicePerson() {
        return servicePerson;
    }

    public void setServicePerson(String servicePerson) {
        this.servicePerson = servicePerson;
    }

    public String getServicePersonTel() {
        return servicePersonTel;
    }

    public void setServicePersonTel(String servicePersonTel) {
        this.servicePersonTel = servicePersonTel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
    
    public boolean nbrIsRepeated(IContractRepository repository){
    	ContractCriteria c = new ContractCriteria();
		c.contractNbr = this.contractNbr;		
		List<Contract> lst = repository.findDeviceContracts(c);
		if(lst != null && lst.size() > 0) {
			lst.removeIf(t->t.getContractId().equals(this.contractId));			
			return lst.size() > 0;			
		}
		
		return false;
    }
}
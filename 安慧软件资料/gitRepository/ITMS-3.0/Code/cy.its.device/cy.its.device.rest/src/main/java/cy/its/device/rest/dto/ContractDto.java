package cy.its.device.rest.dto;

import cy.its.com.dto.BaseDto;

public class ContractDto extends BaseDto{
	private String contractId;				//合同ID

    private String contractorId;			//承建商ID

    private String contractNbr;				//合同编号

    private String contractName;			//合同名称

    private String contractType;			//合同类型

    private String orgId;					//机构ID

    private String bidNbr;					//招标编号

    private String contractTime;			//合同签署时间
    
	public String contractTimeFrom;			//签署时间MIN
	
	public String contractTimeTo;			//签署时间MAX

    private String bidContactName;			//投标联系人

    private String bidContactTel;			//投标联系人电话

    private String beginServiceDate;		//售后服务期始

    private String endServiceDate;			//售后服务期至

    private String servicePerson;			//售后服务联系人

    private String servicePersonTel;		//售后服务联系人电话

    private String remark;					//备注

    private String createBy;				//创建人

    private String createTime;				//创建时间
    
    private int pageNumber;					//当前页
    
    private int pageSize;					//页大小

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

	public String getContractTime() {
		return contractTime;
	}

	public void setContractTime(String contractTime) {
		this.contractTime = contractTime;
	}
	
	public String getContractTimeFrom() {
		return contractTimeFrom;
	}

	public void setContractTimeFrom(String contractTimeFrom) {
		this.contractTimeFrom = contractTimeFrom;
	}

	public String getContractTimeTo() {
		return contractTimeTo;
	}

	public void setContractTimeTo(String contractTimeTo) {
		this.contractTimeTo = contractTimeTo;
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

	public String getBeginServiceDate() {
		return beginServiceDate;
	}

	public void setBeginServiceDate(String beginServiceDate) {
		this.beginServiceDate = beginServiceDate;
	}

	public String getEndServiceDate() {
		return endServiceDate;
	}

	public void setEndServiceDate(String endServiceDate) {
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
    
    
}

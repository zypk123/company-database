package cy.its.device.rest.dto;

import cy.its.com.dto.BaseDto;

public class CompanyDto extends BaseDto{
	
	private String contractorId;			//承建商ID

    private String contractorName;			//承建商名称

    private String addressDesc;				//联系地址

    private String contractorPerson;		//承建商售后负责人

    private String contractorTel;			//售后负责人联系方式
    	
    private String createBy;				//创建人

    private String createTime;				//创建时间

    private String remark;					//备注
    
    private int pageNumber;					//当前页
    
    private int pageSize;					//页大小

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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

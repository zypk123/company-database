package cy.its.device.domain.model;

import java.util.Date;
import java.util.List;

public class DeviceMaintainRegister {
    private String maintenanceId;

    private String maintenanceNbr;

    private String maintenanceCompany;

    private String contactPerson;

    private String phoneNbr;

    private String finishTime;

    private String assignBy;

    private Date assignTime;

    private String maintendanceResult;

    private String solution;

    private Date solutionTime;

    private String remark;

    private String maintenanceStatus;

    private String maintenancePhoto;

    private String orgPrivilegeCode;
    
    private String orgName;
    
    private List<DeviceFault> deviceFaults;

    /**
	 * getter method
	 * @return the deviceFaults
	 */
	
	public List<DeviceFault> getDeviceFaults() {
		return deviceFaults;
	}

	/**
	 * setter method
	 * @param deviceFaults the deviceFaults to set
	 */
	
	public void setDeviceFaults(List<DeviceFault> deviceFaults) {
		this.deviceFaults = deviceFaults;
	}

	public String getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(String maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public String getMaintenanceNbr() {
        return maintenanceNbr;
    }

    public void setMaintenanceNbr(String maintenanceNbr) {
        this.maintenanceNbr = maintenanceNbr;
    }

    public String getMaintenanceCompany() {
        return maintenanceCompany;
    }

    public void setMaintenanceCompany(String maintenanceCompany) {
        this.maintenanceCompany = maintenanceCompany;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhoneNbr() {
        return phoneNbr;
    }

    public void setPhoneNbr(String phoneNbr) {
        this.phoneNbr = phoneNbr;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getAssignBy() {
        return assignBy;
    }

    public void setAssignBy(String assignBy) {
        this.assignBy = assignBy;
    }

    public Date getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(Date assignTime) {
        this.assignTime = assignTime;
    }

    public String getMaintendanceResult() {
        return maintendanceResult;
    }

    public void setMaintendanceResult(String maintendanceResult) {
        this.maintendanceResult = maintendanceResult;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Date getSolutionTime() {
        return solutionTime;
    }

    public void setSolutionTime(Date solutionTime) {
        this.solutionTime = solutionTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMaintenanceStatus() {
        return maintenanceStatus;
    }

    public void setMaintenanceStatus(String maintenanceStatus) {
        this.maintenanceStatus = maintenanceStatus;
    }

    public String getMaintenancePhoto() {
        return maintenancePhoto;
    }

    public void setMaintenancePhoto(String maintenancePhoto) {
        this.maintenancePhoto = maintenancePhoto;
    }

    public String getOrgPrivilegeCode() {
        return orgPrivilegeCode;
    }

    public void setOrgPrivilegeCode(String orgPrivilegeCode) {
        this.orgPrivilegeCode = orgPrivilegeCode;
    }

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
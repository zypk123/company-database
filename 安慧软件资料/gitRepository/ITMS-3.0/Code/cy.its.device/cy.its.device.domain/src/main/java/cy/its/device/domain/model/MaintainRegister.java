package cy.its.device.domain.model;

import java.util.Date;

public class MaintainRegister {
	private String maintenanceId;

	private String maintenanceNbr;

	private String maintenanceCompany;

	private String contactPerson;

	private String phoneNbr;

	private String finishTime;

	private String createBy;

	private Date createTime;

	private String maintendanceResult;

	private String solution;

	private Date solutionTime;

	private String remark;

	private String maintenanceStatus;

	private byte[] photo;

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

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	/**
	 * 分配维护单给维护厂家
	 * 
	 * @param maintenanceCompany
	 *            维护厂家
	 * @param contactPerson
	 *            联系人
	 * @param phoneNbr
	 *            联系方式
	 * @param finishTime
	 *            完成时间
	 * @param createBy
	 *            分派人
	 */
	public void assignToCompany(String maintenanceCompany,
			String contactPerson, String phoneNbr, String finishTime,
			String createBy) {
		this.maintenanceCompany = maintenanceCompany;
		this.contactPerson = contactPerson;
		this.phoneNbr = phoneNbr;
		this.finishTime = finishTime;
		this.createBy = createBy;
		this.createTime = new Date();
		// 分配维护单状态为已维护
	}

	/**
	 * 登记维护结果
	 * 
	 * @param maintendanceResult
	 * @param solution
	 * @param solutionTime
	 * @param remark
	 */
	public void regMaintainResult(String maintendanceResult,
			String solution, Date solutionTime, String remark) {
		this.maintendanceResult = maintendanceResult;
		this.solution = solution;
		this.solutionTime = solutionTime;
		this.remark = remark;
	}
}
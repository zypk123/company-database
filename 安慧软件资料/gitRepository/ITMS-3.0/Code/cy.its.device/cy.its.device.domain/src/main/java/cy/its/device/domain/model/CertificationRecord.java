package cy.its.device.domain.model;

import java.util.Date;

/**
 * 
 * @ClassName: CertificationRecord
 * @Description: 检定证书批量导入MODEL
 * @author wangk@cychina.cn
 * @date 2016年3月25日 上午10:24:43
 *
 */
public class CertificationRecord {

	/** ID */
	private String recordId;
	/** 对应设备表ID */
	private String deviceId;
	/** 导入批次 */
	private String importDate;
	/** 设备编号 */
	private String equipmentNbr;
	/** 证书编号 */
	private String certificationNbr;
	/** 设备类型 */
	private String equipmentType;
	/** 设备类型 */
	private String equipmentName;
	/** 委托单位 */
	private String orgName;
	/** 器具名称 */
	private String deviceName;
	/** 设备型号 */
	private String modelName;
	/** 检定日期 */
	private Date certificatedDate;
	/** 有效日期 */
	private Date expireDate;
	/** 检定状态 */
	private String expireMark;
	/** 检定结果 */
	private String certificatedResult;
	/** 检定机构 */
	private String certificatedOrg;
	/** 检定人 */
	private String certificatedPerson;
	/** 导入标记 */
	private String importMark;
	/** 导入时间 */
	private Date createTime;
	/** 导入人 */
	private String createPerson;
	/** 修改时间 */
	private Date updateTime;
	/** 修改人 */
	private String updatePerson;
	/** 备注 */
	private String remark;
	/** 导入流水 */
	private String importAccept;

	/**
	 * getter method
	 * 
	 * @return the recordId
	 */
	public String getRecordId() {
		return recordId;
	}

	/**
	 * setter method
	 * 
	 * @param recordId
	 *            the recordId to set
	 */
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	/**
	 * getter method
	 * 
	 * @return the importDate
	 */
	public String getImportDate() {
		return importDate;
	}

	/**
	 * setter method
	 * 
	 * @param importDate
	 *            the importDate to set
	 */
	public void setImportDate(String importDate) {
		this.importDate = importDate;
	}

	/**
	 * getter method
	 * 
	 * @return the equipmentType
	 */
	public String getEquipmentType() {
		return equipmentType;
	}

	/**
	 * setter method
	 * 
	 * @param equipmentType
	 *            the equipmentType to set
	 */
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	/**
	 * getter method
	 * 
	 * @return the certificationNbr
	 */
	public String getCertificationNbr() {
		return certificationNbr;
	}

	/**
	 * setter method
	 * 
	 * @param certificationNbr
	 *            the certificationNbr to set
	 */
	public void setCertificationNbr(String certificationNbr) {
		this.certificationNbr = certificationNbr;
	}

	/**
	 * getter method
	 * 
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * setter method
	 * 
	 * @param orgName
	 *            the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * getter method
	 * 
	 * @return the deviceName
	 */
	public String getDeviceName() {
		return deviceName;
	}

	/**
	 * setter method
	 * 
	 * @param deviceName
	 *            the deviceName to set
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	/**
	 * getter method
	 * 
	 * @return the modelName
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * setter method
	 * 
	 * @param modelName
	 *            the modelName to set
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/**
	 * getter method
	 * 
	 * @return the certificatedDate
	 */
	public Date getCertificatedDate() {
		return certificatedDate;
	}

	/**
	 * setter method
	 * 
	 * @param certificatedDate
	 *            the certificatedDate to set
	 */
	public void setCertificatedDate(Date certificatedDate) {
		this.certificatedDate = certificatedDate;
	}

	/**
	 * getter method
	 * 
	 * @return the expireDate
	 */
	public Date getExpireDate() {
		return expireDate;
	}

	/**
	 * setter method
	 * 
	 * @param expireDate
	 *            the expireDate to set
	 */
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	/**
	 * getter method
	 * 
	 * @return the expireMark
	 */
	public String getExpireMark() {
		return expireMark;
	}

	/**
	 * setter method
	 * 
	 * @param expireMark
	 *            the expireMark to set
	 */
	public void setExpireMark(String expireMark) {
		this.expireMark = expireMark;
	}

	/**
	 * getter method
	 * 
	 * @return the certificatedResult
	 */
	public String getCertificatedResult() {
		return certificatedResult;
	}

	/**
	 * setter method
	 * 
	 * @param certificatedResult
	 *            the certificatedResult to set
	 */
	public void setCertificatedResult(String certificatedResult) {
		this.certificatedResult = certificatedResult;
	}

	/**
	 * getter method
	 * 
	 * @return the certificatedOrg
	 */
	public String getCertificatedOrg() {
		return certificatedOrg;
	}

	/**
	 * setter method
	 * 
	 * @param certificatedOrg
	 *            the certificatedOrg to set
	 */
	public void setCertificatedOrg(String certificatedOrg) {
		this.certificatedOrg = certificatedOrg;
	}

	/**
	 * getter method
	 * 
	 * @return the certificatedPerson
	 */
	public String getCertificatedPerson() {
		return certificatedPerson;
	}

	/**
	 * setter method
	 * 
	 * @param certificatedPerson
	 *            the certificatedPerson to set
	 */
	public void setCertificatedPerson(String certificatedPerson) {
		this.certificatedPerson = certificatedPerson;
	}

	/**
	 * getter method
	 * 
	 * @return the importMark
	 */
	public String getImportMark() {
		return importMark;
	}

	/**
	 * setter method
	 * 
	 * @param importMark
	 *            the importMark to set
	 */
	public void setImportMark(String importMark) {
		this.importMark = importMark;
	}

	/**
	 * getter method
	 * 
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * setter method
	 * 
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * getter method
	 * 
	 * @return the createPerson
	 */
	public String getCreatePerson() {
		return createPerson;
	}

	/**
	 * setter method
	 * 
	 * @param createPerson
	 *            the createPerson to set
	 */
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	/**
	 * getter method
	 * 
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * setter method
	 * 
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * getter method
	 * 
	 * @return the updatePerson
	 */
	public String getUpdatePerson() {
		return updatePerson;
	}

	/**
	 * setter method
	 * 
	 * @param updatePerson
	 *            the updatePerson to set
	 */
	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}

	/**
	 * getter method
	 * 
	 * @return the equipmentNbr
	 */
	public String getEquipmentNbr() {
		return equipmentNbr;
	}

	/**
	 * setter method
	 * 
	 * @param equipmentNbr
	 *            the equipmentNbr to set
	 */
	public void setEquipmentNbr(String equipmentNbr) {
		this.equipmentNbr = equipmentNbr;
	}

	/**
	 * getter method
	 * 
	 * @return the equipmentName
	 */
	public String getEquipmentName() {
		return equipmentName;
	}

	/**
	 * setter method
	 * 
	 * @param equipmentName
	 *            the equipmentName to set
	 */
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	/**
	 * getter method
	 * 
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * setter method
	 * 
	 * @param deviceId
	 *            the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * getter method
	 * 
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * setter method
	 * 
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * getter method
	 * 
	 * @return the importAccept
	 */
	public String getImportAccept() {
		return importAccept;
	}

	/**
	 * setter method
	 * 
	 * @param importAccept
	 *            the importAccept to set
	 */
	public void setImportAccept(String importAccept) {
		this.importAccept = importAccept;
	}

}
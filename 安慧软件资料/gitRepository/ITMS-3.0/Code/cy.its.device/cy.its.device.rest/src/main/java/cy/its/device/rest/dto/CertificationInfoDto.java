package cy.its.device.rest.dto;

import java.util.Arrays;

import com.wordnik.swagger.annotations.ApiParam;

import cy.its.com.dto.BaseDto;

/**
 * 检定证书添加、修改、查询 入参
 *
 */
public class CertificationInfoDto extends BaseDto {
	/** 检定证书信息表添加修改相关字段 */
	@ApiParam(name="certificationId",value="检定证书ID")
	private String certificationId; // 检定证书ID
	
//	private String deviceId; // 区间系统配置表、电子监控系统表、装备表 ID
	
	@ApiParam(name="certificationNbr",value="证书编号")
	private String certificationNbr; // 证书编号
	
	@ApiParam(name="certificatedDate",value="检定时间")
	private String certificatedDate; // 检定时间
	
	@ApiParam(name="expireDate",value="有效期")
	private String expireDate; // 有效期
	
	@ApiParam(name="certificatedOrg",value="检定机构")
	private String certificatedOrg; // 检定机构
	
	@ApiParam(name="certificatedPerson",value="检定人员")
	private String certificatedPerson; // 检定人员
	
	@ApiParam(name="url",value="质检部门网址")
	private String url; // 质检部门网址
	
	@ApiParam(name="createBy",value="创建人")
	private String createBy; // 创建人
	
	@ApiParam(name="createTime",value="创建时间")
	private String createTime; // 创建时间
	
	@ApiParam(name="updateBy",value="更新人员")
	private String updateBy; // 更新人员
	
	@ApiParam(name="updateTime",value="更新时间")
	private String updateTime; // 更新时间
	
	@ApiParam(name="certificatePhoto",value="证书图片")
	private String certificatePhoto; // 证书图片
	
	@ApiParam(name="certificatedResult",value="检定结果")
	private String certificatedResult; // 检定结果
	
	@ApiParam(name="remark",value="备注")
	private String remark; // 备注
	
	@ApiParam(name="equipmentNbr",value="设备系统编号（目前包含 区间系统配置表、电子监控系统表、装备表）")
	private String equipmentNbr; // 设备系统编号（目前包含 区间系统配置表、电子监控系统表、装备表）
	
	@ApiParam(name="orgName",value="设备所属机构名称")
	private String orgName; // 设备所属机构名称
	
	@ApiParam(name="equipmentType",value="设备类型")
	private String equipmentType; // 设备类型

	/** 检定证书信息记录表查询展示用到相关字段 */
	@ApiParam(name="recordId",value="检定证书信息记录表ID")
	private String recordId; // 检定证书信息记录表ID
	
	@ApiParam(name="importDate",value="检定批次")
	private String importDate; // 检定批次
	
	@ApiParam(name="deviceName",value="器具名称")
	private String deviceName; // 器具名称
	
	@ApiParam(name="modelName",value="设备型号")
	private String modelName; // 设备型号
	
	@ApiParam(name="importMark",value="导入标记")
	private String importMark; // 导入标记

	@ApiParam(name="equipmentName",value="设备名称")
	private String equipmentName; // 设备名称
	
	@ApiParam(name="equipmentTypeName",value="设备类型名称")
	private String equipmentTypeName; // 设备类型名称
	
	@ApiParam(name="orgId",value="设备所属机构")
	private String orgId; // 设备所属机构
	
	@ApiParam(name="beginCertificatedDate",value="起始检定时间")
	private String beginCertificatedDate; // 起始检定时间
	
	@ApiParam(name="endCertificatedDate",value="截止检定时间")
	private String endCertificatedDate; // 截止检定时间
	
	@ApiParam(name="upLoadPath",value="上传文件路径")
	private String upLoadPath; // 上传文件路径

	@ApiParam(name="recordIdArray",value="检定证书信息记录表ID")
	private String[] recordIdArray; // 检定证书信息记录表ID
	
	@ApiParam(name="expireDateSectionArray",value="检定状态")
	private String[] expireDateSectionArray; // 检定状态
	
	@ApiParam(name="importMarkArray",value="导入标记")
	private String[] importMarkArray; // 导入标记
	
	@ApiParam(name="equipmentTypeArray",value="设备类型")
	private String[] equipmentTypeArray; // 设备类型

	/** Excel批量导入结果相关字段 */
	@ApiParam(name="importSucessCount",value="导入成功数量")
	private int importSucessCount; // 导入成功数量
	
	@ApiParam(name="unRegisteredCount",value="未登记数量")
	private int unRegisteredCount; // 未登记数量
	
	@ApiParam(name="unPoliceCount",value="非交警设备数量")
	private int unPoliceCount; // 非交警设备数量
	
	@ApiParam(name="otherCount",value="其他原因数量")
	private int otherCount; // 其他原因数量
	
	@ApiParam(name="repeatCount",value="重复数量")
	private int repeatCount; // 重复数量
	
	
	@ApiParam(name="errMsg",value="检定同步错误信息")
	private String errMsg; // 检定同步错误信息
	
	
	public String getCertificationId() {
		return certificationId;
	}

	public void setCertificationId(String certificationId) {
		this.certificationId = certificationId;
	}

	public String getCertificationNbr() {
		return certificationNbr;
	}

	public void setCertificationNbr(String certificationNbr) {
		this.certificationNbr = certificationNbr;
	}

	public String getCertificatedDate() {
		return certificatedDate;
	}

	public void setCertificatedDate(String certificatedDate) {
		this.certificatedDate = certificatedDate;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getCertificatedOrg() {
		return certificatedOrg;
	}

	public void setCertificatedOrg(String certificatedOrg) {
		this.certificatedOrg = certificatedOrg;
	}

	public String getCertificatedPerson() {
		return certificatedPerson;
	}

	public void setCertificatedPerson(String certificatedPerson) {
		this.certificatedPerson = certificatedPerson;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getCertificatePhoto() {
		return certificatePhoto;
	}

	public void setCertificatePhoto(String certificatePhoto) {
		this.certificatePhoto = certificatePhoto;
	}

	public String getCertificatedResult() {
		return certificatedResult;
	}

	public void setCertificatedResult(String certificatedResult) {
		this.certificatedResult = certificatedResult;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
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
	 * @return the beginCertificatedDate
	 */
	public String getBeginCertificatedDate() {
		return beginCertificatedDate;
	}

	/**
	 * setter method
	 * 
	 * @param beginCertificatedDate
	 *            the beginCertificatedDate to set
	 */
	public void setBeginCertificatedDate(String beginCertificatedDate) {
		this.beginCertificatedDate = beginCertificatedDate;
	}

	/**
	 * getter method
	 * 
	 * @return the endCertificatedDate
	 */
	public String getEndCertificatedDate() {
		return endCertificatedDate;
	}

	/**
	 * setter method
	 * 
	 * @param endCertificatedDate
	 *            the endCertificatedDate to set
	 */
	public void setEndCertificatedDate(String endCertificatedDate) {
		this.endCertificatedDate = endCertificatedDate;
	}

	/**
	 * getter method
	 * 
	 * @return the expireDateSectionArray
	 */
	public String[] getExpireDateSectionArray() {
		return expireDateSectionArray;
	}

	/**
	 * setter method
	 * 
	 * @param expireDateSectionArray
	 *            the expireDateSectionArray to set
	 */
	public void setExpireDateSectionArray(String[] expireDateSectionArray) {
		this.expireDateSectionArray = expireDateSectionArray;
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
	 * @return the upLoadPath
	 */
	public String getUpLoadPath() {
		return upLoadPath;
	}

	/**
	 * setter method
	 * 
	 * @param upLoadPath
	 *            the upLoadPath to set
	 */
	public void setUpLoadPath(String upLoadPath) {
		this.upLoadPath = upLoadPath;
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
	 * @return the equipmentType
	 */
	public String getEquipmentTypeName() {
		return equipmentTypeName;
	}

	/**
	 * setter method
	 * 
	 * @param equipmentType
	 *            the equipmentType to set
	 */
	public void setEquipmentTypeName(String equipmentTypeName) {
		this.equipmentTypeName = equipmentTypeName;
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
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * setter method
	 * 
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
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
	 * @return the recordIdArray
	 */
	public String[] getRecordIdArray() {
		return recordIdArray;
	}

	/**
	 * setter method
	 * 
	 * @param recordIdArray
	 *            the recordIdArray to set
	 */
	public void setRecordIdArray(String[] recordIdArray) {
		this.recordIdArray = recordIdArray;
	}

	/**
	 * getter method
	 * 
	 * @return the importMarkArray
	 */
	public String[] getImportMarkArray() {
		return importMarkArray;
	}

	/**
	 * setter method
	 * 
	 * @param importMarkArray
	 *            the importMarkArray to set
	 */
	public void setImportMarkArray(String[] importMarkArray) {
		this.importMarkArray = importMarkArray;
	}

	/**
	 * getter method
	 * 
	 * @return the equipmentTypeArray
	 */
	public String[] getEquipmentTypeArray() {
		return equipmentTypeArray;
	}

	/**
	 * setter method
	 * 
	 * @param equipmentTypeArray
	 *            the equipmentTypeArray to set
	 */
	public void setEquipmentTypeArray(String[] equipmentTypeArray) {
		this.equipmentTypeArray = equipmentTypeArray;
	}

	/**
	 * getter method
	 * 
	 * @return the importSucessCount
	 */
	public int getImportSucessCount() {
		return importSucessCount;
	}

	/**
	 * setter method
	 * 
	 * @param importSucessCount
	 *            the importSucessCount to set
	 */
	public void setImportSucessCount(int importSucessCount) {
		this.importSucessCount = importSucessCount;
	}

	/**
	 * getter method
	 * 
	 * @return the unRegisteredCount
	 */
	public int getUnRegisteredCount() {
		return unRegisteredCount;
	}

	/**
	 * setter method
	 * 
	 * @param unRegisteredCount
	 *            the unRegisteredCount to set
	 */
	public void setUnRegisteredCount(int unRegisteredCount) {
		this.unRegisteredCount = unRegisteredCount;
	}

	/**
	 * getter method
	 * 
	 * @return the unPoliceCount
	 */
	public int getUnPoliceCount() {
		return unPoliceCount;
	}

	/**
	 * setter method
	 * 
	 * @param unPoliceCount
	 *            the unPoliceCount to set
	 */
	public void setUnPoliceCount(int unPoliceCount) {
		this.unPoliceCount = unPoliceCount;
	}

	/**
	 * getter method
	 * 
	 * @return the otherCount
	 */
	public int getOtherCount() {
		return otherCount;
	}

	/**
	 * setter method
	 * 
	 * @param otherCount
	 *            the otherCount to set
	 */
	public void setOtherCount(int otherCount) {
		this.otherCount = otherCount;
	}

	/**
	 * getter method
	 * 
	 * @return the repeatCount
	 */
	public int getRepeatCount() {
		return repeatCount;
	}

	/**
	 * setter method
	 * 
	 * @param repeatCount
	 *            the repeatCount to set
	 */
	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}
	
	/**
	 * getter method
	 * @return the errMsg
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * setter method
	 * @param errMsg the errMsg to set
	 */
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	/*
	  * <p>Title: toString</p>
	  * <p>Description: </p>
	  * @return
	  * @see java.lang.Object#toString()
	  */
	@Override
	public String toString() {
		return "CertificationInfoDto [certificationId=" + certificationId 
				+ ", certificationNbr=" + certificationNbr + ", certificatedDate=" + certificatedDate + ", expireDate="
				+ expireDate + ", certificatedOrg=" + certificatedOrg + ", certificatedPerson=" + certificatedPerson
				+ ", url=" + url + ", createBy=" + createBy + ", createTime=" + createTime + ", updateBy=" + updateBy
				+ ", updateTime=" + updateTime + ", certificatePhoto=" + certificatePhoto + ", certificatedResult="
				+ certificatedResult + ", remark=" + remark + ", equipmentNbr=" + equipmentNbr + ", orgName=" + orgName
				+ ", equipmentType=" + equipmentType + ", recordId=" + recordId + ", importDate=" + importDate
				+ ", deviceName=" + deviceName + ", modelName=" + modelName + ", importMark=" + importMark
				+ ", equipmentName=" + equipmentName + ", equipmentTypeName=" + equipmentTypeName + ", orgId=" + orgId
				+ ", beginCertificatedDate=" + beginCertificatedDate + ", endCertificatedDate=" + endCertificatedDate
				+ ", upLoadPath=" + upLoadPath + ", recordIdArray=" + Arrays.toString(recordIdArray)
				+ ", expireDateSectionArray=" + Arrays.toString(expireDateSectionArray) + ", importMarkArray="
				+ Arrays.toString(importMarkArray) + ", equipmentTypeArray=" + Arrays.toString(equipmentTypeArray)
				+ ", importSucessCount=" + importSucessCount + ", unRegisteredCount=" + unRegisteredCount
				+ ", unPoliceCount=" + unPoliceCount + ", otherCount=" + otherCount + ", repeatCount=" + repeatCount
				+ "]";
	}
	
	

}

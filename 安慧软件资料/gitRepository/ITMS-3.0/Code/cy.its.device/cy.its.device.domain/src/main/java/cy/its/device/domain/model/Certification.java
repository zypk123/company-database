package cy.its.device.domain.model;

import java.util.Date;

/**
 * 
  * @ClassName: Certification
  * @Description: 检定证书导入MODEL
  *
 */
public class Certification {
	/**检定证书表ID*/
    private String certificationId;
    /**设备表ID*/
    private String deviceId;
    /**检定证书编号*/
    private String certificationNbr;
    /**检定日期*/
    private Date certificatedDate;
    /**有效日期*/
    private Date expireDate;
    /**检定机构*/
    private String certificatedOrg;
    /**检定人*/
    private String certificatedPerson;
    /**质检部门网址*/
    private String url;
    /**创建人*/
    private String createBy;
    /**创建时间*/
    private Date createTime;
    /**更新人员*/
    private String updateBy;
    /**备注*/
    private String remark;
    /**检定结果*/
    private String certificatedResult;
    /**证书图片*/
    private String certificatePhoto;
    /**设备编码*/
    private String equipmentNbr;
    /**设备型号*/
    private String equipmentType;
    /**是否登记*/
    private String isRegistered;
    /**委托单位*/
    private String orgName;
    /**更新时间*/
    private Date updateTime;

    public String getCertificationId() {
        return certificationId;
    }

    public void setCertificationId(String certificationId) {
        this.certificationId = certificationId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCertificationNbr() {
        return certificationNbr;
    }

    public void setCertificationNbr(String certificationNbr) {
        this.certificationNbr = certificationNbr;
    }

    public Date getCertificatedDate() {
        return certificatedDate;
    }

    public void setCertificatedDate(Date certificatedDate) {
        this.certificatedDate = certificatedDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCertificatedResult() {
        return certificatedResult;
    }

    public void setCertificatedResult(String certificatedResult) {
        this.certificatedResult = certificatedResult;
    }

    public String getCertificatePhoto() {
        return certificatePhoto;
    }

    public void setCertificatePhoto(String certificatePhoto) {
        this.certificatePhoto = certificatePhoto;
    }

	
	/**
	 * getter method
	 * @return the equipmentNbr
	 */
	public String getEquipmentNbr() {
		return equipmentNbr;
	}

	
	/**
	 * setter method
	 * @param equipmentNbr the equipmentNbr to set
	 */
	public void setEquipmentNbr(String equipmentNbr) {
		this.equipmentNbr = equipmentNbr;
	}

	
	/**
	 * getter method
	 * @return the equipmentType
	 */
	public String getEquipmentType() {
		return equipmentType;
	}

	
	/**
	 * setter method
	 * @param equipmentType the equipmentType to set
	 */
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	
	/**
	 * getter method
	 * @return the isRegistered
	 */
	public String getIsRegistered() {
		return isRegistered;
	}

	
	/**
	 * setter method
	 * @param isRegistered the isRegistered to set
	 */
	public void setIsRegistered(String isRegistered) {
		this.isRegistered = isRegistered;
	}

	
	/**
	 * getter method
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	
	/**
	 * setter method
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	
	/**
	 * getter method
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	
	/**
	 * setter method
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
    
}
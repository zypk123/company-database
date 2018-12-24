package cy.its.device.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cy.its.com.constant.SysCodeConstant;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.SystemCriteria;
import cy.its.device.domain.repository.ISysRepository;

public class Sys implements Comparable {
	private String deviceId;

	private String deviceSysModelId;

	private String contractId;

	private String deviceSysNbr;

	private String deviceName;

	private String orgId;

	private String deviceType;

	private String deviceBrand;

	private String manufactureSn;

	private String softwareVersion;

	private String networkType;

	private String safeConnectMeans;

	private String deviceIp;

	private Long devicePort;

	private String userName;

	private String password;

	private String simNbr;

	private Date installDate;

	private String installBy;

	private String mountingFacilityType;

	private Date enableUpdateDate;

    private String useStatusFlag;

	private BigDecimal longitude;

	private BigDecimal latitude;

	private String statusType;

	private Date statusUpdateTime;

	private String syncStatus;

	private String createBy;

	private Date createTime;

	private String updateBy;

	private Date updateTime;

	private String remark;

	private String powerType;

	private String transmissionMode;

	private String bandwidth;

	private String ownership;

	private String siteId;

    private String sectionIdList;

    private String collectionType;

    private String orgPrivilegeCode;

    private String contractorId;

    private String architecture;

    private String serverPlatId;
	
    private String verificationMark;
	
    //道路Id
    private String roadId;
	
    
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceSysModelId() {
		return deviceSysModelId;
	}

	public void setDeviceSysModelId(String deviceSysModelId) {
		this.deviceSysModelId = deviceSysModelId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}

	public void setDeviceSysNbr(String deviceSysNbr) {
		this.deviceSysNbr = deviceSysNbr;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceBrand() {
		return deviceBrand;
	}

	public void setDeviceBrand(String deviceBrand) {
		this.deviceBrand = deviceBrand;
	}

	public String getManufactureSn() {
		return manufactureSn;
	}

	public void setManufactureSn(String manufactureSn) {
		this.manufactureSn = manufactureSn;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public String getNetworkType() {
		return networkType;
	}

	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}

	public String getSafeConnectMeans() {
		return safeConnectMeans;
	}

	public void setSafeConnectMeans(String safeConnectMeans) {
		this.safeConnectMeans = safeConnectMeans;
	}

	public String getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	public Long getDevicePort() {
		return devicePort;
	}

	public void setDevicePort(Long devicePort) {
		this.devicePort = devicePort;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSimNbr() {
		return simNbr;
	}

	public void setSimNbr(String simNbr) {
		this.simNbr = simNbr;
	}

	public Date getInstallDate() {
		return installDate;
	}

	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}

	public String getInstallBy() {
		return installBy;
	}

	public void setInstallBy(String installBy) {
		this.installBy = installBy;
	}

	public String getMountingFacilityType() {
		return mountingFacilityType;
	}

	public void setMountingFacilityType(String mountingFacilityType) {
		this.mountingFacilityType = mountingFacilityType;
	}

	public Date getEnableUpdateDate() {
		return enableUpdateDate;
	}

	public void setEnableUpdateDate(Date enableUpdateDate) {
		this.enableUpdateDate = enableUpdateDate;
	}

    public String getUseStatusFlag() {
        return useStatusFlag;
    }

    public void setUseStatusFlag(String useStatusFlag) {
        this.useStatusFlag = useStatusFlag;
    }

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public Date getStatusUpdateTime() {
		return statusUpdateTime;
	}

	public void setStatusUpdateTime(Date statusUpdateTime) {
		this.statusUpdateTime = statusUpdateTime;
	}

	public String getSyncStatus() {
		return syncStatus;
	}

	public void setSyncStatus(String syncStatus) {
		this.syncStatus = syncStatus;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPowerType() {
		return powerType;
	}

	public void setPowerType(String powerType) {
		this.powerType = powerType;
	}

	public String getTransmissionMode() {
		return transmissionMode;
	}

	public void setTransmissionMode(String transmissionMode) {
		this.transmissionMode = transmissionMode;
	}

	public String getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(String bandwidth) {
		this.bandwidth = bandwidth;
	}

	public String getOwnership() {
		return ownership;
	}

	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

    public String getSectionIdList() {
        return sectionIdList;
    }

    public void setSectionIdList(String sectionIdList) {
        this.sectionIdList = sectionIdList;
    }

    public String getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(String collectionType) {
        this.collectionType = collectionType;
    }

    public String getOrgPrivilegeCode() {
        return orgPrivilegeCode;
    }

    public void setOrgPrivilegeCode(String orgPrivilegeCode) {
        this.orgPrivilegeCode = orgPrivilegeCode;
    }

    public String getContractorId() {
        return contractorId;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public String getServerPlatId() {
        return serverPlatId;
    }

    public void setServerPlatId(String serverPlatId) {
        this.serverPlatId = serverPlatId;
    }
	
	public String getRoadId() {
		return roadId;
	}

	public void setRoadId(String roadId) {
		this.roadId = roadId;
	}
	
	public String getVerificationMark() {
        return verificationMark;
    }

    public void setVerificationMark(String verificationMark) {
        this.verificationMark = verificationMark;
    }


	@Override
	public int compareTo(Object o) {
		Sys otherSys = (Sys) o;
		String otherDeviceType = otherSys.getDeviceType();
		return this.deviceType.compareTo(otherDeviceType);
	}
	
	
	public boolean sysNbrIsRepeated(ISysRepository sysRepository){
		SystemCriteria c = new SystemCriteria();
		c.deviceSysNbr = this.deviceSysNbr;
		int count = sysRepository.countSys(c);
		if(count > 0){
			return true;
		}
		
		return false;
	}
	
	public void enable() throws Exception{		
		if(StringUtil.isNullOrEmpty(this.useStatusFlag) ||
				SysCodeConstant.USE_STATUS_NO_USE.equals(this.useStatusFlag) ||
				SysCodeConstant.USE_STATUS_STOP.equals(this.useStatusFlag)) {
			this.useStatusFlag = SysCodeConstant.USE_STATUS_USE;
			this.enableUpdateDate = new Date();
			return;
		}
		
		throw new Exception("设备未处于停用或初始状态, 无法启用");
	}
	
	public void stop() throws Exception {
		if(SysCodeConstant.USE_STATUS_USE.equals(this.useStatusFlag)){
			this.useStatusFlag = SysCodeConstant.USE_STATUS_STOP;
			this.enableUpdateDate = new Date();
			return;
		}

		throw new Exception("设备处于非启用状态, 无法停用");
	}
	
	public void broken() throws Exception {
		if(SysCodeConstant.USE_STATUS_STOP.equals(this.useStatusFlag)){
			this.useStatusFlag = SysCodeConstant.USE_STATUS_BROKEN;
			this.enableUpdateDate = new Date();
			return;
		}

		throw new Exception("设备未处于停用状态, 无法废弃");
	}
	
	public boolean canDelete(){
		return SysCodeConstant.USE_STATUS_NO_USE.equals(this.useStatusFlag) ||
				SysCodeConstant.USE_STATUS_BROKEN.equals(this.useStatusFlag);
	}
	
	public boolean isInUseStatus() {
		return SysCodeConstant.USE_STATUS_USE.equals(this.useStatusFlag);
	}
	
	public void checkLedSysCanModify(Sys oldSys) throws Exception {
		if (oldSys != null && oldSys.isInUseStatus()) {
			// 当前LED系统处于启用状态
			String oldIp = oldSys.getDeviceIp() != null ? oldSys.getDeviceIp() : "";
			String newIp = this.getDeviceIp() != null ? this.getDeviceIp() : "";
			String oldPort = oldSys.getDevicePort() != null ? String.valueOf(oldSys.getDevicePort()) : "";
			String newPort = this.getDevicePort() != null ? String.valueOf(this.getDevicePort()) : "";

			List<String> lstChange = new ArrayList<String>(2);
			if (!oldIp.equals(newIp)) {
				lstChange.add("IP");
			}
			if (!oldPort.equals(newPort)) {
				lstChange.add("端口");
			}

			if (lstChange.size() > 0) {
				throw new Exception("当前系统为启用中的诱导屏系统,不可修改其" + String.join("和", lstChange));
			}
		}
	}
}
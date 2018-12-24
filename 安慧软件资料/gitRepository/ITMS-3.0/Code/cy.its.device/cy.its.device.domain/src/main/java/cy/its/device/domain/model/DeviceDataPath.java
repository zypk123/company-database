package cy.its.device.domain.model;

import java.util.Date;

public class DeviceDataPath {
    private String dataPathId;
    
    private String deviceId;

    private String snapNbr;

    private Date passTime;

    private Date serverReceivingTime;

    private Date ice2mqTime;

    private Date preTime;

    private Date afterTime;

    private Date uploadTime;

    private Date uploadEndTime;

    private Double totalTime;

    private String deviceKey;
    
    private String sysComponentId;			//���ID
    
	private String orgPrivilegeCode;		//����Ȩ�޹��˴���

    public String getDataPathId() {
        return dataPathId;
    }

    public void setDataPathId(String dataPathId) {
        this.dataPathId = dataPathId;
    }

    public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

    public String getSnapNbr() {
        return snapNbr;
    }

    public void setSnapNbr(String snapNbr) {
        this.snapNbr = snapNbr;
    }

    public Date getPassTime() {
        return passTime;
    }

    public void setPassTime(Date passTime) {
        this.passTime = passTime;
    }

    public Date getServerReceivingTime() {
        return serverReceivingTime;
    }

    public void setServerReceivingTime(Date serverReceivingTime) {
        this.serverReceivingTime = serverReceivingTime;
    }

    public Date getIce2mqTime() {
        return ice2mqTime;
    }

    public void setIce2mqTime(Date ice2mqTime) {
        this.ice2mqTime = ice2mqTime;
    }

    public Date getPreTime() {
        return preTime;
    }

    public void setPreTime(Date preTime) {
        this.preTime = preTime;
    }

    public Date getAfterTime() {
        return afterTime;
    }

    public void setAfterTime(Date afterTime) {
        this.afterTime = afterTime;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Date getUploadEndTime() {
        return uploadEndTime;
    }

    public void setUploadEndTime(Date uploadEndTime) {
        this.uploadEndTime = uploadEndTime;
    }

    public Double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Double totalTime) {
        this.totalTime = totalTime;
    }

    public String getDeviceKey() {
        return deviceKey;
    }

    public void setDeviceKey(String deviceKey) {
        this.deviceKey = deviceKey;
    }

	public String getSysComponentId() {
		return sysComponentId;
	}

	public void setSysComponentId(String sysComponentId) {
		this.sysComponentId = sysComponentId;
	}

	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}
    
}
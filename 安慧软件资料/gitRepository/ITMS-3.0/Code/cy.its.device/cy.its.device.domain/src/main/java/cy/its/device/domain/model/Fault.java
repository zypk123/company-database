package cy.its.device.domain.model;

import java.util.Date;

public class Fault {
    private String faultId;

    private String maintenanceId;

    private String deviceId;

    private String faultAlarm;

    private String faultType;

    private String faultSubType;

    private Date startTime;

    private Date endTime;

    private String collectMethod;

    private String remark;

    private String isValidity;

    private String processState;

    private String createBy;

    private Date createTime;

    private String resolveFlag;

    private Date resolveTime;

    public String getFaultId() {
        return faultId;
    }

    public void setFaultId(String faultId) {
        this.faultId = faultId;
    }

    public String getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(String maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getFaultAlarm() {
        return faultAlarm;
    }

    public void setFaultAlarm(String faultAlarm) {
        this.faultAlarm = faultAlarm;
    }

    public String getFaultType() {
        return faultType;
    }

    public void setFaultType(String faultType) {
        this.faultType = faultType;
    }

    public String getFaultSubType() {
        return faultSubType;
    }

    public void setFaultSubType(String faultSubType) {
        this.faultSubType = faultSubType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCollectMethod() {
        return collectMethod;
    }

    public void setCollectMethod(String collectMethod) {
        this.collectMethod = collectMethod;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsValidity() {
        return isValidity;
    }

    public void setIsValidity(String isValidity) {
        this.isValidity = isValidity;
    }

    public String getProcessState() {
        return processState;
    }

    public void setProcessState(String processState) {
        this.processState = processState;
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

    public String getResolveFlag() {
        return resolveFlag;
    }

    public void setResolveFlag(String resolveFlag) {
        this.resolveFlag = resolveFlag;
    }

    public Date getResolveTime() {
        return resolveTime;
    }

    public void setResolveTime(Date resolveTime) {
        this.resolveTime = resolveTime;
    }
    
    /**
	 * 确认故障与报警信息的有效性
	 *
	 * @return
	 */
	public boolean isValidity() {
		return "1".equals(this.isValidity);
	}

	/**
	 * 关联维护单
	 *
	 * @param maintenanceId
	 */
	public final void attachMaintenance(String maintenanceId) {
		this.maintenanceId = maintenanceId;
		this.processState = "1"; // 0 未处理 1 已处理 2 无需处理
	}

	/**
	 * 解除当前故障与维护单的关联
	 * 
	 * @throws Exception
	 */
	public final void disAttachMaintenance(String maintenanceId)
			throws Exception {
		if (this.maintenanceId.equals(maintenanceId)) {
			throw new Exception("当前故障与维护单已解除关联");
		}
		this.maintenanceId = "";
		this.processState = "0"; // 0 未处理; 1 已处理; 2 无需处理;
	}

//	/**
//	 * 更新处理状态
//	 *
//	 * @param processState
//	 */
//	public final void setProcessState(String processState) {
//		this.processState = processState;
//	}
}
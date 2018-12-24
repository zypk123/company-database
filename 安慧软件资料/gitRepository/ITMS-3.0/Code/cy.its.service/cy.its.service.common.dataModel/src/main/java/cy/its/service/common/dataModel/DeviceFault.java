package cy.its.service.common.dataModel;

import java.util.Date;

public class DeviceFault extends Model {

	/**
	 * 设备ID
	 */
	@Mapper("DEVICE_ID")
	private String deviceId;
	
	/**
	 * 组件ID
	 */
	@Mapper("SYS_COMPONENT_ID")
	private String sysComponentId;

	/**
	 * 发生时间
	 */
	@Mapper("FAULT_EVENT_TIME")
	private Date faultEventTime;

	/**
	 * 故障或报警。1-故障；2-报警。
	 */
	@Mapper("FAULT_ALARM")
	private String faultAlarm;

	/**
	 * 故障/报警类型。1：补光异常 100：无雷达信号 101：雷达无联系 102：雷达不测速 103：雷达数据错误 104：高频分机出错
	 * 105：放大电路故障 106：CPU故障 128：离线 2：相机无联系 200：交流供电中断 201：ups电池电压偏低 202：Ups失效
	 * 203：没有Ups信号 3：相机无图像 300：线圈无联系 301：没有线圈信号 302：线圈数据错误 4：没有图像信号 400：长时间无抓拍图像
	 * 401：无通行车辆 402：存储故障 403：没有软件狗
	 * 
	 */
	@Mapper("FAULT_TYPE")
	private String faultType;

	/**
	 * 故障子类型
	 */
	@Mapper("FAULT_SUB_TYPE")
	private String faultSubType;

	/**
	 * 采集方式。1-前端设备上传；2-系统分析；3-人工添加
	 */
	@Mapper("COLLECT_METHOD")
	private String collectMethod;

	/**
	 * 描述
	 */
	@Mapper("REMARK")
	private String remark;
	
	@Mapper("ORG_PRIVILEGE_CODES")
	private String orgPrivilegeCode;
	

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getSysComponentId() {
		return sysComponentId;
	}

	public void setSysComponentId(String sysComponentId) {
		this.sysComponentId = sysComponentId;
	}

	public Date getFaultEventTime() {
		return faultEventTime;
	}

	public void setFaultEventTime(Date faultEventTime) {
		this.faultEventTime = faultEventTime;
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

	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}
}

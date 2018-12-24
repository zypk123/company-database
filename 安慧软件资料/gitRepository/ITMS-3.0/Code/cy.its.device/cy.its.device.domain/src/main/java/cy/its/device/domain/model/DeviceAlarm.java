package cy.its.device.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 设备报警信息
 * @author dell
 *
 */
public class DeviceAlarm {

	/**
	 * 报警ID
	 */
	private String alarmId;
	
	/**
	 * 报警类型
	 */
	private String alarmType;
	
	/**
	 * 报警子类型
	 */
	private String alarmSubType;
	
	/**
	 * 报警设备关联ID
	 */
	private String alarmDeviceId;
	
	/**
	 * 报警设备关联名称
	 */
	private String alarmDeviceName;
	
	/**
	 * 报警开始时间
	 */
	private Date startTime;
	
	/**
	 * 报警结束时间
	 */
	private Date endTime;
	
	/**
	 * 采集方式
	 */
	private String collectWay;
	
	/**
	 * 报警描述
	 */
	private String alarmDisc;
	
	/**
	 * 机构权限编码
	 */
	private String orgPrivilegeCode;
	
	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 是否签收
	 */
	private String isAccept;
	
	/**
	 * 签收时间
	 */
	private String acceptTime;
	
	/**
	 * 签收人
	 */
	private String acceptPerson;
	
	/**
	 * 签收人姓名
	 */
	private String acceptPersonName;
	
	/**
	 * 是否处理
	 */
	private String isHandle;
	
	/**
	 * 最后处理时间
	 */
	private String handleTime;
	
	/**
	 * 最后处理人
	 */
	private String handlePerson;
	
	/**
	 * 最后处理人姓名
	 */
	private String handlePersonName;
	
	/**
	 * 是否有效
	 */
	private String isValidity;
	
	/**
	 * 处置列表
	 */
	private List<DeviceAlarmHandle> handleList = new ArrayList<DeviceAlarmHandle>();

	public String getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}

	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	public String getAlarmSubType() {
		return alarmSubType;
	}

	public void setAlarmSubType(String alarmSubType) {
		this.alarmSubType = alarmSubType;
	}


	public String getAlarmDeviceId() {
		return alarmDeviceId;
	}

	public void setAlarmDeviceId(String alarmDeviceId) {
		this.alarmDeviceId = alarmDeviceId;
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

	public String getCollectWay() {
		return collectWay;
	}

	public void setCollectWay(String collectWay) {
		this.collectWay = collectWay;
	}

	public String getAlarmDisc() {
		return alarmDisc;
	}

	public void setAlarmDisc(String alarmDisc) {
		this.alarmDisc = alarmDisc;
	}

	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getIsAccept() {
		return isAccept;
	}

	public void setIsAccept(String isAccept) {
		this.isAccept = isAccept;
	}

	public String getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}

	public String getAcceptPerson() {
		return acceptPerson;
	}

	public void setAcceptPerson(String acceptPerson) {
		this.acceptPerson = acceptPerson;
	}

	public String getIsHandle() {
		return isHandle;
	}

	public void setIsHandle(String isHandle) {
		this.isHandle = isHandle;
	}

	public String getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(String handleTime) {
		this.handleTime = handleTime;
	}

	public String getHandlePerson() {
		return handlePerson;
	}

	public void setHandlePerson(String handlePerson) {
		this.handlePerson = handlePerson;
	}

	public String getIsValidity() {
		return isValidity;
	}

	public void setIsValidity(String isValidity) {
		this.isValidity = isValidity;
	}

	public List<DeviceAlarmHandle> getHandleList() {
		return handleList;
	}

	public void setHandleList(List<DeviceAlarmHandle> handleList) {
		this.handleList = handleList;
	}

	public String getAlarmDeviceName() {
		return alarmDeviceName;
	}

	public void setAlarmDeviceName(String alarmDeviceName) {
		this.alarmDeviceName = alarmDeviceName;
	}

	public String getAcceptPersonName() {
		return acceptPersonName;
	}

	public void setAcceptPersonName(String acceptPersonName) {
		this.acceptPersonName = acceptPersonName;
	}

	public String getHandlePersonName() {
		return handlePersonName;
	}

	public void setHandlePersonName(String handlePersonName) {
		this.handlePersonName = handlePersonName;
	}
	
}

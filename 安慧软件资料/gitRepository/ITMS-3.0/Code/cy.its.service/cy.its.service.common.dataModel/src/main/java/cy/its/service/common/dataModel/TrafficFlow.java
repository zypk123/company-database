package cy.its.service.common.dataModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Title: TrafficFlow.java
 * @Package cy.its.service.common.dataModel
 * @Description: 规范化的交通流量
 * @author STJ lijun@cychina.cn
 * @date 2015年11月8日 下午9:33:15
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */
public class TrafficFlow extends Model {
	
//	/**
//	 * 流量ID
//	 */
//	@Mapper("FLOW_ID")
//	private String flowId;

	/**
	 * 点位代码
	 */
	@Mapper("SITE_CODE")
	private String siteCode;

	/**
	 * 系统编号
	 */
	@Mapper("DEVICE_SYS_NBR")
	private String deviceSysNbr;

	/**
	 * 方向类型
	 */
	@Mapper("DIRECTION_TYPE")
	private String directionType;

	/**
	 * 车道
	 */
	@Mapper("LANE")
	private String lane;

	/**
	 * 统计时间
	 */
	@Mapper("COUNT_TIME")
	private Date countTime;

	/**
	 * 统计周期
	 */
	@Mapper("PERIOD")
	private int period;

	/**
	 * 总车数
	 */
	@Mapper("TOTAL_NUM")
	private int totalNum;

	/**
	 * 平均车速
	 */
	@Mapper("AVR_SPEED")
	private BigDecimal avrSpeed;

	/**
	 * 平均车长
	 */
	@Mapper("AVR_LENGTH")
	private BigDecimal avrLength;

	/**
	 * 时间占有率
	 */
	@Mapper("TIME_PERCENT")
	private BigDecimal timePercent;

	/**
	 * 空间占有率
	 */
	@Mapper("SPACE_PERCENT")
	private BigDecimal spacePercent;

	/**
	 * 车头间距
	 */
	@Mapper("VEH_SPACE_HEADWAY")
	private BigDecimal vehSpaceHeadway;

	/**
	 * 车头时距
	 */
	@Mapper("VEH_TIME_HEADWAY")
	private BigDecimal vehTimeHeadway;

	/**
	 * 车辆密度
	 */
	@Mapper("VEH_DENSITY")
	private BigDecimal vehDensity;

	/**
	 * 低速车辆数
	 */
	@Mapper("LOW_SPEED_VEH_NUM")
	private BigDecimal lowSpeedVehNum;

	/**
	 * 超速车辆数
	 */
	@Mapper("HIGH_SPEED_VEH_NUM")
	private BigDecimal highSpeedVehNum;

	/**
	 * 大车数
	 */
	@Mapper("LARGE_NUM")
	private int largeNum;

	/**
	 * 中型车数
	 */
	@Mapper("MIDDLE_NUM")
	private int middleNum;

	/**
	 * 小车数
	 */
	@Mapper("SMALL_NUM")
	private int smallNum;

	/**
	 * 摩托车数
	 */
	@Mapper("MOTOR_NUM")
	private int motorNum;

	/**
	 * 超长车数量
	 */
	@Mapper("SUPPER_LEN_VEH_NUM")
	private BigDecimal supperLenVehNum;

	/**
	 * 其它车数
	 */
	@Mapper("OTHER_NUM")
	private int otherNum;

	/**
	 * 实际值为机构权限代码
	 */
	@Mapper("ORG_PRIVILEGE_CODE")
	private String orgPrivilegeCode;

	/**
	 * 设备编号
	 */
	@Mapper("DEVICE_NBR")
	private String deviceNbr;

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}

	public void setDeviceSysNbr(String deviceSysNbr) {
		this.deviceSysNbr = deviceSysNbr;
	}

	public String getDirectionType() {
		return directionType;
	}

	public void setDirectionType(String directionType) {
		this.directionType = directionType;
	}

	public String getLane() {
		return lane;
	}

	public void setLane(String lane) {
		this.lane = lane;
	}

	public Date getCountTime() {
		return countTime;
	}

	public void setCountTime(Date countTime) {
		this.countTime = countTime;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public BigDecimal getAvrSpeed() {
		return avrSpeed;
	}

	public void setAvrSpeed(BigDecimal avrSpeed) {
		this.avrSpeed = avrSpeed;
	}

	public BigDecimal getAvrLength() {
		return avrLength;
	}

	public void setAvrLength(BigDecimal avrLength) {
		this.avrLength = avrLength;
	}

	public BigDecimal getTimePercent() {
		return timePercent;
	}

	public void setTimePercent(BigDecimal timePercent) {
		this.timePercent = timePercent;
	}

	public BigDecimal getSpacePercent() {
		return spacePercent;
	}

	public void setSpacePercent(BigDecimal spacePercent) {
		this.spacePercent = spacePercent;
	}

	public BigDecimal getVehSpaceHeadway() {
		return vehSpaceHeadway;
	}

	public void setVehSpaceHeadway(BigDecimal vehSpaceHeadway) {
		this.vehSpaceHeadway = vehSpaceHeadway;
	}

	public BigDecimal getVehTimeHeadway() {
		return vehTimeHeadway;
	}

	public void setVehTimeHeadway(BigDecimal vehTimeHeadway) {
		this.vehTimeHeadway = vehTimeHeadway;
	}

	public BigDecimal getVehDensity() {
		return vehDensity;
	}

	public void setVehDensity(BigDecimal vehDensity) {
		this.vehDensity = vehDensity;
	}

	public BigDecimal getLowSpeedVehNum() {
		return lowSpeedVehNum;
	}

	public void setLowSpeedVehNum(BigDecimal lowSpeedVehNum) {
		this.lowSpeedVehNum = lowSpeedVehNum;
	}

	public BigDecimal getHighSpeedVehNum() {
		return highSpeedVehNum;
	}

	public void setHighSpeedVehNum(BigDecimal highSpeedVehNum) {
		this.highSpeedVehNum = highSpeedVehNum;
	}

	public int getLargeNum() {
		return largeNum;
	}

	public void setLargeNum(int largeNum) {
		this.largeNum = largeNum;
	}

	public int getMiddleNum() {
		return middleNum;
	}

	public void setMiddleNum(int middleNum) {
		this.middleNum = middleNum;
	}

	public int getSmallNum() {
		return smallNum;
	}

	public void setSmallNum(int smallNum) {
		this.smallNum = smallNum;
	}

	public int getMotorNum() {
		return motorNum;
	}

	public void setMotorNum(int motorNum) {
		this.motorNum = motorNum;
	}

	public BigDecimal getSupperLenVehNum() {
		return supperLenVehNum;
	}

	public void setSupperLenVehNum(BigDecimal supperLenVehNum) {
		this.supperLenVehNum = supperLenVehNum;
	}

	public int getOtherNum() {
		return otherNum;
	}

	public void setOtherNum(int otherNum) {
		this.otherNum = otherNum;
	}

	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}

	public String getDeviceNbr() {
		return deviceNbr;
	}

	public void setDeviceNbr(String deviceNbr) {
		this.deviceNbr = deviceNbr;
	}
	
    private String deviceId;
	
	private String sysComponentId;
	
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
}

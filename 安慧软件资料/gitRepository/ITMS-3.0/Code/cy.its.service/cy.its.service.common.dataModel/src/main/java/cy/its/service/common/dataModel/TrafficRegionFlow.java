package cy.its.service.common.dataModel;

import java.math.BigDecimal;
import java.util.Date;

public class TrafficRegionFlow extends Model {

	/**
	 * 区间ID
	 */
	@Mapper("REGIONAL_ID")
	private String regionalId;
	
	/**
	 * 区间内总车数
	 */
	@Mapper("TOTAL_NUM")
	private int totalNum;
	
	/**
	 * 大车数
	 */
	@Mapper("LARGE_NUM")
	private int largeNum;
	
	/**
	 * 小车数
	 */
	@Mapper("SMALL_NUM")
	private int smallNum;
	
	/**
	 * 其它车数
	 */
	@Mapper("OTHER_NUM")
	private int otherNum;
	
	/**
	 * 区间内重点车辆数
	 */
	@Mapper("IMPORTANT_NUM")
	private int importantNum;
	
	/**
	 * 平均车速
	 */
	@Mapper("AVG_SPEED")
	private BigDecimal avgSpeed;

	/**
	 * 平均旅行时间。单位为分钟
	 */
	@Mapper("AVG_TRAVEL_TIME")
	private BigDecimal avgTravelTime;

	/**
	 * 机构权限过滤代码
	 */
	@Mapper("ORG_PRIVILEGE_CODE")
	private String orgPrivilegeCode;

	/**
	 * 通行状态
	 */
	@Mapper("TRAFFIC_STATE")
	private String trafficState;

	/**
	 * 过车时间
	 */
	@Mapper("UPDATE_TIME")
	private Date updateTime;

	public String getRegionalId() {
		return regionalId;
	}

	public void setRegionalId(String regionalId) {
		this.regionalId = regionalId;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getLargeNum() {
		return largeNum;
	}

	public void setLargeNum(int largeNum) {
		this.largeNum = largeNum;
	}

	public int getSmallNum() {
		return smallNum;
	}

	public void setSmallNum(int smallNum) {
		this.smallNum = smallNum;
	}

	public int getOtherNum() {
		return otherNum;
	}

	public void setOtherNum(int otherNum) {
		this.otherNum = otherNum;
	}

	public int getImportantNum() {
		return importantNum;
	}

	public void setImportantNum(int importantNum) {
		this.importantNum = importantNum;
	}

	public BigDecimal getAvgSpeed() {
		return avgSpeed;
	}

	public void setAvgSpeed(BigDecimal avgSpeed) {
		this.avgSpeed = avgSpeed;
	}

	public BigDecimal getAvgTravelTime() {
		return avgTravelTime;
	}

	public void setAvgTravelTime(BigDecimal avgTravelTime) {
		this.avgTravelTime = avgTravelTime;
	}

	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}

	public String getTrafficState() {
		return trafficState;
	}

	public void setTrafficState(String trafficState) {
		this.trafficState = trafficState;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}

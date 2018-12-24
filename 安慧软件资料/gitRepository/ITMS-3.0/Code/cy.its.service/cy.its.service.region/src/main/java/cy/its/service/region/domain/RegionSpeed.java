package cy.its.service.region.domain;

import java.util.Date;


public class RegionSpeed {
	/**
	 * 区间ID
	 */
	private String regionalId;

	/**
	* 过车时间
	*/
	private Date updateTime;
	
	/**
	* 平均速度
	*/
	private double avgSpeed;
	
	/**
	* 1 拥堵 2、缓行 3 畅通 
	*/
	private String trafficState;
	
	/**
	* 道路类型
	*/
	private String roadType;
	
	/*
	 * 平均旅行时间
	 */
	private double avgTravelTime;
	
	/**
	* 机构编码
	*/
	private String orgCode;
	
	
	
	private int totalNum=0;
	private int largeNum=0;
	private int smallNum=0;
	private int otherNum=0;
	private int importantNum=0;
	
	/*
	 * 机构权限代码
	 */
	private String orgPrivilegeCode="";
	

	public double getAvgTravelTime() {
		return avgTravelTime;
	}

	public void setAvgTravelTime(double avgTravelTime) {
		this.avgTravelTime = avgTravelTime;
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

	public String getRoadType() {
		return roadType;
	}

	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}

	public double getAvgSpeed() {
		return avgSpeed;
	}

	public void setAvgSpeed(double avgSpeed) {
		this.avgSpeed = avgSpeed;
	}

	
	public String getRegionalId() {
		return regionalId;
	}

	public void setRegionalId(String regionalId) {
		this.regionalId = regionalId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the orgCode
	 */
	public String getOrgCode() {
		return orgCode;
	}

	/**
	 * @param orgCode the orgCode to set
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
}

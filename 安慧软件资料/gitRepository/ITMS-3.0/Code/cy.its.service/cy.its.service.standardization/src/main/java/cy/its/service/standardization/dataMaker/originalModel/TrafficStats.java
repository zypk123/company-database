package cy.its.service.standardization.dataMaker.originalModel;

import java.util.Map;

public class TrafficStats extends BaseOrginalModel
{
    private String roadCode;

    private String deviceNo;

    private String driveDirection;

    private int driveLane;

    private String statsTime;

    private int statsPeriod;

    private int vehicleTotal;

    private float vehicleSpeed;

    private float vehicleLength;

    private float timeOccupyRatio;

    private float spaceOccupyRatio;

    private float spaceHeadway;

    private float timeHeadway;

    private float density;

    private int overSpeedTotal;

    private int underSpeedTotal;

    private int largeVehicleTotal;

    private int middleVehicleTotal;

    private int smallVehicleTotal;

    private int motorVehicleTotal;

    private int longVehicleTotal;

    private Map<String, String> otherTrafficParams;

	public String getRoadCode() {
		return roadCode;
	}

	public void setRoadCode(String roadCode) {
		this.roadCode = roadCode;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getDriveDirection() {
		return driveDirection;
	}

	public void setDriveDirection(String driveDirection) {
		this.driveDirection = driveDirection;
	}

	public int getDriveLane() {
		return driveLane;
	}

	public void setDriveLane(int driveLane) {
		this.driveLane = driveLane;
	}

	public String getStatsTime() {
		return statsTime;
	}

	public void setStatsTime(String statsTime) {
		this.statsTime = statsTime;
	}

	public int getStatsPeriod() {
		return statsPeriod;
	}

	public void setStatsPeriod(int statsPeriod) {
		this.statsPeriod = statsPeriod;
	}

	public int getVehicleTotal() {
		return vehicleTotal;
	}

	public void setVehicleTotal(int vehicleTotal) {
		this.vehicleTotal = vehicleTotal;
	}

	public float getVehicleSpeed() {
		return vehicleSpeed;
	}

	public void setVehicleSpeed(float vehicleSpeed) {
		this.vehicleSpeed = vehicleSpeed;
	}

	public float getVehicleLength() {
		return vehicleLength;
	}

	public void setVehicleLength(float vehicleLength) {
		this.vehicleLength = vehicleLength;
	}

	public float getTimeOccupyRatio() {
		return timeOccupyRatio;
	}

	public void setTimeOccupyRatio(float timeOccupyRatio) {
		this.timeOccupyRatio = timeOccupyRatio;
	}

	public float getSpaceOccupyRatio() {
		return spaceOccupyRatio;
	}

	public void setSpaceOccupyRatio(float spaceOccupyRatio) {
		this.spaceOccupyRatio = spaceOccupyRatio;
	}

	public float getSpaceHeadway() {
		return spaceHeadway;
	}

	public void setSpaceHeadway(float spaceHeadway) {
		this.spaceHeadway = spaceHeadway;
	}

	public float getTimeHeadway() {
		return timeHeadway;
	}

	public void setTimeHeadway(float timeHeadway) {
		this.timeHeadway = timeHeadway;
	}

	public float getDensity() {
		return density;
	}

	public void setDensity(float density) {
		this.density = density;
	}

	public int getOverSpeedTotal() {
		return overSpeedTotal;
	}

	public void setOverSpeedTotal(int overSpeedTotal) {
		this.overSpeedTotal = overSpeedTotal;
	}

	public int getUnderSpeedTotal() {
		return underSpeedTotal;
	}

	public void setUnderSpeedTotal(int underSpeedTotal) {
		this.underSpeedTotal = underSpeedTotal;
	}

	public int getLargeVehicleTotal() {
		return largeVehicleTotal;
	}

	public void setLargeVehicleTotal(int largeVehicleTotal) {
		this.largeVehicleTotal = largeVehicleTotal;
	}

	public int getMiddleVehicleTotal() {
		return middleVehicleTotal;
	}

	public void setMiddleVehicleTotal(int middleVehicleTotal) {
		this.middleVehicleTotal = middleVehicleTotal;
	}

	public int getSmallVehicleTotal() {
		return smallVehicleTotal;
	}

	public void setSmallVehicleTotal(int smallVehicleTotal) {
		this.smallVehicleTotal = smallVehicleTotal;
	}

	public int getMotorVehicleTotal() {
		return motorVehicleTotal;
	}

	public void setMotorVehicleTotal(int motorVehicleTotal) {
		this.motorVehicleTotal = motorVehicleTotal;
	}

	public int getLongVehicleTotal() {
		return longVehicleTotal;
	}

	public void setLongVehicleTotal(int longVehicleTotal) {
		this.longVehicleTotal = longVehicleTotal;
	}

	public Map<String, String> getOtherTrafficParams() {
		return otherTrafficParams;
	}

	public void setOtherTrafficParams(Map<String, String> otherTrafficParams) {
		this.otherTrafficParams = otherTrafficParams;
	}
    
    
}

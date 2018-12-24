package cy.its.service.standardization.dataMaker.originalModel;

import java.util.Map;

public class ViolationVehicle extends BaseOrginalModel{
	 private String deviceNo;

     private String snapNbr;

     private String roadCode;

     private String driveDirection;

     private int lane;

     private String plateNbr;

     private String plateType;

     private String captureTime;

     private int vehicleSpeed;

     private String plateColor;

     private String vehicleType;

     private String[] violationBehaviors;

     private SpeedLimit[] roadSpeedLimits;

     private String redlightTime;

     private String gpsLocationInfo;

     private Map<String, String> extendedProperties;

     private VehicleImageDescription[] imageDescriptions;

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getSnapNbr() {
		return snapNbr;
	}

	public void setSnapNbr(String snapNbr) {
		this.snapNbr = snapNbr;
	}

	public String getRoadCode() {
		return roadCode;
	}

	public void setRoadCode(String roadCode) {
		this.roadCode = roadCode;
	}

	public String getDriveDirection() {
		return driveDirection;
	}

	public void setDriveDirection(String driveDirection) {
		this.driveDirection = driveDirection;
	}

	public int getLane() {
		return lane;
	}

	public void setLane(int lane) {
		this.lane = lane;
	}

	public String getPlateNbr() {
		return plateNbr;
	}

	public void setPlateNbr(String plateNbr) {
		this.plateNbr = plateNbr;
	}

	public String getPlateType() {
		return plateType;
	}

	public void setPlateType(String plateType) {
		this.plateType = plateType;
	}

	public String getCaptureTime() {
		return captureTime;
	}

	public void setCaptureTime(String captureTime) {
		this.captureTime = captureTime;
	}

	public int getVehicleSpeed() {
		return vehicleSpeed;
	}

	public void setVehicleSpeed(int vehicleSpeed) {
		this.vehicleSpeed = vehicleSpeed;
	}

	public String getPlateColor() {
		return plateColor;
	}

	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String[] getViolationBehaviors() {
		return violationBehaviors;
	}

	public void setViolationBehaviors(String[] violationBehaviors) {
		this.violationBehaviors = violationBehaviors;
	}

	public SpeedLimit[] getRoadSpeedLimits() {
		return roadSpeedLimits;
	}

	public void setRoadSpeedLimits(SpeedLimit[] roadSpeedLimits) {
		this.roadSpeedLimits = roadSpeedLimits;
	}

	public String getRedlightTime() {
		return redlightTime;
	}

	public void setRedlightTime(String redlightTime) {
		this.redlightTime = redlightTime;
	}

	public String getGpsLocationInfo() {
		return gpsLocationInfo;
	}

	public void setGpsLocationInfo(String gpsLocationInfo) {
		this.gpsLocationInfo = gpsLocationInfo;
	}

	public Map<String, String> getExtendedProperties() {
		return extendedProperties;
	}

	public void setExtendedProperties(Map<String, String> extendedProperties) {
		this.extendedProperties = extendedProperties;
	}

	public VehicleImageDescription[] getImageDescriptions() {
		return imageDescriptions;
	}

	public void setImageDescriptions(VehicleImageDescription[] imageDescriptions) {
		this.imageDescriptions = imageDescriptions;
	}
     
     
}

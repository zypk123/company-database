package cy.its.service.standardization.dataMaker.originalModel;

public class SpeedLimit {
	private String vehicleType;

	private int lane;

	private int roadOverSpeedLimit;

	private int roadUnderSpeedLimit;

	private int overSpeedMargin;

	private int underSpeedMargin;

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public int getLane() {
		return lane;
	}

	public void setLane(int lane) {
		this.lane = lane;
	}

	public int getRoadOverSpeedLimit() {
		return roadOverSpeedLimit;
	}

	public void setRoadOverSpeedLimit(int roadOverSpeedLimit) {
		this.roadOverSpeedLimit = roadOverSpeedLimit;
	}

	public int getRoadUnderSpeedLimit() {
		return roadUnderSpeedLimit;
	}

	public void setRoadUnderSpeedLimit(int roadUnderSpeedLimit) {
		this.roadUnderSpeedLimit = roadUnderSpeedLimit;
	}

	public int getOverSpeedMargin() {
		return overSpeedMargin;
	}

	public void setOverSpeedMargin(int overSpeedMargin) {
		this.overSpeedMargin = overSpeedMargin;
	}

	public int getUnderSpeedMargin() {
		return underSpeedMargin;
	}

	public void setUnderSpeedMargin(int underSpeedMargin) {
		this.underSpeedMargin = underSpeedMargin;
	}

}

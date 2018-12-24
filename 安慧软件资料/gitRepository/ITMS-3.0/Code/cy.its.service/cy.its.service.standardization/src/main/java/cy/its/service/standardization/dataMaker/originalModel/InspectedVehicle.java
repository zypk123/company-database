package cy.its.service.standardization.dataMaker.originalModel;

import java.util.Map;

public class InspectedVehicle {
	private String inspectDepartment;

	private String inspectAction;

	private String inspectTime;

	private String ExpireDate;

	private String plateNbr;

	private String plateColor;

	private String plateType;

	private String vehicleType;

	private String description;

	private String updateTime;

	private Map<String, String> extendedProperties;

	public String getInspectDepartment() {
		return inspectDepartment;
	}

	public void setInspectDepartment(String inspectDepartment) {
		this.inspectDepartment = inspectDepartment;
	}

	public String getInspectAction() {
		return inspectAction;
	}

	public void setInspectAction(String inspectAction) {
		this.inspectAction = inspectAction;
	}

	public String getInspectTime() {
		return inspectTime;
	}

	public void setInspectTime(String inspectTime) {
		this.inspectTime = inspectTime;
	}

	public String getExpireDate() {
		return ExpireDate;
	}

	public void setExpireDate(String expireDate) {
		ExpireDate = expireDate;
	}

	public String getPlateNbr() {
		return plateNbr;
	}

	public void setPlateNbr(String plateNbr) {
		this.plateNbr = plateNbr;
	}

	public String getPlateColor() {
		return plateColor;
	}

	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}

	public String getPlateType() {
		return plateType;
	}

	public void setPlateType(String plateType) {
		this.plateType = plateType;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Map<String, String> getExtendedProperties() {
		return extendedProperties;
	}

	public void setExtendedProperties(Map<String, String> extendedProperties) {
		this.extendedProperties = extendedProperties;
	}

}

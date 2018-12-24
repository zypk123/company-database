package cy.its.device.domain.criteria;

import cy.its.com.domain.Criteria;

public class DeviceLedSpecCriteria extends Criteria{
	
	public String specName;
	public String colorType;
	public String ledDeviceType;
	public String getSpecName() {
		return specName;
	}
	public void setSpecName(String specName) {
		this.specName = specName;
	}
	public String getColorType() {
		return colorType;
	}
	public void setColorType(String colorType) {
		this.colorType = colorType;
	}
	public String getLedDeviceType() {
		return ledDeviceType;
	}
	public void setLedDeviceType(String ledDeviceType) {
		this.ledDeviceType = ledDeviceType;
	}
	
	
	
}

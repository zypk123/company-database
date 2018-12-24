package cy.its.device.rest.dto;

public class DeviceLedSpecDto {
	public String specId;
	public String specName;
	public String ledDeviceType;
	public String ledDeviceModel;
	public String colorType;
	public String pixesHeight;
	public String pixesWidth;
	public String width;
	public String height;
	public String remark;
	public String ledShape;
	public String ledFunctionType;//LED功能类型
	public String controlType;//控制方式
	public String[] controlTypeStr;//控制方式
	public String controlContractor;//控制卡厂商
	
	
	public String[] getControlTypeStr() {
		return controlTypeStr;
	}
	public void setControlTypeStr(String[] controlTypeStr) {
		this.controlTypeStr = controlTypeStr;
	}
	public String getSpecId() {
		return specId;
	}
	public void setSpecId(String specId) {
		this.specId = specId;
	}
	public String getSpecName() {
		return specName;
	}
	public void setSpecName(String specName) {
		this.specName = specName;
	}
	
	public String getLedDeviceType() {
		return ledDeviceType;
	}
	public void setLedDeviceType(String ledDeviceType) {
		this.ledDeviceType = ledDeviceType;
	}
	public String getLedDeviceModel() {
		return ledDeviceModel;
	}
	public void setLedDeviceModel(String ledDeviceModel) {
		this.ledDeviceModel = ledDeviceModel;
	}
	public String getColorType() {
		return colorType;
	}
	
	
	public String getPixesHeight() {
		return pixesHeight;
	}
	public void setPixesHeight(String pixesHeight) {
		this.pixesHeight = pixesHeight;
	}
	public String getPixesWidth() {
		return pixesWidth;
	}
	public void setPixesWidth(String pixesWidth) {
		this.pixesWidth = pixesWidth;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public void setColorType(String colorType) {
		this.colorType = colorType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getLedShape() {
		return ledShape;
	}
	public void setLedShape(String ledShape) {
		this.ledShape = ledShape;
	}
	public String getLedFunctionType() {
		return ledFunctionType;
	}
	public void setLedFunctionType(String ledFunctionType) {
		this.ledFunctionType = ledFunctionType;
	}
	public String getControlType() {
		return controlType;
	}
	public void setControlType(String controlType) {
		this.controlType = controlType;
	}
	public String getControlContractor() {
		return controlContractor;
	}
	public void setControlContractor(String controlContractor) {
		this.controlContractor = controlContractor;
	}
	
	
}

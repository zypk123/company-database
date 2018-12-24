package cy.its.device.domain.model;

import cy.its.com.domain.AggregateRoot;

public class DeviceLedSpec extends AggregateRoot{
	public String specId;
	public String specName;
	public String LedDeviceType;
	public String LedDeviceModel;
	public String colorType;
	public Integer pixesHeight;
	public Integer pixesWidth;
	public Integer width;
	public Integer height;
	public String remark;
	public String ledShape;
	
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
		return LedDeviceType;
	}
	public void setLedDeviceType(String ledDeviceType) {
		LedDeviceType = ledDeviceType;
	}
	public String getLedDeviceModel() {
		return LedDeviceModel;
	}
	public void setLedDeviceModel(String ledDeviceModel) {
		LedDeviceModel = ledDeviceModel;
	}
	public String getColorType() {
		return colorType;
	}
	public void setColorType(String colorType) {
		this.colorType = colorType;
	}
	public Integer getPixesHeight() {
		return pixesHeight;
	}
	public void setPixesHeight(Integer pixesHeight) {
		this.pixesHeight = pixesHeight;
	}
	public Integer getPixesWidth() {
		return pixesWidth;
	}
	public void setPixesWidth(Integer pixesWidth) {
		this.pixesWidth = pixesWidth;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
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
	@Override
	public String getIdentityId() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

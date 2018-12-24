package cy.its.device.domain.model.led;

import java.util.List;

import cy.its.device.domain.criteria.LedCriteria;
import cy.its.device.domain.criteria.LedSpecCriteria;
import cy.its.device.domain.repository.ISysRepository;
import cy.its.device.domain.repository.led.ILedProgTempRepository;
import cy.its.device.domain.repository.led.ILedSpecRepository;

/**
 * LED规格
 * @author STJ
 *
 */
public class LedSpec {
	private String specId;

    private String specName;

    private String ledDeviceType;

    private String ledDeviceModel;

    private String colorType;

    private Short pixesHeight;

    private Short pixesWidth;

    private Short width;

    private Short height;

    private String remark;

    private String ledShape;

    private String ledFunctionType;

    private String controlType;

    private String controlContractor;

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

    public void setColorType(String colorType) {
        this.colorType = colorType;
    }

    public Short getPixesHeight() {
        return pixesHeight;
    }

    public void setPixesHeight(Short pixesHeight) {
        this.pixesHeight = pixesHeight;
    }

    public Short getPixesWidth() {
        return pixesWidth;
    }

    public void setPixesWidth(Short pixesWidth) {
        this.pixesWidth = pixesWidth;
    }

    public Short getWidth() {
        return width;
    }

    public void setWidth(Short width) {
        this.width = width;
    }

    public Short getHeight() {
        return height;
    }

    public void setHeight(Short height) {
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
    
    public void checkRepeatName(ILedSpecRepository ledSpecRepository) throws Exception{
    	LedSpecCriteria sc = new LedSpecCriteria();
		sc.specName = this.getSpecName();
		List<LedSpec> lst = ledSpecRepository.findLedSpecs(sc);
		if(lst.size() > 0){
			lst.removeIf(s->s.getSpecId().equals(this.getSpecId()));
			if(lst.size() > 0){
				throw new Exception("存在相同名称的规格");
			}
		}
    }
    
    public void checkForCanChanged(ISysRepository sysRepository, ILedProgTempRepository ledProgTempRepository) throws Exception{
    	int templateCount = ledProgTempRepository.templateCountOfSpecId(this.getSpecId());		
		if(templateCount > 0) {
			// 当前规格下存在节目模板
			throw new Exception("当前规格下存在节目模板,禁止修改或删除!");
		}
		
		LedCriteria c = new LedCriteria();
		c.ledSpecId = this.getSpecId();
		c.useStatusFlagArr = new String[]{"1"};
		List<Led> lstLed = sysRepository.findLeds(c);		
		if(lstLed.size() > 0) {
			// 当前规格下存在启用的设备
			throw new Exception("当前规格下存在启用的设备,禁止修改或删除!");
		}
    }
}
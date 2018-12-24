package cy.its.device.domain.model.led;

import java.util.List;

import cy.its.device.domain.criteria.LedProgTemplateCriteria;
import cy.its.device.domain.repository.led.ILedProgTempRepository;

/**
 * 模板节目
 * 
 * @author STJ
 *
 */
public class LedProgTemplate {

	private String progTemplateId;

	private String specId;

	private String templateName;

	private String templateDesc;
	
    private String messageType;

//	private List<LedProgContentMedia> lstLedMedia;
//
//	private List<LedProgContentText> lstLedText;
	
    /**
     * 节目内容
     */
	private String programContent;

	
	public String getProgramContent() {
		return programContent;
	}

	public void setProgramContent(String programContent) {
		this.programContent = programContent;
	}

	public String getProgTemplateId() {
		return progTemplateId;
	}

	public void setProgTemplateId(String progTemplateId) {
		this.progTemplateId = progTemplateId;
	}

	public String getSpecId() {
		return specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateDesc() {
		return templateDesc;
	}

	public void setTemplateDesc(String templateDesc) {
		this.templateDesc = templateDesc;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

//	public List<LedProgContentMedia> getLstLedMedia() {
//		return lstLedMedia;
//	}
//
//	public void setLstLedMedia(List<LedProgContentMedia> lstLedMedia) {
//		this.lstLedMedia = lstLedMedia;
//	}
//
//	public List<LedProgContentText> getLstLedText() {
//		return lstLedText;
//	}
//
//	public void setLstLedText(List<LedProgContentText> lstLedText) {
//		this.lstLedText = lstLedText;
//	}

	public void checkRepeatName(ILedProgTempRepository ledProgTempRepository) throws Exception {
		LedProgTemplateCriteria c = new LedProgTemplateCriteria();
		c.templateName = this.getTemplateName();
		List<LedProgTemplate> lst = ledProgTempRepository.findLedProgTemplates(c);

		if (lst.size() > 0) {
			lst.removeIf(cc -> cc.progTemplateId.equals(this.progTemplateId));
			if (lst.size() > 0) {
				throw new Exception("存在相同名称的节目模板");
			}
		}

	}
}
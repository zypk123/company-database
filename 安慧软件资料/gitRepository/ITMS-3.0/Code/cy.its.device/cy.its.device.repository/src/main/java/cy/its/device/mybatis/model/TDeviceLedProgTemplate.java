package cy.its.device.mybatis.model;


public class TDeviceLedProgTemplate {
	private String progTemplateId;

    private String specId;

    private String templateName;

    private String templateDesc;

    private String messageType;
    
    private String programContent;

    
    public String getProgramContent() {
		return programContent;
	}

	public void setProgramContent(String programContent) {
		this.programContent = programContent;
	}

	public TDeviceLedProgTemplate(){}
   
	public TDeviceLedProgTemplate(String progTemplateId, String specId, String templateName, String templateDesc,
			String messageType, String programContent) {
		super();
		this.progTemplateId = progTemplateId;
		this.specId = specId;
		this.templateName = templateName;
		this.templateDesc = templateDesc;
		this.messageType = messageType;
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
}
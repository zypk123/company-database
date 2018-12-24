package cy.its.device.domain.model.led;

import java.util.Date;

import cy.its.com.util.StringUtil;

/**
 * led节目文本内容库
 * @author STJ
 *
 */
public class LedContentLib {
	
	 private String contentId;

	    private String contentDescription;

	    private String contentType;

	    private String content;

	    private String creator;

	    private String orgCode;

	    private Date createTime;

	    private String modifier;

	    private Date modifyTime;

	    private String remark;
    
    
    public void modifyUser(String modifier) throws Exception {
    	if(StringUtil.isNullOrEmpty(modifier)) {
    		throw new Exception("修改人不能为空");
    	}
    	
    	this.modifier = modifier;
    	this.modifyTime = new Date();
    }

    public void saveCreator(String creator) throws Exception{
    	if(StringUtil.isNullOrEmpty(creator)) {
    		throw new Exception("创建人不能为空");
    	}
    	
    	this.creator = creator;
    	this.createTime = new Date();
    }
    
    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getContentDescription() {
        return contentDescription;
    }

    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
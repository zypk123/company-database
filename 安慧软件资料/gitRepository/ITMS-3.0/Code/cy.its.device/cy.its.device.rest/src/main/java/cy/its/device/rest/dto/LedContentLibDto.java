package cy.its.device.rest.dto;

import cy.its.com.dto.BaseDto;
/**
 * 内容库_前台传输数据包装对象
 * @author liug
 *
 */
public class LedContentLibDto extends BaseDto {
	private String contentId;

    private String contentDescription;
    /**
     * 信息类型  1 宣传文字、 2 违法警示、3 交通管制、4 交通路况、5 交通事故、6 交通法规、7 安全提示、8 气象信息
     */
    private String messageType;

    private String contentType;
    /**
     * 内容
     */
    private String content;

    private String creator;

    private String orgCode;

    private String createTime;

    private String modifier;

    private String modifyTime;

    private String remark;

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

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
}

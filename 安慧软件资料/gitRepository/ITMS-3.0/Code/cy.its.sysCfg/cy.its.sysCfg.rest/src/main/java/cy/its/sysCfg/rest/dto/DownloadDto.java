package cy.its.sysCfg.rest.dto;

import java.util.Date;

import cy.its.com.dto.BaseDto;

public class DownloadDto  extends BaseDto{
	private String downloadId;

    private String downloadTitle;

    private String downloadContent;

    private String downloadUrl;

    public String getDownloadId() {
		return downloadId;
	}

	public void setDownloadId(String downloadId) {
		this.downloadId = downloadId;
	}

	public String getDownloadTitle() {
		return downloadTitle;
	}

	public void setDownloadTitle(String downloadTitle) {
		this.downloadTitle = downloadTitle;
	}

	public String getDownloadContent() {
		return downloadContent;
	}

	public void setDownloadContent(String downloadContent) {
		this.downloadContent = downloadContent;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	private String createTime;

    private String updateTime;
}

package cy.its.video.rest.dto;

import java.util.Date;

import cy.its.com.dto.BaseDto;

/**
 * ‘§÷√Œª
 * 
 * @author Administrator
 *
 */
public class TrafficPresetDto extends BaseDto {
	private String presetRecordId;

	private String deviceId;

	private String sysComponentId;

	private Short preset;

	private String presetDesc;

	private String createBy;

	private Date createTime;

	private String updateBy;

	private Date updateTime;

	public String getPresetRecordId() {
		return presetRecordId;
	}

	public void setPresetRecordId(String presetRecordId) {
		this.presetRecordId = presetRecordId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getSysComponentId() {
		return sysComponentId;
	}

	public void setSysComponentId(String sysComponentId) {
		this.sysComponentId = sysComponentId;
	}

	public Short getPreset() {
		return preset;
	}

	public void setPreset(Short preset) {
		this.preset = preset;
	}

	public String getPresetDesc() {
		return presetDesc;
	}

	public void setPresetDesc(String presetDesc) {
		this.presetDesc = presetDesc;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}

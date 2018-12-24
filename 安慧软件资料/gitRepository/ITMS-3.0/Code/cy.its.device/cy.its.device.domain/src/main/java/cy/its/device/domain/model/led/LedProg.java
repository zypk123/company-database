package cy.its.device.domain.model.led;

import cy.its.com.util.StringUtil;
import cy.its.device.domain.model.SysParam;
import cy.its.device.domain.repository.ISysRepository;
import cy.its.device.domain.repository.led.ILedProgRepository;

/**
 * 节目
 * 
 * @author STJ
 *
 */
public abstract class LedProg {

	private String programId;

	/**
	 * LED设备ID
	 */
	private String deviceId;

	/**
	 * 节目编号
	 */
	private String programNo;

	/**
	 * 节目名称
	 */
	private String perName;

	/**
	 * 显示模式  0:非排他显示;1:排他显示;
	 */
	private String showMode;

	/**
	 * 节目优先级  0:通知;1:警告;2:关键的;3:紧急的;4:计数作用;
	 */
	private String programPriority;

	/**
	 * 信息类型  1 宣传文字、 2 违法警示、3 交通管制、4 交通路况、5 交通事故、6 交通法规、7 安全提示、8 气象信息
	 */
	private String messageType;

//	/**
//	 * 媒体节目内容列表
//	 */
//	private List<LedProgContentMedia> lstLedMedia;
//
//	/**
//	 * 文本节目内容列表
//	 */
//	private List<LedProgContentText> lstLedText;
	
	/**
	 * 节目内容文本
	 */
	private String programContent;

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getProgramNo() {
		return programNo;
	}

	public void setProgramNo(String programNo) {
		this.programNo = programNo;
	}

	public String getPerName() {
		return perName;
	}

	public void setPerName(String perName) {
		this.perName = perName;
	}

	public String getShowMode() {
		return showMode;
	}

	public void setShowMode(String showMode) {
		this.showMode = showMode;
	}

	public String getProgramPriority() {
		return programPriority;
	}

	public void setProgramPriority(String programPriority) {
		this.programPriority = programPriority;
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

	public void check(ISysRepository sysRepository) throws Exception {
		this.checkPlayParams();
		this.checkLedConfig(sysRepository);
//		this.checkContent();
	}

	public void checkLedConfig(ISysRepository sysRepository) throws Exception {
		if (StringUtil.isNullOrEmpty(this.deviceId)) {
			throw new Exception("节目未指定LED设备");
		}

		SysParam sysParam = sysRepository.sysParamOfId(this.deviceId, Led.class);

		if (sysParam == null) {
			throw new Exception("为节目指定的LED设备不存在");
		}
	}

//	public void checkContent() throws Exception {
//		if (this.lstLedMedia != null) {
//			for (LedProgContentMedia media : this.lstLedMedia) {
//				media.checkContent();
//			}
//		}
//
//		if (this.lstLedText != null) {
//			for (LedProgContentText text : this.lstLedText) {
//				text.checkContent();
//			}
//		}
//	}

	
	public void generate(ILedProgRepository iLedProgRepository) {
		this.setProgramId(StringUtil.generateUUID());
		this.setProgramNo(iLedProgRepository.generateProgramNo());
//		this.setContentNo();
	}

//	public void setContentNo(){
//		if (this.getLstLedMedia() != null) {
//			int index = 1;
//			for (LedProgContentMedia media : this.getLstLedMedia()) {
//				media.setMediaId(StringUtil.generateUUID());
//				media.setMediaNo(String.valueOf(index));
//				index++;
//			}
//		}
//
//		if (this.getLstLedText() != null) {
//			int index = 1;
//			for (LedProgContentText text : this.getLstLedText()) {
//				text.setTextId(StringUtil.generateUUID());
//				text.setTextNo(String.valueOf(index));
//			}
//		}
//	}
	
	public String getProgramContent() {
		return programContent;
	}

	public void setProgramContent(String programContent) {
		this.programContent = programContent;
	}

	public abstract void checkPlayParams() throws Exception;
}
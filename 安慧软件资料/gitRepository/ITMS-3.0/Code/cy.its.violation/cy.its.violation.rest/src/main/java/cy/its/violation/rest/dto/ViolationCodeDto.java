/**
 * @Title: ViolationCodeDto.java
 * @Package cy.its.violation.rest.dto
 * @Description: TODO(这里要填写用途)
 * @author wangyf wangyf@cychina.cn
 * @date 2015年11月13日 下午4:42:01
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.violation.rest.dto;

/**
  * @ClassName: ViolationCodeDto
  * @Description: TODO(这里要填写用途)
  * @author wangyf wangyf@cychina.cn
  * @date 2015年11月13日 下午4:42:01
  *
  */

public class ViolationCodeDto {
	//违法代码编号  
	private String vioActionCode;
	//违法类型
	private String vioType;
	//违法大类 
	private String vioPriorType;
	//违法内容概述 
	private String vioSummary ;
	//违法内容
	private String  vioConetent;
	//引用法律条文 
	private String  legalReference;
	//处罚依据条款
	private String punishLegalReference;
	//违法记分
	private String fineScore;
	//最高罚款金额
	private String fineMax;
	//最低罚款金额
	private String fineMin;
	//默认罚款金额
	private String fineDefault;
	//最高暂扣月份
	private String suspendMonthMax;
	//最低暂扣月份
	private String suspendMonthMin;
	//最大拘留天数
	private String detainDaysMax;
	//最小拘留天数
	private String detainDaysMin;
	//强制措施类型
	private String forceType;
	//警告标记
	private String flagWarn;
	//罚款标记
	private String flagFine;
	//暂扣标记
	private String flagSuspend;
	//吊销标记
	private String flagCancel;
	//拘留标记
	private String flagDetain;
	//撤销行驶证标记
	private String flagRevokeVehicle;
	//撤销驾驶证标记
	private String flagRevokeDriver;
	//是否国际
	private String isGb;
	//有效期始
	private String issueDay;
	//有效期至
	private String issueEndDay;
	//是否常用
	private String isCommonUsed;
	
	
	//违法配置表Id
	private String vioActionMatchId;
	//道路类型
	public String roadType;
	//违法类型
	public String violationType; 
	//限速值
	public String limitSpeed;
	//最大超速比
	public String MaxRatio;
	//最小超速比
	public String minRatio;
	
	public String getVioActionMatchId() {
		return vioActionMatchId;
	}
	public void setVioActionMatchId(String vioActionMatchId) {
		this.vioActionMatchId = vioActionMatchId;
	}
	public String getVioActionCode() {
		return vioActionCode;
	}
	public void setVioActionCode(String vioActionCode) {
		this.vioActionCode = vioActionCode;
	}
	public String getVioType() {
		return vioType;
	}
	public void setVioType(String vioType) {
		this.vioType = vioType;
	}
	public String getVioPriorType() {
		return vioPriorType;
	}
	public void setVioPriorType(String vioPriorType) {
		this.vioPriorType = vioPriorType;
	}
	public String getVioSummary() {
		return vioSummary;
	}
	public void setVioSummary(String vioSummary) {
		this.vioSummary = vioSummary;
	}
	public String getVioConetent() {
		return vioConetent;
	}
	public void setVioConetent(String vioConetent) {
		this.vioConetent = vioConetent;
	}
	public String getLegalReference() {
		return legalReference;
	}
	public void setLegalReference(String legalReference) {
		this.legalReference = legalReference;
	}
	public String getPunishLegalReference() {
		return punishLegalReference;
	}
	public void setPunishLegalReference(String punishLegalReference) {
		this.punishLegalReference = punishLegalReference;
	}
	
	public String getFineScore() {
		return fineScore;
	}
	public void setFineScore(String fineScore) {
		this.fineScore = fineScore;
	}
	public String getFineMax() {
		return fineMax;
	}
	public void setFineMax(String fineMax) {
		this.fineMax = fineMax;
	}
	public String getFineMin() {
		return fineMin;
	}
	public void setFineMin(String fineMin) {
		this.fineMin = fineMin;
	}
	public String getFineDefault() {
		return fineDefault;
	}
	public void setFineDefault(String fineDefault) {
		this.fineDefault = fineDefault;
	}
	public String getSuspendMonthMax() {
		return suspendMonthMax;
	}
	public void setSuspendMonthMax(String suspendMonthMax) {
		this.suspendMonthMax = suspendMonthMax;
	}
	public String getSuspendMonthMin() {
		return suspendMonthMin;
	}
	public void setSuspendMonthMin(String suspendMonthMin) {
		this.suspendMonthMin = suspendMonthMin;
	}
	public String getDetainDaysMax() {
		return detainDaysMax;
	}
	public void setDetainDaysMax(String detainDaysMax) {
		this.detainDaysMax = detainDaysMax;
	}
	public String getDetainDaysMin() {
		return detainDaysMin;
	}
	public void setDetainDaysMin(String detainDaysMin) {
		this.detainDaysMin = detainDaysMin;
	}
	public String getForceType() {
		return forceType;
	}
	public void setForceType(String forceType) {
		this.forceType = forceType;
	}
	public String getFlagWarn() {
		return flagWarn;
	}
	public void setFlagWarn(String flagWarn) {
		this.flagWarn = flagWarn;
	}
	public String getFlagFine() {
		return flagFine;
	}
	public void setFlagFine(String flagFine) {
		this.flagFine = flagFine;
	}
	public String getFlagSuspend() {
		return flagSuspend;
	}
	public void setFlagSuspend(String flagSuspend) {
		this.flagSuspend = flagSuspend;
	}
	public String getFlagCancel() {
		return flagCancel;
	}
	public void setFlagCancel(String flagCancel) {
		this.flagCancel = flagCancel;
	}
	public String getFlagDetain() {
		return flagDetain;
	}
	public void setFlagDetain(String flagDetain) {
		this.flagDetain = flagDetain;
	}
	
	
	public String getFlagRevokeVehicle() {
		return flagRevokeVehicle;
	}
	public void setFlagRevokeVehicle(String flagRevokeVehicle) {
		this.flagRevokeVehicle = flagRevokeVehicle;
	}
	public String getFlagRevokeDriver() {
		return flagRevokeDriver;
	}
	public void setFlagRevokeDriver(String flagRevokeDriver) {
		this.flagRevokeDriver = flagRevokeDriver;
	}
	public String getIsGb() {
		return isGb;
	}
	public void setIsGb(String isGb) {
		this.isGb = isGb;
	}
	public String getIssueDay() {
		return issueDay;
	}
	public void setIssueDay(String issueDay) {
		this.issueDay = issueDay;
	}
	public String getIssueEndDay() {
		return issueEndDay;
	}
	public void setIssueEndDay(String issueEndDay) {
		this.issueEndDay = issueEndDay;
	}
	public String getIsCommonUsed() {
		return isCommonUsed;
	}
	public void setIsCommonUsed(String isCommonUsed) {
		this.isCommonUsed = isCommonUsed;
	}
	
	public String getRoadType() {
		return roadType;
	}
	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}
	public String getViolationType() {
		return violationType;
	}
	public void setViolationType(String violationType) {
		this.violationType = violationType;
	}
	public String getLimitSpeed() {
		return limitSpeed;
	}
	public void setLimitSpeed(String limitSpeed) {
		this.limitSpeed = limitSpeed;
	}
	public String getMaxRatio() {
		return MaxRatio;
	}
	public void setMaxRatio(String maxRatio) {
		MaxRatio = maxRatio;
	}
	public String getMinRatio() {
		return minRatio;
	}
	public void setMinRatio(String minRatio) {
		this.minRatio = minRatio;
	}

	
}

package cy.its.violation.domain.model.config;

import java.util.Date;

import cy.its.com.domain.AggregateRoot;

public class VioActionCode extends AggregateRoot {

	/**
	 * 违法代码编号
	 */
	private String vioActionCode;

	/**
	 * 违法类型
	 */
	private String vioType;

	/**
	 * 违法大类(078)
	 */
	public String vioPriorType;

	/**
	 * 违法内容概述
	 */
	private String vioSummary;

	/**
	 * 违法内容
	 */
	public String vioConetent;

	/**
	 * 引用法律条文
	 */
	public String legalReference;

	/**
	 * 处罚依据条款
	 */
	public String punishLegalReference;

	/**
	 * 违法记分
	 */
	public Integer fineScore;

	/**
	 * 最高罚款金额
	 */
	public Integer fineMax;

	/**
	 * 最低罚款金额
	 */
	public Integer fineMin;

	/**
	 * 默认罚款金额
	 */
	public Integer fineDefault;

	/**
	 * 最高暂扣月数
	 */
	public Integer suspendMonthMax;

	/**
	 * 最低暂扣月数
	 */
	public Integer suspendMonthMin;

	/**
	 * 最大拘留天数
	 */
	public Integer detainDaysMax;

	/**
	 * 最小拘留天数
	 */
	public Integer detainDaysMin;

	/**
	 * 强制措施类型(079)
	 */
	public String forceType;

	/**
	 * 警告标记
	 */
	public String flagWarn;

	/**
	 * 罚款标记
	 */
	public String flagFine;

	/**
	 * 暂扣标记
	 */
	public String flagSuspend;

	/**
	 * 吊销标记
	 */
	public String flagCancel;

	/**
	 * 拘留标记
	 */
	public String flagDetain;

	/**
	 * 撤销行驶证标记
	 */
	public String flagRevokeVehicle;

	/**
	 * 撤销驾驶证标记
	 */
	public String flagRevokeDriver;

	/**
	 * 是否国标
	 */
	private String isGb;

	/**
	 * 有效期始
	 */
	private Date issueDay;

	/**
	 * 有效期至
	 */
	private Date issueEndDay;

	/**
	 * 是否常用
	 */
	public String isCommonUsed;

	public VioActionCode() {

	}

	/**
	 * @param vioActionCode
	 * @param vioType
	 * @param vioSummary
	 * @param isGb
	 * @param issueDay
	 * @param issueEndDay
	 * @throws Exception
	 */
	public VioActionCode(String vioActionCode, String vioType, String vioSummary, String isGb, Date issueDay,
			Date issueEndDay) throws Exception {
		this.setVioActionCode(vioActionCode);
		this.setVioType(vioType);
		this.setVioSummary(vioSummary);
		this.setIsGb(isGb);
		this.setIssueDay(issueDay);
		this.setIssueEndDay(issueEndDay);
	}

	public void setVioActionCode(String vioActionCode) throws Exception {
		NotNull(vioActionCode, "违法代码编号");
		this.vioActionCode = vioActionCode;
	}

	public String getVioActionCode() {
		return vioActionCode;
	}

	public void setVioType(String vioType) throws Exception {
		NotNull(vioType, "违法类型");
		this.vioType = vioType;
	}

	public String getVioType() {
		return vioType;
	}

	public void setVioSummary(String vioSummary) throws Exception {
		NotNull(vioSummary, "违法内容概述");
		this.vioSummary = vioSummary;
	}

	public String getVioSummary() {
		return vioSummary;
	}

	public void setIsGb(String isGb) throws Exception {
		NotNull(isGb, "是否国标");
		this.isGb = isGb;
	}

	public String getIsGb() {
		return isGb;
	}

	public void setIssueDay(Date issueDay) throws Exception {
		// NotNull(issueDay, "有效期始");
		this.issueDay = issueDay;
	}

	public Date getIssueDay() {
		return issueDay;
	}

	public void setIssueEndDay(Date issueEndDay) throws Exception {
		// NotNull(issueEndDay, "有效期至");
		this.issueEndDay = issueEndDay;
	}

	public Date getIssueEndDay() {
		return issueEndDay;
	}

	@Override
	public String getIdentityId() {
		return this.vioActionCode;
	}

}

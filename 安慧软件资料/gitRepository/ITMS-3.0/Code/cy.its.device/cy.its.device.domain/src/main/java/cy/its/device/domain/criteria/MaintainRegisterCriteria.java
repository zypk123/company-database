package cy.its.device.domain.criteria;

import java.util.Date;

import cy.its.com.domain.Criteria;

public class MaintainRegisterCriteria extends Criteria
{
	/**
	 * 维护厂家
	 */
	public String maintenanceCompany;

	/**
	 * 分派时间
	 */
	public Date createTimeFrom;
	

	/**
	 * 分派时间
	 */
	public Date createTimeTo;

	/**
	 * 维护结论
	 */
	public String maintendanceResult;

	/**
	 * 维护单状态
	 */
	public String maintenanceStatus;
}
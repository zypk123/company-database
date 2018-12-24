package cy.its.syscfg.domain.criteria;

import cy.its.com.domain.Criteria;

public class OperationLogCriteria extends Criteria {

	/**
	 * 操作用户
	 */
	public String userId;

	/**
	 * 操作用户姓名
	 */
	public String opeUserName;
	
	/**
	 * 操作类型
	 */
	public String sysFunctionCode;

	/**
	 * 操作开始时间
	 */
	public String operateBeginTime;

	/**
	 * 操作结束时间
	 */
	public String operateEndTime;
	
	/**
	 * 操作结果
	 */
	public String operateResult;
	
	/**
	 * 机构权限代码
	 */
	public String orgPrivilegeCode;
}

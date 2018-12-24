package cy.its.device.domain.criteria;

import java.util.Date;

import cy.its.com.domain.Criteria;

public class ContractCriteria extends Criteria {

	/**
	 * 所属机构
	 */
	public String orgId;

	/**
	 * 合同类型
	 */
	public String contractType;

	/**
	 * 承建商ID
	 */
	public String contractorId;

	/**
	 * 签署时间MIN
	 */
	public Date contractTimeFrom;
	
	/**
	 * 签署时间MAX
	 */
	public Date contractTimeTo;
	
	/**
	 * 合同编号
	 */
	public String contractNbr;
	
}

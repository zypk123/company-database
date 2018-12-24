package cy.its.road.domain.criteria;

import cy.its.com.domain.Criteria;


public class SpecialSectionCriteria extends Criteria{

	/**
	 * 特殊路段名称
	 */
	public String specialSectionName;

	/**
	 * 所属道路
	 */
	public String roadId;

	/**
	 * 特殊路段类型。桥梁、隧道、坡道、弯道、事故易发段
	 */
	public String specialSectionType;

}

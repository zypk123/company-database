package cy.its.road.domain.criteria;

import cy.its.com.domain.Criteria;

public class RegionCriteria extends Criteria {

	/**
	 * 区间编号
	 */
	public String regionalCode;
	
	/**
	 * 区间名称
	 */
	public String regionalName;
	
	/**
	 * 所属道路
	 */
	public String roadId;
	
	/**
	 * 行政区划
	 */
	public String districtCode;
	
	/**
	 * 父机构ID
	 * 本条件不为空,查询结果为该机构ID对应的机构及其下属机构的所有流量区间
	 */
	public String orgId;
}

package cy.its.device.domain.criteria;

import java.util.List;

import cy.its.com.domain.Criteria;

public class SiteCriteria extends Criteria {

	/**
	 * 点位代码
	 */
	public String siteCode;

	/**
	 * 点位名称
	 */
	public String siteName;    

	/**
	 * 所属道路
	 */
	public String roadId;
	/**
	 * 所属路段
	 */
	public String roadSectionId;
	/**
	 * 行政区划
	 */
	public String districtCode;
		
	/**
	 * 机构权限编码
	 */
	public String orgPrvCode;
	
	/**
	 * 机构ID列表
	 */
	public List<String> orgIds;
	
}
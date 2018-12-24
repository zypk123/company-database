package cy.its.syscfg.domain.criteria;

import java.util.Date;

import cy.its.com.domain.Criteria;

public class NoticeCriteria extends Criteria {

	/**
	 * 用户所属机构权限代码
	 */
	public String userOrgPrivilegeCode;

	/**
	 * 公告标题
	 */
	public String noticeTitle;
	
	/**
	 * 公告发布时间范围 开始
	 */
	public Date createTimeFrom;
	
	/**
	 * 公告发布时间范围 结束
	 */
	public Date createTimeTo;
}

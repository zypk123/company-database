package cy.its.device.domain.criteria;

import java.util.Date;

import cy.its.com.domain.Criteria;

public class DailyMaintainCriteria extends Criteria {
	
	public String orgId;// 所属机构
	
	public String deviceType;// 设备类型
	
	public String roadId;// 所属道路
	
	public String siteId;// 安装点位
	
	public String contractorId;// 设备厂商
	
	public String orgPrivilegeCode;// 机构权限过滤代码
	
    /**
     * 维护时间 
     */
	public Date maintenanceDateFrom;
	
	
	public Date maintenanceDateTo;

}
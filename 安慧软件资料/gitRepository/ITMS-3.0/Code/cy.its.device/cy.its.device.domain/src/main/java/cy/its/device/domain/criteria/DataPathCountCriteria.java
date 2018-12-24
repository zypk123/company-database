package cy.its.device.domain.criteria;

import java.util.Date;

import cy.its.com.domain.Criteria;

public class DataPathCountCriteria extends Criteria{

	// 道路代码
	public String roadCode;
	
	// 设备编号
	public String deviceSysNbr;
	
	// 点位代码
	public String siteCode;
	
	// 点位名称
	public String siteName;
	
	// 平均延迟 范围 最小值
	public Integer minDelay;
	
	// 平均延迟 范围 最小值
	public Integer maxDelay;
	
	// 起始过车时间
	public Date passTimeFrom;
	
	// 结束过车时间
	public Date passTimeTo;
	
	// 机构权限代码
	public String orgPrivilegeCode;
	
	/**
	 * 是否接入稽查布控系统
	 * 0 : 否
	 * 1 : 是
	 * 2 : 全
	 */
	public int isConnectTrackSys;

}

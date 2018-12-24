package cy.its.device.domain.criteria;

import java.util.List;

import cy.its.com.domain.Criteria;

public class VTollgateCriteria extends Criteria{
	/**
	 * 卡口类型 列表
	 */
	public List<String> tollgateTypeArray;
	
	/**
	 * 设备名称
	 */
	public String deviceName;
	
	/**
	 * 厂商ID
	 */
	public String contractorId;
	
	/**
	 * 使用状态
	 */
	public List<String> useStatusFlagArray;
	
	/**
	 * 机构权限代码
	 */
	public String orgPrivilageCode;
	
	/**
	 * 道路ID
	 */
	public String roadId;
	
	/**
	 * 道路类型
	 */
	public String roadType;
	
	/**
	 * 点位ID
	 */
	public String siteId;
}

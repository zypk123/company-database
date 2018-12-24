package cy.its.device.domain.criteria;

import java.util.List;

import cy.its.com.domain.Criteria;

public class SystemCriteria extends Criteria {

	public String orgPrivilegeCode;
	/**
	 * 电子监控系统ID
	 */
	public String deviceId;

	/**
	 * 系统类型
	 */
	public String deviceType;

	/**
	 * 系统类型数组
	 */
	public String[] deviceTypeArry;
		
	/**
	 * 道路类型
	 */
	public String[] roadTypeArr;
	
	/**
	 * 道路ID数组
	 */
	public String roadId;
	
	/**
	 * 是否支持视频  1: 是; 0: 否;  空:不考虑
	 */
	public String isSupportVideoOrNot;
	
	/**
	 * 安装点位
	 */
	public String siteId;

	/**
	 * 系统编号
	 */
	public String deviceSysNbr;

	/**
	 * 使用状态标识。1-在建（未注册）；2-启用；3-报废
	 */
	public String useStatusFlag;

	/**
	 * 设备IP
	 */
	public String deviceIp;

	/**
	 * 设备状态
	 */
	public String statusType;

	/**
	 * 在线状态
	 */
	public String onlineStatus;
	
	/**
	 * 停用标识
	 */
	public String disableFlag;
	
	/**
	 * 系统名称
	 */
	public String deviceName;
	
	/**
	 * 功能代码列表
	 */
//	public List<String> lstFuncCodeList;
	
	/**
	 * 记录类型，1表示电子监控设备，0表示装备
	 */
	public String recordType;
	
	/**
	 * 厂商ID
	 */
	public String contractorId;
	
	/**
	 * 合同ID
	 */
	public String contractId;
	
	public String ownership;
	
	/**
	 * 服务器平台ID
	 */
	public String serverPlatId;
	
	public String orgId;
	
	/**
	 * 是否支持流量  1: 是; 0: 否;  空:不考虑
	 */
	public String isSupportFlowOrNot;
}
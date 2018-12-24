/**
 * @Title: DeviceFaultCriteria.java
 * @Package cy.its.device.domain.criteria
 * @Description: 设备故障查询条件
 * @author chenzhiying chenzy@cychina.cn
 * @date 2015年11月30日 下午4:01:36
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.device.domain.criteria;

import java.util.Date;

import cy.its.com.domain.Criteria;

/**
  * @ClassName: DeviceFaultCriteria
  * @Description: 设备故障查询条件
  * @author chenzhiying chenzy@cychina.cn
  * @date 2015年11月30日 下午4:01:36
  *
  */

public class DeviceFaultCriteria extends Criteria {
	
	public String orgPrivilegeCode;
	
	public String deviceType;
	
	public String roadId;
	
	public String isValid;
	
	public String deviceSysNbr;
	
	public String deviceId;
	
	/**
	 * 故障类型
	 */
	public String faultType;
	/**
	 * 厂商
	 */
	public String contractorId;
	
	/**
     * 发生时间
     */
	public Date startTimeFrom;
	

    /**
     * 发生时间
     */
	public Date startTimeTo;

	
}

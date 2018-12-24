/**
 * @Title: DeviceMaintainCriteria.java
 * @Package cy.its.device.domain.criteria
 * @Description: TODO(这里要填写用途)
 * @author Administrator Administrator@cychina.cn
 * @date 2015年12月1日 上午10:59:31
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
  * @ClassName: DeviceMaintainCriteria
  * @Description: TODO(这里要填写用途)
  * @author Administrator Administrator@cychina.cn
  * @date 2015年12月1日 上午10:59:31
  *
  */

public class DeviceMaintainCriteria  extends Criteria{
	
	public String orgPrivilegeCode;
	
	/**
	 * 维护厂家
	 */
	public String maintenanceCompanyId;

	/**
	 * 分派时间
	 */
	public Date createTimeFrom;
	

	/**
	 * 分派时间
	 */
	public Date createTimeTo;

	/**
	 * 维护结论
	 */
	public String maintendanceResult;

	/**
	 * 维护单状态
	 */
	public String maintenanceStatus;

}

/**
 * @Title: DeviceRegionCriteria.java
 * @Package cy.its.device.domain.criteria
 * @Description: 区间系统查询条件类
 * @author chenzhiying chenzy@cychina.cn
 * @date 2015年11月19日 下午2:14:33
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.device.domain.criteria;

import cy.its.com.domain.Criteria;

/**
  * @ClassName: DeviceRegionCriteria
  * @Description: 区间系统查询条件类
  * @author chenzhiying chenzy@cychina.cn
  * @date 2015年11月19日 下午2:14:33
  *
  */

public class DeviceRegionCriteria  extends Criteria{

	/**
	 * 区间名称
	 */
	public String regionalName;
	
	/**
	 * 所属道路
	 */
	public String roadId;
	
	/**
	 * 所属机构
	 */
	public String orgId;
	
	/**
	 * 区间编号
	 */
	public String regionalCode;
	
	/**
	 * 设备状态标记
	 * 0-备案 1-启用 2-停用 3-报废
	 */
	public String[] enableFlagArr;
}

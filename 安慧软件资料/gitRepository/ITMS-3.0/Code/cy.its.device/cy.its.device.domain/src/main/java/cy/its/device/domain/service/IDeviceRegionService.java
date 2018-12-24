/**
 * @Title: IDeviceRegionService.java
 * @Package cy.its.device.domain.service
 * @Description: 区间系统领域服务接口类
 * @author chenzhiying chenzy@cychina.cn
 * @date 2015年11月19日 下午3:08:02
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.device.domain.service;

import java.util.List;

import cy.its.device.domain.criteria.DeviceRegionCriteria;
import cy.its.device.domain.model.DeviceRegion;

/**
  * @ClassName: IDeviceRegionService
  * @Description: 区间系统领域服务接口类
  * @author chenzhiying chenzy@cychina.cn
  * @date 2015年11月19日 下午3:08:02
  *
  */

public interface IDeviceRegionService {
	
	String createDeviceRegion(DeviceRegion deviceRegion) throws Exception;
	
	void removeDeviceRegion(String  regionId);
	
	void updateDeviceRegion(DeviceRegion deviceRegion) throws Exception;
	 
	DeviceRegion deviceRegionOfId(String  reionId) throws Exception;
	
	List<DeviceRegion> findDeviceRegion(DeviceRegionCriteria criteria);
	
//	void changeDeviceRegionUseStauts(String devRegionId, String useStatus, String userName) throws RuntimeException;

	/**
	 * 启用区间
	 * @param devRegionId
	 * @param userName
	 * @throws Exception 
	 */
	void enableDeviceRegion(String devRegionId, String userName) throws Exception;
	
	/**
	 * 停用区间
	 * @param devRegionId
	 * @param userName
	 * @throws Exception 
	 */
	void disableDeviceRegion(String devRegionId, String userName) throws Exception;
	
	/**
	 * 废弃区间
	 * @param devRegionId
	 * @param userName
	 * @throws Exception 
	 */
	void discardDeviceRegion(String devRegionId, String userName) throws Exception;
	
	/**
	 * 设备区间变更通知
	 * 设备区间修改、删除和增加时,并且在变更提交到数据库后才可调用本接口
	 */
	void deviceRegionChanged();
}

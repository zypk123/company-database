/**
 * @Title: IDeviceRegionRepository.java
 * @Package cy.its.device.domain.repository
 * @Description: 区间系统repository接口类
 * @author chenzhiying chenzy@cychina.cn
 * @date 2015年11月19日 下午2:21:06
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.device.domain.repository;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.device.domain.criteria.DeviceRegionCriteria;
import cy.its.device.domain.model.DeviceRegion;

/**
 * @ClassName: IDeviceRegionRepository
 * @Description: 区间系统repository接口类
 * @author chenzhiying chenzy@cychina.cn
 * @date 2015年11月19日 下午2:21:06
 *
 */

public interface IDeviceRegionRepository extends IRepository<DeviceRegion> {
	List<DeviceRegion> findDeviceRegion(DeviceRegionCriteria criteria);

	int countDeviceRegion(DeviceRegionCriteria criteria);

	void changeDeviceRegionUseStauts(String devRegionId, String useStatus, String userName) throws RuntimeException;

	void deviceRegionChanged();
}

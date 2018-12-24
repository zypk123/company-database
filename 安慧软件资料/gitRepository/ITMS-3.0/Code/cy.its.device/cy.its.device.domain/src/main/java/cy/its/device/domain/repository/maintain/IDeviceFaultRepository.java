/**
 * @Title: IDeviceFaultRepository.java
 * @Package cy.its.device.domain.repository.maintain
 * @Description: 设备故障repository接口类
 * @author chenzhiying chenzy@cychina.cn
 * @date 2015年11月30日 下午4:35:02
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.device.domain.repository.maintain;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.device.domain.criteria.DeviceFaultCriteria;
import cy.its.device.domain.criteria.SystemCriteria;
import cy.its.device.domain.model.DeviceFault;
import cy.its.device.domain.model.DeviceFaultList;

/**
  * @ClassName: IDeviceFaultRepository
  * @Description: 设备故障repository接口类
  * @author chenzhiying chenzy@cychina.cn
  * @date 2015年11月30日 下午4:35:02
  *
  */

public interface IDeviceFaultRepository extends IRepository<DeviceFault>{
	
	DeviceFault selectDeviceFaultById(String faultId);
	
	List<DeviceFault> findDeviceFaults(DeviceFaultCriteria deviceFaultCriteria);
	
	List<DeviceFaultList> findDeviceFaultsList(DeviceFaultCriteria deviceFaultCriteria);
	
	int countDeviceFaults(DeviceFaultCriteria deviceFaultCriteria);
	
	int deleteNoValidFaults(List<String> faultIdLst);

	List<DeviceFault> findDevFaultsMataince(String deviceId, List<String> faultTypeLst);

	List<DeviceFault> findDevLatestFaultsMataince(SystemCriteria criteria);
}

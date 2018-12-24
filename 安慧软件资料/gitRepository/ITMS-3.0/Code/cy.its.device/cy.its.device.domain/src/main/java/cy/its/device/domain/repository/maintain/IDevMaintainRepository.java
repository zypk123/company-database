/**
 * @Title: IDevMaintainRepository.java
 * @Package cy.its.device.domain.repository.maintain
 * @Description: TODO(这里要填写用途)
 * @author chenzhiying chenzy@cychina.cn
 * @date 2015年11月30日 下午5:21:42
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.device.domain.repository.maintain;

import java.util.List;
import java.util.Map;

import cy.its.com.domain.IRepository;
import cy.its.device.domain.criteria.DeviceMaintainCriteria;
import cy.its.device.domain.model.DeviceMaintainRegister;
import cy.its.device.domain.model.FalutMaintainAsso;

/**
  * @ClassName: IDevMaintainRepository
  * @Description: 维护单Repository接口类
  * @author chenzhiying chenzy@cychina.cn
  * @date 2015年11月30日 下午5:21:42
  *
  */

public interface IDevMaintainRepository  extends IRepository<DeviceMaintainRegister>{
	
	DeviceMaintainRegister selectMaintainByKey(String maintenanceId);
	
    List<DeviceMaintainRegister> findMaintain(DeviceMaintainCriteria deviceMaintainCriteria);
    
    int countMaintain(DeviceMaintainCriteria deviceMaintainCriteria);
    /**
	  * createFalutMaintainAsso(创建故障、维护单关联关系)
	  * @Title: createFalutMaintainAsso
	  * @Description: 创建故障、维护单关联关系
	  * @param @param falutMaintainAsso
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	int createMainFaultAsso(FalutMaintainAsso falutMaintainAsso);
	/**
	  * selectMaintainAndFaultByKey(根据维护单ID，查询维护单信息及其包含的故障列表)
	  * @Title: selectMaintainAndFaultByKey
	  * @Description: 根据维护单ID，查询维护单信息及其包含的故障列表
	  * @param @param maintenanceId
	  * @param @return    设定文件
	  * @return DeviceMaintainRegister    返回类型
	  * @throws
	 */
	DeviceMaintainRegister selectMaintainAndFaultByKey(String maintenanceId);
	/**
	  * deleteMainFaultAsso(删除故障、维护单关联关系)
	  * @Title: deleteMainFaultAsso
	  * @Description: 删除故障、维护单关联关系
	  * @param @param falutMaintainAsso
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int deleteMainFaultAsso(FalutMaintainAsso falutMaintainAsso);
	
	/**
	 * 查询指定设备未解决故障的维护单列表
	 * @param deviceId
	 * @param fault
	 * @return
	 */
	List<DeviceMaintainRegister> maintainsWithOpenFaultOfDevice(String deviceId);
}

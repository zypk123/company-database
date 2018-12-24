/**
 * @Title: IDevMaintainService.java
 * @Package cy.its.device.domain.service
 * @Description: 设备故障、维护领域服务接口类
 * @author chenzhiying chenzy@cychina.cn
 * @date 2015年11月30日 下午9:28:50
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.device.domain.service;

import java.util.Date;
import java.util.List;

import cy.its.com.util.Data;
import cy.its.device.domain.criteria.DeviceFaultCriteria;
import cy.its.device.domain.criteria.DeviceMaintainCriteria;
import cy.its.device.domain.criteria.SystemCriteria;
import cy.its.device.domain.model.DeviceFault;
import cy.its.device.domain.model.DeviceFaultList;
import cy.its.device.domain.model.DeviceMaintainRegister;
import cy.its.device.domain.model.FalutMaintainAsso;
import cy.its.device.domain.model.Fault;

/**
  * @ClassName: IDevMaintainService
  * @Description: 设备故障、维护领域服务接口类
  * @author chenzhiying chenzy@cychina.cn
  * @date 2015年11月30日 下午9:28:50
  *
  */

public interface IDevMaintainService {
	/**
	 * 创建新设备故障信息
	 * 
	 * @param dseviceFault
	 *            设备故障信息
	 */
	public String createDeviceFault(DeviceFault fault);
	/**
	  * selectDeviceFaultById(更加故障ID，查询故障记录)
	  * @Title: selectDeviceFaultById
	  * @Description: 更加故障ID，查询故障记录
	  * @param @param faultId
	  * @param @return    设定文件
	  * @return DeviceFault    返回类型
	  * @throws
	 */
	DeviceFault selectDeviceFaultById(String faultId);
	
	/**
	  * deleteDeviceFaultById(删除一条故障记录)
	  * @Title: deleteDeviceFaultById
	  * @Description: 删除一条故障记录
	  * @param @param faultId
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int deleteDeviceFaultById(String faultId);

	/**
	 * 查询符合条件的设备故障信息列表
	 * 
	 * @param deviceFaultCriteria
	 *            查询条件
	 * @return 设备故障信息列表
	 */
	public List<DeviceFault> findFaults(DeviceFaultCriteria faultCriteria);
	
	
	/**
	 * 查询符合条件的设备故障信息列表(用于排序)
	 * 
	 * @param deviceFaultCriteria
	 *            查询条件
	 * @return 设备故障信息列表
	 */
	public List<DeviceFaultList> findFaultsList(DeviceFaultCriteria faultCriteria);
	

	/**
	 * 确认故障与报警信息的有效性
	 * 
	 * @param faultId
	 * @return
	 * @throws Exception 
	 */
	public Boolean validateFault(String faultId,String validType);
	/**
	  * deleteNoValidFaults(删除无效故障)
	  * @Title: deleteNoValidFaults
	  * @Description: TODO
	  * @param @param faultIdLst
	  * @param @return
	  * @param @throws RuntimeException    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	public int deleteNoValidFaults(List<String> faultIdLst);
	
	int updateFault(DeviceFault deviceFault);
	
	/**
	  * updateFaultProcessStatus(更新故障处理情况)
	  * @Title: updateFaultProcessStatus
	  * @Description: 更新故障处理情况
	  * @param @param faultId
	  * @param @param processStatus
	  * @param @param processTime
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int updateFaultProcessStatus(String faultId,String processStatus,Date processTime);
	
	/**
	  * createMaintainRegister(创建维护单)
	  * @Title: createMaintainRegister
	  * @Description: 创建维护单
	  * @param @param deviceMaintainRegister
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	String createMaintainRegister(DeviceMaintainRegister deviceMaintainRegister);
	/**
	 * 
	  * createMainFaultAsso(创建维护单与故障关联关系)
	  * @Title: createMainFaultAsso
	  * @Description: 创建维护单与故障关联关系
	  * @param @param falutMaintainAsso
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int createMainFaultAsso(FalutMaintainAsso falutMaintainAsso);
	/**
	 * 
	  * selectMaintainByKey(查询维护单)
	  * @Title: selectMaintainByKey
	  * @Description: 查询维护单
	  * @param @param maintenanceId
	  * @param @return    设定文件
	  * @return DeviceMaintainRegister    返回类型
	  * @throws
	 */
	DeviceMaintainRegister selectMaintainByKey(String maintenanceId);
	/**
	 * 
	  * selectMaintainAndFaultByKey(查询维护单及其包含的故障记录)
	  * @Title: selectMaintainAndFaultByKey
	  * @Description: 查询维护单及其包含的故障记录
	  * @param @param maintenanceId
	  * @param @return    设定文件
	  * @return DeviceMaintainRegister    返回类型
	  * @throws
	 */
	DeviceMaintainRegister selectMaintainAndFaultByKey(String maintenanceId);
	/**
	 * 
	  * findMaintain(查询维护单)
	  * @Title: findMaintain
	  * @Description: 查询维护单
	  * @param @param deviceMaintainCriteria
	  * @param @return    设定文件
	  * @return List<DeviceMaintainRegister>    返回类型
	  * @throws
	 */
	List<DeviceMaintainRegister> findMaintain(DeviceMaintainCriteria deviceMaintainCriteria);
	/**
	 * 
	  * dispatchMaintain(分配维护单)
	  * @Title: dispatchMaintain
	  * @Description: 分配维护单
	  * @param @param maintenanceId
	  * @param @param companyId
	  * @param @param contactor
	  * @param @param phone
	  * @param @param finishTime
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int dispatchMaintain(String maintenanceId,String companyId,String contactor,String phone,String finishTime,String dispatchPerson);
	/**
	 * 
	  * callBackMaintain(反馈维护情况)
	  * @Title: callBackMaintain
	  * @Description: 反馈维护情况
	  * @param @param maintenanceId
	  * @param @param mainResult
	  * @param @param solution
	  * @param @param solutionTime
	  * @param @param photoUrl
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int callBackMaintain(String maintenanceId,String mainResult,String solution,Date solutionTime,String photoUrl,String remark);
	/**
	 * 
	  * deleteMaintain(删除维护单记录)
	  * @Title: deleteMaintain
	  * @Description: 删除维护单记录
	  * @param @param maintenanceId
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int deleteMaintain(String maintenanceId);
	/**
	  * deleteMainFaultAsso(删除维护单与故障关联关系)
	  * @Title: deleteMainFaultAsso
	  * @Description: 删除维护单与故障关联关系
	  * @param @param maintenanceId
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int deleteMainFaultAsso(String maintenanceId);
	
	int updateMaintain(DeviceMaintainRegister deviceMaintainRegister);

	int deleteMainAndFaultAsso(FalutMaintainAsso falutMaintainAsso);
	
	List<DeviceFault> findDevFaultsMataince(String deviceId, List<String> faultTypeLst);
	
	
	/**
	 * 查询指定设备的未解决故障的维护单列表
	 * @param deviceId
	 * @return
	 */
	List<DeviceMaintainRegister> maintainsWithOpenFaultOfDevice(String deviceId);
	/**
	 * 查询当前用户下所有设备不同故障类型不同处理状态的最新记录
	 * @param orgPrivilegeCode
	 * @return
	 */
	List<DeviceFault> findDevLatestFaultsMataince(SystemCriteria criteria);
}

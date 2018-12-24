package cy.its.device.domain.service;

import java.util.Date;
import java.util.List;

import cy.its.device.domain.criteria.DailyMaintainCriteria;
import cy.its.device.domain.criteria.FaultCriteria;
import cy.its.device.domain.criteria.MaintainRegisterCriteria;
import cy.its.device.domain.model.DailyMaintain;
import cy.its.device.domain.model.Fault;
import cy.its.device.domain.model.MaintainRegister;

public interface IMaintainService {

	/**
	 * 创建新设备故障信息
	 * 
	 * @param dseviceFault
	 *            设备故障信息
	 */
	public void createDeviceFault(Fault fault);

	/**
	 * 查询符合条件的设备故障信息列表
	 * 
	 * @param deviceFaultCriteria
	 *            查询条件
	 * @return 设备故障信息列表
	 */
	public List<Fault> findFaults(
			FaultCriteria faultCriteria);

	/**
	 * 确认故障与报警信息的有效性
	 * 
	 * @param faultId
	 * @return
	 * @throws Exception 
	 */
	public Boolean validateFault(String faultId) throws Exception;

	/**
	 * 从指定的故障列表创建新维护单
	 * 
	 * @param faultIds
	 *            故障唯一标识列表
	 * @param maintainRegister
	 *            故障维护单
	 * @throws Exception 
	 */
	public void createMaintainRegister(List<String> faultIds,
			MaintainRegister maintainRegister) throws Exception;

	/**
	 * 分派指定的维护单给维护厂家
	 * 
	 * @param maintainId
	 *            分派维护单唯一标识
	 * @param maintenanceCompany
	 *            维护厂家
	 * @param contactPerson
	 *            联系人
	 * @param phoneNbr
	 *            联系电话
	 * @param finishTime
	 *            预计完成时间
	 * @param assignBy
	 *            分派人
	 * @throws Exception 
	 */
	public void assignMaintainToCompany(String maintainId,
			String maintenanceCompany, String contactPerson, String phoneNbr,
			String finishTime, String assignBy) throws Exception;

	/**
	 * 查询指定故障维护单相关的故障列表
	 * 
	 * @param maintenanceId
	 *            故障维护单唯一标识
	 * @return 故障列表
	 */
	public List<Fault> faultsOfMaintain(String maintenanceId);

	/**
	 * 将指定的故障关联到指定的维护单
	 * 
	 * @param maintenanceId
	 *            维护单唯一标识
	 * @param faultId
	 *            设备故障唯一标识
	 * @throws Exception 
	 */
	public void attachFaultToMaintain(String maintenanceId, String faultId) throws Exception;

	/**
	 * 解除指定的维护单与指定故障的关联
	 * 
	 * @param maintenanceId
	 *            维护单唯一标识
	 * @param faultId
	 *            故障唯一标识
	 * @throws Exception
	 */
	public void disAttachFaultOfMaintain(String maintenanceId, String faultId)
			throws Exception;

	/**
	 * 查询符合条件的日常维护单信息列表
	 * 
	 * @param dailyMaintainCriteria
	 *            查询条件
	 * @return 日常维护单信息列表
	 */
	public List<DailyMaintain> findDailyMaintains(
			DailyMaintainCriteria dailyMaintainCriteria);

	/**
	 * 创建新的日常维护单信息
	 * 
	 * @param dailyMaintain
	 *            日常维护单信息
	 */
	public void createDailyMaintain(DailyMaintain dailyMaintain);
	
	/**
	 * 根据日常维护信息ID查询维护信息
	 * @param dailyMaintenanceId 日常维护信息ID
	 * @return 日常维护对象
	 */
	public DailyMaintain dailyMaintainOfId(String dailyMaintenanceId) throws Exception;
	
	/**
	 * 修改日常维护单信息
	 * @param dailyMaintain 日常维护单信息
	 * @throws Exception
	 */
	public void updateDailyMaintain (DailyMaintain dailyMaintain) throws Exception;
	
	/**
	 * 删除日常维护单信息
	 * @param dailyMaintenanceId 日常维护单信息ID
	 * @throws Exception
	 */
	public void deleteDailyMaintain(String dailyMaintenanceId) throws Exception;

	/**
	 * 查询维护单列表
	 * 
	 * @param criteria
	 * @return
	 */
	public List<MaintainRegister> findMaintainRegisters(MaintainRegisterCriteria criteria);

	/**
	 * 登记维护结果
	 * 
	 * @param maintainId
	 *            维护单ID
	 * @param maintendanceResult
	 *            维护单维护结果
	 * @param solution
	 *            解决方法
	 * @param solutionTime
	 *            解决时间
	 * @param remark
	 *            备注
	 * @param processState
	 *            故障处理状态
	 * @throws Exception 
	 */
	public void registerMaintainResult(String maintainId,
			String maintendanceResult, String solution, Date solutionTime,
			String remark, String processState) throws Exception;

}
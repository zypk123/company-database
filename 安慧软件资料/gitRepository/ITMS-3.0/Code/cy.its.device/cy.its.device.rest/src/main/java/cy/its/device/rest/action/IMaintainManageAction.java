package cy.its.device.rest.action;


import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.FaultAlarmDto;
import cy.its.device.rest.dto.MaintainDto;

public interface IMaintainManageAction {
	/**
	 * 查询维护单列表
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public DataGridResult<MaintainDto> queryMaintain(MaintainDto form) throws Exception;
	
	/**
	 * 单个分派维护单
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public String updateMaintain(String maintenanceId, String companyId, String contactor, String phone, String finishTime, String dispatchPerson) throws Exception;
	
	/**
	 * 批量分派维护单
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public String updateSomeMaintain(MaintainDto form) throws Exception;
	
	/**
	 * 登记维护单
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public String updateMaintainResult(String maintenanceId, String mainResult, String solution, String solutionTimes, String remark,String photoUrl,String faultIdStr) throws Exception;
	
	/**
	 * 删除维护单
	 * @param maintenanceIdStr
	 * @return
	 * @throws Exception
	 */
	public String deleteMaintain(String maintenanceId) throws Exception;
	
	/**
	 * 删除故障和维护单的关联关系
	 * @return
	 * @throws Exception
	 */
	public String deleteMainFaultAsso(String faultId,String maintenanceId) throws Exception;
	
	/**
	 * 根据维护单ID查询维护单及故障列表信息
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public DataGridResult<FaultAlarmDto> queryMaintainAndFaultById(MaintainDto form) throws Exception;
}

package cy.its.device.rest.action;

import org.springframework.web.bind.annotation.RequestParam;

import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.MaintainDailyDto;

public interface IMaintainDailyAction {
	/**
	 * 添加日常登记信息
	 * @param form 日常登记信息
	 * @return
	 * @throws Exception
	 */
	public String addMaintainDaily(MaintainDailyDto form) throws Exception;
	
	/**
	 * 删除日常登记信息
	 * @param dailyMaintenanceId 维护记录ID
	 * @return
	 * @throws Exception
	 */
	public String deleteMaintainDaily(String dailyMaintenanceId) throws Exception;
	
	/**
	 * 修改日常登记信息
	 * @param form 日常登记信息
	 * @return
	 * @throws Exception
	 */
	public String editMaintainDaily(MaintainDailyDto form) throws Exception;
	
	/**
	 * 查询日常登记信息列表
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public DataGridResult<MaintainDailyDto> queryMaintainDailyList(MaintainDailyDto form) throws Exception;
	
	/**
	 * 根据日常维护信息ID查询维护信息
	 * @param dailyMaintenanceId 日常维护信息ID
	 * @return 日常维护对象
	 * @throws Exception 异常
	 */
	public MaintainDailyDto lookMaintainDaily(@RequestParam("dailyMaintenanceId") String dailyMaintenanceId) throws Exception;
}

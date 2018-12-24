package cy.its.device.rest.action;

import org.springframework.web.bind.annotation.RequestParam;

import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.IntervalSysDto;

public interface IIntervalSysAction {
	/**
	 * 查询区间系统信息
	 * @param form 查询条件
	 * @return 查询结果对象列表
	 */
	public DataGridResult<IntervalSysDto> queryIntervalSys(IntervalSysDto form) throws Exception; 

	/**
	 * 添加区间系统信息
	 * @param form 添加的区间系统信息
	 * @return 整形 1表示成功，0表示失败
	 */
	public String addIntervalSys(IntervalSysDto form) throws Exception;
	
	/**
	 * 删除区间系统信息
	 * @param regionalId 区间系统ID
	 * @return 整形 1表示成功，0表示失败
	 */
	public int removeIntervalSys(String regionalId) throws Exception;
	
	/**
	 * 编辑区间系统信息
	 * @param form 区间系统编辑后信息
	 * @return 整形 1表示成功，0表示失败
	 */
	public String updateIntervalSys(IntervalSysDto form) throws Exception;
	
	/**
	 * 改变区间系统启用标识
	 * @param devRegionId
	 * @param useStatus
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public int changeDeviceRegionUseStauts(String regionalIdStr, String useStatus, int optStatus) throws Exception;

	


}

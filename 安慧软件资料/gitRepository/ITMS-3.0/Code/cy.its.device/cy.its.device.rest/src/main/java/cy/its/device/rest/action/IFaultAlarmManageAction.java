package cy.its.device.rest.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cy.its.device.rest.dto.CompanyDto;
import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.FaultAlarmDto;
/**
 * 
 * @ClassName: IFaultAlarmManageAction
 * @Description: 故障报警rest服务接口
 * @author qianfx@cychina.cn
 *
 */
public interface IFaultAlarmManageAction {
	/**
	 * 查询故障报警列表
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public DataGridResult<FaultAlarmDto> queryFaultAlarm(FaultAlarmDto form) throws Exception; 
	
	/**
	 * 添加故障信息
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public String addFaultAlarm(FaultAlarmDto form) throws Exception;
	
	/**
	 * 设置设备故障的有效性
	 * @param faultId 故障ID
	 * @param validType	有效性标识
	 * @return
	 * @throws Exception
	 */
	public String updateValidateFault(String faultId,String validType) throws Exception;
	
	/**
	 * 生成维护单
	 * @param faultIdStr 多个故障ID字符串
	 * @param orgPrivilegeCode 机构权限过滤代码
	 * @return
	 * @throws Exception
	 */
	public String addMaintainRegister(String faultIdStr,String orgPrivilegeCode) throws Exception;
	
	/**
	 * 根据但前用户所在机构code查询所有设备
	 * @param orgPrivilegeCode
	 * @return
	 * @throws Exception
	 */
	public List<FaultAlarmDto> findDeviceByOrgPrivilegeCode(String orgPrivilegeCode) throws Exception;
}

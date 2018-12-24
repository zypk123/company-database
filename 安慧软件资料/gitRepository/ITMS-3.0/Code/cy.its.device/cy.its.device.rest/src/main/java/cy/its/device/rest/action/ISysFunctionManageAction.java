package cy.its.device.rest.action;

import java.util.List;

import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.SystemFunctionDto;

public interface ISysFunctionManageAction {
	
	/**
	 * 添加系统功能
	 * @param form 系统功能信息
	 * @return	整形 1表示成功，0表示失败
	 * @throws Exception
	 */
	public int addSysFunction(SystemFunctionDto form) throws Exception;
	
	/**
	 * 查询系统功能列表
	 * @return	对象集合
	 * @throws Exception
	 */
	public DataGridResult<SystemFunctionDto> querySysFunction() throws Exception;
	
	public List<SystemFunctionDto> queryAllSysFunction() throws Exception;
	
	/**
	 * 删除系统功能
	 * @param deviceSysFunctionId 系统功能ID
	 * @return	整形 1表示成功，0表示失败
	 * @throws Exception
	 */
	public int removeSysFunction(String deviceSysFunctionId) throws Exception;
	
	/**
	 * 批量删除系统功能
	 * @param deviceSysFunctionIdStr 多个系统功能ID字符串
	 * @return	整形 1表示成功，0表示失败
	 * @throws Exception
	 */
	public int removeSomeSysFunction(String deviceSysFunctionIdStr) throws Exception;
	
	/**
	 * 编辑系统功能
	 * @param form 系统功能信息
	 * @return	整形 1表示成功，0表示失败
	 * @throws Exception
	 */
	public int editSysFunction(SystemFunctionDto form) throws Exception;
}

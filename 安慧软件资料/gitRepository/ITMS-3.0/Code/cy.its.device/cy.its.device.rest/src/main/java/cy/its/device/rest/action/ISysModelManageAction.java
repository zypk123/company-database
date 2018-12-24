package cy.its.device.rest.action;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;

import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.SystemModelDto;
import cy.its.device.rest.dto.TreeDto;

public interface ISysModelManageAction {
	
	/**
	 * 添加系统型号
	 * @param form 系统型号信息
	 * @return	整形 1表示成功，0表示失败
	 * @throws Exception
	 */
	public int addSysModel(SystemModelDto form) throws Exception;
	
	/**
	 * 编辑系统型号
	 * @param form 系统型号信息
	 * @return	整形 1表示成功，0表示失败
	 * @throws Exception
	 */
	public int updateSysModel(SystemModelDto form) throws Exception;
	
	/**
	 * 查询所有系统型号
	 * @return	对象集合
	 * @throws Exception
	 */
	public List<TreeDto> queryAllSysModel() throws Exception;
	
	/**
	 * 删除系统型号
	 * @param deviceSysFunctionId 系统型号ID
	 * @return	整形 1表示成功，0表示失败
	 * @throws Exception
	 */
	public int removeSysModel(String deviceSysModelId) throws Exception;
	
	/**
	 * 添加系统型号功能
	 * @param form 型号及其功能信息
	 * @return	整形 1表示成功，0表示失败
	 * @throws Exception
	 */
	public int addModelFunction(SystemModelDto form) throws Exception;
	
	/**
	 * 查询系统型号功能列表
	 * @return	对象集合
	 * @throws Exception
	 */
	public DataGridResult<SystemModelDto> queryModelFunction(String deviceSysModelId) throws Exception;
	
	/**
	 * 
	  * querySysFunction(查询系统型号已配置好的功能)
	  * @Title: querySysFunction
	  * @Description: 查询系统型号已配置好的功能
	  * @param @param deviceSysModelId
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return List<SystemModelDto>    返回类型
	  * @throws
	 */
	public List<SystemModelDto> querySysFunction(String deviceSysModelId) throws Exception;
	
	/**
	 * 删除列表中型号功能
	 * @return 整形 1表示成功，0表示失败
	 * @throws Exception
	 */
	public int removeModelFunction(String deviceSysModelId, String deviceSysFunctionId) throws Exception;
	
	/**
	 * 批量删除列表中型号功能
	 * @param deviceSysModelIdStr 多个系统型号ID字符串，deviceSysFunctionIdStr 多个系统功能ID字符串
	 * @return	整形 1表示成功，0表示失败
	 * @throws Exception
	 */
	public int removeSomeModelFunction(String deviceSysModelIdStr, String deviceSysFunctionIdStr) throws Exception;

	/**
	 * 
	  * editModelFunction(编辑系统型号功能信息)
	  * @Title: editModelFunction
	  * @Description: 编辑系统型号功能
	  * @param @param form
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	public int editModelFunction(SystemModelDto form) throws Exception;

}



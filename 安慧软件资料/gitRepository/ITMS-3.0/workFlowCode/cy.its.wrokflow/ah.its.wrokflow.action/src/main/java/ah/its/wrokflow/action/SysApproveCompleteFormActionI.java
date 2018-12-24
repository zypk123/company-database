package ah.its.wrokflow.action;

import java.util.Map;

import ah.its.wrokflow.dto.SystemApproveDto;
import ah.its.wrokflow.repository.dao.SysApprove;
import ah.its.wrokflow.repository.dao.SysApproveFile;

/**
* @Title: SysApproveCompleteFormActionI.java 
* @Package cy.its.wrokflow.approve.action 
* @Description: 系统申请建设后审批单(验收)
* @author lil@cychina.cn
* @date 2016年3月28日 下午8:40:59 
* @version V1.0   
 */
public interface SysApproveCompleteFormActionI {
	
	/**
	 * @Description:保存系统验收申请
	 * @param @param 
	 * @return Map    返回类型 
	 * @throws
	 */
	public Map saveSysApproveComplete(SysApproveFile form);

	/**
	 * 提交系统验收申请
	 * @Description:描述
	 * @param @param 
	 * @return Map    返回类型 
	 * @throws
	 */
	public Map submitSysApproveComplete(String processId,String SystemApproveId);
	
	/**
	 * 保存并提交系统验收申请
	 * @Description:描述
	 * @param @param 
	 * @return Map    返回类型 
	 * @throws
	 */
	public Map saveSysApproveCompleteSubmit(SysApproveFile form);
	
	/**
	 * 更新系统验收申请
	 * @Description:描述
	 * @param @param 
	 * @return Map    返回类型 
	 * @throws
	 */
	public Map updateSysApproveComplete(SysApproveFile form);
	
	/**
	 * 删除系统验收申请
	 * @Description:描述
	 * @param @param 
	 * @return Map    返回类型 
	 * @throws
	 */
	public Map deleteSysApproveComplete(String ids);
	
	/**
	 * 条件查询系统验收申请
	 * @Description:描述
	 * @param @param 
	 * @return Map    返回类型 
	 * @throws
	 */
	public Map querySysApproveComplete(SystemApproveDto dto);
	
	/**
	 * 根据id查找系统验收申请
	 * @Description:描述
	 * @param @param 
	 * @return Map    返回类型 
	 * @throws
	 */
	public Map querySysApproveCompleteById(String approveId);
	
	/**
	 * 根据id查找系统验收申请，并查询审批记录
	 * @Description:描述
	 * @param @param 
	 * @return Map    返回类型 
	 * @throws
	 */
	public Map querySysApproveCompleteDataById(String approveId);
	
	/**
	 * 系统验收申请历史查询
	 * @Description:描述
	 * @param @param 
	 * @return Map    返回类型 
	 * @throws
	 */
	Map querySysApproveCompleteHistory(SystemApproveDto dto);

}

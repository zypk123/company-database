package ah.its.wrokflow.action;

import java.util.Map;

import ah.its.wrokflow.dto.SystemApproveDto;
import ah.its.wrokflow.repository.dao.SysApprove;

/**
 * 系统申报Action接口
 * @Title: SystemApproveFormActionI.java
 * @Package ah.its.wrokflow.action
 * @Description: 系统申报处理
 * @author chengf chengf@cychina.cn
 * @date 2016年6月13日 下午5:04:41
 * @version V1.0
 */
public interface SysApproveFormActionI {
	
	/**
	 * 保存系统申请单
	 * @Description:描述
	 * @param @param 
	 * @return Map    返回类型 
	 * @throws
	 */
	public Map saveSystemApprove(SysApprove form);
	
	/**
	 * 提交系统申请单
	 * @Description:描述
	 * @param @param form
	 * @return Map    返回类型 
	 * @throws
	 */
	public Map submitSystemApprove(String processId,String SystemApproveId);
	
	/**
	 * 保存并提交系统申请单
	 * @Description:描述
	 * @param @param form
	 * @return Map    返回类型 
	 * @throws
	 */
	public Map saveSystemApproveSubmit(SysApprove form);
	
	/**
	 * 更新系统申请单
	 * @Description:更新系统申请单
	 * @param @param form
	 * @return Map    返回类型 
	 * @throws
	 */
	public Map updateSystemApprove(SysApprove form);
	
	/**
	 * 批量删除系统申请单
	 * @Description:描述
	 * @param @param form
	 * @return Map    返回类型 
	 * @throws
	 */
	public Map deleteSystemApprove(String ids);
	
	/**
	 * 条件查询系统申请单
	 * @Description:描述
	 * @param @param form
	 * @return Map    返回类型 
	 * @throws
	 */
	public Map querySystemApprove(SystemApproveDto dto);
	
	/**
	 * 根据申请单id查询申请单详情
	 * @Description:描述
	 * @param @param 
	 * @return Map    返回类型 
	 * @throws
	 */
	public Map querySystemApproveById(String approveId);
	
	/**
	 * 通过ID获取申请单数据，同时查询出已经审批过的记录。
	 * @Description:描述
	 * @param @param 
	 * @return Map    返回类型 
	 * @throws
	 */
	public Map querySystemApproveDataById(String approveId);

	/**
	 * 系统申请历史查询
	 * @Description:描述
	 * @param @param 
	 * @return Map    返回类型 
	 * @throws
	 */
	Map querySystemApproveHistory(SystemApproveDto dto);

	/**
	 * 查询历史记录详情
	 * @Description:描述
	 * @param @param 
	 * @return Map    返回类型 
	 * @throws
	 */
	//Map querySystemApproveHistoryDetail(String approveId);

}

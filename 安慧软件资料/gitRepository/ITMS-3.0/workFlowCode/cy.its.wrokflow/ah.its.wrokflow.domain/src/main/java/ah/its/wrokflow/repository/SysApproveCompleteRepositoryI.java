package ah.its.wrokflow.repository;

import java.util.List;
import java.util.Map;

import ah.its.wrokflow.repository.dao.SysApprove;
import ah.its.wrokflow.repository.dao.SysApproveFile;

public interface SysApproveCompleteRepositoryI {

	
	/**
	 * 保存系统申请单
	 * @Description:描述
	 * @param @param 
	 * @return Map    返回类型 
	 * @throws
	 */
	public  int  saveSystemApproveComplete(SysApproveFile record);
	
	/**
	 * 流程实例ID，更新到申请单中
	 * @Description:描述
	 * @param @param 
	 * @return Map    返回类型 
	 * @throws
	 */
	public  int  updateSystemApproveComplete(SysApproveFile record);
	
	/**
	 * 查询申请单
	 * @Description:描述
	 * @param @param 
	 * @return Map    返回类型 
	 * @throws
	 */
	public  List<SysApproveFile>  selectAllData(Map map);
	
	/**
	 * 删除申请单
	 * @Description:描述
	 * @param @param 
	 * @return Map    返回类型 
	 * @throws
	 */
	public  int  deleteSystemApproveComplete(Map map);
	
	/**
	 * 根据id查找申请单
	 * @Description:描述
	 * @param @param 
	 * @return Map    返回类型 
	 * @throws
	 */
	public SysApproveFile querySystemApproveCompleteById(String approveId);

	/**
	 * 系统申请历史查询
	 * @Description:描述
	 * @param @param 
	 * @return Map    返回类型 
	 * @throws
	 */
	public List<SysApprove> querySystemApproveCompleteHistory(Map map);

}

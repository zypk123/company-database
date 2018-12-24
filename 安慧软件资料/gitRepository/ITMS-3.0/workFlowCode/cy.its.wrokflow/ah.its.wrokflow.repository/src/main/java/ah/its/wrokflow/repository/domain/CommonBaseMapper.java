package ah.its.wrokflow.repository.domain;

import java.util.List;
import java.util.Map;

import ah.its.wrokflow.repository.dao.CommonBaseObj;

public interface CommonBaseMapper {
	
	/** 
	* @Description: 获取部门所有的任务
	* @param @param map
	* @param @return    设定文件 
	* @return List<CommonBaseObj>    返回类型 
	* @throws 
	*/
	List<CommonBaseObj> selectTaskByGroup(Map map);
	
	
	/** 
	* @Description: 获取自己所有的任务
	* @param @param map
	* @param @return    设定文件 
	* @return List<CommonBaseObj>    返回类型 
	* @throws 
	*/
	List<CommonBaseObj> selectTaskByUser(Map map);
	
	/** 
	* @Description: 查找当前用户的通知
	* @param @param map
	* @param @return    设定文件 
	* @return List<CommonBaseObj>    返回类型 
	* @throws 
	*/
	List<CommonBaseObj> selectNoticeByUser(Map map);
	
	/** 
	* @Description: 获取部门审批过的申请任务
	* @param @param map
	* @param @return    设定文件 
	* @return List<CommonBaseObj>    返回类型 
	* @throws 
	*/
	List<CommonBaseObj> selectDevChkByGroup(Map map);
	List<CommonBaseObj> selectSysChkByGroup(Map map);
	List<CommonBaseObj> selectSysCompleteChkByGroup(Map map);
}

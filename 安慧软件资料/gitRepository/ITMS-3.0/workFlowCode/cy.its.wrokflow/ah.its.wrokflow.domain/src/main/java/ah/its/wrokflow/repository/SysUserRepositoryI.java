package ah.its.wrokflow.repository;

import java.util.List;
import java.util.Map;

import ah.its.wrokflow.repository.dao.SysUser;

/**
* @Title: SysUserRepositoryI.java 
* @Package ah.its.wrokflow.repository 
* @Description:
* @author lil@cychina.cn
* @date 2016年4月5日 下午5:45:27 
* @version V1.0   
 */
public interface SysUserRepositoryI {
	
	/** 
	* @Description: 根据用户ID，获取用户的组还有pwd
	* @param @param userId
	* @param @return    设定文件 
	* @return SysUser    返回类型 
	* @throws 
	*/
	SysUser queryUserShipById(String  userId);
	
	
	/**
	 * 查找用户
	 * @param user
	 * @return Map 返回类型 
	 */
	List<SysUser> queryUsers(SysUser user);
	/**
	 * 查找用户（带Group信息）
	 * @param user
	 * @return Map 返回类型 
	 */
	List<SysUser> queryUsersWithGroupinfo(SysUser user);
	
	/**
	 * 根据groupId查找用户
	 * @param String groupId
	 */
	List<SysUser> queryUsersByGroupId(String groupId);
	
}

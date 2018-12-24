package ah.its.wrokflow.domain;

import java.util.List;
import java.util.Map;

import ah.its.wrokflow.repository.dao.SysUser;

/**
* @Title: SysUserDomainI.java 
* @Package ah.its.wrokflow.domain 
* @Description: 用户操作的领域 
* @author lil@cychina.cn
* @date 2016年4月5日 下午6:41:42 
* @version V1.0   
 */
public interface SysUserDomainI {
	
	
	/** 
	* @Description: 返回MAP，状态 status 0 失败，1 成功 
	* @param @param userId
	* @param @return    设定文件 
	* @return Map    返回类型 
	* @throws 
	*/
	Map queryUserShipById(String  userId,String pwd);
	
	/**
	 * 查找用户
	 * @param user
	 * @return
	 */
	Map queryUsers(SysUser user);
	List queryUsersWithGroupinfo(SysUser user);
	
	/**
	 * 根据groupId查找用户
	 * @param String groupId
	 */
	List queryUsersByGroupId(String groupId);
}

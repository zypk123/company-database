package ah.its.wrokflow.action;

import java.util.Map;

import ah.its.wrokflow.dto.SysUserDto;
import ah.its.wrokflow.repository.dao.SysUser;

/**
* @Title: SysUserActionI.java 
* @Package ah.its.wrokflow.action 
* @Description: 处理用户信息类
* @author lil@cychina.cn
* @date 2016年4月5日 下午4:59:25 
* @version V1.0   
 */
public interface SysUserActionI {
	
	/** 
	* @Description: 用户信息验证 
	* @param @param loginName
	* @param @param pwd
	* @param @return    设定文件 
	* @return Map    返回类型 
	* @throws 
	*/
	public  Map  validateUser(String loginName,String  pwd);
	
	
	/**
	 * @Description: 查找用户
	 * @param user
	 * @return
	 */
	public Map findUsers(SysUser user);
	
	/**
	 * 根据groupId查找用户
	 * @param String groupId
	 */
	public Map queryUsersByGroupId(String groupId,String startPage,String pageSize);
	/**
	 * 查询用户信息带有group信息
	 * @param user
	 * @return
	 */
	public Map queryUsersWithGroupinfo(SysUserDto user); 
}

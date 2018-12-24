package cy.its.sysLog.service;

import cy.its.sysLog.domain.UserStatus;

public interface UserStatusLogI {
	/** 
	* @Title: insert 
	* @Description: 新增菜单日志
	* @param @param record
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int updateUserStatus(UserStatus record);
}

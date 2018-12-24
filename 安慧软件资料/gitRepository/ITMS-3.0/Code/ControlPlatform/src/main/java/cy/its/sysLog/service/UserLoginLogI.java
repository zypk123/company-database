package cy.its.sysLog.service;

import cy.its.sysLog.domain.UserLogin;

public interface UserLoginLogI {
	/** 
	* @Title: insert 
	* @Description: 新增菜单日志
	* @param @param record
	* @param @return 设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int insert(UserLogin record);
	
	/** 
	* @Description: 根据登录名，获取用户ID
	* @param @param name
	* @param @return    USER_ID 
	* @return String    返回类型 
	* @throws 
	*/
	String getUserId(String name);
}

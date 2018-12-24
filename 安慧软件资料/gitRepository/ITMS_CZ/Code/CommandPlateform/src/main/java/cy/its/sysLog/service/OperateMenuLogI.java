package cy.its.sysLog.service;

import cy.its.sysLog.domain.OperateMenu;

/**
* @Title: OperateMenuLogI.java 
* @Package cy.its.sysLog.service 
* @Description: 记录菜单操作
* @author lil@cychina.cn
* @date 2016年3月11日 下午4:14:58 
* @version V1.0   
 */
public interface OperateMenuLogI { 
	/** 
	* @Title: insert 
	* @Description: 新增菜单日志
	* @param @param record
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int insert(OperateMenu record);
}

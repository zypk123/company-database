package cy.its.sysLog.service;

import cy.its.sysLog.domain.SyOperationLog;

/**
* @Title: SyOperationLogI.java 
* @Package cy.its.sysLog.service 
* @Description: TODO 操作日记读写
* @author lil@cychina.cn
* @date 2015年8月31日 下午3:42:56 
* @version V1.0   
 */
public interface SyOperationLogI {
	
	/** 
	* @Title: insert 
	* @Description: 新增日志
	* @param @param record
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int insert(SyOperationLog record);
	
}

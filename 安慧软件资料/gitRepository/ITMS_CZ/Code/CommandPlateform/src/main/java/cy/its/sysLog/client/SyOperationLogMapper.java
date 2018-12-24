package cy.its.sysLog.client;

import cy.its.sysLog.domain.SyOperationLog;

/**
* @Title: SyOperationLogMapper.java 
* @Package cy.its.sysLog.dao 
* @Description: 日志接口 
* @author lil@cychina.cn
* @date 2015年9月1日 上午11:17:06 
* @version V1.0   
 */
public interface SyOperationLogMapper {
	
    int insert(SyOperationLog record);
    
}
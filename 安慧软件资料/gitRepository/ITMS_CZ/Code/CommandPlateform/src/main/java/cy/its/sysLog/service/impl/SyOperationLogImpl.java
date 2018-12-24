package cy.its.sysLog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.platform.common.utils.SqlHelper;
import cy.its.sysLog.client.SyOperationLogMapper;
import cy.its.sysLog.domain.SyOperationLog;
import cy.its.sysLog.service.SyOperationLogI;

/**
* @Title: SyOperationLogImpl.java 
* @Package cy.its.sysLog.service.impl 
* @Description: 日同日志数据库操作
* @author lil@cychina.cn
* @date 2015年8月31日 下午3:44:15 
* @version V1.0   
 */
@Service
public class SyOperationLogImpl implements SyOperationLogI{

	@Autowired
	private SyOperationLogMapper syOperationLogMapper;//注入数据库操作类
	
	/* (non-Javadoc)
	 * @see cy.its.sysLog.service.SyOperationLogI#insert(cy.its.sysLog.domain.SyOperationLog)
	 */
	@Override
	public int insert(SyOperationLog record) {
		return syOperationLogMapper.insert(record);
	}

}

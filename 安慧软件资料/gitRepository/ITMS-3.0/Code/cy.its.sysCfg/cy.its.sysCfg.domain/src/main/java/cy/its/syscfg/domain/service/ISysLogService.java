package cy.its.syscfg.domain.service;

import java.util.List;

import cy.its.syscfg.domain.criteria.OperationLogCriteria;
import cy.its.syscfg.domain.model.sysLog.OperationLog;

public interface ISysLogService {

	/**
	 * 查询符合条件的系统日志信息列表
	 * 
	 * @param criteria
	 *            系统日志查询条件
	 * @return 系统日志信息列表
	 */
	public List<OperationLog> findSysLogs(OperationLogCriteria criteria);

	/**
	 * 创建新操作日志
	 * 
	 * @param operationLog
	 *            操作日志信息
	 */
	public void createSysLog(OperationLog operationLog);

}
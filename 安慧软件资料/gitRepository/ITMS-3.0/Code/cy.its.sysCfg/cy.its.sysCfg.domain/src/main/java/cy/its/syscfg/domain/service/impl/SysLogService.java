package cy.its.syscfg.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.syscfg.domain.criteria.OperationLogCriteria;
import cy.its.syscfg.domain.model.sysLog.OperationLog;
import cy.its.syscfg.domain.repository.sysLog.ISysLogRepository;
import cy.its.syscfg.domain.service.ISysLogService;

/**
 * 系统日志服务
 *
 */
@Service
public class SysLogService implements ISysLogService {

	@Autowired
	ISysLogRepository sysLogRepository;

	/**
	 * 查询符合条件的系统日志信息列表
	 * 
	 * @param criteria
	 *            系统日志查询条件
	 * @return 系统日志信息列表
	 */
	public List<OperationLog> findSysLogs(OperationLogCriteria criteria) {
		return sysLogRepository.findOperationLogs(criteria);
	}

	/**
	 * 创建新操作日志
	 * 
	 * @param operationLog
	 *            操作日志信息
	 */
	public void createSysLog(OperationLog operationLog) {
		sysLogRepository.save(operationLog);
	}
}

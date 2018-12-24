package cy.its.syscfg.repository.syslog;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.syscfg.domain.criteria.OperationLogCriteria;
import cy.its.syscfg.domain.model.sysLog.OperationLog;
import cy.its.syscfg.domain.repository.sysLog.ISysLogRepository;
import cy.its.syscfg.mybatis.client.CustomMapper;
import cy.its.syscfg.mybatis.client.T_Sys_Operation_LogMapper;
import cy.its.syscfg.mybatis.model.T_Sys_Operation_Log;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class SyslogRepository implements ISysLogRepository {

	@Autowired
	T_Sys_Operation_LogMapper t_Sys_Operation_LogMapper;

	@Autowired
	CustomMapper customMapper;

	@Override
	public OperationLog aggregateOfId(String id) {
		T_Sys_Operation_Log t_Sys_Operation_Log = t_Sys_Operation_LogMapper
				.selectByPrimaryKey(id);
		OperationLog optLog = new OperationLog();
		ObjectMapUtils.parseObject(optLog, t_Sys_Operation_Log);
		return optLog;
	}

	@Override
	public String save(OperationLog operationLog) {
		try {
			operationLog.setOpeLogId(StringUtil.generateUUID());
		} catch (Exception e) {
		}
		T_Sys_Operation_Log sysLog = new T_Sys_Operation_Log();
		ObjectMapUtils.parseObject(sysLog, operationLog);

		t_Sys_Operation_LogMapper.insertSelective(sysLog);
		return sysLog.getOpeLogId();
	}

	@Override
	public int delete(String id) {
		return t_Sys_Operation_LogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(OperationLog obj) {
		T_Sys_Operation_Log sysLog = new T_Sys_Operation_Log();
		ObjectMapUtils.parseObject(sysLog, obj);
		return t_Sys_Operation_LogMapper.updateByPrimaryKey(sysLog);
	}

	@Override
	public List<OperationLog> findOperationLogs(OperationLogCriteria criteria) {
		Map<String, Object> params = ParamUtil.parseParams(criteria);
		PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize(),true);
		Page<T_Sys_Operation_Log> page =  (Page<T_Sys_Operation_Log>) customMapper.selectOptLogs(params);
		criteria.setTotal((int) page.getTotal());

		return page.getResult()
				.stream()
				.map(c -> {
					try {
						OperationLog oLog = new OperationLog(c.getOpeLogId(), c
								.getSysFunctionCode(), c.getOpeUserName(), c
								.getOperateTime(), c.getOperateRecordCounts(),
								c.getOperateResult(), c.getConsumeSeconds(), c
										.getErrorDesc(), c.getComputerIp(), c
										.getRebackable());
						ObjectMapUtils.parseObject(oLog, c);
						return oLog;
					} catch (Exception e) {
						e.printStackTrace();
					}

					return null;
				}).collect(Collectors.toList());
	}

	@Override
	public int countOperationLogs(OperationLogCriteria criteria) {
		return customMapper.countOptLogs(ParamUtil.parseParams(criteria));
	}
}

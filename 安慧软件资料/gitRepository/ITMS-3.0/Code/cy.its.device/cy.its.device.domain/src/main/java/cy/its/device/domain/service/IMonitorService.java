package cy.its.device.domain.service;

import java.util.List;

import cy.its.device.domain.model.TableSpaceMonitor;

public interface IMonitorService {
	/**
	 * 表空间监控查询
	 * @return 表空间数据
	 */
	public List<TableSpaceMonitor> findTableSpace();
}

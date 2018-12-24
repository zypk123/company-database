package cy.its.device.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.device.domain.model.TableSpaceMonitor;
import cy.its.device.domain.repository.ITableSpaceRepository;
import cy.its.device.domain.service.IMonitorService;

@Service
public class MonitorService implements IMonitorService {
	
	@Autowired
	ITableSpaceRepository tableSpaceRepository;
	/**
	 * 表空间监控查询
	 * @return 表空间数据
	 */
	public List<TableSpaceMonitor> findTableSpace(){
		return tableSpaceRepository.findTableSpace();
	}
}

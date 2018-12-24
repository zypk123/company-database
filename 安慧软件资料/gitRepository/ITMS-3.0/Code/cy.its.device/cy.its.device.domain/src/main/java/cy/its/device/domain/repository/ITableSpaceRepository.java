package cy.its.device.domain.repository;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.device.domain.model.TableSpaceMonitor;

public interface ITableSpaceRepository {
	
	List<TableSpaceMonitor> findTableSpace();
}

package cy.its.device.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.device.domain.model.TableSpaceMonitor;
import cy.its.device.domain.repository.ITableSpaceRepository;
import cy.its.device.mybatis.client.TableSpaceMapper;

@Service
public class TableSpaceRepository implements ITableSpaceRepository {
	
	@Autowired
	TableSpaceMapper tableSpaceMapper;

	@Override
	public List<TableSpaceMonitor> findTableSpace() {
		
		return tableSpaceMapper.selectTableSpace();
	}

}

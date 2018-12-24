package cy.its.device.mybatis.client;

import java.util.List;

import cy.its.device.domain.model.TableSpaceMonitor;

public interface TableSpaceMapper {
	List<TableSpaceMonitor> selectTableSpace();
}

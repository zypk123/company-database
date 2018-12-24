package cy.its.device.domain.repository.surveySystem;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.device.domain.criteria.ServerAppCriteria;
import cy.its.device.domain.model.ServerApplication;

public interface IServerApplicationRepository extends IRepository<ServerApplication> {

	List<ServerApplication> findServerApps(ServerAppCriteria criteria);
	int countServerApps(ServerAppCriteria criteria);

}

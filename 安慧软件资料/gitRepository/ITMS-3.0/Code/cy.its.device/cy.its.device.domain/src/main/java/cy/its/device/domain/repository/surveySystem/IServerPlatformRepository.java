package cy.its.device.domain.repository.surveySystem;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.device.domain.criteria.ServerPlatformCriteria;
import cy.its.device.domain.model.ServerPlatform;
import cy.its.device.domain.model.ServerPlatformAppAsso;

public interface IServerPlatformRepository extends IRepository<ServerPlatform> {
	List<ServerPlatform> findServerPlatforms(ServerPlatformCriteria criteria);

	void savePlatAppAsso(ServerPlatformAppAsso asso);

	void deletePlatAppAsso(ServerPlatformAppAsso asso);

	void updatePlatAppAsso(ServerPlatformAppAsso oldAsso, ServerPlatformAppAsso newAsso);

	List<ServerPlatformAppAsso> findPlatAppAsso(String serverPlatId, String serverAppId);
}

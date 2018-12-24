package cy.its.device.domain.repository.surveySystem;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.device.domain.model.BackupService;
import cy.its.device.domain.model.DataSheet;
import cy.its.device.domain.model.Server;

public interface IServerRepository extends IRepository<Server> {
	List<Server> findServers(String surveySystemId);
	List<BackupService> backServicsOfServer(String serverId);
	List<DataSheet> dataSheetsOfServer(String serverId);
    List<Server> selectServerOfIp(String serverIp);
	void serverChanged();
}

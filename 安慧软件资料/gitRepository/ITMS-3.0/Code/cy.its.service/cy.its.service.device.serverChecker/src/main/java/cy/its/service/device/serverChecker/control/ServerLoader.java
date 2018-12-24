package cy.its.service.device.serverChecker.control;

import java.util.List;

import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.device.serverChecker.data.ServerDAO;
import cy.its.service.device.serverChecker.ifs.IServerLoader;
import cy.its.service.device.serverChecker.ifs.IServerManager;
import cy.its.service.device.serverChecker.model.ServerInfo;

@Export
public class ServerLoader implements IServerLoader {

	@Import
	IServerManager serverManager;
	
	@Override
	public void loadServer() throws Exception {
		List<ServerInfo> servers = ServerDAO.getServerInfo();		
		serverManager.loadServer(servers);
	}
	
	@Override
	public void publishAllStatus(){
		serverManager.publishAllServerStatus();
	}

	@Override
	public void periodCheckStatus() {
		serverManager.periodCheckStatus();
	}
}

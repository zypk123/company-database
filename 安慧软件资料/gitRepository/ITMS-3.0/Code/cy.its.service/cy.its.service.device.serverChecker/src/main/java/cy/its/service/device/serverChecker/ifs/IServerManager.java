package cy.its.service.device.serverChecker.ifs;

import java.util.List;

import cy.its.service.common.dataModel.SurveyUpgrade_ServiceStatus;
import cy.its.service.common.dataModel.SurveyUpgrade_ServerStatus;
import cy.its.service.device.serverChecker.model.ServerInfo;

public interface IServerManager {
	
	void loadServer(List<ServerInfo> servers) throws Exception ;	
	
	void receiveServerStatus(SurveyUpgrade_ServerStatus serverStatus);	

	void receiveServiceStatus(SurveyUpgrade_ServiceStatus processStatus);
	
	void publishAllServerStatus();
	
	void periodCheckStatus();
}

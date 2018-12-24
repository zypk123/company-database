package cy.its.service.device.serverChecker.ifs;

public interface IServerLoader {
	
	void loadServer() throws Throwable;

	void publishAllStatus();
	
	void periodCheckStatus();
}

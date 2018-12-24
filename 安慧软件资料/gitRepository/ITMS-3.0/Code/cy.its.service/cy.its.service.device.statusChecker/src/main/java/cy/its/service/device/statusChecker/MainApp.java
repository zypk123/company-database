package cy.its.service.device.statusChecker;

import cy.its.service.common.ice.grid.AppService;
import cy.its.service.common.ioc.AppContext;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.common.log.LogManager;


@Export
public class MainApp extends AppService {

	@Import
	LoadRunner loadRunner;

	/**
	 * ³ÌÐòÈë¿Ú
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LogManager.createDefaultLogger("statusChecker", "statusChecker");
			AppContext.init("cy.its.service.device.statusChecker");
			Run(AppContext.getBean(MainApp.class), args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Start(String[] args) throws Exception {
		loadRunner.start();
	}

	@Override
	public void Stop() throws Exception {
		loadRunner.stop();
	}

	@Override
	public String getAppName() {
		return "statusChecker";
	}
}

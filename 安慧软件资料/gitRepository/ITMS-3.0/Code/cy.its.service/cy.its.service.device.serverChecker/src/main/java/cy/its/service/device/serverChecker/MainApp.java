package cy.its.service.device.serverChecker;

import cy.its.service.common.ice.grid.AppService;
import cy.its.service.common.ioc.AppContext;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.common.log.LogManager;
import cy.its.service.device.serverChecker.ifs.ILoadRunner;

@Export
public class MainApp extends AppService {

	@Import
	ILoadRunner loader;
	
	/**
	 * ³ÌÐòÈë¿Ú
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LogManager.createDefaultLogger("serverChecker", "serverChecker");
			AppContext.init("cy.its.service.device.serverChecker");
			Run(AppContext.getBean(MainApp.class), args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void Start(String[] args) throws Exception {
		loader.Start();
	}

	@Override
	public void Stop() throws Exception {
		loader.Stop();
	}

	@Override
	public String getAppName() {
		return "serverChecker";
	}

}

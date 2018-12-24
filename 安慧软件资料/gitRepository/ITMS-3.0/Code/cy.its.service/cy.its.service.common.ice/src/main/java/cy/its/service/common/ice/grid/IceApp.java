package cy.its.service.common.ice.grid;

import Ice.Application;

final class IceApp extends Application {

	AppService service;

	public IceApp(AppService service) {
		this.service = service;
	}

	@Override
	public int run(String[] args) {

		if (service == null) {
			return -1;
		}

		try {
			service.Start(args);
			this.waitForExit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		try {
			service.Stop();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	private void waitForExit() {
//		 Scanner scan = new Scanner(System.in);
//		 scan.nextLine();
		communicator().waitForShutdown();
	}

}

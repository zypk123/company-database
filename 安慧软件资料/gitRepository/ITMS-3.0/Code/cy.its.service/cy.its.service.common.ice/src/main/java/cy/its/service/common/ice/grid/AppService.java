package cy.its.service.common.ice.grid;

public abstract class AppService {
	
	public abstract void Start(String[] args) throws Exception;

	public abstract void Stop() throws Exception;
    
	public abstract String getAppName();

    public static void Run(AppService service, String[] args)
    {
        IceApp app = new IceApp(service);
        app.main(service.getAppName(), args);
        System.exit(0);
    }
}

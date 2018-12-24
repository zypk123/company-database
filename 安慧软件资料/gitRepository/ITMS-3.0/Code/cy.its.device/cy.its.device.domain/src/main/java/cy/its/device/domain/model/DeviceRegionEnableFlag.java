package cy.its.device.domain.model;

public enum DeviceRegionEnableFlag {
	NEW(new DeviceRegionFlagNew()),
	ENABLED(new DeviceRegionFlagEnabled()),
	DISABLED(new DeviceRegionFlagDisabled()),
	DISCARDED(new DeviceRegionFlagDiscarded());
	
	
	DeviceRegionEnableFlag(DeviceRegionFlag flag){
		this.flag = flag;
	}
	
	
	DeviceRegionFlag flag;
	
	public void setEnableFlag(DeviceRegion region) throws Exception{
		flag.execute(region);
	}
}

abstract class DeviceRegionFlag {
	protected final static String FLAG_NEW = "0";
	protected final static String FLAG_ENABLED = "1";
	protected final static String FLAG_DISABLED = "2";
	protected final static String FLAG_DISCARDED = "3";
	
	abstract void execute(DeviceRegion region) throws Exception;	
}
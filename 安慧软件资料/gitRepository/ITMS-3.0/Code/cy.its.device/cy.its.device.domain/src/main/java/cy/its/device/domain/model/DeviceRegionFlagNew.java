package cy.its.device.domain.model;

class DeviceRegionFlagNew extends DeviceRegionFlag {

	@Override
	void execute(DeviceRegion region) throws Exception {
		if (FLAG_ENABLED.equals(region.getEnableFlag()) ||
			FLAG_DISABLED.equals(region.getEnableFlag())|| 
			FLAG_DISCARDED.equals(region.getEnableFlag())) {
			throw new Exception("区间已经处于非备案状态,无法再改为备案状态");
		}
		
		region.setEnableFlag(FLAG_NEW);
	}
}

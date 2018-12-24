package cy.its.device.domain.model;

class DeviceRegionFlagEnabled extends DeviceRegionFlag {

	@Override
	void execute(DeviceRegion region) throws Exception {

		if (FLAG_ENABLED.equals(region.getEnableFlag())) {
			throw new Exception("无法启用区间,因为该区间已经处于启用状态");
		}

		if (FLAG_DISCARDED.equals(region.getEnableFlag())) {
			throw new Exception("无法启用区间,因为该区间已经处于废弃状态");
		}

		region.setEnableFlag(FLAG_ENABLED);
	}
}

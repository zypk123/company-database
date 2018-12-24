package cy.its.device.domain.model;

class DeviceRegionFlagDisabled extends DeviceRegionFlag {

	@Override
	void execute(DeviceRegion region) throws Exception {
		if (FLAG_NEW.equals(region.getEnableFlag())) {
			throw new Exception("无法停用区间,因为该区间处于备案状态");
		}

		if (FLAG_DISCARDED.equals(region.getEnableFlag())) {
			throw new Exception("无法废弃区间,因为该区间处于废弃状态");
		}

		if (FLAG_DISABLED.equals(region.getEnableFlag())) {
			throw new Exception("无法废弃区间,因为该区间已经处于停用状态");
		}

		region.setEnableFlag(FLAG_DISABLED);
	}

}

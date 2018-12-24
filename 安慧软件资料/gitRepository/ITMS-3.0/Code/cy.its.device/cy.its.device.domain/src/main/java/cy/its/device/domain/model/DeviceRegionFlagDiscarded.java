package cy.its.device.domain.model;

class DeviceRegionFlagDiscarded extends DeviceRegionFlag {

	@Override
	void execute(DeviceRegion region) throws Exception {
		if (FLAG_NEW.equals(region.getEnableFlag())) {
			throw new Exception("无法废弃区间,因为该区间处于备案状态");
		}

		if (FLAG_ENABLED.equals(region.getEnableFlag())) {
			throw new Exception("无法废弃区间,因为该区间处于启用状态");
		}

		if (FLAG_DISCARDED.equals(region.getEnableFlag())) {
			throw new Exception("无法废弃区间,因为该区间已经处于废弃状态");
		}

		region.setEnableFlag(FLAG_DISCARDED);
	}

}

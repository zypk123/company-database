package cy.its.device.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.device.domain.criteria.DeviceLedSpecCriteria;
import cy.its.device.domain.model.DeviceLedSpec;
import cy.its.device.domain.repository.IDeviceLedSpecRepository;
import cy.its.device.domain.service.IDeviceLedSpecService;
@Service
public class DeviceLedSpecService implements IDeviceLedSpecService {
	
	@Autowired
	IDeviceLedSpecRepository DeviceLedSpecRepository;
	
	@Override
	public String creatDeviceLedSpec(DeviceLedSpec deviceLedSpec) {
		// TODO Auto-generated method stub
		return DeviceLedSpecRepository.save(deviceLedSpec);
	}

	@Override
	public void updateDeviceLedSpec(DeviceLedSpec deviceLedSpec) {
		// TODO Auto-generated method stub
		DeviceLedSpecRepository.update(deviceLedSpec);
	}


	@Override
	public List<DeviceLedSpec> findByDeviceLedSpec(DeviceLedSpecCriteria deviceLedCritera) {
		// TODO Auto-generated method stub
		return DeviceLedSpecRepository.findByDeviceLedSpec(deviceLedCritera);
	}

	@Override
	public int removeDeviceLedSpecId(Map<String,Object> ids) {
		// TODO Auto-generated method stub
		return DeviceLedSpecRepository.removeDeviceLedSpecId(ids);
	}

}

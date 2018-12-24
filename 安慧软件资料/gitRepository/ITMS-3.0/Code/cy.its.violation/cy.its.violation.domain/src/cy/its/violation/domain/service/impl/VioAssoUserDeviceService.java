package cy.its.violation.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.violation.domain.criteria.AssoUserDeviceCriteria;
import cy.its.violation.domain.model.config.VioAssoUserDevice;
import cy.its.violation.domain.service.IVioAssoUserDeviceService;
import cy.its.violation.repository.IVioAssoUserDeviceRepository;

@Service
public class VioAssoUserDeviceService implements IVioAssoUserDeviceService {

	@Autowired
	IVioAssoUserDeviceRepository assoUserDeviceRepository;

	@Override
	public int removeVioAssoUserDevice(String id) {
		return assoUserDeviceRepository.delete(id);
	}

	@Override
	public int removeVioAssoUserDeviceByUserID(String userID) {
		return assoUserDeviceRepository.deleteByUserID(userID);
	}

	@Override
	public String saveVioAssoUserDevice(VioAssoUserDevice code) {
		return assoUserDeviceRepository.save(code);
	}

	@Override
	public int updateVioAssoUserDevice(VioAssoUserDevice code) {
		return assoUserDeviceRepository.update(code);
	}

	@Override
	public List<VioAssoUserDevice> findVioAssoUserDevice(AssoUserDeviceCriteria crieria) {
		return assoUserDeviceRepository.findVioAssoUserDevice(crieria);
	}

	@Override
	public VioAssoUserDevice findVioAssoUserDeviceByPrimaryKey(String id) throws Exception {
		return assoUserDeviceRepository.aggregateOfId(id);
	}

}

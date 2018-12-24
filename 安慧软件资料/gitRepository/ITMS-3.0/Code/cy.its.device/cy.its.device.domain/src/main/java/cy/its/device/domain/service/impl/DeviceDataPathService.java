package cy.its.device.domain.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.device.domain.criteria.DataPathCountCriteria;
import cy.its.device.domain.criteria.DeviceDataPathCriteria;
import cy.its.device.domain.model.DataPathBasic;
import cy.its.device.domain.model.DataPathDetail;
import cy.its.device.domain.model.DataPathDynamicDetail;
import cy.its.device.domain.model.DeviceDataPath;
import cy.its.device.domain.repository.IDeviceDataPathRepository;
import cy.its.device.domain.service.IDeviceDataPathService;


@Service
public class DeviceDataPathService implements IDeviceDataPathService {
	
	@Autowired
	IDeviceDataPathRepository deviceDataPathRepository;
	
	@Override
	public List<DeviceDataPath> findDataPath(DeviceDataPathCriteria criteria) {
		// TODO Auto-generated method stub
		return deviceDataPathRepository.findDataPath(criteria);
	}
	
	@Override
	public List<DataPathBasic> countDataPathBasics(DataPathCountCriteria criteria) {
		return deviceDataPathRepository.countDataPathBasics(criteria);
	}
	
	@Override
	public DataPathDetail dataPathDetailOfDevice(String deviceSysNbr, Date passTimeBegin, Date passTimeEnd) {
		return deviceDataPathRepository.dataPathDetailOfDevice(deviceSysNbr, passTimeBegin, passTimeEnd);
	}

	@Override
	public DataPathDynamicDetail dynamicDataPathDetailOfDevice(String deviceSysNbr, Date passTimeBegin, Date passTimeEnd) {
		return deviceDataPathRepository.dynamicDataPathDetailOfDevice(deviceSysNbr, passTimeBegin, passTimeEnd);
	}

	@Override
	public DataPathDynamicDetail dynamicDataPathToItmsDetailOfDevice(String deviceSysNbr, Date passTimeBegin,
			Date passTimeEnd) {
		return deviceDataPathRepository.dynamicDataPathToItmsDetailOfDevice(deviceSysNbr, passTimeBegin, passTimeEnd);
	}

	@Override
	public List<DataPathBasic> countAllDataPathBasics(DataPathCountCriteria criteria) {
		return deviceDataPathRepository.countAllDataPathBasics(criteria);
	}
}

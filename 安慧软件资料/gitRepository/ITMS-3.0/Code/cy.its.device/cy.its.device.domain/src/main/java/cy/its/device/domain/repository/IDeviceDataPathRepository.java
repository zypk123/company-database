package cy.its.device.domain.repository;

import java.util.Date;
import java.util.List;

import cy.its.device.domain.criteria.DataPathCountCriteria;
import cy.its.device.domain.criteria.DeviceDataPathCriteria;
import cy.its.device.domain.model.DataPathBasic;
import cy.its.device.domain.model.DataPathDetail;
import cy.its.device.domain.model.DataPathDynamicDetail;
import cy.its.device.domain.model.DeviceDataPath;

public interface IDeviceDataPathRepository {
	//查数据轨迹列表
	List<DeviceDataPath> findDataPath(DeviceDataPathCriteria criteria);
	
	int countDataPath(DeviceDataPathCriteria criteria);

	DataPathDetail dataPathDetailOfDevice(String deviceSysNbr, Date passTimeBegin, Date passTimeEnd);
	
	List<DataPathBasic> countDataPathBasics(DataPathCountCriteria criteria);

	DataPathDynamicDetail dynamicDataPathDetailOfDevice(String deviceSysNbr, Date passTimeBegin, Date passTimeEnd);

	List<DataPathBasic> countDataPathToItmsBasics(DataPathCountCriteria criteria);

	DataPathDynamicDetail dynamicDataPathToItmsDetailOfDevice(String deviceSysNbr, Date passTimeBegin, Date passTimeEnd);

	List<DataPathBasic> countAllDataPathBasics(DataPathCountCriteria criteria);
}

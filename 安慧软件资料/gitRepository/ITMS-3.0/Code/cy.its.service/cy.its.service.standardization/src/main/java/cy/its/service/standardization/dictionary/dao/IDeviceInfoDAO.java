package cy.its.service.standardization.dictionary.dao;

import java.util.List;

import cy.its.service.common.dataAccess.DbAccess;
import cy.its.service.standardization.dictionary.model.DeviceInfo;

public interface IDeviceInfoDAO {

	List<DeviceInfo> selectDeviceInfo(DbAccess dbAccess) throws Exception;

}
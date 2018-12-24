package cy.its.service.standardization.dictionary.dao;

import java.util.List;

import cy.its.service.common.dataAccess.DbAccess;
import cy.its.service.standardization.dictionary.model.SurveyUpgrade_DeviceInfo;

public interface ISurveyUpgrade_DeviceDAO {
	List<SurveyUpgrade_DeviceInfo> selectDeviceInfo(DbAccess dbAccess) throws Exception;
}

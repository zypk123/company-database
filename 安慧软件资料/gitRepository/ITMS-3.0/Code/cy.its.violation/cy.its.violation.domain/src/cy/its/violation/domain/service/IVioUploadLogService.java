package cy.its.violation.domain.service;

import cy.its.violation.domain.model.config.VioUploadLog;

public interface IVioUploadLogService {

	int removeVioUploadLog(String id);

	String saveVioUploadLog(VioUploadLog code);

	int updateVioUploadLog(VioUploadLog code);

	VioUploadLog findVioUploadLogByPrimaryKey(String id) throws Exception;
}

package cy.its.violation.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.violation.domain.model.config.VioUploadLog;
import cy.its.violation.domain.service.IVioUploadLogService;
import cy.its.violation.repository.IVioUploadLogRepository;

@Service
public class VioUploadLogService implements IVioUploadLogService {

	@Autowired
	IVioUploadLogRepository vioUploadLogRepository;

	@Override
	public int removeVioUploadLog(String id) {
		return vioUploadLogRepository.delete(id);
	}

	@Override
	public String saveVioUploadLog(VioUploadLog code) {
		return vioUploadLogRepository.save(code);
	}

	@Override
	public int updateVioUploadLog(VioUploadLog code) {
		return vioUploadLogRepository.update(code);
	}

	@Override
	public VioUploadLog findVioUploadLogByPrimaryKey(String id) throws Exception {
		return vioUploadLogRepository.aggregateOfId(id);
	}

}

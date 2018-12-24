package cy.its.device.domain.repository.led;

import java.util.Date;
import java.util.List;

import cy.its.device.domain.criteria.LedPublishLogCriteria;
import cy.its.device.domain.model.led.LedControlParam;
import cy.its.device.domain.model.led.LedField;
import cy.its.device.domain.model.led.LedPublishLog;
import cy.its.device.domain.model.led.LedTask;
import cy.its.device.domain.model.led.ReponseResult;

public interface ILedOtherRepository {

	List<LedField> allLedFields();

	List<LedPublishLog> findLedPublishLogs(LedPublishLogCriteria criteria);

	void createLedPublishLog(LedPublishLog ledPublishLog);

	int countLedPublishLogs(LedPublishLogCriteria criteria);
		
	void savePublishLog(LedTask ledTask, LedControlParam params, 
			ReponseResult respRslt, Date beginTime,
			Date endTime);

}

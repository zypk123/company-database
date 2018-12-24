package cy.its.device.domain.repository.led;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.device.domain.criteria.LedTaskCriteria;
import cy.its.device.domain.model.led.LedTask;

public interface ILedTaskRepository extends IRepository<LedTask> {
	List<LedTask> findLedTask(LedTaskCriteria criteria);
	int countLedTask(LedTaskCriteria criteria);
	String generateTaskId();
}

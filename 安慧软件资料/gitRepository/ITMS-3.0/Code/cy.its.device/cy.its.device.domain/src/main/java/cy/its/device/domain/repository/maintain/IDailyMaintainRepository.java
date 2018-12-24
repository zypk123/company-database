package cy.its.device.domain.repository.maintain;

import cy.its.com.domain.IRepository;
import cy.its.device.domain.criteria.*;
import cy.its.device.domain.model.DailyMaintain;

public interface IDailyMaintainRepository extends IRepository<DailyMaintain>
{
	java.util.List<DailyMaintain> findDailyMaintains(DailyMaintainCriteria maintainCriteria);

	int countDailyMaintains(DailyMaintainCriteria criteria);
	
	//DailyMaintain dailyMaintainOfId(String dailyMaintenanceId);
}

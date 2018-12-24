package cy.its.video.domain.repository;

import java.util.List;
import java.util.Map;

import cy.its.com.domain.IRepository;
import cy.its.video.domain.model.TrafficPlan;

public interface ITrafficPlanRepository extends IRepository<TrafficPlan> {

	/**
	 * 获取所有巡航方案列表
	 * 
	 * @return
	 */
	List<TrafficPlan> getTrafficPlanByCondition(Map<String, String> map);

}

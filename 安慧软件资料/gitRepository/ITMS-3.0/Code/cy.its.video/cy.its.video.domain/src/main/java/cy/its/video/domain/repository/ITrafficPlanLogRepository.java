package cy.its.video.domain.repository;

import java.util.List;
import java.util.Map;

import cy.its.com.domain.IRepository;
import cy.its.video.domain.model.TrafficPlanLog;

public interface ITrafficPlanLogRepository extends IRepository<TrafficPlanLog> {

	/**
	 * 根据巡航方案获取巡航日志
	 * 
	 * @param videoPlanID
	 * @return
	 */
	List<TrafficPlanLog> getTrafficPlanLog(Map<String, Object> map);

	/**
	 * 根据条件获取总数
	 * 
	 * @param map
	 * @return
	 */
	int countTrafficPlanLogTotal(Map<String, Object> map);

}

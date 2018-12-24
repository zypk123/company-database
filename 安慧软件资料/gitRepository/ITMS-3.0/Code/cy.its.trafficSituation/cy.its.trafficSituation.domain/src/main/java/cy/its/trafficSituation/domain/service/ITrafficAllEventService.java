package cy.its.trafficSituation.domain.service;

import java.util.List;
import java.util.Map;

public interface ITrafficAllEventService {

	/*
	 * 气象
	 */
	public List queryWeatherEvent(Map map);
	
	/*
	 * 能见度
	 */
	public List queryVisibilityEvent(Map map);

	/*
	 *路感
	 */
	public List queryRoadSensorEvent(Map map);

	/*
	 * 事件检测
	 */
	public List queryTrafficEvent(Map map);
	
}

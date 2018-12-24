/**
 * @Title: ITrafficWeatherRepository.java
 * @Package cy.its.trafficSituation.domain.repository
 * @Description: 气象仪基础设施层接口
 * @author gyf guanyf@cychina.cn
 * @date 2015年10月28日 下午7:51:10
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.domain.repository;

import java.util.List;
import java.util.Map;

import cy.its.com.domain.IRepository;
import cy.its.trafficSituation.domain.model.TrafficWeather;

public interface ITrafficWeatherRepository extends IRepository<TrafficWeather> {
	List<TrafficWeather> selectWeathers(Map<String,Object> map);
	TrafficWeather getLastWeatherByNBR(String deviceNbr);
	List<TrafficWeather> select2HourWeathers(Map<String, Object> map);
}

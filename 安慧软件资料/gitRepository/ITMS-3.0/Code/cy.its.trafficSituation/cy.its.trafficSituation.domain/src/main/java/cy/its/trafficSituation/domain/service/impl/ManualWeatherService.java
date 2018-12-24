/**
 * @Title: ManualWeatherService.java
 * @Package cy.its.trafficSituation.domain.service.impl
 * @Description:  人工登记恶劣天气领域层实现类
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月10日 下午3:23:51
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.trafficSituation.domain.model.TrafficManualWeather;
import cy.its.trafficSituation.domain.repository.IManualWeatherRepository;
import cy.its.trafficSituation.domain.service.IManualWeatherService;

@Service
public class ManualWeatherService implements IManualWeatherService {
	@Autowired
	IManualWeatherRepository manualWeatherRepository;
	/*
	  * <p>Title: selectById</p>
	  * <p>Description: </p>
	  * @param id
	  * @return
	  * @see cy.its.trafficSituation.domain.service.IManualWeatherService#selectById(java.lang.String)
	  */

	@Override
	public TrafficManualWeather selectById(String id) throws Exception {
		return manualWeatherRepository.aggregateOfId(id);
	}

	/*
	  * <p>Title: save</p>
	  * <p>Description: </p>
	  * @param manualWeather
	  * @return
	  * @see cy.its.trafficSituation.domain.service.IManualWeatherService#save(cy.its.trafficSituation.domain.model.TrafficManualWeather)
	  */

	@Override
	public String save(TrafficManualWeather manualWeather) {
		return manualWeatherRepository.save(manualWeather);
	}

	/*
	  * <p>Title: delete</p>
	  * <p>Description: </p>
	  * @param id
	  * @return
	  * @see cy.its.trafficSituation.domain.service.IManualWeatherService#delete(java.lang.String)
	  */

	@Override
	public int delete(String id) {
		return manualWeatherRepository.delete(id);
	}

	/*
	  * <p>Title: update</p>
	  * <p>Description: </p>
	  * @param manualWeather
	  * @return
	  * @see cy.its.trafficSituation.domain.service.IManualWeatherService#update(cy.its.trafficSituation.domain.model.TrafficManualWeather)
	  */

	@Override
	public int update(TrafficManualWeather manualWeather) {
		return manualWeatherRepository.update(manualWeather);
	}

	/*
	  * <p>Title: selectAll</p>
	  * <p>Description: </p>
	  * @param map
	  * @return
	  * @see cy.its.trafficSituation.domain.service.IManualWeatherService#selectAll(java.util.Map)
	  */

	@Override
	public List<TrafficManualWeather> selectAll(Map<String, Object> map) {
		return manualWeatherRepository.selectAll(map);
	}

}

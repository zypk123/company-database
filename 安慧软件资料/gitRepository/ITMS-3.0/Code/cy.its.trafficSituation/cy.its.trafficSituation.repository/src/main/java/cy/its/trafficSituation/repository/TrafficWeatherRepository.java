/**
 * @Title: TrafficWeatherRepository.java
 * @Package cy.its.trafficSituation.repository
 * @Description: 气象仪基础设施层实现类
 * @author gyf guanyf@cychina.cn
 * @date 2015年10月28日 下午7:54:15
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.trafficSituation.domain.model.TrafficWeather;
import cy.its.trafficSituation.domain.repository.ITrafficWeatherRepository;
import cy.its.trafficSituation.mybatis.client.TrafficWeatherMapper;

@Service
public class TrafficWeatherRepository implements ITrafficWeatherRepository {

	@Autowired
	TrafficWeatherMapper trafficWeatherMapper;
	/*
	 * <p>Title: aggregateOfId</p> <p>Description: </p>
	 * 
	 * @param id
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 * @see cy.its.com.domain.IRepository#aggregateOfId(java.lang.String)
	 */

	@Override
	public TrafficWeather aggregateOfId(String id) throws Exception {
		return trafficWeatherMapper.selectByPrimaryKey(id);
	}

	/*
	 * <p>Title: save</p> <p>Description: </p>
	 * 
	 * @param obj
	 * 
	 * @return
	 * 
	 * @see cy.its.com.domain.IRepository#save(java.lang.Object)
	 */

	@Override
	public String save(TrafficWeather obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * <p>Title: delete</p> <p>Description: </p>
	 * 
	 * @param id
	 * 
	 * @return
	 * 
	 * @see cy.its.com.domain.IRepository#delete(java.lang.String)
	 */

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * <p>Title: update</p> <p>Description: </p>
	 * 
	 * @param obj
	 * 
	 * @return
	 * 
	 * @see cy.its.com.domain.IRepository#update(java.lang.Object)
	 */

	@Override
	public int update(TrafficWeather obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * <p>Title: selectWeathers</p> <p>Description: </p>
	 * 
	 * @param map
	 * 
	 * @return
	 * 
	 * @see cy.its.trafficSituation.domain.repository.ITrafficWeatherRepository#
	 * selectWeathers(java.util.Map)
	 */

	@Override
	public List<TrafficWeather> selectWeathers(Map<String, Object> map) {
		return trafficWeatherMapper.selectWeathers(map);
	}

	@Override
	public List<TrafficWeather> select2HourWeathers(Map<String, Object> map) {
		return trafficWeatherMapper.select2HourWeathers(map);
	}
	/*
	 * <p>Title: selectLastByNBR</p> <p>Description: </p>
	 * 
	 * @param deviceNbr
	 * 
	 * @return
	 * 
	 * @see cy.its.trafficSituation.domain.repository.ITrafficWeatherRepository#
	 * selectLastByNBR(java.lang.String)
	 */

	@Override
	public TrafficWeather getLastWeatherByNBR(String deviceNbr) {
		return trafficWeatherMapper.getLastWeatherByNBR(deviceNbr);
	}

}

package cy.its.trafficSituation.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.trafficSituation.domain.model.TrafficAlarmConfig;
import cy.its.trafficSituation.domain.repository.ITrafficAlarmConfigRepository;
import cy.its.trafficSituation.domain.service.ITrafficAlarmConfigService;
@Service
public class TrafficAlarmConfigService implements ITrafficAlarmConfigService {

	@Autowired
	ITrafficAlarmConfigRepository trafficAlarmRepository;
	public TrafficAlarmConfig trafficAlarmConfigOfId(String id) throws Exception {
		return trafficAlarmRepository.aggregateOfId(id);
	}

	public String save(TrafficAlarmConfig trafficAlarmConfig) {
		return trafficAlarmRepository.save(trafficAlarmConfig);
	}

	public int delete(String id) {
		return trafficAlarmRepository.delete(id);
	}

	public int update(TrafficAlarmConfig trafficAlarmConfig) {
		return trafficAlarmRepository.update(trafficAlarmConfig);
	}

	public List<TrafficAlarmConfig> selectAll(Map map) {
		return trafficAlarmRepository.selectAll(map);
	}

	/*
	  * <p>Title: selectByType</p>
	  * <p>Description: </p>
	  * @param alarmValueType
	  * @return
	  * @see cy.its.trafficSituation.domain.service.ITrafficAlarmConfigService#selectByType(java.lang.String)
	  */
	@Override
	public List<TrafficAlarmConfig> selectByType(String alarmValueType) {
		return trafficAlarmRepository.selectByType(alarmValueType);
	}

}

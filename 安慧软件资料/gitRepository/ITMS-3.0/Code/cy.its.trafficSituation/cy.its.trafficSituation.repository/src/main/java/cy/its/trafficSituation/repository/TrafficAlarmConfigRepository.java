package cy.its.trafficSituation.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cy.its.com.util.StringUtil;
import cy.its.trafficSituation.domain.model.TrafficAlarmConfig;
import cy.its.trafficSituation.domain.repository.ITrafficAlarmConfigRepository;
import cy.its.trafficSituation.mybatis.client.TrafficAlarmConfigMapper;


@Repository
public class TrafficAlarmConfigRepository implements ITrafficAlarmConfigRepository {

	@Autowired
	private TrafficAlarmConfigMapper trafficAlarmConfigMapper;
	
	public TrafficAlarmConfig aggregateOfId(String id) throws Exception {
		return trafficAlarmConfigMapper.selectByPrimaryKey(id);
	}
	public String save(TrafficAlarmConfig obj) {
		obj.setAlarmValueId(StringUtil.generateUUID());
		trafficAlarmConfigMapper.insert(obj);
		return obj.getAlarmValueId();
	}
	public int delete(String id) {
		return trafficAlarmConfigMapper.deleteByPrimaryKey(id);
	}
	public int update(TrafficAlarmConfig obj) {
		return trafficAlarmConfigMapper.updateByPrimaryKey(obj);
	}
	public List<TrafficAlarmConfig> selectAll(Map map) {
		return trafficAlarmConfigMapper.selectAll(map);
	}
	/*
	  * <p>Title: selectByType</p>
	  * <p>Description: </p>
	  * @param alarmValueType
	  * @return
	  * @see cy.its.trafficSituation.domain.repository.ITrafficAlarmConfigRepository#selectByType(java.lang.String)
	  */
	@Override
	public List<TrafficAlarmConfig> selectByType(String alarmValueType) {
		return trafficAlarmConfigMapper.selectByType(alarmValueType);
	}
}
